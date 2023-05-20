// $Id: SuppLnInfo_tHelper.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SuppLnInfo_tHelper { 
	private static TypeCode myTc = null;
	private SuppLnInfo_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/SuppLnInfo_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t();
		value.LtdLnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tHelper.read (i);
		value.NpaPrfxLn = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_tHelper.read (i);
		value.NON_PUB_IND = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "LtdLnInfo";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "NpaPrfxLn";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_tHelper.type();
			members[2] = new StructMember();
			members[2].name = "NON_PUB_IND";
			members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.B_tHelper.id(), "B_t", members[2].type);
			myTc = ORB.init().create_struct_tc (id(), "SuppLnInfo_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tHelper.write (o, value.LtdLnInfo);
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_tHelper.write (o, value.NpaPrfxLn);
		o.write_char(value.NON_PUB_IND);
	}
}
