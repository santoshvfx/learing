// $Id: AddrMenuPktResp_t.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AddrMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t AddrMenuProcStatus[];

	public AddrMenuPktResp_t () {
	}
	public AddrMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t AddrMenuProcStatus[]) { 
		this.TS = TS;
		this.AddrMenuProcStatus = AddrMenuProcStatus;

	}
}
