package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ASGBP_t implements org.omg.CORBA.portable.IDLEntity { 
	public String ABP;
	public String LTS;
	public String RST;
	public String DEF;
	public String RMK;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t[] TF;

	public ASGBP_t () {
	}
	public ASGBP_t (String ABP, String LTS, String RST, String DEF, String RMK, com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t[] TF) { 
		this.ABP = ABP;
		this.LTS = LTS;
		this.RST = RST;
		this.DEF = DEF;
		this.RMK = RMK;
		this.TF = TF;

	} 
}
