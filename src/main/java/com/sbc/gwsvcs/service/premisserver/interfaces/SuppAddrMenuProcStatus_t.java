// $Id: SuppAddrMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SuppAddrMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t SuppAddrMenu[];

	public SuppAddrMenuProcStatus_t () {
	}
	public SuppAddrMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t SuppAddrMenu[]) { 
		this.RTCD = RTCD;
		this.SuppAddrMenu = SuppAddrMenu;

	}
}
