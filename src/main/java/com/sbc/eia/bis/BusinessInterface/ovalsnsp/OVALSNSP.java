//$Id: OVALSNSP.java,v 1.13.12.1 2011/08/20 00:10:27 dc860q Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.util.Hashtable;
import java.util.Properties;

import com.att.lms.bis.service.ovals.OvalsPla;
import com.att.lms.bis.service.ovals.OvalsWrapper;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationBase;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author gg2712
 */
public class OVALSNSP extends Host implements LocationI
{
	private static final String HostList[] = null;
	private OvalsNspController controller = null;
	private String ruleFile = null;
    private ExceptionBuilderResult exBldReslt = null;
    private ServiceAddressValidationHelper aServiceAddressValidationHelper = null;
    private Utility aUtility;
    private Hashtable aProperties;

    /**
     * Constructor.
     */
	public OVALSNSP(Company aCompany, Utility aUtility,Hashtable aProperties)
	{		
		super(aCompany, aUtility, aProperties);
		ruleFile = (String) getProperties().get(LIMTag.CSV_FileName_OVALS_NSP);
        this.aUtility = aUtility;
        this.aProperties = aProperties;
	}

    /**
     * @see com.sbc.eia.bis.BusinessInterface.location.LocationI#retrieveLocationForAddress(Address, ProviderLocationProperties[], LongOpt, LongOpt)
     */
    public RetrieveLocationForAddressReturn retrieveLocationForAddress(
        Address aAddress,
        ProviderLocationProperties[] aLocationPropertiesToGet,
        LongOpt aMaximumBasicAddressReturnLimit,
        LongOpt aMaximumUnitReturnLimit,
        StringOpt aExchangeCode)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
    {
    	RetrieveLocationForAddressReturn result = null;

        OvalsWrapper wrapper = new OvalsWrapper(aUtility, null, null, new OvalsPla(aProperties), null);

        result = wrapper.retrieveLocationForAddressPla(
                aAddress,
                aLocationPropertiesToGet,
                aMaximumBasicAddressReturnLimit,
                aMaximumUnitReturnLimit);

        return result;
    	/*
    	try
    	{
    		AddressHandlerOvalsNSP nspAddr = new AddressHandlerOvalsNSP(aAddress);
    		String originalCityName = nspAddr.getCity();
			OvalsNspRetrieveLocReq request = null;
			
			//PR 23126571: Set AdditionalInfo and AssignedHouseNumber to blank
			//to bypass the validation rules in retrieveLocationForServiceAddress
			//Since AddressHandler just use a object reference to aAddress, any "set" field change
			//in AddressHandler will also change the value in aAddress object.
			nspAddr.setAddAddrInfo("");
			nspAddr.setAssignedHouseNumber("");
			
			request = new OvalsNspRetrieveLocReq(getRetrieveLocationBase().limBase, nspAddr, aLocationPropertiesToGet, aExchangeCode, getMaxValue(aMaximumBasicAddressReturnLimit));
			request.logAddressReq();
			
//    		if (nspAddr.getUnitType().length() > 0 ||
//    			nspAddr.getUnitValue().length() > 0 ||
//    			nspAddr.getStructType().length() > 0 ||
//    			nspAddr.getStructValue().length() > 0 ||
//    			nspAddr.getLevelType().length() > 0 ||
//    		    nspAddr.getLevelValue().length() > 0)
//    		{
//    		    //If address contains supplemental info, do service adddress validation first
//    			//CR 53263: LIM BIS will return the validated MSAG address and echo back the client’s original living unit data in the LIM BIS exact match response
//    			ServiceAddressValidationHelper savHelper = 
//    				new ServiceAddressValidationHelper(getRetrieveLocationBase().limBase);
//    		    try
//    		    {
//    		        savHelper.doServiceAddressValidation(
//    				    aAddress,
//    				    aLocationPropertiesToGet,
//    				    aMaximumBasicAddressReturnLimit,
//    				    aMaximumUnitReturnLimit); 
//    			
//    		        if(savHelper.isExactMatch())
//    		        {
//    		            // If exactMatch, use returned address from Regional system to create AddressHandlerOvalsNSP
//    		            // and OvalsNSPRetrieveLocReq objects and send to OVALS MSG as usual 
//    		            nspAddr = new AddressHandlerOvalsNSP(savHelper.getExactMatchAddress());    				
//    		            request = new OvalsNspRetrieveLocReq(getRetrieveLocationBase().limBase, nspAddr, aLocationPropertiesToGet, aExchangeCode, getMaxValue(aMaximumBasicAddressReturnLimit));
//    		            request.logAddressReq();
//    		        }
//    		        else if (savHelper.isListAddress())
//    		        {
//    		            //return alternative list Addresses to client now
//    		            return savHelper.getRlfaReturn();
//    		        }
//    		        else
//    		        {
//    		            //Ranged address exception will be covered by ServiceAddressValidationHelper
//    		            //Not exact or list address return 
//    		            getRetrieveLocationBase().limBase.log(
//			                LogEventId.DEBUG_LEVEL_2,
//							"OVALSNSP::retrieveLocationForAddress(): not exact or list address return.");
//
//    		            OvalsNspResponseFactory.handleMsagValidationAddressSAGNotMatchMSAG(getRetrieveLocationBase().limBase);
//    		        }
//    		    }
//    		    finally
//    		    {
//    		        savHelper = null;
//    		    }
//    		}
//    		else
//    		{
//    			request = new OvalsNspRetrieveLocReq(getRetrieveLocationBase().limBase, nspAddr, aLocationPropertiesToGet, aExchangeCode, getMaxValue(aMaximumBasicAddressReturnLimit));
//    			request.logAddressReq();
//    		}
        	
        	OvalsNspResponseHelper response = getOvalsNspController().processOvalsNspMsagValidation(request);
			 
			result = (OvalsNspResponseFactory.handleOvalsNspMsagValidation(
						getRetrieveLocationBase().limBase,
						response,
						request,
						originalCityName,
						getMaxValue(aMaximumBasicAddressReturnLimit),
						getMaxValue(aMaximumUnitReturnLimit)))
							.toAddressReturn();
        }
		catch (AddressHandlerException ahe)
        {
            exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_AddressHandlerError,
                        ahe.getMessage(),
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "OVALS_NSP",
                        Severity.UnRecoverable,
                        null);
        }
        return result;*/
    }
  
  	
    /**
     * @see com.sbc.eia.bis.BusinessInterface.location.LocationI#retrieveLocationForService(TN, ProviderLocationProperties[], LongOpt, LongOpt)
     */
    public RetrieveLocationForServiceReturn retrieveLocationForService(
        TN aService,
        ProviderLocationProperties[] aLocationPropertiesToGet,
        LongOpt aMaximumBasicAddressReturnLimit,
        LongOpt aMaximumUnitReturnLimit)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
    {
    	RetrieveLocationForServiceReturn result = null;
        String limRuleFile = (String) getProperties().get(LIMTag.CSV_FileName_LIM);
        
        exBldReslt = 
                ExceptionBuilder.parseException(
                        getBisContext(),
                        limRuleFile,
                        "",
                        LIMTag.CSV_InternalError,
                        "Not Implemented",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "OVALS_NSP",
                        Severity.UnRecoverable,
                        null);
    
        return result;
    }

    /**
     * Getter method for the BisContext.
     * @return BisContext
     */
    public BisContext getBisContext()
    {
        return getRetrieveLocationBase().limBase.getCallerContext();
    }

    /**
     * Getter for the RetrieveLocationBase object.
     * @return RetrieveLocationBase
     */
    protected RetrieveLocationBase getRetrieveLocationBase()
    {
        return (RetrieveLocationBase) getPassThru();
    }

    /**
     * Getter method for the Utility object.
     * @return Utility
     */
    public Utility getUtility()
    {
        return utility;
    }

    /**
     * Getter method for the Utility object.
     * @return Utility
     */
    public OvalsNspController getOvalsNspController()
    {
    	if (controller == null) 
    	{
			controller = new OvalsNspController(getRetrieveLocationBase().limBase);
		}
		return controller;	
	}

    /**
     * Extract the int value from a max-value LongOpt object.
     * @return int
     * @param max LongOpt
     */
    protected int getMaxValue(LongOpt max)
    {
        try
        {
            if (max != null)
                return max.theValue();
        }
        catch (org.omg.CORBA.BAD_OPERATION bo) {}
    
        return 0;
    }
    
    /**
     * Return a list of Selectors of company/state combinations that this class supports.
     * Creation date: (3/19/01 11:25:58 AM)
     * @return Selector[]
     * @param util Utility a utility object providing logging functions
     * @exception InvalidStateException attempted to create an invalid State
     * @exception NullDataException a required value is null
     * @exception InvalidCompanyException attempted to create an inalid company
     */
    public static Selector[] getCacheEntries (Utility util)
    throws InvalidStateException, NullDataException, InvalidCompanyException
    {
        util.log (LogEventId.INFO_LEVEL_2, "OVALSNSP::getCacheEntries()");
        
        // Add entries to the HostFactory cache at start time to avoid long searches
        return new Selector[] {
            new Selector(new Company(OVALSNSPTag.Company_OvalsNSP, State.getAnAnyState(), null, null),
                LocationI.LOCATION_INTERFACE_NAME, (OVALSNSP.class).getName())
        };
    }
    
    /**
     * Return a list of host subclasses of this class.
     * Creation date: (3/19/01 10:36:20 AM)
     * @return String[] an array of host subclasses
     * @param util Utility a logging utility
     */
    public static String[] getHostList(Utility util)
    {
        util.log(LogEventId.INFO_LEVEL_2, "OVALSNSP::getHostList()");
        return HostList;
    }
    
    /**
     * Return the business interfaces that this class implements.
     * Creation date: (3/19/01 10:38:34 AM)
     * @return String[] an array of business interface names
     * @param util Utility a logging utility
     */
    public static String[] getInterfaceList(Utility util)
    {
        util.log(LogEventId.INFO_LEVEL_2, "OVALSNSP::getInterfaceList()");
        return new String[] { LocationI.LOCATION_INTERFACE_NAME };
    }
    
    /**
     * Return a list of supported companies.
     * Creation date: (3/19/01 10:02:48 AM)
     * @return Company a supported company
     * @param util Utility a logging utility
     * @exception InvalidStateException A state is invalid.
     * @exception NullDataException A required input parameter was null.
     * @exception InvalidCompanyException A company is invalid.
     */
    public static Company[] getSupportedCompanies(Utility util)
        throws InvalidStateException, NullDataException, InvalidCompanyException
    {
        util.log(LogEventId.INFO_LEVEL_2, "OVALSNSP::getSupportedCompanies()");
        return new Company[] {
            new Company(OVALSNSPTag.Company_OvalsNSP, State.getAnAnyState(), null, null)
        	};
    }
    
    /**
     * Getter method for the LIM Properties.
     * Creation date: (3/20/01 5:00:10 PM)
     * @return Properties
     */
    public Properties getLimProps()
    {
        return (Properties) getRetrieveLocationBase().limBase.getPROPERTIES();
    }

	/**
	 * getServiceAddressValidationHelper
	 */    
	private ServiceAddressValidationHelper getServiceAddressValidationHelper()
	{
		if(aServiceAddressValidationHelper == null)
		{
			aServiceAddressValidationHelper = 
				new ServiceAddressValidationHelper(getRetrieveLocationBase().limBase);
		}
		return aServiceAddressValidationHelper;
		
	}
}
