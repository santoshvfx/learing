// $Id: PremisTnRsrvReq_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnRsrvReq_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t TnRsrvReq;
	public String HOST_NM;

	public PremisTnRsrvReq_t () {
	}
	public PremisTnRsrvReq_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch, com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t TnRsrvReq, String HOST_NM) { 
		this.Header = Header;
		this.Scratch = Scratch;
		this.TnRsrvReq = TnRsrvReq;
		this.HOST_NM = HOST_NM;

	}
}
