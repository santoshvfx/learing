// $Id: ASONErrorRespHelper.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONErrorRespHelper { 
	private static TypeCode myTc = null;
	private ASONErrorRespHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONErrorResp:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp();
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.read (i);
		value.replyCode = i.read_short ();
		{
			byte[] _bytes = new byte[36];
			i.read_octet_array (_bytes, 0, 36);
			int _j;
			for (_j = 0; _j < 36; _j++)
				if (_bytes[_j] == 0)
					break;
			value.advisoryMsg = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sendEndString = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[4];
			members[0] = new StructMember();
			members[0].name = "tagInformation";
			members[0].type = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.type();
			members[1] = new StructMember();
			members[1].name = "replyCode";
			members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
			members[2] = new StructMember();
			members[2].name = "advisoryMsg";
			members[2].type = ORB.init().create_array_tc (
				36, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "sendEndString";
			members[3].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ASONErrorResp", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp value) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.write (o, value.tagInformation);
		o.write_short(value.replyCode);
		{
			byte[] _bytes = new byte[36];
			byte[] _bytes_src = value.advisoryMsg.getBytes();
			int _j;
			for (_j = 0; _j < 36 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 36);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.sendEndString.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
	}
}
