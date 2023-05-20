// $Id: PremisUnnbrdMenuResp_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class PremisUnnbrdMenuResp_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch;
	public com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_t UnnbrdMenuPktResp;
	public com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t AppPrmRespItem;

	public PremisUnnbrdMenuResp_t () {
	}
	public PremisUnnbrdMenuResp_t (com.sbc.gwsvcs.service.premisserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t Scratch, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_t UnnbrdMenuPktResp, com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t AppPrmRespItem) { 
		this.Header = Header;
		this.Scratch = Scratch;
		this.UnnbrdMenuPktResp = UnnbrdMenuPktResp;
		this.AppPrmRespItem = AppPrmRespItem;

	}
}
