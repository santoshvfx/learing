package com.sbc.gwsvcs.service.facsaccess.interfaces;

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
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eHelper.write (o, value); 
	}
	public TypeCode _type () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eHelper.type(); 
	} 
}
