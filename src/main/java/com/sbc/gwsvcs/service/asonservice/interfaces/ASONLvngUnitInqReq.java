// $Id: ASONLvngUnitInqReq.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONLvngUnitInqReq implements java.io.Serializable { 
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
	public String savedLuKey;
	public String raiCode;
	public String sagAreaId;
	public String wireCenter;
	public String communityName;
	public String streetDirection;
	public String streetName1;
	public String streetName2_40;
	public String assignedHouseNumberInd;
	public String stNbrFld1;
	public String stNbrFld2;
	public String locLocValue1;
	public String locLocValue2;
	public String locLocValue3;
	public String locLocValue4;
	public String locLocValue5;
	public String custTN;
	public String custName;
	public String custAddress;
	public String streetRangeLow;
	public String streetRangeHigh;
	public String oddEvenInd;
	public String exchange;
	public String centralOffice;
	public String map;
	public String rateBandZone;
	public String zipCode;
	public String npa;
	public String skRaiCode;
	public String skSaiCode;
	public String skAlphaNumInd;
	public String skAddressName;
	public String skDirectional;
	public String skHighRange;
	public String skLowRange;
	public String skOddEvenIndicator;
	public String skExchange;
	public String skSagCO;
	public String skMap;
	public String skRateBandZone;
	public String skZipCode;
	public String skNpa;
	public String skCountyAbbrev;
	public String skMunicipality;
	public String sentEndString;
	public String maxUnitReturnLimit;

	public ASONLvngUnitInqReq () {
	}
	public ASONLvngUnitInqReq (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short requestCode, com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st commandLine, String dateKey, String functionKeyDepressed, String idGroup, String idTerminal, String idTypist, String regionalAreaId, String timeKey, String savedLuKey, String raiCode, String sagAreaId, String wireCenter, String communityName, String streetDirection, String streetName1, String streetName2_40, String assignedHouseNumberInd, String stNbrFld1, String stNbrFld2, String locLocValue1, String locLocValue2, String locLocValue3, String locLocValue4, String locLocValue5, String custTN, String custName, String custAddress, String streetRangeLow, String streetRangeHigh, String oddEvenInd, String exchange, String centralOffice, String map, String rateBandZone, String zipCode, String npa, String skRaiCode, String skSaiCode, String skAlphaNumInd, String skAddressName, String skDirectional, String skHighRange, String skLowRange, String skOddEvenIndicator, String skExchange, String skSagCO, String skMap, String skRateBandZone, String skZipCode, String skNpa, String skCountyAbbrev, String skMunicipality, String sentEndString, String maxUnitReturnLimit) { 
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
		this.savedLuKey = savedLuKey;
		this.raiCode = raiCode;
		this.sagAreaId = sagAreaId;
		this.wireCenter = wireCenter;
		this.communityName = communityName;
		this.streetDirection = streetDirection;
		this.streetName1 = streetName1;
		this.streetName2_40 = streetName2_40;
		this.assignedHouseNumberInd = assignedHouseNumberInd;
		this.stNbrFld1 = stNbrFld1;
		this.stNbrFld2 = stNbrFld2;
		this.locLocValue1 = locLocValue1;
		this.locLocValue2 = locLocValue2;
		this.locLocValue3 = locLocValue3;
		this.locLocValue4 = locLocValue4;
		this.locLocValue5 = locLocValue5;
		this.custTN = custTN;
		this.custName = custName;
		this.custAddress = custAddress;
		this.streetRangeLow = streetRangeLow;
		this.streetRangeHigh = streetRangeHigh;
		this.oddEvenInd = oddEvenInd;
		this.exchange = exchange;
		this.centralOffice = centralOffice;
		this.map = map;
		this.rateBandZone = rateBandZone;
		this.zipCode = zipCode;
		this.npa = npa;
		this.skRaiCode = skRaiCode;
		this.skSaiCode = skSaiCode;
		this.skAlphaNumInd = skAlphaNumInd;
		this.skAddressName = skAddressName;
		this.skDirectional = skDirectional;
		this.skHighRange = skHighRange;
		this.skLowRange = skLowRange;
		this.skOddEvenIndicator = skOddEvenIndicator;
		this.skExchange = skExchange;
		this.skSagCO = skSagCO;
		this.skMap = skMap;
		this.skRateBandZone = skRateBandZone;
		this.skZipCode = skZipCode;
		this.skNpa = skNpa;
		this.skCountyAbbrev = skCountyAbbrev;
		this.skMunicipality = skMunicipality;
		this.sentEndString = sentEndString;
		this.maxUnitReturnLimit = maxUnitReturnLimit;

	}
}
