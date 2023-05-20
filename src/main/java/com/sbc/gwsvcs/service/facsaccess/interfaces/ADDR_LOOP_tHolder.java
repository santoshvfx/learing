package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ADDR_LOOP_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ADDR_LOOP_t value;

	public ADDR_LOOP_tHolder () {
	}
	public ADDR_LOOP_tHolder (ADDR_LOOP_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_tHelper.type();
	} 
}
