package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class SOP_t implements org.omg.CORBA.portable.IDLEntity { 
	public String GA;
	public String LGTH;
	public String UBA;
	public String CAPAC;
	public String BTOFF;

	public SOP_t () {
	}
	public SOP_t (String GA, String LGTH, String UBA, String CAPAC, String BTOFF) { 
		this.GA = GA;
		this.LGTH = LGTH;
		this.UBA = UBA;
		this.CAPAC = CAPAC;
		this.BTOFF = BTOFF;

	} 
}
