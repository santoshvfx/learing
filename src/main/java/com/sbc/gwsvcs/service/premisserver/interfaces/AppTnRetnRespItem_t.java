// $Id: AppTnRetnRespItem_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AppTnRetnRespItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_t TnRetnReq;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t TnaFacActnLnItem;

	public AppTnRetnRespItem_t () {
	}
	public AppTnRetnRespItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_t TnRetnReq, com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t TnaFacActnLnItem) { 
		this.TnRetnReq = TnRetnReq;
		this.TnaFacActnLnItem = TnaFacActnLnItem;

	}
}
