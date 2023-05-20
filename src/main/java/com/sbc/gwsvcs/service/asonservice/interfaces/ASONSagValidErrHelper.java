// $Id: ASONSagValidErrHelper.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONSagValidErrHelper { 
	private static TypeCode myTc = null;
	private ASONSagValidErrHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONSagValidErr:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr();
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.read (i);
		value.comReplyHdr2 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.read (i);
		value.comReplyHdr3 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stHelper.read (i);
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "comReplyHdr1";
			members[0].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.type();
			members[1] = new StructMember();
			members[1].name = "comReplyHdr2";
			members[1].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.type();
			members[2] = new StructMember();
			members[2].name = "comReplyHdr3";
			members[2].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stHelper.type();
			myTc = ORB.init().create_struct_tc (id(), "ASONSagValidErr", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr value) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.write (o, value.comReplyHdr1);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.write (o, value.comReplyHdr2);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stHelper.write (o, value.comReplyHdr3);
	}
}
