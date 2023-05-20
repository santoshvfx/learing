// $Id: ASONSagValRespMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValRespMsg implements MMarshalObject { 
	public ASONSagValResp value;

	public ASONSagValRespMsg () {
	}
	public ASONSagValRespMsg (ASONSagValResp initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValResp create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValResp();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.advisoryMsg = new String ();
value.raiCode = new String ();
value.sagAreaId = new String ();
value.alphaNumInd = new String ();
value.addressName = new String ();
value.directional = new String ();
value.highAddrRange = new String ();
value.lowAddrRange = new String ();
value.oddEvenIndicator = new String ();
value.centralOffice = new String ();
value.exchange = new String ();
value.map = new String ();
value.rateBandZone = new String ();
value.busRateBand = new String ();
value.zipCode = new String ();
value.npa = new String ();
value.countyAbbrev = new String ();
value.municipality = new String ();
value.wireCenter = new String ();
value.community = new String ();
value.state = new String ();
value.editIndicators = new String ();
value.lastAssignedHouseNumUsed = new String ();
value.cityAbbreviation = new String ();
value.populateCommNameInd = new String ();
value.alternateAddressInd = new String ();
value.lfacsDupAddressInd = new String ();
value.equipmentType = new String ();
value.analogOrDigitalType = new String ();
value.tar = new String ();
value.remoteOrHostType = new String ();
value.e911Sur = new String ();
value.e911Exempt = new String ();
value.e911Nrg = new String ();
value.operSur4Ind = new String ();
value.operSur16Ind = new String ();
value.matchInd = new String ();
value.npaPrfxArray = new com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_st[144];
for (int i0 = 0; i0 < 144; i0++) { 
value.npaPrfxArray[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_stMsg.create();
}
value.sendEndString = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValResp (ms, tag); 
	}
	static public ASONSagValResp decodeASONSagValResp (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValResp value = create();
		ms.startStruct (tag, false);
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.decodetaginformation_st (ms, "tagInformation");
		value.replyCode = ms.decodeShort ("replyCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.raiCode = ms.decodeOctetString (3, "raiCode");
		value.sagAreaId = ms.decodeOctetString (2, "sagAreaId");
		value.alphaNumInd = ms.decodeOctetString (2, "alphaNumInd");
		value.addressName = ms.decodeOctetString (41, "addressName");
		value.directional = ms.decodeOctetString (4, "directional");
		value.highAddrRange = ms.decodeOctetString (16, "highAddrRange");
		value.lowAddrRange = ms.decodeOctetString (16, "lowAddrRange");
		value.oddEvenIndicator = ms.decodeOctetString (2, "oddEvenIndicator");
		value.centralOffice = ms.decodeOctetString (4, "centralOffice");
		value.exchange = ms.decodeOctetString (5, "exchange");
		value.map = ms.decodeOctetString (5, "map");
		value.rateBandZone = ms.decodeOctetString (3, "rateBandZone");
		value.busRateBand = ms.decodeOctetString (2, "busRateBand");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.npa = ms.decodeOctetString (4, "npa");
		value.countyAbbrev = ms.decodeOctetString (5, "countyAbbrev");
		value.municipality = ms.decodeOctetString (4, "municipality");
		value.wireCenter = ms.decodeOctetString (7, "wireCenter");
		value.community = ms.decodeOctetString (21, "community");
		value.state = ms.decodeOctetString (3, "state");
		value.editIndicators = ms.decodeOctetString (11, "editIndicators");
		value.lastAssignedHouseNumUsed = ms.decodeOctetString (9, "lastAssignedHouseNumUsed");
		value.cityAbbreviation = ms.decodeOctetString (5, "cityAbbreviation");
		value.populateCommNameInd = ms.decodeOctetString (2, "populateCommNameInd");
		value.alternateAddressInd = ms.decodeOctetString (2, "alternateAddressInd");
		value.lfacsDupAddressInd = ms.decodeOctetString (2, "lfacsDupAddressInd");
		value.equipmentType = ms.decodeOctetString (4, "equipmentType");
		value.analogOrDigitalType = ms.decodeOctetString (2, "analogOrDigitalType");
		value.tar = ms.decodeOctetString (5, "tar");
		value.remoteOrHostType = ms.decodeOctetString (9, "remoteOrHostType");
		value.e911Sur = ms.decodeOctetString (3, "e911Sur");
		value.e911Exempt = ms.decodeOctetString (3, "e911Exempt");
		value.e911Nrg = ms.decodeOctetString (3, "e911Nrg");
		value.operSur4Ind = ms.decodeOctetString (2, "operSur4Ind");
		value.operSur16Ind = ms.decodeOctetString (2, "operSur16Ind");
		value.matchInd = ms.decodeOctetString (2, "matchInd");
		ms.startArray ("npaPrfxArray", false);
		{ 
			value.npaPrfxArray = new com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_st[144];
			for (int __i0 = 0; __i0 < 144; __i0++) { 
				value.npaPrfxArray[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_stMsg.decodenpaprfx_st (ms, "npaPrfxArray");
			} 
		}
		ms.endArray ("npaPrfxArray", false);
		value.sendEndString = ms.decodeOctetString (4, "sendEndString");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValResp (ms, value, tag); 
	}
	static public void encodeASONSagValResp (MMarshalStrategy ms, ASONSagValResp value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.encodetaginformation_st (ms, value.tagInformation, "tagInformation");
		ms.encode (value.replyCode, "replyCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.raiCode, 3, "raiCode");
ms.encode (value.sagAreaId, 2, "sagAreaId");
ms.encode (value.alphaNumInd, 2, "alphaNumInd");
ms.encode (value.addressName, 41, "addressName");
ms.encode (value.directional, 4, "directional");
ms.encode (value.highAddrRange, 16, "highAddrRange");
ms.encode (value.lowAddrRange, 16, "lowAddrRange");
ms.encode (value.oddEvenIndicator, 2, "oddEvenIndicator");
ms.encode (value.centralOffice, 4, "centralOffice");
ms.encode (value.exchange, 5, "exchange");
ms.encode (value.map, 5, "map");
ms.encode (value.rateBandZone, 3, "rateBandZone");
ms.encode (value.busRateBand, 2, "busRateBand");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.npa, 4, "npa");
ms.encode (value.countyAbbrev, 5, "countyAbbrev");
ms.encode (value.municipality, 4, "municipality");
ms.encode (value.wireCenter, 7, "wireCenter");
ms.encode (value.community, 21, "community");
ms.encode (value.state, 3, "state");
ms.encode (value.editIndicators, 11, "editIndicators");
ms.encode (value.lastAssignedHouseNumUsed, 9, "lastAssignedHouseNumUsed");
ms.encode (value.cityAbbreviation, 5, "cityAbbreviation");
ms.encode (value.populateCommNameInd, 2, "populateCommNameInd");
ms.encode (value.alternateAddressInd, 2, "alternateAddressInd");
ms.encode (value.lfacsDupAddressInd, 2, "lfacsDupAddressInd");
ms.encode (value.equipmentType, 4, "equipmentType");
ms.encode (value.analogOrDigitalType, 2, "analogOrDigitalType");
ms.encode (value.tar, 5, "tar");
ms.encode (value.remoteOrHostType, 9, "remoteOrHostType");
ms.encode (value.e911Sur, 3, "e911Sur");
ms.encode (value.e911Exempt, 3, "e911Exempt");
ms.encode (value.e911Nrg, 3, "e911Nrg");
ms.encode (value.operSur4Ind, 2, "operSur4Ind");
ms.encode (value.operSur16Ind, 2, "operSur16Ind");
ms.encode (value.matchInd, 2, "matchInd");
ms.startArray ("npaPrfxArray", true);
{ 
for (int __i0 = 0; __i0 < 144; __i0++) { 
com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_stMsg.encodenpaprfx_st (ms, value.npaPrfxArray[__i0], "npaPrfxArray");
} 
}
ms.endArray ("npaPrfxArray", true);
ms.encode (value.sendEndString, 4, "sendEndString");
ms.endStruct (tag, true); 
}
public static ASONSagValResp fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONSagValResp (ms, "ASONSagValResp"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValRespHelper.type(); 
}
public static byte [] toOctet (ASONSagValResp val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONSagValResp (ms, val, "ASONSagValResp");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
