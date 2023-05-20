// $Id: lufrecordkeys_st.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class lufrecordkeys_st implements java.io.Serializable { 
	public String raiCode;
	public String sagAreaId;
	public String wireCenter;
	public String communityName;
	public String streetDirection;
	public String streetName;
	public String assignedHouseNumberInd;
	public String stNbrFld1;
	public String stNbrFld2;
	public String locValue1;
	public String locValue2;
	public String locValue3;
	public String locValue4;
	public String locValue5;

	public lufrecordkeys_st () {
	}
	public lufrecordkeys_st (String raiCode, String sagAreaId, String wireCenter, String communityName, String streetDirection, String streetName, String assignedHouseNumberInd, String stNbrFld1, String stNbrFld2, String locValue1, String locValue2, String locValue3, String locValue4, String locValue5) { 
		this.raiCode = raiCode;
		this.sagAreaId = sagAreaId;
		this.wireCenter = wireCenter;
		this.communityName = communityName;
		this.streetDirection = streetDirection;
		this.streetName = streetName;
		this.assignedHouseNumberInd = assignedHouseNumberInd;
		this.stNbrFld1 = stNbrFld1;
		this.stNbrFld2 = stNbrFld2;
		this.locValue1 = locValue1;
		this.locValue2 = locValue2;
		this.locValue3 = locValue3;
		this.locValue4 = locValue4;
		this.locValue5 = locValue5;

	}
}
