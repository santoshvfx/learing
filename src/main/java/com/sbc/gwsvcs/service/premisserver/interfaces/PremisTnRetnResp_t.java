// $Id: PremisTnRetnResp_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnRetnResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnRetnPktResp;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_t AppTnRetnRespItem;

	public PremisTnRetnResp_t () {
	}
	public PremisTnRetnResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnRetnPktResp, com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_t AppTnRetnRespItem) { 
		this.Header = Header;
		this.TnRetnPktResp = TnRetnPktResp;
		this.AppTnRetnRespItem = AppTnRetnRespItem;

	}
}
