// $Id: HITPktResp_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class HITPktResp_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;

	public HITPktResp_t () {
	}
	public HITPktResp_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS) { 
		this.RTCD = RTCD;
		this.TS = TS;

	}
}
