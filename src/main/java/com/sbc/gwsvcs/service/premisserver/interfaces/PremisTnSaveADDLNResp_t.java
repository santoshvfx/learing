// $Id: PremisTnSaveADDLNResp_t.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisTnSaveADDLNResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnSavePktResp;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t AppTnSaveADDLNRespItem;

	public PremisTnSaveADDLNResp_t () {
	}
	public PremisTnSaveADDLNResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t TnSavePktResp, com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t AppTnSaveADDLNRespItem) { 
		this.Header = Header;
		this.TnSavePktResp = TnSavePktResp;
		this.AppTnSaveADDLNRespItem = AppTnSaveADDLNRespItem;

	}
}
