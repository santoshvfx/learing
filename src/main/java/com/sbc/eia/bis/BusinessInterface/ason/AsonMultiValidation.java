// $Id: AsonMultiValidation.java,v 1.4 2008/02/29 23:20:58 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ason;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq;


/**
 * AsonMultiValidation class.
 * Creation date: (9/20/01 7:58:55 AM)
 * @author: David Brawley
 */
public class AsonMultiValidation extends RetrieveLocResp {
	protected AsonHelper asonHelper = null;
	protected AsonRetrieveLocReq req = null;
	protected List sagIdList  	= new ArrayList();
	protected boolean compoundStNameSfx;
	protected boolean descriptiveAddr = false;
	protected boolean communityRequired;
    /**
     * Constructor for AsonMultiValidation.
     * @param utility LIMBase
     * @param addr     an Address object
     */
    public AsonMultiValidation(LIMBase utility, Address addr) {
    	super(utility);
    	asonHelper = new AsonHelper();
    
    	try{
    		if (addr != null)
    			asonHelper.address = new AddressHandlerASON(addr);
    		else
    			asonHelper.address = new AddressHandlerASON();
    	}
    
    	catch(AddressHandlerException e){}
    }
    /**
     * Getter for AsonRetrieveLocReq of AsonMultiValidation class.
     * Creation date: (5/6/02 7:46:46 AM)
     * @return AsonRetrieveLocReq
     * @see #setReq(AsonRetrieveLocReq)
     */
    public AsonRetrieveLocReq getReq() {
    	return req;
    }
    /**
     * Non-public getter for the internal List.
     * Creation date: (3/16/01 10:04:02 AM)
     * @return List
     * @see #setSagIdList(String)
     */
    List getSagIdList()
    {
    	return sagIdList;
    }
    /**
     * Getter method for the SAG Validation Request.
     * Creation date: (3/20/01 5:00:10 PM)
     * @return ASONSagValidReq
     */
    public ASONSagValidReq getSagValidReq()
    {
    	String community = ASONTag.EMPTY_STRING;
    	String addressName = ASONTag.EMPTY_STRING;
    	
    	if (isCommunityRequired())
    		community = (asonHelper.address.getCity().length() > ASONTag.SAG_VAL_COM_MAX) ?				
    				asonHelper.address.getCity().substring(0, ASONTag.SAG_VAL_COM_MAX).toUpperCase() :
    				asonHelper.address.getCity().toUpperCase();
    
    	if (isCompoundStNameSfx())
    		addressName = asonHelper.address.getStreetAddr(true).toUpperCase();
    	else
    		addressName = asonHelper.address.getStreetAddr(false).toUpperCase();
    	
    	if ((asonHelper.address.getHousNbr().trim()).equalsIgnoreCase(""))
    		addressName = "@ " + addressName;
    
    	Date today = new java.util.Date();
    	SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd.HHmmss.SSS" ) ;
    	String dateString = formatter.format( today ) ;
    	String sendingDate = dateString.substring(2,8);
    	String sendingTime = dateString.substring(9,15) + dateString.substring(16,18);
    	
    	return ( new ASONSagValidReq(
    		ASONTag.REQUEST_CODE_4441,							//requestCode
    		ASONTag.SAG_VALID_REQ_LNGTH,						//msgLength
    		ASONTag.ID_GROUP,									//idGroup
    		ASONTag.ID_TYPIST,									//idTypist
    		ASONTag.ID_TERMINAL,								//idSystem
    		sendingDate,										//requestDate
    		sendingTime,										//requestTime
    		asonHelper.address.getState().toUpperCase(),		//regionalAreaId
    		(addressName.length() > ASONTag.SAG_VAL_ST_NM_MAX) ?				
    			addressName.substring(0, ASONTag.SAG_VAL_ST_NM_MAX) :
    			addressName,
    		community,											//community
    		asonHelper.address.getPostalCode().toUpperCase(),	//zipCode
    		'N'));												//descriptiveAddrInd
    }
    /**
     * Getter for boolean communityRequired.
     * Creation date: (6/14/02 10:38:22 AM)
     * @return boolean
     */
    public boolean isCommunityRequired() {
    	return communityRequired;
    }
    /**
     * Getter for boolean compoundStNameSfx.
     * Creation date: (9/20/01 8:07:11 AM)
     * @return boolean
     */
    public boolean isCompoundStNameSfx() {
    	return compoundStNameSfx;
    }
    /**
     * Getter for boolean descriptiveAddr.
     * Creation date: (5/6/02 5:53:14 AM)
     * @return boolean
     */
    public boolean isDescriptiveAddr() {
    	return descriptiveAddr;
    }
    /**
     * Setter for boolean communityRequired.
     * Creation date: (6/14/02 10:38:22 AM)
     * @param newCommunityRequired boolean
     */
    public void setCommunityRequired(boolean newCommunityRequired) {
    	communityRequired = newCommunityRequired;
    }
    /**
     * Setter method for boolean compundStNameSfx.
     * Creation date: (9/20/01 8:07:11 AM)
     * @param newCompoundStNameSfx boolean
     */
    public void setCompoundStNameSfx(boolean newCompoundStNameSfx) {
    	compoundStNameSfx = newCompoundStNameSfx;
    }
    /**
     * Setter method for boolean descriptiveAddr.
     * Creation date: (5/6/02 5:53:14 AM)
     * @param newDescriptiveAddr boolean
     */
    public void setDescriptiveAddr(boolean newDescriptiveAddr) {
    	descriptiveAddr = newDescriptiveAddr;
    }
    /**
     * Setter for AsonRetrieveLocReq of AsonMultiValidation class.
     * Creation date: (5/6/02 7:46:46 AM)
     * @param newReq AsonRetrieveLocReq
     * @see #getReq
     */
    public void setReq(AsonRetrieveLocReq newReq) {
    	req = newReq;
    }
    /**
     * Method to instantiate the sagIdList from the SAI alternates from ASONSagValidErr.
     * Creation date: (3/6/01 4:35:51 PM)
     * @param sagErr  an ASONSagValidErr object
     */
    public void setSagIdList(ASONSagValidErr sagErr)
    {
    	if ((String.valueOf(sagErr.comReplyHdr3.saiPrimary).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiPrimary).trim());
    
    	if ((String.valueOf(sagErr.comReplyHdr3.saiAlt1).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiAlt1).trim());
    			
    	if ((String.valueOf(sagErr.comReplyHdr3.saiAlt2).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiAlt2).trim());
    			
    	if ((String.valueOf(sagErr.comReplyHdr3.saiAlt3).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiAlt3).trim());
    }
}
