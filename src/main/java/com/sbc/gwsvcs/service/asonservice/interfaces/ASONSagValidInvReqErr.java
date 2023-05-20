// $Id: ASONSagValidInvReqErr.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagValidInvReqErr implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st comReplyHdr1;

	public ASONSagValidInvReqErr () {
	}
	public ASONSagValidInvReqErr (com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st comReplyHdr1) { 
		this.comReplyHdr1 = comReplyHdr1;

	}
}
