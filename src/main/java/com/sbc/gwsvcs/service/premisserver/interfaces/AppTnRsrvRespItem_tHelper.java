// $Id: AppTnRsrvRespItem_tHelper.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class AppTnRsrvRespItem_tHelper { 
	private static TypeCode myTc = null;
	private AppTnRsrvRespItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/AppTnRsrvRespItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t();
		value.TnRsrvReq = com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tHelper.read (i);
		value.TnaFacActnLnItem = com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[2];
			members[0] = new StructMember();
			members[0].name = "TnRsrvReq";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "TnaFacActnLnItem";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "AppTnRsrvRespItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tHelper.write (o, value.TnRsrvReq);
		com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tHelper.write (o, value.TnaFacActnLnItem);
	}
}
