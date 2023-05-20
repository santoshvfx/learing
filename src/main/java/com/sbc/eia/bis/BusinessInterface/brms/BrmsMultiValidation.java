// $Id: BrmsMultiValidation.java,v 1.5 2008/02/29 23:22:18 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.brms;

import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.bis.lim.database.queries.BRMSSagInfo;


/**
 * BRMSMultiValidation class.
 * Creation date: (9/20/01 7:58:55 AM)
 * @author: David Brawley
 */
public class BrmsMultiValidation extends RetrieveLocResp {
	protected String wireCenter = "";
	protected BRMSSagInfo [] brmsSagInfo = null; 
	protected AddressHandlerBRMS brmsReq = null;
	protected AddressHandlerBRMS brmsResp = null;

    /**
     * Constructor for BRMSMultiValidation.
     * @param utility LIMBase
     */
    public BrmsMultiValidation(LIMBase utility) {
    	super(utility);
    	setBrmsReq(new AddressHandlerBRMS());
    	setBrmsResp(new AddressHandlerBRMS());
    }
    /**
     * Gets the AddressHandlerBRMS request object.
     * Creation date: (1/28/02 11:52:01 AM)
     * @return AddressHandlerBRMS
     * @see #setBrmsReq(AddressHandlerBRMS)
     */
    public AddressHandlerBRMS getBrmsReq() {
    	return brmsReq;
    }
    /**
     * Gets the AddressHandlerBRMS response object.
     * Creation date: (1/28/02 11:52:01 AM)
     * @return AddressHandlerBRMS
     * @see #setBrmsResp(AddressHandlerBRMS)
     */
    public AddressHandlerBRMS getBrmsResp() {
    	return brmsResp;
    }
   /**
     * Gets the concatenated street name, removing characters
     * defined by BRMSTag.ASTERISK and BRMSTag.SINGLE_QUOTE.
     * Creation date: (1/28/02 12:43:21 PM)
     * @return String
     */
    protected String getStNmCnctntd() {
    	
    	StringBuffer stNm = new StringBuffer(brmsReq.getStreetName());
    	int index = 0;
    	
    	while ((stNm.toString()).indexOf(BRMSTag.ASTERISK) >= 0){
    		index = (stNm.toString()).indexOf(BRMSTag.ASTERISK);
    		stNm.deleteCharAt(index);
    	}
    	
    	while ((stNm.toString()).indexOf(BRMSTag.SINGLE_QUOTE) >= 0){
    		index = (stNm.toString()).indexOf(BRMSTag.SINGLE_QUOTE);
    		stNm.deleteCharAt(index);
    	}
    
    	return stNm.toString();
    }
    /**
     * Gets the street name by replaceing characters defined by
     * BRMSTag.ASTERISK and BRMSTag.SINGLE_QUOTE with a space.
     * Creation date: (1/28/02 12:42:38 PM)
     * @return String
     */
    protected String getStNmSpace() {
    	
    	StringBuffer stNm = new StringBuffer(brmsReq.getStreetName());
    	int index = 0;
    	
    	while ((stNm.toString()).indexOf(BRMSTag.ASTERISK) >= 0){
    		index = (stNm.toString()).indexOf(BRMSTag.ASTERISK);
    		stNm.setCharAt(index, ' ');
    	}
    	
    	while ((stNm.toString()).indexOf(BRMSTag.SINGLE_QUOTE) >= 0){
    		index = (stNm.toString()).indexOf(BRMSTag.SINGLE_QUOTE);
    		stNm.setCharAt(index, ' ');
    	}
    
    	return stNm.toString();
    
    }
 
    /**
     * Sets the AddressHandlerBRMS request object.
     * Creation date: (1/28/02 11:52:01 AM)
     * @param newBrmsReq  an AddressHandlerBRMS object
     * @see #getBrmsReq
     */
    public void setBrmsReq(AddressHandlerBRMS newBrmsReq) {
    	brmsReq = newBrmsReq;
    }
    /**
     * Sets the AddressHandlerBRMS response object.
     * Creation date: (1/28/02 11:52:01 AM)
     * @param newBrmsResp  an AddressHandlerBRMS object
     * @see #getBrmsResp
     */
    public void setBrmsResp(AddressHandlerBRMS newBrmsResp) {
    	brmsResp = newBrmsResp;
    }
    /**
     * Sets the SAG type.
     * Creation date: (1/28/02 11:49:37 AM)
     * @param newSagType SAGType
     * @see #getSagType
     */
    public void setSagInfo (BRMSSagInfo [] newSagInfo) {
    	brmsSagInfo = newSagInfo;
    }
    /**
     * Gets the wire center.
     * Creation date: (1/28/02 11:49:03 AM)
     * @return String
     * @see #setWireCenter(String)
     */
    public java.lang.String getWireCenter() {
    	return wireCenter;
    }
    /**
     * Sets the wire center.
     * Creation date: (1/28/02 11:49:03 AM)
     * @param newWireCenter String
     * @see #getWireCenter
     */
    public void setWireCenter(java.lang.String newWireCenter) {
    	wireCenter = newWireCenter;
    }

}
