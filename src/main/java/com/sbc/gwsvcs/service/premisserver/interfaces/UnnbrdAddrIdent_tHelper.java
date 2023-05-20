// $Id: UnnbrdAddrIdent_tHelper.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class UnnbrdAddrIdent_tHelper { 
	private static TypeCode myTc = null;
	private UnnbrdAddrIdent_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/UnnbrdAddrIdent_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PSTL_RTE_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BOX_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[61];
			i.read_octet_array (_bytes, 0, 61);
			int _j;
			for (_j = 0; _j < 61; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LSTD_NM = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.UNNBR_SRCH_STS_IND = new String (_bytes, 0, _j);
		}
		value.GEO_SEG_REQ_IND = i.read_char ();
		value.ASGND_HOUS_NBR_LSTG_IND = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[6];
			members[0] = new StructMember();
			members[0].name = "PSTL_RTE_CD";
			members[0].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.PstlRteCd_tHelper.id(), "PstlRteCd_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "BOX_CD";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.BoxCd_tHelper.id(), "BoxCd_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "LSTD_NM";
			members[2].type = ORB.init().create_array_tc (
				61, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.LstdNm_tHelper.id(), "LstdNm_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "UNNBR_SRCH_STS_IND";
			members[3].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrSrchStsInd_tHelper.id(), "UnnbrSrchStsInd_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "GEO_SEG_REQ_IND";
			members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegReqInd_tHelper.id(), "GeoSegReqInd_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "ASGND_HOUS_NBR_LSTG_IND";
			members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrLstgInd_tHelper.id(), "AsgndHousNbrLstgInd_t", members[5].type);
			myTc = ORB.init().create_struct_tc (id(), "UnnbrdAddrIdent_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t value) { 
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.PSTL_RTE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.BOX_CD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[61];
			byte[] _bytes_src = value.LSTD_NM.getBytes();
			int _j;
			for (_j = 0; _j < 61 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 61);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.UNNBR_SRCH_STS_IND.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		o.write_char(value.GEO_SEG_REQ_IND);
		o.write_char(value.ASGND_HOUS_NBR_LSTG_IND);
	}
}
