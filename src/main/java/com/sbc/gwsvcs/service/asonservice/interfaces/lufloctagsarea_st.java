// $Id: lufloctagsarea_st.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class lufloctagsarea_st implements java.io.Serializable { 
	public String locTag1;
	public String locTag2;
	public String locTag3;
	public String locTag4;
	public String locTag5;

	public lufloctagsarea_st () {
	}
	public lufloctagsarea_st (String locTag1, String locTag2, String locTag3, String locTag4, String locTag5) { 
		this.locTag1 = locTag1;
		this.locTag2 = locTag2;
		this.locTag3 = locTag3;
		this.locTag4 = locTag4;
		this.locTag5 = locTag5;

	}
}
