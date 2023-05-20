// $Id: UnnbrdAddrIdent_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnnbrdAddrIdent_t implements java.io.Serializable { 
	public String PSTL_RTE_CD;
	public String BOX_CD;
	public String LSTD_NM;
	public String UNNBR_SRCH_STS_IND;
	public char GEO_SEG_REQ_IND;
	public char ASGND_HOUS_NBR_LSTG_IND;

	public UnnbrdAddrIdent_t () {
	}
	public UnnbrdAddrIdent_t (String PSTL_RTE_CD, String BOX_CD, String LSTD_NM, String UNNBR_SRCH_STS_IND, char GEO_SEG_REQ_IND, char ASGND_HOUS_NBR_LSTG_IND) { 
		this.PSTL_RTE_CD = PSTL_RTE_CD;
		this.BOX_CD = BOX_CD;
		this.LSTD_NM = LSTD_NM;
		this.UNNBR_SRCH_STS_IND = UNNBR_SRCH_STS_IND;
		this.GEO_SEG_REQ_IND = GEO_SEG_REQ_IND;
		this.ASGND_HOUS_NBR_LSTG_IND = ASGND_HOUS_NBR_LSTG_IND;

	}
}
