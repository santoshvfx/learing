// $Id: ASONLvngUnitInqResp.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONLvngUnitInqResp implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation;
	public short replyCode;
	public String advisoryMsg;
	public String codeDisplay;
	public String lowRangeDisplay;
	public String highRangeDisplay;
	public com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st infoLines[];
	public String savedLivuntKey;
	public String stNbrFld1;
	public String stNbrFld2;
	public String locLocValue1;
	public String locLocValue2;
	public String locLocValue3;
	public String locLocValue4;
	public String locLocValue5;
	public String communityName;
	public com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st lufRecordKeys[];
	public com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st lufLocTagsArea[];
	public String sentEndString;

	public ASONLvngUnitInqResp () {
	}
	public ASONLvngUnitInqResp (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short replyCode, String advisoryMsg, String codeDisplay, String lowRangeDisplay, String highRangeDisplay, com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st infoLines[], String savedLivuntKey, String stNbrFld1, String stNbrFld2, String locLocValue1, String locLocValue2, String locLocValue3, String locLocValue4, String locLocValue5, String communityName, com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st lufRecordKeys[], com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st lufLocTagsArea[], String sentEndString) { 
		this.tagInformation = tagInformation;
		this.replyCode = replyCode;
		this.advisoryMsg = advisoryMsg;
		this.codeDisplay = codeDisplay;
		this.lowRangeDisplay = lowRangeDisplay;
		this.highRangeDisplay = highRangeDisplay;
		this.infoLines = infoLines;
		this.savedLivuntKey = savedLivuntKey;
		this.stNbrFld1 = stNbrFld1;
		this.stNbrFld2 = stNbrFld2;
		this.locLocValue1 = locLocValue1;
		this.locLocValue2 = locLocValue2;
		this.locLocValue3 = locLocValue3;
		this.locLocValue4 = locLocValue4;
		this.locLocValue5 = locLocValue5;
		this.communityName = communityName;
		this.lufRecordKeys = lufRecordKeys;
		this.lufLocTagsArea = lufLocTagsArea;
		this.sentEndString = sentEndString;

	}
}
