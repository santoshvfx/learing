// $Id: StNmMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class StNmMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuItem_t StNmMenu[];

	public StNmMenuProcStatus_t () {
	}
	public StNmMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuItem_t StNmMenu[]) { 
		this.RTCD = RTCD;
		this.StNmMenu = StNmMenu;

	}
}
