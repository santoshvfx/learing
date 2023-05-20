// $Id: UnnbrdMenuPktResp_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnnbrdMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t UnnbrdMenuProcStatus[];

	public UnnbrdMenuPktResp_t () {
	}
	public UnnbrdMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t UnnbrdMenuProcStatus[]) { 
		this.TS = TS;
		this.UnnbrdMenuProcStatus = UnnbrdMenuProcStatus;

	}
}
