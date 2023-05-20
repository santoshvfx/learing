// $Id: ASONLvngUnitValRespHolder.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONLvngUnitValRespHolder implements org.omg.CORBA.portable.Streamable { 
	public ASONLvngUnitValResp value;

	public ASONLvngUnitValRespHolder () {
	}
	public ASONLvngUnitValRespHolder (ASONLvngUnitValResp initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValRespHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValRespHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValRespHelper.write (o, value); 
	}
}
