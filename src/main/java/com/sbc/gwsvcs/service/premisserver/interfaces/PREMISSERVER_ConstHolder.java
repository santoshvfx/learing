// $Id: PREMISSERVER_ConstHolder.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PREMISSERVER_ConstHolder implements org.omg.CORBA.portable.Streamable { 
	public PREMISSERVER_Const value;

	public PREMISSERVER_ConstHolder () { }
	public PREMISSERVER_ConstHolder (PREMISSERVER_Const initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = PREMISSERVER_ConstHelper.read (i); 
	}
	public TypeCode _type () { return PREMISSERVER_ConstHelper.type(); }
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		PREMISSERVER_ConstHelper.write (o, value); 
	}
}
