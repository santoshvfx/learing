// $Id: TnMatchMenuPktResp_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnMatchMenuPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t TnMatchMenuProcStatus[];

	public TnMatchMenuPktResp_t () {
	}
	public TnMatchMenuPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t TnMatchMenuProcStatus[]) { 
		this.TS = TS;
		this.TnMatchMenuProcStatus = TnMatchMenuProcStatus;

	}
}
