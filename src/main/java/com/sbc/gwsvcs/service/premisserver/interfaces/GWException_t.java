// $Id: GWException_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class GWException_t implements java.io.Serializable { 
	public int ERR_CD;
	public String ERR_TX;

	public GWException_t () {
	}
	public GWException_t (int ERR_CD, String ERR_TX) { 
		this.ERR_CD = ERR_CD;
		this.ERR_TX = ERR_TX;

	}
}
