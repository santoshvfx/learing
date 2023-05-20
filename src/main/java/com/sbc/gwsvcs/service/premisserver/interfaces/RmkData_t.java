// $Id: RmkData_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class RmkData_t implements java.io.Serializable { 
	public String BASC_ADDR_RMK;

	public RmkData_t () {
	}
	public RmkData_t (String BASC_ADDR_RMK) { 
		this.BASC_ADDR_RMK = BASC_ADDR_RMK;

	}
}
