package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class OU_t implements org.omg.CORBA.portable.IDLEntity { 
	public String OUN;
	public String OUBP;
	public String GRP1;
	public String GRP2;
	public String GRP3;
	public String GRP4;
	public String CAP1;
	public String CAP2;
	public String CAP3;
	public String CAP4;

	public OU_t () {
	}
	public OU_t (String OUN, String OUBP, String GRP1, String GRP2, String GRP3, String GRP4, String CAP1, String CAP2, String CAP3, String CAP4) { 
		this.OUN = OUN;
		this.OUBP = OUBP;
		this.GRP1 = GRP1;
		this.GRP2 = GRP2;
		this.GRP3 = GRP3;
		this.GRP4 = GRP4;
		this.CAP1 = CAP1;
		this.CAP2 = CAP2;
		this.CAP3 = CAP3;
		this.CAP4 = CAP4;

	} 
}
