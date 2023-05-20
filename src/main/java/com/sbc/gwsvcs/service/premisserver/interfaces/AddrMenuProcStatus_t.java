// $Id: AddrMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AddrMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t AddrMenu[];

	public AddrMenuProcStatus_t () {
	}
	public AddrMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t AddrMenu[]) { 
		this.RTCD = RTCD;
		this.AddrMenu = AddrMenu;

	}
}
