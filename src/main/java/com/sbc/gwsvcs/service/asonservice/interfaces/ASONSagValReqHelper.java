// $Id: ASONSagValReqHelper.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONSagValReqHelper { 
	private static TypeCode myTc = null;
	private ASONSagValReqHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONSagValReq:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq();
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.read (i);
		value.requestCode = i.read_short ();
		value.commandLine = com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stHelper.read (i);
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.dateKey = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.functionKeyDepressed = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.idGroup = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.idTerminal = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.idTypist = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.regionalAreaId = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.timeKey = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[70];
			i.read_octet_array (_bytes, 0, 70);
			int _j;
			for (_j = 0; _j < 70; _j++)
				if (_bytes[_j] == 0)
					break;
			value.addressName = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.zipCode = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.community = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[107];
			i.read_octet_array (_bytes, 0, 107);
			int _j;
			for (_j = 0; _j < 107; _j++)
				if (_bytes[_j] == 0)
					break;
			value.holdSagKey = new String (_bytes, 0, _j);
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
			StructMember members[] = new StructMember[15];
			members[0] = new StructMember();
			members[0].name = "tagInformation";
			members[0].type = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.type();
			members[1] = new StructMember();
			members[1].name = "requestCode";
			members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
			members[2] = new StructMember();
			members[2].name = "commandLine";
			members[2].type = com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stHelper.type();
			members[3] = new StructMember();
			members[3].name = "dateKey";
			members[3].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "functionKeyDepressed";
			members[4].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "idGroup";
			members[5].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "idTerminal";
			members[6].type = ORB.init().create_array_tc (
				16, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "idTypist";
			members[7].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "regionalAreaId";
			members[8].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9] = new StructMember();
			members[9].name = "timeKey";
			members[9].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10] = new StructMember();
			members[10].name = "addressName";
			members[10].type = ORB.init().create_array_tc (
				70, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11] = new StructMember();
			members[11].name = "zipCode";
			members[11].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[12] = new StructMember();
			members[12].name = "community";
			members[12].type = ORB.init().create_array_tc (
				21, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[13] = new StructMember();
			members[13].name = "holdSagKey";
			members[13].type = ORB.init().create_array_tc (
				107, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[14] = new StructMember();
			members[14].name = "sentEndString";
			members[14].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ASONSagValReq", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq value) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.write (o, value.tagInformation);
		o.write_short(value.requestCode);
		com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stHelper.write (o, value.commandLine);
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.dateKey.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.functionKeyDepressed.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.idGroup.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.idTerminal.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.idTypist.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.regionalAreaId.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.timeKey.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[70];
			byte[] _bytes_src = value.addressName.getBytes();
			int _j;
			for (_j = 0; _j < 70 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 70);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.zipCode.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.community.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[107];
			byte[] _bytes_src = value.holdSagKey.getBytes();
			int _j;
			for (_j = 0; _j < 107 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 107);
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
