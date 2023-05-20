// $Id: StAddrRngeMenuPktResp_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class StAddrRngeMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t StAddrRngeMenuProcStatus[];

	public StAddrRngeMenuPktResp_t () {
	}
	public StAddrRngeMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t StAddrRngeMenuProcStatus[]) { 
		this.TS = TS;
		this.StAddrRngeMenuProcStatus = StAddrRngeMenuProcStatus;

	}
}
