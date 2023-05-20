// $Id: PrmStNm_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PrmStNm_t implements java.io.Serializable { 
	public String SAD_ST_DRCTL;
	public String SAD_ST_NM;
	public String SAD_THRFR;
	public String SAD_ST_SUFX;

	public PrmStNm_t () {
	}
	public PrmStNm_t (String SAD_ST_DRCTL, String SAD_ST_NM, String SAD_THRFR, String SAD_ST_SUFX) { 
		this.SAD_ST_DRCTL = SAD_ST_DRCTL;
		this.SAD_ST_NM = SAD_ST_NM;
		this.SAD_THRFR = SAD_THRFR;
		this.SAD_ST_SUFX = SAD_ST_SUFX;

	}
}
