// $Id: NpaPrfxLn_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class NpaPrfxLn_t implements java.io.Serializable { 
	public String NPA;
	public String PRFX_CD;
	public String LN;

	public NpaPrfxLn_t () {
	}
	public NpaPrfxLn_t (String NPA, String PRFX_CD, String LN) { 
		this.NPA = NPA;
		this.PRFX_CD = PRFX_CD;
		this.LN = LN;

	}
}
