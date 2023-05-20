// $Id: ASONLvngUnitInqReqMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONLvngUnitInqReqMsg implements MMarshalObject { 
	public ASONLvngUnitInqReq value;

	public ASONLvngUnitInqReqMsg () {
	}
	public ASONLvngUnitInqReqMsg (ASONLvngUnitInqReq initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReq create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReq();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.commandLine = new com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st();
value.dateKey = new String ();
value.functionKeyDepressed = new String ();
value.idGroup = new String ();
value.idTerminal = new String ();
value.idTypist = new String ();
value.regionalAreaId = new String ();
value.timeKey = new String ();
value.savedLuKey = new String ();
value.raiCode = new String ();
value.sagAreaId = new String ();
value.wireCenter = new String ();
value.communityName = new String ();
value.streetDirection = new String ();
value.streetName1 = new String ();
value.streetName2_40 = new String ();
value.assignedHouseNumberInd = new String ();
value.stNbrFld1 = new String ();
value.stNbrFld2 = new String ();
value.locLocValue1 = new String ();
value.locLocValue2 = new String ();
value.locLocValue3 = new String ();
value.locLocValue4 = new String ();
value.locLocValue5 = new String ();
value.custTN = new String ();
value.custName = new String ();
value.custAddress = new String ();
value.streetRangeLow = new String ();
value.streetRangeHigh = new String ();
value.oddEvenInd = new String ();
value.exchange = new String ();
value.centralOffice = new String ();
value.map = new String ();
value.rateBandZone = new String ();
value.zipCode = new String ();
value.npa = new String ();
value.skRaiCode = new String ();
value.skSaiCode = new String ();
value.skAlphaNumInd = new String ();
value.skAddressName = new String ();
value.skDirectional = new String ();
value.skHighRange = new String ();
value.skLowRange = new String ();
value.skOddEvenIndicator = new String ();
value.skExchange = new String ();
value.skSagCO = new String ();
value.skMap = new String ();
value.skRateBandZone = new String ();
value.skZipCode = new String ();
value.skNpa = new String ();
value.skCountyAbbrev = new String ();
value.skMunicipality = new String ();
value.sentEndString = new String ();
value.maxUnitReturnLimit = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONLvngUnitInqReq (ms, tag); 
	}
	static public ASONLvngUnitInqReq decodeASONLvngUnitInqReq (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONLvngUnitInqReq value = create();
		ms.startStruct (tag, false);
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.decodetaginformation_st (ms, "tagInformation");
		value.requestCode = ms.decodeShort ("requestCode");
		value.commandLine = com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stMsg.decodecommandline_st (ms, "commandLine");
		value.dateKey = ms.decodeOctetString (7, "dateKey");
		value.functionKeyDepressed = ms.decodeOctetString (3, "functionKeyDepressed");
		value.idGroup = ms.decodeOctetString (6, "idGroup");
		value.idTerminal = ms.decodeOctetString (16, "idTerminal");
		value.idTypist = ms.decodeOctetString (4, "idTypist");
		value.regionalAreaId = ms.decodeOctetString (3, "regionalAreaId");
		value.timeKey = ms.decodeOctetString (9, "timeKey");
		value.savedLuKey = ms.decodeOctetString (143, "savedLuKey");
		value.raiCode = ms.decodeOctetString (3, "raiCode");
		value.sagAreaId = ms.decodeOctetString (2, "sagAreaId");
		value.wireCenter = ms.decodeOctetString (7, "wireCenter");
		value.communityName = ms.decodeOctetString (21, "communityName");
		value.streetDirection = ms.decodeOctetString (4, "streetDirection");
		value.streetName1 = ms.decodeOctetString (2, "streetName1");
		value.streetName2_40 = ms.decodeOctetString (40, "streetName2_40");
		value.assignedHouseNumberInd = ms.decodeOctetString (2, "assignedHouseNumberInd");
		value.stNbrFld1 = ms.decodeOctetString (16, "stNbrFld1");
		value.stNbrFld2 = ms.decodeOctetString (5, "stNbrFld2");
		value.locLocValue1 = ms.decodeOctetString (11, "locLocValue1");
		value.locLocValue2 = ms.decodeOctetString (11, "locLocValue2");
		value.locLocValue3 = ms.decodeOctetString (11, "locLocValue3");
		value.locLocValue4 = ms.decodeOctetString (11, "locLocValue4");
		value.locLocValue5 = ms.decodeOctetString (11, "locLocValue5");
		value.custTN = ms.decodeOctetString (11, "custTN");
		value.custName = ms.decodeOctetString (13, "custName");
		value.custAddress = ms.decodeOctetString (21, "custAddress");
		value.streetRangeLow = ms.decodeOctetString (16, "streetRangeLow");
		value.streetRangeHigh = ms.decodeOctetString (16, "streetRangeHigh");
		value.oddEvenInd = ms.decodeOctetString (2, "oddEvenInd");
		value.exchange = ms.decodeOctetString (5, "exchange");
		value.centralOffice = ms.decodeOctetString (4, "centralOffice");
		value.map = ms.decodeOctetString (5, "map");
		value.rateBandZone = ms.decodeOctetString (3, "rateBandZone");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.npa = ms.decodeOctetString (4, "npa");
		value.skRaiCode = ms.decodeOctetString (3, "skRaiCode");
		value.skSaiCode = ms.decodeOctetString (2, "skSaiCode");
		value.skAlphaNumInd = ms.decodeOctetString (2, "skAlphaNumInd");
		value.skAddressName = ms.decodeOctetString (41, "skAddressName");
		value.skDirectional = ms.decodeOctetString (4, "skDirectional");
		value.skHighRange = ms.decodeOctetString (16, "skHighRange");
		value.skLowRange = ms.decodeOctetString (16, "skLowRange");
		value.skOddEvenIndicator = ms.decodeOctetString (2, "skOddEvenIndicator");
		value.skExchange = ms.decodeOctetString (5, "skExchange");
		value.skSagCO = ms.decodeOctetString (4, "skSagCO");
		value.skMap = ms.decodeOctetString (5, "skMap");
		value.skRateBandZone = ms.decodeOctetString (3, "skRateBandZone");
		value.skZipCode = ms.decodeOctetString (6, "skZipCode");
		value.skNpa = ms.decodeOctetString (4, "skNpa");
		value.skCountyAbbrev = ms.decodeOctetString (5, "skCountyAbbrev");
		value.skMunicipality = ms.decodeOctetString (4, "skMunicipality");
		value.sentEndString = ms.decodeOctetString (4, "sentEndString");
		value.maxUnitReturnLimit = ms.decodeOctetString (4, "maxUnitReturnLimit");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONLvngUnitInqReq (ms, value, tag); 
	}
	static public void encodeASONLvngUnitInqReq (MMarshalStrategy ms, ASONLvngUnitInqReq value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.encodetaginformation_st (ms, value.tagInformation, "tagInformation");
		ms.encode (value.requestCode, "requestCode");
		com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stMsg.encodecommandline_st (ms, value.commandLine, "commandLine");
		ms.encode (value.dateKey, 7, "dateKey");
	ms.encode (value.functionKeyDepressed, 3, "functionKeyDepressed");
ms.encode (value.idGroup, 6, "idGroup");
ms.encode (value.idTerminal, 16, "idTerminal");
ms.encode (value.idTypist, 4, "idTypist");
ms.encode (value.regionalAreaId, 3, "regionalAreaId");
ms.encode (value.timeKey, 9, "timeKey");
ms.encode (value.savedLuKey, 143, "savedLuKey");
ms.encode (value.raiCode, 3, "raiCode");
ms.encode (value.sagAreaId, 2, "sagAreaId");
ms.encode (value.wireCenter, 7, "wireCenter");
ms.encode (value.communityName, 21, "communityName");
ms.encode (value.streetDirection, 4, "streetDirection");
ms.encode (value.streetName1, 2, "streetName1");
ms.encode (value.streetName2_40, 40, "streetName2_40");
ms.encode (value.assignedHouseNumberInd, 2, "assignedHouseNumberInd");
ms.encode (value.stNbrFld1, 16, "stNbrFld1");
ms.encode (value.stNbrFld2, 5, "stNbrFld2");
ms.encode (value.locLocValue1, 11, "locLocValue1");
ms.encode (value.locLocValue2, 11, "locLocValue2");
ms.encode (value.locLocValue3, 11, "locLocValue3");
ms.encode (value.locLocValue4, 11, "locLocValue4");
ms.encode (value.locLocValue5, 11, "locLocValue5");
ms.encode (value.custTN, 11, "custTN");
ms.encode (value.custName, 13, "custName");
ms.encode (value.custAddress, 21, "custAddress");
ms.encode (value.streetRangeLow, 16, "streetRangeLow");
ms.encode (value.streetRangeHigh, 16, "streetRangeHigh");
ms.encode (value.oddEvenInd, 2, "oddEvenInd");
ms.encode (value.exchange, 5, "exchange");
ms.encode (value.centralOffice, 4, "centralOffice");
ms.encode (value.map, 5, "map");
ms.encode (value.rateBandZone, 3, "rateBandZone");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.npa, 4, "npa");
ms.encode (value.skRaiCode, 3, "skRaiCode");
ms.encode (value.skSaiCode, 2, "skSaiCode");
ms.encode (value.skAlphaNumInd, 2, "skAlphaNumInd");
ms.encode (value.skAddressName, 41, "skAddressName");
ms.encode (value.skDirectional, 4, "skDirectional");
ms.encode (value.skHighRange, 16, "skHighRange");
ms.encode (value.skLowRange, 16, "skLowRange");
ms.encode (value.skOddEvenIndicator, 2, "skOddEvenIndicator");
ms.encode (value.skExchange, 5, "skExchange");
ms.encode (value.skSagCO, 4, "skSagCO");
ms.encode (value.skMap, 5, "skMap");
ms.encode (value.skRateBandZone, 3, "skRateBandZone");
ms.encode (value.skZipCode, 6, "skZipCode");
ms.encode (value.skNpa, 4, "skNpa");
ms.encode (value.skCountyAbbrev, 5, "skCountyAbbrev");
ms.encode (value.skMunicipality, 4, "skMunicipality");
ms.encode (value.sentEndString, 4, "sentEndString");
ms.encode (value.maxUnitReturnLimit, 4, "maxUnitReturnLimit");
ms.endStruct (tag, true); 
}
public static ASONLvngUnitInqReq fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONLvngUnitInqReq (ms, "ASONLvngUnitInqReq"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReqHelper.type(); 
}
public static byte [] toOctet (ASONLvngUnitInqReq val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONLvngUnitInqReq (ms, val, "ASONLvngUnitInqReq");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
