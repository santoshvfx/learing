// $Id: ASONSagValidDescErr.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagValidDescErr implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st comReplyHdr1;
	public com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st comReplyHdr2;
	public com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st comReplyHdr3;
	public String descAddrRmk1;
	public String descAddrRmk2;
	public String descAddrRmk3;
	public String descAddrRmk4;

	public ASONSagValidDescErr () {
	}
	public ASONSagValidDescErr (com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st comReplyHdr1, com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st comReplyHdr2, com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st comReplyHdr3, String descAddrRmk1, String descAddrRmk2, String descAddrRmk3, String descAddrRmk4) { 
		this.comReplyHdr1 = comReplyHdr1;
		this.comReplyHdr2 = comReplyHdr2;
		this.comReplyHdr3 = comReplyHdr3;
		this.descAddrRmk1 = descAddrRmk1;
		this.descAddrRmk2 = descAddrRmk2;
		this.descAddrRmk3 = descAddrRmk3;
		this.descAddrRmk4 = descAddrRmk4;

	}
}
