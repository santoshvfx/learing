// $Id: ASONSagInqReq.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagInqReq implements java.io.Serializable { 
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
	public String sagAreaId;
	public String sagDirectional;
	public String addressName;
	public String zipCode;
	public String savedSagKey;
	public String savedSagScreenInd;
	public String exactPositioningInd;
	public String sentEndString;
	public String maxAddressReturnLimit;

	public ASONSagInqReq () {
	}
	public ASONSagInqReq (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short requestCode, com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st commandLine, String dateKey, String functionKeyDepressed, String idGroup, String idTerminal, String idTypist, String regionalAreaId, String timeKey, String sagAreaId, String sagDirectional, String addressName, String zipCode, String savedSagKey, String savedSagScreenInd, String exactPositioningInd, String sentEndString, String maxAddressReturnLimit) { 
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
		this.sagAreaId = sagAreaId;
		this.sagDirectional = sagDirectional;
		this.addressName = addressName;
		this.zipCode = zipCode;
		this.savedSagKey = savedSagKey;
		this.savedSagScreenInd = savedSagScreenInd;
		this.exactPositioningInd = exactPositioningInd;
		this.sentEndString = sentEndString;
		this.maxAddressReturnLimit = maxAddressReturnLimit;

	}
}
