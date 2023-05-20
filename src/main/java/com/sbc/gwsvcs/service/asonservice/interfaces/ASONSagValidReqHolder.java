// $Id: ASONSagValidReqHolder.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValidReqHolder implements org.omg.CORBA.portable.Streamable { 
	public ASONSagValidReq value;

	public ASONSagValidReqHolder () {
	}
	public ASONSagValidReqHolder (ASONSagValidReq initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReqHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReqHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReqHelper.write (o, value); 
	}
}
