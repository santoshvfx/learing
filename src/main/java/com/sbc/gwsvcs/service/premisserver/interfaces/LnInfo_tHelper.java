// $Id: LnInfo_tHelper.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class LnInfo_tHelper { 
	private static TypeCode myTc = null;
	private LnInfo_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/LnInfo_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t();
		value.LN_ID = i.read_char ();
		value.SuppLnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[2];
			members[0] = new StructMember();
			members[0].name = "LN_ID";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.PrmLnId_tHelper.id(), "PrmLnId_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "SuppLnInfo";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "LnInfo_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t value) { 
		o.write_char(value.LN_ID);
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tHelper.write (o, value.SuppLnInfo);
	}
}