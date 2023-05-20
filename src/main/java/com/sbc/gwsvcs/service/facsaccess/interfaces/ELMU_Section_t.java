package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ELMU_Section_t implements org.omg.CORBA.portable.IDLEntity { 
	public String PRT;
	public String STEP;
	public String TEA;
	public String DREC;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t[] ELPMKP;

	public ELMU_Section_t () {
	}
	public ELMU_Section_t (String PRT, String STEP, String TEA, String DREC, com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t[] ELPMKP) { 
		this.PRT = PRT;
		this.STEP = STEP;
		this.TEA = TEA;
		this.DREC = DREC;
		this.ELPMKP = ELPMKP;

	} 
}
