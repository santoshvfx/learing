// $Id: ASONLvngUnitValReqMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONLvngUnitValReqMsg implements MMarshalObject { 
	public ASONLvngUnitValReq value;

	public ASONLvngUnitValReqMsg () {
	}
	public ASONLvngUnitValReqMsg (ASONLvngUnitValReq initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReq create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReq();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.commandLine = new com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st();
value.dateKey = new String ();
value.functionKeyDepressed = new String ();
value.idGroup = new String ();
value.idTerminal = new String ();
value.idTypist = new String ();
value.regionalAreaId = new String ();
value.timeKey = new String ();
value.raiCode = new String ();
value.sagAreaId = new String ();
value.wireCenter = new String ();
value.communityName = new String ();
value.streetDirection = new String ();
value.streetName = new String ();
value.assignedHouseNumberInd = new String ();
value.stNbrFld1 = new String ();
value.stNbrFld2 = new String ();
value.loc1 = new String ();
value.loc2 = new String ();
value.loc3 = new String ();
value.loc4 = new String ();
value.loc5 = new String ();
value.locTag1 = new String ();
value.locTag2 = new String ();
value.locTag3 = new String ();
value.locTag4 = new String ();
value.locTag5 = new String ();
value.zipCode = new String ();
value.sentEndString = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONLvngUnitValReq (ms, tag); 
	}
	static public ASONLvngUnitValReq decodeASONLvngUnitValReq (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONLvngUnitValReq value = create();
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
		value.raiCode = ms.decodeOctetString (3, "raiCode");
		value.sagAreaId = ms.decodeOctetString (2, "sagAreaId");
		value.wireCenter = ms.decodeOctetString (7, "wireCenter");
		value.communityName = ms.decodeOctetString (21, "communityName");
		value.streetDirection = ms.decodeOctetString (4, "streetDirection");
		value.streetName = ms.decodeOctetString (41, "streetName");
		value.assignedHouseNumberInd = ms.decodeOctetString (2, "assignedHouseNumberInd");
		value.stNbrFld1 = ms.decodeOctetString (16, "stNbrFld1");
		value.stNbrFld2 = ms.decodeOctetString (5, "stNbrFld2");
		value.loc1 = ms.decodeOctetString (11, "loc1");
		value.loc2 = ms.decodeOctetString (11, "loc2");
		value.loc3 = ms.decodeOctetString (11, "loc3");
		value.loc4 = ms.decodeOctetString (11, "loc4");
		value.loc5 = ms.decodeOctetString (11, "loc5");
		value.locTag1 = ms.decodeOctetString (6, "locTag1");
		value.locTag2 = ms.decodeOctetString (6, "locTag2");
		value.locTag3 = ms.decodeOctetString (6, "locTag3");
		value.locTag4 = ms.decodeOctetString (6, "locTag4");
		value.locTag5 = ms.decodeOctetString (6, "locTag5");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.sentEndString = ms.decodeOctetString (4, "sentEndString");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONLvngUnitValReq (ms, value, tag); 
	}
	static public void encodeASONLvngUnitValReq (MMarshalStrategy ms, ASONLvngUnitValReq value, String tag) throws MMarshalException { 
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
ms.encode (value.raiCode, 3, "raiCode");
ms.encode (value.sagAreaId, 2, "sagAreaId");
ms.encode (value.wireCenter, 7, "wireCenter");
ms.encode (value.communityName, 21, "communityName");
ms.encode (value.streetDirection, 4, "streetDirection");
ms.encode (value.streetName, 41, "streetName");
ms.encode (value.assignedHouseNumberInd, 2, "assignedHouseNumberInd");
ms.encode (value.stNbrFld1, 16, "stNbrFld1");
ms.encode (value.stNbrFld2, 5, "stNbrFld2");
ms.encode (value.loc1, 11, "loc1");
ms.encode (value.loc2, 11, "loc2");
ms.encode (value.loc3, 11, "loc3");
ms.encode (value.loc4, 11, "loc4");
ms.encode (value.loc5, 11, "loc5");
ms.encode (value.locTag1, 6, "locTag1");
ms.encode (value.locTag2, 6, "locTag2");
ms.encode (value.locTag3, 6, "locTag3");
ms.encode (value.locTag4, 6, "locTag4");
ms.encode (value.locTag5, 6, "locTag5");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.sentEndString, 4, "sentEndString");
ms.endStruct (tag, true); 
}
public static ASONLvngUnitValReq fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONLvngUnitValReq (ms, "ASONLvngUnitValReq"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReqHelper.type(); 
}
public static byte [] toOctet (ASONLvngUnitValReq val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONLvngUnitValReq (ms, val, "ASONLvngUnitValReq");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
