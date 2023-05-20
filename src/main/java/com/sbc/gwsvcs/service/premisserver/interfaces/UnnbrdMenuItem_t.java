// $Id: UnnbrdMenuItem_t.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class UnnbrdMenuItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm;
	public String CMTY_NM;
	public String STATE_CD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t AsgndHousNbrRnge[];

	public UnnbrdMenuItem_t () {
	}
	public UnnbrdMenuItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t StNm, String CMTY_NM, String STATE_CD, com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t AsgndHousNbrRnge[]) { 
		this.StNm = StNm;
		this.CMTY_NM = CMTY_NM;
		this.STATE_CD = STATE_CD;
		this.AsgndHousNbrRnge = AsgndHousNbrRnge;

	}
}
