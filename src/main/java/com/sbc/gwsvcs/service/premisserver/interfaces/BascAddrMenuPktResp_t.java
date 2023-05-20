// $Id: BascAddrMenuPktResp_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class BascAddrMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t BascAddrMenuProcStatus[];

	public BascAddrMenuPktResp_t () {
	}
	public BascAddrMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t BascAddrMenuProcStatus[]) { 
		this.TS = TS;
		this.BascAddrMenuProcStatus = BascAddrMenuProcStatus;

	}
}
