// $Id: UnnbrdAddrRngeMenuPktResp_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnnbrdAddrRngeMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t UnnbrdAddrRngeMenuProcStatus[];

	public UnnbrdAddrRngeMenuPktResp_t () {
	}
	public UnnbrdAddrRngeMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t UnnbrdAddrRngeMenuProcStatus[]) { 
		this.TS = TS;
		this.UnnbrdAddrRngeMenuProcStatus = UnnbrdAddrRngeMenuProcStatus;

	}
}
