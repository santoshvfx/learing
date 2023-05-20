// $Id: SuppLnInfo_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SuppLnInfo_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t LtdLnInfo;
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_t NpaPrfxLn;
	public char NON_PUB_IND;

	public SuppLnInfo_t () {
	}
	public SuppLnInfo_t (com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t LtdLnInfo, com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_t NpaPrfxLn, char NON_PUB_IND) { 
		this.LtdLnInfo = LtdLnInfo;
		this.NpaPrfxLn = NpaPrfxLn;
		this.NON_PUB_IND = NON_PUB_IND;

	}
}
