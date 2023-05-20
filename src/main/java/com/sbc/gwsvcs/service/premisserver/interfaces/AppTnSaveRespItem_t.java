// $Id: AppTnSaveRespItem_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AppTnSaveRespItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_t TnSaveReq;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t TnaFacActnLnItem;

	public AppTnSaveRespItem_t () {
	}
	public AppTnSaveRespItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_t TnSaveReq, com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t TnaFacActnLnItem) { 
		this.TnSaveReq = TnSaveReq;
		this.TnaFacActnLnItem = TnaFacActnLnItem;

	}
}
