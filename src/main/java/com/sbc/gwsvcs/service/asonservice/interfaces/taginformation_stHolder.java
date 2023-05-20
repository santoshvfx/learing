// $Id: taginformation_stHolder.java,v 1.1 2002/09/29 03:53:01 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class taginformation_stHolder implements org.omg.CORBA.portable.Streamable { 
	public taginformation_st value;

	public taginformation_stHolder () {
	}
	public taginformation_stHolder (taginformation_st initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.write (o, value); 
	}
}
