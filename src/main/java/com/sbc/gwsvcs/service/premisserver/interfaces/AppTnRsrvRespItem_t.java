// $Id: AppTnRsrvRespItem_t.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class AppTnRsrvRespItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t TnRsrvReq;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t TnaFacActnLnItem;

	public AppTnRsrvRespItem_t () {
	}
	public AppTnRsrvRespItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t TnRsrvReq, com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t TnaFacActnLnItem) { 
		this.TnRsrvReq = TnRsrvReq;
		this.TnaFacActnLnItem = TnaFacActnLnItem;

	}
}
