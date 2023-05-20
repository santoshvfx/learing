package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class MISCLU_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CANSO;
	public String CANLI;
	public String LURSV;
	public String PNDELU;
	public String RULE;

	public MISCLU_t () {
	}
	public MISCLU_t (String CANSO, String CANLI, String LURSV, String PNDELU, String RULE) { 
		this.CANSO = CANSO;
		this.CANLI = CANLI;
		this.LURSV = LURSV;
		this.PNDELU = PNDELU;
		this.RULE = RULE;

	} 
}
