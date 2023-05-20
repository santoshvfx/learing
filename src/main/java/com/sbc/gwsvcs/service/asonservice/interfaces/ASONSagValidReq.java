// $Id: ASONSagValidReq.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class ASONSagValidReq implements java.io.Serializable { 
	public short requestCode;
	public String MsgLength;
	public String IdGroup;
	public String IdTypist;
	public String IdSystem;
	public String RequestDateYYYYMMDD;
	public String RequestTimeHHMMSSCC;
	public String RegionalAreaId;
	public String addressName;
	public String community;
	public String zipCode;
	public char descriptiveAddrInd;

	public ASONSagValidReq () {
	}
	public ASONSagValidReq (short requestCode, String MsgLength, String IdGroup, String IdTypist, String IdSystem, String RequestDateYYYYMMDD, String RequestTimeHHMMSSCC, String RegionalAreaId, String addressName, String community, String zipCode, char descriptiveAddrInd) { 
		this.requestCode = requestCode;
		this.MsgLength = MsgLength;
		this.IdGroup = IdGroup;
		this.IdTypist = IdTypist;
		this.IdSystem = IdSystem;
		this.RequestDateYYYYMMDD = RequestDateYYYYMMDD;
		this.RequestTimeHHMMSSCC = RequestTimeHHMMSSCC;
		this.RegionalAreaId = RegionalAreaId;
		this.addressName = addressName;
		this.community = community;
		this.zipCode = zipCode;
		this.descriptiveAddrInd = descriptiveAddrInd;

	}
}
