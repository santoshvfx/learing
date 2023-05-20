// $Id: UnadrmGsgm_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnadrmGsgm_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t UnnbrdAddrRngeMenu[];
	public com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t GeoSegMenu[];

	public UnadrmGsgm_t () {
	}
	public UnadrmGsgm_t (com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t UnnbrdAddrRngeMenu[], com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t GeoSegMenu[]) { 
		this.UnnbrdAddrRngeMenu = UnnbrdAddrRngeMenu;
		this.GeoSegMenu = GeoSegMenu;

	}
}
