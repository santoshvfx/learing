// $Id: ASONSagValReq.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagValReq implements java.io.Serializable { 
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
	public String addressName;
	public String zipCode;
	public String community;
	public String holdSagKey;
	public String sentEndString;

	public ASONSagValReq () {
	}
	public ASONSagValReq (com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st tagInformation, short requestCode, com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st commandLine, String dateKey, String functionKeyDepressed, String idGroup, String idTerminal, String idTypist, String regionalAreaId, String timeKey, String addressName, String zipCode, String community, String holdSagKey, String sentEndString) { 
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
		this.addressName = addressName;
		this.zipCode = zipCode;
		this.community = community;
		this.holdSagKey = holdSagKey;
		this.sentEndString = sentEndString;

	}
}
