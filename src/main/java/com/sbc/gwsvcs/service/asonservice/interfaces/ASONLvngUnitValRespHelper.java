// $Id: ASONLvngUnitValRespHelper.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONLvngUnitValRespHelper { 
	private static TypeCode myTc = null;
	private ASONLvngUnitValRespHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONLvngUnitValResp:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp();
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
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.raiCode = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sagAreaId = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.wireCenter = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.communityName = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.streetDirection = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[41];
			i.read_octet_array (_bytes, 0, 41);
			int _j;
			for (_j = 0; _j < 41; _j++)
				if (_bytes[_j] == 0)
					break;
			value.streetName = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.assignedHseNumberInd = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.stNbrFld1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.stNbrFld2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.loc1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.loc2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.loc3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.loc4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.loc5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.locTag1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.locTag2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.locTag3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.locTag4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.locTag5 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.clusterCode = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.serviceAvailInd = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[23];
			i.read_octet_array (_bytes, 0, 23);
			int _j;
			for (_j = 0; _j < 23; _j++)
				if (_bytes[_j] == 0)
					break;
			value.luFiller = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.customerTN = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.customerName = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.customerAddress = new String (_bytes, 0, _j);
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
			StructMember members[] = new StructMember[30];
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
			members[4].name = "raiCode";
			members[4].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[5] = new StructMember();
			members[5].name = "sagAreaId";
			members[5].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "wireCenter";
			members[6].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "communityName";
			members[7].type = ORB.init().create_array_tc (
				21, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "streetDirection";
			members[8].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9] = new StructMember();
			members[9].name = "streetName";
			members[9].type = ORB.init().create_array_tc (
				41, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[10] = new StructMember();
			members[10].name = "assignedHseNumberInd";
			members[10].type = ORB.init().create_array_tc (
				2, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11] = new StructMember();
			members[11].name = "stNbrFld1";
			members[11].type = ORB.init().create_array_tc (
				16, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[12] = new StructMember();
			members[12].name = "stNbrFld2";
			members[12].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[13] = new StructMember();
			members[13].name = "loc1";
			members[13].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[14] = new StructMember();
			members[14].name = "loc2";
			members[14].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[15] = new StructMember();
			members[15].name = "loc3";
			members[15].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[16] = new StructMember();
			members[16].name = "loc4";
			members[16].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[17] = new StructMember();
			members[17].name = "loc5";
			members[17].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[18] = new StructMember();
			members[18].name = "locTag1";
			members[18].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[19] = new StructMember();
			members[19].name = "locTag2";
			members[19].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[20] = new StructMember();
			members[20].name = "locTag3";
			members[20].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[21] = new StructMember();
			members[21].name = "locTag4";
			members[21].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[22] = new StructMember();
			members[22].name = "locTag5";
			members[22].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[23] = new StructMember();
			members[23].name = "clusterCode";
			members[23].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[24] = new StructMember();
			members[24].name = "serviceAvailInd";
			members[24].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[25] = new StructMember();
			members[25].name = "luFiller";
			members[25].type = ORB.init().create_array_tc (
				23, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[26] = new StructMember();
			members[26].name = "customerTN";
			members[26].type = ORB.init().create_array_tc (
				11, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[27] = new StructMember();
			members[27].name = "customerName";
			members[27].type = ORB.init().create_array_tc (
				13, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[28] = new StructMember();
			members[28].name = "customerAddress";
			members[28].type = ORB.init().create_array_tc (
				21, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[29] = new StructMember();
			members[29].name = "sentEndString";
			members[29].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			myTc = ORB.init().create_struct_tc (id(), "ASONLvngUnitValResp", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp value) { 
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
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.raiCode.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.sagAreaId.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.wireCenter.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.communityName.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.streetDirection.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[41];
			byte[] _bytes_src = value.streetName.getBytes();
			int _j;
			for (_j = 0; _j < 41 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 41);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.assignedHseNumberInd.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.stNbrFld1.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.stNbrFld2.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.loc1.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.loc2.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.loc3.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.loc4.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.loc5.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.locTag1.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.locTag2.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.locTag3.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.locTag4.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.locTag5.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.clusterCode.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.serviceAvailInd.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[23];
			byte[] _bytes_src = value.luFiller.getBytes();
			int _j;
			for (_j = 0; _j < 23 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 23);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.customerTN.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.customerName.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.customerAddress.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
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
