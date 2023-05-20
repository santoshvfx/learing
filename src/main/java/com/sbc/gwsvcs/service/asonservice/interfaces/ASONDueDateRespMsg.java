// $Id: ASONDueDateRespMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONDueDateRespMsg implements MMarshalObject { 
	public ASONDueDateResp value;

	public ASONDueDateRespMsg () {
	}
	public ASONDueDateRespMsg (ASONDueDateResp initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateResp();
value.advisoryMsg = new String ();
value.stuffWeDoNotNeed = new String ();
value.openDates = new com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st[15];
for (int i0 = 0; i0 < 15; i0++) { 
value.openDates[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.openDates_stMsg.create();
}
value.helpTextKey = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONDueDateResp (ms, tag); 
	}
	static public ASONDueDateResp decodeASONDueDateResp (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONDueDateResp value = create();
		ms.startStruct (tag, false);
		value.replyCode = ms.decodeShort ("replyCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.codeDisplay = ms.decodeChar ("codeDisplay");
		value.stuffWeDoNotNeed = ms.decodeOctetString (157, "stuffWeDoNotNeed");
		ms.startArray ("openDates", false);
		{ 
			value.openDates = new com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st[15];
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				value.openDates[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.openDates_stMsg.decodeopenDates_st (ms, "openDates");
			} 
		}
		ms.endArray ("openDates", false);
		value.helpTextKey = ms.decodeOctetString (12, "helpTextKey");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONDueDateResp (ms, value, tag); 
	}
	static public void encodeASONDueDateResp (MMarshalStrategy ms, ASONDueDateResp value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.replyCode, "replyCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.codeDisplay, "codeDisplay");
	ms.encode (value.stuffWeDoNotNeed, 157, "stuffWeDoNotNeed");
ms.startArray ("openDates", true);
{ 
	for (int __i0 = 0; __i0 < 15; __i0++) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.openDates_stMsg.encodeopenDates_st (ms, value.openDates[__i0], "openDates");
	} 
}
ms.endArray ("openDates", true);
ms.encode (value.helpTextKey, 12, "helpTextKey");
ms.endStruct (tag, true); 
}
public static ASONDueDateResp fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONDueDateResp (ms, "ASONDueDateResp"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateRespHelper.type(); 
}
public static byte [] toOctet (ASONDueDateResp val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONDueDateResp (ms, val, "ASONDueDateResp");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
