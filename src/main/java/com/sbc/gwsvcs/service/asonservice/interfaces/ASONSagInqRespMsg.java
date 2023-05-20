// $Id: ASONSagInqRespMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagInqRespMsg implements MMarshalObject { 
	public ASONSagInqResp value;

	public ASONSagInqRespMsg () {
	}
	public ASONSagInqRespMsg (ASONSagInqResp initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.advisoryMsg = new String ();
value.codeDisplay = new String ();
value.sagLines = new com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st[18];
for (int i0 = 0; i0 < 18; i0++) { 
value.sagLines[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagline_stMsg.create();
}
value.sagKeys = new com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st[18];
for (int i0 = 0; i0 < 18; i0++) { 
value.sagKeys[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_stMsg.create();
}
value.savedSagKey = new String ();
value.sagByPassArea = new com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st[18];
for (int i0 = 0; i0 < 18; i0++) { 
value.sagByPassArea[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_stMsg.create();
}
value.sentEndString = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagInqResp (ms, tag); 
	}
	static public ASONSagInqResp decodeASONSagInqResp (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagInqResp value = create();
		ms.startStruct (tag, false);
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.decodetaginformation_st (ms, "tagInformation");
		value.replyCode = ms.decodeShort ("replyCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.codeDisplay = ms.decodeOctetString (2, "codeDisplay");
		ms.startArray ("sagLines", false);
		{ 
			value.sagLines = new com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.sagLines[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagline_stMsg.decodesagline_st (ms, "sagLines");
			} 
		}
		ms.endArray ("sagLines", false);
		ms.startArray ("sagKeys", false);
		{ 
			value.sagKeys = new com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.sagKeys[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_stMsg.decodesagkey_st (ms, "sagKeys");
			} 
		}
		ms.endArray ("sagKeys", false);
		value.savedSagKey = ms.decodeOctetString (107, "savedSagKey");
		ms.startArray ("sagByPassArea", false);
		{ 
			value.sagByPassArea = new com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.sagByPassArea[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_stMsg.decodesagbypassarea_st (ms, "sagByPassArea");
			} 
		}
		ms.endArray ("sagByPassArea", false);
		value.sentEndString = ms.decodeOctetString (4, "sentEndString");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagInqResp (ms, value, tag); 
	}
	static public void encodeASONSagInqResp (MMarshalStrategy ms, ASONSagInqResp value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.encodetaginformation_st (ms, value.tagInformation, "tagInformation");
		ms.encode (value.replyCode, "replyCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.codeDisplay, 2, "codeDisplay");
ms.startArray ("sagLines", true);
{ 
	for (int __i0 = 0; __i0 < 18; __i0++) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.sagline_stMsg.encodesagline_st (ms, value.sagLines[__i0], "sagLines");
	} 
}
ms.endArray ("sagLines", true);
ms.startArray ("sagKeys", true);
{ 
	for (int __i0 = 0; __i0 < 18; __i0++) { 
		com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_stMsg.encodesagkey_st (ms, value.sagKeys[__i0], "sagKeys");
	} 
}
ms.endArray ("sagKeys", true);
ms.encode (value.savedSagKey, 107, "savedSagKey");
ms.startArray ("sagByPassArea", true);
{ 
for (int __i0 = 0; __i0 < 18; __i0++) { 
	com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_stMsg.encodesagbypassarea_st (ms, value.sagByPassArea[__i0], "sagByPassArea");
} 
}
ms.endArray ("sagByPassArea", true);
ms.encode (value.sentEndString, 4, "sentEndString");
ms.endStruct (tag, true); 
}
public static ASONSagInqResp fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONSagInqResp (ms, "ASONSagInqResp"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqRespHelper.type(); 
}
public static byte [] toOctet (ASONSagInqResp val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONSagInqResp (ms, val, "ASONSagInqResp");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
