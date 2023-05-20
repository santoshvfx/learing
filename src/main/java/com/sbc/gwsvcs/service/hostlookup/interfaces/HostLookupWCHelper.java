package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class HostLookupWCHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private HostLookupWCHelper () {
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC();
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.wc = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC value) { 
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.wc.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[1];
				members[0] = new StructMember();
				members[0].name = "wc";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (7, members[0].type);
				myTc = ORB.init().create_struct_tc (id(), "HostLookupWC", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/hostlookup/interfaces/HostLookupWC:1.0"; } 
}
