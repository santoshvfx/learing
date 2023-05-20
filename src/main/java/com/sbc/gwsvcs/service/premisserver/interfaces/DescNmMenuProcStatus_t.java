// $Id: DescNmMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class DescNmMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuItem_t DescNmMenu[];

	public DescNmMenuProcStatus_t () {
	}
	public DescNmMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuItem_t DescNmMenu[]) { 
		this.RTCD = RTCD;
		this.DescNmMenu = DescNmMenu;

	}
}
