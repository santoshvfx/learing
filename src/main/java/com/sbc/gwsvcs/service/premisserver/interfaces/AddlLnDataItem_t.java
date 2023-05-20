// $Id: AddlLnDataItem_t.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AddlLnDataItem_t implements java.io.Serializable { 
	public String ADDL_TN;
	public char ADDL_LN_ID;

	public AddlLnDataItem_t () {
	}
	public AddlLnDataItem_t (String ADDL_TN, char ADDL_LN_ID) { 
		this.ADDL_TN = ADDL_TN;
		this.ADDL_LN_ID = ADDL_LN_ID;

	}
}
