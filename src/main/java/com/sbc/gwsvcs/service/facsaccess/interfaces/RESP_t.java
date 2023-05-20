package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class RESP_t implements org.omg.CORBA.portable.IDLEntity { 
	public String STAT;
	public String ETYP;
	public String ERRMSG;

	public RESP_t () {
	}
	public RESP_t (String STAT, String ETYP, String ERRMSG) { 
		this.STAT = STAT;
		this.ETYP = ETYP;
		this.ERRMSG = ERRMSG;

	} 
}
