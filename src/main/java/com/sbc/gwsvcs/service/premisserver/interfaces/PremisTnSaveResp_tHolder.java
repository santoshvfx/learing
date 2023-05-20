// $Id: PremisTnSaveResp_tHolder.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnSaveResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public PremisTnSaveResp_t value;

	public PremisTnSaveResp_tHolder () {
	}
	public PremisTnSaveResp_tHolder (PremisTnSaveResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveResp_tHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveResp_tHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveResp_tHelper.write (o, value); 
	}
}
