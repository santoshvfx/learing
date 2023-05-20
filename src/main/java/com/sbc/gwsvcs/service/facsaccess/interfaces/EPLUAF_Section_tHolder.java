package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPLUAF_Section_tHolder implements org.omg.CORBA.portable.Streamable { 
	public EPLUAF_Section_t value;

	public EPLUAF_Section_tHolder () {
	}
	public EPLUAF_Section_tHolder (EPLUAF_Section_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.EPLUAF_Section_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPLUAF_Section_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPLUAF_Section_tHelper.type();
	} 
}
