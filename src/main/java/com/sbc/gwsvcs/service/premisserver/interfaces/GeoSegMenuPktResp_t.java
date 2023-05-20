// $Id: GeoSegMenuPktResp_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class GeoSegMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t GeoSegMenuProcStatus[];

	public GeoSegMenuPktResp_t () {
	}
	public GeoSegMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t GeoSegMenuProcStatus[]) { 
		this.TS = TS;
		this.GeoSegMenuProcStatus = GeoSegMenuProcStatus;

	}
}
