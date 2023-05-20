// $Id: SuppLnInfoItem_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SuppLnInfoItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t SuppLnInfo;

	public SuppLnInfoItem_t () {
	}
	public SuppLnInfoItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t SuppLnInfo) { 
		this.SuppLnInfo = SuppLnInfo;

	}
}
