// $Id: BascAddrMenuPktResp_tHelper.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class BascAddrMenuPktResp_tHelper { 
	private static TypeCode myTc = null;
	private BascAddrMenuPktResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/BascAddrMenuPktResp_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t();
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.BascAddrMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.BascAddrMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_tHelper.read (i);
			} 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[2];
			members[0] = new StructMember();
			members[0].name = "TS";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "BascAddrMenuProcStatus";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_tHelper.type();
			members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
			myTc = ORB.init().create_struct_tc (id(), "BascAddrMenuPktResp_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tHelper.write (o, value.TS);
		{ 
			o.write_long (value.BascAddrMenuProcStatus.length);
			for (int __i = 0; __i < value.BascAddrMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_tHelper.write (o, value.BascAddrMenuProcStatus[__i]);
			} 
		}
	}
}
