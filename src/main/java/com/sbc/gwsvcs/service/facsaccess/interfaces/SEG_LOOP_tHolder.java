package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SEG_LOOP_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SEG_LOOP_t value;

	public SEG_LOOP_tHolder () {
	}
	public SEG_LOOP_tHolder (SEG_LOOP_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_tHelper.type();
	} 
}
