// $Id: UnnbrdMenuProcStatus_tHolder.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdMenuProcStatus_tHolder implements org.omg.CORBA.portable.Streamable { 
	public UnnbrdMenuProcStatus_t value;

	public UnnbrdMenuProcStatus_tHolder () {
	}
	public UnnbrdMenuProcStatus_tHolder (UnnbrdMenuProcStatus_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_tHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_tHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_tHelper.write (o, value); 
	}
}
