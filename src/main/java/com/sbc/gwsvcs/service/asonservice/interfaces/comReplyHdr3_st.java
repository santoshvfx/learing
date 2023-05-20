// $Id: comReplyHdr3_st.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class comReplyHdr3_st implements java.io.Serializable { 
	public String sagErrorCode;
	public char saiPrimary;
	public char saiAlt1;
	public char saiAlt2;
	public char saiAlt3;

	public comReplyHdr3_st () {
	}
	public comReplyHdr3_st (String sagErrorCode, char saiPrimary, char saiAlt1, char saiAlt2, char saiAlt3) { 
		this.sagErrorCode = sagErrorCode;
		this.saiPrimary = saiPrimary;
		this.saiAlt1 = saiAlt1;
		this.saiAlt2 = saiAlt2;
		this.saiAlt3 = saiAlt3;

	}
}
