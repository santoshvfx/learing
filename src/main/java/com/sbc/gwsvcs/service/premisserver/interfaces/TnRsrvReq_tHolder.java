// $Id: TnRsrvReq_tHolder.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnRsrvReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TnRsrvReq_t value;

	public TnRsrvReq_tHolder () {
	}
	public TnRsrvReq_tHolder (TnRsrvReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tHelper.write (o, value); 
	}
}
