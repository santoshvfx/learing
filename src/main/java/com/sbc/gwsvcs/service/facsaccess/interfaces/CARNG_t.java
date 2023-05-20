package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class CARNG_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TYPE;
	public String LPR;
	public String HPR;
	public String BZ;
	public String LTC;
	public String LTF;
	public String SDP;
	public String TSB;
	public String PGS;
	public String RTA;
	public String DLE;
	public String TSI;

	public CARNG_t () {
	}
	public CARNG_t (String TYPE, String LPR, String HPR, String BZ, String LTC, String LTF, String SDP, String TSB, String PGS, String RTA, String DLE, String TSI) { 
		this.TYPE = TYPE;
		this.LPR = LPR;
		this.HPR = HPR;
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
