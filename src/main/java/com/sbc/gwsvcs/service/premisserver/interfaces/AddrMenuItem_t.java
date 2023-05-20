// $Id: AddrMenuItem_t.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AddrMenuItem_t implements java.io.Serializable { 
	public String CMTY_NM;
	public String ALT_CMTY_1_NM;
	public String ALT_CMTY_2_NM;
	public String STATE_CD;
	public String ZC;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t AddrRnge;

	public AddrMenuItem_t () {
	}
	public AddrMenuItem_t (String CMTY_NM, String ALT_CMTY_1_NM, String ALT_CMTY_2_NM, String STATE_CD, String ZC, com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t AddrRnge) { 
		this.CMTY_NM = CMTY_NM;
		this.ALT_CMTY_1_NM = ALT_CMTY_1_NM;
		this.ALT_CMTY_2_NM = ALT_CMTY_2_NM;
		this.STATE_CD = STATE_CD;
		this.ZC = ZC;
		this.AddrRnge = AddrRnge;

	}
}
