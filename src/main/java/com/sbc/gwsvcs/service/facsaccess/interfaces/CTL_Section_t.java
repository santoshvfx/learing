package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class CTL_Section_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TT;
	public String DT;
	public String TM;
	public String SNDR;
	public String DEST;
	public String WO;
	public String MN;
	public String EMP;
	public String WC;

	public CTL_Section_t () {
	}
	public CTL_Section_t (String TT, String DT, String TM, String SNDR, String DEST, String WO, String MN, String EMP, String WC) { 
		this.TT = TT;
		this.DT = DT;
		this.TM = TM;
		this.SNDR = SNDR;
		this.DEST = DEST;
		this.WO = WO;
		this.MN = MN;
		this.EMP = EMP;
		this.WC = WC;

	} 
}
