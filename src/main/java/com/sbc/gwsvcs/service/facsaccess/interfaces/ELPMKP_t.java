package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ELPMKP_t implements org.omg.CORBA.portable.IDLEntity { 
	public String STAT;
	public String CA;
	public String LPR;
	public String HPR;
	public String GHCPTR;
	public String LUNIT;
	public String COIL;
	public String NLDS;
	public String DEFLDSP;
	public String COES;
	public String LDSP1;
	public String LDSP2;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t[] BO;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t[] SOP;

	public ELPMKP_t () {
	}
	public ELPMKP_t (String STAT, String CA, String LPR, String HPR, String GHCPTR, String LUNIT, String COIL, String NLDS, String DEFLDSP, String COES, String LDSP1, String LDSP2, com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t[] BO, com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t[] SOP) { 
		this.STAT = STAT;
		this.CA = CA;
		this.LPR = LPR;
		this.HPR = HPR;
		this.GHCPTR = GHCPTR;
		this.LUNIT = LUNIT;
		this.COIL = COIL;
		this.NLDS = NLDS;
		this.DEFLDSP = DEFLDSP;
		this.COES = COES;
		this.LDSP1 = LDSP1;
		this.LDSP2 = LDSP2;
		this.BO = BO;
		this.SOP = SOP;

	} 
}
