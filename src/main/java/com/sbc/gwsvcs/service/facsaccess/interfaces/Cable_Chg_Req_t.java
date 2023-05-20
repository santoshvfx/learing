package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class Cable_Chg_Req_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t ECCABLE_Old;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t ECCABLE_New;

	public Cable_Chg_Req_t () {
	}
	public Cable_Chg_Req_t (com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header, com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t C1, com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t CTL, com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t ECCABLE_Old, com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t ECCABLE_New) { 
		this.Header = Header;
		this.C1 = C1;
		this.CTL = CTL;
		this.ECCABLE_Old = ECCABLE_Old;
		this.ECCABLE_New = ECCABLE_New;

	} 
}
