// $Id: TnRsrvReq_t.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnRsrvReq_t implements java.io.Serializable { 
	public String SAGA;
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr;
	public String GEO_SEG_ID;
	public String BLG_DT;
	public String NPA_CD;
	public String PRFX_CD;
	public String TN_CTGY_CD;
	public int TN_REQ_QTY;
	public char LN_ID;

	public TnRsrvReq_t () {
	}
	public TnRsrvReq_t (String SAGA, com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr, String GEO_SEG_ID, String BLG_DT, String NPA_CD, String PRFX_CD, String TN_CTGY_CD, int TN_REQ_QTY, char LN_ID) { 
		this.SAGA = SAGA;
		this.PrmAddr = PrmAddr;
		this.GEO_SEG_ID = GEO_SEG_ID;
		this.BLG_DT = BLG_DT;
		this.NPA_CD = NPA_CD;
		this.PRFX_CD = PRFX_CD;
		this.TN_CTGY_CD = TN_CTGY_CD;
		this.TN_REQ_QTY = TN_REQ_QTY;
		this.LN_ID = LN_ID;

	}
}
