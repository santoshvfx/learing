// $Id: UnadrmGsgm_tHelper.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class UnadrmGsgm_tHelper { 
	private static TypeCode myTc = null;
	private UnadrmGsgm_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/UnadrmGsgm_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t();
		{ 
			int __seqLength = i.read_long();
			value.UnnbrdAddrRngeMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.UnnbrdAddrRngeMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.GeoSegMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.GeoSegMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_tHelper.read (i);
			} 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[2];
			members[0] = new StructMember();
			members[0].name = "UnnbrdAddrRngeMenu";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tHelper.type();
			members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
			members[1] = new StructMember();
			members[1].name = "GeoSegMenu";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_tHelper.type();
			members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
			myTc = ORB.init().create_struct_tc (id(), "UnadrmGsgm_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t value) { 
		{ 
			o.write_long (value.UnnbrdAddrRngeMenu.length);
			for (int __i = 0; __i < value.UnnbrdAddrRngeMenu.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tHelper.write (o, value.UnnbrdAddrRngeMenu[__i]);
			} 
		}
		{ 
			o.write_long (value.GeoSegMenu.length);
			for (int __i = 0; __i < value.GeoSegMenu.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_tHelper.write (o, value.GeoSegMenu[__i]);
			} 
		}
	}
}
