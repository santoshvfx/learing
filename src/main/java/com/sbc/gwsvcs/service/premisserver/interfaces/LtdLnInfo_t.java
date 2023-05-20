// $Id: LtdLnInfo_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class LtdLnInfo_t implements java.io.Serializable { 
	public String LN_STS_ID;
	public String LSTD_NM;

	public LtdLnInfo_t () {
	}
	public LtdLnInfo_t (String LN_STS_ID, String LSTD_NM) { 
		this.LN_STS_ID = LN_STS_ID;
		this.LSTD_NM = LSTD_NM;

	}
}
