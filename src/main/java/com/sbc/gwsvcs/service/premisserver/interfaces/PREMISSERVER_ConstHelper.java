// $Id: PREMISSERVER_ConstHelper.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.*;

public class PREMISSERVER_ConstHelper { 
	private static TypeCode myTc = null;
	private PREMISSERVER_ConstHelper() { }
	public static PREMISSERVER_Const extract (org.omg.CORBA.Any a) { 
		InputStream i = a.create_input_stream();
		return read (i); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/PREMISSERVER_Const:1.0"; }
	public static void insert (org.omg.CORBA.Any a, PREMISSERVER_Const t) { 
		OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static PREMISSERVER_Const narrow (org.omg.CORBA.Object obj) throws BAD_PARAM { 
		if (obj == null) 
			return null; 
		if (obj instanceof PREMISSERVER_Const) 
			return (PREMISSERVER_Const) obj; 
		if (!obj._is_a(id())) 
			throw new BAD_PARAM(); 
		Delegate dup = ((ObjectImpl) obj)._get_delegate();
		PREMISSERVER_Const ret = new _PREMISSERVER_ConstStub (dup);
		return ret; 
	}
	public static PREMISSERVER_Const read (InputStream i) { 
		return PREMISSERVER_ConstHelper.narrow (i.read_Object()); 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) 
			myTc = org.omg.CORBA.ORB.init().create_interface_tc(id(), "PREMISSERVER_Const"); 
		return myTc; 
	}
	public static void write (OutputStream o, PREMISSERVER_Const val) { 
		o.write_Object (val); 
	}
}
