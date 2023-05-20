package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class GWException_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private GWException_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t();
		value.ERR_CD = i.read_long ();
		{
			byte[] _bytes = new byte[1025];
			i.read_octet_array (_bytes, 0, 1025);
			int _j;
			for (_j = 0; _j < 1025; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ERR_TX = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t value) { 
		o.write_long(value.ERR_CD);
		{
			byte[] _bytes = new byte[1025];
			byte[] _bytes_src = value.ERR_TX.getBytes();
			int _j;
			for (_j = 0; _j < 1025 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 1025);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[2];
				members[0] = new StructMember();
				members[0].name = "ERR_CD";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
				members[1] = new StructMember();
				members[1].name = "ERR_TX";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (1025, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.facsaccess.interfaces.Tx1024_tHelper.id(), "Tx1024_t", members[1].type);
				myTc = ORB.init().create_struct_tc (id(), "GWException_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/GWException_t:1.0"; } 
}
