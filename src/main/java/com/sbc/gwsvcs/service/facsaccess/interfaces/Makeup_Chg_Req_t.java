package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class Makeup_Chg_Req_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t ELMU_Old;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t ELMU_New;

	public Makeup_Chg_Req_t () {
	}
	public Makeup_Chg_Req_t (com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header, com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1, com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL, com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t ELMU_Old, com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t ELMU_New) { 
		this.Header = Header;
		this.C1 = C1;
		this.CTL = CTL;
		this.ELMU_Old = ELMU_Old;
		this.ELMU_New = ELMU_New;

	} 
}
