// $Id: ZipMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:18 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZipMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_t ZipMenu[];

	public ZipMenuProcStatus_t () {
	}
	public ZipMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_t ZipMenu[]) { 
		this.RTCD = RTCD;
		this.ZipMenu = ZipMenu;

	}
}
