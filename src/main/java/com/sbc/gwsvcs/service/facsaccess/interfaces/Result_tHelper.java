package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Result_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Result_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t();
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tHelper.read (i);
		value.C1 = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tHelper.read (i);
		value.CTL = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.RESP = new com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.RESP[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tHelper.read (i);
			} 
		}
		value.ECTERM = com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_tHelper.read (i);
		value.ECCABLE = com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tHelper.read (i);
		value.ECPRAT = com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_tHelper.read (i);
		value.ELMU = com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tHelper.read (i);
		value.EPAF = com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.LOOP = new com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.LOOP[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t value) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tHelper.write (o, value.Header);
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tHelper.write (o, value.C1);
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tHelper.write (o, value.CTL);
		{ 
			o.write_long (value.RESP.length);
			for (int __i = 0; __i < value.RESP.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tHelper.write (o, value.RESP[__i]);
			} 
		}
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_tHelper.write (o, value.ECTERM);
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tHelper.write (o, value.ECCABLE);
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_tHelper.write (o, value.ECPRAT);
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tHelper.write (o, value.ELMU);
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tHelper.write (o, value.EPAF);
		{ 
			o.write_long (value.LOOP.length);
			for (int __i = 0; __i < value.LOOP.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_tHelper.write (o, value.LOOP[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[10];
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
				members[3].name = "RESP";
				members[3].type = com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tHelper.type();
				members[3].type = ORB.init().create_sequence_tc (0, members[3].type);
				members[4] = new StructMember();
				members[4].name = "ECTERM";
				members[4].type = com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_tHelper.type();
				members[5] = new StructMember();
				members[5].name = "ECCABLE";
				members[5].type = com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tHelper.type();
				members[6] = new StructMember();
				members[6].name = "ECPRAT";
				members[6].type = com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_tHelper.type();
				members[7] = new StructMember();
				members[7].name = "ELMU";
				members[7].type = com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tHelper.type();
				members[8] = new StructMember();
				members[8].name = "EPAF";
				members[8].type = com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tHelper.type();
				members[9] = new StructMember();
				members[9].name = "LOOP";
				members[9].type = com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_tHelper.type();
				members[9].type = ORB.init().create_sequence_tc (0, members[9].type);
				myTc = ORB.init().create_struct_tc (id(), "Result_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/Result_t:1.0"; } 
}
