// $Id: UnadrmGsgmProcStatus_tHelper.java,v 1.1 2002/09/29 04:28:16 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class UnadrmGsgmProcStatus_tHelper { 
	private static TypeCode myTc = null;
	private UnadrmGsgmProcStatus_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/UnadrmGsgmProcStatus_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t();
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RTCD = new String (_bytes, 0, _j);
		}
		value.UnadrmGsgm = com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[2];
			members[0] = new StructMember();
			members[0].name = "RTCD";
			members[0].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.RTCD_tHelper.id(), "RTCD_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "UnadrmGsgm";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "UnadrmGsgmProcStatus_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t value) { 
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.RTCD.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tHelper.write (o, value.UnadrmGsgm);
	}
}
