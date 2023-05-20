package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ADDR_LSTFN_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ADDR_LSTFN_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t();
		{ 
			int __seqLength = i.read_long();
			value.BADR = new com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.BADR[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.SUPL = new com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SUPL[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t value) { 
		{ 
			o.write_long (value.BADR.length);
			for (int __i = 0; __i < value.BADR.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tHelper.write (o, value.BADR[__i]);
			} 
		}
		{ 
			o.write_long (value.SUPL.length);
			for (int __i = 0; __i < value.SUPL.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tHelper.write (o, value.SUPL[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[2];
				members[0] = new StructMember();
				members[0].name = "BADR";
				members[0].type = com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tHelper.type();
				members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
				members[1] = new StructMember();
				members[1].name = "SUPL";
				members[1].type = com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				myTc = ORB.init().create_struct_tc (id(), "ADDR_LSTFN_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/ADDR_LSTFN_t:1.0"; } 
}
