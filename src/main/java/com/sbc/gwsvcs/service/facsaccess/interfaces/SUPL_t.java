package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class SUPL_t implements org.omg.CORBA.portable.IDLEntity { 
	public String STYP;
	public String SID;
	public String ETYP;
	public String EID;
	public String UTYP;
	public String UID;

	public SUPL_t () {
	}
	public SUPL_t (String STYP, String SID, String ETYP, String EID, String UTYP, String UID) { 
		this.STYP = STYP;
		this.SID = SID;
		this.ETYP = ETYP;
		this.EID = EID;
		this.UTYP = UTYP;
		this.UID = UID;

	} 
}
