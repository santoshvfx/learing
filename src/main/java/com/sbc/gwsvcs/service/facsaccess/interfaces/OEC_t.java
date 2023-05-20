package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class OEC_t implements org.omg.CORBA.portable.IDLEntity { 
	public String GRD;
	public String CLS;
	public String WIRES;
	public String PGI;
	public String NLI;
	public String MI;
	public String CTG;
	public String COTE;
	public String QUAL;
	public String SIG;
	public String MET;
	public String DDR;
	public String LATY;
	public String DSP;

	public OEC_t () {
	}
	public OEC_t (String GRD, String CLS, String WIRES, String PGI, String NLI, String MI, String CTG, String COTE, String QUAL, String SIG, String MET, String DDR, String LATY, String DSP) { 
		this.GRD = GRD;
		this.CLS = CLS;
		this.WIRES = WIRES;
		this.PGI = PGI;
		this.NLI = NLI;
		this.MI = MI;
		this.CTG = CTG;
		this.COTE = COTE;
		this.QUAL = QUAL;
		this.SIG = SIG;
		this.MET = MET;
		this.DDR = DDR;
		this.LATY = LATY;
		this.DSP = DSP;

	} 
}
