package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class CARNG_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private CARNG_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t();
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TYPE = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BZ = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LTC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LTF = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SDP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TSB = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PGS = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RTA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DLE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TSI = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t value) { 
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.TYPE.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
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
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.BZ.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.LTC.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.LTF.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.SDP.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TSB.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.PGS.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.RTA.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.DLE.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TSI.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[12];
				members[0] = new StructMember();
				members[0].name = "TYPE";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (10, members[0].type);
				members[1] = new StructMember();
				members[1].name = "LPR";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (5, members[1].type);
				members[2] = new StructMember();
				members[2].name = "HPR";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (5, members[2].type);
				members[3] = new StructMember();
				members[3].name = "BZ";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (2, members[3].type);
				members[4] = new StructMember();
				members[4].name = "LTC";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (5, members[4].type);
				members[5] = new StructMember();
				members[5].name = "LTF";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (5, members[5].type);
				members[6] = new StructMember();
				members[6].name = "SDP";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (2, members[6].type);
				members[7] = new StructMember();
				members[7].name = "TSB";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (2, members[7].type);
				members[8] = new StructMember();
				members[8].name = "PGS";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (5, members[8].type);
				members[9] = new StructMember();
				members[9].name = "RTA";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (3, members[9].type);
				members[10] = new StructMember();
				members[10].name = "DLE";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (2, members[10].type);
				members[11] = new StructMember();
				members[11].name = "TSI";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (2, members[11].type);
				myTc = ORB.init().create_struct_tc (id(), "CARNG_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/CARNG_t:1.0"; } 
}
