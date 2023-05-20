//$Id: AsonRetrieveLocReq.java,v 1.6 2007/10/07 22:10:23 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.gwsvcs.service.asonservice.ASONServiceTest;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqReq;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq;

/**
 * This class extends RetrieveLocReq to specialize it for ASON.
 * Creation date: (7/17/07)
 * @author: Jean Duka
 */
public class AsonRetrieveLocReq extends RetrieveLocReq
{
	protected AddressHandlerASON asonAddr = null;
	protected String sagId = "";
	protected boolean serviceReq = false;
    protected boolean exactMatchReq = false;

    /**
     * ASONRetrieveLocReq constructor.
     * @param utility LIMBase
     * @param address AddressHandlerASON
     * @param aLocationPropertiesToGet LocationPropertiesToGetSeqOpt
     * @param aPreviousOwnerName StringOpt
     */
    public AsonRetrieveLocReq(
        LIMBase utility,
        AddressHandlerASON aAddress,
        String[] aLocationPropertiesToGet,
        String aPreviousOwnerName)
    {
    	super(utility, aAddress, aLocationPropertiesToGet, aPreviousOwnerName);
    	setAsonAddr(aAddress);
    }
    
    
    /**
     * ASONRetrieveLocReq constructor.
     * @param utility LIMBase
     * @param address AddressHandlerASON
     */
    public AsonRetrieveLocReq(
        LIMBase utility, 
        AddressHandlerASON aAddress, 
        String[] aLocPropertiesToGet)
    {
        super(utility, aAddress, aLocPropertiesToGet);
        setAsonAddr(aAddress);
    }
    
    /**
     * ASONRetrieveLocReq constructor.
     * @param utility LIMBase
     * @param address AddressHandlerASON
     */
//    public AsonRetrieveLocReq(
//        LIMBase utility, 
//        AddressHandlerASON aAddress, 
//        ProviderLocationProperties[] aPropertiesToGet)
//    {
//        super(utility, aAddress, aPropertiesToGet);
//        setAsonAddr(aAddress);
//    }
    /**
     * Get AddressHandler for request.
     * Creation date: (7/23/01 11:39:09 AM)
     * @return AddressHandlerASON
     * @see #setAsonAddr(String)
     */
    protected AddressHandlerASON getAsonAddr() 
    {
    	return asonAddr;
    }
    /**
     * Getter method for ASON SAG1 Inquiry Request.
     * Creation date: (3/20/01 5:00:10 PM)
     * @param maxZip  a String
     * @return resp ASONSagInqReq
     */
    public ASONSagInqReq getAsonSagInqReq(String maxZip)
    {
    	String addressName = "";
    	
    	if (asonAddr.getAddAddrInfo().length() > 0)
    		addressName = asonAddr.getAddAddrInfo().toUpperCase();
    	else
    		addressName = asonAddr.getStreetName().toUpperCase();
    	
    	return (new ASONSagInqReq(	
    					AsonHelper.getTagInfo(),					//tagInformation
    					ASONTag.REQUEST_CODE_7300,					//requestCode
    					AsonHelper.getCmndLine(),					//commandLine
    					ASONTag.DATE_KEY,							//dateKey
    					ASONTag.FUNCTION_KEY_DEPRESSED,				//functionKeyDepressed
    					ASONTag.ID_GROUP,							//idGroup
    					ASONTag.ID_TERMINAL,						//idTerminal
    					ASONTag.ID_TYPIST,							//idTypist
    					asonAddr.getState().toUpperCase(),			//regionalAreaId
    					ASONTag.TIME_KEY,							//timeKey
    					getSagId().toUpperCase(),					//sagAreaId
    					asonAddr.getStreetDir().toUpperCase(),		//sagDirectional
    																//addressName
    					(addressName.length() > ASONTag.SAG_INQ_ST_NM_MAX) ?				
    		    			addressName.substring(0, ASONTag.SAG_INQ_ST_NM_MAX) :
    		    			addressName,
    					ASONTag.EMPTY_STRING,						//zipCode
    					ASONTag.EMPTY_STRING,						//savedSagKey
    					ASONTag.EMPTY_STRING,						//savedSagScreenInd
    					ASONTag.EMPTY_STRING,						//exactPositioningInd
    					ASONTag.SENT_END_STRING,					//sentEndString
    					maxZip));									//maximumAddresses
    
    }
    /**
     * handleAsonSag1Resp will process the screen vector, for each screen
     * the lines will be parsed and added to the address range list.
     * Creation date: (3/27/01 12:01:14 PM)
     * @param sagScrns  a Vector object
     * @return a String
     */
    protected String getPostalCode(Vector sagScrns)
    {
    	ASONSagInqResp resp = null;
    	String postalCode = "";
    	
    	for (int i = 0; i < sagScrns.size(); i++)
    	{
    		resp = (ASONSagInqResp) sagScrns.get(i);
    		getUtility().log(LogEventId.DEBUG_LEVEL_2, ASONServiceTest.display(resp));
    		// set default
    		if (i==0)
    			postalCode = resp.sagByPassArea[0].bpZipCode.trim();
    		
    		// parse sagByPassArea Array
    		for (int j = 0; j < resp.sagByPassArea.length; j++)
            {
    			// if community populated, valid address
    			if ((resp.sagByPassArea[j].bpCommunity != null) &&
    				((resp.sagByPassArea[j].bpCommunity.trim()).length() > 0))
                {
    				if ((resp.sagByPassArea[j].bpCommunity.trim()).equalsIgnoreCase(asonAddr.getCity()))
                    {
    					return (resp.sagByPassArea[j].bpZipCode.trim());
    				}
    			}	
    		} // end for j
    	} // end for i
    	return (postalCode);
    }
    /**
     * Getter for SagId of AsonRetrieveLocReq class.
     * Creation date: (7/24/01 10:15:57 AM)
     * @return String
     * @see #setSagId
     */
    public String getSagId() 
    {
    	return sagId;
    }
    /**
     * Getter method for the SAG Validation Request.
     * Creation date: (3/20/01 5:00:10 PM)
     * @param astericStNmSfx  a boolean
     * @return ASONSagValidReq
     */
    public ASONSagValidReq getSagValidReq()
    {
    	String addressName = "";
    	char descriptiveAddrInd = 'N';
    
    	if (asonAddr.getAddAddrInfo().length() > 0)
        {
    		addressName = asonAddr.getAddAddrInfo().toUpperCase();
    		descriptiveAddrInd = 'Y';
    	}
    	else
        {
    		addressName = asonAddr.getStreetAddr(false).toUpperCase();
    		if ((asonAddr.getHousNbr().trim()).equalsIgnoreCase(""))
    			addressName = "@ " + addressName;
    	}
    
    	Date today = new java.util.Date();
    	SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd.HHmmss.SSS" ) ;
    	String dateString = formatter.format( today ) ;
    	String sendingDate = dateString.substring(2,8);
    	String sendingTime = dateString.substring(9,15) + dateString.substring(16,18);
    	
    	return ( new ASONSagValidReq(
    		ASONTag.REQUEST_CODE_4441,						//requestCode
    		ASONTag.SAG_VALID_REQ_LNGTH,					//msgLength
    		ASONTag.ID_GROUP,								//idGroup
    		ASONTag.ID_TYPIST,								//idTypist
    		ASONTag.ID_TERMINAL,							//idSystem
    		sendingDate,									//requestDate
    		sendingTime,									//requestTime
    		asonAddr.getState().toUpperCase(),				//regionalAreaId
    														//addressName
    		(addressName.length() > ASONTag.SAG_VAL_ST_NM_MAX) ?				
    			addressName.substring(0, ASONTag.SAG_VAL_ST_NM_MAX) :
    			addressName,
    		ASONTag.EMPTY_STRING, 							//community
    		asonAddr.getPostalCode().toUpperCase(),			//zipCode
    		descriptiveAddrInd));							//descriptiveAddrInd
    
    }
    /**
     * Gets the boolean indicating whether this
     * request is for a service.
     * Creation date: (3/26/02 7:52:09 AM)
     * @return boolean
     */
    public boolean isServiceReq() 
    {
    	return serviceReq;
    }
    /**
     * Setter for AddressHandlerAson of AsonRetrieveLocReq class.
     * Creation date: (7/23/01 11:39:09 AM)
     * @param newAsonAddr AddressHandlerASON
     * @see #getAsonAddr
     */
    private void setAsonAddr(AddressHandlerASON newAsonAddr) 
    {
    	asonAddr = newAsonAddr;
    }

    /**
     * Setter for SagId of AsonRetrieveLocReq class.
     * Creation date: (7/24/01 10:15:57 AM)
     * @param newSagId String
     * @see #getSagId
     */
    public void setSagId(String newSagId) 
    {
    	sagId = newSagId;
    }

    /**
     * Sets the boolean indicating whether this
     * request is for a service.
     * Creation date: (3/26/02 7:52:09 AM)
     * @param newServiceReq boolean
     */
    public void setServiceReq(boolean newServiceReq) 
    {
    	serviceReq = newServiceReq;
    }

   	/**
   	 * Returns the exactMatchReq.
   	 * @return boolean
   	 */
   	public boolean isExactMatchReq() 
    {
   		return exactMatchReq;
   	}
    
   	/**
   	 * Sets the exactMatchReq.
   	 * @param exactMatchReq The exactMatchReq to set
   	 */
   	public void setExactMatchReq(boolean exactMatchReq) 
    {
   		this.exactMatchReq = exactMatchReq;
   	}
}
