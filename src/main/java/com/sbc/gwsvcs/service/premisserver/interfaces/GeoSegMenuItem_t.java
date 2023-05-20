// $Id: GeoSegMenuItem_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class GeoSegMenuItem_t implements java.io.Serializable { 
	public String GEO_SEG_ID;
	public String LOW_ASGND_HOUS_NBR_VALU_ID;
	public String HI_ASGND_HOUS_NBR_VALU_ID;
	public char ASGND_HOUS_NBR_RNGE_IND;
	public String CMTY_NM;
	public String STATE_CD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t SagInfo;

	public GeoSegMenuItem_t () {
	}
	public GeoSegMenuItem_t (String GEO_SEG_ID, String LOW_ASGND_HOUS_NBR_VALU_ID, String HI_ASGND_HOUS_NBR_VALU_ID, char ASGND_HOUS_NBR_RNGE_IND, String CMTY_NM, String STATE_CD, com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t SagInfo) { 
		this.GEO_SEG_ID = GEO_SEG_ID;
		this.LOW_ASGND_HOUS_NBR_VALU_ID = LOW_ASGND_HOUS_NBR_VALU_ID;
		this.HI_ASGND_HOUS_NBR_VALU_ID = HI_ASGND_HOUS_NBR_VALU_ID;
		this.ASGND_HOUS_NBR_RNGE_IND = ASGND_HOUS_NBR_RNGE_IND;
		this.CMTY_NM = CMTY_NM;
		this.STATE_CD = STATE_CD;
		this.SagInfo = SagInfo;

	}
}
