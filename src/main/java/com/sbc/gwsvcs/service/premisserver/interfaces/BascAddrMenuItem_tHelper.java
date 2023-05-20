// $Id: BascAddrMenuItem_tHelper.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class BascAddrMenuItem_tHelper { 
	private static TypeCode myTc = null;
	private BascAddrMenuItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/BascAddrMenuItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t();
		value.StNbrId = com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tHelper.read (i);
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGND_HOUS_NBR_ID = new String (_bytes, 0, _j);
		}
		value.StNm = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tHelper.read (i);
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CMTY_NM = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SAG_RMK_1_DESC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SAG_RMK_2_DESC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SAG_RMK_3_DESC = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[7];
			members[0] = new StructMember();
			members[0].name = "StNbrId";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "ASGND_HOUS_NBR_ID";
			members[1].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrId_tHelper.id(), "AsgndHousNbrId_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "StNm";
			members[2].type = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tHelper.type();
			members[3] = new StructMember();
			members[3].name = "CMTY_NM";
			members[3].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CmtyNm_tHelper.id(), "CmtyNm_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "SAG_RMK_1_DESC";
			members[4].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SagRmkDesc_tHelper.id(), "SagRmkDesc_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "SAG_RMK_2_DESC";
			members[5].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SagRmkDesc_tHelper.id(), "SagRmkDesc_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "SAG_RMK_3_DESC";
			members[6].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SagRmkDesc_tHelper.id(), "SagRmkDesc_t", members[6].type);
			myTc = ORB.init().create_struct_tc (id(), "BascAddrMenuItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tHelper.write (o, value.StNbrId);
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ASGND_HOUS_NBR_ID.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tHelper.write (o, value.StNm);
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.CMTY_NM.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.SAG_RMK_1_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.SAG_RMK_2_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.SAG_RMK_3_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
	}
}