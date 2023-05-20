package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ELPMKP_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ELPMKP_t value;

	public ELPMKP_tHolder () {
	}
	public ELPMKP_tHolder (ELPMKP_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tHelper.type();
	} 
}
