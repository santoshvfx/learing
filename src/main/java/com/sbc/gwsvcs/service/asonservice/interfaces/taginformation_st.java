// $Id: taginformation_st.java,v 1.1 2002/09/29 03:53:01 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class taginformation_st implements java.io.Serializable { 
	public String headerInfo1;
	public String sendingSystem;
	public String headerInfo2;
	public String sendingDate;
	public String headerInfo3;
	public String sendingTime;
	public String headerInfo4;
	public String receivingSystem;
	public String headerInfo5;
	public String receivingDate;
	public String headerInfo6;
	public String receivingTime;
	public String headerInfo7;
	public String tagErrCode;
	public String headerInfo8;
	public String wordAlignmentByte;

	public taginformation_st () {
	}
	public taginformation_st (String headerInfo1, String sendingSystem, String headerInfo2, String sendingDate, String headerInfo3, String sendingTime, String headerInfo4, String receivingSystem, String headerInfo5, String receivingDate, String headerInfo6, String receivingTime, String headerInfo7, String tagErrCode, String headerInfo8, String wordAlignmentByte) { 
		this.headerInfo1 = headerInfo1;
		this.sendingSystem = sendingSystem;
		this.headerInfo2 = headerInfo2;
		this.sendingDate = sendingDate;
		this.headerInfo3 = headerInfo3;
		this.sendingTime = sendingTime;
		this.headerInfo4 = headerInfo4;
		this.receivingSystem = receivingSystem;
		this.headerInfo5 = headerInfo5;
		this.receivingDate = receivingDate;
		this.headerInfo6 = headerInfo6;
		this.receivingTime = receivingTime;
		this.headerInfo7 = headerInfo7;
		this.tagErrCode = tagErrCode;
		this.headerInfo8 = headerInfo8;
		this.wordAlignmentByte = wordAlignmentByte;

	}
}
