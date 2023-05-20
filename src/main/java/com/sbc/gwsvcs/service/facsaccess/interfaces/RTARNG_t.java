package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class RTARNG_t implements org.omg.CORBA.portable.IDLEntity { 
	public String RTANO;
	public String RTA;
	public String RLC;
	public String RMK;

	public RTARNG_t () {
	}
	public RTARNG_t (String RTANO, String RTA, String RLC, String RMK) { 
		this.RTANO = RTANO;
		this.RTA = RTA;
		this.RLC = RLC;
		this.RMK = RMK;

	} 
}
