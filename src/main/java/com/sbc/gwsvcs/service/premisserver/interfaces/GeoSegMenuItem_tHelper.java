// $Id: GeoSegMenuItem_tHelper.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class GeoSegMenuItem_tHelper { 
	private static TypeCode myTc = null;
	private GeoSegMenuItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/GeoSegMenuItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.GEO_SEG_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LOW_ASGND_HOUS_NBR_VALU_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.HI_ASGND_HOUS_NBR_VALU_ID = new String (_bytes, 0, _j);
		}
		value.ASGND_HOUS_NBR_RNGE_IND = i.read_char ();
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
		value.SagInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[7];
			members[0] = new StructMember();
			members[0].name = "GEO_SEG_ID";
			members[0].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegId_tHelper.id(), "GeoSegId_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "LOW_ASGND_HOUS_NBR_VALU_ID";
			members[1].type = ORB.init().create_array_tc (
				14, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.HousNbrValuId_tHelper.id(), "HousNbrValuId_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "HI_ASGND_HOUS_NBR_VALU_ID";
			members[2].type = ORB.init().create_array_tc (
				14, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.HousNbrValuId_tHelper.id(), "HousNbrValuId_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "ASGND_HOUS_NBR_RNGE_IND";
			members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.AHNRInd_tHelper.id(), "AHNRInd_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "CMTY_NM";
			members[4].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CmtyNm_tHelper.id(), "CmtyNm_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "STATE_CD";
			members[5].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.StateCd_tHelper.id(), "StateCd_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "SagInfo";
			members[6].type = com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "GeoSegMenuItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t value) { 
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.GEO_SEG_ID.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.LOW_ASGND_HOUS_NBR_VALU_ID.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.HI_ASGND_HOUS_NBR_VALU_ID.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		o.write_char(value.ASGND_HOUS_NBR_RNGE_IND);
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
		com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tHelper.write (o, value.SagInfo);
	}
}
