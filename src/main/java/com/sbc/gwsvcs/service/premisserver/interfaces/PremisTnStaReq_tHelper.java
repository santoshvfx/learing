// $Id: PremisTnStaReq_tHelper.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class PremisTnStaReq_tHelper { 
	private static TypeCode myTc = null;
	private PremisTnStaReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/PremisTnStaReq_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t();
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tHelper.read (i);
		value.TnStaReq = com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[2];
			members[0] = new StructMember();
			members[0].name = "Header";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "TnStaReq";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "PremisTnStaReq_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tHelper.write (o, value.Header);
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_tHelper.write (o, value.TnStaReq);
	}
}
