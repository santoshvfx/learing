// $Id: TnMatchMenuItem_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnMatchMenuItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t BascAddrInfo;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo;
	public String ZC;
	public String SPL_INSTR;
	public com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t LtdLnInfo;

	public TnMatchMenuItem_t () {
	}
	public TnMatchMenuItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t BascAddrInfo, com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo, String ZC, String SPL_INSTR, com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t LtdLnInfo) { 
		this.BascAddrInfo = BascAddrInfo;
		this.SuppAddrInfo = SuppAddrInfo;
		this.ZC = ZC;
		this.SPL_INSTR = SPL_INSTR;
		this.LtdLnInfo = LtdLnInfo;

	}
}
