// $Id: PremisTnRsrvADDLNResp_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnRsrvADDLNResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnRsrvPktResp;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t AppTnRsrvADDLNRespItem;

	public PremisTnRsrvADDLNResp_t () {
	}
	public PremisTnRsrvADDLNResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnRsrvPktResp, com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t AppTnRsrvADDLNRespItem) { 
		this.Header = Header;
		this.TnRsrvPktResp = TnRsrvPktResp;
		this.AppTnRsrvADDLNRespItem = AppTnRsrvADDLNRespItem;

	}
}
