package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class FALP_Add_Req_tHolder implements org.omg.CORBA.portable.Streamable { 
	public FALP_Add_Req_t value;

	public FALP_Add_Req_tHolder () {
	}
	public FALP_Add_Req_tHolder (FALP_Add_Req_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.FALP_Add_Req_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.FALP_Add_Req_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.FALP_Add_Req_tHelper.type();
	} 
}
