// $Id: TNAPktResp_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TNAPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_t TNAProcStatus[];

	public TNAPktResp_t () {
	}
	public TNAPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_t TNAProcStatus[]) { 
		this.TS = TS;
		this.TNAProcStatus = TNAProcStatus;

	}
}
