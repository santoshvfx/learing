// $Id: BascAddrInfo_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class BascAddrInfo_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t StNbrId;
	public String ASGND_HOUS_NBR_ID;
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm;
	public String CMTY_NM;
	public String STATE_CD;

	public BascAddrInfo_t () {
	}
	public BascAddrInfo_t (com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t StNbrId, String ASGND_HOUS_NBR_ID, com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm, String CMTY_NM, String STATE_CD) { 
		this.StNbrId = StNbrId;
		this.ASGND_HOUS_NBR_ID = ASGND_HOUS_NBR_ID;
		this.StNm = StNm;
		this.CMTY_NM = CMTY_NM;
		this.STATE_CD = STATE_CD;

	}
}
