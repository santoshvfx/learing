package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class MISCLU_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private MISCLU_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t();
		{
			byte[] _bytes = new byte[24];
			i.read_octet_array (_bytes, 0, 24);
			int _j;
			for (_j = 0; _j < 24; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CANSO = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[26];
			i.read_octet_array (_bytes, 0, 26);
			int _j;
			for (_j = 0; _j < 26; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CANLI = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[36];
			i.read_octet_array (_bytes, 0, 36);
			int _j;
			for (_j = 0; _j < 36; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LURSV = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PNDELU = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[34];
			i.read_octet_array (_bytes, 0, 34);
			int _j;
			for (_j = 0; _j < 34; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RULE = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t value) { 
		{
			byte[] _bytes = new byte[24];
			byte[] _bytes_src = value.CANSO.getBytes();
			int _j;
			for (_j = 0; _j < 24 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 24);
		}
		{
			byte[] _bytes = new byte[26];
			byte[] _bytes_src = value.CANLI.getBytes();
			int _j;
			for (_j = 0; _j < 26 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 26);
		}
		{
			byte[] _bytes = new byte[36];
			byte[] _bytes_src = value.LURSV.getBytes();
			int _j;
			for (_j = 0; _j < 36 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 36);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.PNDELU.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[34];
			byte[] _bytes_src = value.RULE.getBytes();
			int _j;
			for (_j = 0; _j < 34 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 34);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[5];
				members[0] = new StructMember();
				members[0].name = "CANSO";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (24, members[0].type);
				members[1] = new StructMember();
				members[1].name = "CANLI";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (26, members[1].type);
				members[2] = new StructMember();
				members[2].name = "LURSV";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (36, members[2].type);
				members[3] = new StructMember();
				members[3].name = "PNDELU";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (13, members[3].type);
				members[4] = new StructMember();
				members[4].name = "RULE";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (34, members[4].type);
				myTc = ORB.init().create_struct_tc (id(), "MISCLU_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/MISCLU_t:1.0"; } 
}
