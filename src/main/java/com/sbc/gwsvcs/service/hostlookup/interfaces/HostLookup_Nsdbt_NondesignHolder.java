package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookup_Nsdbt_NondesignHolder implements org.omg.CORBA.portable.Streamable { 
	public HostLookup_Nsdbt_Nondesign value;

	public HostLookup_Nsdbt_NondesignHolder () {
	}
	public HostLookup_Nsdbt_NondesignHolder (HostLookup_Nsdbt_Nondesign initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Nsdbt_NondesignHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Nsdbt_NondesignHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Nsdbt_NondesignHelper.type();
	} 
}
