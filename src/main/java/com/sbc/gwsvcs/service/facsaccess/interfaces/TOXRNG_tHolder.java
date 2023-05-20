package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOXRNG_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TOXRNG_t value;

	public TOXRNG_tHolder () {
	}
	public TOXRNG_tHolder (TOXRNG_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_tHelper.type();
	} 
}
