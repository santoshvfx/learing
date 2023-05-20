package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class Cable_Chg_Req_tHolder implements org.omg.CORBA.portable.Streamable { 
	public Cable_Chg_Req_t value;

	public Cable_Chg_Req_tHolder () {
	}
	public Cable_Chg_Req_tHolder (Cable_Chg_Req_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.Cable_Chg_Req_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Cable_Chg_Req_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Cable_Chg_Req_tHelper.type();
	} 
}
