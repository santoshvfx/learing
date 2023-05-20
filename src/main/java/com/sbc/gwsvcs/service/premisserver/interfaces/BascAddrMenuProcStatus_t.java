// $Id: BascAddrMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class BascAddrMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t BascAddrMenu[];

	public BascAddrMenuProcStatus_t () {
	}
	public BascAddrMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t BascAddrMenu[]) { 
		this.RTCD = RTCD;
		this.BascAddrMenu = BascAddrMenu;

	}
}
