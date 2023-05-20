// $Id: RetrieveLocationBase.java,v 1.47 2007/10/07 22:45:57 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.*;
import com.sbc.eia.bis.BusinessInterface.ason.ASON;
import com.sbc.eia.bis.BusinessInterface.brms.BRMS;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.BusinessInterface.ovals.OVALS;
import com.sbc.eia.bis.BusinessInterface.ovalsnsp.OVALSNSP;
import com.sbc.eia.bis.BusinessInterface.ovalsusps.OVALSUSPS;
import com.sbc.eia.bis.BusinessInterface.premis.PREMIS;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.service.hostlookup.HOSTLOOKUPHelper;
import com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R;
import com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTN;
import java.rmi.RemoteException;
import com.sbc.eia.bis.BusinessInterface.ovalsnsp.OVALSNSPTag;
import com.sbc.eia.bis.BusinessInterface.ovalsusps.OVALSUSPSTag;

/**
 * A superclass containing common code for the two RetrieveLocation transaction
 * classes, RetrieveLocationForAddressTrans and RetrieveLocationForServiceTrans.
 * Creation date: (3/27/01 3:33:38 PM)
 * @author: David Prager
 */
public class RetrieveLocationBase 
{
	protected CompanyFactory companyFactory = null;
	protected String[] hostList = new String[] { 
                                               (ASON.class).getName(), 
                                               (BRMS.class).getName(), 
                                               (PREMIS.class).getName(), 
                                               (OVALS.class).getName(), 
                                               (OVALSUSPS.class).getName(),
                                               (OVALSNSP.class).getName(),
                                               };
	protected static String hostLookupSvcNm = "";
	protected Company company = null;
	protected boolean setCache = false;
	protected boolean retrieveCache = false;
    protected boolean processUsps = false;
    protected boolean processNsp = false;
    protected boolean processSrvc = false;
    protected RetrieveLocReq req1 = null;

	private HOSTLOOKUPHelper hostLookupHelper = null;
	private HostLookupFull_R hostLookupRecord = null;
	private String saga = "";
	private OVALSNSP m_OvalsNsp = null;

	public LIMBase limBase = null;
		
	ExceptionBuilderResult exBldReslt = null;
	String ruleFile =null;


    /**
     * Getter method for the CompanyFactory.
     * Creation date: (3/26/01 11:11:30 AM)
     * @return CompanyFactory
     * @throws BusinessException
     * @see #setCompanyFactory(CompanyFactory)
     */
    protected CompanyFactory getCompanyFactory() throws BusinessException
    {
    	if (companyFactory == null)
    	{
    		setCompanyFactory(new CompanyFactory(hostList,limBase,limBase.getPROPERTIES()));
    	}
    	return companyFactory;
    }
    
    /**
     * Setter method for the CompanyFactory.
     * Creation date: (3/26/01 11:11:30 AM)
     * @param newCompanyFactory CompanyFactory
     * @see #getCompanyFactory
     */
    protected void setCompanyFactory(CompanyFactory newCompanyFactory)
    {
    	companyFactory = newCompanyFactory;
    }
    
    /**
     * Getter for HostLookupHelper of RetrieveLocationBase class.
     * Creation date: (4/25/01 10:11:27 AM)
     * @return HOSTLOOKUPHelper
     * @throws ServiceException
     * @see #setHostLookupHelper(HOSTLOOKUPHelper)
     */
    public HOSTLOOKUPHelper getHostLookupHelper() throws ServiceException
    {
    	if (hostLookupHelper == null)
    	{
    		setHostLookupHelper(new HOSTLOOKUPHelper(limBase.getPROPERTIES(),limBase));
    	}
    	return hostLookupHelper;
    }
    
    /**
     * Getter for hostLookupSvcNm used for logging purposes
     * Creation date: (10/29/02 11:01:38 AM)
     * @return String
     */
    public String getHostLookupSvcNm() {
    	
    	try{
    		if (hostLookupSvcNm.length() > 0)
    			return (hostLookupSvcNm);
    
            StringBuffer temp = new StringBuffer();
            temp.append(((Properties)(limBase.getPROPERTIES())).getProperty("GWS_HOSTLOOKUP").trim());
            hostLookupSvcNm = temp.toString();
    	}
    	catch(NullPointerException npe){}
    	
    	return (hostLookupSvcNm);
    }
    
    /**
     * Getter method for the HostLookup record.  If it's null, invoke HostLookup service
     * to get it.
     * Creation date: (4/30/01 10:16:01 AM)
     * @return HostLookupFull_R
     * @param service TN
     * @exception ServiceException
     * @see #setHostLookupRecord(HostLookupFull_R)
     */
    public HostLookupFull_R getHostLookupRecord(TN service) throws ServiceException
    {
    	if (hostLookupRecord == null)
    	{
    		limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationBase::getHostLookupRecord()|RetrieveLocationBase::setHostLookupRecord()|PRE");
    
    		limBase.log(LogEventId.REMOTE_CALL, RetrieveLocTag.HOSTLOOKUP, getHostLookupSvcNm(), RetrieveLocTag.GET_HOST_LOOKUP_RECORD,
    			RetrieveLocTag.HL_LOOKUP_FULL);
    		
    		setHostLookupRecord( (HostLookupFull_R) getHostLookupHelper().
    			hlLookupFull(null,null,ServiceHelper.USE_DEFAULT_TIMEOUT,new HostLookupTN(service.toString())).
    			getTheObject() );
    		
    		limBase.log(LogEventId.REMOTE_RETURN, RetrieveLocTag.HOSTLOOKUP, getHostLookupSvcNm(), RetrieveLocTag.GET_HOST_LOOKUP_RECORD,
    			RetrieveLocTag.HL_LOOKUP_FULL);
    			
    		limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationBase::getHostLookupRecord()|RetrieveLocationBase::setHostLookupRecord()|POST");
    	}
    	return hostLookupRecord;
    }
    
    /**
     * Getter method for the PREMIS SAGA that just returns whatever is stored, empty or not.
     * Creation date: (4/25/01 11:55:25 AM)
     * @return String
     * @see #setSaga(String)
     */
    public String getSaga()
    {
    	return saga;
    }
    
    /**
     * Getter for the PREMIS SAGA that looks it up (by TN) if it's empty.
     * Creation date: (5/24/01 10:13:36 AM)
     * @return String
     * @param service TN
     * @throws ServiceException
     * @see #setSaga(String)
     */
    public String getSaga(TN service) throws ServiceException
    {
    	if (saga.length() == 0)
    	{
    		setSaga(getHostLookupRecord(service).saga);
    	}
    	return saga;
    }
    
    /**
     * Setter for HostLookupHelper of RetrieveLocationBase class.
     * Creation date: (4/25/01 10:11:27 AM)
     * @param newHostLookupHelper HOSTLOOKUPHelper
     * @see #getHostLookupHelper
     */
    protected void setHostLookupHelper(HOSTLOOKUPHelper newHostLookupHelper)
    {
    	hostLookupHelper = newHostLookupHelper;
    }
    
    /**
     * Setter for HostLookupRecord of RetrieveLocationBase class.
     * Creation date: (4/30/01 10:30:07 AM)
     * @param newHostLookupRecord HostLookupFull_R
     * @see #getHostLookupRecord
     */
    public void setHostLookupRecord(HostLookupFull_R newHostLookupRecord)
    {
    	hostLookupRecord = newHostLookupRecord;
    }
    
    /**
     * Setter method for the PREMIS SAGA.
     * Creation date: (4/25/01 11:55:25 AM)
     * @param newSaga String
     * @see #getSaga
     */
    public void setSaga(String newSaga)
    {
    	saga = (newSaga == null) ? "" : newSaga;
    }
    
    /**
     * Constructor for RetrieveLocationBase.
     * Creation date: (4/17/01 9:32:33 AM)
     * @param lim_base LIMBase
     */
    public RetrieveLocationBase (LIMBase lim_base)
    {
    	limBase = lim_base;
    	ruleFile = (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
    }
        
    /**
     * Use the BusinessInterface framework to get an object that implements the LocationI interface.
     * The caller can pass either address or service, and the other null.
     * Creation date: (3/28/01 10:06:10 AM)
     * @return LocationI
     * @param address AddressHandler
     * @param service TN
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    protected LocationI getLocationI(String astateCode,TN service, String aBusinessUnit)
    throws InvalidData, 
    	   AccessDenied, 
    	   BusinessViolation, 
    	   DataNotFound, 
    	   SystemFailure, 
    	   NotImplemented, 
    	   ObjectNotFound
    {
    	LocationI locationI = null;
    	Company co = null;
		String providerName = "";
    	StringBuffer logText = new StringBuffer("Mapped ");
    	String serviceCenter = (new BisContextManager (limBase.getCallerContext())).getServiceCenter();
    	setHostLookupRecord(null);
    
    	limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationBase::getLocationI()|Company::getBusinessInterface()|PRE");
    	try
    	{	            
            if (processUsps) 
            { 
                co = getCompanyFactory().getCompany(OVALSUSPSTag.Company_USPostalService);
            } 
            
            else if (processNsp) 
            { 
                co = getCompanyFactory().getCompany(OVALSNSPTag.Company_OvalsNSP);
            }
            
   			//Route request by Service Center.
            else if (serviceCenter != null && serviceCenter.length() > 0)
    		{	
    			logText.append("ServiceCenter [" + serviceCenter + "]");
    			if (serviceCenter.equals ("SBCT"))
    				co = getCompanyFactory().getCompany(Company.Company_SBCTelecom);
    			else
   					co = getCompanyFactory().getCompany(new ServiceCenter(serviceCenter.toUpperCase()));
   			}
   			// Route request by address
   			else if (astateCode != null)
    		{
    			String stateCode = astateCode;
    			if (stateCode.length() > 0)
    			{
    				co = getCompanyFactory().getCompany(new State(stateCode));
    				logText.append("State [" + stateCode + "]");
    			}
    			else	// have to use saga, if there is no saga => default to OVALS
    			{
    				logText.append("Saga [" + getSaga() + "]");	
    				if ((getSaga().length()) > 1) // PREMIS
    					co = getCompanyFactory().getCompany(Company.Company_PacificBell);
    				else if (getSaga().length() > 0)					// ASON
    					co = getCompanyFactory().getCompany(Company.Company_Ameritech);
    				else
    					co = getCompanyFactory().getCompany(Company.Company_SBCTelecom);
    			}
    		}
    		//Route request by TN service
    		else if (service != null) 
    		{
    			logText.append("TN [" + service.toString() + "]");
    			setSaga(getHostLookupRecord(service).saga);
    			co = getCompanyFactory().getCompany(getHostLookupRecord(service).location);
    		}
    		else  //should never happen, nulls errored before call to getlocatinI
    		{
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					null,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					null);
    		}
    
    		logText.append(" to Company [" + co.getCode() + "]");
    		limBase.log(LogEventId.DEBUG_LEVEL_1,logText.toString());
    
    		company = co;
    		locationI = (LocationI) co.getBusinessInterface(LocationI.LOCATION_INTERFACE_NAME,this);
			if (locationI.getClass().equals(PREMIS.class)) {
				locationI = getOvalsNSP(this.company);
			}
    	}
    	catch (BusinessException be)
    	{
    		limBase.handleBusinessException(be,locationI);
    	}
    	catch (ServiceException se)
    	{
    
    		Properties tagValues2 = new Properties();
    		tagValues2.setProperty("CLASS", Address.class.getName());
    		tagValues2.setProperty("DESCRIPTION", se.getMessage());
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_TnServiceError,
    					"Host Lookup Exception: Message[Number not found <" + service.toString() + ">]",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues2);
    	}
    
    	limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationBase::getLocationI()|Company::getBusinessInterface()|POST");	
    	return locationI;
    }
    
    /**
     * Return true if and only if the param company equals
     * the company produced by the CompanyFactory for the param state.
     * Creation date: (01/14/02 11:11:30 AM)
     * @return boolean
     * @param company String
     * @param state String
     */
    protected boolean validateCompany (String company, String state)
    {
    	try
    	{
    		Company co = getCompanyFactory().getCompany (new State (state));
    		if (co.getCode (). equals (company))
    			return true;
    		else
    			return false;
    	}
    	catch (BusinessException e)
    	{
    		return false;
    	}
    }
    
    /**
     * Determine if setCache and retrieveCache should be done.
     */
    protected void checkCacheExcept ()
    {
    	setCache = true;
    	retrieveCache = true;
    
    	if (((Properties)(limBase.getPROPERTIES())).getProperty("ACCESS_CACHE_ADDRESS_EXCEPT") != null)
    	{
    		String exceptCacheList = ((Properties)(limBase.getPROPERTIES())).getProperty("ACCESS_CACHE_ADDRESS_EXCEPT").trim();
    		if (exceptCacheList != null && exceptCacheList.length() > 0)
    		{
    			String bisApp = (new BisContextManager(limBase.getCallerContext())).getApplication().trim();
    			if (bisApp != null && bisApp.length() > 0)
    			{
    				StringTokenizer st = new StringTokenizer (exceptCacheList, ",", false);
    				while (st.hasMoreTokens())
    				{
    					if(st.nextToken().trim().equalsIgnoreCase(bisApp.trim()))
    					{
    						retrieveCache = false;
    						break;
    					}
    				} // end while
    			} // end if (bisApp != null && bisApp.length() > 0)		
    		} // end if (accessCacheList != null && accessCacheList.length() > 0)
    	} // end if (((Properties)(limBase.getPROPERTIES())).getProperty("ACCESS_CACHE_ADDRESS_EXCEPT") != null)
    	else
    	{
    		setCache = false;
    		retrieveCache = false;	
    	}
    }
    
    /**
     * Edit ProviderLocationProperties input for missing data, invalid data, business violations.
     * Creation date: (7/13/01 12:10:29 PM)
     * @param addr Address
     * @param providerLocPropsSeqOpt ProviderLocationPropertiesSeqOpt
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    protected void editProviderLocationProperties(ProviderLocationPropertiesSeqOpt providerLocPropsSeqOpt) 
    throws InvalidData, 
           SystemFailure, 
           BusinessViolation, 
           DataNotFound, 
           AccessDenied, 
           ObjectNotFound, 
           NotImplemented
    {
        
        LIMBisContextManager limBisContextMgr = new LIMBisContextManager (limBase.getCallerContext());
        
        Properties tagValues = new Properties();
        tagValues.setProperty("APPLNAME", limBisContextMgr.getApplication());
        String businessUnit = "";
        if (limBisContextMgr.getBusinessUnit() == null)
        	businessUnit = "null";
        else
            businessUnit = limBisContextMgr.getBusinessUnit();
        tagValues.setProperty("BUSUNIT", businessUnit);
        if (providerLocPropsSeqOpt != null &&
            providerLocPropsSeqOpt.discriminator() && providerLocPropsSeqOpt.theValue().length > 1)
                exBldReslt = 
                    ExceptionBuilder.parseException(
                            limBase.getCallerContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_EditError,
                            "Can specify only one set of properties",
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            limBase.myLogger.getBisLogger(),
                            "LIM",
                            Severity.UnRecoverable,
                            tagValues);
            
        if (providerLocPropsSeqOpt != null &&
        	providerLocPropsSeqOpt.discriminator() && providerLocPropsSeqOpt.theValue().length > 0)
        {              
            ProviderLocationProperties[] providerLocProps = providerLocPropsSeqOpt.theValue();
        
            ProviderLocationProperties[] props1 = { providerLocProps[0] };
            req1 = new RetrieveLocReq(this.limBase, new AddressHandler(), props1);
        
// RZ can this happen ???             
                if (req1.getLocationPropertiesRequested().isRetrievePostalAddress() &&
                    req1.getLocationPropertiesRequested().isRetrieveServiceAddress())
                {               
                    exBldReslt = 
                            ExceptionBuilder.parseException(
                                limBase.getCallerContext(),
                                ruleFile,
                                "",
                                LIMTag.CSV_EditError,
                                "Specifying both Postal Address and Service " +
                                 "Address properties in the same request is " +
                                  "prohibited",
                                true,
                                ExceptionBuilderRule.NO_DEFAULT,
                                null,
                                null,
                                limBase.myLogger.getBisLogger(),
                                "LIM",
                                Severity.UnRecoverable,
                                null);
                }

                if (req1.getLocationPropertiesRequested().isRetrievePostalAddress() &&
                    req1.getLocationPropertiesRequested().isRetrieveE911Address())
                {               
                    exBldReslt = 
                            ExceptionBuilder.parseException(
                                limBase.getCallerContext(),
                                ruleFile,
                                "",
                                LIMTag.CSV_EditError,
                                "Specifying both Postal Address and E911 " +
                                 "Address properties in the same request is " +
                                  "prohibited",
                                true,
                                ExceptionBuilderRule.NO_DEFAULT,
                                null,
                                null,
                                limBase.myLogger.getBisLogger(),
                                "LIM",
                                Severity.UnRecoverable,
                                null);
                }

                if (req1.getLocationPropertiesRequested().isRetrieveServiceAddress() &&
                    req1.getLocationPropertiesRequested().isRetrieveE911Address())
                {               
                    exBldReslt = 
                            ExceptionBuilder.parseException(
                                limBase.getCallerContext(),
                                ruleFile,
                                "",
                                LIMTag.CSV_EditError,
                                "Specifying both Service Address and E911 " +
                                 "Address properties in the same request is " +
                                  "prohibited",
                                true,
                                ExceptionBuilderRule.NO_DEFAULT,
                                null,
                                null,
                                limBase.myLogger.getBisLogger(),
                                "LIM",
                                Severity.UnRecoverable,
                                null);
                }
        }
        else
        	req1 = new RetrieveLocReq(this.limBase, new AddressHandler(), null);
    } 
      
    /**	
     * Constract addressKey to search the corresponding Blob.
     * @return String
     * @param reqAddr Address
     */
    protected String getAddressKey (Address reqAddr)
    {
    	AddressHandler ah = null;
    	try
    	{
    		ah = new AddressHandler (reqAddr);
    	}
    	catch (AddressHandlerException ahe)
    	{
    		return null; // this should not happen
    	}
    	
    	// Constract a key for search in the database.
    	//
    	StringBuffer addrKey = new StringBuffer ("");
    	
    	if (ah.getRoute() != null)
    		addrKey.append (ah.getRoute());
    	addrKey.append ("|");
    	if (ah.getBox() != null)
    		addrKey.append (ah.getBox());
    	addrKey.append ("|");
    	if (ah.getHousNbrPfx() != null)
    		addrKey.append (ah.getHousNbrPfx());
    	addrKey.append ("|"); 
    	if (ah.getHousNbr() != null)
    		addrKey.append (ah.getHousNbr());
    	addrKey.append ("|");
    	if (ah.getAasgndHousNbr() != null)
    		addrKey.append (ah.getAasgndHousNbr());
    	addrKey.append ("|");
    	if (ah.getHousNbrSfx() != null)
    		addrKey.append (ah.getHousNbrSfx());
    	addrKey.append ("|");
    	if (ah.getStDir() != null)
    		addrKey.append (ah.getStDir());
    	addrKey.append ("|");
    	if (ah.getStName() != null)
    		addrKey.append (ah.getStName());
    	addrKey.append ("|");
    	if (ah.getStThorofare() != null)
    		addrKey.append (ah.getStThorofare());
    	addrKey.append ("|");
    	if (ah.getStNameSfx() != null)
    		addrKey.append (ah.getStNameSfx());
    	addrKey.append ("|");
    	if (ah.getCity() != null)
    		addrKey.append (ah.getCity());
    	addrKey.append ("|");	
    	addrKey.append ("|"); // for state
    	if (ah.getPostalCode() != null)
    		addrKey.append (ah.getPostalCode());
    	addrKey.append ("|");
    	if (ah.getPostalCodePlus4() != null)
    		addrKey.append (ah.getPostalCodePlus4());
    	addrKey.append ("|");

    	String st = ah.getStructType();
    	if (st != null)
    		addrKey.append (st);
    	addrKey.append ("|");
    	String sv = ah.getStructValue();
    	if (sv != null)
    		addrKey.append (sv);
    	addrKey.append ("+");
    	String lt = ah.getLevelType();
    	if (lt != null)
    		addrKey.append (lt);
    	addrKey.append ("|");
    	String lv = ah.getLevelValue();
    	if (lv != null)
    		addrKey.append (lv);
    	addrKey.append ("+");
    	String ut = ah.getUnitType();
    	if (ut != null)
    		addrKey.append (ut);
    	addrKey.append ("|");
    	String uv = ah.getUnitValue();
    	if (uv != null)
    		addrKey.append (uv);
    	addrKey.append ("+");
    
    	return addrKey.toString ();	
    }

	private OVALSNSP getOvalsNSP(Company company) throws InvalidStateException, NullDataException, InvalidCompanyException {
		if (m_OvalsNsp == null)
		{
			m_OvalsNsp = new OVALSNSP(company, limBase, limBase.getPROPERTIES());
		}

		return m_OvalsNsp;
	}
}