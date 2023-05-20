package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class INRNG_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CA;
	public String LPR;
	public String HPR;
	public String LSPBP;
	public String PRF;

	public INRNG_t () {
	}
	public INRNG_t (String CA, String LPR, String HPR, String LSPBP, String PRF) { 
		this.CA = CA;
		this.LPR = LPR;
		this.HPR = HPR;
		this.LSPBP = LSPBP;
		this.PRF = PRF;

	} 
}
