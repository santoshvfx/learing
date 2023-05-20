package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class OUTRNG_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CA;
	public String LPR;
	public String HPR;
	public String LSPBP;

	public OUTRNG_t () {
	}
	public OUTRNG_t (String CA, String LPR, String HPR, String LSPBP) { 
		this.CA = CA;
		this.LPR = LPR;
		this.HPR = HPR;
		this.LSPBP = LSPBP;

	} 
}
