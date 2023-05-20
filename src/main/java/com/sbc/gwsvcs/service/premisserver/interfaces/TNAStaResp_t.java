// $Id: TNAStaResp_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TNAStaResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t TN;
	public String WC_CD;
	public String TERMN_TRAF_AREA_CD;
	public String TN_LIST_2_NBR;
	public String TN_STS_CD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t TN_STS_2_DT;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t SO_DDT;
	public String SO_3_NBR;
	public String COS;
	public String ADDR_TX;
	public String HTG_TN_2_ID[];
	public com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch;

	public TNAStaResp_t () {
	}
	public TNAStaResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t TN, String WC_CD, String TERMN_TRAF_AREA_CD, String TN_LIST_2_NBR, String TN_STS_CD, com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t TN_STS_2_DT, com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t SO_DDT, String SO_3_NBR, String COS, String ADDR_TX, String HTG_TN_2_ID[], com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch) { 
		this.TN = TN;
		this.WC_CD = WC_CD;
		this.TERMN_TRAF_AREA_CD = TERMN_TRAF_AREA_CD;
		this.TN_LIST_2_NBR = TN_LIST_2_NBR;
		this.TN_STS_CD = TN_STS_CD;
		this.TN_STS_2_DT = TN_STS_2_DT;
		this.SO_DDT = SO_DDT;
		this.SO_3_NBR = SO_3_NBR;
		this.COS = COS;
		this.ADDR_TX = ADDR_TX;
		this.HTG_TN_2_ID = HTG_TN_2_ID;
		this.Scratch = Scratch;

	}
}
