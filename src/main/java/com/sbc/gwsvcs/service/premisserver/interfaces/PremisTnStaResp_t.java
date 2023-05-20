// $Id: PremisTnStaResp_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnStaResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t TnStaResp;

	public PremisTnStaResp_t () {
	}
	public PremisTnStaResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t TnStaResp) { 
		this.Header = Header;
		this.TnStaResp = TnStaResp;

	}
}
