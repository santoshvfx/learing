package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class MobileCTNLookup_RHolder implements org.omg.CORBA.portable.Streamable { 
	public MobileCTNLookup_R value;

	public MobileCTNLookup_RHolder () {
	}
	public MobileCTNLookup_RHolder (MobileCTNLookup_R initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_RHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_RHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_RHelper.type();
	} 
}
