// $Id: AsonAddrSagValidResp.java,v 1.3 2004/04/16 20:03:01 db4252 Exp $

package com.sbc.eia.bis.BusinessInterface.ason;

import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReq;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp;

/**
 * This class represents an address-match from ASONService.
 * Creation date: (3/12/01 9:37:38 AM)
 * @author: David Brawley
 */
public class AsonAddrSagValidResp extends RetrieveLocResp
{
	protected AsonRetrieveLocReq req = null;
	protected ASONSagValidResp svr = null;
	protected AsonHelper asonHelper = null;

    /**
     * Construct this object from a ASON Sag Validation Response (S8A).
     * @param utility LIMBase
     * @param svrResp  an ASONSagValResp object
     * @param locReq   an AsonRetrieveLocReq object
     * @param addr     an AddressHandlerASON object
     * @exception SystemFailure
     */
    public AsonAddrSagValidResp(LIMBase utility, ASONSagValidResp svrResp, AsonRetrieveLocReq locReq, AddressHandlerASON addr)
    	throws SystemFailure
    {
    	super(utility);
    	svr = svrResp;
    	req = locReq;
    	asonHelper = new AsonHelper();
    	asonHelper.address = addr;
    }
    /**
     * Compares two objects for equality. Returns a boolean that indicates
     * whether this object is equivalent to the specified object. This method
     * is used when an object is stored in a hashtable.
     * @param obj the Object to compare with
     * @return true if these Objects are equal; false otherwise.
     * @see Hashtable
     */
    public boolean equals(Object obj)
    {
    	if (obj instanceof AsonHitResp)
    	{
    		return hashString().equals(((AsonHitResp) obj).hashString());
    	}
    	return false;
    }
    /**
     * Getter method for the ASONLvngUnitValReq member.
     * Creation date: (3/20/01 5:00:10 PM)
     * @return ASONLvngUnitValReq
     */
    public ASONLvngUnitValReq getAsonLuvReq()
    {
    	String houseNbr   = "";
    	String assgndHousNbrInd = ((asonHelper.address.getAasgndHousNbr()).length() > 0) ? "Y" : "";
    	
    	if (assgndHousNbrInd.equalsIgnoreCase("Y"))
    		houseNbr = asonHelper.address.getAasgndHousNbr().toUpperCase();
    	else
    		houseNbr = asonHelper.address.getHouseNumber().toUpperCase();
    	
    	return( new ASONLvngUnitValReq(
    		asonHelper.getTagInfo(),							//tagInformation
    		ASONTag.REQUEST_CODE_5215,							//requestCode
    		asonHelper.getCmndLine(),							//commandLine
    		ASONTag.DATE_KEY,									//dateKey
    		ASONTag.FUNCTION_KEY_DEPRESSED,						//functionKeyDepressed
    		ASONTag.ID_GROUP,									//idGroup
    		ASONTag.ID_TERMINAL,								//idTerminal
    		ASONTag.ID_TYPIST,									//idTypist
    		svr.raiCode,										//regionalAreaId
    		ASONTag.TIME_KEY,									//timeKey
    		svr.raiCode,										//raiCode
    		String.valueOf(svr.sagAreaId),						//SagAreaId
    		svr.sagWireCenter,									//wireCenter
    		svr.communitySag,									//communityName
    		asonHelper.address.getStreetDir().toUpperCase(),	//streetDirection
    		svr.addressNameSag.trim(),							//addressName
    		assgndHousNbrInd.toUpperCase(),						//assignedHouseNumberInd
    		houseNbr,											//stNbrFld1
    		asonHelper.address.getHousNbrSfx().toUpperCase(),	//stNbrFld2
    		req.getAddr().getStructValue().toUpperCase(),		//loc1
    		req.getAddr().getLevelValue().toUpperCase(),		//loc2
    		req.getAddr().getUnitValue().toUpperCase(),			//loc3
    		ASONTag.EMPTY_STRING,								//loc4
    		ASONTag.EMPTY_STRING,								//loc5
    		req.getAddr().getStructType().toUpperCase(),		//locTag1
    		req.getAddr().getLevelType().toUpperCase(),			//locTag2
    		req.getAddr().getUnitType().toUpperCase(),			//locTag3
    		ASONTag.EMPTY_STRING,								//locTag4
    		ASONTag.EMPTY_STRING,								//locTag5
    		svr.zipCodeSag,										//zipCode
    		ASONTag.SENT_END_STRING));							//sendEndString
    }
    /**
     * Getter for AsonRetrieveLocReq of AsonAddrRangeResp class.
     * Creation date: (9/17/01 7:17:37 AM)
     * @return AsonRetrieveLocReq
     * @see #setReq(AsonRetrieveLocReq)
     */
    public AsonRetrieveLocReq getReq() {
    	return req;
    }
    /**
     * Getter method for ASONSagValidResp member.
     * Creation date: (3/15/01 3:29:22 PM)
     * @return ASONSagValidResp
     */
    public ASONSagValidResp getSagValidResp()
    {
    	return svr;
    }
    /**
     * Generates a hash code for the receiver.
     * This method is supported primarily for
     * hash tables, such as those provided in java.util.
     * @return an integer hash code for the receiver
     * @see Hashtable
     */
    public int hashCode()
    {
    	return hashString().hashCode();
    }
    /**
     * Return a String to be used by methods equals() and hashCode().
     * Creation date: (3/13/01 3:11:37 PM)
     * @return String
     */
    protected String hashString()
    {
    
    	return (
    		svr.comReplyHdr1.ReplyCode +
    		svr.comReplyHdr1.MsgLength +
    		svr.comReplyHdr1.TmfAction +
    		svr.comReplyHdr1.RequestDateYYYYMMDD +
    		svr.comReplyHdr1.RequestTimeHHMMSSCC +
    		svr.comReplyHdr1.ReplyDateYYYYMMDD +
    		svr.comReplyHdr1.ReplyTimeHHMMSSCC +
    		svr.comReplyHdr1.MsgCode +
    		svr.comReplyHdr1.MsgDelimiter +
    		svr.comReplyHdr1.MsgText +
    		svr.comReplyHdr2.addressName +
    		svr.comReplyHdr2.community +
    		svr.comReplyHdr2.zipCode +
    		svr.comReplyHdr2.descriptiveAddrInd +
    		svr.raiCode +
    		svr.sagAreaId +
    		svr.addressNameSag +
    		svr.alphaNumInd +
    		svr.alternateAddressInd +
    		svr.analogOrDigitalType +
    		svr.busRateBand +
    		svr.centralOffice +
    		svr.cityAbbreviation +
    		svr.communitySag +
    		svr.countyAbbrev +
    		svr.directional +
    		svr.e911Exempt +
    		svr.e911Nrg +
    		svr.e911Sur +
    		svr.needBillAddrInd +
    		svr.editAgainstLufFile +
    		svr.needLocLevel1Ind +
    		svr.needLocLevel2Ind +
    		svr.needLocLevel3Ind +
    		svr.needCommNameInd +
    		svr.secondLineInd +
    		svr.needBillAddrInd +
    		svr.equipmentType +
    		svr.exchange +
    		svr.highAddrRange +
    		svr.lastAssignedHouseNumUsed +
    		svr.lfacsDupAddressInd +
    		svr.lowAddrRange +
    		svr.map +
    		svr.matchInd +
    		svr.municipality +
    		svr.oddEvenIndicator +
    		svr.rateBand +
    		svr.rateZone +
    		svr.state +
    		svr.tar +
    		svr.sagWireCenter +
    		svr.zipCodeSag
    		);
    }
    /**
     * Setter for AsonRetrieveLocReq of AsonAddrSagValResp class.
     * Creation date: (9/17/01 7:17:37 AM)
     * @param newReq AsonRetrieveLocReq
     * @see #getReq
     */
    public void setReq(AsonRetrieveLocReq newReq) {
    	req = newReq;
    }
}
