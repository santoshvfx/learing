// $Id: LnDataItem_tHelper.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class LnDataItem_tHelper { 
	private static TypeCode myTc = null;
	private LnDataItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/LnDataItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t();
		value.LnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tHelper.read (i);
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MODULR_WIRE_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DISCT_REAS_DESC = new String (_bytes, 0, _j);
		}
		value.ActvtyDt = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.read (i);
		value.CONN_THRU_IND = i.read_char ();
		value.CONN_FAC_IND = i.read_char ();
		value.DED_INSD_PLNT_IND = i.read_char ();
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.COS = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[8];
			members[0] = new StructMember();
			members[0].name = "LnInfo";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "MODULR_WIRE_ID";
			members[1].type = ORB.init().create_array_tc (
				21, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.ModulrWireId_tHelper.id(), "ModulrWireId_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "DISCT_REAS_DESC";
			members[2].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.DisctReasDesc_tHelper.id(), "DisctReasDesc_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "ActvtyDt";
			members[3].type = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.type();
			members[4] = new StructMember();
			members[4].name = "CONN_THRU_IND";
			members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[4].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.B_tHelper.id(), "B_t", members[4].type);
			members[5] = new StructMember();
			members[5].name = "CONN_FAC_IND";
			members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[5].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.B_tHelper.id(), "B_t", members[5].type);
			members[6] = new StructMember();
			members[6].name = "DED_INSD_PLNT_IND";
			members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[6].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.B_tHelper.id(), "B_t", members[6].type);
			members[7] = new StructMember();
			members[7].name = "COS";
			members[7].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Cos_tHelper.id(), "Cos_t", members[7].type);
			myTc = ORB.init().create_struct_tc (id(), "LnDataItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tHelper.write (o, value.LnInfo);
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.MODULR_WIRE_ID.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.DISCT_REAS_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tHelper.write (o, value.ActvtyDt);
		o.write_char(value.CONN_THRU_IND);
		o.write_char(value.CONN_FAC_IND);
		o.write_char(value.DED_INSD_PLNT_IND);
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.COS.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
	}
}
