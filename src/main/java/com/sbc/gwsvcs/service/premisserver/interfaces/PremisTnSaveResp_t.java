// $Id: PremisTnSaveResp_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnSaveResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnSavePktResp;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t AppTnSaveRespItem;

	public PremisTnSaveResp_t () {
	}
	public PremisTnSaveResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnSavePktResp, com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t AppTnSaveRespItem) { 
		this.Header = Header;
		this.TnSavePktResp = TnSavePktResp;
		this.AppTnSaveRespItem = AppTnSaveRespItem;

	}
}
