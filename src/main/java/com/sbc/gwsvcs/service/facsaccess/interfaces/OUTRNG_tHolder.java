package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class OUTRNG_tHolder implements org.omg.CORBA.portable.Streamable { 
	public OUTRNG_t value;

	public OUTRNG_tHolder () {
	}
	public OUTRNG_tHolder (OUTRNG_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tHelper.type();
	} 
}
