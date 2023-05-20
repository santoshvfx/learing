// $Id: DescNmMenuItem_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class DescNmMenuItem_t implements java.io.Serializable { 
	public String DESC_ADDR;
	public com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t StNbrId;
	public String ASGND_HOUS_NBR_ID;
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm;
	public String CMTY_NM;
	public String SAG_RMK_1_DESC;
	public String SAG_RMK_2_DESC;
	public String SAG_RMK_3_DESC;

	public DescNmMenuItem_t () {
	}
	public DescNmMenuItem_t (String DESC_ADDR, com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t StNbrId, String ASGND_HOUS_NBR_ID, com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm, String CMTY_NM, String SAG_RMK_1_DESC, String SAG_RMK_2_DESC, String SAG_RMK_3_DESC) { 
		this.DESC_ADDR = DESC_ADDR;
		this.StNbrId = StNbrId;
		this.ASGND_HOUS_NBR_ID = ASGND_HOUS_NBR_ID;
		this.StNm = StNm;
		this.CMTY_NM = CMTY_NM;
		this.SAG_RMK_1_DESC = SAG_RMK_1_DESC;
		this.SAG_RMK_2_DESC = SAG_RMK_2_DESC;
		this.SAG_RMK_3_DESC = SAG_RMK_3_DESC;

	}
}
