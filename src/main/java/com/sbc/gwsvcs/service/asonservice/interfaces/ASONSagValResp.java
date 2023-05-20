// $Id: ASONSagValResp.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagValResp implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation;
	public short replyCode;
	public String advisoryMsg;
	public String raiCode;
	public String sagAreaId;
	public String alphaNumInd;
	public String addressName;
	public String directional;
	public String highAddrRange;
	public String lowAddrRange;
	public String oddEvenIndicator;
	public String centralOffice;
	public String exchange;
	public String map;
	public String rateBandZone;
	public String busRateBand;
	public String zipCode;
	public String npa;
	public String countyAbbrev;
	public String municipality;
	public String wireCenter;
	public String community;
	public String state;
	public String editIndicators;
	public String lastAssignedHouseNumUsed;
	public String cityAbbreviation;
	public String populateCommNameInd;
	public String alternateAddressInd;
	public String lfacsDupAddressInd;
	public String equipmentType;
	public String analogOrDigitalType;
	public String tar;
	public String remoteOrHostType;
	public String e911Sur;
	public String e911Exempt;
	public String e911Nrg;
	public String operSur4Ind;
	public String operSur16Ind;
	public String matchInd;
	public com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_st npaPrfxArray[];
	public String sendEndString;

	public ASONSagValResp () {
	}
	public ASONSagValResp (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short replyCode, String advisoryMsg, String raiCode, String sagAreaId, String alphaNumInd, String addressName, String directional, String highAddrRange, String lowAddrRange, String oddEvenIndicator, String centralOffice, String exchange, String map, String rateBandZone, String busRateBand, String zipCode, String npa, String countyAbbrev, String municipality, String wireCenter, String community, String state, String editIndicators, String lastAssignedHouseNumUsed, String cityAbbreviation, String populateCommNameInd, String alternateAddressInd, String lfacsDupAddressInd, String equipmentType, String analogOrDigitalType, String tar, String remoteOrHostType, String e911Sur, String e911Exempt, String e911Nrg, String operSur4Ind, String operSur16Ind, String matchInd, com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_st npaPrfxArray[], String sendEndString) { 
		this.tagInformation = tagInformation;
		this.replyCode = replyCode;
		this.advisoryMsg = advisoryMsg;
		this.raiCode = raiCode;
		this.sagAreaId = sagAreaId;
		this.alphaNumInd = alphaNumInd;
		this.addressName = addressName;
		this.directional = directional;
		this.highAddrRange = highAddrRange;
		this.lowAddrRange = lowAddrRange;
		this.oddEvenIndicator = oddEvenIndicator;
		this.centralOffice = centralOffice;
		this.exchange = exchange;
		this.map = map;
		this.rateBandZone = rateBandZone;
		this.busRateBand = busRateBand;
		this.zipCode = zipCode;
		this.npa = npa;
		this.countyAbbrev = countyAbbrev;
		this.municipality = municipality;
		this.wireCenter = wireCenter;
		this.community = community;
		this.state = state;
		this.editIndicators = editIndicators;
		this.lastAssignedHouseNumUsed = lastAssignedHouseNumUsed;
		this.cityAbbreviation = cityAbbreviation;
		this.populateCommNameInd = populateCommNameInd;
		this.alternateAddressInd = alternateAddressInd;
		this.lfacsDupAddressInd = lfacsDupAddressInd;
		this.equipmentType = equipmentType;
		this.analogOrDigitalType = analogOrDigitalType;
		this.tar = tar;
		this.remoteOrHostType = remoteOrHostType;
		this.e911Sur = e911Sur;
		this.e911Exempt = e911Exempt;
		this.e911Nrg = e911Nrg;
		this.operSur4Ind = operSur4Ind;
		this.operSur16Ind = operSur16Ind;
		this.matchInd = matchInd;
		this.npaPrfxArray = npaPrfxArray;
		this.sendEndString = sendEndString;

	}
}
