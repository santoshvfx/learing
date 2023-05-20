// $Id: FacActnLnItem_tHelper.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class FacActnLnItem_tHelper { 
	private static TypeCode myTc = null;
	private FacActnLnItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/FacActnLnItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SAGA = new String (_bytes, 0, _j);
		}
		value.PrmAddr = com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tHelper.read (i);
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ALT_CMTY_1_NM = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ALT_CMTY_2_NM = new String (_bytes, 0, _j);
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
		{
			byte[] _bytes = new byte[68];
			i.read_octet_array (_bytes, 0, 68);
			int _j;
			for (_j = 0; _j < 68; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ALT_ADDR_NM = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DESC_ADDR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LOC_STD = new String (_bytes, 0, _j);
		}
		value.SagInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tHelper.read (i);
		value.CMTY_NM_RQRD_IND = i.read_char ();
		value.STATE_NM_RQRD_IND = i.read_char ();
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEL_FEAT_RMK_1_DESC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEL_FEAT_RMK_2_DESC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEL_FEAT_RMK_3_DESC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEL_FEAT_RMK_4_DESC = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.LnData = new com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.LnData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.SwngEntyData = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SwngEntyData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.RmkData = new com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.RmkData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_tHelper.read (i);
			} 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[18];
			members[0] = new StructMember();
			members[0].name = "SAGA";
			members[0].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Saga_tHelper.id(), "Saga_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "PrmAddr";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tHelper.type();
			members[2] = new StructMember();
			members[2].name = "ALT_CMTY_1_NM";
			members[2].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CmtyNm_tHelper.id(), "CmtyNm_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "ALT_CMTY_2_NM";
			members[3].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CmtyNm_tHelper.id(), "CmtyNm_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "SPL_INSTR";
			members[4].type = ORB.init().create_array_tc (
				101, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SplInstr_tHelper.id(), "SplInstr_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "ALT_ADDR_NM";
			members[5].type = ORB.init().create_array_tc (
				68, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.AltAddrNm_tHelper.id(), "AltAddrNm_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "DESC_ADDR";
			members[6].type = ORB.init().create_array_tc (
				51, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.DescAddr_tHelper.id(), "DescAddr_t", members[6].type);
			members[7] = new StructMember();
			members[7].name = "LOC_STD";
			members[7].type = ORB.init().create_array_tc (
				51, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.LocStd_tHelper.id(), "LocStd_t", members[7].type);
			members[8] = new StructMember();
			members[8].name = "SagInfo";
			members[8].type = com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tHelper.type();
			members[9] = new StructMember();
			members[9].name = "CMTY_NM_RQRD_IND";
			members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[9].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Pos_tHelper.id(), "Pos_t", members[9].type);
			members[10] = new StructMember();
			members[10].name = "STATE_NM_RQRD_IND";
			members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[10].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Pos_tHelper.id(), "Pos_t", members[10].type);
			members[11] = new StructMember();
			members[11].name = "TEL_FEAT_RMK_1_DESC";
			members[11].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[11].type);
			members[12] = new StructMember();
			members[12].name = "TEL_FEAT_RMK_2_DESC";
			members[12].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[12].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[12].type);
			members[13] = new StructMember();
			members[13].name = "TEL_FEAT_RMK_3_DESC";
			members[13].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[13].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[13].type);
			members[14] = new StructMember();
			members[14].name = "TEL_FEAT_RMK_4_DESC";
			members[14].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[14].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[14].type);
			members[15] = new StructMember();
			members[15].name = "LnData";
			members[15].type = com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_tHelper.type();
			members[15].type = ORB.init().create_sequence_tc (0, members[15].type);
			members[16] = new StructMember();
			members[16].name = "SwngEntyData";
			members[16].type = com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tHelper.type();
			members[16].type = ORB.init().create_sequence_tc (0, members[16].type);
			members[17] = new StructMember();
			members[17].name = "RmkData";
			members[17].type = com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_tHelper.type();
			members[17].type = ORB.init().create_sequence_tc (15, members[17].type);
			myTc = ORB.init().create_struct_tc (id(), "FacActnLnItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t value) { 
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.SAGA.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tHelper.write (o, value.PrmAddr);
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.ALT_CMTY_1_NM.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.ALT_CMTY_2_NM.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
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
		{
			byte[] _bytes = new byte[68];
			byte[] _bytes_src = value.ALT_ADDR_NM.getBytes();
			int _j;
			for (_j = 0; _j < 68 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 68);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.DESC_ADDR.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.LOC_STD.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tHelper.write (o, value.SagInfo);
		o.write_char(value.CMTY_NM_RQRD_IND);
		o.write_char(value.STATE_NM_RQRD_IND);
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.TEL_FEAT_RMK_1_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.TEL_FEAT_RMK_2_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.TEL_FEAT_RMK_3_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.TEL_FEAT_RMK_4_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{ 
			o.write_long (value.LnData.length);
			for (int __i = 0; __i < value.LnData.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_tHelper.write (o, value.LnData[__i]);
			} 
		}
		{ 
			o.write_long (value.SwngEntyData.length);
			for (int __i = 0; __i < value.SwngEntyData.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tHelper.write (o, value.SwngEntyData[__i]);
			} 
		}
		{ 
			o.write_long (value.RmkData.length);
			for (int __i = 0; __i < value.RmkData.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_tHelper.write (o, value.RmkData[__i]);
			} 
		}
	}
}
