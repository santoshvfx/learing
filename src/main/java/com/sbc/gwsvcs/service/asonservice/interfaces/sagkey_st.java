// $Id: sagkey_st.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class sagkey_st implements java.io.Serializable { 
	public String raiCode;
	public String sagAreaId;
	public String alphaNumInd;
	public String addressName;
	public String directional;
	public String highAddrRange;
	public String lowAddrRange;
	public String oddEvenIndicator;
	public String exchange;
	public String centralOffice;
	public String map;
	public String rateBandZone;
	public String zipCode;
	public String npa;
	public String countyAbbrev;
	public String municipality;
	public String filler;

	public sagkey_st () {
	}
	public sagkey_st (String raiCode, String sagAreaId, String alphaNumInd, String addressName, String directional, String highAddrRange, String lowAddrRange, String oddEvenIndicator, String exchange, String centralOffice, String map, String rateBandZone, String zipCode, String npa, String countyAbbrev, String municipality, String filler) { 
		this.raiCode = raiCode;
		this.sagAreaId = sagAreaId;
		this.alphaNumInd = alphaNumInd;
		this.addressName = addressName;
		this.directional = directional;
		this.highAddrRange = highAddrRange;
		this.lowAddrRange = lowAddrRange;
		this.oddEvenIndicator = oddEvenIndicator;
		this.exchange = exchange;
		this.centralOffice = centralOffice;
		this.map = map;
		this.rateBandZone = rateBandZone;
		this.zipCode = zipCode;
		this.npa = npa;
		this.countyAbbrev = countyAbbrev;
		this.municipality = municipality;
		this.filler = filler;

	}
}
