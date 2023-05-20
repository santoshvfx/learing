package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class LSEG_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CA;
	public String PR;
	public String PCOM;
	public String PRMK;

	public LSEG_t () {
	}
	public LSEG_t (String CA, String PR, String PCOM, String PRMK) { 
		this.CA = CA;
		this.PR = PR;
		this.PCOM = PCOM;
		this.PRMK = PRMK;

	} 
}
