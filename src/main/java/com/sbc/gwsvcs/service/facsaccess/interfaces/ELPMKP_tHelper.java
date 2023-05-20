package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ELPMKP_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ELPMKP_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t();
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STAT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.HPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[44];
			i.read_octet_array (_bytes, 0, 44);
			int _j;
			for (_j = 0; _j < 44; _j++)
				if (_bytes[_j] == 0)
					break;
			value.GHCPTR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LUNIT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.COIL = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.NLDS = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[56];
			i.read_octet_array (_bytes, 0, 56);
			int _j;
			for (_j = 0; _j < 56; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DEFLDSP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.COES = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[60];
			i.read_octet_array (_bytes, 0, 60);
			int _j;
			for (_j = 0; _j < 60; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LDSP1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[60];
			i.read_octet_array (_bytes, 0, 60);
			int _j;
			for (_j = 0; _j < 60; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LDSP2 = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.BO = new com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.BO[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.BO_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.SOP = new com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SOP[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t value) { 
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.STAT.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.CA.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.LPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.HPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[44];
			byte[] _bytes_src = value.GHCPTR.getBytes();
			int _j;
			for (_j = 0; _j < 44 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 44);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.LUNIT.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.COIL.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.NLDS.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[56];
			byte[] _bytes_src = value.DEFLDSP.getBytes();
			int _j;
			for (_j = 0; _j < 56 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 56);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.COES.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[60];
			byte[] _bytes_src = value.LDSP1.getBytes();
			int _j;
			for (_j = 0; _j < 60 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 60);
		}
		{
			byte[] _bytes = new byte[60];
			byte[] _bytes_src = value.LDSP2.getBytes();
			int _j;
			for (_j = 0; _j < 60 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 60);
		}
		{ 
			o.write_long (value.BO.length);
			for (int __i = 0; __i < value.BO.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.BO_tHelper.write (o, value.BO[__i]);
			} 
		}
		{ 
			o.write_long (value.SOP.length);
			for (int __i = 0; __i < value.SOP.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_tHelper.write (o, value.SOP[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[14];
				members[0] = new StructMember();
				members[0].name = "STAT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (7, members[0].type);
				members[1] = new StructMember();
				members[1].name = "CA";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (11, members[1].type);
				members[2] = new StructMember();
				members[2].name = "LPR";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (5, members[2].type);
				members[3] = new StructMember();
				members[3].name = "HPR";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (5, members[3].type);
				members[4] = new StructMember();
				members[4].name = "GHCPTR";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (44, members[4].type);
				members[5] = new StructMember();
				members[5].name = "LUNIT";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (3, members[5].type);
				members[6] = new StructMember();
				members[6].name = "COIL";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (5, members[6].type);
				members[7] = new StructMember();
				members[7].name = "NLDS";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (3, members[7].type);
				members[8] = new StructMember();
				members[8].name = "DEFLDSP";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (56, members[8].type);
				members[9] = new StructMember();
				members[9].name = "COES";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (9, members[9].type);
				members[10] = new StructMember();
				members[10].name = "LDSP1";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (60, members[10].type);
				members[11] = new StructMember();
				members[11].name = "LDSP2";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (60, members[11].type);
				members[12] = new StructMember();
				members[12].name = "BO";
				members[12].type = com.sbc.gwsvcs.service.facsaccess.interfaces.BO_tHelper.type();
				members[12].type = ORB.init().create_sequence_tc (0, members[12].type);
				members[13] = new StructMember();
				members[13].name = "SOP";
				members[13].type = com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_tHelper.type();
				members[13].type = ORB.init().create_sequence_tc (0, members[13].type);
				myTc = ORB.init().create_struct_tc (id(), "ELPMKP_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/ELPMKP_t:1.0"; } 
}
