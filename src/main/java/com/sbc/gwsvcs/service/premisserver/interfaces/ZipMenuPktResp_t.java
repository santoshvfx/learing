// $Id: ZipMenuPktResp_t.java,v 1.1 2002/09/29 04:28:18 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class ZipMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t ZipMenuProcStatus[];

	public ZipMenuPktResp_t () {
	}
	public ZipMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t ZipMenuProcStatus[]) { 
		this.TS = TS;
		this.ZipMenuProcStatus = ZipMenuProcStatus;

	}
}
