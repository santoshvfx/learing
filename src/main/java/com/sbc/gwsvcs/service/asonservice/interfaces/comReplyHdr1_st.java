// $Id: comReplyHdr1_st.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class comReplyHdr1_st implements java.io.Serializable { 
	public short ReplyCode;
	public String MsgLength;
	public char TmfAction;
	public String RequestDateYYYYMMDD;
	public String RequestTimeHHMMSSCC;
	public String ReplyDateYYYYMMDD;
	public String ReplyTimeHHMMSSCC;
	public String MsgCode;
	public char MsgDelimiter;
	public String MsgText;
	public char XDRalignFiller;

	public comReplyHdr1_st () {
	}
	public comReplyHdr1_st (short ReplyCode, String MsgLength, char TmfAction, String RequestDateYYYYMMDD, String RequestTimeHHMMSSCC, String ReplyDateYYYYMMDD, String ReplyTimeHHMMSSCC, String MsgCode, char MsgDelimiter, String MsgText, char XDRalignFiller) { 
		this.ReplyCode = ReplyCode;
		this.MsgLength = MsgLength;
		this.TmfAction = TmfAction;
		this.RequestDateYYYYMMDD = RequestDateYYYYMMDD;
		this.RequestTimeHHMMSSCC = RequestTimeHHMMSSCC;
		this.ReplyDateYYYYMMDD = ReplyDateYYYYMMDD;
		this.ReplyTimeHHMMSSCC = ReplyTimeHHMMSSCC;
		this.MsgCode = MsgCode;
		this.MsgDelimiter = MsgDelimiter;
		this.MsgText = MsgText;
		this.XDRalignFiller = XDRalignFiller;

	}
}
