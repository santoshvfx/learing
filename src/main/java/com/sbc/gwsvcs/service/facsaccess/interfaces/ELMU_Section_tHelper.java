package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ELMU_Section_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ELMU_Section_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t();
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
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEA = new String (_bytes, 0, _j);
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
			int __seqLength = i.read_long();
			value.ELPMKP = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.ELPMKP[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t value) { 
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
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.TEA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
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
			o.write_long (value.ELPMKP.length);
			for (int __i = 0; __i < value.ELPMKP.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tHelper.write (o, value.ELPMKP[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "PRT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (5, members[0].type);
				members[1] = new StructMember();
				members[1].name = "STEP";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (8, members[1].type);
				members[2] = new StructMember();
				members[2].name = "TEA";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (51, members[2].type);
				members[3] = new StructMember();
				members[3].name = "DREC";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (2, members[3].type);
				members[4] = new StructMember();
				members[4].name = "ELPMKP";
				members[4].type = com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tHelper.type();
				members[4].type = ORB.init().create_sequence_tc (0, members[4].type);
				myTc = ORB.init().create_struct_tc (id(), "ELMU_Section_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/ELMU_Section_t:1.0"; } 
}
