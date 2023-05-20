package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ExceptionResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t OutExcp;

	public ExceptionResp_t () {
	}
	public ExceptionResp_t (com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t Header, com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t OutExcp) { 
		this.Header = Header;
		this.OutExcp = OutExcp;

	} 
}
