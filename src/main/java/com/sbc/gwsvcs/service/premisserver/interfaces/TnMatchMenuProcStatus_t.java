// $Id: TnMatchMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnMatchMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t TnMatchMenu[];

	public TnMatchMenuProcStatus_t () {
	}
	public TnMatchMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t TnMatchMenu[]) { 
		this.RTCD = RTCD;
		this.TnMatchMenu = TnMatchMenu;

	}
}
