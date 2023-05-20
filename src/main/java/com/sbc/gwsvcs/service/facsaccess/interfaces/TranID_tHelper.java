package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.ORB;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;

public class TranID_tHelper { 

	private static TypeCode myTc = null;

	public static String read(org.omg.CORBA.portable.InputStream i) {
		String value;
		{
			byte[] _bytes = new byte[41];
			i.read_octet_array (_bytes, 0, 41);
			int _j;
			for (_j = 0; _j < 41; _j++)
				if (_bytes[_j] == 0)
					break;
			value = new String (_bytes, 0, _j);
		}
		return value;
		}

	public static void write(org.omg.CORBA.portable.OutputStream o, String value) {
		{
			byte[] _bytes = new byte[41];
			byte[] _bytes_src = value.getBytes();
			int _j;
			for (_j = 0; _j < 41 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 41);
		}
		}

	public static void insert (org.omg.CORBA.Any a, String t) { 
		a.insert_string (t); 
	}
	public static String extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return a.extract_string (); 
	}
	public static String id() { return "IDL:com.sbc.gwsvcs.service.facsaccess.interfaces.TranID_t:1.0"; }

	public static TypeCode type() { 
		if (myTc == null)
		{
			StructMember[] members = new StructMember[1];
			members[0] = new StructMember();
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
			members[0].type = ORB.init().create_array_tc (41, members[0].type);
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.facsaccess.interfaces.TranID_tHelper.id(), "TranID_t", members[0].type);
			myTc = members[0].type;
		}
		return myTc; 
	} 
}
