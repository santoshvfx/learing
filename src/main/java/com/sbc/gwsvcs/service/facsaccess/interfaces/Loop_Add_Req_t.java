package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class Loop_Add_Req_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t EPAF;

	public Loop_Add_Req_t () {
	}
	public Loop_Add_Req_t (com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header, com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1, com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL, com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t EPAF) { 
		this.Header = Header;
		this.C1 = C1;
		this.CTL = CTL;
		this.EPAF = EPAF;

	} 
}
