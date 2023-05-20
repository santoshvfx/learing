// $Id: SuppAddrInfo_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SuppAddrInfo_t implements java.io.Serializable { 
	public String STRUCT_ID;
	public String STRUCT_TYPE;
	public String ELEV_ID;
	public String ELEV_TYPE;
	public String UNIT_ID;
	public String UNIT_TYPE;

	public SuppAddrInfo_t () {
	}
	public SuppAddrInfo_t (String STRUCT_ID, String STRUCT_TYPE, String ELEV_ID, String ELEV_TYPE, String UNIT_ID, String UNIT_TYPE) { 
		this.STRUCT_ID = STRUCT_ID;
		this.STRUCT_TYPE = STRUCT_TYPE;
		this.ELEV_ID = ELEV_ID;
		this.ELEV_TYPE = ELEV_TYPE;
		this.UNIT_ID = UNIT_ID;
		this.UNIT_TYPE = UNIT_TYPE;

	}
}
