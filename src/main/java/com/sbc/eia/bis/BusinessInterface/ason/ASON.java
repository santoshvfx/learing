// $Id: ASON.java,v 1.34.22.1 2011/04/05 22:33:42 jr5306 Exp $

package com.sbc.eia.bis.BusinessInterface.ason;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.TN;
// OverrideParser-Removed import com.sbc.bccs.utilities.OverrideParserAccessDeniedException;
// OverrideParser-Removed import com.sbc.bccs.utilities.OverrideParserSystemFailureException;
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
import com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason.ASONTag;
//import com.sbc.eia.bis.BusinessInterface.lfacs.INQFASGRequestHelper;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.common.utilities.PropertyLoader;
import com.sbc.eia.bis.embus.service.facsrc.FACSRCService;
import com.sbc.eia.bis.embus.service.facsrc.SendRequestToFACSRC;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.impl.INQTERMImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGTypeImpl.REQTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ResponseMessageImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.StatusInfoTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ErrorsAggTypeImpl.ErrorCodeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ErrorsAggTypeImpl.ErrorDescriptionImpl;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
// OverrideParser-Removed import com.sbc.eia.bis.lim.transactions.RetrieveLocation.LIMOverrideParser;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.ProviderLocationPropertyHandler;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.sm.SmFacade;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.service.asonservice.ASONServiceHelper;
import com.sbc.gwsvcs.service.facsaccess.FACSAccessHelper;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t;
import com.sbc.eia.idl.rm_ls_types.Loop;
import com.sbc.eia.idl.sm.GetServiceAddressReturn;

import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp;

/**
 * This is the ASON Host BusinessInterface class for the LIM RetrieveLocation
 * transactions.
 * Creation date: (2/28/01 11:20:02 AM)
 * @author: David Brawley
 */
public class ASON extends Host implements LocationI
{
    private static final String HostList[] = null;
    protected static String asonSvcNm = "";
    protected static String facsAccessSvcNm = "";
    protected static String smHostNm = "";
    protected static String asonMaxAddrLimit = "";
    protected static String asonMaxZipLimit = "";
    protected ASONServiceHelper asonServiceHelper = null;
    protected FACSAccessHelper facsServiceHelper = null;
    private FACSRCService aFACSRCService = null;

    /**
     *  SM BIS related info.
     */
    private static String aProviderURL   = "";
    private static String aSmHome = null;

    private static final String SM_EXCEPTION_MSG = "SM Exception";
    private static final String LIM_ORIGINATION = "LIM";
    private ExceptionBuilderResult exBldReslt = null;
    private String ruleFile = null;
    private boolean doSagValidationRequest = false;
    private EventResultPair savedResult = null;
    private String aOriginator = null;
    
	/**
	 * LIM Alternate Routing info	OverrideParser-Removed
	
	private static String bisOverrideAuth = "";
	private String lfacsOverride = null;
	private String asonOverride = null;
   */
    /**
     * Construct a ASON Host object.
     * @param aCompany Company
     * @param aUtility Utility
     * @param aProperties Hashtable
     */
    public ASON(Company aCompany,Utility aUtility,Hashtable aProperties)
    {
    	super(aCompany, aUtility, aProperties);
    	ruleFile = (String) getProperties().get(LIMTag.CSV_FileName_ASON);
    	LIMBisContextManager contextManager = null;
    }
    
    /**
     * Send the AddressValidation request to the ASON service and get the response.
     * Creation date: (3/28/01 3:36:26 PM)
     * @return RetrieveLocResp
     * @param request ASONRetrieveLocReq
     * @param maxAddressLimit LongOpt
     * @param maxUnitLimit LongOpt
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected RetrieveLocResp doAddressValidation( AsonRetrieveLocReq request,
    	LongOpt maxAddressLimit, LongOpt maxUnitLimit )
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	boolean compoundStNmSfx = false;
    	int maxAddr = getMaxValue(maxAddressLimit);
    	int maxUnit = getMaxValue(maxUnitLimit);
    	RetrieveLocResp response = null;
    
    	LIMBisContextManager contextManager = new LIMBisContextManager(getLimBase().limBase.getCallerContext());
    	doSagValidationRequest = false;
        if (contextManager.getSagValidationRequest() != null)
        	if (contextManager.getSagValidationRequest().equalsIgnoreCase ("true"))
        		doSagValidationRequest = true;
        		
    	try
    	{ 
			/* Check whether the Platform supports Alternate Routing. OverrideParser-Removed
			if(getBisOverride().equalsIgnoreCase("TRUE"))
			{
				routingOverride(getBisContext());
			} */
    		if ((request.asonAddr.getStNameSfx()).length() == 2)
    			compoundStNmSfx = true;
    				
    		getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                            ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);

			savedResult = getASONServiceHelper(0).asonSagValidReq(null, null, ServiceHelper.USE_DEFAULT_TIMEOUT, 
    			request.getSagValidReq());	
            response = AsonResponseFactory.handleSagValidFromVicuna( getLimBase().limBase, savedResult,
				request, request.asonAddr, maxAddr, maxUnit, compoundStNmSfx);
            
            getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                            ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);
    
    		if (response instanceof AsonMultiValidation){
    			getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::doAddressValidation()|ASON::handleMultiValidation()|PRE");
    			response = handleMultiValidation(request, (AsonMultiValidation) response);
                getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::doAddressValidation()|ASON::handleMultiValidation()|POST");		}
    	}
    	catch (ServiceException se)
    	{
    		handleServiceException(se);
    	}
    
    	if (response instanceof AsonAddrRangeResp){
    		getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::doAddressValidation()|ASON::handleAddrRangeResp()|PRE");
    		response = handleAddrRangeResp(request, (AsonAddrRangeResp) response);
    	    getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::doAddressValidation()|ASON::handleAddrRangeResp()|POST");
    	}
    	else if (response instanceof AsonAddrSagValidResp)
    	{
    		getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::doAddressValidation()|ASON::handleAddrSagValidResp()|PRE");
    		response = handleAddrSagValidResp(request, (AsonAddrSagValidResp) response);
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::doAddressValidation()|ASON::handleAddrSagValidResp()|POST");	           
         }
    
    	return response;
    }
    
    /**
     * Find postal code for request by sag-id.
     * Creation date: (9/19/01 8:36:13 AM)
     * @param req AsonRetrieveLocReq
     * @exception ServiceException
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public void findReqPostalCode( AsonRetrieveLocReq req)
    throws ServiceException, InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	if ((req.getSagId().trim()).equals("")) {
    		Properties tagValues = new Properties();
    		tagValues.setProperty("CLASS", Address.class.getName());
    		tagValues.setProperty("ZIP", req.asonAddr.getPostalCode());
    
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Postal Code",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					tagValues);
    		
    	}
    
        getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                     ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_INQ_REQ);
    	
    		req.asonAddr.setPostalCode(req.getPostalCode(getASONServiceHelper(0).asonSagInqReq( null, null, 
    		ServiceHelper.USE_DEFAULT_TIMEOUT, req.getAsonSagInqReq(getASONMaxZipLimit()))));
            
        getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                     ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_INQ_REQ);
    
    
    	if ((req.asonAddr.getPostalCode()).equals("")){
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					"Postal Code Missing",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    	}
    	
    }
        /**
         * Creates SM Bean and calls getServiceLocation interface.
         * Creation date: (9/6/01 12:52:50 PM)
         * @return Location
         * @param ObjectKeyTN ObjectKey
         * @exception InvalidData An input parameter contained invalid data.
         * @exception AccessDenied Access to the specified domain object or information is not allowed.
         * @exception BusinessViolation The attempted action violates a business rule.
         * @exception DataNotFound The attempted action has no data.
         * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
         * @exception NotImplemented The method has not been implemented.
         * @exception ObjectNotFound The desired domain object could not be found.
         */
        public Location getAddressByTN (ObjectKey objectKeyTN)
        throws  InvalidData, 
        		AccessDenied, 
        		BusinessViolation, 
        		SystemFailure, 
        		NotImplemented, 
        		ObjectNotFound,
        		DataNotFound
        {

        	getUtility().log(LogEventId.DEBUG_LEVEL_2, "ASON::getAddressByTN()");
       
        	/**
        	 *  Determine SM BIS connection info.
        	 */
        	aProviderURL = getLimProps().getProperty ("SM_PROVIDER_URL");
        	aSmHome = getLimProps().getProperty ("SM_Home");
        	
        	// get Address from SM
			SmClient smClient = new SmClient();
//				new SmClient( aProviderURL, aSmHome, LIM_ORIGINATION, getLimProps().getProperty("INITIAL_CONTEXT_PROPERTIES_FILE"));
	
 			Location loc = new Location ();
        
            //
            // Temporary local context to specify "LIMBIS" as 
            // application name on input to SM
            // 
        	BisContext aBisContext = getLimBase().limBase.buildBisContext(getBisContext());
            			
         	try 
        	{
        		getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::getAddressByTN()|SmClient::getSmBean()|PRE");
        		SmFacade smBean = smClient.getSmBean(aBisContext, getUtility());
                getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::getAddressByTN()|SmClient::getSmBean()|POST");
                
        		GetServiceAddressReturn getServiceLocation = new GetServiceAddressReturn ();
        
                getUtility().log(LogEventId.REMOTE_CALL, getSmHostNm(aProviderURL), 
                        aSmHome, aSmHome, ASONTag.SM_GET_SERVICE_LOCATION); 
                
        		getServiceLocation = smBean.getServiceLocation (aBisContext, objectKeyTN);
                
                getUtility().log(LogEventId.REMOTE_RETURN, getSmHostNm(aProviderURL), 
                        aSmHome, aSmHome, ASONTag.SM_GET_SERVICE_LOCATION); 
        
        		
        		loc = getServiceLocation.aAddress;
        	}  
        	/*catch(RemoteException e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
       					ExceptionCode.ERR_SMCL_REMOTE_EXCEPTION,
        					e.getMessage(),
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
       					getUtility(),
        					"ASON",
        					Severity.UnRecoverable,
        					null);
        	}*/
        	// SM should not throw this NotImplemented exception. Redefine as SystemFailure because LIM needs to know about it.	
        	catch(NotImplemented e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
        					e.aExceptionData.aCode,
        					"",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					getUtility(),
        					"SM",
        					Severity.UnRecoverable,
        					null);
        	}
        	catch(SystemFailure e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
        					e.aExceptionData.aCode,
        					"",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					getUtility(),
        					"SM",
        					Severity.UnRecoverable,
        					null);
        	}
        	// SM should not throw this InvalidData exception. Redefine as SystemFailure because LIM needs to know about it.
        	catch(InvalidData e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
        					e.aExceptionData.aCode,
        					"",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					getUtility(),
        					"SM",
        					Severity.UnRecoverable,
        					null);
        	}
        	// Change ObjectNotFound to DataNotFound from LIM.
        	catch(ObjectNotFound e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
        					e.aExceptionData.aCode,
        					"",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					getUtility(),
        					"SM",
        					Severity.UnRecoverable,
        					null);
        	}
        	catch(DataNotFound e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
        					e.aExceptionData.aCode,
        					"",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					getUtility(),
        					"SM",
        					Severity.UnRecoverable,
        					null);
        	}
        	// SM should not throw this AccessDenied exception. Redefine as SystemFailure because LIM needs to know about it.
        	catch(AccessDenied e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
        					e.aExceptionData.aCode,
        					"",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					getUtility(),
        					"SM",
        					Severity.UnRecoverable,
        					null);
        	}
        	// SM should not throw this BusinessViolation exception. Redefine as SystemFailure because LIM needs to know about it.
        	catch(BusinessViolation e) {
        		exBldReslt =
        				ExceptionBuilder.parseException(
        					getBisContext(),
        					ruleFile,
        					"",
        					e.aExceptionData.aCode,
        					"",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					getUtility(),
        					"SM",
        					Severity.UnRecoverable,
        					null);
        	}
        	
        	return loc;
        }
    
    /**
     * Getter for asonMaxAddressLimit. 
     * Creation date: (9/7/01 11:01:38 AM)
     * @return int
     * @param maxAddr int
     */
    public int getASONMaxAddrLimit(int maxAddr) {
    
        try{
            if (maxAddr > 0 )
                return maxAddr;
    
            if ( asonMaxAddrLimit.length() > 0)
                return (new Integer(asonMaxAddrLimit).intValue());
                    
            asonMaxAddrLimit = (getLimProps().getProperty( "ASON_MAX_ADDRESS_LIMIT" )).trim();  
    
            if ( getLimBase().limBase.allNumeric(asonMaxAddrLimit) )
                return (new Integer(asonMaxAddrLimit).intValue());
        }
        catch(NullPointerException npe){}
    
        asonMaxAddrLimit = ASONTag.DEFAULT_MAX_ADDRESS_LIMIT;
    
        return (new Integer(asonMaxAddrLimit).intValue());
    }
    
    /**
     * Getter for asonMaxZipLimit. 
     * Creation date: (9/7/01 11:01:38 AM)
     * @return String
     */
    public String getASONMaxZipLimit() {
    	
    	try{
    		if (asonMaxZipLimit.length() > 0)
    			return (asonMaxZipLimit);
    
    		asonMaxZipLimit = (getLimProps().getProperty( "ASON_MAX_ZIPCODE_SEARCH_LIMIT" )).trim();	
    	
    		if ((asonMaxZipLimit.length() > 0)  &&
    			(getLimBase().limBase.allNumeric(asonMaxZipLimit))) 
    			return (asonMaxZipLimit);
    	}
    	catch(NullPointerException npe){}
    
    	asonMaxZipLimit = ASONTag.DEFAULT_MAX_ZIP_LIMIT; 
    	
    	return (asonMaxZipLimit);
    }
    
    /**
     * Getter for asonSvcNm. 
     * Creation date: (10/24/02 11:01:38 AM)
     * @return String
     */
    public String getAsonSvcNm() {
    	
    	try{
    		if (asonSvcNm.length() > 0)
    			return (asonSvcNm);
    
            StringBuffer temp = new StringBuffer();
            temp.append((getLimProps().getProperty( "GWS_ASONSERVICE" )).trim());
            temp.append(",APPLDATA="); 	
            temp.append((getLimProps().getProperty( "ASONSERVICE_APPLDATA" )).trim()); 	
            asonSvcNm = temp.toString();
    	}
    	catch(NullPointerException npe){}
    	
    	return (asonSvcNm);
    }
    
    /**
     * Getter for FACSAccessSvcNm. 
     * Creation date: (10/24/02 11:01:38 AM)
     * @return String
     */
    public String getFACSAccessSvcNm() {
    	
    	try{
    		if (facsAccessSvcNm.length() > 0)
    			return (facsAccessSvcNm);
    
            facsAccessSvcNm = (getLimProps().getProperty( "GWS_FACSACCESS" )).trim();
            
    	}
    	catch(NullPointerException npe){}
    	
    	return (facsAccessSvcNm);
    }
    
    /**
     * Getter for smHostNm. 
     * Creation date: (10/24/02 11:01:38 AM)
     * @return String
     */
    public String getSmHostNm(String newProviderURL) {
    	
    	if (newProviderURL == null || newProviderURL.trim().length() == 0)
    		return "";
    
    	return (newProviderURL);
    }
    
    /**
     * Getter method for the ASONServiceHelper.
     * Creation date: (3/21/01 3:13:20 PM)
     * @return ASONServiceHelper
     * @param maxAddressLimit int
     * @throws ServiceException
     * @see #setASONServiceHelper
     */
    protected ASONServiceHelper getASONServiceHelper(int maxAddressLimit) throws ServiceException
    {
    	if (asonServiceHelper == null)
    	{
    		setASONServiceHelper(new ASONServiceHelper(getLimProps(),getUtility()));
    	}
    
    	if (maxAddressLimit == 0)
    		asonServiceHelper.setMaximumMessagesToReceive(asonServiceHelper.RECEIVE_ALL_MESSAGES);
    	else
    		asonServiceHelper.setMaximumMessagesToReceive(maxAddressLimit);
    	
    	return asonServiceHelper;
    }
    /**
     * Getter method for the BisContext.
     * Creation date: (3/20/01 4:07:35 PM)
     * @return BisContext
     */
    public BisContext getBisContext()
    {
    	return getLimBase().limBase.getCallerContext();
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
    public static Selector[] getCacheEntries(Utility util)
    	throws InvalidStateException, NullDataException, InvalidCompanyException
    {
    	util.log(LogEventId.INFO_LEVEL_2, "ASON::getCacheEntries()");
    	
    	// Add entries to the HostFactory cache at start time to avoid long searches
    	return new Selector[] {
    		new Selector(new Company(Company.Company_Ameritech, new State(State.State_Illinois), null, null),
    			LocationI.LOCATION_INTERFACE_NAME, (ASON.class).getName()),
    		new Selector(new Company(Company.Company_Ameritech, new State(State.State_Michigan), null, null),
    			LocationI.LOCATION_INTERFACE_NAME, (ASON.class).getName()),
    		new Selector(new Company(Company.Company_Ameritech, new State(State.State_Indiana), null, null),
    			LocationI.LOCATION_INTERFACE_NAME, (ASON.class).getName()),
    		new Selector(new Company(Company.Company_Ameritech, new State(State.State_Wisconsin), null, null),
    			LocationI.LOCATION_INTERFACE_NAME, (ASON.class).getName()),
    		new Selector(new Company(Company.Company_Ameritech, new State(State.State_Ohio), null, null),
    			LocationI.LOCATION_INTERFACE_NAME, (ASON.class).getName())
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
    	util.log(LogEventId.INFO_LEVEL_2, "ASON::getHostList()");
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
    	util.log(LogEventId.INFO_LEVEL_2, "ASON::getInterfaceList()");
    	return new String[] { LocationI.LOCATION_INTERFACE_NAME };
    }
    /**
     * Getter for the RetrieveLocationBase object.
     * Creation date: (3/21/01 3:21:40 PM)
     * @return RetrieveLocationBase
     */
    protected RetrieveLocationBase getLimBase()
    {
    	return (RetrieveLocationBase) getPassThru();
    }
    /**
     * Getter method for the LIM Properties.
     * Creation date: (3/20/01 5:00:10 PM)
     * @return Properties
     */
    public Properties getLimProps()
    {
    	return (Properties) getLimBase().limBase.getPROPERTIES();
    }
    /**
     * Extract the int value from a max-value LongOpt object.
     * Creation date: (3/26/01 2:42:08 PM)
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
    	util.log(LogEventId.INFO_LEVEL_2, "ASON::getSupportedCompanies()");
    	return new Company[] {
    		new Company(Company.Company_Ameritech, State.getAnAnyState(), null, null)
    		};
    }
    /**
     * Getter method for the Utility object.
     * Creation date: (3/20/01 4:13:28 PM)
     * @return Utility
     */
    public Utility getUtility()
    {
    	return utility;
    }
	/**	OverrideParser-Removed
	 * Getter method for the BIS_OVERRIDE authorization from properties file
	 * Creation date:  oct 28, 2004 sp5136
	 
	public String getBisOverride()
	{
		try
		{
			if (!(bisOverrideAuth.length() > 0))
			{
				bisOverrideAuth = (getLimProps().getProperty( LIMTag.AUTHORIZATION_OVERRIDE )).trim();
			} 
		}
		catch(NullPointerException npe){}
    	
		return (bisOverrideAuth);
	} */
    
    /**
     * Sends the INQFASG request to LFACS via FACSRCAccess.
     * 
     * @param BisContext  aContext
     * @param String      aPrimaryNpaNxx
     * @param INQFASGImpl aINQFASGRequest
     * @return ResponseMessageImpl
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * 
     * @author Rene Duka
     */
    private ResponseMessageImpl sendINQFASGRequest(
        BisContext aContext,
        String aPrimaryNpaNxx,
        INQFASGImpl aINQFASGRequest)
        throws
            /*InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound,*/
            ServiceException
    {
        String aMethodName = "ASON: sendINQFASGRequest()";
        utility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
    
        String aDestination = "";                
    
        ResponseMessageImpl aFACSRCResponse = null;        
        aFACSRCResponse = SendRequestToFACSRC.sendRequest(aINQFASGRequest, 
                                                          utility, 
                                                          getProperties(), 
                                                          aContext, 
                                                          aFACSRCService, 
                                                          aDestination, 
                                                          aPrimaryNpaNxx);            
    
        utility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        return aFACSRCResponse;
    }
    
    /**
     * Parses the INQFASG response.
     * 
     * @param ResponseMessageImpl aFACSRCResponse
     * @return INQFASGResponseHelper
     * 
     * @author Rene Duka
     */
    private INQFASGResponseHelper parseINQFASGResponse(
        ResponseMessageImpl aFACSRCResponse)
        /*throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound  */
    {
        String aMethodName = "ASON: parseINQFASGResponse()";
        utility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        
        INQFASGResponseHelper aINQFASGResponseHelper = null;
        
        try
        {
            INQFASGTypeImpl aINQFASGResponse = new INQFASGTypeImpl();            
            aINQFASGResponse = (INQFASGTypeImpl) aFACSRCResponse.getResults().getINQFASG();
            
            aINQFASGResponseHelper = new INQFASGResponseHelper(utility, getProperties());
            aINQFASGResponseHelper.parseResponse(aINQFASGResponse);
        }
        catch (Exception e) 
        {
    		ExceptionData excData = new ExceptionData(
    			ExceptionCode.ERR_LIM_SERVICE_SYSTEM_FAILURE, 
    			e.getMessage(),
    			IDLUtil.toOpt("LIM"), 
    			(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));
    
    		utility.log(LogEventId.ERROR, excData.aDescription);
        }
        finally 
        {
            utility.log(LogEventId.DEBUG_LEVEL_1, " < " + aMethodName);
        }
        return aINQFASGResponseHelper;
    }
    
    /**
     * Set the workingTN list from LFACS. 
     * @param hitResp  a AsonHitResp object
     * @exception SystemFailure
     */
    protected void setWorkingTNList(AsonHitResp hitResp)
        throws SystemFailure
    {
        try
        { 
        	getUtility().log(LogEventId.REMOTE_CALL, ASONTag.FACSACCESS, getFACSAccessSvcNm(), ASONTag.FACSACCESS_SERVICE,
                    ASONTag.FACSACCESS_INQ_FASG_REQ);
        	
        	Fasg_Inq_Req_t q =hitResp.getFACSQueryReq();
	        	
            Result_t dat= (Result_t)getFacsServiceHelper().inqFasgReq(null, null, 
                getFacsServiceHelper().USE_DEFAULT_TIMEOUT, q).getTheObject();

            getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.FACSACCESS, getFACSAccessSvcNm(), ASONTag.FACSACCESS_SERVICE,
                ASONTag.FACSACCESS_INQ_FASG_REQ);
                
            if (dat.C1.ST.equals("S")) {
                
                hitResp.addWorkingTn(dat.LOOP);
            
                LOOP_Section_t[] loop = dat.LOOP;
            
                getUtility().log(LogEventId.INFO_LEVEL_2, "Selected InqFasgData:");
                
                StringBuffer loopInfo = new StringBuffer(" loop = ");
                for (int i=0; i < loop.length; i++)
                    loopInfo.append("|" + loop[i].CKID + "|" + loop[i].STAT + "|\n");
                    
                getUtility().log(LogEventId.INFO_LEVEL_2, loopInfo.toString());
            
            } else {
                
                getUtility().log(LogEventId.INFO_LEVEL_2, "ASON::setWorkingTNList()::Error Resulted in FACSAccess. Error Message: ");
                
                StringBuffer errorInfo = new StringBuffer("Errors  :  ");
                for (int i=0; i < dat.RESP.length; i++)
                    errorInfo.append(" | " + dat.RESP[i].ERRMSG + " | Error Type:  " + dat.RESP[i].ETYP + " | \n");
                    
                getUtility().log(LogEventId.INFO_LEVEL_2, errorInfo.toString());    
                
                
            }
        }
        catch (ServiceException se)
        {
            String code = "";
            String errText = se.getClass().getName();
            String timeOut = "";
        
            if (se instanceof ServiceTimeoutException)
            {
                // Check for time out
                code = LIMTag.CSV_LimTimedOutCode;      // Define as Timed out exception code in csv file.
                timeOut = "FACSAccess time out. ";
            }
            // Check for ASONService offline.
            else if (se.getMessage().indexOf("Service not available") > -1 && 
                se.getMessage().indexOf("downtime") > -1)
            {
                code = LIMTag.CSV_LimTimedOutCode;      // Define as Timed out exception code in csv file.
                timeOut = "FACSAccess Off-Line. ";
            }
            else{
                code = LIMTag.CSV_InternalError;
            }
        
            errText = timeOut + errText.substring(errText.lastIndexOf(".")+1) +
                        ": Code[" + se.getExceptionCode() + "], OriginalCode[" +
            se.getOriginalExceptionCode() + "], Message[" + se.getMessage() + "]";
            
            getUtility().log(LogEventId.INFO_LEVEL_2, errText);
        }
        
    }
    
    protected void setWorkingTNList_FACSRC(AsonHitResp hitResp)
    throws SystemFailure
    {	
    		INQFASGRequestHelper aRequestHelper = new INQFASGRequestHelper(utility, 
                    getProperties(),
                    hitResp); 
    		
    		REQTypeImpl aRequest = aRequestHelper.getRequest(); 
    		
    		INQFASGImpl aINQFASGRequest = new INQFASGImpl(); 
    		
            aINQFASGRequest.setREQ(aRequest); // Set the value of aINQFASGRequest using the values from aRequest
    		
 			if (aFACSRCService == null) 
 	        {
 	            try 
 	            {
					aFACSRCService = new FACSRCService(getProperties(), utility);
				} 
 	            catch (com.sbc.eia.bis.embus.service.access.ServiceException e) 
 	            {
 	            	String errorMsg = "";
					errorMsg = e.getMessage();					
					utility.log(LogEventId.INFO_LEVEL_2, errorMsg);
				}
 	        }
 
 			try 	// Try part where we send the request to FACSRC
 			{
 				ResponseMessageImpl aFACSRCResponse = sendINQFASGRequest(getBisContext(),
 						hitResp.svr.sagWireCenter,
                        aINQFASGRequest); 
 				
 	        	if (aFACSRCResponse != null) 	// This is the part where we check if there's a aFACSRCResponse. 
 	        	{ 
 	        		INQFASGResponseHelper aINQFASGResponseHelper = parseINQFASGResponse(aFACSRCResponse); 
 	        		
 	        		List facsErrs = aFACSRCResponse.getStatusInfo().getErrors().getErrorCodeAndErrorDescription();	
 
 	        		if (aINQFASGResponseHelper.getErrorResponse() == null && facsErrs.size() == 0) 	// check if there's any error from FACSRC or LFACS
 	        		{
 	        			
 	        			hitResp.addWorkingTn_facsrc(aINQFASGResponseHelper.getLoops()); 	// This is where we set the TN for FACSRC
 	        		
 	        			com.sbc.eia.bis.BusinessInterface.ason.Loop[] loop = aINQFASGResponseHelper.getLoops(); 
 	               
 	        			getUtility().log(LogEventId.INFO_LEVEL_2, "Selected InqFasgData:"); 	// Log the Selected InqFasgData.
 	                
 	        			StringBuffer loopInfo = new StringBuffer(" loop = ");
 	        			for (int i=0; i < loop.length; i++)
 	        			{
 	        				loopInfo.append("|" + loop[i].getCKID() + "|" + loop[i].getSTAT() + "|\n");
 	        			}
 	        			getUtility().log(LogEventId.INFO_LEVEL_2, loopInfo.toString());
 	                
 	        		}
 	        		else
 	        		{ 
 	        			getUtility().log(LogEventId.INFO_LEVEL_2, "ASON::setWorkingTNList()::Error Resulted in FACSRC. Error Message: ");
 	                   
 	                   StringBuffer errorInfo = new StringBuffer("Errors  :  ");
 
 	                   if (facsErrs.size() > 0)
 	                   {
 	                	   ErrorCodeImpl errCode = (ErrorCodeImpl) facsErrs.get(0);
 	                	   ErrorDescriptionImpl errDesc = (ErrorDescriptionImpl) facsErrs.get(1);
 
 	                	   errorInfo.append(" | " + errDesc.getValue() + " | Error Code:  " + errCode.getValue() + " | \n");
 	                   }
 	                   else if (aINQFASGResponseHelper.getErrorResponse() != null)
 	                   {
 	                	   errorInfo.append(" | " + aINQFASGResponseHelper.getErrorMessage() + " | Error Type:  " + aINQFASGResponseHelper.getErrorType() + " | \n"); 
 	                   } 
 	                   
 	                   getUtility().log(LogEventId.INFO_LEVEL_2, errorInfo.toString());
 	                   
 	        		}
 	        	}	
 			} 
			catch (ServiceException se)
	        {
	            String code = "";
	            String errText = se.getClass().getName();
	            String timeOut = "";
	        
	            if (se instanceof ServiceTimeoutException)
	            {
	                // Check for time out
	                code = LIMTag.CSV_LimTimedOutCode;      // Define as Timed out exception code in csv file.
	                timeOut = "FACSRC time out. ";
	            }
	            // Check for ASONService offline.
	            else if (se.getMessage().indexOf("Service not available") > -1 && 
	                se.getMessage().indexOf("downtime") > -1)
	            {
	                code = LIMTag.CSV_LimTimedOutCode;      // Define as Timed out exception code in csv file.
	                timeOut = "FACSRC Off-Line. ";
	            }
	            else
	            {
	                code = LIMTag.CSV_InternalError;
	            }
	        
	            errText = timeOut + errText.substring(errText.lastIndexOf(".")+1) +
	                        ": Code[" + se.getExceptionCode() + "], OriginalCode[" +
	            se.getOriginalExceptionCode() + "], Message[" + se.getMessage() + "]";
	            
	            getUtility().log(LogEventId.INFO_LEVEL_2, errText);
	        }
			finally{}
    }
    

    /**
     * The street number was not found for address. 
     * Handle it as an AddressList, instead of an ExactMatch response.
     * Creation date: (6/27/01 12:01:14 PM)
     * @param locReq    an AsonRetrieveLocReq object
     * @param listResp  an AsonAddrListResp object
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected void handleAddrListResp(AsonRetrieveLocReq locReq, AsonAddrListResp listResp)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	try
    	{
            getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                            ASONTag.ASON_SERVICE, ASONTag.ASON_LVNG_UNIT_INQ_REQ);
    		
    		listResp.parseAsonLuiVector(getASONServiceHelper(0).asonLvngUnitInqReq( null, null, 
    				ServiceHelper.USE_DEFAULT_TIMEOUT, listResp.getAsonLuiReq(locReq)));
    		
            getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                            ASONTag.ASON_SERVICE, ASONTag.ASON_LVNG_UNIT_INQ_REQ);
    	}
    	catch (ServiceException se)
    	{
    		handleServiceException(se);
    	}
    
    	if (listResp.getAddrList().isEmpty()){
    
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					null,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    	}
    }
    /**
     * We have to shield the client from sagaId.  Resend the request with each sagaId from ASON
     * For each one:
     *   Accumulate addresses in sagaId lists
     *   Extract addresses sent to client from each list.
     * Creation date: (3/27/01 12:01:14 PM)
     * @return RetrieveLocResp
     * @param locReq     AsonRetrieveLocReq
     * @param rangeResp  AsonAddrRangeResp
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected RetrieveLocResp handleAddrRangeResp(AsonRetrieveLocReq locReq, AsonAddrRangeResp rangeResp)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    
    	/**
    	 *  WTN response must be an exactMatch, 
         *  otherwise it will be an ObjectNotFound condition.
    	 */
    	if (locReq.isServiceReq()){
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_TnServiceError,
    					null,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    	}
        
        /**
         *  If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1)  
         *  request is for exactMatch only, 
         *  otherwise it will be an ObjectNotFound condition.
         */
        if (locReq.isExactMatchReq()){
            exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_AddressError,
                        null,
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "ASON",
                        Severity.UnRecoverable,
                        null);
        }
    
    	if (rangeResp.getSagIdList().isEmpty()){
    		Properties tagValues = new Properties();
    		tagValues.setProperty("CLASS", Address.class.getName());
    		tagValues.setProperty("ZIP", locReq.asonAddr.getPostalCode());
    		
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Postal Code",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					tagValues);
    	}
    
    	getLimBase().limBase.log(LogEventId.DEBUG_LEVEL_1,
    			"AsonAddrRangeResp: SagIds from ASON " + rangeResp.getSagIdList().toString());
    	
    	rangeResp.setReq(locReq);
    		
    	int vectorNbr = 0;
    	Iterator it = rangeResp.getSagIdList().iterator();
    	Vector sagVector = null;
    	while (it.hasNext())
    	{
    		locReq.setSagId((String) it.next());
    		try
    		{
                getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                            ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_INQ_REQ);
    			
    			sagVector = getASONServiceHelper(0).asonSagInqReq( null, null, 
    					ServiceHelper.USE_DEFAULT_TIMEOUT, rangeResp.getAsonSagInqReq(locReq, 
    					getASONMaxAddrLimit(ASONTag.NO_SIZE_LIMIT)));
    			
                getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                            ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_INQ_REQ);
    			getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::parseAsonSagInqVector()|PRE");
    
    			switch (vectorNbr){
    				case 0:
    						rangeResp.parseAsonSagInqVector(sagVector, rangeResp.sagAddrList0);
    						break;
    				case 1:
    						rangeResp.parseAsonSagInqVector(sagVector, rangeResp.sagAddrList1);
    						break;
    				case 2:
    						rangeResp.parseAsonSagInqVector(sagVector, rangeResp.sagAddrList2);
    						break;
    				case 3:
    						rangeResp.parseAsonSagInqVector(sagVector, rangeResp.sagAddrList3);
    						break;
    				default:
    						break;
    			}
    
                getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::parseAsonSagInqVector()|POST");
    			sagVector.clear();
    			vectorNbr++;
    		}
    		catch (ServiceException se)
    		{
    			handleServiceException(se);
    		}
    	} // end while
    
    	try{
            // extract address from multiple Sag address lists and sort 
    		getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::getSagRangeList()|PRE");
    		rangeResp.getSagRangeList(getASONMaxAddrLimit(rangeResp.getMaxAddresses()));
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::getSagRangeList()|POST");
     
            // get alternate address response   
    		getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::getAltAddrResp()|PRE");
    		rangeResp.getAltAddrResp();
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::getAltAddrResp()|POST");
       	}
        
    	catch(AddressHandlerException ahe){
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
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    	}
    
    	if (rangeResp.getRangeList().isEmpty()){
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					null,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    	}
    
    	// set request address for sorting in AddressRangeResp class
        getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::setOrigReq()|PRE");
    	rangeResp.setOrigReq((AddressHandler) locReq.getAddr());
        getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrRangeResp()|AsonAddrRangeResp::setOrigReq()|POST");
            
    	return rangeResp;
    }
    /**
     * The Street Name has been found, need to detemine whether to 
     * return AddressList response, instead of an ExactMatch response.
     * Creation date: (6/27/01 12:01:14 PM)
     * @return RetrieveLocResp
     * @param locReq   AsonRetrieveLocReq
     * @param SvrResp  AsonAddrSagValResp
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected RetrieveLocResp handleAddrSagValidResp(AsonRetrieveLocReq locReq, AsonAddrSagValidResp SvrResp)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	RetrieveLocResp response = null;
    	EventResultPair result = null;
        
    	try 
    	{
            getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                        ASONTag.ASON_SERVICE, ASONTag.ASON_LVNG_UNIT_VAL_REQ);
    
    		result = getASONServiceHelper(0).asonLvngUnitValReq(null, null, ServiceHelper.USE_DEFAULT_TIMEOUT, SvrResp.getAsonLuvReq());
    		
    		response = AsonResponseFactory.handleLuvFromVicuna( getLimBase().limBase, result,
    			locReq, SvrResp.getMaxAddresses(), SvrResp.getMaxUnits(), savedResult, doSagValidationRequest );
    		
            getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                       ASONTag.ASON_SERVICE, ASONTag.ASON_LVNG_UNIT_VAL_REQ);
    
    		if (response instanceof AsonHitResp)
    		{
    			//
    			// The response may have changed based on the SagValidationRequest rules. So, response may be
    			// AsonHitResp even if the result from ASONLivingUnitValidationReq was not successful.
    			// So, check also for the value of ((ASONLvngUnitValResp)result.getTheObject()).replyCode.
    			// 
    			if (((ASONLvngUnitValResp)result.getTheObject()).replyCode == 0)
    			{
                	BisContextManager bcm = new BisContextManager(getBisContext());
     				((AsonHitResp) response).setDescAddr(locReq.getAddr().getAddAddrInfo());
    				((AsonHitResp) response).setSvr(SvrResp.svr);
    				((AsonHitResp) response).setStNmInd(locReq.getAddr().getStNmInd());
                	if ((!bcm.getApplication().equals("RM_BIS")) &&
                    	(!bcm.getApplication().equals("NAM_BIS")) &&
                    	(!bcm.getApplication().equals("SM_BIS")) )	 
                	{
      			    	if ((locReq.locationPropertiesRequested.isRetrieveALL()) ||
                        	(locReq.locationPropertiesRequested.isRetrieveTelephoneNumber()) ||
                        	(locReq.locationPropertiesRequested.isRetrieveWorkingServiceOnLocation()))
                    	{
      			            boolean booleanAccess = false; // needed to determine if the call will be to FACSACCESS or FACSRC
      			            String aPropertyFile = "/lim.properties"; // this is where we look for the FACSRC values
      			    		java.util.Hashtable newPROPERTIES; // creates a hashtable named newPROPERTIES
      			            try {
      			    			newPROPERTIES = PropertyLoader.getInstance().loadProperties(aPropertyFile); //get the instance of lim.properties
      			    			String methodAccess = (String) newPROPERTIES.get("FACS_RC_OPTION"); // get the value "FACS_RC_OPTION"
      			    			Boolean BooleanObj		= Boolean.valueOf(methodAccess); // set the value of BooleanObj to the value of methodAccess
      			    			booleanAccess	= BooleanObj.booleanValue(); // set the value of bolleanAccess to the BooleanObj
      			    		} catch (IOException e) { // catch IOException
      			    			throw new SystemFailure(
      			    					null,
      			    					new ExceptionData(
      			    						ExceptionCode.ERR_LIM_PROPERTIES_FILE_NOT_FOUND,
      			    						" " + e.toString(),
      			    						IDLUtil.toOpt("LIM"),
      			    						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
      			    		}
      			    		if (booleanAccess){ //if booleanAccess is true then setWorkingTNList via FACSRC
      			    			setWorkingTNList_FACSRC((AsonHitResp) response);
      			    		}
      			    		else //if booleanAccess is false then setWorkingTNList via FACSACCESS
      			    		{
      			    			setWorkingTNList((AsonHitResp) response);
      			      		}
  
                    	}
    				}
    			}
    		}
    		else if (response instanceof AsonAddrListResp)
    		{
    			/**
    			 *  WTN needs to be an exact hit, 
                 *  otherwise it will be an ObjectNotFound condition.
    			 */
    			if (locReq.isServiceReq())
    			{
    				exBldReslt =
    						ExceptionBuilder.parseException(
    							getBisContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_TnServiceError,
    							null,
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							utility,
    							"ASON",
    							Severity.UnRecoverable,
    							null);
    			}
                
               /**
                *  If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1)  
                *  request is for exactMatch only, 
                *  otherwise it will be an ObjectNotFound condition.
                */
               if (locReq.isExactMatchReq()){
                   exBldReslt =
                           ExceptionBuilder.parseException(
                               getBisContext(),
                               ruleFile,
                               "",
                               LIMTag.CSV_AddressError,
                               null,
                               true,
                               ExceptionBuilderRule.NO_DEFAULT,
                               null,
                               null,
                               utility,
                               "ASON",
                               Severity.UnRecoverable,
                               null);
                }
    
    			/**
    			 *  Descriptive needs to be an exact hit, 
                 *  otherwise it will be an ObjectNotFound condition.
    			 */
    			if (locReq.asonAddr.getAddAddrInfo().length() > 0)
    			{
    				exBldReslt =
    						ExceptionBuilder.parseException(
    							getBisContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_AddressError,
    							"Descriptive not found",
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							utility,
    							"ASON",
    							Severity.UnRecoverable,
    							null);
    			}
    			((AsonAddrListResp) response).setSvr(SvrResp.svr);				
    			getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrSagValidResp()|ASON::handleAddrListResp()|PRE");
    			handleAddrListResp(SvrResp.getReq(), ((AsonAddrListResp) response));
                getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::handleAddrSagValidResp()|ASON::handleAddrListResp()|POST");		
    		}
    		else {
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					null,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    		}
    
    	}
    	catch (ServiceException se)
    	{
    		handleServiceException(se);
    	}
    		
    	return response;
    }
    
    /**
     * Getter method for the LFACSServiceHelper.
     * Creation date: (10/14/03 9:39:20 AM)
     * Created By: RamaKishore (rk7964)
     * @return FACSAccessHelper
     * @exception ServiceException
     * @see #setFACSServiceHelper(FACSAccessHelper)
     */
    	protected FACSAccessHelper getFacsServiceHelper() throws ServiceException
    	{
    		if (facsServiceHelper == null)
    		{
    			setFacsServiceHelper(new FACSAccessHelper(getLimProps(),getUtility()));
    		}
    	
    		return facsServiceHelper;
    	}
    	
    /**
     * Setter method for the FACSServiceHelper.
     * Creation date: (10/14/03 9:45:20 AM)
     * Created By: RamaKishore (rk7964)
     * @param newFACSServiceHelper  a FACSAccessHelper object specifying the facs service helper value
     * @see #getFACSServiceHelper
     */
    protected void setFacsServiceHelper(FACSAccessHelper newFacsServiceHelper)
    {
    	facsServiceHelper = newFacsServiceHelper;
    }
    
    /**
     * Determine reason for mulitValidation and instantiate new request for sag validation 
     * return response from handleSagValidFromVicuna call.
     * Creation date: (6/27/01 12:01:14 PM)
     * @return RetrieveLocResp
     * @param locReq   AsonRetrieveLocReq
     * @param multiValidation  AsonMultiValidation
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected RetrieveLocResp handleMultiValidation(AsonRetrieveLocReq locReq, AsonMultiValidation multiValidation)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	RetrieveLocResp response = null;
    	AsonMultiValidation workingMV = multiValidation;
    
    	try{
    		
    		if (workingMV.isDescriptiveAddr()){
                getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                                ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);
                savedResult = getASONServiceHelper(0).asonSagValidReq(null, null, ServiceHelper.USE_DEFAULT_TIMEOUT, 
    				workingMV.getSagValidReq());
    			response = AsonResponseFactory.handleSagValidFromVicuna( getLimBase().limBase, savedResult,
    				locReq, workingMV.asonHelper.address,
    				workingMV.getMaxAddresses(), workingMV.getMaxUnits(),
    				(workingMV.asonHelper.address.getStNameSfx().length() == 2) ? true : false);
    			if (response instanceof AsonMultiValidation)
    				workingMV = (AsonMultiValidation) response; 
                getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                                ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);
    		}
    
    		if (workingMV.isCompoundStNameSfx()){
                getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                                ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);
                savedResult = getASONServiceHelper(0).asonSagValidReq(null, null, ServiceHelper.USE_DEFAULT_TIMEOUT, 
    				workingMV.getSagValidReq());
    			response = AsonResponseFactory.handleSagValidFromVicuna( getLimBase().limBase, savedResult,
    				locReq, workingMV.asonHelper.address, 
    				workingMV.getMaxAddresses(), workingMV.getMaxUnits(),	false);
    			if (response instanceof AsonMultiValidation){
    				workingMV = (AsonMultiValidation) response;
    				workingMV.setCompoundStNameSfx(true);
    			}
                getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                                ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);
    		}
    		
    		if (workingMV.isCommunityRequired()){
                getUtility().log(LogEventId.REMOTE_CALL, ASONTag.ASON, getAsonSvcNm(), 
                                ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);
                savedResult = getASONServiceHelper(0).asonSagValidReq(null, null, ServiceHelper.USE_DEFAULT_TIMEOUT, 
    				workingMV.getSagValidReq());
    			response = AsonResponseFactory.handleSagValidFromVicuna( getLimBase().limBase, savedResult,
    				locReq, workingMV.asonHelper.address,
    				workingMV.getMaxAddresses(), workingMV.getMaxUnits(), false);
                getUtility().log(LogEventId.REMOTE_RETURN, ASONTag.ASON, getAsonSvcNm(), 
                                ASONTag.ASON_SERVICE, ASONTag.ASON_SAG_VALID_REQ);
    
    		}
    		
    		if ((response instanceof AsonAddrRangeResp) &&
    			(multiValidation.isDescriptiveAddr())){
    			exBldReslt =
    					ExceptionBuilder.parseException(
    						getBisContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_AddressError,
    						"Descriptive not found",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"ASON",
    						Severity.UnRecoverable,
    						null);
    		}
    
    		if (response instanceof AsonMultiValidation){
    			response = new AsonAddrRangeResp(getLimBase().limBase);
    			((AsonAddrRangeResp) response).setSagIdList(multiValidation.getSagIdList());
    			response.setMaxAddresses(multiValidation.getMaxAddresses());
    			response.setMaxUnits(multiValidation.getMaxUnits());
    		}
    	}
    
    	catch (ServiceException se)
    	{
    		handleServiceException(se);
    	}
    
    	return response;
    }
    /**
     * Handle a ServiceException from the ASONService Vicuna wrapper.
     * Creation date: (3/28/01 11:12:53 AM)
     * @param svcExc ServiceException
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected void handleServiceException(ServiceException svcExc)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	String code = "";
    	String errText = svcExc.getClass().getName();
    	String timeOut = "";
    	
    	if (svcExc instanceof ServiceTimeoutException)
    	{
    		// Check for time out
    		code = LIMTag.CSV_LimTimedOutCode;		// Define as Timed out exception code in csv file.
    		timeOut = "ASON time out. ";
    	}
    		// Check for ASONService offline.
    	else if (svcExc.getMessage().indexOf("Service not available") > -1 && 
    		svcExc.getMessage().indexOf("downtime") > -1)
    	{
    		code = LIMTag.CSV_LimTimedOutCode;		// Define as Timed out exception code in csv file.
    		timeOut = "ASONService Off-Line. ";
    	}
    	else{
    		code = LIMTag.CSV_InternalError;
    	}
    	
    	errText = timeOut + errText.substring(errText.lastIndexOf(".")+1) +
    		": Code[" + svcExc.getExceptionCode() + "], OriginalCode[" +
    		svcExc.getOriginalExceptionCode() + "], Message[" + svcExc.getMessage() + "]";
    		
    	getUtility().log(LogEventId.DEBUG_LEVEL_2, "svcExc.getOriginalExceptionCode="+ String.valueOf(svcExc.getOriginalExceptionCode()));
    	
    	Properties tagValues = new Properties();
    	tagValues.setProperty("CODE", svcExc.getExceptionCode());
    	tagValues.setProperty("MSG", svcExc.getMessage());
    
    	exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					code,
    					errText,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					tagValues);
    }
    /**
     * Starts the application.
     * @param args an array of command-line arguments
     */
    public static void main(java.lang.String[] args) {
    	// Insert code to start the application here.
    }
    
    /**
     * Implementation of method retrieveLocationForAddress() from the LocationI interface by
     * ASON host.
     * Creation date: (2/22/01 12:45:09 PM)
     * @return RetrieveLocationForAddressReturn
     * @param aAddress AddressHandler
     * @param aMaximumBasicAddressReturnLimit LongOpt
     * @param aMaximumUnitReturnLimit LongOpt
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public RetrieveLocationForAddressReturn retrieveLocationForAddress(
        Address aAddress, 
        ProviderLocationProperties[] aPropertiesToGet,
    	LongOpt aMaximumBasicAddressReturnLimit, 
        LongOpt aMaximumUnitReturnLimit,
        StringOpt aExchangeCode)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	RetrieveLocationForAddressReturn result = null;
    
    	try
    	{
    		AddressHandlerASON asonAddr = new AddressHandlerASON(aAddress);
    		AsonRetrieveLocReq request = new AsonRetrieveLocReq(getLimBase().limBase, asonAddr, aPropertiesToGet);
            request.logAddressReq();
    		request.setServiceReq(false);
            // If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1) request is for exactMatch only response
            request.setExactMatchReq(getMaxValue(aMaximumBasicAddressReturnLimit) == LIMTag.EXACT_MATCH_REQ ? true : false);
            
    
    		// if postal code in request is blank, perform SagInq (S8B) to find it.
    		if ((request.asonAddr.getPostalCode()).equals("")){
    			request.setSagId(getLimBase().getSaga());
                getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForAddress()|ASON::findReqPostalCode()|PRE");
    			findReqPostalCode(request);
                getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForAddress()|ASON::findReqPostalCode()|POST");
    		}
    
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForAddress()|ASON::doAddressValidation()|PRE");		
    		result = (doAddressValidation( request, aMaximumBasicAddressReturnLimit, aMaximumUnitReturnLimit )).
    			toAddressReturn();
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForAddress()|ASON::doAddressValidation()|POST");
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
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    	}
    	catch (ServiceException se)
    	{
    		handleServiceException(se);
    	}
    
    	return result;
    }
    /**
     * Implementation of method retrieveLocationForService() from the LocationI interface by
     * ASON host.
     * Creation date: (2/22/01 12:45:09 PM)
     * @return RetrieveLocationForServiceReturn
     * @param aService TN
     * @param aMaximumBasicAddressReturnLimit LongOpt
     * @param aMaximumUnitReturnLimit LongOpt
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public RetrieveLocationForServiceReturn retrieveLocationForService(
        TN aService,
        ProviderLocationProperties[] aPropertiesToGet,
    	LongOpt aMaximumBasicAddressReturnLimit, 
        LongOpt aMaximumUnitReturnLimit)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
        RetrieveLocationForServiceReturn result = null;
        ObjectKey service = new ObjectKey();
        
        try
        {
            service = new ObjectKey (aService.toString (), "");
    
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForService()|ASON::getAddressByTN()|PRE");
            AddressHandler aAddress = 
                new AddressHandler((((Location) getAddressByTN(service)).aProviderLocationProperties[0].aServiceAddress.theValue()));
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForService()|ASON::getAddressByTN()|POST");  
            
            getUtility().log(LogEventId.DEBUG_LEVEL_2, LIMIDLUtil.display(aAddress.getAddress()));
            
            /** Fix City problem if applicable **/
            aAddress.setCity( aAddress.getCity() );
        
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForService()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|PRE");                         
            aAddress.setState(RetrieveStateCodeByZip.retrieveStateCodeByZip(getLimProps(),
            aAddress.getPostalCode(),getLimBase().limBase));
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForService()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|POST");
                
            AddressHandlerASON asonAddr = new AddressHandlerASON(aAddress.getAddress());
            AsonRetrieveLocReq request = new AsonRetrieveLocReq(getLimBase().limBase, asonAddr, aPropertiesToGet);
            request.logAddressReq();
            request.setServiceReq(true);
            
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForService()|ASON::doAddressValidation()|PRE");
            result = (doAddressValidation( request, aMaximumBasicAddressReturnLimit, aMaximumUnitReturnLimit )).
                toServiceReturn();
            getUtility().log(LogEventId.AUDIT_TRAIL,"ASON::retrieveLocationForService()|ASON::doAddressValidation()|POST");
        }
        catch( AddressHandlerException ahe ){} 
        
        catch( SQLException sqle )
        {
                exBldReslt = 
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_OracleError,
                        "Zip:"+sqle.getMessage(),
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "ASON",
                        Severity.UnRecoverable,
                        null);
        }
    
        catch( ObjectNotFound onf )
        {
            exBldReslt = 
                ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_TnServiceError,
                        null,
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "ASON",
                        Severity.UnRecoverable,
                        null);
        }
        
        return result;
    }
    /**
     * Setter method for the ASONServiceHelper.
     * Creation date: (3/21/01 3:13:20 PM)
     * @param newASONServiceHelper ASONServiceHelper
     * @see #getASONServiceHelper
     */
    protected void setASONServiceHelper(ASONServiceHelper newASONServiceHelper)
    {
    	asonServiceHelper = newASONServiceHelper;
    }
	/**	OverrideParser-Removed
	 * Checks for routing overrides.  If exists sets the value for ASON or LFACS respectively
	 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 * Created by Siva Papineni(sp5136) 10/07/04
	 

	private void  routingOverride(BisContext aContext)
			throws  InvalidData,
					AccessDenied,
					BusinessViolation,
					SystemFailure,
					NotImplemented,
					ObjectNotFound,
					DataNotFound
	{
		String overrideValue = null;
		Properties tagValues = new Properties();
		try
		{
			LIMOverrideParser limOverride = new LIMOverrideParser(aContext,getLimBase().limBase);
			overrideValue = limOverride.handleOverride();
			if (limOverride.isAITASON)
			{
				asonOverride = overrideValue;
				getUtility().log(LogEventId.INFO_LEVEL_1,"Overriding ASONSERVICE_APPLDATA, default value: "
								+ getLimProps().getProperty(LIMTag.ASON_APPLDATA) + " override value: "
								+ asonOverride);
			} 
			else if (limOverride.isAITLFACS)
			{
				lfacsOverride = overrideValue;
				getUtility().log(LogEventId.INFO_LEVEL_1,"Overriding LFACS SERVER_APPLDATA, default value: "
								+ getLimProps().getProperty("LFACSSERVICE_APPLDATA") + " override value: "
								+ lfacsOverride);
			} 	
		}
		catch(OverrideParserSystemFailureException e)
		{
			getUtility().log(LogEventId.EXCEPTION, "System Failure in loading routing override table: " +
												e.getMessage());
			tagValues.setProperty("MSG", e.getMessage());
			
			ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_InternalError,
					"Routing Override System Failure",
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null, 
					utility,
					"ASON",
					Severity.UnRecoverable,
					tagValues); 					
		}
		catch(OverrideParserAccessDeniedException e)
		{
			getUtility().log(LogEventId.EXCEPTION, "Access Denied in loading routing override table: " + 
												e.getMessage());
			tagValues.setProperty("MSG", e.getMessage());	
			
			ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_InternalError,
					"Routing Override Access Denied",
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null, 
					utility,
					"ASON",
					Severity.UnRecoverable,
					tagValues); 					
		}
	}
	*/
}
