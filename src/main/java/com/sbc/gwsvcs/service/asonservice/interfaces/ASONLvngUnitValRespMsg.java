// $Id: ASONLvngUnitValRespMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONLvngUnitValRespMsg implements MMarshalObject { 
	public ASONLvngUnitValResp value;

	public ASONLvngUnitValRespMsg () {
	}
	public ASONLvngUnitValRespMsg (ASONLvngUnitValResp initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.advisoryMsg = new String ();
value.codeDisplay = new String ();
value.raiCode = new String ();
value.sagAreaId = new String ();
value.wireCenter = new String ();
value.communityName = new String ();
value.streetDirection = new String ();
value.streetName = new String ();
value.assignedHseNumberInd = new String ();
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
value.clusterCode = new String ();
value.serviceAvailInd = new String ();
value.luFiller = new String ();
value.customerTN = new String ();
value.customerName = new String ();
value.customerAddress = new String ();
value.sentEndString = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONLvngUnitValResp (ms, tag); 
	}
	static public ASONLvngUnitValResp decodeASONLvngUnitValResp (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONLvngUnitValResp value = create();
		ms.startStruct (tag, false);
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.decodetaginformation_st (ms, "tagInformation");
		value.replyCode = ms.decodeShort ("replyCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.codeDisplay = ms.decodeOctetString (2, "codeDisplay");
		value.raiCode = ms.decodeOctetString (3, "raiCode");
		value.sagAreaId = ms.decodeOctetString (2, "sagAreaId");
		value.wireCenter = ms.decodeOctetString (7, "wireCenter");
		value.communityName = ms.decodeOctetString (21, "communityName");
		value.streetDirection = ms.decodeOctetString (4, "streetDirection");
		value.streetName = ms.decodeOctetString (41, "streetName");
		value.assignedHseNumberInd = ms.decodeOctetString (2, "assignedHseNumberInd");
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
		value.clusterCode = ms.decodeOctetString (3, "clusterCode");
		value.serviceAvailInd = ms.decodeOctetString (3, "serviceAvailInd");
		value.luFiller = ms.decodeOctetString (23, "luFiller");
		value.customerTN = ms.decodeOctetString (11, "customerTN");
		value.customerName = ms.decodeOctetString (13, "customerName");
		value.customerAddress = ms.decodeOctetString (21, "customerAddress");
		value.sentEndString = ms.decodeOctetString (4, "sentEndString");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONLvngUnitValResp (ms, value, tag); 
	}
	static public void encodeASONLvngUnitValResp (MMarshalStrategy ms, ASONLvngUnitValResp value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.encodetaginformation_st (ms, value.tagInformation, "tagInformation");
		ms.encode (value.replyCode, "replyCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.codeDisplay, 2, "codeDisplay");
ms.encode (value.raiCode, 3, "raiCode");
ms.encode (value.sagAreaId, 2, "sagAreaId");
ms.encode (value.wireCenter, 7, "wireCenter");
ms.encode (value.communityName, 21, "communityName");
ms.encode (value.streetDirection, 4, "streetDirection");
ms.encode (value.streetName, 41, "streetName");
ms.encode (value.assignedHseNumberInd, 2, "assignedHseNumberInd");
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
ms.encode (value.clusterCode, 3, "clusterCode");
ms.encode (value.serviceAvailInd, 3, "serviceAvailInd");
ms.encode (value.luFiller, 23, "luFiller");
ms.encode (value.customerTN, 11, "customerTN");
ms.encode (value.customerName, 13, "customerName");
ms.encode (value.customerAddress, 21, "customerAddress");
ms.encode (value.sentEndString, 4, "sentEndString");
ms.endStruct (tag, true); 
}
public static ASONLvngUnitValResp fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONLvngUnitValResp (ms, "ASONLvngUnitValResp"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValRespHelper.type(); 
}
public static byte [] toOctet (ASONLvngUnitValResp val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONLvngUnitValResp (ms, val, "ASONLvngUnitValResp");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
