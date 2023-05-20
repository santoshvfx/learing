// $Id: AppPrmRespItem_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AppPrmRespItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t CntlData;
	public short FAC_ACTN_LN_CNT;
	public com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t FacActnLn;

	public AppPrmRespItem_t () {
	}
	public AppPrmRespItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t CntlData, short FAC_ACTN_LN_CNT, com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t FacActnLn) { 
		this.CntlData = CntlData;
		this.FAC_ACTN_LN_CNT = FAC_ACTN_LN_CNT;
		this.FacActnLn = FacActnLn;

	}
}
