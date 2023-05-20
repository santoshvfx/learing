// $Id: FacActnLnItem_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class FacActnLnItem_t implements java.io.Serializable { 
	public String SAGA;
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr;
	public String ALT_CMTY_1_NM;
	public String ALT_CMTY_2_NM;
	public String SPL_INSTR;
	public String ALT_ADDR_NM;
	public String DESC_ADDR;
	public String LOC_STD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t SagInfo;
	public char CMTY_NM_RQRD_IND;
	public char STATE_NM_RQRD_IND;
	public String TEL_FEAT_RMK_1_DESC;
	public String TEL_FEAT_RMK_2_DESC;
	public String TEL_FEAT_RMK_3_DESC;
	public String TEL_FEAT_RMK_4_DESC;
	public com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t LnData[];
	public com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t SwngEntyData[];
	public com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t RmkData[];

	public FacActnLnItem_t () {
	}
	public FacActnLnItem_t (String SAGA, com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t PrmAddr, String ALT_CMTY_1_NM, String ALT_CMTY_2_NM, String SPL_INSTR, String ALT_ADDR_NM, String DESC_ADDR, String LOC_STD, com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t SagInfo, char CMTY_NM_RQRD_IND, char STATE_NM_RQRD_IND, String TEL_FEAT_RMK_1_DESC, String TEL_FEAT_RMK_2_DESC, String TEL_FEAT_RMK_3_DESC, String TEL_FEAT_RMK_4_DESC, com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t LnData[], com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t SwngEntyData[], com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t RmkData[]) { 
		this.SAGA = SAGA;
		this.PrmAddr = PrmAddr;
		this.ALT_CMTY_1_NM = ALT_CMTY_1_NM;
		this.ALT_CMTY_2_NM = ALT_CMTY_2_NM;
		this.SPL_INSTR = SPL_INSTR;
		this.ALT_ADDR_NM = ALT_ADDR_NM;
		this.DESC_ADDR = DESC_ADDR;
		this.LOC_STD = LOC_STD;
		this.SagInfo = SagInfo;
		this.CMTY_NM_RQRD_IND = CMTY_NM_RQRD_IND;
		this.STATE_NM_RQRD_IND = STATE_NM_RQRD_IND;
		this.TEL_FEAT_RMK_1_DESC = TEL_FEAT_RMK_1_DESC;
		this.TEL_FEAT_RMK_2_DESC = TEL_FEAT_RMK_2_DESC;
		this.TEL_FEAT_RMK_3_DESC = TEL_FEAT_RMK_3_DESC;
		this.TEL_FEAT_RMK_4_DESC = TEL_FEAT_RMK_4_DESC;
		this.LnData = LnData;
		this.SwngEntyData = SwngEntyData;
		this.RmkData = RmkData;

	}
}
