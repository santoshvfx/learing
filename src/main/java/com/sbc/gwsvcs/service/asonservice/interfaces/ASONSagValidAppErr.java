// $Id: ASONSagValidAppErr.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagValidAppErr implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st comReplyHdr1;
	public com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st comReplyHdr2;

	public ASONSagValidAppErr () {
	}
	public ASONSagValidAppErr (com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st comReplyHdr1, com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st comReplyHdr2) { 
		this.comReplyHdr1 = comReplyHdr1;
		this.comReplyHdr2 = comReplyHdr2;

	}
}
