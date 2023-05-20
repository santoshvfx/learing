package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.*;
import org.omg.CORBA.portable.*;

public class TrnsptType_eHelper { 
	private static TypeCode myTc = null;
	private TrnsptType_eHelper() {
	}
	public static void write (OutputStream out, TrnsptType_e value) { 
		out.write_long (value.value()); 
	}
	public static TrnsptType_e read (InputStream in) { 
		return TrnsptType_e.from_int (in.read_long()); 
	}
	public static TrnsptType_e extract (Any a) { 
		InputStream in = a.create_input_stream();
		return read(in); 
	}
	public static void insert (Any a, TrnsptType_e val) { 
		OutputStream out = a.create_output_stream();
		write (out, val); 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			String names[] = new String[3];
			names[0] = "FILE_TRNSPT";
			names[1] = "QUE_TRNSPT";
			names[2] = "RPC_TRNSPT";
			myTc = ORB.init().create_enum_tc (id(), "TrnsptType_e", names); 
		}
		return myTc; 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/TrnsptType_e:1.0"; } 
}
