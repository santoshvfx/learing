// $Id: PremisTnStaReq_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnStaReq_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t TnStaReq;

	public PremisTnStaReq_t () {
	}
	public PremisTnStaReq_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t TnStaReq) { 
		this.Header = Header;
		this.TnStaReq = TnStaReq;

	}
}
