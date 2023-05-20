// $Id: PremisAddrMenuResp_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisAddrMenuResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_t AddrMenuPktResp;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t AppPrmRespItem;

	public PremisAddrMenuResp_t () {
	}
	public PremisAddrMenuResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch, com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_t AddrMenuPktResp, com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t AppPrmRespItem) { 
		this.Header = Header;
		this.Scratch = Scratch;
		this.AddrMenuPktResp = AddrMenuPktResp;
		this.AppPrmRespItem = AppPrmRespItem;

	}
}
