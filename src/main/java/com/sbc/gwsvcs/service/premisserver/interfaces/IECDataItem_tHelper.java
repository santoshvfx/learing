// $Id: IECDataItem_tHelper.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class IECDataItem_tHelper { 
	private static TypeCode myTc = null;
	private IECDataItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/IECDataItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t();
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.IEC_CD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[66];
			i.read_octet_array (_bytes, 0, 66);
			int _j;
			for (_j = 0; _j < 66; _j++)
				if (_bytes[_j] == 0)
					break;
			value.IEC_RMK_1_DESC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[66];
			i.read_octet_array (_bytes, 0, 66);
			int _j;
			for (_j = 0; _j < 66; _j++)
				if (_bytes[_j] == 0)
					break;
			value.IEC_RMK_2_DESC = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "IEC_CD";
			members[0].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.IECCd_tHelper.id(), "IECCd_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "IEC_RMK_1_DESC";
			members[1].type = ORB.init().create_array_tc (
				66, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.IECRmkDesc_tHelper.id(), "IECRmkDesc_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "IEC_RMK_2_DESC";
			members[2].type = ORB.init().create_array_tc (
				66, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.IECRmkDesc_tHelper.id(), "IECRmkDesc_t", members[2].type);
			myTc = ORB.init().create_struct_tc (id(), "IECDataItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t value) { 
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.IEC_CD.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[66];
			byte[] _bytes_src = value.IEC_RMK_1_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 66 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 66);
		}
		{
			byte[] _bytes = new byte[66];
			byte[] _bytes_src = value.IEC_RMK_2_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 66 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 66);
		}
	}
}
