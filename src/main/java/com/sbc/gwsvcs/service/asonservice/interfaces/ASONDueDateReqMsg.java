// $Id: ASONDueDateReqMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONDueDateReqMsg implements MMarshalObject { 
	public ASONDueDateReq value;

	public ASONDueDateReqMsg () {
	}
	public ASONDueDateReqMsg (ASONDueDateReq initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateReq create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateReq();
value.advisoryMsg = new String ();
value.hlpWhen = new String ();
value.sagDesired = new String ();
value.resOrBus = new String ();
value.filler1 = new String ();
value.dateKey = new String ();
value.functionKeyDepressed = new String ();
value.helpCrossRefKey = new String ();
value.helpTextKey = new String ();
value.helpCursorRow = new String ();
value.idGroup = new String ();
value.idTerminal = new String ();
value.idTypist = new String ();
value.timeKey = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONDueDateReq (ms, tag); 
	}
	static public ASONDueDateReq decodeASONDueDateReq (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONDueDateReq value = create();
		ms.startStruct (tag, false);
		value.requestCode = ms.decodeShort ("requestCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.hlpWhen = ms.decodeOctetString (10, "hlpWhen");
		value.sagDesired = ms.decodeOctetString (11, "sagDesired");
		value.hyphen = ms.decodeChar ("hyphen");
		value.resOrBus = ms.decodeOctetString (6, "resOrBus");
		value.filler1 = ms.decodeOctetString (11, "filler1");
		value.dateKey = ms.decodeOctetString (7, "dateKey");
		value.functionKeyDepressed = ms.decodeOctetString (3, "functionKeyDepressed");
		value.helpCrossRefKey = ms.decodeOctetString (13, "helpCrossRefKey");
		value.helpTextKey = ms.decodeOctetString (12, "helpTextKey");
		value.helpCursorRow = ms.decodeOctetString (3, "helpCursorRow");
		value.idGroup = ms.decodeOctetString (6, "idGroup");
		value.idTerminal = ms.decodeOctetString (16, "idTerminal");
		value.idTypist = ms.decodeOctetString (4, "idTypist");
		value.timeKey = ms.decodeOctetString (9, "timeKey");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONDueDateReq (ms, value, tag); 
	}
	static public void encodeASONDueDateReq (MMarshalStrategy ms, ASONDueDateReq value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.requestCode, "requestCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.hlpWhen, 10, "hlpWhen");
ms.encode (value.sagDesired, 11, "sagDesired");
ms.encode (value.hyphen, "hyphen");
ms.encode (value.resOrBus, 6, "resOrBus");
ms.encode (value.filler1, 11, "filler1");
ms.encode (value.dateKey, 7, "dateKey");
ms.encode (value.functionKeyDepressed, 3, "functionKeyDepressed");
ms.encode (value.helpCrossRefKey, 13, "helpCrossRefKey");
ms.encode (value.helpTextKey, 12, "helpTextKey");
ms.encode (value.helpCursorRow, 3, "helpCursorRow");
ms.encode (value.idGroup, 6, "idGroup");
ms.encode (value.idTerminal, 16, "idTerminal");
ms.encode (value.idTypist, 4, "idTypist");
ms.encode (value.timeKey, 9, "timeKey");
ms.endStruct (tag, true); 
}
public static ASONDueDateReq fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONDueDateReq (ms, "ASONDueDateReq"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateReqHelper.type(); 
}
public static byte [] toOctet (ASONDueDateReq val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONDueDateReq (ms, val, "ASONDueDateReq");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
