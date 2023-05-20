// $Id: comReplyHdr1_stHelper.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class comReplyHdr1_stHelper { 
	private static TypeCode myTc = null;
	private comReplyHdr1_stHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/comReplyHdr1_st:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st();
		value.ReplyCode = i.read_short ();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MsgLength = new String (_bytes, 0, _j);
		}
		value.TmfAction = i.read_char ();
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
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ReplyDateYYYYMMDD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ReplyTimeHHMMSSCC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MsgCode = new String (_bytes, 0, _j);
		}
		value.MsgDelimiter = i.read_char ();
		{
			byte[] _bytes = new byte[31];
			i.read_octet_array (_bytes, 0, 31);
			int _j;
			for (_j = 0; _j < 31; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MsgText = new String (_bytes, 0, _j);
		}
		value.XDRalignFiller = i.read_char ();
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[11];
			members[0] = new StructMember();
			members[0].name = "ReplyCode";
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
			members[1] = new StructMember();
			members[1].name = "MsgLength";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2] = new StructMember();
			members[2].name = "TmfAction";
			members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[3] = new StructMember();
			members[3].name = "RequestDateYYYYMMDD";
			members[3].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[4] = new StructMember();
			members[4].name = "RequestTimeHHMMSSCC";
			members[4].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "ReplyDateYYYYMMDD";
			members[5].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "ReplyTimeHHMMSSCC";
			members[6].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "MsgCode";
			members[7].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "MsgDelimiter";
			members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[9] = new StructMember();
			members[9].name = "MsgText";
			members[9].type = ORB.init().create_array_tc (
				31, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10] = new StructMember();
			members[10].name = "XDRalignFiller";
			members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			myTc = ORB.init().create_struct_tc (id(), "comReplyHdr1_st", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st value) { 
		o.write_short(value.ReplyCode);
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.MsgLength.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		o.write_char(value.TmfAction);
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
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.ReplyDateYYYYMMDD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.ReplyTimeHHMMSSCC.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.MsgCode.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		o.write_char(value.MsgDelimiter);
		{
			byte[] _bytes = new byte[31];
			byte[] _bytes_src = value.MsgText.getBytes();
			int _j;
			for (_j = 0; _j < 31 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 31);
		}
		o.write_char(value.XDRalignFiller);
	}
}
