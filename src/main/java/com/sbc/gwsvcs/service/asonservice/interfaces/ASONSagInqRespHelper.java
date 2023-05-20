// $Id: ASONSagInqRespHelper.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONSagInqRespHelper { 
	private static TypeCode myTc = null;
	private ASONSagInqRespHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONSagInqResp:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp();
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
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.codeDisplay = new String (_bytes, 0, _j);
		}
		{ 
			value.sagLines = new com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.sagLines[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagline_stHelper.read (i);
			} 
		}
		{ 
			value.sagKeys = new com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.sagKeys[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_stHelper.read (i);
			} 
		}
		{
			byte[] _bytes = new byte[107];
			i.read_octet_array (_bytes, 0, 107);
			int _j;
			for (_j = 0; _j < 107; _j++)
				if (_bytes[_j] == 0)
					break;
			value.savedSagKey = new String (_bytes, 0, _j);
		}
		{ 
			value.sagByPassArea = new com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.sagByPassArea[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_stHelper.read (i);
			} 
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sentEndString = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[9];
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
			members[3].name = "codeDisplay";
			members[3].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "sagLines";
			members[4].type = com.sbc.gwsvcs.service.asonservice.interfaces.sagline_stHelper.type();
			members[4].type = ORB.init().create_array_tc (18, members[4].type);
			members[5] = new StructMember();
			members[5].name = "sagKeys";
			members[5].type = com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_stHelper.type();
			members[5].type = ORB.init().create_array_tc (18, members[5].type);
			members[6] = new StructMember();
			members[6].name = "savedSagKey";
			members[6].type = ORB.init().create_array_tc (
				107, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "sagByPassArea";
			members[7].type = com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_stHelper.type();
			members[7].type = ORB.init().create_array_tc (18, members[7].type);
			members[8] = new StructMember();
			members[8].name = "sentEndString";
			members[8].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ASONSagInqResp", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp value) { 
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
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.codeDisplay.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{ 
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				com.sbc.gwsvcs.service.asonservice.interfaces.sagline_stHelper.write (o, value.sagLines[__i0]);
			} 
		}
		{ 
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_stHelper.write (o, value.sagKeys[__i0]);
			} 
		}
		{
			byte[] _bytes = new byte[107];
			byte[] _bytes_src = value.savedSagKey.getBytes();
			int _j;
			for (_j = 0; _j < 107 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 107);
		}
		{ 
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_stHelper.write (o, value.sagByPassArea[__i0]);
			} 
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.sentEndString.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
	}
}
