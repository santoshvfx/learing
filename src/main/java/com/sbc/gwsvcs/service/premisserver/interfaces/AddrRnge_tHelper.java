// $Id: AddrRnge_tHelper.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class AddrRnge_tHelper { 
	private static TypeCode myTc = null;
	private AddrRnge_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/AddrRnge_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t();
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LOW_HOUS_NBR_VALU_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.HI_HOUS_NBR_VALU_ID = new String (_bytes, 0, _j);
		}
		value.RNGE_IND = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "LOW_HOUS_NBR_VALU_ID";
			members[0].type = ORB.init().create_array_tc (
				14, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.HousNbrValuId_tHelper.id(), "HousNbrValuId_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "HI_HOUS_NBR_VALU_ID";
			members[1].type = ORB.init().create_array_tc (
				14, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.HousNbrValuId_tHelper.id(), "HousNbrValuId_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "RNGE_IND";
			members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.RngeInd_tHelper.id(), "RngeInd_t", members[2].type);
			myTc = ORB.init().create_struct_tc (id(), "AddrRnge_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t value) { 
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.LOW_HOUS_NBR_VALU_ID.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.HI_HOUS_NBR_VALU_ID.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		o.write_char(value.RNGE_IND);
	}
}
