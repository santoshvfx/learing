// $Id: UnnbrdAddrRngeMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnnbrdAddrRngeMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t Unadrm;

	public UnnbrdAddrRngeMenuProcStatus_t () {
	}
	public UnnbrdAddrRngeMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t Unadrm) { 
		this.RTCD = RTCD;
		this.Unadrm = Unadrm;

	}
}
