// $Id: ASONErrorResp.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONErrorResp implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation;
	public short replyCode;
	public String advisoryMsg;
	public String sendEndString;

	public ASONErrorResp () {
	}
	public ASONErrorResp (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short replyCode, String advisoryMsg, String sendEndString) { 
		this.tagInformation = tagInformation;
		this.replyCode = replyCode;
		this.advisoryMsg = advisoryMsg;
		this.sendEndString = sendEndString;

	}
}
