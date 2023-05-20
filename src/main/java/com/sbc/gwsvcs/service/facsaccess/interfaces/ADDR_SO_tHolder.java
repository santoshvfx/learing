package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ADDR_SO_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ADDR_SO_t value;

	public ADDR_SO_tHolder () {
	}
	public ADDR_SO_tHolder (ADDR_SO_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_tHelper.type();
	} 
}
