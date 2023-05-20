// $Id: AddrRnge_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AddrRnge_t implements java.io.Serializable { 
	public String LOW_HOUS_NBR_VALU_ID;
	public String HI_HOUS_NBR_VALU_ID;
	public char RNGE_IND;

	public AddrRnge_t () {
	}
	public AddrRnge_t (String LOW_HOUS_NBR_VALU_ID, String HI_HOUS_NBR_VALU_ID, char RNGE_IND) { 
		this.LOW_HOUS_NBR_VALU_ID = LOW_HOUS_NBR_VALU_ID;
		this.HI_HOUS_NBR_VALU_ID = HI_HOUS_NBR_VALU_ID;
		this.RNGE_IND = RNGE_IND;

	}
}
