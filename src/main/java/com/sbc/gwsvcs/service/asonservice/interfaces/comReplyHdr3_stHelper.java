// $Id: comReplyHdr3_stHelper.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class comReplyHdr3_stHelper { 
	private static TypeCode myTc = null;
	private comReplyHdr3_stHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/comReplyHdr3_st:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st();
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sagErrorCode = new String (_bytes, 0, _j);
		}
		value.saiPrimary = i.read_char ();
		value.saiAlt1 = i.read_char ();
		value.saiAlt2 = i.read_char ();
		value.saiAlt3 = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[5];
			members[0] = new StructMember();
			members[0].name = "sagErrorCode";
			members[0].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1] = new StructMember();
			members[1].name = "saiPrimary";
			members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[2] = new StructMember();
			members[2].name = "saiAlt1";
			members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[3] = new StructMember();
			members[3].name = "saiAlt2";
			members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[4] = new StructMember();
			members[4].name = "saiAlt3";
			members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			myTc = ORB.init().create_struct_tc (id(), "comReplyHdr3_st", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st value) { 
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.sagErrorCode.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		o.write_char(value.saiPrimary);
		o.write_char(value.saiAlt1);
		o.write_char(value.saiAlt2);
		o.write_char(value.saiAlt3);
	}
}
