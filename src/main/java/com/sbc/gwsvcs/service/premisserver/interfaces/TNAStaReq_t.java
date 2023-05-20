// $Id: TNAStaReq_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TNAStaReq_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t TN;
	public String WC_CD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch;

	public TNAStaReq_t () {
	}
	public TNAStaReq_t (com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t TN, String WC_CD, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch) { 
		this.TN = TN;
		this.WC_CD = WC_CD;
		this.Scratch = Scratch;

	}
}
