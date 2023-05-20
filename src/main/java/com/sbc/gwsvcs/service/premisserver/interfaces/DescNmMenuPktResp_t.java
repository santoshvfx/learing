// $Id: DescNmMenuPktResp_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class DescNmMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t DescNmMenuProcStatus[];

	public DescNmMenuPktResp_t () {
	}
	public DescNmMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t DescNmMenuProcStatus[]) { 
		this.TS = TS;
		this.DescNmMenuProcStatus = DescNmMenuProcStatus;

	}
}
