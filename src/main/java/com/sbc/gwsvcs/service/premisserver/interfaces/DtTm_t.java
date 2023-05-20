// $Id: DtTm_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class DtTm_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t DT;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Tm_t TM;
	public String TM_OST;

	public DtTm_t () {
	}
	public DtTm_t (com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t DT, com.sbc.gwsvcs.service.premisserver.interfaces.Tm_t TM, String TM_OST) { 
		this.DT = DT;
		this.TM = TM;
		this.TM_OST = TM_OST;

	}
}
