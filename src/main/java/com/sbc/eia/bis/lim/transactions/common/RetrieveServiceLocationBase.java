//$Id: RetrieveServiceLocationBase.java,v 1.5 2007/10/11 21:41:41 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.ServiceLocator;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.service.hostlookup.HOSTLOOKUPHelper;
import com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R;
import com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTN;

/**
 * A superclass containing common code for the two RetrieveLocationForServiceAddressTrans and
 * RetrieveLocationForTelephoneNumberTrans classes
 * Creation date: (7/20/07)
 * @author: jh9785
 */

public class RetrieveServiceLocationBase 
{
    public static final String HOSTLOOKUP = "HOSTLOOKUP";
    public static final String GET_HOST_LOOKUP_RECORD = "RetrieveLocationBase::getHostLookupRecord()";
    public static final String HL_LOOKUP_FULL = "HOSTLOOKUPHelper::hlLookupFull()";
    
    public LIMBase limBase = null;
    private String saga = "";
	private HOSTLOOKUPHelper hostLookupHelper = null;
	private HostLookupFull_R hostLookupRecord = null;
	protected static String hostLookupSvcNm = "";
	protected boolean setCache = false;
	protected boolean retrieveCache = false;
	protected ExceptionBuilderResult exBldReslt = null;
	protected String ruleFile = null;
    protected ServiceLocator m_serviceLocator = null;
	private Company company = null;
	
    /**
     * Constructor for RetrieveServiceLocationBase.
     * @param lim_base LIMBase
     */
    public RetrieveServiceLocationBase(LIMBase lim_base)
    {
        limBase = lim_base;
        ruleFile = (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
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
     * Return caching value m_serviceLocator
     * @return m_serviceLocator ServiceLocator
     */
    protected ServiceLocator getServiceLocator()
    {
        if (m_serviceLocator == null)
        {
            m_serviceLocator = new ServiceLocator(limBase);
        }
        return m_serviceLocator;
    }
    
    /**
     * Determine if setCache and retrieveCache should be done.
     * @param applicationName String
     */
    protected void checkCacheExcept(String applicationName)
    {
        /* Check lim.properties: ACCESS_CACHE_ADDRESS_EXCEPT=OBF
         * List may be stored in limbase once.
         */
        setCache = true;
    	retrieveCache = true;
    
    	if (((Properties)(limBase.getPROPERTIES())).getProperty("ACCESS_CACHE_ADDRESS_EXCEPT") != null)
    	{
    		String exceptCacheList = ((Properties)(limBase.getPROPERTIES())).getProperty("ACCESS_CACHE_ADDRESS_EXCEPT").trim();
    		if (exceptCacheList != null && exceptCacheList.length() > 0)
    		{
    			String bisApp = applicationName;
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
    	    limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveServiceLocationBase::getHostLookupRecord()|RetrieveLocationBase::setHostLookupRecord()|PRE");
    
    	    limBase.log(LogEventId.REMOTE_CALL, HOSTLOOKUP, getHostLookupSvcNm(), GET_HOST_LOOKUP_RECORD, HL_LOOKUP_FULL);
//    	    setHostLookupRecord( (HostLookupFull_R) getHostLookupHelper().
//        			hlLookupFull(null,null,ServiceHelper.USE_DEFAULT_TIMEOUT,new HostLookupTN(service.toString())).
//        			getTheObject() );
    		setHostLookupRecord( (HostLookupFull_R) getHostLookupHelper().
    			hlLookupFull(null,null,ServiceHelper.USE_DEFAULT_TIMEOUT,new HostLookupTN(service.toString())).
    			getTheObject() );
    		
    		limBase.log(LogEventId.REMOTE_RETURN, HOSTLOOKUP, getHostLookupSvcNm(), GET_HOST_LOOKUP_RECORD, HL_LOOKUP_FULL);
    			
    		limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveServiceLocationBase::getHostLookupRecord()|RetrieveLocationBase::setHostLookupRecord()|POST");
    	}
    	return hostLookupRecord;
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
     * Setter for company of RetrieveServiceLocationBase class.
     * @param newCompany Company
     * @see #getCompany
     */
    public void setCompany(Company newCompany)
    {
        company = newCompany;
    }
    
    /**
     * Getter method for Company, empty or not.
     * @return Company
     * @see #setCompany(Company)
     */
    public Company getCompany()
    {
    	return company;
    }
}
