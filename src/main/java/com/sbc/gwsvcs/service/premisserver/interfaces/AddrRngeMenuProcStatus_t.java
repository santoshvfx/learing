// $Id: AddrRngeMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AddrRngeMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t AddrRngeMenu[];

	public AddrRngeMenuProcStatus_t () {
	}
	public AddrRngeMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t AddrRngeMenu[]) { 
		this.RTCD = RTCD;
		this.AddrRngeMenu = AddrRngeMenu;

	}
}
