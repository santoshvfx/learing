package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Header_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Header_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		{
			byte[] _bytes = new byte[15];
			i.read_octet_array (_bytes, 0, 15);
			int _j;
			for (_j = 0; _j < 15; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CLNT_UUID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[31];
			i.read_octet_array (_bytes, 0, 31);
			int _j;
			for (_j = 0; _j < 31; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CLNT_CONTEXT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[41];
			i.read_octet_array (_bytes, 0, 41);
			int _j;
			for (_j = 0; _j < 41; _j++)
				if (_bytes[_j] == 0)
					break;
			value.HOST_NAME = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[41];
			i.read_octet_array (_bytes, 0, 41);
			int _j;
			for (_j = 0; _j < 41; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TRNSACT_ID = new String (_bytes, 0, _j);
		}
		value.TRNSPT_CD = com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eHelper.read (i);
		{
			byte[] _bytes = new byte[121];
			i.read_octet_array (_bytes, 0, 121);
			int _j;
			for (_j = 0; _j < 121; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RPLY_DEST = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t value) { 
		{
			byte[] _bytes = new byte[15];
			byte[] _bytes_src = value.CLNT_UUID.getBytes();
			int _j;
			for (_j = 0; _j < 15 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 15);
		}
		{
			byte[] _bytes = new byte[31];
			byte[] _bytes_src = value.CLNT_CONTEXT.getBytes();
			int _j;
			for (_j = 0; _j < 31 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 31);
		}
		{
			byte[] _bytes = new byte[41];
			byte[] _bytes_src = value.HOST_NAME.getBytes();
			int _j;
			for (_j = 0; _j < 41 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 41);
		}
		{
			byte[] _bytes = new byte[41];
			byte[] _bytes_src = value.TRNSACT_ID.getBytes();
			int _j;
			for (_j = 0; _j < 41 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 41);
		}
		com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eHelper.write (o, value.TRNSPT_CD);
		{
			byte[] _bytes = new byte[121];
			byte[] _bytes_src = value.RPLY_DEST.getBytes();
			int _j;
			for (_j = 0; _j < 121 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 121);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "CLNT_UUID";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (15, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.facsaccess.interfaces.UUID_tHelper.id(), "UUID_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "CLNT_CONTEXT";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (31, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.facsaccess.interfaces.Context_tHelper.id(), "Context_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "HOST_NAME";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (41, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.facsaccess.interfaces.HostNm_tHelper.id(), "HostNm_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "TRNSACT_ID";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (41, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.facsaccess.interfaces.TranID_tHelper.id(), "TranID_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "TRNSPT_CD";
				members[4].type = com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eHelper.type();
				members[5] = new StructMember();
				members[5].name = "RPLY_DEST";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (121, members[5].type);
				members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.facsaccess.interfaces.Destination_tHelper.id(), "Destination_t", members[5].type);
				myTc = ORB.init().create_struct_tc (id(), "Header_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/Header_t:1.0"; } 
}
