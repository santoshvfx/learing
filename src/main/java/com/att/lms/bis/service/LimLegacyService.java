package com.att.lms.bis.service;

import com.att.aft.jms.FusionQueueConnectionFactory;
import com.att.aft.jms.FusionURIList;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.facades.lim.ejb.LimBean;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.Cingular.RetrieveServiceAreaByPostalCodeTrans;
import com.sbc.eia.bis.lim.transactions.FieldAddress.FieldAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationForAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationForServiceTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForPostalAddress.RetrieveLocationForPostalAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForServiceAddress.RetrieveLocationForServiceAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForTelephoneNumber.RetrieveLocationForTelephoneNumberTrans;
import com.sbc.eia.bis.lim.transactions.UpdateBillingAddress.UpdateBillingAddressTrans;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import org.apache.commons.lang3.StringUtils;

import javax.naming.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LimLegacyService extends LimBean {

    private RetrieveLocationForServiceAddressTrans cacheRetrieveLocationForServiceAddress = null;
    private RetrieveLocationForTelephoneNumberTrans cacheRetrieveLocationForTelephoneNumber = null;
    private RetrieveLocationForAddressTrans cacheRetrieveLocationForAddress = null;
    private RetrieveLocationForServiceTrans cacheRetrieveLocationForService = null;
    private RetrieveServiceAreaByPostalCodeTrans cacheRetrieveServiceAreaByPostalCode = null;
    private RetrieveLocationForPostalAddressTrans cacheRetrieveLocationForPostalAddress = null;
    private UpdateBillingAddressTrans cacheUpdateBillingAddress = null;
    private FieldAddressTrans cacheFieldAddress = null;

    public LimLegacyService() throws Exception {

        //PROPERTIES = new java.util.Properties();
        //Context cxt = new InitialContext();
        //String loc = (String) cxt.lookup("java:comp/env/" + PROP_FILE_LOCATION);
        String loc = "lim.properties";
        PROPERTIES = PropertiesFileLoader.read(loc, null);
        setPROPERTIES(PROPERTIES);
        getRulesFiles();
        decodePasswords();
        setCatalystEnvironment();
        initLIMBase();
        log(LogEventId.DEBUG_LEVEL_2, "LIM related properties loaded...");
    }

    public static Map<String,Object> toMap(Context ctx) throws NamingException {
        String namespace = /*ctx instanceof InitialContext ? ctx.getNameInNamespace() :*/ "";
        HashMap<String, Object> map = new HashMap<String, Object>();
        System.out.println("> Listing namespace: " + namespace);
        NamingEnumeration<NameClassPair> list = ctx.list(namespace);
        while (list.hasMoreElements()) {
            NameClassPair next = list.next();
            String name = next.getName();
            String jndiPath = namespace + name;
            Object lookup;
            try {
                System.out.println("> Looking up name: " + jndiPath);
                Object tmp = ctx.lookup(jndiPath);
                if (tmp instanceof Context) {
                    lookup = toMap((Context) tmp);
                } else {
                    lookup = tmp;
                    System.out.println("Entry: " + tmp + ", : class: " + tmp.getClass());
                }
            } catch (Throwable t) {
                lookup = t.getMessage();
            }
            map.put(jndiPath, lookup);

        }
        return map;
    }

    public static UnfieldedAddress parseUnfieldedAddress (UnfieldedAddress address, BisContext aBisContext) {

        if (isNull(address.getABusinessName()) && isNull(address.getACity()) && isNull(address.getAState()) &&
                isNull(address.getAPostalCode()) && isNull(address.getAPostalCodePlus4())) {
            try {
                boolean hasBN2 = false;

                //Dynamically handle MaxCassCharPerLine
                ObjectProperty[] props = aBisContext.getAProperties();
                for (ObjectProperty prop : props) {
                    if ("BN2_KEYED".equals(prop.getATag())) {
                        hasBN2 = Boolean.parseBoolean(prop.getAValue().trim());
                        break;
                    }
                }

                if (address.getAAddressLines() != null && !address.getAAddressLines().is__uninitialized()) {
                    String[] addressLines = address.getAAddressLines().theValue();
                    String lastLine = addressLines[addressLines.length-1];

                    if (address.getABusinessName() == null || address.getABusinessName().is__uninitialized()) {
                        StringOpt aBusinessName = new StringOpt();
                        aBusinessName.theValue(addressLines[0].trim());
                        address.setABusinessName(aBusinessName);
                    }

                    if (hasBN2 && (address.getAAttention() == null || address.getAAttention().is__uninitialized())) {
                        StringOpt aAttention = new StringOpt();
                        aAttention.theValue(addressLines[1].trim());
                        address.setAAttention(aAttention);
                    }

                    String newAddressLines[] = {"", "", ""};

                    int addressIndex = hasBN2 ? 2 : 1;

                    if (addressLines.length >= 3) {
                        newAddressLines[0] = addressLines[addressIndex].trim();
                    }

                    if (addressLines.length >= 4) {
                        newAddressLines[1] = addressLines[addressIndex+1].trim();
                    }

                    if (addressLines.length >= 5 && !hasBN2) {
                        newAddressLines[2] = addressLines[addressIndex+2].trim();
                    }

                    StringSeqOpt aAddressLines = new StringSeqOpt();
                    aAddressLines.theValue(newAddressLines);

                    address.setAAddressLines(aAddressLines);

                    String[] addressTokens = lastLine.replaceAll("[,\\u00A0-]"," ").split("\\s+");
                    int i = addressTokens.length-1;

                    //Parse postal code +4
                    if (addressTokens[i].matches("\\d{4}")) {
                        if (isNull(address.getAPostalCodePlus4())) {
                            StringOpt aPostalCodePlus4 = new StringOpt();
                            aPostalCodePlus4.theValue(addressTokens[i]);
                            address.setAPostalCodePlus4(aPostalCodePlus4);
                            i--;
                        }
                    }

                    //Parse postal code
                    if (addressTokens[i].matches("\\d{5}")) {
                        if (isNull(address.getAPostalCode())) {
                            StringOpt aPostalCode = new StringOpt();
                            aPostalCode.theValue(addressTokens[i]);
                            address.setAPostalCode(aPostalCode);
                            i--;
                        }
                    }

                    //Parse state
                    if (addressTokens[i].matches("[a-zA-z]{2}")) {
                        if (isNull(address.getAState())) {
                            StringOpt aState = new StringOpt();
                            aState.theValue(addressTokens[i]);
                            address.setAState(aState);
                            i--;
                        }
                    }

                    if (isNull(address.getACity())) {
                        ArrayList<String> remainingTokens = new ArrayList<String>();
                        for (int j = 0; j <= i; j++) {
                            remainingTokens.add(addressTokens[j]);
                        }

                        String city = String.join(" ", remainingTokens);

                        StringOpt aCity = new StringOpt();
                        aCity.theValue(city);
                        address.setACity(aCity);
                    }
                }
            } catch (Exception e) {
                System.err.println("Could not parse unfielded address: " + e.toString());
            }
        }

        return address;
    }

    private static boolean isNull(StringOpt stringOpt) {
        try {
            return stringOpt == null || stringOpt.is__uninitialized() || StringUtils.isEmpty(stringOpt.theValue());
        } catch (Exception e) {
            return stringOpt == null || stringOpt.is__uninitialized() || StringUtils.isEmpty(stringOpt.get___theValue());
        }
//        return stringOpt == null || stringOpt.is__uninitialized() || StringUtils.isEmpty(stringOpt.get___theValue()) || stringOpt.get___theValue() == null;
    }
}