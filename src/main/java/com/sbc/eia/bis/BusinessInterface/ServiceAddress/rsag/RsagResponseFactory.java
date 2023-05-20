//$Id: RsagResponseFactory.java,v 1.12 2007/11/07 19:40:33 jd3462 Exp $
package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.Properties;

import com.bellsouth.cbs.order.ws.CbsOrderAddressResponseV7;
import com.bellsouth.cbs.order.ws.CbsOrderServiceAddressV7;
import com.bellsouth.cbs.order.ws.CbsStatusMessageV7;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;

/**
 * @author jd3462
 */
public class RsagResponseFactory 
{
    private LIMBase aLimBase = null;
    private RsagRetrieveLocReq aRsagRetrieveLocReq = null;
    
    public static final String DEFAULT_CBS_ERROR_TEXT = "Unexpected CBS response received!";
    
    private static ArrayList match_msgid_arraylist = null;
    private static ArrayList matchTNMenu_msgid_arraylist = null;
    private static ArrayList alt_msgid_arraylist = null;
    private static ArrayList range_msgid_arraylist = null;
    private static ArrayList excp_msgid_arraylist = null;

    /* initializer for match_msgid_arraylist */
    static {
        match_msgid_arraylist = new ArrayList();
        
        match_msgid_arraylist.add("0000"); //Contract A
        match_msgid_arraylist.add("I901"); //Contract B
        match_msgid_arraylist.add("I902");
        match_msgid_arraylist.add("I903");
        match_msgid_arraylist.add("I904");
        match_msgid_arraylist.add("W901");
        match_msgid_arraylist.add("W902");
        match_msgid_arraylist.add("W903");
        match_msgid_arraylist.add("W904");
        match_msgid_arraylist.add("W905");
        match_msgid_arraylist.add("W907");
        match_msgid_arraylist.add("W909");
        match_msgid_arraylist.add("W910");
        match_msgid_arraylist.add("W911");
        match_msgid_arraylist.add("W912");
        match_msgid_arraylist.add("W915");
        match_msgid_arraylist.add("W918");
        match_msgid_arraylist.add("W921");
        match_msgid_arraylist.add("W922");
        match_msgid_arraylist.add("W923");
        match_msgid_arraylist.add("W924");
        match_msgid_arraylist.add("W925");
        match_msgid_arraylist.add("W926");
        match_msgid_arraylist.add("W927");
        match_msgid_arraylist.add("W929");
        match_msgid_arraylist.add("W930");
        match_msgid_arraylist.add("W931");
        match_msgid_arraylist.add("W932");
        match_msgid_arraylist.add("W933");
        match_msgid_arraylist.add("W934");
        match_msgid_arraylist.add("W935");
        match_msgid_arraylist.add("W936");
        match_msgid_arraylist.add("W937");
        match_msgid_arraylist.add("W938");
        match_msgid_arraylist.add("W943");
    }
    
    /* initializer for matchTNMenu_msgid_arraylist */
    static {
        matchTNMenu_msgid_arraylist = new ArrayList();
        
        matchTNMenu_msgid_arraylist.add("E921"); //Contract N for RLFTN
        matchTNMenu_msgid_arraylist.add("E967"); 
    }

        /* initializer for alt_msgid_arraylist */
    static {
        alt_msgid_arraylist = new ArrayList();
        
        alt_msgid_arraylist.add("E901"); //Contract E
        alt_msgid_arraylist.add("E931");
        alt_msgid_arraylist.add("E903"); //Contract G
        alt_msgid_arraylist.add("E911"); //Contract J
        alt_msgid_arraylist.add("E942");
        alt_msgid_arraylist.add("E919"); //Contract K
        alt_msgid_arraylist.add("E920"); //Contract L
        alt_msgid_arraylist.add("E922");
        alt_msgid_arraylist.add("E929");
        alt_msgid_arraylist.add("E930");
        alt_msgid_arraylist.add("E949");
        alt_msgid_arraylist.add("W908"); //Contract O
        alt_msgid_arraylist.add("E908"); //Contract O
        alt_msgid_arraylist.add("E958");
        alt_msgid_arraylist.add("E961");
        alt_msgid_arraylist.add("E962");
    }
    
    /* initializer for range_msgid_arraylist */
    static {
        range_msgid_arraylist = new ArrayList();
        
        range_msgid_arraylist.add("E902"); //Contract F
        range_msgid_arraylist.add("E951");
        range_msgid_arraylist.add("E905"); //Contract H
        range_msgid_arraylist.add("E940");
        range_msgid_arraylist.add("E952");
        range_msgid_arraylist.add("E955");
        range_msgid_arraylist.add("W906"); //Contract M
    }

    /* initializer for excp_msgid_arraylist */
    static {
        excp_msgid_arraylist = new ArrayList();
        
        excp_msgid_arraylist.add("I913"); //Contract C
        excp_msgid_arraylist.add("W913");
        excp_msgid_arraylist.add("W939");
        excp_msgid_arraylist.add("E904");
        excp_msgid_arraylist.add("E909");
        excp_msgid_arraylist.add("E912");
        excp_msgid_arraylist.add("E914");
        excp_msgid_arraylist.add("E915");
        excp_msgid_arraylist.add("E916");
        excp_msgid_arraylist.add("E923");
        excp_msgid_arraylist.add("E924");
        excp_msgid_arraylist.add("E928");
        excp_msgid_arraylist.add("E932");
        excp_msgid_arraylist.add("E943");
        excp_msgid_arraylist.add("E910"); //Contract I
        excp_msgid_arraylist.add("E941");
    }

    /**
     * Constructor for RsagResponseFactory
     * @param limBase
     */
    public RsagResponseFactory(LIMBase limBase)
    {
        aLimBase = limBase;
    }
    
    /**
     * @param retrieveLocReq
     * @param cbsResponse
     * @return
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
 /*   public RetrieveLocResp getRetrieveLocationResponseForAddress(
            RsagRetrieveLocReq retrieveLocReq, 
            CbsOrderAddressResponseV7 cbsResponse)
        throws
            SystemFailure, 
            InvalidData,
            ObjectNotFound,
            BusinessViolation,
            AccessDenied,
            NotImplemented,
            DataNotFound
    {
        RetrieveLocResp rv = null;
        String errMsgText = DEFAULT_CBS_ERROR_TEXT;
        
        if (cbsResponse != null 
            && cbsResponse.addresses.size() > 0 
            && cbsResponse.getServiceResponseV7().messages.size() > 0)
        {
            if (cbsResponse.getServiceResponseV7().getStatus()Code == 1)
            {
                for (int i=0; i < cbsResponse.getServiceResponseV7().messages.size(); i++)
                {
                    if (match_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        rv = new RsagHitResp(aLimBase, 
                                             retrieveLocReq, 
                                             cbsResponse.getAddresses().get(0), 
                                             cbsResponse.getServiceResponseV7().getMessages().get(i));
                        break;
                    }
                    else if (alt_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        rv = new RsagAddrListResp(aLimBase, 
                                                  cbsResponse.addresses, 
                                                  cbsResponse.getServiceResponseV7().getMessages().get(i),
                                                  retrieveLocReq);
                        break;
                    }
                    else if (range_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        rv = new RsagAddrRangeResp(aLimBase, 
                                                   cbsResponse.addresses, 
                                                   cbsResponse.getServiceResponseV7().getMessages().get(i),
                                                   retrieveLocReq.getMaxBasicAddressAlternatives());
                        break;
                    }
                    else if (excp_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        handleRsagAddressError(cbsResponse.addresses, cbsResponse.getServiceResponseV7().getMessages().get(i));
                    }
                    else
                    {
                        if (!cbsResponse.getServiceResponseV7().getMessages().get(i).getCode().equalsIgnoreCase("TRANSID"))
                        {
                            errMsgText = "CBS MessageID: " + cbsResponse.getServiceResponseV7().getMessages().get(i).getCode() + "CBS MessageText: " + cbsResponse.getServiceResponseV7().getMessages().get(i).getText();
                        }
                    }
                }
            }
            //else if (cbsResponse.getServiceResponseV7().getStatus()Code == 3)
            else
            {
                handleRsagException(null, 
                        "CBS Edit", 
                        cbsResponse.getServiceResponseV7().getMessages().get(0).getCode(), 
                        cbsResponse.getServiceResponseV7().getMessages().get(0).getText());
            }
        }
        //should never land here because CBS/RSAG would throw an exception
        if (rv == null)
        {
            handleRsagException(null, null, null, errMsgText);
        }
        
        return rv;
    }
*/    
    public RetrieveLocResp getRetrieveLocationResponseForAddress(
            RsagRetrieveLocReq retrieveLocReq, 
            CbsOrderAddressResponseV7 cbsResponse)
        throws
            SystemFailure, 
            InvalidData,
            ObjectNotFound,
            BusinessViolation,
            AccessDenied,
            NotImplemented,
            DataNotFound
    {
        RetrieveLocResp rv = null;
        String errMsgText = DEFAULT_CBS_ERROR_TEXT;
        
        if (cbsResponse != null 
            && cbsResponse.getAddresses().size() > 0 
            && cbsResponse.getServiceResponseV7().getMessages().size() > 0)
        {
            if (cbsResponse.getServiceResponseV7().getStatusCode() == 1)
            {
                for (int i=0; i < cbsResponse.getServiceResponseV7().getMessages().size(); i++)
                {
                    if (match_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        rv = new RsagHitResp(aLimBase, 
                                             retrieveLocReq, 
                                             cbsResponse.getAddresses().get(0), 
                                             cbsResponse.getServiceResponseV7().getMessages().get(i));
                        break;
                    }
                    else if (alt_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        CbsOrderServiceAddressV7[] cbsGetAddressArray = cbsResponse.getAddresses().toArray(new CbsOrderServiceAddressV7[cbsResponse.getAddresses().size()]);
                        rv = new RsagAddrListResp(aLimBase,
                                                  cbsGetAddressArray,
                                                  cbsResponse.getServiceResponseV7().getMessages().get(i),
                                                  retrieveLocReq);
                        break;
                    }
                    else if (range_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        rv = new RsagAddrRangeResp(aLimBase, 
                        		((CbsOrderServiceAddressV7[])cbsResponse.getAddresses().toArray()), 
                                cbsResponse.getServiceResponseV7().getMessages().get(i),
                                retrieveLocReq.getMaxBasicAddressAlternatives());

                        break;
                    }
                    else if (excp_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        handleRsagAddressError(((CbsOrderServiceAddressV7[]) cbsResponse.getAddresses().toArray()), cbsResponse.getServiceResponseV7().getMessages().get(i));
                    }
                    else
                    {
                        if (!cbsResponse.getServiceResponseV7().getMessages().get(i).getCode().equalsIgnoreCase("TRANSID"))
                        {
                            errMsgText = "CBS MessageID: " + cbsResponse.getServiceResponseV7().getMessages().get(i).getCode() + "CBS MessageText: " + cbsResponse.getServiceResponseV7().getMessages().get(i).getText();
                        }
                    }
                }
            }
            //else if (cbsResponse.getServiceResponseV7().getStatus()Code == 3)
            else
            {
                handleRsagException(null, 
                        "CBS Edit", 
                        cbsResponse.getServiceResponseV7().getMessages().get(0).getCode(), 
                        cbsResponse.getServiceResponseV7().getMessages().get(0).getText());
            }
        }
        //should never land here because CBS/RSAG would throw an exception
        if (rv == null)
        {
            handleRsagException(null, null, null, errMsgText);
        }
        
        return rv;
    }
    
    /**
     * @param retrieveLocReq
     * @param cbsResponse
     * @return
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */ 
    public RetrieveLocResp getRetrieveLocationResponseForTN(
            RsagRetrieveLocReq retrieveLocReq, 
            CbsOrderAddressResponseV7 cbsResponse)
        throws
            SystemFailure, 
            InvalidData,
            ObjectNotFound,
            BusinessViolation,
            AccessDenied,
            NotImplemented,
            DataNotFound
    {
        RetrieveLocResp rv = null;
        String errMsgText = DEFAULT_CBS_ERROR_TEXT;
        boolean wsopi = false;
        
        if (cbsResponse != null 
            && cbsResponse.getAddresses().size() > 0 
            && cbsResponse.getServiceResponseV7().getMessages().size() > 0)
        {
            if (cbsResponse.getServiceResponseV7().getStatusCode() == 1)
            {
                //i=serviceResponse.messages counter
                for (int i=0; i < cbsResponse.getServiceResponseV7().getMessages().size(); i++)
                {
                    if (match_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        wsopi = false;
                        //only take the first address if exact match msgId 
                        if (cbsResponse.getAddresses().get(0).getNetworkFacilities() != null)
                        {
                            //k=network facilities counter
                            for (int j = 0; j < cbsResponse.getAddresses().get(0).getNetworkFacilities().size(); j++)
                            {
                                //Take the frist TN found that has a status value equal to W
                                if (cbsResponse.getAddresses().get(0).getNetworkFacilities().get(j).getInstalledTN() != null &&
                                    cbsResponse.getAddresses().get(0).getNetworkFacilities().get(j).getInstalledTN().getStatus() != null &&
                                    cbsResponse.getAddresses().get(0).getNetworkFacilities().get(j).getInstalledTN().getStatus().equalsIgnoreCase("W") &&
                                    cbsResponse.getAddresses().get(0).getNetworkFacilities().get(j).getInstalledTN().getTn() != null)
                                {
                                    wsopi = true;
                                    break;
                                }
                            }
                        }
                        if (wsopi)
                        {
                            rv = new RsagHitResp(aLimBase, 
                                    retrieveLocReq, 
                                    cbsResponse.getAddresses().get(0), 
                                    cbsResponse.getServiceResponseV7().getMessages().get(i));
                        }
                        else
                        {
                            handleRsagException(LIMTag.CSV_TnServiceError, "Invalid TN", null, null);
                        }
                        break;
                    }
                    /*When the Menu of Address Telephone Response (Contract N, multiple addresses found) is returned
                     *for input Telephone Number (TN) from CBS response, LIM BIS shall not return all the addresses
                     *to the client.  Return the first address found with a TN status of W.
                     */
                    else if (matchTNMenu_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        wsopi = false;
                        int j; //addresses counter
                        for (j = 0; j < cbsResponse.getAddresses().size(); j++)
                        {
                            if (cbsResponse.getAddresses().get(j).getNetworkFacilities() != null)
                            {
                                //k=network facilities counter
                                for (int k = 0; k < cbsResponse.getAddresses().get(j).getNetworkFacilities().size(); k++)
                                {
                                    //Take the frist TN found that has a status value equal to W
                                    if (cbsResponse.getAddresses().get(j).getNetworkFacilities().get(k).getInstalledTN() != null &&
                                        cbsResponse.getAddresses().get(j).getNetworkFacilities().get(k).getInstalledTN().getStatus() != null &&
                                        cbsResponse.getAddresses().get(j).getNetworkFacilities().get(k).getInstalledTN().getStatus().equalsIgnoreCase("W") &&
                                        cbsResponse.getAddresses().get(j).getNetworkFacilities().get(k).getInstalledTN().getTn() != null)
                                    {
                                        wsopi = true;
                                        break;
                                    }
                                }
                            }
                            if (wsopi)
                            {
                                break;
                            }
                        }
                        if (wsopi)
                        {
                            rv = new RsagHitResp(aLimBase, 
                                                 retrieveLocReq, 
                                                 cbsResponse.getAddresses().get(j), 
                                                 cbsResponse.getServiceResponseV7().getMessages().get(i));
                            break;
                        }
                        else
                        {
                            handleRsagException(LIMTag.CSV_TnServiceError, "Invalid TN", null, null);
                        }
                    }
                    else if (excp_msgid_arraylist.contains (cbsResponse.getServiceResponseV7().getMessages().get(i).getCode()))
                    {
                        handleRsagException(cbsResponse.getServiceResponseV7().getMessages().get(i).getCode(),
                                "TN", 
                                cbsResponse.getServiceResponseV7().getMessages().get(i).getCode(),
                                cbsResponse.getServiceResponseV7().getMessages().get(i).getText());
                      
                    }
                    else
                    {
                        if (!cbsResponse.getServiceResponseV7().getMessages().get(i).getCode().equalsIgnoreCase("TRANSID"))
                        {
                            errMsgText = "CBS MessageID: " + cbsResponse.getServiceResponseV7().getMessages().get(i).getCode() + "CBS MessageText: " + cbsResponse.getServiceResponseV7().getMessages().get(i).getText();
                        }
                    }
                }
            }
            //else if (cbsResponse.getServiceResponseV7().getStatus()Code == 3)
            else
            {
                handleRsagException(null, 
                        "CBS Edit", 
                        cbsResponse.getServiceResponseV7().getMessages().get(0).getCode(), 
                        cbsResponse.getServiceResponseV7().getMessages().get(0).getText());
            }
        }
        //should never land here because RSAG would throw an exception
        if (rv == null)
        {
            handleRsagException(null, null, null, errMsgText);
        }
        
        return rv;
    }
    /**
     * @param cbsServiceAddress
     * @param cbsStatusMessage
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
    private void handleRsagAddressError(
            CbsOrderServiceAddressV7 cbsServiceAddress[], 
            CbsStatusMessageV7 cbsStatusMessage)
        throws 
            SystemFailure,
            InvalidData,
            ObjectNotFound,
            BusinessViolation,
            AccessDenied,
            NotImplemented,
            DataNotFound
    {
        String msgText = cbsStatusMessage.getText();
        String unitPattern = "";
        String elevationPattern = "";
        String structurePattern = "";
        boolean addUnitSeparator = false;
        boolean addElevationSeparator = false;
        boolean addStructureSeparator = false;

        if (cbsStatusMessage.getCode().equalsIgnoreCase("E910") 
            || cbsStatusMessage.getCode().equalsIgnoreCase("E941"))
        {
            for (int i=0; i < cbsServiceAddress[0].getBasicAddr().getLocationStandards().size(); i++)
            {
                if ((cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getUnitEditPattern().length() > 0)
                    && (cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getUnitType() != 0))

                {
                    if (addUnitSeparator)
                    {
                        unitPattern += ",";
                    }
                    unitPattern += translateCbsUnitType(cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getUnitType()) + 
                        "=" + cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getUnitEditPattern();
                    addUnitSeparator = true;
                }
                if ((cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getElevationEditPattern().length() > 0)
                    && (cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getElevationType() != 0))
                {
                    if (addElevationSeparator)
                    {
                        elevationPattern += ",";
                    }
                    elevationPattern += translateCbsElevationType(cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getElevationType()) + 
                    "=" + cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getElevationEditPattern();
                    addElevationSeparator = true;
                }
                if ((cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getStructureEditPattern().length() > 0)
                    && (cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getStructureType() != 0))
                {
                    if (addStructureSeparator)
                    {
                        structurePattern += ",";
                    }
                    structurePattern += translateCbsStructureType(cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getStructureType()) + 
                    "=" + cbsServiceAddress[0].getBasicAddr().getLocationStandards().get(i).getStructureEditPattern();
                    addStructureSeparator = true;
                }
            }
            if (unitPattern.trim().length() > 0)
            {
                msgText += " Valid UnitEditPattern=<" + unitPattern.trim() + ">";
            }
            if (elevationPattern.trim().length() > 0)
            {
                msgText += " Valid .get.getElevationEditPattern()()=<" + elevationPattern.trim() + ">";
            }
            if (structurePattern.trim().length() > 0)
            {
                msgText += " Valid StructureEditPattern=<" + structurePattern.trim() + ">";
            }
        }
        handleRsagException(cbsStatusMessage.getCode(), 
                            "ADDRESS", 
                            cbsStatusMessage.getCode(), 
                            msgText);
    }
    /**
     * @param code
     * @param text
     * @param msgId
     * @param msgText
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
    private void handleRsagException(String code, String text, String msgId, String msgText) 
        throws 
            SystemFailure,
            InvalidData,
            ObjectNotFound,
            BusinessViolation,
            AccessDenied,
            NotImplemented,
            DataNotFound
    {
        ExceptionBuilderResult exBldReslt = null;
        
        Properties tagValues = new Properties();
        tagValues.setProperty("CODE", msgId == null ? "No code available" : msgId);
        tagValues.setProperty("MSG", msgText == null ? "No message available" : msgText);
        
        exBldReslt =
                ExceptionBuilder.parseException(
                    getLimBase().getCallerContext(),
                    getLimBase().getRsagRulesFile(),
                    "",
                    code,
                    text,
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getLimBase(),
                    "RSAG",
                    Severity.UnRecoverable,
                    tagValues);
    }

    /**
     * @param aCbsStructureType
     * @return
     */
    private String translateCbsStructureType(int aCbsStructureType)
    {
        String structureType = "";
        if (aCbsStructureType == 1)
            structureType = "BLDG";
        if (aCbsStructureType == 2)
            structureType = "PIER";
        if (aCbsStructureType == 3)
            structureType = "WING";
        return structureType;
    }
    /**
     * @param aCbsElevationType
     * @return
     */
    private String translateCbsElevationType(int aCbsElevationType)
    {
        String elevationType = "";
        if (aCbsElevationType == 1)
            elevationType = "FLR";
        return elevationType;
    }
    /**
     * @param aCbsUnitType
     * @return
     */
    private String translateCbsUnitType(int aCbsUnitType)
    {
        String unitType = "";
        if (aCbsUnitType == 1)
            unitType = "APT";
        if (aCbsUnitType == 2)
            unitType = "RM";
        if (aCbsUnitType == 3)
            unitType = "LOT";
        if (aCbsUnitType == 4)
            unitType = "SLIP";
        if (aCbsUnitType == 5)
            unitType = "SUIT";
        if (aCbsUnitType == 6)
            unitType = "UNIT";
        return unitType;
    }
    /**
     * @return Returns the aLimBase.
     */
    private LIMBase getLimBase()
    {
        return aLimBase;
    }
}
