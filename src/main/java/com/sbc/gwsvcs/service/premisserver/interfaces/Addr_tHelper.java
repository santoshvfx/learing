// $Id: Addr_tHelper.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Addr_tHelper { 
	private static TypeCode myTc = null;
	private Addr_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/Addr_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t();
		value.BascAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tHelper.read (i);
		value.SuppAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tHelper.read (i);
		value.UnnbrdAddrIdent = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_tHelper.read (i);
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DESC_ADDR = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[4];
			members[0] = new StructMember();
			members[0].name = "BascAddrInfo";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "SuppAddrInfo";
			members[1].type = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tHelper.type();
			members[2] = new StructMember();
			members[2].name = "UnnbrdAddrIdent";
			members[2].type = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_tHelper.type();
			members[3] = new StructMember();
			members[3].name = "DESC_ADDR";
			members[3].type = ORB.init().create_array_tc (
				51, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.DescAddr_tHelper.id(), "DescAddr_t", members[3].type);
			myTc = ORB.init().create_struct_tc (id(), "Addr_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tHelper.write (o, value.BascAddrInfo);
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tHelper.write (o, value.SuppAddrInfo);
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_tHelper.write (o, value.UnnbrdAddrIdent);
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.DESC_ADDR.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
	}
}
