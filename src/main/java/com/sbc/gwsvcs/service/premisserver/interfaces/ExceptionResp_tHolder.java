// $Id: ExceptionResp_tHolder.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
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
		value = com.sbc.gwsvcs.service.premisserver.interfaces.ExceptionResp_tHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.premisserver.interfaces.ExceptionResp_tHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.ExceptionResp_tHelper.write (o, value); 
	}
}
