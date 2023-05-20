// $Id: ASONSagValidReqHelper.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONSagValidReqHelper { 
	private static TypeCode myTc = null;
	private ASONSagValidReqHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONSagValidReq:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq();
		value.requestCode = i.read_short ();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MsgLength = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.IdGroup = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.IdTypist = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.IdSystem = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RequestDateYYYYMMDD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RequestTimeHHMMSSCC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RegionalAreaId = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.community = new String (_bytes, 0, _j);
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
		value.descriptiveAddrInd = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[12];
			members[0] = new StructMember();
			members[0].name = "requestCode";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
			members[1] = new StructMember();
			members[1].name = "MsgLength";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "IdGroup";
			members[2].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "IdTypist";
			members[3].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "IdSystem";
			members[4].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "RequestDateYYYYMMDD";
			members[5].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "RequestTimeHHMMSSCC";
			members[6].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "RegionalAreaId";
			members[7].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "addressName";
			members[8].type = ORB.init().create_array_tc (
				70, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9] = new StructMember();
			members[9].name = "community";
			members[9].type = ORB.init().create_array_tc (
				21, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10] = new StructMember();
			members[10].name = "zipCode";
			members[10].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11] = new StructMember();
			members[11].name = "descriptiveAddrInd";
			members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			myTc = ORB.init().create_struct_tc (id(), "ASONSagValidReq", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq value) { 
		o.write_short(value.requestCode);
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.MsgLength.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.IdGroup.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.IdTypist.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.IdSystem.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.RequestDateYYYYMMDD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.RequestTimeHHMMSSCC.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.RegionalAreaId.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
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
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.community.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
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
		o.write_char(value.descriptiveAddrInd);
	}
}
