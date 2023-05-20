// $Id: SuppAddrMenuItem_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SuppAddrMenuItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_t SuppLnInfoItem[];

	public SuppAddrMenuItem_t () {
	}
	public SuppAddrMenuItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo, com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_t SuppLnInfoItem[]) { 
		this.SuppAddrInfo = SuppAddrInfo;
		this.SuppLnInfoItem = SuppLnInfoItem;

	}
}
