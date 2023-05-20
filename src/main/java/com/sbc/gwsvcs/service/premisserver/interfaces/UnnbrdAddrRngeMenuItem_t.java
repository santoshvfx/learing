// $Id: UnnbrdAddrRngeMenuItem_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnnbrdAddrRngeMenuItem_t implements java.io.Serializable { 
	public String ASGND_HOUS_NBR_ID;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo;
	public String CMTY_NM;
	public String STATE_CD;
	public String ZC;
	public String SPL_INSTR;
	public com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t LnInfo;

	public UnnbrdAddrRngeMenuItem_t () {
	}
	public UnnbrdAddrRngeMenuItem_t (String ASGND_HOUS_NBR_ID, com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo, String CMTY_NM, String STATE_CD, String ZC, String SPL_INSTR, com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t LnInfo) { 
		this.ASGND_HOUS_NBR_ID = ASGND_HOUS_NBR_ID;
		this.SuppAddrInfo = SuppAddrInfo;
		this.CMTY_NM = CMTY_NM;
		this.STATE_CD = STATE_CD;
		this.ZC = ZC;
		this.SPL_INSTR = SPL_INSTR;
		this.LnInfo = LnInfo;

	}
}
