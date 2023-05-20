// $Id: infoline_stHelper.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class infoline_stHelper { 
	private static TypeCode myTc = null;
	private infoline_stHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/infoline_st:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st();
		{
			byte[] _bytes = new byte[80];
			i.read_octet_array (_bytes, 0, 80);
			int _j;
			for (_j = 0; _j < 80; _j++)
				if (_bytes[_j] == 0)
					break;
			value.infoLine = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[1];
			members[0] = new StructMember();
			members[0].name = "infoLine";
			members[0].type = ORB.init().create_array_tc (
				80, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "infoline_st", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st value) { 
		{
			byte[] _bytes = new byte[80];
			byte[] _bytes_src = value.infoLine.getBytes();
			int _j;
			for (_j = 0; _j < 80 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 80);
		}
	}
}
