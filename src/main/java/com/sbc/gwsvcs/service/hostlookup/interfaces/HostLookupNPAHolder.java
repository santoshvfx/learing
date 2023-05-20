package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupNPAHolder implements org.omg.CORBA.portable.Streamable { 
	public HostLookupNPA value;

	public HostLookupNPAHolder () {
	}
	public HostLookupNPAHolder (HostLookupNPA initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupNPAHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupNPAHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupNPAHelper.type();
	} 
}
