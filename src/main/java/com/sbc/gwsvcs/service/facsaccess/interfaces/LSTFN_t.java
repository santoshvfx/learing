package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class LSTFN_t implements org.omg.CORBA.portable.IDLEntity { 
	public String LST;
	public String ITM;
	public String CKID;
	public String TID;
	public String FRCA;
	public String FRPR;
	public String FBP;
	public String FOBP;
	public String TOCA;
	public String TOPR;
	public String TBP;
	public String TOBP;
	public String LSTTE;
	public String FRLSTTE;
	public String TOLSTTE;
	public String LRMK;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t[] ADDR;

	public LSTFN_t () {
	}
	public LSTFN_t (String LST, String ITM, String CKID, String TID, String FRCA, String FRPR, String FBP, String FOBP, String TOCA, String TOPR, String TBP, String TOBP, String LSTTE, String FRLSTTE, String TOLSTTE, String LRMK, com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t[] ADDR) { 
		this.LST = LST;
		this.ITM = ITM;
		this.CKID = CKID;
		this.TID = TID;
		this.FRCA = FRCA;
		this.FRPR = FRPR;
		this.FBP = FBP;
		this.FOBP = FOBP;
		this.TOCA = TOCA;
		this.TOPR = TOPR;
		this.TBP = TBP;
		this.TOBP = TOBP;
		this.LSTTE = LSTTE;
		this.FRLSTTE = FRLSTTE;
		this.TOLSTTE = TOLSTTE;
		this.LRMK = LRMK;
		this.ADDR = ADDR;

	} 
}
