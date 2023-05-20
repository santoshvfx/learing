// $Id: UnadrmGsgmPktResp_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnadrmGsgmPktResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t UnadrmGsgmProcStatus[];

	public UnadrmGsgmPktResp_t () {
	}
	public UnadrmGsgmPktResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t TS, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t UnadrmGsgmProcStatus[]) { 
		this.TS = TS;
		this.UnadrmGsgmProcStatus = UnadrmGsgmProcStatus;

	}
}
