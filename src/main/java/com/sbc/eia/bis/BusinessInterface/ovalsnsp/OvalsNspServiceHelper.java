//$Id: OvalsNspServiceHelper.java,v 1.9 2008/02/29 23:27:30 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.util.Hashtable;
import java.util.Properties;

import com.att.lms.bis.service.ovals.OvalsPla;
import com.att.lms.bis.service.ovals.OvalsWrapper;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps.OVALSUSPS;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
import com.sbc.eia.bis.embus.service.access.ServiceHelperSelfTestException;
import com.sbc.eia.bis.embus.service.access.ServicePasswordDecrypter;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.OVALSNSPTypeImpl;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;

/**
 * @author Jean Duka
 */
public class OvalsNspServiceHelper extends ServiceHelper
{
    private final static String SERVICENAME = "OVALSNSP";
    private final static String OVALSNSP_REQUEST = "validateMsagAddress";
    private final static String EMBUS_MESSAGE_TAG = "embusMessageTag";
	private final static String VALIDATE_MSAG_ADDRESS ="validateMsagAddress";
    
    private Logger logger = null;
    private Hashtable properties;

    /**
     * Constructor for OvalsNspServiceHelper.
     * @param hashTable The list of properties for LIM.
     * @param logger The Logger object for constructing the base class.
     * @throws ServiceException
     */
    public OvalsNspServiceHelper(Hashtable hashTable, Logger logger)
        throws ServiceException 
    {
        super(hashTable, logger, SERVICENAME);
        this.logger = logger;
        this.properties = hashTable;
		
/*        setServiceAccess(
            new OvalsNspServiceAccess(
                	getEnvironmentName(),
                	getClientConfigFileName(),
                	logger));*/
    }
    
    /**
     * Method sendOvalsNspMsagValidationRequest.
     * @param destinationTag
     * @param request
     * @return OVALSNSPTypeImpl
     * @throws ServiceException
     */
    public OVALSNSPTypeImpl sendOvalsNspMsagValidationRequest(OVALSNSPTypeImpl request)
        throws ServiceException
    {
    	OVALSNSPTypeImpl resp = null;
    	try
    	{
	        Properties propsToSet = new Properties();
        	propsToSet.setProperty(EMBUS_MESSAGE_TAG, VALIDATE_MSAG_ADDRESS);

        	Object ovalsnspRespObj =
            	getServiceAccess().sendAndReceive(OVALSNSP_REQUEST, new Object[] {request}, propsToSet)[0];
            	
            resp = (OVALSNSPTypeImpl) ovalsnspRespObj;	
	   	}
    	catch(ServiceException e)
    	{
    		throw e;
    	}
    	catch(Exception e)
    	{
    		throw new ServiceException("Unexpected error encountered in OvalsNspServiceHelper.", e);
	   	}

    	return resp;
    }

    private StringOpt toStringOpt(String str) {
        StringOpt strOpt = new StringOpt();
        strOpt.theValue(str);
        return strOpt;
    }

    public BisContext selfTest(BisContext aContext) throws ServiceException {
        OVALSNSP ovalsnsp = new OVALSNSP(null, (Utility)this.logger, this.properties);

        Address aAddress = new Address();
        LongOpt aMaxAddressAlternatives = new LongOpt();
        FieldedAddress fieldedAddress = new FieldedAddress();
        fieldedAddress.aRoute = new StringOpt();
        fieldedAddress.aBox = new StringOpt();
        fieldedAddress.aHouseNumberPrefix = new StringOpt();
        fieldedAddress.aHouseNumber = toStringOpt("8239");
        fieldedAddress.aAssignedHouseNumber = new StringOpt();
        fieldedAddress.aHouseNumberSuffix = new StringOpt();
        fieldedAddress.aStreetDirection = new StringOpt();
        fieldedAddress.aStreetName = toStringOpt("GREENHOLLOW LN");
        fieldedAddress.aStreetThoroughfare = toStringOpt("DR");
        fieldedAddress.aStreetNameSuffix = new StringOpt();
        fieldedAddress.aCity = toStringOpt("DALLAS");
        fieldedAddress.aState = toStringOpt("TX");
        fieldedAddress.aPostalCode = toStringOpt("75240");
        fieldedAddress.aPostalCodePlus4 = new StringOpt();
        fieldedAddress.aCounty = new StringOpt();
        fieldedAddress.aCountry = new StringOpt();
        fieldedAddress.aStructureType = new StringOpt();
        fieldedAddress.aStructureValue = new StringOpt();
        fieldedAddress.aLevelType = new StringOpt();
        fieldedAddress.aLevelValue = new StringOpt();
        fieldedAddress.aUnitType = new StringOpt();
        fieldedAddress.aUnitValue = new StringOpt();
        fieldedAddress.aOriginalStreetDirection = new StringOpt();
        fieldedAddress.aOriginalStreetNameSuffix = new StringOpt();
        fieldedAddress.aCassAddressLines = new StringSeqOpt();
        fieldedAddress.aAuxiliaryAddressLines = new StringSeqOpt();
        fieldedAddress.aCassAdditionalInfo = new StringOpt();
        fieldedAddress.aAdditionalInfo = new StringOpt();
        fieldedAddress.aBusinessName = new StringOpt();
        fieldedAddress.aCountryCode = new StringOpt();
        fieldedAddress.aCityCode = new StringOpt();
        fieldedAddress.aServiceLocationName = new StringOpt();
        fieldedAddress.aAddressId = new StringOpt();
        fieldedAddress.aAliasName = new StringOpt();
        fieldedAddress.aAttention = new StringOpt();
        aAddress.aFieldedAddress(fieldedAddress);

        ProviderLocationPropertiesSeqOpt aProviderLocationProperties = new ProviderLocationPropertiesSeqOpt();
        ProviderLocationProperties[] props = new ProviderLocationProperties[1];
        ProviderLocationProperties prop = new ProviderLocationProperties();
        StringOpt provName = new StringOpt();
        provName.theValue("SBC");
        prop.aProviderName = provName;
        String[] locPropsToGet = new String[2];
        locPropsToGet[0] = "ServiceAddress";
        locPropsToGet[1] = "All";
        prop.aLocationPropertiesToGet = locPropsToGet;
        props[0] = prop;

        aProviderLocationProperties.theValue(props);
        LongOpt aMaxBasicAddressAlternatives = new LongOpt();
        aMaxBasicAddressAlternatives.theValue(56);
        LongOpt aMaxLivingUnitAlternatives = new LongOpt();
        aMaxLivingUnitAlternatives.theValue(70);
        StringOpt aExchangeCode = new StringOpt();

        try {
            RetrieveLocationForAddressReturn response =
                    ovalsnsp.retrieveLocationForAddress(aAddress,aProviderLocationProperties.theValue(),aMaxBasicAddressAlternatives,aMaxLivingUnitAlternatives,aExchangeCode);
        } catch (Exception e) {
            log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest failed. Exception message: " + e.getMessage());
            throw new ServiceHelperSelfTestException("ServiceHelper::selfTest failed! " + e.getMessage(),	e);
        }

        return aContext;
    }

    /**
     * Closes any existing messaging artifacts that may cause resource
     * leaks. Delegate to service access exit().
     */
    public void exit() 
    {
        getServiceAccess().exit();
    }
}
