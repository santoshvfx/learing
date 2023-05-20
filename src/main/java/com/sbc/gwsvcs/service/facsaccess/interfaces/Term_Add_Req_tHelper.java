package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Term_Add_Req_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Term_Add_Req_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Add_Req_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Add_Req_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Add_Req_t();
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tHelper.read (i);
		value.C1 = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tHelper.read (i);
		value.CTL = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tHelper.read (i);
		value.EPTERM = com.sbc.gwsvcs.service.facsaccess.interfaces.EPTERM_Section_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Add_Req_t value) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tHelper.write (o, value.Header);
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tHelper.write (o, value.C1);
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tHelper.write (o, value.CTL);
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPTERM_Section_tHelper.write (o, value.EPTERM);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Add_Req_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Term_Add_Req_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[4];
				members[0] = new StructMember();
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "C1";
				members[1].type = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "CTL";
				members[2].type = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tHelper.type();
				members[3] = new StructMember();
				members[3].name = "EPTERM";
				members[3].type = com.sbc.gwsvcs.service.facsaccess.interfaces.EPTERM_Section_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "Term_Add_Req_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/Term_Add_Req_t:1.0"; } 
}
