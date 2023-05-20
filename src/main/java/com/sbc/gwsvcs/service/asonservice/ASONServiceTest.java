// $Id: ASONServiceTest.java,v 1.3 2002/10/17 17:23:42 rg3454 Exp $

package com.sbc.gwsvcs.service.asonservice;

import java.text.*;
import java.util.*;
import com.sbc.bccs.utilities.*;
import com.sbc.gwsvcs.service.asonservice.exceptions.*;
import com.sbc.gwsvcs.service.asonservice.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * This is the ASONService Test class.
 * Creation date: (4/26/01 12:32:12 PM)
 * @author: David Brawley
 */
public class ASONServiceTest implements Logger {
/**
 * Construct a asonserviceTest object.
 * Creation date: (4/26/01 12:33:23 PM)
 */
public ASONServiceTest() {
	super();
}
/**
 * Display ASONDueDateErr response.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateErr
 */
public static String display(ASONDueDateErr response) {
	
	StringBuffer outptStr = new StringBuffer("ASONDueDateErr = ");

	outptStr.append(response.replyCode + "|" +
					response.advisoryMsg + "|" +
					response.cmdName + "|" +
					response.codeDisplay + "|" +
					response.indInvInput + "|" +
					response.indSystemStatus + "|");

	return new String(outptStr.toString());
}
/**
 * Display ASONDueDateResp.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp
 */
public static String display(ASONDueDateResp response) {

	StringBuffer outptStr = new StringBuffer("ASONDueDateResp = ");

	outptStr.append(response.replyCode + "|");
	for (int i = 0; i < response.openDates.length; i++)
	{
		outptStr.append(response.openDates[i].weekDay1 + "|" +
			response.openDates[i].mmDD1 + "|" +
			response.openDates[i].amOrPmOrAll1 + "|" +
			response.openDates[i].weekDay2 + "|" +
			response.openDates[i].mmDD2 + "|" +
			response.openDates[i].amOrPmOrAll2 + "|"
		);
	}
	
	return new String(outptStr.toString());
}
/**
 * Display ASONErrorResp.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp
 */
public static String display(ASONErrorResp response) {
	
	StringBuffer outptStr = new StringBuffer("ASONErrorResp = ");
	outptStr.append(response.tagInformation.headerInfo1.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo2.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo3.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo4.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo5.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo6.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo7.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.tagErrCode.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo8.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.wordAlignmentByte.trim());
	outptStr.append("|");
	outptStr.append(response.replyCode);
	outptStr.append("|");
	outptStr.append(response.advisoryMsg.trim());
	outptStr.append("|");
	return new String(outptStr.toString());
}
/**
 * Display ASONLvngUnitInqResp.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqResp
 */
public static String display(ASONLvngUnitInqResp response) {
	
	StringBuffer outptStr = new StringBuffer("ASONLvngUnitInqResp = ");
	outptStr.append(response.tagInformation.headerInfo1.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo2.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo3.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo4.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo5.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo6.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo7.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.tagErrCode.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo8.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.wordAlignmentByte.trim());
	outptStr.append("|");
	outptStr.append(response.replyCode);
	outptStr.append("|");
	outptStr.append(response.advisoryMsg.trim());
	outptStr.append("|");
	outptStr.append(response.codeDisplay.trim());
	outptStr.append("|");
	outptStr.append(response.lowRangeDisplay.trim());
	outptStr.append("|");
	outptStr.append(response.highRangeDisplay.trim());
	outptStr.append("|");
	for (int i = 0; i < response.infoLines.length; i++){
		outptStr.append(response.infoLines[i].infoLine);
		outptStr.append("|");
	}
	outptStr.append(response.savedLivuntKey.trim());
	outptStr.append("|");
	outptStr.append(response.stNbrFld1.trim());
	outptStr.append("|");
	outptStr.append(response.stNbrFld2.trim());
	outptStr.append("|");
	outptStr.append(response.locLocValue1.trim());
	outptStr.append("|");
	outptStr.append(response.locLocValue2.trim());
	outptStr.append("|");
	outptStr.append(response.locLocValue3.trim());
	outptStr.append("|");
	outptStr.append(response.locLocValue4.trim());
	outptStr.append("|");
	outptStr.append(response.locLocValue5.trim());
	outptStr.append("|");
	outptStr.append(response.communityName.trim());
	outptStr.append("|");
	
	for (int i = 0; i < response.lufRecordKeys.length; i++){
		outptStr.append(response.lufRecordKeys[i].raiCode.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].sagAreaId.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].wireCenter.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].communityName.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].streetDirection.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].streetName.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].assignedHouseNumberInd.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].stNbrFld1.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].stNbrFld2.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].locValue1.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].locValue2.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].locValue3.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].locValue4.trim());
		outptStr.append("|");
		outptStr.append(response.lufRecordKeys[i].locValue5.trim());
		outptStr.append("|");
	}
	
	for (int i = 0; i < response.lufLocTagsArea.length; i++){
		outptStr.append(response.lufLocTagsArea[i].locTag1.trim());
		outptStr.append("|");
		outptStr.append(response.lufLocTagsArea[i].locTag2.trim());
		outptStr.append("|");
		outptStr.append(response.lufLocTagsArea[i].locTag3.trim());
		outptStr.append("|");
		outptStr.append(response.lufLocTagsArea[i].locTag4.trim());
		outptStr.append("|");
		outptStr.append(response.lufLocTagsArea[i].locTag5.trim());
		outptStr.append("|");
	}
	outptStr.append(response.sentEndString.trim());
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display ASONLvngUnitValResp.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp
 */
public static String display(ASONLvngUnitValResp response) {
	
	StringBuffer outptStr = new StringBuffer("ASONLvngUnitValResp = ");
	outptStr.append(response.tagInformation.headerInfo1.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo2.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo3.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo4.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo5.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo6.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo7.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.tagErrCode.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo8.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.wordAlignmentByte.trim());
	outptStr.append("|");
	outptStr.append(response.replyCode);
	outptStr.append("|");
	outptStr.append(response.advisoryMsg.trim());
	outptStr.append("|");
	outptStr.append(response.codeDisplay.trim());
	outptStr.append("|");
	outptStr.append(response.raiCode.trim());
	outptStr.append("|");
	outptStr.append(response.sagAreaId.trim());
	outptStr.append("|");
	outptStr.append(response.wireCenter.trim());
	outptStr.append("|");
	outptStr.append(response.communityName.trim());
	outptStr.append("|");
	outptStr.append(response.streetDirection.trim());
	outptStr.append("|");
	outptStr.append(response.streetName.trim());
	outptStr.append("|");
	outptStr.append(response.assignedHseNumberInd.trim());
	outptStr.append("|");
	outptStr.append(response.stNbrFld1.trim());
	outptStr.append("|");
	outptStr.append(response.stNbrFld2.trim());
	outptStr.append("|");
	outptStr.append(response.loc1.trim());
	outptStr.append("|");
	outptStr.append(response.loc2.trim());
	outptStr.append("|");
	outptStr.append(response.loc3.trim());
	outptStr.append("|");
	outptStr.append(response.loc4.trim());
	outptStr.append("|");
	outptStr.append(response.loc5.trim());
	outptStr.append("|");
	outptStr.append(response.locTag1.trim());
	outptStr.append("|");
	outptStr.append(response.locTag2.trim());
	outptStr.append("|");
	outptStr.append(response.locTag3.trim());
	outptStr.append("|");
	outptStr.append(response.locTag4.trim());
	outptStr.append("|");
	outptStr.append(response.locTag5.trim());
	outptStr.append("|");
	outptStr.append(response.clusterCode.trim());
	outptStr.append("|");
	outptStr.append(response.serviceAvailInd.trim());
	outptStr.append("|");
	outptStr.append(response.luFiller.trim());
	outptStr.append("|");
	outptStr.append(response.customerTN.trim());
	outptStr.append("|");
	outptStr.append(response.customerName.trim());
	outptStr.append("|");
	outptStr.append(response.customerAddress.trim());
	outptStr.append("|");
	outptStr.append(response.sentEndString.trim());
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display ASONSagInqResp.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp
 */
public static String display(ASONSagInqResp response) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagInqResp = ");
	outptStr.append(response.tagInformation.headerInfo1.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo2.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo3.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo4.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo5.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo6.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo7.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.tagErrCode.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo8.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.wordAlignmentByte.trim());
	outptStr.append("|");
	outptStr.append(response.replyCode);
	outptStr.append("|");
	outptStr.append(response.advisoryMsg.trim());
	outptStr.append("|");
	outptStr.append(response.codeDisplay.trim());
	outptStr.append("|");
	
	for (int i = 0; i < response.sagLines.length; i++){
		outptStr.append(response.sagLines[i].sagLine);
		outptStr.append("|");
	}
	
	for (int i = 0; i < response.sagKeys.length; i++){
		outptStr.append(response.sagKeys[i].raiCode.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].sagAreaId.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].alphaNumInd.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].addressName.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].directional.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].highAddrRange.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].lowAddrRange.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].oddEvenIndicator.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].exchange.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].centralOffice.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].map.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].rateBandZone.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].zipCode.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].npa.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].countyAbbrev.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].municipality.trim());
		outptStr.append("|");
		outptStr.append(response.sagKeys[i].filler.trim());
		outptStr.append("|");
	}
	outptStr.append(response.savedSagKey);
	outptStr.append("|");
	
	for (int i = 0; i < response.sagByPassArea.length; i++){
		outptStr.append(response.sagByPassArea[i].bpZipCode.trim());
		outptStr.append("|");
		outptStr.append(response.sagByPassArea[i].bpExchange.trim());
		outptStr.append("|");
		outptStr.append(response.sagByPassArea[i].bpCounty.trim());
		outptStr.append("|");
		outptStr.append(response.sagByPassArea[i].bpCommunity.trim());
		outptStr.append("|");
	}


	outptStr.append(response.sentEndString.trim());
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display ASONSagValidAppErr.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidAppErr
 */
public static String display(ASONSagValidAppErr response) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagValidAppErr = ");
	outptStr.append(response.comReplyHdr1.ReplyCode);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgLength.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.TmfAction);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgDelimiter);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgText.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.XDRalignFiller);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.addressName.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.community.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.zipCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.descriptiveAddrInd);
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display ASONSagValidDescErr.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr
 */
public static String display(ASONSagValidDescErr response) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagValidDescErr = ");
	outptStr.append(response.comReplyHdr1.ReplyCode);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgLength.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.TmfAction);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgDelimiter);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgText.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.XDRalignFiller);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.addressName.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.community.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.zipCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.descriptiveAddrInd);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.sagErrorCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiPrimary);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiAlt1);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiAlt2);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiAlt3);
	outptStr.append("|");
	outptStr.append(response.descAddrRmk1);
	outptStr.append("|");
	outptStr.append(response.descAddrRmk2);
	outptStr.append("|");
	outptStr.append(response.descAddrRmk3);
	outptStr.append("|");
	outptStr.append(response.descAddrRmk4);
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display ASONSagValidErr.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr
 */
public static String display(ASONSagValidErr response) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagValidErr = ");
	outptStr.append(response.comReplyHdr1.ReplyCode);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgLength.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.TmfAction);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgDelimiter);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgText.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.XDRalignFiller);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.addressName.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.community.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.zipCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.descriptiveAddrInd);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.sagErrorCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiPrimary);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiAlt1);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiAlt2);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr3.saiAlt3);
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display ASONSagValidInvReqErr.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidInvReqErr
 */
public static String display(ASONSagValidInvReqErr response) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagValidInvReqErr = ");
	outptStr.append(response.comReplyHdr1.ReplyCode);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgLength.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.TmfAction);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgDelimiter);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgText.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.XDRalignFiller);
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display ASONSagValidErr.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr
 */
public static String display(ASONSagValidReq request) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagValidReq = ");
	outptStr.append(request.requestCode);
	outptStr.append("|");
	outptStr.append(request.MsgLength.trim());
	outptStr.append("|");
	outptStr.append(request.IdGroup.trim());
	outptStr.append("|");
	outptStr.append(request.IdTypist.trim());
	outptStr.append("|");
	outptStr.append(request.IdSystem.trim());
	outptStr.append("|");
	outptStr.append(request.RequestDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(request.RequestTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(request.RegionalAreaId.trim());
	outptStr.append("|");
	outptStr.append(request.addressName.trim());
	outptStr.append("|");
	outptStr.append(request.community.trim());
	outptStr.append("|");
	outptStr.append(request.zipCode.trim());
	outptStr.append("|");
	outptStr.append(request.descriptiveAddrInd);
	outptStr.append("|");

	return new String(outptStr.toString());
}
/**
 * Display ASONSagValidResp.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp
 */
public static String display(ASONSagValidResp response) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagValidResp = ");
	outptStr.append(response.comReplyHdr1.ReplyCode);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgLength.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.TmfAction);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.RequestTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyDateYYYYMMDD.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.ReplyTimeHHMMSSCC.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgDelimiter);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.MsgText.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr1.XDRalignFiller);
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.addressName.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.community.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.zipCode.trim());
	outptStr.append("|");
	outptStr.append(response.comReplyHdr2.descriptiveAddrInd);
	outptStr.append("|");
	outptStr.append(response.raiCode.trim());
	outptStr.append("|");
	outptStr.append(response.sagAreaId);
	outptStr.append("|");
	outptStr.append(response.alphaNumInd);
	outptStr.append("|");
	outptStr.append(response.addressNameSag.trim());
	outptStr.append("|");
	outptStr.append(response.directional.trim());
	outptStr.append("|");
	outptStr.append(response.highAddrRange.trim());
	outptStr.append("|");
	outptStr.append(response.lowAddrRange.trim());
	outptStr.append("|");
	outptStr.append(response.oddEvenIndicator);
	outptStr.append("|");
	outptStr.append(response.exchange.trim());
	outptStr.append("|");
	outptStr.append(response.centralOffice.trim());
	outptStr.append("|");
	outptStr.append(response.map.trim());
	outptStr.append("|");
	outptStr.append(response.rateBand);
	outptStr.append("|");
	outptStr.append(response.rateZone);
	outptStr.append("|");
	outptStr.append(response.zipCodeSag.trim());
	outptStr.append("|");
	outptStr.append(response.npa.trim());
	outptStr.append("|");
	outptStr.append(response.countyAbbrev.trim());
	outptStr.append("|");
	outptStr.append(response.municipality.trim());
	outptStr.append("|");
	outptStr.append(response.sagWireCenter.trim());
	outptStr.append("|");
	outptStr.append(response.communitySag.trim());
	outptStr.append("|");
	outptStr.append(response.state.trim());
	outptStr.append("|");
	outptStr.append(response.needBillAddrInd);
	outptStr.append("|");
	outptStr.append(response.editAgainstLufFile);
	outptStr.append("|");
	outptStr.append(response.needLocLevel1Ind);
	outptStr.append("|");
	outptStr.append(response.needLocLevel2Ind);
	outptStr.append("|");
	outptStr.append(response.needLocLevel3Ind);
	outptStr.append("|");
	outptStr.append(response.needCommNameInd);
	outptStr.append("|");
	outptStr.append(response.secondLineInd);
	outptStr.append("|");
	outptStr.append(response.metroOptSvcInd);
	outptStr.append("|");
	outptStr.append(response.omitCentralOfficeInd);
	outptStr.append("|");
	outptStr.append(response.remarksInd);
	outptStr.append("|");
	outptStr.append(response.lastAssignedHouseNumUsed.trim());
	outptStr.append("|");
	outptStr.append(response.cityAbbreviation.trim());
	outptStr.append("|");
	outptStr.append(response.populateCommNameInd);
	outptStr.append("|");
	outptStr.append(response.alternateAddressInd);
	outptStr.append("|");
	outptStr.append(response.lfacsDupAddressInd);
	outptStr.append("|");
	outptStr.append(response.equipmentType.trim());
	outptStr.append("|");
	outptStr.append(response.analogOrDigitalType);
	outptStr.append("|");
	outptStr.append(response.tar.trim());
	outptStr.append("|");
	outptStr.append(response.tnSplitSwitch);
	outptStr.append("|");
	outptStr.append(response.busRateBand);
	outptStr.append("|");
	outptStr.append(response.remoteOrHostType.trim());
	outptStr.append("|");
	outptStr.append(response.alternateNpa.trim());
	outptStr.append("|");
	outptStr.append(response.addrRmks1.trim());
	outptStr.append("|");
	outptStr.append(response.addrRmks2.trim());
	outptStr.append("|");
	outptStr.append(response.addrRmks3.trim());
	outptStr.append("|");
	outptStr.append(response.addrRmks4.trim());
	outptStr.append("|");
	outptStr.append(response.descAddrRmks1.trim());
	outptStr.append("|");
	outptStr.append(response.descAddrRmks2.trim());
	outptStr.append("|");
	outptStr.append(response.descAddrRmks3.trim());
	outptStr.append("|");
	outptStr.append(response.descAddrRmks4.trim());
	outptStr.append("|");
	outptStr.append(response.matchInd);
	outptStr.append("|");
	outptStr.append(response.lata.trim());
	outptStr.append("|");
	outptStr.append(response.filler1.trim());
	outptStr.append("|");
	outptStr.append(response.e911Sur.trim());
	outptStr.append("|");
	outptStr.append(response.e911Exempt.trim());
	outptStr.append("|");
	outptStr.append(response.e911Nrg.trim());
	outptStr.append("|");
	outptStr.append(response.operSur4Ind);
	outptStr.append("|");
	outptStr.append(response.operSur16Ind);
	outptStr.append("|");
	outptStr.append(response.facsWireCenter.trim());
	outptStr.append("|");
	outptStr.append(response.primaryLso.trim());
	outptStr.append("|");
	outptStr.append(response.NbrOfNpaNxx.trim());
	outptStr.append("|");
	for (int i = 0; i < response.npaNxx.length; i++){
		outptStr.append(response.npaNxx[i].npa.trim());
		outptStr.append(response.npaNxx[i].nxx.trim());
		outptStr.append("|");
	}
	
	return new String(outptStr.toString());
}
/**
 * Display ASONSagValResp.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValResp
 */
public static String display(ASONSagValResp response) {
	
	StringBuffer outptStr = new StringBuffer("ASONSagValResp = ");
	outptStr.append(response.tagInformation.headerInfo1.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo2.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo3.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.sendingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo4.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingSystem.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo5.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingDate.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo6.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.receivingTime.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo7.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.tagErrCode.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.headerInfo8.trim());
	outptStr.append("|");
	outptStr.append(response.tagInformation.wordAlignmentByte.trim());
	outptStr.append("|");
	outptStr.append(response.replyCode);
	outptStr.append("|");
	outptStr.append(response.advisoryMsg.trim());
	outptStr.append("|");
	outptStr.append(response.raiCode.trim());
	outptStr.append("|");
	outptStr.append(response.sagAreaId.trim());
	outptStr.append("|");
	outptStr.append(response.alphaNumInd.trim());
	outptStr.append("|");
	outptStr.append(response.addressName.trim());
	outptStr.append("|");
	outptStr.append(response.directional.trim());
	outptStr.append("|");
	outptStr.append(response.highAddrRange.trim());
	outptStr.append("|");
	outptStr.append(response.lowAddrRange.trim());
	outptStr.append("|");
	outptStr.append(response.oddEvenIndicator.trim());
	outptStr.append("|");
	outptStr.append(response.centralOffice.trim());
	outptStr.append("|");
	outptStr.append(response.exchange.trim());
	outptStr.append("|");
	outptStr.append(response.map.trim());
	outptStr.append("|");
	outptStr.append(response.rateBandZone.trim());
	outptStr.append("|");
	outptStr.append(response.busRateBand.trim());
	outptStr.append("|");
	outptStr.append(response.zipCode.trim());
	outptStr.append("|");
	outptStr.append(response.npa.trim());
	outptStr.append("|");
	outptStr.append(response.countyAbbrev.trim());
	outptStr.append("|");
	outptStr.append(response.municipality.trim());
	outptStr.append("|");
	outptStr.append(response.wireCenter.trim());
	outptStr.append("|");
	outptStr.append(response.community.trim());
	outptStr.append("|");
	outptStr.append(response.state.trim());
	outptStr.append("|");
	outptStr.append(response.editIndicators.trim());
	outptStr.append("|");
	outptStr.append(response.lastAssignedHouseNumUsed.trim());
	outptStr.append("|");
	outptStr.append(response.cityAbbreviation.trim());
	outptStr.append("|");
	outptStr.append(response.populateCommNameInd.trim());
	outptStr.append("|");
	outptStr.append(response.alternateAddressInd.trim());
	outptStr.append("|");
	outptStr.append(response.lfacsDupAddressInd.trim());
	outptStr.append("|");
	outptStr.append(response.equipmentType.trim());
	outptStr.append("|");
	outptStr.append(response.analogOrDigitalType.trim());
	outptStr.append("|");
	outptStr.append(response.tar.trim());
	outptStr.append("|");
	outptStr.append(response.remoteOrHostType.trim());
	outptStr.append("|");
	outptStr.append(response.e911Sur.trim());
	outptStr.append("|");
	outptStr.append(response.e911Exempt.trim());
	outptStr.append("|");
	outptStr.append(response.e911Nrg.trim());
	outptStr.append("|");
	outptStr.append(response.operSur4Ind.trim());
	outptStr.append("|");
	outptStr.append(response.operSur16Ind.trim());
	outptStr.append("|");
	outptStr.append(response.matchInd.trim());
	outptStr.append("|");
	for (int i = 0; i < response.npaPrfxArray.length; i++){
		outptStr.append(response.npaPrfxArray[i].npa.trim());
		outptStr.append(response.npaPrfxArray[i].nxx.trim());
		outptStr.append("|");
	}
	outptStr.append(response.sendEndString.trim());
	outptStr.append("|");
	
	return new String(outptStr.toString());
}
/**
 * Display TandemDgSrvErr.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr
 */
public static String display(TandemDgSrvErr response) {
	
	StringBuffer outptStr = new StringBuffer("TandemDgSrvErr = ");
	outptStr.append(response.advisoryMsg.trim());
	outptStr.append("|");
	return new String(outptStr.toString());
}
/**
 * Generate test ASON SAG Validation Request.
 * Creation date: (6/5/01 9:20:08 AM)
 * @return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq
 */
public static ASONDueDateReq genTestDueDateReq() {

	return (new ASONDueDateReq(
					(short)7100,		//requestCode
					"",					//advisoryMsg
					"HLP-WHEN-",		//hlpWhen
					"317IPLS63X",		//sagDesired
					'-',				//hyphen
					"RES",				//resOrBus
					"",					//filler1
					"",					//dateKey
					"01",				//functionKeyDepressed
					"INR09 RAPT1",		//helpCrossRefKey
					"",					//helpTextKey
					"01",				//helpCursorRow
					"RESAL",			//idGroup
					"RMBIS",			//idTerminal
					"999",				//idTypist
					""));				//timeKey
}
/**
 * Generate test ASON Living Unit Inquiry Request.
 * Creation date: (6/5/01 9:20:08 AM)
 * @return com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReq
 */
public static ASONLvngUnitInqReq genTestLUInqReq() {

	short requestCode = 5210;
	taginformation_st tagInfo = new taginformation_st ("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
	commandline_st cmdLine = new commandline_st ("", "");
	
	return (new ASONLvngUnitInqReq(
					tagInfo,
					requestCode,
					cmdLine,
					"",			//dateKey
					"",			//functionKeyDepressed
					"",			//idGroup
					"",			//idTerminal
					"",			//idTypist
					"",			//regionalAreaId
					"",			//timeKey
					"",			//savedLuKey
					"",			//raiCode
					"",			//sagAreaId
					"",			//wireCenter
					"",			//communityName
					"",			//streetDirection
					"",			//streetName1
					"", 		//streetName2_40
					"",			//assignedHouseNumberInd
					"",			//stNbrFld1
					"",			//stNbrFld2
					"",			//locLocValue1
					"",			//locLocValue2
					"",			//locLocValue3
					"",			//locLocValue4
					"",			//locLocValue5
					"",			//custTn
					"",			//custName
					"",			//custAddress
					"",			//streetRangeLow
					"",			//streetRangeHigh
					"",			//oddEvenInd
					"",			//exchange
					"",			//centralOffice
					"",			//map
					"",			//rateBandZone
					"",			//zipCode
					"",			//npa
					"",			//skRaiCode
					"",			//skSaiCode
					"",			//skAlphaNumInd
					"",			//skAddressName
					"",			//skDirectional
					"",			//skHighRange
					"",			//skLowRange
					"",			//skOddEvenIndicator
					"",			//skExchange
					"",			//skSagCO
					"",			//skMap
					"",			//SkRateBandZone
					"",			//skZipCode
					"",			//skNpa
					"",			//skCountyAbbrev
					"",			//skMunicipality
					"",			//sendEndString
					"0" ));		//maxUnitReturnLimit
}
/**
 * Generate test ASON Living Unit Validation Request.
 * Creation date: (6/5/01 9:20:08 AM)
 * @return com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReq
 */
public static ASONLvngUnitValReq genTestLUValReq() {

	taginformation_st tagInfo = new taginformation_st ("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
	commandline_st cmdLine = new commandline_st ("", "");
	
	return (new ASONLvngUnitValReq(
					tagInfo,
					(short) 5215,
					cmdLine,
					"",					//dateKey
					"01",				//functionKeyDepressed
					"VRSAA",			//idGroup
					"LIMBIS",			//idTerminal
					"999",				//idTypist
					"IN",				//regionalAreaId
					"",					//timeKey
					"IN",				//raiCode
					"I",				//sagAreaId
					"142",				//wireCenter
					"INDIANAPOLIS",		//communityName
					"",					//streetDirection
					"SNOWFLAKE DR",		//streetName
					"",					//assignedHouseNumberInd
					"7632",				//stNbrFld1
					"",					//stNbrFld2
					"",					//loc1
					"",					//loc2
					"",					//loc3
					"",					//loc4
					"",					//loc5
					"",					//locTag1
					"",					//locTag2
					"",					//locTag3
					"",					//locTag4
					"",					//locTag5
					"46227",			//zipCode
					""));				//sendEndString
}
/**
 * Generate test ASON SAG2 Inquiry Request.
 * Creation date: (6/5/01 9:20:08 AM)
 * @return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSag1InqReq
 */
public static ASONSagInqReq genTestSagInqReq() {

	short requestCode = 7300;
	taginformation_st tagInfo = new taginformation_st ("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
	commandline_st cmdLine = new commandline_st ("", "");
	
	return (new ASONSagInqReq(
					tagInfo,
					requestCode,
					cmdLine,
					"",			//dateKey
					"01",		//functionKeyDepressed
					"VRSAA",	//idGroup
					"LIMBIS",	//idTerminal
					"999",		//idTypist
					"IN",		//regionalAreaId
					"",			//timeKey
					"I",		//sagAreaId
					"",			//sagDirectional
					"1 BENTWOD",//addressName
					"46143",	//zipCode
					"",			//savedSagKey
					"",			//savedSagScreenInd
					"",			//exactPositioningInd
					"",			//sentEndString
					"0" ));		//maxUnitReturnLimit
}
/**
 * Generate test ASON SAG Valid Request.
 * Creation date: (6/5/01 9:20:08 AM)
 * @return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq
 */
public static ASONSagValidReq genTestSagValidReq() {

	Date today = new java.util.Date();
	SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd.HHmmss.SSS" ) ;
	String dateString = formatter.format( today ) ;
	String sendingDate = dateString.substring(2,8);
	String sendingTime = dateString.substring(9,15) + dateString.substring(16,18);
	
	return (new ASONSagValidReq(
					(short) 4441,				//requestCode
					"139",						//msgLength
					"VRSAA",					//idGroup
					"999",						//idTypist
					"LIMBIS",					//idSystem
					sendingDate,				//requestDate
					sendingTime,				//requestTime
					"IN",						//regionalAreaId
					"INDIANA STATE PRISON",		//addressName
					"MICHIGAN CITY",			//community
					"46360",					//zipCode
					'Y'));						//descriptiveAddrInd
}
/**
 * Generate test ASON SAG Validation Request.
 * Creation date: (6/5/01 9:20:08 AM)
 * @return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq
 */
public static ASONSagValReq genTestSagValReq() {

	Date today = new java.util.Date();
	SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd.HHmmss.SSS" ) ;
	String dateString = formatter.format( today ) ;
	String sendingDate = dateString.substring(2,8);
	String sendingTime = dateString.substring(9,15) + dateString.substring(16,18);
	taginformation_st tagInfo = new taginformation_st ("", "LIMBIS", "", sendingDate, "", sendingTime, "", "", "", "", "", "", "", "", "", "");
	commandline_st cmdLine = new commandline_st ("", "");
	
	return (new ASONSagValReq(
					tagInfo,
					(short) 0000,
					cmdLine,
					"",							//dateKey
					"",							//functionKeyDepressed
					"",							//idGroup
					"",							//idTerminal
					"",							//idTypist
					"IN",						//regionalAreaId
					"",							//timeKey
					"7632 SNOWFLAKE DR",		//addressName
					"46227",					//zipCode
					"INDIANAPOLIS",				//community
					"",							//holdSagKey
					""));						//sentEndString
}
/**
 * Log ASONServiceTest processes.
 * Creation date: (4/26/01 12:42:18 PM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) {
	System.out.println("LOG: " + eventId + " " + message);
}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

/**
 * Starts the ASONServiceTest application
 * Creation date: (4/26/01 12:33:23 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args)
{
	Hashtable props = new Hashtable();
	props.put("APPLDATA", "AIT");
//	props.put("ASONSERVICE_APPLDATA", "asonTest");
	props.put("ASONSERVICE_APPLDATA", "asonWav6");
	props.put("ASONSERVICE_TIMEOUT", "60");
	props.put("GWSVCS_CLNTUUID", "limbis");
	props.put("VICUNA_XML_FILE", "vicunalite.xml");
	props.put("VICUNA_SERVICE_CONFIG_DIR", "");
	
	try
	{
		EventResultPair response = null;
		Vector vectorResponse = null;
		ASONServiceTest aASONServiceTest = new ASONServiceTest();
		ASONServiceHelper helper = new ASONServiceHelper(props, aASONServiceTest);

		// Active
		// C=US,O=AIT,OU=ASON,CN=AsonService 	APPL <asonTest>
/***/
		// ASON Sag Validation Request
		ASONSagValidReq asonSagValidReq = genTestSagValidReq();
		System.out.println(display(asonSagValidReq));
		response = helper.asonSagValidReq(null, null, 0, asonSagValidReq);
/***/
/*** /
		// ASON Sag Validation Request
		ASONSagValReq asonSagValReq = genTestSagValReq();
		response = helper.asonSagValReq(null, null, 0, asonSagValReq);
/***/
/*** /
		// ASON Sag Inquiry Request
		ASONSagInqReq asonSagInqReq = genTestSagInqReq();
		vectorResponse = helper.asonSagInqReq(null, null, 0, asonSagInqReq);
/***/
/*** /
		// ASON Living Unit Validation Request
		ASONLvngUnitValReq asonLvngUnitValReq = genTestLUValReq();
		response = helper.asonLvngUnitValReq(null, null, 0, asonLvngUnitValReq);
/***/
/*** /
		// ASON Living Unit Inquiry Request
		ASONLvngUnitInqReq asonLvngUnitInqReq = genTestLUInqReq();
		vectorResponse = helper.asonLvngUnitInqReq(null, null, 0, asonLvngUnitInqReq);
/***/
/*** /
		// ASON Open Dates Inquiry Request
		ASONDueDateReq asonDueDateReq = genTestDueDateReq();
		response = helper.asonDueDateReq(null, null, 0, asonDueDateReq);
/***/

		if (vectorResponse == null){
			System.out.println("Received event: " + response.getEventNbr());
			switch (response.getEventNbr())
			{
				case ASONServiceAccess.ASON_SAG_VALID_RESP_NBR:
					ASONSagValidResp sagValidResp = (ASONSagValidResp)response.getTheObject();
					System.out.println(display(sagValidResp));
					break;
				case ASONServiceAccess.ASON_SAG_VALIDATION_RESP_NBR:
					ASONSagValResp sagValResp = (ASONSagValResp)response.getTheObject();
					System.out.println(display(sagValResp));
					break;
				case ASONServiceAccess.ASON_LU_VALIDATION_RESP_NBR:
					ASONLvngUnitValResp lvngUnitValResp = (ASONLvngUnitValResp)response.getTheObject();
					System.out.println(display(lvngUnitValResp));
					break;
				case ASONServiceAccess.ASON_DUE_DATE_RESP_NBR:
					ASONDueDateResp dueDateResp = (ASONDueDateResp)response.getTheObject();
					System.out.println(display(dueDateResp));
					break;
				case ASONServiceAccess.ASON_DUE_DATE_ERR_NBR:
					ASONDueDateErr dueDateErr = (ASONDueDateErr)response.getTheObject();
					System.out.println(display(dueDateErr));
					break;
				case ASONServiceAccess.ASON_SAG_VALIDATION_ERR_NBR:
				case ASONServiceAccess.ASON_LU_VALIDATION_ERR_NBR:
					ASONErrorResp errorResp = (ASONErrorResp)response.getTheObject();
					System.out.println(display(errorResp));
					break;
				case ASONServiceAccess.ASON_SAG_VALID_ERR_NBR:
					ASONSagValidErr sagValidErr = (ASONSagValidErr)response.getTheObject();
					System.out.println(display(sagValidErr));
					break;
				case ASONServiceAccess.ASON_SAG_VALID_APP_ERR_NBR:
					ASONSagValidAppErr sagValidAppErr = (ASONSagValidAppErr)response.getTheObject();
					System.out.println(display(sagValidAppErr));
					break;
				case ASONServiceAccess.ASON_SAG_VALID_DESC_ERR_NBR:
					ASONSagValidDescErr sagValidDescErr = (ASONSagValidDescErr)response.getTheObject();
					System.out.println(display(sagValidDescErr));
					break;
				case ASONServiceAccess.ASON_SAG_VALID_INVREQ_ERR_NBR:
					ASONSagValidInvReqErr sagValidInvReqErr = (ASONSagValidInvReqErr)response.getTheObject();
					System.out.println(display(sagValidInvReqErr));
					break;
				case ASONServiceAccess.DG_TANDEM_ERR_NBR:
					TandemDgSrvErr dgSrvErrorResp = (TandemDgSrvErr)response.getTheObject();
					System.out.println(display(dgSrvErrorResp));
					break;
				default:
					System.out.println("UNKNOWN EVENT<" + response.getEventNbr() + ">");
			} // end switch
		} // end if EventResultPair
		else {
			if (vectorResponse.firstElement() instanceof ASONSagInqResp){
				for (int i = 0; i < vectorResponse.size(); i++){
					ASONSagInqResp sagInqResp = (ASONSagInqResp)vectorResponse.get(i);
					System.out.println(display(sagInqResp));
				}

			}
			else if (vectorResponse.firstElement() instanceof ASONSagInqResp){
					for (int i = 0; i < vectorResponse.size(); i++){
						ASONLvngUnitInqResp lvngUnitInqResp = (ASONLvngUnitInqResp)((EventResultPair)vectorResponse.get(i)).getTheObject();
						System.out.println(display(lvngUnitInqResp));
					}
			}
			else{
				System.out.println("ERROR: Unknown VectorResponse");
			}
		} // end if vectorResponse
	}
	catch (ASONServiceException e)
	{
		System.out.println("ASONServiceException: " + e.getExceptionCode() + " " + e.getMessage());
	}
	catch (ServiceException e)
	{
		System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
		e.printStackTraces();
	}

}
}
