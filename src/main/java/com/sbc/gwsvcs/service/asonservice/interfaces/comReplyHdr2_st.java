// $Id: comReplyHdr2_st.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class comReplyHdr2_st implements java.io.Serializable { 
	public String addressName;
	public String community;
	public String zipCode;
	public char descriptiveAddrInd;

	public comReplyHdr2_st () {
	}
	public comReplyHdr2_st (String addressName, String community, String zipCode, char descriptiveAddrInd) { 
		this.addressName = addressName;
		this.community = community;
		this.zipCode = zipCode;
		this.descriptiveAddrInd = descriptiveAddrInd;

	}
}
