package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPAF_Section_tHolder implements org.omg.CORBA.portable.Streamable { 
	public EPAF_Section_t value;

	public EPAF_Section_tHolder () {
	}
	public EPAF_Section_tHolder (EPAF_Section_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tHelper.type();
	} 
}
