// $Id: TnSaveReq_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnSaveReq_t implements java.io.Serializable { 
	public String SAGA;
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr;
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn;
	public char LN_ID;

	public TnSaveReq_t () {
	}
	public TnSaveReq_t (String SAGA, com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr, com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn, char LN_ID) { 
		this.SAGA = SAGA;
		this.PrmAddr = PrmAddr;
		this.NpaPrfxLn = NpaPrfxLn;
		this.LN_ID = LN_ID;

	}
}
