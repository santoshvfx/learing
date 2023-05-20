// $Id: TrnsptType_eHolder.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TrnsptType_eHolder implements org.omg.CORBA.portable.Streamable { 
	public TrnsptType_e value;

	public TrnsptType_eHolder () {
	}
	public TrnsptType_eHolder (TrnsptType_e initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.premisserver.interfaces.TrnsptType_eHelper.read (i); 
	}
	public TypeCode _type () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.TrnsptType_eHelper.type(); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TrnsptType_eHelper.write (o, value); 
	}
}
