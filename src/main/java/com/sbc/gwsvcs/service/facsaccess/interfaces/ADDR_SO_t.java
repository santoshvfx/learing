package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ADDR_SO_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TEA;
	public String TP;
	public String TEC;
	public String XRST;
	public String PTR;
	public String RT;
	public String RZ;
	public String ICSW;
	public String TYPE;
	public String RSTTE;
	public String RSTLU;
	public String RMK0TE;
	public String RMK0LU;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[] BADR;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[] SUPL;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t[] MISCLU;

	public ADDR_SO_t () {
	}
	public ADDR_SO_t (String TEA, String TP, String TEC, String XRST, String PTR, String RT, String RZ, String ICSW, String TYPE, String RSTTE, String RSTLU, String RMK0TE, String RMK0LU, com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[] BADR, com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[] SUPL, com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t[] MISCLU) { 
		this.TEA = TEA;
		this.TP = TP;
		this.TEC = TEC;
		this.XRST = XRST;
		this.PTR = PTR;
		this.RT = RT;
		this.RZ = RZ;
		this.ICSW = ICSW;
		this.TYPE = TYPE;
		this.RSTTE = RSTTE;
		this.RSTLU = RSTLU;
		this.RMK0TE = RMK0TE;
		this.RMK0LU = RMK0LU;
		this.BADR = BADR;
		this.SUPL = SUPL;
		this.MISCLU = MISCLU;

	} 
}
