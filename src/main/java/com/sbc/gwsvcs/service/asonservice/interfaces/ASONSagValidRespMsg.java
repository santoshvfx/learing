// $Id: ASONSagValidRespMsg.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValidRespMsg implements MMarshalObject { 
	public ASONSagValidResp value;

	public ASONSagValidRespMsg () {
	}
	public ASONSagValidRespMsg (ASONSagValidResp initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp();
value.comReplyHdr1 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st();
value.comReplyHdr2 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st();
value.raiCode = new String ();
value.addressNameSag = new String ();
value.directional = new String ();
value.highAddrRange = new String ();
value.lowAddrRange = new String ();
value.exchange = new String ();
value.centralOffice = new String ();
value.map = new String ();
value.zipCodeSag = new String ();
value.npa = new String ();
value.countyAbbrev = new String ();
value.municipality = new String ();
value.sagWireCenter = new String ();
value.communitySag = new String ();
value.state = new String ();
value.lastAssignedHouseNumUsed = new String ();
value.cityAbbreviation = new String ();
value.equipmentType = new String ();
value.tar = new String ();
value.remoteOrHostType = new String ();
value.alternateNpa = new String ();
value.addrRmks1 = new String ();
value.addrRmks2 = new String ();
value.addrRmks3 = new String ();
value.addrRmks4 = new String ();
value.descAddrRmks1 = new String ();
value.descAddrRmks2 = new String ();
value.descAddrRmks3 = new String ();
value.descAddrRmks4 = new String ();
value.lata = new String ();
value.filler1 = new String ();
value.e911Sur = new String ();
value.e911Exempt = new String ();
value.e911Nrg = new String ();
value.facsWireCenter = new String ();
value.primaryLso = new String ();
value.NbrOfNpaNxx = new String ();
value.npaNxx = new com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_st[144];
for (int i0 = 0; i0 < 144; i0++) { 
value.npaNxx[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_stMsg.create();
}
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValidResp (ms, tag); 
	}
	static public ASONSagValidResp decodeASONSagValidResp (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValidResp value = create();
		ms.startStruct (tag, false);
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.decodecomReplyHdr1_st (ms, "comReplyHdr1");
		value.comReplyHdr2 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.decodecomReplyHdr2_st (ms, "comReplyHdr2");
		value.raiCode = ms.decodeOctetString (3, "raiCode");
		value.sagAreaId = ms.decodeChar ("sagAreaId");
		value.alphaNumInd = ms.decodeChar ("alphaNumInd");
		value.addressNameSag = ms.decodeOctetString (41, "addressNameSag");
		value.directional = ms.decodeOctetString (4, "directional");
		value.highAddrRange = ms.decodeOctetString (16, "highAddrRange");
		value.lowAddrRange = ms.decodeOctetString (16, "lowAddrRange");
		value.oddEvenIndicator = ms.decodeChar ("oddEvenIndicator");
		value.exchange = ms.decodeOctetString (5, "exchange");
		value.centralOffice = ms.decodeOctetString (4, "centralOffice");
		value.map = ms.decodeOctetString (5, "map");
		value.rateBand = ms.decodeChar ("rateBand");
		value.rateZone = ms.decodeChar ("rateZone");
		value.zipCodeSag = ms.decodeOctetString (6, "zipCodeSag");
		value.npa = ms.decodeOctetString (4, "npa");
		value.countyAbbrev = ms.decodeOctetString (5, "countyAbbrev");
		value.municipality = ms.decodeOctetString (4, "municipality");
		value.sagWireCenter = ms.decodeOctetString (7, "sagWireCenter");
		value.communitySag = ms.decodeOctetString (21, "communitySag");
		value.state = ms.decodeOctetString (3, "state");
		value.needBillAddrInd = ms.decodeChar ("needBillAddrInd");
		value.editAgainstLufFile = ms.decodeChar ("editAgainstLufFile");
		value.needLocLevel1Ind = ms.decodeChar ("needLocLevel1Ind");
		value.needLocLevel2Ind = ms.decodeChar ("needLocLevel2Ind");
		value.needLocLevel3Ind = ms.decodeChar ("needLocLevel3Ind");
		value.needCommNameInd = ms.decodeChar ("needCommNameInd");
		value.secondLineInd = ms.decodeChar ("secondLineInd");
		value.metroOptSvcInd = ms.decodeChar ("metroOptSvcInd");
		value.omitCentralOfficeInd = ms.decodeChar ("omitCentralOfficeInd");
		value.remarksInd = ms.decodeChar ("remarksInd");
		value.lastAssignedHouseNumUsed = ms.decodeOctetString (9, "lastAssignedHouseNumUsed");
		value.cityAbbreviation = ms.decodeOctetString (5, "cityAbbreviation");
		value.populateCommNameInd = ms.decodeChar ("populateCommNameInd");
		value.alternateAddressInd = ms.decodeChar ("alternateAddressInd");
		value.lfacsDupAddressInd = ms.decodeChar ("lfacsDupAddressInd");
		value.equipmentType = ms.decodeOctetString (4, "equipmentType");
		value.analogOrDigitalType = ms.decodeChar ("analogOrDigitalType");
		value.tar = ms.decodeOctetString (5, "tar");
		value.tnSplitSwitch = ms.decodeChar ("tnSplitSwitch");
		value.busRateBand = ms.decodeChar ("busRateBand");
		value.remoteOrHostType = ms.decodeOctetString (9, "remoteOrHostType");
		value.alternateNpa = ms.decodeOctetString (4, "alternateNpa");
		value.addrRmks1 = ms.decodeOctetString (73, "addrRmks1");
		value.addrRmks2 = ms.decodeOctetString (73, "addrRmks2");
		value.addrRmks3 = ms.decodeOctetString (73, "addrRmks3");
		value.addrRmks4 = ms.decodeOctetString (73, "addrRmks4");
		value.descAddrRmks1 = ms.decodeOctetString (73, "descAddrRmks1");
		value.descAddrRmks2 = ms.decodeOctetString (73, "descAddrRmks2");
		value.descAddrRmks3 = ms.decodeOctetString (73, "descAddrRmks3");
		value.descAddrRmks4 = ms.decodeOctetString (73, "descAddrRmks4");
		value.matchInd = ms.decodeChar ("matchInd");
		value.lata = ms.decodeOctetString (4, "lata");
		value.filler1 = ms.decodeOctetString (28, "filler1");
		value.e911Sur = ms.decodeOctetString (3, "e911Sur");
		value.e911Exempt = ms.decodeOctetString (3, "e911Exempt");
		value.e911Nrg = ms.decodeOctetString (3, "e911Nrg");
		value.operSur4Ind = ms.decodeChar ("operSur4Ind");
		value.operSur16Ind = ms.decodeChar ("operSur16Ind");
		value.facsWireCenter = ms.decodeOctetString (7, "facsWireCenter");
		value.primaryLso = ms.decodeOctetString (7, "primaryLso");
		value.NbrOfNpaNxx = ms.decodeOctetString (4, "NbrOfNpaNxx");
		ms.startArray ("npaNxx", false);
		{ 
			value.npaNxx = new com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_st[144];
			for (int __i0 = 0; __i0 < 144; __i0++) { 
				value.npaNxx[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_stMsg.decodenpaNxx_st (ms, "npaNxx");
			} 
		}
		ms.endArray ("npaNxx", false);
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValidResp (ms, value, tag); 
	}
	static public void encodeASONSagValidResp (MMarshalStrategy ms, ASONSagValidResp value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.encodecomReplyHdr1_st (ms, value.comReplyHdr1, "comReplyHdr1");
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.encodecomReplyHdr2_st (ms, value.comReplyHdr2, "comReplyHdr2");
		ms.encode (value.raiCode, 3, "raiCode");
	ms.encode (value.sagAreaId, "sagAreaId");
	ms.encode (value.alphaNumInd, "alphaNumInd");
	ms.encode (value.addressNameSag, 41, "addressNameSag");
ms.encode (value.directional, 4, "directional");
ms.encode (value.highAddrRange, 16, "highAddrRange");
ms.encode (value.lowAddrRange, 16, "lowAddrRange");
ms.encode (value.oddEvenIndicator, "oddEvenIndicator");
ms.encode (value.exchange, 5, "exchange");
ms.encode (value.centralOffice, 4, "centralOffice");
ms.encode (value.map, 5, "map");
ms.encode (value.rateBand, "rateBand");
ms.encode (value.rateZone, "rateZone");
ms.encode (value.zipCodeSag, 6, "zipCodeSag");
ms.encode (value.npa, 4, "npa");
ms.encode (value.countyAbbrev, 5, "countyAbbrev");
ms.encode (value.municipality, 4, "municipality");
ms.encode (value.sagWireCenter, 7, "sagWireCenter");
ms.encode (value.communitySag, 21, "communitySag");
ms.encode (value.state, 3, "state");
ms.encode (value.needBillAddrInd, "needBillAddrInd");
ms.encode (value.editAgainstLufFile, "editAgainstLufFile");
ms.encode (value.needLocLevel1Ind, "needLocLevel1Ind");
ms.encode (value.needLocLevel2Ind, "needLocLevel2Ind");
ms.encode (value.needLocLevel3Ind, "needLocLevel3Ind");
ms.encode (value.needCommNameInd, "needCommNameInd");
ms.encode (value.secondLineInd, "secondLineInd");
ms.encode (value.metroOptSvcInd, "metroOptSvcInd");
ms.encode (value.omitCentralOfficeInd, "omitCentralOfficeInd");
ms.encode (value.remarksInd, "remarksInd");
ms.encode (value.lastAssignedHouseNumUsed, 9, "lastAssignedHouseNumUsed");
ms.encode (value.cityAbbreviation, 5, "cityAbbreviation");
ms.encode (value.populateCommNameInd, "populateCommNameInd");
ms.encode (value.alternateAddressInd, "alternateAddressInd");
ms.encode (value.lfacsDupAddressInd, "lfacsDupAddressInd");
ms.encode (value.equipmentType, 4, "equipmentType");
ms.encode (value.analogOrDigitalType, "analogOrDigitalType");
ms.encode (value.tar, 5, "tar");
ms.encode (value.tnSplitSwitch, "tnSplitSwitch");
ms.encode (value.busRateBand, "busRateBand");
ms.encode (value.remoteOrHostType, 9, "remoteOrHostType");
ms.encode (value.alternateNpa, 4, "alternateNpa");
ms.encode (value.addrRmks1, 73, "addrRmks1");
ms.encode (value.addrRmks2, 73, "addrRmks2");
ms.encode (value.addrRmks3, 73, "addrRmks3");
ms.encode (value.addrRmks4, 73, "addrRmks4");
ms.encode (value.descAddrRmks1, 73, "descAddrRmks1");
ms.encode (value.descAddrRmks2, 73, "descAddrRmks2");
ms.encode (value.descAddrRmks3, 73, "descAddrRmks3");
ms.encode (value.descAddrRmks4, 73, "descAddrRmks4");
ms.encode (value.matchInd, "matchInd");
ms.encode (value.lata, 4, "lata");
ms.encode (value.filler1, 28, "filler1");
ms.encode (value.e911Sur, 3, "e911Sur");
ms.encode (value.e911Exempt, 3, "e911Exempt");
ms.encode (value.e911Nrg, 3, "e911Nrg");
ms.encode (value.operSur4Ind, "operSur4Ind");
ms.encode (value.operSur16Ind, "operSur16Ind");
ms.encode (value.facsWireCenter, 7, "facsWireCenter");
ms.encode (value.primaryLso, 7, "primaryLso");
ms.encode (value.NbrOfNpaNxx, 4, "NbrOfNpaNxx");
ms.startArray ("npaNxx", true);
{ 
for (int __i0 = 0; __i0 < 144; __i0++) { 
com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_stMsg.encodenpaNxx_st (ms, value.npaNxx[__i0], "npaNxx");
} 
}
ms.endArray ("npaNxx", true);
ms.endStruct (tag, true); 
}
public static ASONSagValidResp fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONSagValidResp (ms, "ASONSagValidResp"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidRespHelper.type(); 
}
public static byte [] toOctet (ASONSagValidResp val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONSagValidResp (ms, val, "ASONSagValidResp");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
