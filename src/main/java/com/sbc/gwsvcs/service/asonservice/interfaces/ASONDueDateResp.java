// $Id: ASONDueDateResp.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONDueDateResp implements java.io.Serializable { 
	public short replyCode;
	public String advisoryMsg;
	public char codeDisplay;
	public String stuffWeDoNotNeed;
	public com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st openDates[];
	public String helpTextKey;

	public ASONDueDateResp () {
	}
	public ASONDueDateResp (short replyCode, String advisoryMsg, char codeDisplay, String stuffWeDoNotNeed, com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st openDates[], String helpTextKey) { 
		this.replyCode = replyCode;
		this.advisoryMsg = advisoryMsg;
		this.codeDisplay = codeDisplay;
		this.stuffWeDoNotNeed = stuffWeDoNotNeed;
		this.openDates = openDates;
		this.helpTextKey = helpTextKey;

	}
}
