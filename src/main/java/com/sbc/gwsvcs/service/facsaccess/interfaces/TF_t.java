package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class TF_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TFN;
	public String TFCA;
	public String TFPR;
	public String TRC;
	public String TMED;
	public String FTE;
	public String FTP;
	public String FTEC;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.OU_t[] OU;

	public TF_t () {
	}
	public TF_t (String TFN, String TFCA, String TFPR, String TRC, String TMED, String FTE, String FTP, String FTEC, com.sbc.gwsvcs.service.facsaccess.interfaces.OU_t[] OU) { 
		this.TFN = TFN;
		this.TFCA = TFCA;
		this.TFPR = TFPR;
		this.TRC = TRC;
		this.TMED = TMED;
		this.FTE = FTE;
		this.FTP = FTP;
		this.FTEC = FTEC;
		this.OU = OU;

	} 
}
