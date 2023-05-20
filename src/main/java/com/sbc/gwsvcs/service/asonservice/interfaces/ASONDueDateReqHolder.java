// $Id: ASONDueDateReqHolder.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONDueDateReqHolder implements org.omg.CORBA.portable.Streamable { 
	public ASONDueDateReq value;

	public ASONDueDateReqHolder () {
	}
	public ASONDueDateReqHolder (ASONDueDateReq initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateReqHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateReqHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateReqHelper.write (o, value); 
	}
}
