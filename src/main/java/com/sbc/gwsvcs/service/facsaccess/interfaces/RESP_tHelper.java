package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class RESP_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private RESP_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t();
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STAT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ETYP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[121];
			i.read_octet_array (_bytes, 0, 121);
			int _j;
			for (_j = 0; _j < 121; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ERRMSG = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t value) { 
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.STAT.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.ETYP.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[121];
			byte[] _bytes_src = value.ERRMSG.getBytes();
			int _j;
			for (_j = 0; _j < 121 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 121);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[3];
				members[0] = new StructMember();
				members[0].name = "STAT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (8, members[0].type);
				members[1] = new StructMember();
				members[1].name = "ETYP";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (9, members[1].type);
				members[2] = new StructMember();
				members[2].name = "ERRMSG";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (121, members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "RESP_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/RESP_t:1.0"; } 
}
