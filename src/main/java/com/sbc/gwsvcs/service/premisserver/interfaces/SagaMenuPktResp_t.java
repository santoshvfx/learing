// $Id: SagaMenuPktResp_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SagaMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t SagaMenuProcStatus[];

	public SagaMenuPktResp_t () {
	}
	public SagaMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t SagaMenuProcStatus[]) { 
		this.TS = TS;
		this.SagaMenuProcStatus = SagaMenuProcStatus;

	}
}
