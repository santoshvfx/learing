// $Id: TnaFacActnLnItem_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TnaFacActnLnItem_t implements java.io.Serializable { 
	public String CMTY_NM;
	public String STATE_CD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t TnSucc[];
	public String TEL_FEAT_RMK_1_DESC;
	public String TEL_FEAT_RMK_2_DESC;
	public String TEL_FEAT_RMK_3_DESC;
	public String TEL_FEAT_RMK_4_DESC;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t AddlLnData[];
	public com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t SwngEntyData[];

	public TnaFacActnLnItem_t () {
	}
	public TnaFacActnLnItem_t (String CMTY_NM, String STATE_CD, com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t TnSucc[], String TEL_FEAT_RMK_1_DESC, String TEL_FEAT_RMK_2_DESC, String TEL_FEAT_RMK_3_DESC, String TEL_FEAT_RMK_4_DESC, com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t AddlLnData[], com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t SwngEntyData[]) { 
		this.CMTY_NM = CMTY_NM;
		this.STATE_CD = STATE_CD;
		this.TnSucc = TnSucc;
		this.TEL_FEAT_RMK_1_DESC = TEL_FEAT_RMK_1_DESC;
		this.TEL_FEAT_RMK_2_DESC = TEL_FEAT_RMK_2_DESC;
		this.TEL_FEAT_RMK_3_DESC = TEL_FEAT_RMK_3_DESC;
		this.TEL_FEAT_RMK_4_DESC = TEL_FEAT_RMK_4_DESC;
		this.AddlLnData = AddlLnData;
		this.SwngEntyData = SwngEntyData;

	}
}
