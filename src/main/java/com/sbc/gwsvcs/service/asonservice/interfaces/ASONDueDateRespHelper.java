// $Id: ASONDueDateRespHelper.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONDueDateRespHelper { 
	private static TypeCode myTc = null;
	private ASONDueDateRespHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONDueDateResp:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp();
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
		value.codeDisplay = i.read_char ();
		{
			byte[] _bytes = new byte[157];
			i.read_octet_array (_bytes, 0, 157);
			int _j;
			for (_j = 0; _j < 157; _j++)
				if (_bytes[_j] == 0)
					break;
			value.stuffWeDoNotNeed = new String (_bytes, 0, _j);
		}
		{ 
			value.openDates = new com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st[15];
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				value.openDates[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.openDates_stHelper.read (i);
			} 
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.helpTextKey = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[6];
			members[0] = new StructMember();
			members[0].name = "replyCode";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
			members[1] = new StructMember();
			members[1].name = "advisoryMsg";
			members[1].type = ORB.init().create_array_tc (
				36, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "codeDisplay";
			members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[3] = new StructMember();
			members[3].name = "stuffWeDoNotNeed";
			members[3].type = ORB.init().create_array_tc (
				157, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "openDates";
			members[4].type = com.sbc.gwsvcs.service.asonservice.interfaces.openDates_stHelper.type();
			members[4].type = ORB.init().create_array_tc (15, members[4].type);
			members[5] = new StructMember();
			members[5].name = "helpTextKey";
			members[5].type = ORB.init().create_array_tc (
				12, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ASONDueDateResp", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp value) { 
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
		o.write_char(value.codeDisplay);
		{
			byte[] _bytes = new byte[157];
			byte[] _bytes_src = value.stuffWeDoNotNeed.getBytes();
			int _j;
			for (_j = 0; _j < 157 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 157);
		}
		{ 
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				com.sbc.gwsvcs.service.asonservice.interfaces.openDates_stHelper.write (o, value.openDates[__i0]);
			} 
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.helpTextKey.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
	}
}
