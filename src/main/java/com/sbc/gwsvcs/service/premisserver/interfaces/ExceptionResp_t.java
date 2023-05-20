// $Id: ExceptionResp_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class ExceptionResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.GWException_t OutExcp;

	public ExceptionResp_t () {
	}
	public ExceptionResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.GWException_t OutExcp) { 
		this.Header = Header;
		this.OutExcp = OutExcp;

	}
}
