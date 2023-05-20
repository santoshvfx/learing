package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class Loop_Add_Req_tHolder implements org.omg.CORBA.portable.Streamable { 
	public Loop_Add_Req_t value;

	public Loop_Add_Req_tHolder () {
	}
	public Loop_Add_Req_tHolder (Loop_Add_Req_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.Loop_Add_Req_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Loop_Add_Req_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Loop_Add_Req_tHelper.type();
	} 
}
