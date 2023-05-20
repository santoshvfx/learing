package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class BADR_t implements org.omg.CORBA.portable.IDLEntity { 
	public String BAD;
	public String STR;
	public String CNA;
	public String STN;

	public BADR_t () {
	}
	public BADR_t (String BAD, String STR, String CNA, String STN) { 
		this.BAD = BAD;
		this.STR = STR;
		this.CNA = CNA;
		this.STN = STN;

	} 
}
