// $Id: Addr_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class Addr_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t BascAddrInfo;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t UnnbrdAddrIdent;
	public String DESC_ADDR;

	public Addr_t () {
	}
	public Addr_t (com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t BascAddrInfo, com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t SuppAddrInfo, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t UnnbrdAddrIdent, String DESC_ADDR) { 
		this.BascAddrInfo = BascAddrInfo;
		this.SuppAddrInfo = SuppAddrInfo;
		this.UnnbrdAddrIdent = UnnbrdAddrIdent;
		this.DESC_ADDR = DESC_ADDR;

	}
}
