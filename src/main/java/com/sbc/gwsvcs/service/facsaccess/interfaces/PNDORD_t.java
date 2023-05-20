package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class PNDORD_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SOID;
	public String ORD;
	public String DD;
	public String PNDLPS;

	public PNDORD_t () {
	}
	public PNDORD_t (String SOID, String ORD, String DD, String PNDLPS) { 
		this.SOID = SOID;
		this.ORD = ORD;
		this.DD = DD;
		this.PNDLPS = PNDLPS;

	} 
}
