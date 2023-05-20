// $Id: StAddrRngeMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class StAddrRngeMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t StAddrRngeMenu[];

	public StAddrRngeMenuProcStatus_t () {
	}
	public StAddrRngeMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t StAddrRngeMenu[]) { 
		this.RTCD = RTCD;
		this.StAddrRngeMenu = StAddrRngeMenu;

	}
}
