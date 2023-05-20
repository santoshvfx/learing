package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class BO_t implements org.omg.CORBA.portable.IDLEntity { 
	public String BORES;
	public String BOCAP;
	public String BOOFF;

	public BO_t () {
	}
	public BO_t (String BORES, String BOCAP, String BOOFF) { 
		this.BORES = BORES;
		this.BOCAP = BOCAP;
		this.BOOFF = BOOFF;

	} 
}
