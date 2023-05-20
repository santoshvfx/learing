package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASGBP_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ASGBP_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ABP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LTS = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DEF = new String (_bytes, 0, _j);
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
			int __seqLength = i.read_long();
			value.TF = new com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.TF[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.TF_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t value) { 
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.ABP.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.LTS.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.RST.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.DEF.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
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
			o.write_long (value.TF.length);
			for (int __i = 0; __i < value.TF.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.TF_tHelper.write (o, value.TF[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[6];
				members[0] = new StructMember();
				members[0].name = "ABP";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (5, members[0].type);
				members[1] = new StructMember();
				members[1].name = "LTS";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (5, members[1].type);
				members[2] = new StructMember();
				members[2].name = "RST";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (21, members[2].type);
				members[3] = new StructMember();
				members[3].name = "DEF";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (4, members[3].type);
				members[4] = new StructMember();
				members[4].name = "RMK";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (51, members[4].type);
				members[5] = new StructMember();
				members[5].name = "TF";
				members[5].type = com.sbc.gwsvcs.service.facsaccess.interfaces.TF_tHelper.type();
				members[5].type = ORB.init().create_sequence_tc (0, members[5].type);
				myTc = ORB.init().create_struct_tc (id(), "ASGBP_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/ASGBP_t:1.0"; } 
}
