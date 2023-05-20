// $Id: UnadrmGsgmProcStatus_t.java,v 1.1 2002/09/29 04:28:16 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnadrmGsgmProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t UnadrmGsgm;

	public UnadrmGsgmProcStatus_t () {
	}
	public UnadrmGsgmProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t UnadrmGsgm) { 
		this.RTCD = RTCD;
		this.UnadrmGsgm = UnadrmGsgm;

	}
}
