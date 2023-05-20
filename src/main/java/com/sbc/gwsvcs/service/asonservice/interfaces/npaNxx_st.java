// $Id: npaNxx_st.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class npaNxx_st implements java.io.Serializable { 
	public String npa;
	public String nxx;

	public npaNxx_st () {
	}
	public npaNxx_st (String npa, String nxx) { 
		this.npa = npa;
		this.nxx = nxx;

	}
}
