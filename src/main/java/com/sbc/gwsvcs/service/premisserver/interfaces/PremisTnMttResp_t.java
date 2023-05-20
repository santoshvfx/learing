// $Id: PremisTnMttResp_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnMttResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t TnMttResp;

	public PremisTnMttResp_t () {
	}
	public PremisTnMttResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t TnMttResp) { 
		this.Header = Header;
		this.TnMttResp = TnMttResp;

	}
}
