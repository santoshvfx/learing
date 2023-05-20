// $Id: Tm_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class Tm_t implements java.io.Serializable { 
	public String HR;
	public String MTE;
	public String SECS;

	public Tm_t () {
	}
	public Tm_t (String HR, String MTE, String SECS) { 
		this.HR = HR;
		this.MTE = MTE;
		this.SECS = SECS;

	}
}
