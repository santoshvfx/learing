// $Id: RapReq_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class RapReq_t implements java.io.Serializable { 
	public String SAGA;
	public String ZC;
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t Addr;

	public RapReq_t () {
	}
	public RapReq_t (String SAGA, String ZC, com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t NpaPrfxLn, com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t Addr) { 
		this.SAGA = SAGA;
		this.ZC = ZC;
		this.NpaPrfxLn = NpaPrfxLn;
		this.Addr = Addr;

	}
}
