// $Id: TnRetnReq_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnRetnReq_t implements java.io.Serializable { 
	public String SAGA;
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr;
	public String GEO_SEG_ID;
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn[];
	public char LN_ID;

	public TnRetnReq_t () {
	}
	public TnRetnReq_t (String SAGA, com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr, String GEO_SEG_ID, com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn[], char LN_ID) { 
		this.SAGA = SAGA;
		this.PrmAddr = PrmAddr;
		this.GEO_SEG_ID = GEO_SEG_ID;
		this.NpaPrfxLn = NpaPrfxLn;
		this.LN_ID = LN_ID;

	}
}
