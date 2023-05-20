// $Id: UnnbrdMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnnbrdMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t UnnbrdMenu[];

	public UnnbrdMenuProcStatus_t () {
	}
	public UnnbrdMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t UnnbrdMenu[]) { 
		this.RTCD = RTCD;
		this.UnnbrdMenu = UnnbrdMenu;

	}
}
