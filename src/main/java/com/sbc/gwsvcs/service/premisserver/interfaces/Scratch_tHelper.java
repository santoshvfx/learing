// $Id: Scratch_tHelper.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Scratch_tHelper { 
	private static TypeCode myTc = null;
	private Scratch_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/Scratch_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		{ 
			int __seqLength = i.read_long();
			value.SCRATCH = new char[__seqLength];
			i.read_char_array (value.SCRATCH, 0, __seqLength); 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[1];
			members[0] = new StructMember();
			members[0].name = "SCRATCH";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
			myTc = ORB.init().create_struct_tc (id(), "Scratch_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t value) { 
		{ 
			o.write_long (value.SCRATCH.length);
			o.write_char_array (value.SCRATCH, 0, value.SCRATCH.length); 
		}
	}
}
