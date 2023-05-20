package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class Term_Inq_Req_tHolder implements org.omg.CORBA.portable.Streamable { 
	public Term_Inq_Req_t value;

	public Term_Inq_Req_tHolder () {
	}
	public Term_Inq_Req_tHolder (Term_Inq_Req_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Inq_Req_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Inq_Req_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Inq_Req_tHelper.type();
	} 
}
