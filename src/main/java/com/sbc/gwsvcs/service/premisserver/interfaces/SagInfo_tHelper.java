// $Id: SagInfo_tHelper.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SagInfo_tHelper { 
	private static TypeCode myTc = null;
	private SagInfo_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/SagInfo_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.EXCH_ID = new String (_bytes, 0, _j);
		}
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
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RT_ZONE_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TAX_AREA_CD = new String (_bytes, 0, _j);
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
			int __seqLength = i.read_long();
			value.NPA_LST = new String[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				{
					byte[] _bytes = new byte[4];
					i.read_octet_array (_bytes, 0, 4);
					int _j;
					for (_j = 0; _j < 4; _j++)
						if (_bytes[_j] == 0)
							break;
					value.NPA_LST[__i] = new String (_bytes, 0, _j);
				}
			} 
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEL_FEAT_ID = new String (_bytes, 0, _j);
		}
		value.HGRP_AVAIL_IND = i.read_char ();
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BUS_OFC_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DIR_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CO_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LATA_PREMIS = new String (_bytes, 0, _j);
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
			int __seqLength = i.read_long();
			value.ATTN = new char[__seqLength];
			i.read_char_array (value.ATTN, 0, __seqLength); 
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.GEO_SEG_RMK_DESC = new String (_bytes, 0, _j);
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
			StructMember members[] = new StructMember[18];
			members[0] = new StructMember();
			members[0].name = "EXCH_ID";
			members[0].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.ExchId_tHelper.id(), "ExchId_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "WC_CD";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.WcCd_tHelper.id(), "WcCd_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "RT_ZONE_CD";
			members[2].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.RtZoneCd_tHelper.id(), "RtZoneCd_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "TAX_AREA_CD";
			members[3].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TaxAreaCd_tHelper.id(), "TaxAreaCd_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "ZC";
			members[4].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Zc_tHelper.id(), "Zc_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "NPA_LST";
			members[5].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Npa_tHelper.id(), "Npa_t", members[5].type);
			members[5].type = ORB.init().create_sequence_tc (0, members[5].type);
			members[6] = new StructMember();
			members[6].name = "TEL_FEAT_ID";
			members[6].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatId_tHelper.id(), "TelFeatId_t", members[6].type);
			members[7] = new StructMember();
			members[7].name = "HGRP_AVAIL_IND";
			members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Pos_tHelper.id(), "Pos_t", members[7].type);
			members[8] = new StructMember();
			members[8].name = "BUS_OFC_CD";
			members[8].type = ORB.init().create_array_tc (
				10, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.BusOfcCd_tHelper.id(), "BusOfcCd_t", members[8].type);
			members[9] = new StructMember();
			members[9].name = "DIR_CD";
			members[9].type = ORB.init().create_array_tc (
				10, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.DirCd_tHelper.id(), "DirCd_t", members[9].type);
			members[10] = new StructMember();
			members[10].name = "CO_ID";
			members[10].type = ORB.init().create_array_tc (
				10, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CoId_tHelper.id(), "CoId_t", members[10].type);
			members[11] = new StructMember();
			members[11].name = "LATA_PREMIS";
			members[11].type = ORB.init().create_array_tc (
				10, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.LataPremis_tHelper.id(), "LataPremis_t", members[11].type);
			members[12] = new StructMember();
			members[12].name = "TERMN_TRAF_AREA_CD";
			members[12].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[12].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TermnTrafAreaCd_tHelper.id(), "TermnTrafAreaCd_t", members[12].type);
			members[13] = new StructMember();
			members[13].name = "ATTN";
			members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[13].type = ORB.init().create_sequence_tc (0, members[13].type);
			members[14] = new StructMember();
			members[14].name = "GEO_SEG_RMK_DESC";
			members[14].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[14].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegRmkDesc_tHelper.id(), "GeoSegRmkDesc_t", members[14].type);
			members[15] = new StructMember();
			members[15].name = "SAG_RMK_1_DESC";
			members[15].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[15].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SagRmkDesc_tHelper.id(), "SagRmkDesc_t", members[15].type);
			members[16] = new StructMember();
			members[16].name = "SAG_RMK_2_DESC";
			members[16].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[16].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SagRmkDesc_tHelper.id(), "SagRmkDesc_t", members[16].type);
			members[17] = new StructMember();
			members[17].name = "SAG_RMK_3_DESC";
			members[17].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[17].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SagRmkDesc_tHelper.id(), "SagRmkDesc_t", members[17].type);
			myTc = ORB.init().create_struct_tc (id(), "SagInfo_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t value) { 
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.EXCH_ID.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
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
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.RT_ZONE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.TAX_AREA_CD.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
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
			o.write_long (value.NPA_LST.length);
			for (int __i = 0; __i < value.NPA_LST.length; __i++) { 
				{
					byte[] _bytes = new byte[4];
					byte[] _bytes_src = value.NPA_LST[__i].getBytes();
					int _j;
					for (_j = 0; _j < 4 - 1; _j++)
						_bytes[_j] = _bytes_src[_j];
					_bytes[_j] = 0;
					o.write_octet_array (_bytes, 0, 4);
				}
			} 
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.TEL_FEAT_ID.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		o.write_char(value.HGRP_AVAIL_IND);
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.BUS_OFC_CD.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.DIR_CD.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.CO_ID.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.LATA_PREMIS.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
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
			o.write_long (value.ATTN.length);
			o.write_char_array (value.ATTN, 0, value.ATTN.length); 
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.GEO_SEG_RMK_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
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
