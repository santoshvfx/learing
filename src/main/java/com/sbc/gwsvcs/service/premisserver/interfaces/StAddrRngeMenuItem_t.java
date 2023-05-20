// $Id: StAddrRngeMenuItem_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class StAddrRngeMenuItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t AddrRnge;
	public String CMTY_NM;
	public String STATE_CD;
	public String ZC;

	public StAddrRngeMenuItem_t () {
	}
	public StAddrRngeMenuItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm, com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t AddrRnge, String CMTY_NM, String STATE_CD, String ZC) { 
		this.StNm = StNm;
		this.AddrRnge = AddrRnge;
		this.CMTY_NM = CMTY_NM;
		this.STATE_CD = STATE_CD;
		this.ZC = ZC;

	}
}
