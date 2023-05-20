package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class Term_Chg_Req_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t ECTERM_Old;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t ECTERM_New;

	public Term_Chg_Req_t () {
	}
	public Term_Chg_Req_t (com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header, com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1, com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL, com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t ECTERM_Old, com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t ECTERM_New) { 
		this.Header = Header;
		this.C1 = C1;
		this.CTL = CTL;
		this.ECTERM_Old = ECTERM_Old;
		this.ECTERM_New = ECTERM_New;

	} 
}
