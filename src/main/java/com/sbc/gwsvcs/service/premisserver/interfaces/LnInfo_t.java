// $Id: LnInfo_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class LnInfo_t implements java.io.Serializable { 
	public char LN_ID;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t SuppLnInfo;

	public LnInfo_t () {
	}
	public LnInfo_t (char LN_ID, com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t SuppLnInfo) { 
		this.LN_ID = LN_ID;
		this.SuppLnInfo = SuppLnInfo;

	}
}
