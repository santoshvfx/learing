// $Id: PremisTnMttReq_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnMttReq_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_t TnMttReq;

	public PremisTnMttReq_t () {
	}
	public PremisTnMttReq_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_t TnMttReq) { 
		this.Header = Header;
		this.TnMttReq = TnMttReq;

	}
}
