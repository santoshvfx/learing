// $Id: TNAStaResp_tHelper.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TNAStaResp_tHelper { 
	private static TypeCode myTc = null;
	private TNAStaResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/TNAStaResp_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t();
		value.TN = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.read (i);
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
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_STS_CD = new String (_bytes, 0, _j);
		}
		value.TN_STS_2_DT = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.read (i);
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
		{
			byte[] _bytes = new byte[71];
			i.read_octet_array (_bytes, 0, 71);
			int _j;
			for (_j = 0; _j < 71; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ADDR_TX = new String (_bytes, 0, _j);
		}
		{ 
			value.HTG_TN_2_ID = new String[4];
			for (int __i0 = 0; __i0 < 4; __i0++) { 
				{
					byte[] _bytes = new byte[11];
					i.read_octet_array (_bytes, 0, 11);
					int _j;
					for (_j = 0; _j < 11; _j++)
						if (_bytes[_j] == 0)
							break;
					value.HTG_TN_2_ID[__i0] = new String (_bytes, 0, _j);
				}
			} 
		}
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[12];
			members[0] = new StructMember();
			members[0].name = "TN";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "WC_CD";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.WcCd_tHelper.id(), "WcCd_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "TERMN_TRAF_AREA_CD";
			members[2].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TermnTrafAreaCd_tHelper.id(), "TermnTrafAreaCd_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "TN_LIST_2_NBR";
			members[3].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TnList2Nbr_tHelper.id(), "TnList2Nbr_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "TN_STS_CD";
			members[4].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TnStsCd_tHelper.id(), "TnStsCd_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "TN_STS_2_DT";
			members[5].type = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.type();
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
			members[9].name = "ADDR_TX";
			members[9].type = ORB.init().create_array_tc (
				71, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.AddrTx_tHelper.id(), "AddrTx_t", members[9].type);
			members[10] = new StructMember();
			members[10].name = "HTG_TN_2_ID";
			members[10].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.HtgTn2Id_tHelper.id(), "HtgTn2Id_t", members[10].type);
			members[10].type = ORB.init().create_array_tc (4, members[10].type);
			members[11] = new StructMember();
			members[11].name = "Scratch";
			members[11].type = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "TNAStaResp_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.write (o, value.TN);
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
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TN_STS_CD.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.write (o, value.TN_STS_2_DT);
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
		{
			byte[] _bytes = new byte[71];
			byte[] _bytes_src = value.ADDR_TX.getBytes();
			int _j;
			for (_j = 0; _j < 71 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 71);
		}
		{ 
			for (int __i0 = 0; __i0 < 4; __i0++) { 
				{
					byte[] _bytes = new byte[11];
					byte[] _bytes_src = value.HTG_TN_2_ID[__i0].getBytes();
					int _j;
					for (_j = 0; _j < 11 - 1; _j++)
						_bytes[_j] = _bytes_src[_j];
					_bytes[_j] = 0;
					o.write_octet_array (_bytes, 0, 11);
				}
			} 
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.write (o, value.Scratch);
	}
}
