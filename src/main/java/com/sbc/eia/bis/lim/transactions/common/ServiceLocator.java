//$Id: ServiceLocator.java,v 1.9 2007/10/17 19:06:13 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.BusinessException;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.CompanyFactory;
import com.sbc.eia.bis.BusinessInterface.ServiceCenter;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason.ASON;
import com.sbc.eia.bis.BusinessInterface.ServiceAddress.brms.BRMS;
import com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis.PREMIS;
import com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag.RSAG;
import com.sbc.eia.bis.BusinessInterface.location.ServiceAddressLocationI;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForServiceAddress.RetrieveLocationForServiceAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForTelephoneNumber.RetrieveLocationForTelephoneNumberTrans;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.Severity;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;

/**
 * A helper class to retrieve proper ServiceAddressLocationI
 * for directing to differet backend system
 */
public class ServiceLocator
{
    public LIMBase loc_limBase = null;
    ExceptionBuilderResult exBldReslt = null;
	String ruleFile =null;
	protected CompanyFactory companyFactory = null;
	protected String[] hostList = new String[] { 
            								(ASON.class).getName(), 
            								(BRMS.class).getName(), 
            								(PREMIS.class).getName(),
            								(RSAG.class).getName()
            								};
	/**
     * Constructor for ServiceLocator.
     * @param limBase LIMBase
     */
    public ServiceLocator(LIMBase limBase)
    {
        super();
        
        loc_limBase = limBase;
        ruleFile = (String) loc_limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
    }
    
    /**
     * Use the BusinessInterface framework to get an object that implements the LocationI interface.
     * The caller can pass either address or service, and the other null.
     * Creation date: (7/20/07)
     * @return ServiceAddressLocationI
     * @param astateCode String
     * @param serviceCenter String
     * @param passThru RetrieveLocationForServiceAddressTrans
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public ServiceAddressLocationI getServiceLocationI(String astateCode, String serviceCenter, RetrieveLocationForServiceAddressTrans passThru)
    	throws 
    		InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		DataNotFound, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound
    {
        ServiceAddressLocationI serviceLocationI = null;
        Company co = null;
        StringBuffer logText = new StringBuffer("Mapped ");
        String loc_Saga = "";
        
        loc_Saga = passThru.getSaga();
        
        loc_limBase.log(LogEventId.AUDIT_TRAIL, "ServiceLocator::getServiceLocationI()|Company::getBusinessInterface()|PRE");
        
        try
    	{
            //Route request by Service Center.
            if (serviceCenter != null && serviceCenter.length() > 0 && loc_limBase.validateState(serviceCenter, loc_limBase.REGIONS_ALL))
            {
                logText.append("ServiceCenter [" + serviceCenter + "]");
   				co = getCompanyFactory().getCompany(new ServiceCenter(serviceCenter.toUpperCase()));
   				//used in CBS/RSAG
   				loc_limBase.setCurrentState(serviceCenter);
            }
   			else if (astateCode != null)
    		{
    			String stateCode = astateCode;
                //Route request by State
    			if (stateCode.length() > 0)
    			{
    				co = getCompanyFactory().getCompany(new State(stateCode));
    				logText.append("State [" + stateCode + "]");
    				//used in CBS/RSAG
    				loc_limBase.setCurrentState(stateCode);
    			}
    			else	// have to use saga
    			{
    				logText.append("Saga [" + loc_Saga + "]");	
    				if ((loc_Saga.length()) >= 2 && (loc_Saga.length()) <= 4) 	// PREMIS, SAGA is 2-4 characters
    					co = getCompanyFactory().getCompany(Company.Company_PacificBell);
    				else if (loc_Saga.length() == 1)	// ASON, SAGA is 1 characters
    					co = getCompanyFactory().getCompany(Company.Company_Ameritech);
    				else
    				    //Theoretically, it should not come into here:
    					//If Service Center is not in 22 states, it will use State.
    				    //If State is published and is not in 22 states, it will throw an exception in the validation.
    				    //If empty state, it will use zip code to check ZC table for state. If zip code is not found in ZC, an exception will be thrown.
    				    //If Saga zip code is found and state is empty, Saga zip code must satisfy the rules in validation for PREMIS and ASON.
    					exBldReslt =
    					    ExceptionBuilder.parseException(
    					            loc_limBase.getCallerContext(),
    					            ruleFile,
    					            "",
    					            LIMTag.CSV_EditError,
    					            "Failed on checking Service Center, State, and Saga. Can not decide which backend system to go",
    					            true,
    					            ExceptionBuilderRule.NO_DEFAULT,
    					            null,
    					            null,
    					            loc_limBase.myLogger.getBisLogger(),
    					            "LIM",
    					            Severity.UnRecoverable,
    					            null);
    			}
    		}
   			else  //should never happen, nulls errored before call to getlocatinI
    		{
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					null,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					null);
    		}
            
            logText.append(" to Company [" + co.getCode() + "]");
            loc_limBase.log(LogEventId.DEBUG_LEVEL_1,logText.toString());
            
            serviceLocationI = (ServiceAddressLocationI) co.getBusinessInterface(ServiceAddressLocationI.LOCATION_INTERFACE_NAME, passThru);
    	}
        catch (BusinessException be)
        {
            loc_limBase.handleBusinessException(be,serviceLocationI);
        }
        
        loc_limBase.log(LogEventId.AUDIT_TRAIL, "ServiceLocator::getServiceLocationI()|Company::getBusinessInterface()|POST");	
    	return serviceLocationI;
    }
  
    /**
     * Use the BusinessInterface framework to get an object that implements the ServiceAddressLocationI interface.
     * The caller can pass either address or service, and the other null.
     * @return ServiceAddressLocationI
     * @param service TN
     * @param serviceCenter String
     * @param passThru RetrieveLocationForTelephoneNumberTrans
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public ServiceAddressLocationI getServiceLocationI(TN service, RetrieveLocationForTelephoneNumberTrans passThru)
    throws InvalidData, 
    	   AccessDenied, 
    	   BusinessViolation, 
    	   DataNotFound, 
    	   SystemFailure, 
    	   NotImplemented, 
    	   ObjectNotFound
    {
    	ServiceAddressLocationI locationI = null;
    	Company co = null;
    	StringBuffer logText = new StringBuffer("Mapped ");
       	passThru.setHostLookupRecord(null);
    
       	String serviceCenter = (new BisContextManager (loc_limBase.getCallerContext())).getServiceCenter();
       	loc_limBase.log(LogEventId.AUDIT_TRAIL, "ServiceLocator::getServiceLocationI()|Company::getBusinessInterface()|PRE");
    	
       	try
    	{	            
       	    //Route request by Service Center.
       	    if (serviceCenter != null && serviceCenter.length() > 0 && loc_limBase.validateState(serviceCenter, loc_limBase.REGIONS_ALL))
    		{	
    			logText.append("ServiceCenter [" + serviceCenter + "]");
   				co = getCompanyFactory().getCompany(new ServiceCenter(serviceCenter.toUpperCase()));
   					
   				loc_limBase.setCurrentState(serviceCenter);
   			}
    		//Route request by TN service
       	    else
       	    { 
    			logText.append("TN [" + service.toString() + "]");
    			passThru.setSaga(passThru.getHostLookupRecord(service).saga);
    			co = getCompanyFactory().getCompany(passThru.getHostLookupRecord(service).location);
    		}
    
    		logText.append(" to Company [" + co.getCode() + "]");
    		loc_limBase.log(LogEventId.DEBUG_LEVEL_1,logText.toString());
    		passThru.setCompany(co);
    		locationI = (ServiceAddressLocationI) co.getBusinessInterface(ServiceAddressLocationI.LOCATION_INTERFACE_NAME,passThru);
    	}
    	catch (BusinessException be)
    	{
    		loc_limBase.handleBusinessException(be,locationI);
    	}
    	catch (ServiceException se)
    	{
    		Properties tagValues2 = new Properties();
    		tagValues2.setProperty("CLASS", Address.class.getName());
    		tagValues2.setProperty("DESCRIPTION", se.getMessage());
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					loc_limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_TnServiceError,
    					"Host Lookup Exception: Message[Number not found <" + service.toString() + ">]",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues2);
    	}
    
    	loc_limBase.log(LogEventId.AUDIT_TRAIL, "ServiceLocator::getServiceLocationI()|Company::getBusinessInterface()|POST");	
    	return locationI;
    }
    
    /**
     * Getter method for the CompanyFactory.
     * Creation date: (3/26/01 11:11:30 AM)
     * @return CompanyFactory
     * @throws BusinessException
     * @see #setCompanyFactory(CompanyFactory)
     */
    public CompanyFactory getCompanyFactory() throws BusinessException
    {
    	if (companyFactory == null)
    	{
    		setCompanyFactory(new CompanyFactory(hostList, loc_limBase, loc_limBase.getPROPERTIES()));
    	}
    	return companyFactory;
    }
    
    /**
     * Setter method for the CompanyFactory.
     * Creation date: (3/26/01 11:11:30 AM)
     * @param newCompanyFactory CompanyFactory
     * @see #getCompanyFactory
     */
    public void setCompanyFactory(CompanyFactory newCompanyFactory)
    {
    	companyFactory = newCompanyFactory;
    }
}
