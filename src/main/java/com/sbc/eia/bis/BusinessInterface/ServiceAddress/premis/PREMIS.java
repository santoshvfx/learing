// $Id: PREMIS.java,v 1.7 2007/10/15 20:51:06 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Logger;
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
import com.sbc.eia.bis.BusinessInterface.location.ServiceAddressLocationI;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.database.queries.RetrieveClecNameByExchAndCoId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.transactions.common.RetrieveServiceLocationBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerPremis;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.access.vicuna.multiplexer.VicunaWrapperMultiplexer;
import com.sbc.gwsvcs.access.vicuna.multiplexer.VicunaWrapperMultiplexerException;
import com.sbc.gwsvcs.service.premisserver.PREMISServerHelper;
import com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t;


/**
 * This is the PREMIS Host BusinessInterface class for the LIM RetrieveLocation
 * transactions.
 * Creation date: (2/28/01 11:20:02 AM)
 * @author: David Prager
 */
public class PREMIS extends Host implements ServiceAddressLocationI
{
	private static final String HostList[] = null;
	protected PREMISServerHelper premisServerHelper = null;
	protected static String premisSvcNm = "";
    protected static int premisMaxHelpers = 0;
    protected static int premisTimeout = 0;
    protected static double premisSliceTimeout = 0;
	ExceptionBuilderResult exBldReslt = null;
	String ruleFile =null;

    /**
     * Construct a PREMIS Host object.
     * @param aCompany Company
     * @param aUtility LIMBase
     * @param aProperties Hashtable
     */
    public PREMIS(Company aCompany,Utility aUtility,Hashtable aProperties)
    {
    	super(aCompany, aUtility, aProperties);
    	ruleFile = (String) getProperties().get(LIMTag.CSV_FileName_PREMIS);
    }
    /**
     * Send the ServiceAddressValidation request to PREMISServer and get the response.
     * Creation date: (3/28/01 3:36:26 PM)
     * @return RetrieveLocResp
     * @param request PremisRetrieveLocReq
     * @param maxAddressLimit LongOpt
     * @param maxUnitLimit LongOpt
     * @param sagaList List
     * @exception ServiceException
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected RetrieveLocResp doServiceAddressValidation( PremisRetrieveLocReq request,
    	int maxAddressLimit, int maxUnitLimit, List sagaList )
    	throws ServiceException, InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	int maxAddr = maxAddressLimit;
    	int maxUnit = maxUnitLimit;
    	RetrieveLocResp response = null;

    	if (sagaList == null || sagaList.size() == 0){
    	   getLimBase().limBase.log(LogEventId.REMOTE_CALL, PREMISTag.NAME, getPremisSvcNm(), PREMISTag.SERVER, 
    		  PREMISTag.PREMIS_VALDT_ADDR_REQ); 
    
    	   response = PremisResponseFactory.fromVicuna( getLimBase().limBase,
    			 getPremisServerHelper().premisValdtAddrReq(null, null, ServiceHelper.USE_DEFAULT_TIMEOUT, request.toVicuna()),
    			 request, maxAddr, maxUnit);
    	
    	   getLimBase().limBase.log(LogEventId.REMOTE_RETURN, PREMISTag.NAME, getPremisSvcNm(), PREMISTag.SERVER, 
    		  PREMISTag.PREMIS_VALDT_ADDR_REQ); 
        }
        else{
           response = (RetrieveLocResp) new PremisSagaResp(getLimBase().limBase, sagaList);
           response.setMaxAddresses(maxAddr);
           response.setMaxUnits(maxUnit);
        }
    	
    	if (response instanceof PremisTNMenu){
            getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::doServiceAddressValidation()|PREMIS::handleTNMenu()|PRE");
    		response = handleTNMenu(request,(PremisTNMenu) response);
            getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::doServiceAddressValidation()|PREMIS::handleTNMenu()|POST");
        }
    	else if (response instanceof PremisSagaResp){
            getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::doServiceAddressValidation()|PREMIS::handleSagaMenu()|PRE");
    		response = handleSagaMenu(request,(PremisSagaResp) response);
            getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::doServiceAddressValidation()|PREMIS::handleSagaMenu()|POST");
        }
    
        if (response instanceof PremisHit){
            getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::doServiceAddressValidation()|PREMIS::handlePremisHit()|PRE");
    		handlePremisHit(request, (PremisHit) response);
            getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::doServiceAddressValidation()|PREMIS::handlePremisHit()|POST");
        }
        else if (response instanceof PremisAddrRangeResp)
    		((PremisAddrRangeResp)response).setOrigReq((AddressHandler) request.getAddr());
    
        /**
         *  If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1)  
         *  request is for exactMatch only, 
         *  otherwise it will be an ObjectNotFound condition.
         */
         if ((!(response instanceof PremisHit)) &&
             (request.isExactMatchReq())){
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
                     "PREMIS",
                     Severity.UnRecoverable,
                     null);
         }
    	
    	return response;
    }
    /**
     * Getter for premisMaxHelpers. 
     * Creation date: (10/29/02 11:01:38 AM)
     * @return int
     */
    public int getPremisMaxHelpers() {
        
        try{
            if (premisMaxHelpers != 0)
                return (premisMaxHelpers);
    
            String temp = ((getLimProps().getProperty( "PREMISSERVER_HELPERS" )).trim());     
            premisMaxHelpers = Integer.valueOf(temp).intValue();
            if (premisMaxHelpers == 0)
                premisMaxHelpers = PREMISTag.PREMIS_DEFAULT_MAX_HELPERS;
         }
        catch(NumberFormatException nfe){
            premisMaxHelpers = PREMISTag.PREMIS_DEFAULT_MAX_HELPERS;
        }
        catch(NullPointerException npe){
            premisMaxHelpers = PREMISTag.PREMIS_DEFAULT_MAX_HELPERS;
        }
        
        return (premisMaxHelpers);
    }
    /**
     * Getter for premisTimeout. 
     * Creation date: (10/29/02 11:01:38 AM)
     * @return int
     */
    public int getPremisTimeout() {
        
        try{
            if (premisTimeout != 0)
                return (premisTimeout);
    
            String temp = ((getLimProps().getProperty( "PREMISSERVER_TIMEOUT" )).trim());     
            premisTimeout = Integer.valueOf(temp).intValue();
            if (premisTimeout == 0)
                premisTimeout = PREMISTag.PREMIS_DEFAULT_TIMEOUT;
         }
        catch(NumberFormatException nfe){
            premisTimeout = PREMISTag.PREMIS_DEFAULT_TIMEOUT;
        }
        catch(NullPointerException npe){
            premisTimeout = PREMISTag.PREMIS_DEFAULT_TIMEOUT;
        }
        
        return (premisTimeout);
    }
    
    /**
     * Getter for premisSliceTimeout. 
     * Creation date: (10/29/02 11:01:38 AM)
     * @return int
     */
    public double getPremisSliceTimeout() {
        
        try{
            if (premisSliceTimeout != 0)
                return (premisSliceTimeout);
    
            String temp = ((getLimProps().getProperty( "PREMISSERVER_SLICE_TIMEOUT" )).trim());     
            premisSliceTimeout = Double.valueOf(temp).doubleValue();
            if (premisSliceTimeout == 0)
                premisSliceTimeout = PREMISTag.PREMIS_DEFAULT_SLICE_TIMEOUT;
         }
        catch(NumberFormatException nfe){
            premisSliceTimeout = PREMISTag.PREMIS_DEFAULT_SLICE_TIMEOUT;
        }
        catch(NullPointerException npe){
            premisSliceTimeout = PREMISTag.PREMIS_DEFAULT_SLICE_TIMEOUT;
        }
        
        return (premisSliceTimeout);
    }
    
    /**
     * Getter for premisSvcNm. 
     * Creation date: (10/29/02 11:01:38 AM)
     * @return String
     */
    public String getPremisSvcNm() {
    	
    	try{
    		if (premisSvcNm.length() > 0)
    			return (premisSvcNm);
    
            StringBuffer temp = new StringBuffer();
            temp.append((getLimProps().getProperty( "GWS_PREMISSERVER" )).trim());
            temp.append(",APPLDATA="); 	
            temp.append((getLimProps().getProperty( "PREMISSERVER_APPLDATA" )).trim()); 	
            premisSvcNm = temp.toString();
    	}
    	catch(NullPointerException npe){}
    	
    	return (premisSvcNm);
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
     * @param util Utility
     * @exception InvalidStateException  An attempt was made to create an invalid state.
     * @exception NullDataException A required input parameter was null.
     * @exception InvalidCompanyException An attempt was made to create an invalid company.
     */
    public static Selector[] getCacheEntries(Utility util)
    	throws InvalidStateException, NullDataException, InvalidCompanyException
    {
    	util.log(LogEventId.INFO_LEVEL_2, "PREMIS::getCacheEntries()");
    	
    	// Add entries to the HostFactory cache at start time to avoid long searches
    	return new Selector[] {
    		new Selector(new Company(Company.Company_PacificBell, new State(State.State_California), null, null),
    		    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (PREMIS.class).getName()),
    		new Selector(new Company(Company.Company_PacificBell, new State(State.State_Nevada), null, null),
    		    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (PREMIS.class).getName()),
    		new Selector(new Company(Company.Company_SouthWesternBell, new State(State.State_Missouri), null, null),
    		    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (PREMIS.class).getName()),
    		new Selector(new Company(Company.Company_SouthWesternBell, new State(State.State_Arkansas), null, null),
    		    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (PREMIS.class).getName()),
    		new Selector(new Company(Company.Company_SouthWesternBell, new State(State.State_Kansas), null, null),
    		    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (PREMIS.class).getName()),
    		new Selector(new Company(Company.Company_SouthWesternBell, new State(State.State_Oklahoma), null, null),
    		    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (PREMIS.class).getName()),
    		new Selector(new Company(Company.Company_SouthWesternBell, new State(State.State_Texas), null, null),
    		    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (PREMIS.class).getName())
    	};
    }
    /**
     * Get sagaList for requested zipCode.
     * Creation date: (1/16/02 9:59:18 AM)
     * @return 	List
     * @param zipCode 	String
     */
    public List getSagaListByZipCode(String zipCode){
    
    	// call method to lookup zipcode in zip2saga hashtable
    	return(getLimBase().limBase.findZipCode(zipCode));
    }
    
    /**
     * Get Clec Name for requested exch id and co id as input to database table.
     * Creation date: (1/16/02 9:59:18 AM)
     * @return 	String
     * @param exchId 	String
     * @param coId      String
     * @exception SQLException
     */
    public String getClecNameByExchAndCoId(String exchId, String coId)
    	throws java.sql.SQLException {
    
    	// call table with exch id and co id
    	return(RetrieveClecNameByExchAndCoId.retrieveClecNameByExchAndCoId(
    			getLimProps(), exchId, coId, (Logger) getLimBase().limBase.myLogger.getBisLogger()));
    }
    /**
     * Return a list of host subclasses of this class.
     * Creation date: (3/19/01 10:36:20 AM)
     * @return String[]
     * @param util Utility
     */
    public static String[] getHostList(Utility util)
    {
    	util.log(LogEventId.INFO_LEVEL_2, "PREMIS::getHostList()");
    	return HostList;
    }
    /**
     * Return the business interfaces that this class implements.
     * Creation date: (3/19/01 10:38:34 AM)
     * @return String[]
     * @param util Utility
     */
    public static String[] getInterfaceList(Utility util)
    {
    	util.log(LogEventId.INFO_LEVEL_2, "PREMIS::getInterfaceList()");
    	return new String[] { ServiceAddressLocationI.LOCATION_INTERFACE_NAME };
    }
    /**
     * Getter for the RetrieveServiceLocationBase object.
     * @return RetrieveServiceLocationBase
     */
    protected RetrieveServiceLocationBase getLimBase()
    {
    	return (RetrieveServiceLocationBase) getPassThru();
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
     * @param max  a LongOpt object
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
     * Getter method for the PREMISServerHelper.
     * Creation date: (3/21/01 3:13:20 PM)
     * @return PREMISServerHelper
     * @throws ServiceException ...
     * @see #setPremisServerHelper(PREMISServerHelper)
     */
    protected PREMISServerHelper getPremisServerHelper() throws ServiceException
    {
    	if (premisServerHelper == null)
    	{
    		setPremisServerHelper(new PREMISServerHelper(getLimProps(),getUtility()));
    	}
    	return premisServerHelper;
    }
    /**
     * Return a list of supported companies.
     * Creation date: (3/19/01 10:02:48 AM)
     * @return Company
     * @param util Utility
     * @exception InvalidStateException
     * @exception NullDataException
     * @exception InvalidCompanyException
     */
    public static Company[] getSupportedCompanies(Utility util)
    	throws InvalidStateException, NullDataException, InvalidCompanyException
    {
    	util.log(LogEventId.INFO_LEVEL_2, "PREMIS::getSupportedCompanies()");
    	return new Company[] {
    		new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null),
    		new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null)
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

    /**
     * PremisHit response returned, check for validity and set indicators.  
     * Creation date: (5/10/02 06:01:14 AM)
     * @param request PremisRetrieveLocReq
     * @param hitResp  a PremisHit object
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected void handlePremisHit(PremisRetrieveLocReq request, PremisHit hitResp)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	hitResp.setStNmInd(request.getAddr().getStNmInd());
    	hitResp.setServingArea(PREMISTag.EMPTY_STRING);
    
    	/**
    	 *  Compare for an exact Descriptive Address if specified on the request.
    	 */
    	if (request.getAddr().getAddAddrInfo().length() > 0)
    	{	
    		if ( ! request.getAddr().getAddAddrInfo().equalsIgnoreCase(hitResp.getAppPremisResp().FacActnLn.DESC_ADDR.trim()))
    		{
    			exBldReslt =
    					ExceptionBuilder.parseException(
    						getBisContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_AddressError,
    						"Request and Response are different",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"PREMIS",
    						Severity.UnRecoverable,
    						null);
    		}
    	}
    	
    	/**
    	 *  Check if location is address outside of the SBC territory.
    	 */
    	if ( hitResp.fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase( State.State_California ) || 
    		 hitResp.fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase( State.State_Nevada     )){
    		if ((hitResp.fal.SagInfo.EXCH_ID != null ) &&
    			(hitResp.fal.SagInfo.CO_ID != null )){	
    					
    	 		try{
    		 		String exchId = (hitResp.fal.SagInfo.EXCH_ID.trim().length() > 3) ? 
    		 						 hitResp.fal.SagInfo.EXCH_ID.trim().substring(0,3) : 
    		 						 hitResp.fal.SagInfo.EXCH_ID.trim();
    
    		 		String coId = (hitResp.fal.SagInfo.CO_ID.trim().length() > 2) ? 
    		 					   hitResp.fal.SagInfo.CO_ID.trim().substring(0,2) : 
    		 					   hitResp.fal.SagInfo.CO_ID.trim();
    
                    getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::handlePremisHit()|PREMIS::getClecNameByExchAndCoId()|PRE");
    				String clecName = getClecNameByExchAndCoId(exchId, coId);
                    getUtility().log(LogEventId.AUDIT_TRAIL,"PREMIS::handlePremisHit()|PREMIS::getClecNameByExchAndCoId()|POST");
                    
    				// table currently does not return Exhausted Resultset, must check result length
    				if (clecName.length() > 0)
    		 			hitResp.setServingArea((clecName + ", " + exchId + " " + coId));
    
    	 		}
    	 		catch (SQLException e) {
    				if (!(e.getMessage().indexOf("Exhausted Resultset") >= 0)) {
    					exBldReslt =
    						ExceptionBuilder.parseException(
    							getBisContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_OracleError,
    							"Clec_Exco: " + e.getMessage(),
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							utility,
    							"PREMIS",
    							Severity.Recoverable,
    							null);
    				}
    			}  // catch SQLException
    		} // != null
    	}
    	else if (hitResp.fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase( State.State_Arkansas ) || 
    		 	 hitResp.fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase( State.State_Kansas   ) ||
    		 	 hitResp.fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase( State.State_Missouri ) ||
    		 	 hitResp.fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase( State.State_Oklahoma ) ||
    		 	 hitResp.fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase( State.State_Texas    )){ 
    	 			if ((hitResp.fal.SagInfo.CO_ID != null) &&
    	 				(hitResp.fal.SagInfo.CO_ID.trim().length() > 0)){
    					hitResp.setServingArea(hitResp.fal.SagInfo.CO_ID);
    				}
    	}
    }
    /**
     * We have to shield the client from saga.  Resend the request with each saga from the
     * saga menu.
     * For each one:
     *   If we get a hit, make sure it matches the original request before using it.
     *   If we get a menu: if there are no hits, use it, but if there are hits, throw it away.
     * Creation date: (3/27/01 12:01:14 PM)
     * @return RetrieveLocResp
     * @param request PremisRetrieveLocReq
     * @param sagaResp PremisSagaResp
     * @exception ServiceException
     * @exception SystemFailure
     * @exception InvalidData
     */
    protected RetrieveLocResp handleSagaMenu(
    	PremisRetrieveLocReq request,
    	PremisSagaResp sagaResp)
    	throws ServiceException, SystemFailure, InvalidData {
    	ServiceException serviceException = null;
    	PremisRetrieveLocReq temp = null;
    	PremisComboResp response = new PremisComboResp(getLimBase().limBase);
    	response.setMaxAddresses(sagaResp.getMaxAddresses());
    	response.setMaxUnits(sagaResp.getMaxUnits());
    	AddressHandler reqAddr = request.getAddr();
    	AddressHandler rspAddr = null;
        
    	PremisMultiplexerData reqArray[] =
    		new PremisMultiplexerData[sagaResp.getSagaList().size()];
    	PremisMultiplexerMethods premisMethods =
    		new PremisMultiplexerMethods(getLimBase().limBase, getLimProps());
        premisMethods.setPremisSvcNm(getPremisSvcNm());
    	int j = 0;
    
    	Iterator it = sagaResp.getSagaList().iterator();
    	while (it.hasNext()) {
    		//  PREMIS_SAGA_MENU is returned on input by Service (TN)
    		//  PREMIS_ZIP_MENU is returned on input by Address
    		//  must check to see which type of request originated list, 
    		//  for Address request zipCode can never be blank
    		if (request.getAddr().getPostalCode() == null
    			|| request.getAddr().getPostalCode().length() == 0)
    			temp =
    				new PremisRetrieveLocReq(
                        getLimBase().limBase, 
                        request.getTn(),
                        request.getPropertiesToGetArray());
    					
    		else
    			temp =
    				new PremisRetrieveLocReq(
    					getLimBase().limBase,
    					request.getAddr(),
                        request.getPropertiesToGetArray(),
                        request.getPreviousOwnerName());
    		String saga = (String) it.next();
    		temp.setSaga(saga);
    		reqArray[j] = new PremisMultiplexerData(saga, temp);
    		j++;
    	}
    	try {
    		VicunaWrapperMultiplexer eng =
    			new VicunaWrapperMultiplexer(getUtility(), 
                    getPremisMaxHelpers(), getPremisTimeout(), getPremisSliceTimeout());
    		eng.run(premisMethods, reqArray);
    	}
    	catch (VicunaWrapperMultiplexerException ve) {
    		try {
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", ve.getMessage());
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					getBisContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					"VicunaWrapperMultiplexerException",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"PREMIS",
    					Severity.UnRecoverable,
    					tagValues);
    		}
    		catch (SystemFailure sf) {
    			throw sf;
    		}
    		catch (Exception e) {} //hide the other exception types
    	}
        
    	PremisMultiplexerData workingData = null;
    	RetrieveLocResp workingResp = null;
     
     
        // Loop thru reponses from VicunaMultiplexer process
        // Add data to appropriate response array
        // A ServiceTimeOutException overrides a normal Premis exception
        // need to return timeout if PremisHit not found. 
        
    	for (int i = 0; i < reqArray.length; i++) {
    		try {
                workingResp = null;
    			workingData = reqArray[i];
                if (workingData.dataResp != null){
                    workingResp =
                        PremisResponseFactory.fromVicuna(
                            getLimBase().limBase,
                            workingData.getDataResp(),
                            request,
                            sagaResp.getMaxAddresses(),
                            sagaResp.getMaxUnits());
                   }
    			else if (workingData.serviceException != null) {
    				if (serviceException == null)
    					serviceException = workingData.serviceException;
    			}
                else if (workingData.serviceTimeoutException != null) {
                    if ((serviceException == null)  ||
                        (!(serviceException instanceof ServiceTimeoutException)))
                        serviceException = (ServiceException)workingData.serviceTimeoutException;
                }
                else{
                    exBldReslt =
                        ExceptionBuilder.parseException(
                            getBisContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_InternalError,
                            "VicunaWrapperMultiplexerData",
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            utility,
                            "PREMIS",
                            Severity.UnRecoverable,
                            null);
                }
    		}
    		catch (SystemFailure sf) {
    			throw sf;
    		}
            catch (Exception e) {} //hide the other exception types
            
    		if (workingResp instanceof PremisHit) {
    			PremisHit ph = (PremisHit) workingResp;
    			ph.setStNmInd(request.getAddr().getStNmInd());
    			rspAddr = ph.getServiceAddress();
    			String respCity = rspAddr.getCity();
    			if (respCity.startsWith(PREMISTag.THREE_DOLLAR_SIGNS)
    				|| respCity.startsWith(PREMISTag.THREE_ASTERICKS))
    				respCity = respCity.substring(3);
    			// a hit isn't really a hit unless it matches the provided request fields
    			if ((ph.getStNmInd() == false)
    				&& (reqAddr.getRoute().length() == 0
    					|| reqAddr.getRoute().equalsIgnoreCase(rspAddr.getRoute()))
    				&& (reqAddr.getBox().length() == 0
    					|| reqAddr.getBox().equalsIgnoreCase(rspAddr.getBox()))
    				&& (reqAddr.getHousNbr().length() == 0
    					|| reqAddr.getHousNbr().equalsIgnoreCase(
    						rspAddr.getHousNbr()))
    				&& (reqAddr.getStName().length() == 0
    					|| reqAddr.getStName().equalsIgnoreCase(rspAddr.getStName()))
    				&& (reqAddr.getCity().length() == 0
    					|| reqAddr.getCity().equalsIgnoreCase(respCity))
    				&& (reqAddr.getState().length() == 0
    					|| reqAddr.getState().equalsIgnoreCase(rspAddr.getState()))
    				&& (reqAddr.getPostalCode().length() == 0
    					|| reqAddr.getPostalCode().equalsIgnoreCase(
    						rspAddr.getPostalCode()))) {
    				response.add(ph);
    			}
    			else if (
    				(ph.getStNmInd())
    					&& ((rspAddr.getStName().trim())
    						.substring(0, 3)
    						.equals("@ ,"))
    					&& (reqAddr.getCity().length() == 0
    						&& reqAddr.getCity().equalsIgnoreCase(
    							(rspAddr.getStName()).substring(3)))
    					&& (reqAddr.getState().length() == 0
    						|| reqAddr.getState().equalsIgnoreCase(
    							rspAddr.getState()))
    					&& (reqAddr.getPostalCode().length() == 0
    						|| reqAddr.getPostalCode().equalsIgnoreCase(
    							rspAddr.getPostalCode()))) {
    				response.add(ph);
    			}
    			else if (
    				(ph.getStNmInd())
    					&& (reqAddr.getStName().length() == 0
    						|| reqAddr.getStName().equalsIgnoreCase(
    							(rspAddr.getStName()).substring(2)))
    					&& (reqAddr.getCity().length() == 0
    						|| reqAddr.getCity().equalsIgnoreCase(respCity))
    					&& (reqAddr.getState().length() == 0
    						|| reqAddr.getState().equalsIgnoreCase(
    							rspAddr.getState()))
    					&& (reqAddr.getPostalCode().length() == 0
    						|| reqAddr.getPostalCode().equalsIgnoreCase(
    							rspAddr.getPostalCode()))) {
    				response.add(ph);
    			}
    			else {
    				getUtility().log(
    					LogEventId.DEBUG_LEVEL_1,
    					"This Premis 'hit' response is not considered a match:\n"
    						+ rspAddr.toString());
    				if (response.matches.size() == 0) {
    					//this is different from the request fields so convert it to a list response
    					PremisAddrListResp pal =
    						new PremisAddrListResp(ph.getUtility(), ph.apr);
    					pal.getAddrList().add(rspAddr.getAddress());
    					response.add(pal);
    				}
    			}
    		}
    		else if (workingResp instanceof PremisAddrListResp) {
    			if (response.matches.size() == 0)
    				//don't need these if we have matches
    				response.add((PremisAddrListResp) workingResp);
    		}
    		else if (workingResp instanceof PremisAddrRangeResp) {
    			if (response.matches.size() == 0) {
    				//don't need these if we have matches
    				((PremisAddrRangeResp) workingResp).setOrigReq(
    					(AddressHandler) request.getAddr());
    				response.add((PremisAddrRangeResp) workingResp);
    			}
    		}
    	} // end for loop
     
     
        // Check PREMIS matches array has PremisHit, 
        // use first PremisHit in array   
    	if (response.matches.size() > 0) {
    		response.lists.clear();
    		response.ranges.clear();
    		return (PremisHit) response.matches.get(0);
    	}
     
        // If no PremisHit found, but there was a ServiceTimeoutException
        // return ServiceTimeoutException  
        if (serviceException instanceof ServiceTimeoutException){
                throw serviceException;
        }
     
        // if a list of addresses is found, 
        // do not send ranged address alternatives  
    	if (response.lists.size() > 0) {
    		response.ranges.clear();
    	}
     
        // if no data is found, return exception   
    	if (response.lists.size() == 0 &&
    		response.ranges.size() == 0){ 
             //no good responses at all!
    		if (serviceException != null)
    			throw serviceException;
            try {
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
                        "PREMIS",
                        Severity.UnRecoverable,
                        null);
            }
            catch (SystemFailure sf) {
              throw sf;
            }
            catch (Exception e) {} //hide the other exception types
        }
        
    	return response;
    }
    /**
     * Handle a ServiceException from the PREMISServer Vicuna wrapper.
     * Creation date: (3/28/01 11:12:53 AM)
     * @param svcExc ServiceException
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     * @exception DataNotFound
     */
    protected void handleServiceException(ServiceException svcExc)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	String code = "";						
    	String errText = svcExc.getClass().getName();
    	String timeOut = "";
    	
    	if (svcExc instanceof ServiceTimeoutException)
    	{
    		/////code = ExceptionCode.ERR_LIM_TIMEOUT;
    		code = LIMTag.CSV_LimTimedOutCode;		// Define as Timed out exception code in csv file.
    		if ((svcExc.getMessage().indexOf("PREMIS")) > 0)
    			timeOut = "PREMIS time out. ";
    		else
    		if ((svcExc.getMessage().indexOf("HOSTLOOKUP")) > 0)
    			timeOut = "HOSTLOOKUP time out. ";
    		else
    			timeOut = "HOST time out. ";
    	}
    
    	errText = timeOut + errText.substring(errText.lastIndexOf(".")+1) +
    		": Code[" + svcExc.getExceptionCode() + "], OriginalCode[" +
    		svcExc.getOriginalExceptionCode() + "], Message[" + svcExc.getMessage() + "]";
    
    	/****	
    	/**
    	 * TN is valid but it's not in the HostLookup table
    	 *
    	if ( svcExc.getExceptionCode().equalsIgnoreCase(ExceptionCode.ERR_GWS_HOSTLOOKUP) ) {
    		if (svcExc.getOriginalExceptionCode().equals("50") ) {
    			code = ExceptionCode.ERR_LIM_SERVICE_NOT_FOUND;
    		}
    	}
    	/**
    	 * Determine if there was a TOPServer specific exception.
    	 *
    	if ( svcExc.getExceptionCode().equalsIgnoreCase(ExceptionCode.ERR_GWS_PREMISSERVER) ) {
    		if (svcExc.getOriginalExceptionCode().equals("7600") ) {
    			code = ExceptionCode.ERR_LIM_TOPSERVER;
    		}
    	}
    	*****/
    
    	if( code.length() < 1 )
    		code = String.valueOf(svcExc.getOriginalExceptionCode());
    
    	/**
    	 *  Use the Rules file to determine which exception code to throw based on 
    	 *  the Original Exception code from the legacy system.
    	 */
    	exBldReslt = ExceptionBuilder.parseException(
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
    					"PREMIS",
    					Severity.UnRecoverable,
    					null);
    
    }
    /**
     * TN Menu response returned with only address data and no location information.  
     * Resend the request with address data as input to retrieve PREMIS_HIT and all location data
     * Creation date: (2/21/02 12:01:14 PM)
     * @return RetrieveLocResp
     * @param request PremisRetrieveLocReq
     * @param tnMenu   a PremisTNMenu object
     * @exception ServiceException
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected RetrieveLocResp handleTNMenu(PremisRetrieveLocReq request, PremisTNMenu tnMenu)
    	throws ServiceException, InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	
    	if (tnMenu.premisAddr == null)
    		exBldReslt =
    					ExceptionBuilder.parseException(
    						getBisContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_TnServiceError,
    						"Working Status",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"PREMIS",
    						Severity.UnRecoverable,
    						null);
    
    	getUtility().log(LogEventId.INFO_LEVEL_2,
    				"PREMIS.HandleTNMenu(): Resend following address to PREMISServer for TN[" + 
    				 request.getTn().toString() + "] " + tnMenu.premisAddr.toString());
    			
    	getLimBase().limBase.log(LogEventId.REMOTE_CALL, PREMISTag.NAME, getPremisSvcNm(), PREMISTag.SERVER,
    		PREMISTag.PREMIS_VALDT_ADDR_REQ);
    	
    	RetrieveLocResp response = PremisResponseFactory.fromVicuna( getLimBase().limBase,
    		getPremisServerHelper().premisValdtAddrReq(null, null, ServiceHelper.USE_DEFAULT_TIMEOUT, 
    		tnMenu.getValdtAddrReq(request)), request, tnMenu.getMaxAddresses(), tnMenu.getMaxUnits() );
    	
    	getLimBase().limBase.log(LogEventId.REMOTE_RETURN, PREMISTag.NAME, getPremisSvcNm(), PREMISTag.SERVER,
    		PREMISTag.PREMIS_VALDT_ADDR_REQ);
    	
    	if (!(response instanceof PremisHit))
    	{
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
    						"PREMIS",
    						Severity.UnRecoverable,
    						null);
    	}
    
    	return response;
    }
    /**
     * Return true if the line status is "working".
     * Creation date: (4/27/01 10:30:55 AM)
     * @return boolean
     * @param lnInfo LtdLnInfo_t
     */
     
    public static boolean lineIsWorking(LtdLnInfo_t lnInfo)
    {
    	if (lnInfo.LN_STS_ID.equalsIgnoreCase(PREMISTag.LINE_WORKING))
    		return true;
    	return false;
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
     * Premis host.
     * Creation date: (2/22/01 12:45:09 PM)
     * @return RetrieveLocationForAddressReturn
     * @param aContext BisContext
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
    public RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddress(
            Address aAddress, 
            String[] aLocationPropertiesToGet,
            String aPreviousOwnerName,
            String aCrossBoundaryState,
        	int aMaximumBasicAddressReturnLimit, 
            int aMaximumUnitReturnLimit)
    throws InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			DataNotFound, 
			SystemFailure, 
			NotImplemented, 
			ObjectNotFound
	{
    	RetrieveLocationForServiceAddressReturn result = null;
    
    	try {
    		
    		AddressHandlerPremis addrPremis = new AddressHandlerPremis(aAddress);
    
    		if (addrPremis.getHousNbrSfx().length() > 0) {
    			addrPremis.setHousNbrSfx(
    	            AddressHandlerPremis.getHouseNumberSuffix(addrPremis.getHousNbrSfx()));
    		}
    
    		PremisRetrieveLocReq request =
    			new PremisRetrieveLocReq(getLimBase().limBase, addrPremis, aLocationPropertiesToGet, aPreviousOwnerName);
            request.logAddressReq();
    		request.setSaga(getLimBase().getSaga());
            // If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1) request is for exactMatch only response
            request.setExactMatchReq(aMaximumBasicAddressReturnLimit == LIMTag.EXACT_MATCH_REQ ? true : false);
    
            getLimBase().limBase.log(LogEventId.AUDIT_TRAIL, "PREMIS::retrieveLocationForServiceAddress()|PREMIS::doServiceAddressValidation()|PRE");
    		result =
    			(doServiceAddressValidation(request,
    				aMaximumBasicAddressReturnLimit,
    				aMaximumUnitReturnLimit,
                    getSagaListByZipCode(addrPremis.getPostalCode())))
    				.toServiceAddressReturn();
            getLimBase().limBase.log(LogEventId.AUDIT_TRAIL, "PREMIS::retrieveLocationForServiceAddress()|PREMIS::doServiceAddressValidation()|POST");
    	} 
        catch (ServiceException e) {
    		handleServiceException(e);
    	} 
        catch (AddressHandlerException ahe) {
     	}
    
    	return result;
    }
    
    /**
     *  Implementation of method retrieveLocationForTelephoneNumber() from the ServiceAddressLocationI interface by
     *  PREMIS host.
     *  @return RetrieveLocationForTelephoneNumberReturn
     *  @param aTelephoneNumber TelephoneNumber
     *  @param aLocationPropertiesToGet String[]
     *  @exception InvalidData
     *  @exception AccessDenied
     *  @exception BusinessViolation
     *  @exception DataNotFound
     *  @exception SystemFailure
     *  @exception NotImplemented
     *  @exception ObjectNotFound
     */
    public RetrieveLocationForTelephoneNumberReturn retrieveLocationForTelephoneNumber (
        	TelephoneNumber aTelephoneNumber,
        	String[] aLocationPropertiesToGet) 
    	throws 
    		InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		DataNotFound, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound
    {
    	RetrieveLocationForTelephoneNumberReturn result = null;
    	int maxAddressLimit=0;
		int maxUnitLimit=0;
    	try
    	{
    		TN aService = new TN(aTelephoneNumber.aNpa + aTelephoneNumber.aNxx + aTelephoneNumber.aLine);
    		PremisRetrieveLocReq  request  = new PremisRetrieveLocReq(getLimBase().limBase, aService, aLocationPropertiesToGet);
            request.logServiceReq();
    		request.setSaga( getLimBase().getSaga(aService) );
            getUtility().log(LogEventId.DEBUG_LEVEL_2,"Service: " + aService.toString() + "  Saga: " + request.getSaga());
    
    
    		/**
    		 *  Throw exception if SAGA is blank.
    		 */
    		if (request.getSaga().length() == 0){
    			exBldReslt =
    					ExceptionBuilder.parseException(
    						getBisContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_TnServiceError,
    						"TN Not Found with SAGA in HostLookup",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"HostLookup",
    						Severity.UnRecoverable,
    						null);
    					
    		}
    
            getLimBase().limBase.log(LogEventId.AUDIT_TRAIL, "PREMIS::retrieveLocationForTelephoneNumber()|PREMIS::doServiceAddressValidation()|PRE");
    		
			RetrieveLocResp response = 
                    doServiceAddressValidation( request,
                    		maxAddressLimit, maxUnitLimit,
                                         null);
            getLimBase().limBase.log(LogEventId.AUDIT_TRAIL, "PREMIS::retrieveLocationForTelephoneNumber()|PREMIS::doServiceAddressValidation()|POST"); 
            
    		if (response instanceof PremisHit)
    		{
              	getUtility().log(LogEventId.DEBUG_LEVEL_2,"Checking if exact match TN is in WORKING status..");
              	//check to see if Premis Hit TN has a Working Status by searching for the TN in the Working Tn Set
            	if(!((PremisHit) response).getWorkingTnSet().contains((TN) aService))
    	        {
            
        	    	exBldReslt =
    						ExceptionBuilder.parseException(
    							getBisContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_TnServiceError,
    							"Working Status",
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							utility,
    							"PREMIS",
    							Severity.UnRecoverable,
    							null);
    			}
    		}
    		getLimBase().limBase.log(LogEventId.AUDIT_TRAIL, "PREMIS::retrieveLocationForTelephoneNumber()|PREMIS::toTelephoneNumberReturn()|PRE");
     		result = response.toTelephoneNumberReturn();   		
            getLimBase().limBase.log(LogEventId.AUDIT_TRAIL, "PREMIS::retrieveLocationForTelephoneNumber()|PREMIS::toTelephoneNumberReturn()|POST");
    	}
    	catch (ServiceException e)
    	{
    		handleServiceException(e);
    	}
    
    	return result;
    }
    
    /**
     * Setter method for the PREMISServerHelper.
     * Creation date: (3/21/01 3:13:20 PM)
     * @param newPremisServerHelper PREMISServerHelper
     * @see #getPremisServerHelper
     */
    protected void setPremisServerHelper(PREMISServerHelper newPremisServerHelper)
    {
    	premisServerHelper = newPremisServerHelper;
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
	* Created by Siva Papineni(sp5136) 11/07/04

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
	   try
	   {
		   LIMOverrideParser limOverride = new LIMOverrideParser(aContext,getLimBase().limBase);
		   overrideValue = limOverride.handleOverride();
		   if (limOverride.isPREMIS)
		   {
			   premisOverride = overrideValue;
			   getUtility().log(LogEventId.INFO_LEVEL_1,"Overriding PREMIS SERVICE_APPLDATA, default value: "
							   + getLimProps().getProperty(LIMTag.PREMIS_APPLDATA) + " override value: "
							   + premisOverride);
		   } 
	   }
	   catch(OverrideParserSystemFailureException e)
	   {
		   getUtility().log(LogEventId.EXCEPTION, "System Failure in loading routing override table: " +
											   e.getMessage());
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
				   "PREMIS",
				   Severity.UnRecoverable,
				   null); 					
	   }
	   catch(OverrideParserAccessDeniedException e)
	   {
		   getUtility().log(LogEventId.EXCEPTION, "Access Denied in loading routing override table: " + 
											   e.getMessage());
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
				   "PREMIS",
				   Severity.UnRecoverable,
				   null); 					
	   }
   }	*/
}
