// $Id: LtdLnInfo_tHelper.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class LtdLnInfo_tHelper { 
	private static TypeCode myTc = null;
	private LtdLnInfo_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/LtdLnInfo_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LN_STS_ID = new String (_bytes, 0, _j);
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
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[2];
			members[0] = new StructMember();
			members[0].name = "LN_STS_ID";
			members[0].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.LnStsId_tHelper.id(), "LnStsId_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "LSTD_NM";
			members[1].type = ORB.init().create_array_tc (
				61, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.LstdNm_tHelper.id(), "LstdNm_t", members[1].type);
			myTc = ORB.init().create_struct_tc (id(), "LtdLnInfo_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t value) { 
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.LN_STS_ID.getBytes();
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
	}
}
