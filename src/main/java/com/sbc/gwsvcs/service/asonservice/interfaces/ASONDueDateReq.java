// $Id: ASONDueDateReq.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONDueDateReq implements java.io.Serializable { 
	public short requestCode;
	public String advisoryMsg;
	public String hlpWhen;
	public String sagDesired;
	public char hyphen;
	public String resOrBus;
	public String filler1;
	public String dateKey;
	public String functionKeyDepressed;
	public String helpCrossRefKey;
	public String helpTextKey;
	public String helpCursorRow;
	public String idGroup;
	public String idTerminal;
	public String idTypist;
	public String timeKey;

	public ASONDueDateReq () {
	}
	public ASONDueDateReq (short requestCode, String advisoryMsg, String hlpWhen, String sagDesired, char hyphen, String resOrBus, String filler1, String dateKey, String functionKeyDepressed, String helpCrossRefKey, String helpTextKey, String helpCursorRow, String idGroup, String idTerminal, String idTypist, String timeKey) { 
		this.requestCode = requestCode;
		this.advisoryMsg = advisoryMsg;
		this.hlpWhen = hlpWhen;
		this.sagDesired = sagDesired;
		this.hyphen = hyphen;
		this.resOrBus = resOrBus;
		this.filler1 = filler1;
		this.dateKey = dateKey;
		this.functionKeyDepressed = functionKeyDepressed;
		this.helpCrossRefKey = helpCrossRefKey;
		this.helpTextKey = helpTextKey;
		this.helpCursorRow = helpCursorRow;
		this.idGroup = idGroup;
		this.idTerminal = idTerminal;
		this.idTypist = idTypist;
		this.timeKey = timeKey;

	}
}
