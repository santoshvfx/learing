// $Id: ASONDueDateErr.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONDueDateErr implements java.io.Serializable { 
	public short replyCode;
	public String advisoryMsg;
	public String cmdName;
	public char codeDisplay;
	public char indInvInput;
	public char indSystemStatus;

	public ASONDueDateErr () {
	}
	public ASONDueDateErr (short replyCode, String advisoryMsg, String cmdName, char codeDisplay, char indInvInput, char indSystemStatus) { 
		this.replyCode = replyCode;
		this.advisoryMsg = advisoryMsg;
		this.cmdName = cmdName;
		this.codeDisplay = codeDisplay;
		this.indInvInput = indInvInput;
		this.indSystemStatus = indSystemStatus;

	}
}
