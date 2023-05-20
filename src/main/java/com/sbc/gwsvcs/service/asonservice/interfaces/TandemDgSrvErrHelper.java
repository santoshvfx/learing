// $Id: TandemDgSrvErrHelper.java,v 1.1 2002/09/29 03:53:01 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class TandemDgSrvErrHelper { 
	private static TypeCode myTc = null;
	private TandemDgSrvErrHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/TandemDgSrvErr:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr();
		{
			byte[] _bytes = new byte[220];
			i.read_octet_array (_bytes, 0, 220);
			int _j;
			for (_j = 0; _j < 220; _j++)
				if (_bytes[_j] == 0)
					break;
			value.advisoryMsg = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[1];
			members[0] = new StructMember();
			members[0].name = "advisoryMsg";
			members[0].type = ORB.init().create_array_tc (
				220, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "TandemDgSrvErr", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr value) { 
		{
			byte[] _bytes = new byte[220];
			byte[] _bytes_src = value.advisoryMsg.getBytes();
			int _j;
			for (_j = 0; _j < 220 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 220);
		}
	}
}
