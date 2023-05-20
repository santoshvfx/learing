// $Id: openDates_stHelper.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class openDates_stHelper { 
	private static TypeCode myTc = null;
	private openDates_stHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/openDates_st:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st();
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.weekDay1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.mmDD1 = new String (_bytes, 0, _j);
		}
		value.filler1 = i.read_char ();
		value.amOrPmOrAll1 = i.read_char ();
		value.filler2 = i.read_char ();
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.weekDay2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.mmDD2 = new String (_bytes, 0, _j);
		}
		value.filler3 = i.read_char ();
		value.amOrPmOrAll2 = i.read_char ();
		value.filler4 = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[10];
			members[0] = new StructMember();
			members[0].name = "weekDay1";
			members[0].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1] = new StructMember();
			members[1].name = "mmDD1";
			members[1].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "filler1";
			members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[3] = new StructMember();
			members[3].name = "amOrPmOrAll1";
			members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[4] = new StructMember();
			members[4].name = "filler2";
			members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[5] = new StructMember();
			members[5].name = "weekDay2";
			members[5].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "mmDD2";
			members[6].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "filler3";
			members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[8] = new StructMember();
			members[8].name = "amOrPmOrAll2";
			members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[9] = new StructMember();
			members[9].name = "filler4";
			members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			myTc = ORB.init().create_struct_tc (id(), "openDates_st", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st value) { 
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.weekDay1.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.mmDD1.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		o.write_char(value.filler1);
		o.write_char(value.amOrPmOrAll1);
		o.write_char(value.filler2);
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.weekDay2.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.mmDD2.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		o.write_char(value.filler3);
		o.write_char(value.amOrPmOrAll2);
		o.write_char(value.filler4);
	}
}
