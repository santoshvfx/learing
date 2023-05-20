// $Id: SagInfo_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SagInfo_t implements java.io.Serializable { 
	public String EXCH_ID;
	public String WC_CD;
	public String RT_ZONE_CD;
	public String TAX_AREA_CD;
	public String ZC;
	public String NPA_LST[];
	public String TEL_FEAT_ID;
	public char HGRP_AVAIL_IND;
	public String BUS_OFC_CD;
	public String DIR_CD;
	public String CO_ID;
	public String LATA_PREMIS;
	public String TERMN_TRAF_AREA_CD;
	public char ATTN[];
	public String GEO_SEG_RMK_DESC;
	public String SAG_RMK_1_DESC;
	public String SAG_RMK_2_DESC;
	public String SAG_RMK_3_DESC;

	public SagInfo_t () {
	}
	public SagInfo_t (String EXCH_ID, String WC_CD, String RT_ZONE_CD, String TAX_AREA_CD, String ZC, String NPA_LST[], String TEL_FEAT_ID, char HGRP_AVAIL_IND, String BUS_OFC_CD, String DIR_CD, String CO_ID, String LATA_PREMIS, String TERMN_TRAF_AREA_CD, char ATTN[], String GEO_SEG_RMK_DESC, String SAG_RMK_1_DESC, String SAG_RMK_2_DESC, String SAG_RMK_3_DESC) { 
		this.EXCH_ID = EXCH_ID;
		this.WC_CD = WC_CD;
		this.RT_ZONE_CD = RT_ZONE_CD;
		this.TAX_AREA_CD = TAX_AREA_CD;
		this.ZC = ZC;
		this.NPA_LST = NPA_LST;
		this.TEL_FEAT_ID = TEL_FEAT_ID;
		this.HGRP_AVAIL_IND = HGRP_AVAIL_IND;
		this.BUS_OFC_CD = BUS_OFC_CD;
		this.DIR_CD = DIR_CD;
		this.CO_ID = CO_ID;
		this.LATA_PREMIS = LATA_PREMIS;
		this.TERMN_TRAF_AREA_CD = TERMN_TRAF_AREA_CD;
		this.ATTN = ATTN;
		this.GEO_SEG_RMK_DESC = GEO_SEG_RMK_DESC;
		this.SAG_RMK_1_DESC = SAG_RMK_1_DESC;
		this.SAG_RMK_2_DESC = SAG_RMK_2_DESC;
		this.SAG_RMK_3_DESC = SAG_RMK_3_DESC;

	}
}
