// $Id: TnaFacActnLnItem_tHelper.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TnaFacActnLnItem_tHelper { 
	private static TypeCode myTc = null;
	private TnaFacActnLnItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/TnaFacActnLnItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t();
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
			int __seqLength = i.read_long();
			value.TnSucc = new com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.TnSucc[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_tHelper.read (i);
			} 
		}
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
			value.AddlLnData = new com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AddlLnData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.SwngEntyData = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SwngEntyData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tHelper.read (i);
			} 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[9];
			members[0] = new StructMember();
			members[0].name = "CMTY_NM";
			members[0].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CmtyNm_tHelper.id(), "CmtyNm_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "STATE_CD";
			members[1].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.StateCd_tHelper.id(), "StateCd_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "TnSucc";
			members[2].type = com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_tHelper.type();
			members[2].type = ORB.init().create_sequence_tc (5, members[2].type);
			members[3] = new StructMember();
			members[3].name = "TEL_FEAT_RMK_1_DESC";
			members[3].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "TEL_FEAT_RMK_2_DESC";
			members[4].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "TEL_FEAT_RMK_3_DESC";
			members[5].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "TEL_FEAT_RMK_4_DESC";
			members[6].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TelFeatRmkDesc_tHelper.id(), "TelFeatRmkDesc_t", members[6].type);
			members[7] = new StructMember();
			members[7].name = "AddlLnData";
			members[7].type = com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_tHelper.type();
			members[7].type = ORB.init().create_sequence_tc (0, members[7].type);
			members[8] = new StructMember();
			members[8].name = "SwngEntyData";
			members[8].type = com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tHelper.type();
			members[8].type = ORB.init().create_sequence_tc (0, members[8].type);
			myTc = ORB.init().create_struct_tc (id(), "TnaFacActnLnItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t value) { 
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
			o.write_long (value.TnSucc.length);
			for (int __i = 0; __i < value.TnSucc.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_tHelper.write (o, value.TnSucc[__i]);
			} 
		}
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
			o.write_long (value.AddlLnData.length);
			for (int __i = 0; __i < value.AddlLnData.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_tHelper.write (o, value.AddlLnData[__i]);
			} 
		}
		{ 
			o.write_long (value.SwngEntyData.length);
			for (int __i = 0; __i < value.SwngEntyData.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tHelper.write (o, value.SwngEntyData[__i]);
			} 
		}
	}
}
