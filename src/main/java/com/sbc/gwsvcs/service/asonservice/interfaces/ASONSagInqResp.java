// $Id: ASONSagInqResp.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagInqResp implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation;
	public short replyCode;
	public String advisoryMsg;
	public String codeDisplay;
	public com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st sagLines[];
	public com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st sagKeys[];
	public String savedSagKey;
	public com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st sagByPassArea[];
	public String sentEndString;

	public ASONSagInqResp () {
	}
	public ASONSagInqResp (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short replyCode, String advisoryMsg, String codeDisplay, com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st sagLines[], com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st sagKeys[], String savedSagKey, com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st sagByPassArea[], String sentEndString) { 
		this.tagInformation = tagInformation;
		this.replyCode = replyCode;
		this.advisoryMsg = advisoryMsg;
		this.codeDisplay = codeDisplay;
		this.sagLines = sagLines;
		this.sagKeys = sagKeys;
		this.savedSagKey = savedSagKey;
		this.sagByPassArea = sagByPassArea;
		this.sentEndString = sentEndString;

	}
}
