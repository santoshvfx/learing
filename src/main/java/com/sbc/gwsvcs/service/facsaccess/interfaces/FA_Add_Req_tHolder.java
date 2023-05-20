package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class FA_Add_Req_tHolder implements org.omg.CORBA.portable.Streamable { 
	public FA_Add_Req_t value;

	public FA_Add_Req_tHolder () {
	}
	public FA_Add_Req_tHolder (FA_Add_Req_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.FA_Add_Req_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.FA_Add_Req_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.FA_Add_Req_tHelper.type();
	} 
}
