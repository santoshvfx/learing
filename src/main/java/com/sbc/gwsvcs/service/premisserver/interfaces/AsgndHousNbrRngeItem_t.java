// $Id: AsgndHousNbrRngeItem_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AsgndHousNbrRngeItem_t implements java.io.Serializable { 
	public String LOW_ASGND_HOUS_NBR_VALU_ID;
	public String HI_ASGND_HOUS_NBR_VALU_ID;
	public char ASGND_HOUS_NBR_RNGE_IND;
	public String ZC;

	public AsgndHousNbrRngeItem_t () {
	}
	public AsgndHousNbrRngeItem_t (String LOW_ASGND_HOUS_NBR_VALU_ID, String HI_ASGND_HOUS_NBR_VALU_ID, char ASGND_HOUS_NBR_RNGE_IND, String ZC) { 
		this.LOW_ASGND_HOUS_NBR_VALU_ID = LOW_ASGND_HOUS_NBR_VALU_ID;
		this.HI_ASGND_HOUS_NBR_VALU_ID = HI_ASGND_HOUS_NBR_VALU_ID;
		this.ASGND_HOUS_NBR_RNGE_IND = ASGND_HOUS_NBR_RNGE_IND;
		this.ZC = ZC;

	}
}
