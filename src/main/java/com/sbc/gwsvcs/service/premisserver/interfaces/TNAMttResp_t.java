// $Id: TNAMttResp_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TNAMttResp_t implements java.io.Serializable { 
	public String WC_CD;
	public String TERMN_TRAF_AREA_CD;
	public String TN_LIST_2_NBR;
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t TN;
	public String TN_CTGY_CD;
	public String ADDR_TX;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t SO_DDT;
	public String SO_3_NBR;
	public String COS;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch;

	public TNAMttResp_t () {
	}
	public TNAMttResp_t (String WC_CD, String TERMN_TRAF_AREA_CD, String TN_LIST_2_NBR, com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t TN, String TN_CTGY_CD, String ADDR_TX, com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t SO_DDT, String SO_3_NBR, String COS, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch) { 
		this.WC_CD = WC_CD;
		this.TERMN_TRAF_AREA_CD = TERMN_TRAF_AREA_CD;
		this.TN_LIST_2_NBR = TN_LIST_2_NBR;
		this.TN = TN;
		this.TN_CTGY_CD = TN_CTGY_CD;
		this.ADDR_TX = ADDR_TX;
		this.SO_DDT = SO_DDT;
		this.SO_3_NBR = SO_3_NBR;
		this.COS = COS;
		this.Scratch = Scratch;

	}
}
