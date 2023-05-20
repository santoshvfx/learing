package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class Result_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t[] RESP;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t ECTERM;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t ECCABLE;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_t ECPRAT;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t ELMU;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t EPAF;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t[] LOOP;

	public Result_t () {
	}
	public Result_t (com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header, com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1, com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL, com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t[] RESP, com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t ECTERM, com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t ECCABLE, com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_t ECPRAT, com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t ELMU, com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t EPAF, com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t[] LOOP) { 
		this.Header = Header;
		this.C1 = C1;
		this.CTL = CTL;
		this.RESP = RESP;
		this.ECTERM = ECTERM;
		this.ECCABLE = ECCABLE;
		this.ECPRAT = ECPRAT;
		this.ELMU = ELMU;
		this.EPAF = EPAF;
		this.LOOP = LOOP;

	} 
}
