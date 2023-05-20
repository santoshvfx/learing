package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class PNDORD_tHolder implements org.omg.CORBA.portable.Streamable { 
	public PNDORD_t value;

	public PNDORD_tHolder () {
	}
	public PNDORD_tHolder (PNDORD_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_tHelper.type();
	} 
}
