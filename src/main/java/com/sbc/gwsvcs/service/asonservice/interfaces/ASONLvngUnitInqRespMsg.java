// $Id: ASONLvngUnitInqRespMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONLvngUnitInqRespMsg implements MMarshalObject { 
	public ASONLvngUnitInqResp value;

	public ASONLvngUnitInqRespMsg () {
	}
	public ASONLvngUnitInqRespMsg (ASONLvngUnitInqResp initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqResp create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqResp();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.advisoryMsg = new String ();
value.codeDisplay = new String ();
value.lowRangeDisplay = new String ();
value.highRangeDisplay = new String ();
value.infoLines = new com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st[15];
for (int i0 = 0; i0 < 15; i0++) { 
value.infoLines[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.infoline_stMsg.create();
}
value.savedLivuntKey = new String ();
value.stNbrFld1 = new String ();
value.stNbrFld2 = new String ();
value.locLocValue1 = new String ();
value.locLocValue2 = new String ();
value.locLocValue3 = new String ();
value.locLocValue4 = new String ();
value.locLocValue5 = new String ();
value.communityName = new String ();
value.lufRecordKeys = new com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st[15];
for (int i0 = 0; i0 < 15; i0++) { 
value.lufRecordKeys[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_stMsg.create();
}
value.lufLocTagsArea = new com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st[15];
for (int i0 = 0; i0 < 15; i0++) { 
value.lufLocTagsArea[i0] = com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_stMsg.create();
}
value.sentEndString = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONLvngUnitInqResp (ms, tag); 
	}
	static public ASONLvngUnitInqResp decodeASONLvngUnitInqResp (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONLvngUnitInqResp value = create();
		ms.startStruct (tag, false);
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.decodetaginformation_st (ms, "tagInformation");
		value.replyCode = ms.decodeShort ("replyCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.codeDisplay = ms.decodeOctetString (2, "codeDisplay");
		value.lowRangeDisplay = ms.decodeOctetString (16, "lowRangeDisplay");
		value.highRangeDisplay = ms.decodeOctetString (16, "highRangeDisplay");
		ms.startArray ("infoLines", false);
		{ 
			value.infoLines = new com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st[15];
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				value.infoLines[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.infoline_stMsg.decodeinfoline_st (ms, "infoLines");
			} 
		}
		ms.endArray ("infoLines", false);
		value.savedLivuntKey = ms.decodeOctetString (143, "savedLivuntKey");
		value.stNbrFld1 = ms.decodeOctetString (16, "stNbrFld1");
		value.stNbrFld2 = ms.decodeOctetString (5, "stNbrFld2");
		value.locLocValue1 = ms.decodeOctetString (11, "locLocValue1");
		value.locLocValue2 = ms.decodeOctetString (11, "locLocValue2");
		value.locLocValue3 = ms.decodeOctetString (11, "locLocValue3");
		value.locLocValue4 = ms.decodeOctetString (11, "locLocValue4");
		value.locLocValue5 = ms.decodeOctetString (11, "locLocValue5");
		value.communityName = ms.decodeOctetString (21, "communityName");
		ms.startArray ("lufRecordKeys", false);
		{ 
			value.lufRecordKeys = new com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st[15];
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				value.lufRecordKeys[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_stMsg.decodelufrecordkeys_st (ms, "lufRecordKeys");
			} 
		}
		ms.endArray ("lufRecordKeys", false);
		ms.startArray ("lufLocTagsArea", false);
		{ 
			value.lufLocTagsArea = new com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st[15];
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				value.lufLocTagsArea[__i0] = com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_stMsg.decodelufloctagsarea_st (ms, "lufLocTagsArea");
			} 
		}
		ms.endArray ("lufLocTagsArea", false);
		value.sentEndString = ms.decodeOctetString (4, "sentEndString");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONLvngUnitInqResp (ms, value, tag); 
	}
	static public void encodeASONLvngUnitInqResp (MMarshalStrategy ms, ASONLvngUnitInqResp value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.encodetaginformation_st (ms, value.tagInformation, "tagInformation");
		ms.encode (value.replyCode, "replyCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.codeDisplay, 2, "codeDisplay");
ms.encode (value.lowRangeDisplay, 16, "lowRangeDisplay");
ms.encode (value.highRangeDisplay, 16, "highRangeDisplay");
ms.startArray ("infoLines", true);
{ 
for (int __i0 = 0; __i0 < 15; __i0++) { 
com.sbc.gwsvcs.service.asonservice.interfaces.infoline_stMsg.encodeinfoline_st (ms, value.infoLines[__i0], "infoLines");
} 
}
ms.endArray ("infoLines", true);
ms.encode (value.savedLivuntKey, 143, "savedLivuntKey");
ms.encode (value.stNbrFld1, 16, "stNbrFld1");
ms.encode (value.stNbrFld2, 5, "stNbrFld2");
ms.encode (value.locLocValue1, 11, "locLocValue1");
ms.encode (value.locLocValue2, 11, "locLocValue2");
ms.encode (value.locLocValue3, 11, "locLocValue3");
ms.encode (value.locLocValue4, 11, "locLocValue4");
ms.encode (value.locLocValue5, 11, "locLocValue5");
ms.encode (value.communityName, 21, "communityName");
ms.startArray ("lufRecordKeys", true);
{ 
for (int __i0 = 0; __i0 < 15; __i0++) { 
com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_stMsg.encodelufrecordkeys_st (ms, value.lufRecordKeys[__i0], "lufRecordKeys");
} 
}
ms.endArray ("lufRecordKeys", true);
ms.startArray ("lufLocTagsArea", true);
{ 
for (int __i0 = 0; __i0 < 15; __i0++) { 
com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_stMsg.encodelufloctagsarea_st (ms, value.lufLocTagsArea[__i0], "lufLocTagsArea");
} 
}
ms.endArray ("lufLocTagsArea", true);
ms.encode (value.sentEndString, 4, "sentEndString");
ms.endStruct (tag, true); 
}
public static ASONLvngUnitInqResp fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONLvngUnitInqResp (ms, "ASONLvngUnitInqResp"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqRespHelper.type(); 
}
public static byte [] toOctet (ASONLvngUnitInqResp val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONLvngUnitInqResp (ms, val, "ASONLvngUnitInqResp");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
