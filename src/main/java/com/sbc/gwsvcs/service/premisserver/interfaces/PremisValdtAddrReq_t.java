// $Id: PremisValdtAddrReq_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisValdtAddrReq_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t RapReq;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch;
	public String HOST_NM;

	public PremisValdtAddrReq_t () {
	}
	public PremisValdtAddrReq_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t RapReq, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch, String HOST_NM) { 
		this.Header = Header;
		this.RapReq = RapReq;
		this.Scratch = Scratch;
		this.HOST_NM = HOST_NM;

	}
}
