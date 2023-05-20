// $Id: ASONSagValidDescErrHelper.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONSagValidDescErrHelper { 
	private static TypeCode myTc = null;
	private ASONSagValidDescErrHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONSagValidDescErr:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr();
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.read (i);
		value.comReplyHdr2 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.read (i);
		value.comReplyHdr3 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stHelper.read (i);
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmk1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmk2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmk3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmk4 = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[7];
			members[0] = new StructMember();
			members[0].name = "comReplyHdr1";
			members[0].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.type();
			members[1] = new StructMember();
			members[1].name = "comReplyHdr2";
			members[1].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.type();
			members[2] = new StructMember();
			members[2].name = "comReplyHdr3";
			members[2].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stHelper.type();
			members[3] = new StructMember();
			members[3].name = "descAddrRmk1";
			members[3].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "descAddrRmk2";
			members[4].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "descAddrRmk3";
			members[5].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "descAddrRmk4";
			members[6].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ASONSagValidDescErr", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr value) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.write (o, value.comReplyHdr1);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.write (o, value.comReplyHdr2);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stHelper.write (o, value.comReplyHdr3);
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmk1.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmk2.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmk3.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmk4.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
	}
}
