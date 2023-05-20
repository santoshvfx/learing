package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ADDR_LOOP_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ADDR_LOOP_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t();
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.XRST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PTR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RZ = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ICSW = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TYPE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RSTTE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RSTLU = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RMK0TE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RMK0LU = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.BADR = new com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.BADR[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.SUPL = new com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SUPL[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.MISCLU = new com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.MISCLU[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.PNDORD = new com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.PNDORD[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t value) { 
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.TEA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.TP.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.TEC.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.XRST.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.PTR.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.RT.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.RZ.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.ICSW.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.TYPE.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.RSTTE.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.RSTLU.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RMK0TE.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RMK0LU.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{ 
			o.write_long (value.BADR.length);
			for (int __i = 0; __i < value.BADR.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tHelper.write (o, value.BADR[__i]);
			} 
		}
		{ 
			o.write_long (value.SUPL.length);
			for (int __i = 0; __i < value.SUPL.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tHelper.write (o, value.SUPL[__i]);
			} 
		}
		{ 
			o.write_long (value.MISCLU.length);
			for (int __i = 0; __i < value.MISCLU.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_tHelper.write (o, value.MISCLU[__i]);
			} 
		}
		{ 
			o.write_long (value.PNDORD.length);
			for (int __i = 0; __i < value.PNDORD.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_tHelper.write (o, value.PNDORD[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[17];
				members[0] = new StructMember();
				members[0].name = "TEA";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (51, members[0].type);
				members[1] = new StructMember();
				members[1].name = "TP";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (6, members[1].type);
				members[2] = new StructMember();
				members[2].name = "TEC";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (12, members[2].type);
				members[3] = new StructMember();
				members[3].name = "XRST";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (21, members[3].type);
				members[4] = new StructMember();
				members[4].name = "PTR";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (16, members[4].type);
				members[5] = new StructMember();
				members[5].name = "RT";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (6, members[5].type);
				members[6] = new StructMember();
				members[6].name = "RZ";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (3, members[6].type);
				members[7] = new StructMember();
				members[7].name = "ICSW";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (5, members[7].type);
				members[8] = new StructMember();
				members[8].name = "TYPE";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (6, members[8].type);
				members[9] = new StructMember();
				members[9].name = "RSTTE";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (21, members[9].type);
				members[10] = new StructMember();
				members[10].name = "RSTLU";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (21, members[10].type);
				members[11] = new StructMember();
				members[11].name = "RMK0TE";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (51, members[11].type);
				members[12] = new StructMember();
				members[12].name = "RMK0LU";
				members[12].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[12].type = ORB.init().create_array_tc (51, members[12].type);
				members[13] = new StructMember();
				members[13].name = "BADR";
				members[13].type = com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tHelper.type();
				members[13].type = ORB.init().create_sequence_tc (0, members[13].type);
				members[14] = new StructMember();
				members[14].name = "SUPL";
				members[14].type = com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tHelper.type();
				members[14].type = ORB.init().create_sequence_tc (0, members[14].type);
				members[15] = new StructMember();
				members[15].name = "MISCLU";
				members[15].type = com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_tHelper.type();
				members[15].type = ORB.init().create_sequence_tc (0, members[15].type);
				members[16] = new StructMember();
				members[16].name = "PNDORD";
				members[16].type = com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_tHelper.type();
				members[16].type = ORB.init().create_sequence_tc (0, members[16].type);
				myTc = ORB.init().create_struct_tc (id(), "ADDR_LOOP_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/ADDR_LOOP_t:1.0"; } 
}
