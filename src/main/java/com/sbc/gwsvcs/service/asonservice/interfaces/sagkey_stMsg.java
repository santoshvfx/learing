// $Id: sagkey_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class sagkey_stMsg implements MMarshalObject { 
	public sagkey_st value;

	public sagkey_stMsg () {
	}
	public sagkey_stMsg (sagkey_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st();
value.raiCode = new String ();
value.sagAreaId = new String ();
value.alphaNumInd = new String ();
value.addressName = new String ();
value.directional = new String ();
value.highAddrRange = new String ();
value.lowAddrRange = new String ();
value.oddEvenIndicator = new String ();
value.exchange = new String ();
value.centralOffice = new String ();
value.map = new String ();
value.rateBandZone = new String ();
value.zipCode = new String ();
value.npa = new String ();
value.countyAbbrev = new String ();
value.municipality = new String ();
value.filler = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodesagkey_st (ms, tag); 
	}
	static public sagkey_st decodesagkey_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		sagkey_st value = create();
		ms.startStruct (tag, false);
		value.raiCode = ms.decodeOctetString (3, "raiCode");
		value.sagAreaId = ms.decodeOctetString (2, "sagAreaId");
		value.alphaNumInd = ms.decodeOctetString (2, "alphaNumInd");
		value.addressName = ms.decodeOctetString (41, "addressName");
		value.directional = ms.decodeOctetString (4, "directional");
		value.highAddrRange = ms.decodeOctetString (16, "highAddrRange");
		value.lowAddrRange = ms.decodeOctetString (16, "lowAddrRange");
		value.oddEvenIndicator = ms.decodeOctetString (2, "oddEvenIndicator");
		value.exchange = ms.decodeOctetString (5, "exchange");
		value.centralOffice = ms.decodeOctetString (4, "centralOffice");
		value.map = ms.decodeOctetString (5, "map");
		value.rateBandZone = ms.decodeOctetString (3, "rateBandZone");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.npa = ms.decodeOctetString (4, "npa");
		value.countyAbbrev = ms.decodeOctetString (5, "countyAbbrev");
		value.municipality = ms.decodeOctetString (4, "municipality");
		value.filler = ms.decodeOctetString (2, "filler");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodesagkey_st (ms, value, tag); 
	}
	static public void encodesagkey_st (MMarshalStrategy ms, sagkey_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.raiCode, 3, "raiCode");
	ms.encode (value.sagAreaId, 2, "sagAreaId");
ms.encode (value.alphaNumInd, 2, "alphaNumInd");
ms.encode (value.addressName, 41, "addressName");
ms.encode (value.directional, 4, "directional");
ms.encode (value.highAddrRange, 16, "highAddrRange");
ms.encode (value.lowAddrRange, 16, "lowAddrRange");
ms.encode (value.oddEvenIndicator, 2, "oddEvenIndicator");
ms.encode (value.exchange, 5, "exchange");
ms.encode (value.centralOffice, 4, "centralOffice");
ms.encode (value.map, 5, "map");
ms.encode (value.rateBandZone, 3, "rateBandZone");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.npa, 4, "npa");
ms.encode (value.countyAbbrev, 5, "countyAbbrev");
ms.encode (value.municipality, 4, "municipality");
ms.encode (value.filler, 2, "filler");
ms.endStruct (tag, true); 
}
public static sagkey_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodesagkey_st (ms, "sagkey_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_stHelper.type(); 
}
public static byte [] toOctet (sagkey_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodesagkey_st (ms, val, "sagkey_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
