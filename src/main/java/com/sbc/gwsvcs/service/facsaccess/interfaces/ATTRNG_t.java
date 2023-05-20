package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ATTRNG_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CODE;
	public String CA;
	public String LPR;
	public String HPR;
	public String BP;
	public String RST;

	public ATTRNG_t () {
	}
	public ATTRNG_t (String CODE, String CA, String LPR, String HPR, String BP, String RST) { 
		this.CODE = CODE;
		this.CA = CA;
		this.LPR = LPR;
		this.HPR = HPR;
		this.BP = BP;
		this.RST = RST;

	} 
}
