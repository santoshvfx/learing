// $Id: lufrecordkeys_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class lufrecordkeys_stMsg implements MMarshalObject { 
	public lufrecordkeys_st value;

	public lufrecordkeys_stMsg () {
	}
	public lufrecordkeys_stMsg (lufrecordkeys_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st();
value.raiCode = new String ();
value.sagAreaId = new String ();
value.wireCenter = new String ();
value.communityName = new String ();
value.streetDirection = new String ();
value.streetName = new String ();
value.assignedHouseNumberInd = new String ();
value.stNbrFld1 = new String ();
value.stNbrFld2 = new String ();
value.locValue1 = new String ();
value.locValue2 = new String ();
value.locValue3 = new String ();
value.locValue4 = new String ();
value.locValue5 = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodelufrecordkeys_st (ms, tag); 
	}
	static public lufrecordkeys_st decodelufrecordkeys_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		lufrecordkeys_st value = create();
		ms.startStruct (tag, false);
		value.raiCode = ms.decodeOctetString (3, "raiCode");
		value.sagAreaId = ms.decodeOctetString (2, "sagAreaId");
		value.wireCenter = ms.decodeOctetString (7, "wireCenter");
		value.communityName = ms.decodeOctetString (21, "communityName");
		value.streetDirection = ms.decodeOctetString (4, "streetDirection");
		value.streetName = ms.decodeOctetString (41, "streetName");
		value.assignedHouseNumberInd = ms.decodeOctetString (2, "assignedHouseNumberInd");
		value.stNbrFld1 = ms.decodeOctetString (16, "stNbrFld1");
		value.stNbrFld2 = ms.decodeOctetString (5, "stNbrFld2");
		value.locValue1 = ms.decodeOctetString (11, "locValue1");
		value.locValue2 = ms.decodeOctetString (11, "locValue2");
		value.locValue3 = ms.decodeOctetString (11, "locValue3");
		value.locValue4 = ms.decodeOctetString (11, "locValue4");
		value.locValue5 = ms.decodeOctetString (11, "locValue5");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodelufrecordkeys_st (ms, value, tag); 
	}
	static public void encodelufrecordkeys_st (MMarshalStrategy ms, lufrecordkeys_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.raiCode, 3, "raiCode");
	ms.encode (value.sagAreaId, 2, "sagAreaId");
ms.encode (value.wireCenter, 7, "wireCenter");
ms.encode (value.communityName, 21, "communityName");
ms.encode (value.streetDirection, 4, "streetDirection");
ms.encode (value.streetName, 41, "streetName");
ms.encode (value.assignedHouseNumberInd, 2, "assignedHouseNumberInd");
ms.encode (value.stNbrFld1, 16, "stNbrFld1");
ms.encode (value.stNbrFld2, 5, "stNbrFld2");
ms.encode (value.locValue1, 11, "locValue1");
ms.encode (value.locValue2, 11, "locValue2");
ms.encode (value.locValue3, 11, "locValue3");
ms.encode (value.locValue4, 11, "locValue4");
ms.encode (value.locValue5, 11, "locValue5");
ms.endStruct (tag, true); 
}
public static lufrecordkeys_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodelufrecordkeys_st (ms, "lufrecordkeys_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_stHelper.type(); 
}
public static byte [] toOctet (lufrecordkeys_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodelufrecordkeys_st (ms, val, "lufrecordkeys_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
