package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class EPCABLE_Section_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private EPCABLE_Section_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPCABLE_Section_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPCABLE_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPCABLE_Section_t();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PRT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STEP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DREC = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RMK = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CNST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORIG = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.CARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.CARNG[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.RTARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.RTARNG[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.EPCABLE_Section_t value) { 
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.PRT.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.STEP.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.DREC.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
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
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RMK.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.CNST.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.ORIG.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{ 
			o.write_long (value.CARNG.length);
			for (int __i = 0; __i < value.CARNG.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_tHelper.write (o, value.CARNG[__i]);
			} 
		}
		{ 
			o.write_long (value.RTARNG.length);
			for (int __i = 0; __i < value.RTARNG.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tHelper.write (o, value.RTARNG[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.EPCABLE_Section_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPCABLE_Section_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[9];
				members[0] = new StructMember();
				members[0].name = "PRT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (5, members[0].type);
				members[1] = new StructMember();
				members[1].name = "STEP";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (8, members[1].type);
				members[2] = new StructMember();
				members[2].name = "DREC";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (2, members[2].type);
				members[3] = new StructMember();
				members[3].name = "CA";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (11, members[3].type);
				members[4] = new StructMember();
				members[4].name = "RMK";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (51, members[4].type);
				members[5] = new StructMember();
				members[5].name = "CNST";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (5, members[5].type);
				members[6] = new StructMember();
				members[6].name = "ORIG";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (2, members[6].type);
				members[7] = new StructMember();
				members[7].name = "CARNG";
				members[7].type = com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_tHelper.type();
				members[7].type = ORB.init().create_sequence_tc (0, members[7].type);
				members[8] = new StructMember();
				members[8].name = "RTARNG";
				members[8].type = com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tHelper.type();
				members[8].type = ORB.init().create_sequence_tc (0, members[8].type);
				myTc = ORB.init().create_struct_tc (id(), "EPCABLE_Section_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/EPCABLE_Section_t:1.0"; } 
}
