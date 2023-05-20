package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ADDR_LSTFN_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[] BADR;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[] SUPL;

	public ADDR_LSTFN_t () {
	}
	public ADDR_LSTFN_t (com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[] BADR, com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[] SUPL) { 
		this.BADR = BADR;
		this.SUPL = SUPL;

	} 
}
