// $Id: TNAMttResp_tHelper.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TNAMttResp_tHelper { 
	private static TypeCode myTc = null;
	private TNAMttResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/TNAMttResp_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.WC_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TERMN_TRAF_AREA_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_LIST_2_NBR = new String (_bytes, 0, _j);
		}
		value.TN = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.read (i);
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_CTGY_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[71];
			i.read_octet_array (_bytes, 0, 71);
			int _j;
			for (_j = 0; _j < 71; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ADDR_TX = new String (_bytes, 0, _j);
		}
		value.SO_DDT = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.read (i);
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SO_3_NBR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.COS = new String (_bytes, 0, _j);
		}
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[10];
			members[0] = new StructMember();
			members[0].name = "WC_CD";
			members[0].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.WcCd_tHelper.id(), "WcCd_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "TERMN_TRAF_AREA_CD";
			members[1].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TermnTrafAreaCd_tHelper.id(), "TermnTrafAreaCd_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "TN_LIST_2_NBR";
			members[2].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TnList2Nbr_tHelper.id(), "TnList2Nbr_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "TN";
			members[3].type = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.type();
			members[4] = new StructMember();
			members[4].name = "TN_CTGY_CD";
			members[4].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCd_tHelper.id(), "TnCtgyCd_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "ADDR_TX";
			members[5].type = ORB.init().create_array_tc (
				71, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.AddrTx_tHelper.id(), "AddrTx_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "SO_DDT";
			members[6].type = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.type();
			members[7] = new StructMember();
			members[7].name = "SO_3_NBR";
			members[7].type = ORB.init().create_array_tc (
				14, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.So3Nbr_tHelper.id(), "So3Nbr_t", members[7].type);
			members[8] = new StructMember();
			members[8].name = "COS";
			members[8].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Cos_tHelper.id(), "Cos_t", members[8].type);
			members[9] = new StructMember();
			members[9].name = "Scratch";
			members[9].type = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "TNAMttResp_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t value) { 
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.WC_CD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.TERMN_TRAF_AREA_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.TN_LIST_2_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.write (o, value.TN);
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.TN_CTGY_CD.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[71];
			byte[] _bytes_src = value.ADDR_TX.getBytes();
			int _j;
			for (_j = 0; _j < 71 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 71);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.write (o, value.SO_DDT);
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.SO_3_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.COS.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.write (o, value.Scratch);
	}
}
