package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupSTHolder implements org.omg.CORBA.portable.Streamable { 
	public HostLookupST value;

	public HostLookupSTHolder () {
	}
	public HostLookupSTHolder (HostLookupST initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupSTHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupSTHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupSTHelper.type();
	} 
}
