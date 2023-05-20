// $Id: TnSucc_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnSucc_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn;
	public char SUCC_CD;

	public TnSucc_t () {
	}
	public TnSucc_t (com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn, char SUCC_CD) { 
		this.NpaPrfxLn = NpaPrfxLn;
		this.SUCC_CD = SUCC_CD;

	}
}
