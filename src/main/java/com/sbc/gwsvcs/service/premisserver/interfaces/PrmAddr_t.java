// $Id: PrmAddr_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PrmAddr_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t BascAddrInfo;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo;

	public PrmAddr_t () {
	}
	public PrmAddr_t (com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t BascAddrInfo, com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo) { 
		this.BascAddrInfo = BascAddrInfo;
		this.SuppAddrInfo = SuppAddrInfo;

	}
}
