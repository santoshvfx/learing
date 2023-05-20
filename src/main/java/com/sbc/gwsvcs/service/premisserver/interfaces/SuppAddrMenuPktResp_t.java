// $Id: SuppAddrMenuPktResp_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SuppAddrMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t SuppAddrMenuProcStatus[];

	public SuppAddrMenuPktResp_t () {
	}
	public SuppAddrMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t SuppAddrMenuProcStatus[]) { 
		this.TS = TS;
		this.SuppAddrMenuProcStatus = SuppAddrMenuProcStatus;

	}
}
