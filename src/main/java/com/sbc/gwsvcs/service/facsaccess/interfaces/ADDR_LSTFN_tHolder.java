package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ADDR_LSTFN_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ADDR_LSTFN_t value;

	public ADDR_LSTFN_tHolder () {
	}
	public ADDR_LSTFN_tHolder (ADDR_LSTFN_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_tHelper.type();
	} 
}
