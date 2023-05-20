package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class Header_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CLNT_UUID;
	public String CLNT_CONTEXT;
	public String HOST_NAME;
	public String TRNSACT_ID;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e TRNSPT_CD;
	public String RPLY_DEST;

	public Header_t () {
	}
	public Header_t (String CLNT_UUID, String CLNT_CONTEXT, String HOST_NAME, String TRNSACT_ID, com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e TRNSPT_CD, String RPLY_DEST) { 
		this.CLNT_UUID = CLNT_UUID;
		this.CLNT_CONTEXT = CLNT_CONTEXT;
		this.HOST_NAME = HOST_NAME;
		this.TRNSACT_ID = TRNSACT_ID;
		this.TRNSPT_CD = TRNSPT_CD;
		this.RPLY_DEST = RPLY_DEST;

	} 
}
