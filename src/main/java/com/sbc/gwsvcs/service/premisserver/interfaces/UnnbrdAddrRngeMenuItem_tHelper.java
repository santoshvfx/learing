// $Id: UnnbrdAddrRngeMenuItem_tHelper.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class UnnbrdAddrRngeMenuItem_tHelper { 
	private static TypeCode myTc = null;
	private UnnbrdAddrRngeMenuItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/UnnbrdAddrRngeMenuItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t();
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGND_HOUS_NBR_ID = new String (_bytes, 0, _j);
		}
		value.SuppAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tHelper.read (i);
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
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STATE_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ZC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[101];
			i.read_octet_array (_bytes, 0, 101);
			int _j;
			for (_j = 0; _j < 101; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SPL_INSTR = new String (_bytes, 0, _j);
		}
		value.LnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[7];
			members[0] = new StructMember();
			members[0].name = "ASGND_HOUS_NBR_ID";
			members[0].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrId_tHelper.id(), "AsgndHousNbrId_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "SuppAddrInfo";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tHelper.type();
			members[2] = new StructMember();
			members[2].name = "CMTY_NM";
			members[2].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CmtyNm_tHelper.id(), "CmtyNm_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "STATE_CD";
			members[3].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.StateCd_tHelper.id(), "StateCd_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "ZC";
			members[4].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Zc_tHelper.id(), "Zc_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "SPL_INSTR";
			members[5].type = ORB.init().create_array_tc (
				101, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SplInstr_tHelper.id(), "SplInstr_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "LnInfo";
			members[6].type = com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "UnnbrdAddrRngeMenuItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t value) { 
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ASGND_HOUS_NBR_ID.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tHelper.write (o, value.SuppAddrInfo);
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
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.STATE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.ZC.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[101];
			byte[] _bytes_src = value.SPL_INSTR.getBytes();
			int _j;
			for (_j = 0; _j < 101 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 101);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tHelper.write (o, value.LnInfo);
	}
}
