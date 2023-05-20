// $Id: TNAStaReq_tHelper.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TNAStaReq_tHelper { 
	private static TypeCode myTc = null;
	private TNAStaReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/TNAStaReq_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t();
		value.TN = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.read (i);
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.WC_CD = new String (_bytes, 0, _j);
		}
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "TN";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "WC_CD";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.WcCd_tHelper.id(), "WcCd_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "Scratch";
			members[2].type = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "TNAStaReq_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.write (o, value.TN);
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.WC_CD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.write (o, value.Scratch);
	}
}
