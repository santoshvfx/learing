// $Id: TnaFacActnLnItem_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnaFacActnLnItem_tMsg implements MMarshalObject { 
	public TnaFacActnLnItem_t value;

	public TnaFacActnLnItem_tMsg () {
	}
	public TnaFacActnLnItem_tMsg (TnaFacActnLnItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t();
value.CMTY_NM = new String ();
value.STATE_CD = new String ();
int __seqLength = 0;
value.TnSucc = new com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t[__seqLength];
value.TEL_FEAT_RMK_1_DESC = new String ();
value.TEL_FEAT_RMK_2_DESC = new String ();
value.TEL_FEAT_RMK_3_DESC = new String ();
value.TEL_FEAT_RMK_4_DESC = new String ();
value.AddlLnData = new com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t[__seqLength];
value.SwngEntyData = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t[__seqLength];
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnaFacActnLnItem_t (ms, tag); 
	}
	static public TnaFacActnLnItem_t decodeTnaFacActnLnItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnaFacActnLnItem_t value = create();
		ms.startStruct (tag, false);
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		{ 
			ms.startSequence ("TnSucc", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.TnSucc = new com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.TnSucc[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_tMsg.decodeTnSucc_t (ms, "TnSucc");
			} 
		ms.endSequence ("TnSucc", false);
		}
		value.TEL_FEAT_RMK_1_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_1_DESC");
		value.TEL_FEAT_RMK_2_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_2_DESC");
		value.TEL_FEAT_RMK_3_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_3_DESC");
		value.TEL_FEAT_RMK_4_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_4_DESC");
		{ 
			ms.startSequence ("AddlLnData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.AddlLnData = new com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AddlLnData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_tMsg.decodeAddlLnDataItem_t (ms, "AddlLnData");
			} 
		ms.endSequence ("AddlLnData", false);
		}
		{ 
			ms.startSequence ("SwngEntyData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SwngEntyData = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SwngEntyData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tMsg.decodeSwngEntyDataItem_t (ms, "SwngEntyData");
			} 
		ms.endSequence ("SwngEntyData", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnaFacActnLnItem_t (ms, value, tag); 
	}
	static public void encodeTnaFacActnLnItem_t (MMarshalStrategy ms, TnaFacActnLnItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CMTY_NM, 33, "CMTY_NM");
	ms.encode (value.STATE_CD, 3, "STATE_CD");
{ 
	ms.startSequence ("TnSucc", true);
	ms.encode (value.TnSucc.length, "m_length", true);
	for (int __i = 0; __i < value.TnSucc.length; __i++) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_tMsg.encodeTnSucc_t (ms, value.TnSucc[__i], "TnSucc");
	}
	ms.endSequence ("TnSucc", true);
}
ms.encode (value.TEL_FEAT_RMK_1_DESC, 73, "TEL_FEAT_RMK_1_DESC");
ms.encode (value.TEL_FEAT_RMK_2_DESC, 73, "TEL_FEAT_RMK_2_DESC");
ms.encode (value.TEL_FEAT_RMK_3_DESC, 73, "TEL_FEAT_RMK_3_DESC");
ms.encode (value.TEL_FEAT_RMK_4_DESC, 73, "TEL_FEAT_RMK_4_DESC");
{ 
ms.startSequence ("AddlLnData", true);
ms.encode (value.AddlLnData.length, "m_length", true);
for (int __i = 0; __i < value.AddlLnData.length; __i++) { 
com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_tMsg.encodeAddlLnDataItem_t (ms, value.AddlLnData[__i], "AddlLnData");
}
ms.endSequence ("AddlLnData", true);
}
{ 
ms.startSequence ("SwngEntyData", true);
ms.encode (value.SwngEntyData.length, "m_length", true);
for (int __i = 0; __i < value.SwngEntyData.length; __i++) { 
com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tMsg.encodeSwngEntyDataItem_t (ms, value.SwngEntyData[__i], "SwngEntyData");
}
ms.endSequence ("SwngEntyData", true);
}
ms.endStruct (tag, true); 
}
public static TnaFacActnLnItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTnaFacActnLnItem_t (ms, "TnaFacActnLnItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tHelper.type(); 
}
public static byte [] toOctet (TnaFacActnLnItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTnaFacActnLnItem_t (ms, val, "TnaFacActnLnItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
