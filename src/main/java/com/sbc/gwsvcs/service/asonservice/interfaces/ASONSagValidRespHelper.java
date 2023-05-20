// $Id: ASONSagValidRespHelper.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ASONSagValidRespHelper { 
	private static TypeCode myTc = null;
	private ASONSagValidRespHelper () {
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/asonservice/interfaces/ASONSagValidResp:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp();
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.read (i);
		value.comReplyHdr2 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.read (i);
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.raiCode = new String (_bytes, 0, _j);
		}
		value.sagAreaId = i.read_char ();
		value.alphaNumInd = i.read_char ();
		{
			byte[] _bytes = new byte[41];
			i.read_octet_array (_bytes, 0, 41);
			int _j;
			for (_j = 0; _j < 41; _j++)
				if (_bytes[_j] == 0)
					break;
			value.addressNameSag = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.directional = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.highAddrRange = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.lowAddrRange = new String (_bytes, 0, _j);
		}
		value.oddEvenIndicator = i.read_char ();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.exchange = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.centralOffice = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.map = new String (_bytes, 0, _j);
		}
		value.rateBand = i.read_char ();
		value.rateZone = i.read_char ();
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.zipCodeSag = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.npa = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.countyAbbrev = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.municipality = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sagWireCenter = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.communitySag = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.state = new String (_bytes, 0, _j);
		}
		value.needBillAddrInd = i.read_char ();
		value.editAgainstLufFile = i.read_char ();
		value.needLocLevel1Ind = i.read_char ();
		value.needLocLevel2Ind = i.read_char ();
		value.needLocLevel3Ind = i.read_char ();
		value.needCommNameInd = i.read_char ();
		value.secondLineInd = i.read_char ();
		value.metroOptSvcInd = i.read_char ();
		value.omitCentralOfficeInd = i.read_char ();
		value.remarksInd = i.read_char ();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.lastAssignedHouseNumUsed = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cityAbbreviation = new String (_bytes, 0, _j);
		}
		value.populateCommNameInd = i.read_char ();
		value.alternateAddressInd = i.read_char ();
		value.lfacsDupAddressInd = i.read_char ();
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.equipmentType = new String (_bytes, 0, _j);
		}
		value.analogOrDigitalType = i.read_char ();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tar = new String (_bytes, 0, _j);
		}
		value.tnSplitSwitch = i.read_char ();
		value.busRateBand = i.read_char ();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.remoteOrHostType = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.alternateNpa = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.addrRmks1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.addrRmks2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.addrRmks3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.addrRmks4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmks1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmks2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmks3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.descAddrRmks4 = new String (_bytes, 0, _j);
		}
		value.matchInd = i.read_char ();
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.lata = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[28];
			i.read_octet_array (_bytes, 0, 28);
			int _j;
			for (_j = 0; _j < 28; _j++)
				if (_bytes[_j] == 0)
					break;
			value.filler1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.e911Sur = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.e911Exempt = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.e911Nrg = new String (_bytes, 0, _j);
		}
		value.operSur4Ind = i.read_char ();
		value.operSur16Ind = i.read_char ();
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.facsWireCenter = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.primaryLso = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.NbrOfNpaNxx = new String (_bytes, 0, _j);
		}
		{ 
			value.npaNxx = new com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_st[144];
			for (int __i0 = 0; __i0 < 144; __i0++) { 
				value.npaNxx[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_stHelper.read (i);
			} 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[64];
			members[0] = new StructMember();
			members[0].name = "comReplyHdr1";
			members[0].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.type();
			members[1] = new StructMember();
			members[1].name = "comReplyHdr2";
			members[1].type = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.type();
			members[2] = new StructMember();
			members[2].name = "raiCode";
			members[2].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[3] = new StructMember();
			members[3].name = "sagAreaId";
			members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[4] = new StructMember();
			members[4].name = "alphaNumInd";
			members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[5] = new StructMember();
			members[5].name = "addressNameSag";
			members[5].type = ORB.init().create_array_tc (
				41, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[6] = new StructMember();
			members[6].name = "directional";
			members[6].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[7] = new StructMember();
			members[7].name = "highAddrRange";
			members[7].type = ORB.init().create_array_tc (
				16, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[8] = new StructMember();
			members[8].name = "lowAddrRange";
			members[8].type = ORB.init().create_array_tc (
				16, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[9] = new StructMember();
			members[9].name = "oddEvenIndicator";
			members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[10] = new StructMember();
			members[10].name = "exchange";
			members[10].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[11] = new StructMember();
			members[11].name = "centralOffice";
			members[11].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[12] = new StructMember();
			members[12].name = "map";
			members[12].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[13] = new StructMember();
			members[13].name = "rateBand";
			members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[14] = new StructMember();
			members[14].name = "rateZone";
			members[14].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[15] = new StructMember();
			members[15].name = "zipCodeSag";
			members[15].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[16] = new StructMember();
			members[16].name = "npa";
			members[16].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[17] = new StructMember();
			members[17].name = "countyAbbrev";
			members[17].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[18] = new StructMember();
			members[18].name = "municipality";
			members[18].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[19] = new StructMember();
			members[19].name = "sagWireCenter";
			members[19].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[20] = new StructMember();
			members[20].name = "communitySag";
			members[20].type = ORB.init().create_array_tc (
				21, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[21] = new StructMember();
			members[21].name = "state";
			members[21].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[22] = new StructMember();
			members[22].name = "needBillAddrInd";
			members[22].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[23] = new StructMember();
			members[23].name = "editAgainstLufFile";
			members[23].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[24] = new StructMember();
			members[24].name = "needLocLevel1Ind";
			members[24].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[25] = new StructMember();
			members[25].name = "needLocLevel2Ind";
			members[25].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[26] = new StructMember();
			members[26].name = "needLocLevel3Ind";
			members[26].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[27] = new StructMember();
			members[27].name = "needCommNameInd";
			members[27].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[28] = new StructMember();
			members[28].name = "secondLineInd";
			members[28].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[29] = new StructMember();
			members[29].name = "metroOptSvcInd";
			members[29].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[30] = new StructMember();
			members[30].name = "omitCentralOfficeInd";
			members[30].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[31] = new StructMember();
			members[31].name = "remarksInd";
			members[31].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[32] = new StructMember();
			members[32].name = "lastAssignedHouseNumUsed";
			members[32].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[33] = new StructMember();
			members[33].name = "cityAbbreviation";
			members[33].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[34] = new StructMember();
			members[34].name = "populateCommNameInd";
			members[34].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[35] = new StructMember();
			members[35].name = "alternateAddressInd";
			members[35].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[36] = new StructMember();
			members[36].name = "lfacsDupAddressInd";
			members[36].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[37] = new StructMember();
			members[37].name = "equipmentType";
			members[37].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[38] = new StructMember();
			members[38].name = "analogOrDigitalType";
			members[38].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[39] = new StructMember();
			members[39].name = "tar";
			members[39].type = ORB.init().create_array_tc (
				5, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[40] = new StructMember();
			members[40].name = "tnSplitSwitch";
			members[40].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[41] = new StructMember();
			members[41].name = "busRateBand";
			members[41].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[42] = new StructMember();
			members[42].name = "remoteOrHostType";
			members[42].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[43] = new StructMember();
			members[43].name = "alternateNpa";
			members[43].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[44] = new StructMember();
			members[44].name = "addrRmks1";
			members[44].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[45] = new StructMember();
			members[45].name = "addrRmks2";
			members[45].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[46] = new StructMember();
			members[46].name = "addrRmks3";
			members[46].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[47] = new StructMember();
			members[47].name = "addrRmks4";
			members[47].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[48] = new StructMember();
			members[48].name = "descAddrRmks1";
			members[48].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[49] = new StructMember();
			members[49].name = "descAddrRmks2";
			members[49].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[50] = new StructMember();
			members[50].name = "descAddrRmks3";
			members[50].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[51] = new StructMember();
			members[51].name = "descAddrRmks4";
			members[51].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[52] = new StructMember();
			members[52].name = "matchInd";
			members[52].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[53] = new StructMember();
			members[53].name = "lata";
			members[53].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[54] = new StructMember();
			members[54].name = "filler1";
			members[54].type = ORB.init().create_array_tc (
				28, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[55] = new StructMember();
			members[55].name = "e911Sur";
			members[55].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[56] = new StructMember();
			members[56].name = "e911Exempt";
			members[56].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[57] = new StructMember();
			members[57].name = "e911Nrg";
			members[57].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[58] = new StructMember();
			members[58].name = "operSur4Ind";
			members[58].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[59] = new StructMember();
			members[59].name = "operSur16Ind";
			members[59].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[60] = new StructMember();
			members[60].name = "facsWireCenter";
			members[60].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[61] = new StructMember();
			members[61].name = "primaryLso";
			members[61].type = ORB.init().create_array_tc (
				7, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[62] = new StructMember();
			members[62].name = "NbrOfNpaNxx";
			members[62].type = ORB.init().create_array_tc (
				4, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[63] = new StructMember();
			members[63].name = "npaNxx";
			members[63].type = com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_stHelper.type();
			members[63].type = ORB.init().create_array_tc (144, members[63].type);
			myTc = ORB.init().create_struct_tc (id(), "ASONSagValidResp", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp value) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.write (o, value.comReplyHdr1);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.write (o, value.comReplyHdr2);
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.raiCode.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		o.write_char(value.sagAreaId);
		o.write_char(value.alphaNumInd);
		{
			byte[] _bytes = new byte[41];
			byte[] _bytes_src = value.addressNameSag.getBytes();
			int _j;
			for (_j = 0; _j < 41 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 41);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.directional.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.highAddrRange.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.lowAddrRange.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		o.write_char(value.oddEvenIndicator);
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.exchange.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.centralOffice.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.map.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		o.write_char(value.rateBand);
		o.write_char(value.rateZone);
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.zipCodeSag.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.npa.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.countyAbbrev.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.municipality.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.sagWireCenter.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.communitySag.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.state.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		o.write_char(value.needBillAddrInd);
		o.write_char(value.editAgainstLufFile);
		o.write_char(value.needLocLevel1Ind);
		o.write_char(value.needLocLevel2Ind);
		o.write_char(value.needLocLevel3Ind);
		o.write_char(value.needCommNameInd);
		o.write_char(value.secondLineInd);
		o.write_char(value.metroOptSvcInd);
		o.write_char(value.omitCentralOfficeInd);
		o.write_char(value.remarksInd);
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.lastAssignedHouseNumUsed.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.cityAbbreviation.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		o.write_char(value.populateCommNameInd);
		o.write_char(value.alternateAddressInd);
		o.write_char(value.lfacsDupAddressInd);
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.equipmentType.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		o.write_char(value.analogOrDigitalType);
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.tar.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		o.write_char(value.tnSplitSwitch);
		o.write_char(value.busRateBand);
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.remoteOrHostType.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.alternateNpa.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.addrRmks1.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.addrRmks2.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.addrRmks3.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.addrRmks4.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmks1.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmks2.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmks3.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.descAddrRmks4.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		o.write_char(value.matchInd);
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.lata.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[28];
			byte[] _bytes_src = value.filler1.getBytes();
			int _j;
			for (_j = 0; _j < 28 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 28);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.e911Sur.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.e911Exempt.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.e911Nrg.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		o.write_char(value.operSur4Ind);
		o.write_char(value.operSur16Ind);
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.facsWireCenter.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.primaryLso.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.NbrOfNpaNxx.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{ 
			for (int __i0 = 0; __i0 < 144; __i0++) { 
				com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_stHelper.write (o, value.npaNxx[__i0]);
			} 
		}
	}
}
