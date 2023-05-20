package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ExceptionResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ExceptionResp_t value;

	public ExceptionResp_tHolder () {
	}
	public ExceptionResp_tHolder (ExceptionResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.ExceptionResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ExceptionResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ExceptionResp_tHelper.type();
	} 
}
