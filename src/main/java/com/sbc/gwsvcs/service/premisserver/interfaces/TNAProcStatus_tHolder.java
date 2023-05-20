// $Id: TNAProcStatus_tHolder.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TNAProcStatus_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TNAProcStatus_t value;

	public TNAProcStatus_tHolder () {
	}
	public TNAProcStatus_tHolder (TNAProcStatus_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_tHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_tHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_tHelper.write (o, value); 
	}
}
