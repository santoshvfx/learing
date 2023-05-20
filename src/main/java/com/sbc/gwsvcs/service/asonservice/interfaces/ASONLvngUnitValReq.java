// $Id: ASONLvngUnitValReq.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONLvngUnitValReq implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation;
	public short requestCode;
	public com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st commandLine;
	public String dateKey;
	public String functionKeyDepressed;
	public String idGroup;
	public String idTerminal;
	public String idTypist;
	public String regionalAreaId;
	public String timeKey;
	public String raiCode;
	public String sagAreaId;
	public String wireCenter;
	public String communityName;
	public String streetDirection;
	public String streetName;
	public String assignedHouseNumberInd;
	public String stNbrFld1;
	public String stNbrFld2;
	public String loc1;
	public String loc2;
	public String loc3;
	public String loc4;
	public String loc5;
	public String locTag1;
	public String locTag2;
	public String locTag3;
	public String locTag4;
	public String locTag5;
	public String zipCode;
	public String sentEndString;

	public ASONLvngUnitValReq () {
	}
	public ASONLvngUnitValReq (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short requestCode, com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st commandLine, String dateKey, String functionKeyDepressed, String idGroup, String idTerminal, String idTypist, String regionalAreaId, String timeKey, String raiCode, String sagAreaId, String wireCenter, String communityName, String streetDirection, String streetName, String assignedHouseNumberInd, String stNbrFld1, String stNbrFld2, String loc1, String loc2, String loc3, String loc4, String loc5, String locTag1, String locTag2, String locTag3, String locTag4, String locTag5, String zipCode, String sentEndString) { 
		this.tagInformation = tagInformation;
		this.requestCode = requestCode;
		this.commandLine = commandLine;
		this.dateKey = dateKey;
		this.functionKeyDepressed = functionKeyDepressed;
		this.idGroup = idGroup;
		this.idTerminal = idTerminal;
		this.idTypist = idTypist;
		this.regionalAreaId = regionalAreaId;
		this.timeKey = timeKey;
		this.raiCode = raiCode;
		this.sagAreaId = sagAreaId;
		this.wireCenter = wireCenter;
		this.communityName = communityName;
		this.streetDirection = streetDirection;
		this.streetName = streetName;
		this.assignedHouseNumberInd = assignedHouseNumberInd;
		this.stNbrFld1 = stNbrFld1;
		this.stNbrFld2 = stNbrFld2;
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.loc3 = loc3;
		this.loc4 = loc4;
		this.loc5 = loc5;
		this.locTag1 = locTag1;
		this.locTag2 = locTag2;
		this.locTag3 = locTag3;
		this.locTag4 = locTag4;
		this.locTag5 = locTag5;
		this.zipCode = zipCode;
		this.sentEndString = sentEndString;

	}
}
