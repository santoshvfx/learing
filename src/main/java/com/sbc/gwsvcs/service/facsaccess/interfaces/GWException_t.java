package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class GWException_t implements org.omg.CORBA.portable.IDLEntity { 
	public int ERR_CD;
	public String ERR_TX;

	public GWException_t () {
	}
	public GWException_t (int ERR_CD, String ERR_TX) { 
		this.ERR_CD = ERR_CD;
		this.ERR_TX = ERR_TX;

	} 
}
