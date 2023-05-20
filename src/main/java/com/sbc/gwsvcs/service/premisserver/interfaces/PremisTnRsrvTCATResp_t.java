// $Id: PremisTnRsrvTCATResp_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnRsrvTCATResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnRsrvTCATPktResp;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t AppTnRsrvRespItem;

	public PremisTnRsrvTCATResp_t () {
	}
	public PremisTnRsrvTCATResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnRsrvTCATPktResp, com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t AppTnRsrvRespItem) { 
		this.Header = Header;
		this.TnRsrvTCATPktResp = TnRsrvTCATPktResp;
		this.AppTnRsrvRespItem = AppTnRsrvRespItem;

	}
}
