// $Id: sagbypassarea_st.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class sagbypassarea_st implements java.io.Serializable { 
	public String bpZipCode;
	public String bpExchange;
	public String bpCounty;
	public String bpCommunity;
	public char XDRalignFiller;

	public sagbypassarea_st () {
	}
	public sagbypassarea_st (String bpZipCode, String bpExchange, String bpCounty, String bpCommunity, char XDRalignFiller) { 
		this.bpZipCode = bpZipCode;
		this.bpExchange = bpExchange;
		this.bpCounty = bpCounty;
		this.bpCommunity = bpCommunity;
		this.XDRalignFiller = XDRalignFiller;

	}
}
