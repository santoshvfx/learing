// $Id: npaprfx_st.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class npaprfx_st implements java.io.Serializable { 
	public String npa;
	public String nxx;

	public npaprfx_st () {
	}
	public npaprfx_st (String npa, String nxx) { 
		this.npa = npa;
		this.nxx = nxx;

	}
}
