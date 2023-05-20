// $Id: AddrRngeMenuItem_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AddrRngeMenuItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t AddrRnge;
	public String CMTY_NM;
	public String STATE_CD;
	public String ZC;

	public AddrRngeMenuItem_t () {
	}
	public AddrRngeMenuItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t AddrRnge, String CMTY_NM, String STATE_CD, String ZC) { 
		this.AddrRnge = AddrRnge;
		this.CMTY_NM = CMTY_NM;
		this.STATE_CD = STATE_CD;
		this.ZC = ZC;

	}
}
