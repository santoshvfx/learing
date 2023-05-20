// $Id: GeoSegMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class GeoSegMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t Gsgm;

	public GeoSegMenuProcStatus_t () {
	}
	public GeoSegMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t Gsgm) { 
		this.RTCD = RTCD;
		this.Gsgm = Gsgm;

	}
}
