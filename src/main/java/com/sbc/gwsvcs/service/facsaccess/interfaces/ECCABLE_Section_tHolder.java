package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ECCABLE_Section_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ECCABLE_Section_t value;

	public ECCABLE_Section_tHolder () {
	}
	public ECCABLE_Section_tHolder (ECCABLE_Section_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tHelper.type();
	} 
}
