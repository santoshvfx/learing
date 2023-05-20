package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class OCDAT_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CA;
	public String TYPE;
	public String LPR;
	public String HPR;
	public String LSPBP;
	public String BZ;
	public String LTC;
	public String LTF;
	public String SDP;
	public String TSB;
	public String PGS;
	public String RTA;
	public String DLE;
	public String TSI;

	public OCDAT_t () {
	}
	public OCDAT_t (String CA, String TYPE, String LPR, String HPR, String LSPBP, String BZ, String LTC, String LTF, String SDP, String TSB, String PGS, String RTA, String DLE, String TSI) { 
		this.CA = CA;
		this.TYPE = TYPE;
		this.LPR = LPR;
		this.HPR = HPR;
		this.LSPBP = LSPBP;
		this.BZ = BZ;
		this.LTC = LTC;
		this.LTF = LTF;
		this.SDP = SDP;
		this.TSB = TSB;
		this.PGS = PGS;
		this.RTA = RTA;
		this.DLE = DLE;
		this.TSI = TSI;

	} 
}
