// $Id: UnnbrdAddrRngeMenuItem_tHolder.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdAddrRngeMenuItem_tHolder implements org.omg.CORBA.portable.Streamable { 
	public UnnbrdAddrRngeMenuItem_t value;

	public UnnbrdAddrRngeMenuItem_tHolder () {
	}
	public UnnbrdAddrRngeMenuItem_tHolder (UnnbrdAddrRngeMenuItem_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tHelper.write (o, value); 
	}
}
