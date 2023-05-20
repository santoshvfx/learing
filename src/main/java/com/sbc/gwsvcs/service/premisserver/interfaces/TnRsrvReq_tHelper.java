// $Id: TnRsrvReq_tHelper.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TnRsrvReq_tHelper { 
	private static TypeCode myTc = null;
	private TnRsrvReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/TnRsrvReq_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t();
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
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.GEO_SEG_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BLG_DT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.NPA_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PRFX_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TN_CTGY_CD = new String (_bytes, 0, _j);
		}
		value.TN_REQ_QTY = i.read_long ();
		value.LN_ID = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[9];
			members[0] = new StructMember();
			members[0].name = "SAGA";
			members[0].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Saga_tHelper.id(), "Saga_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "PrmAddr";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tHelper.type();
			members[2] = new StructMember();
			members[2].name = "GEO_SEG_ID";
			members[2].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegId_tHelper.id(), "GeoSegId_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "BLG_DT";
			members[3].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.BlgDt_tHelper.id(), "BlgDt_t", members[3].type);
			members[4] = new StructMember();
			members[4].name = "NPA_CD";
			members[4].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Npa_tHelper.id(), "Npa_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "PRFX_CD";
			members[5].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.PrfxCd_tHelper.id(), "PrfxCd_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "TN_CTGY_CD";
			members[6].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCd_tHelper.id(), "TnCtgyCd_t", members[6].type);
			members[7] = new StructMember();
			members[7].name = "TN_REQ_QTY";
			members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
			members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TnReqQty_tHelper.id(), "TnReqQty_t", members[7].type);
			members[8] = new StructMember();
			members[8].name = "LN_ID";
			members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[8].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.PrmLnId_tHelper.id(), "PrmLnId_t", members[8].type);
			myTc = ORB.init().create_struct_tc (id(), "TnRsrvReq_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t value) { 
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
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.GEO_SEG_ID.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.BLG_DT.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.NPA_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.PRFX_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.TN_CTGY_CD.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		o.write_long(value.TN_REQ_QTY);
		o.write_char(value.LN_ID);
	}
}
