// $Id: SwngEntyDataItem_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwngEntyDataItem_tMsg implements MMarshalObject { 
	public SwngEntyDataItem_t value;

	public SwngEntyDataItem_tMsg () {
	}
	public SwngEntyDataItem_tMsg (SwngEntyDataItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t();
value.SWNG_ENTY_RMK_1_DESC = new String ();
value.SWNG_ENTY_RMK_2_DESC = new String ();
int __seqLength = 0;
value.IECData = new com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t[__seqLength];
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwngEntyDataItem_t (ms, tag); 
	}
	static public SwngEntyDataItem_t decodeSwngEntyDataItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwngEntyDataItem_t value = create();
		ms.startStruct (tag, false);
		value.SWNG_ENTY_RMK_1_DESC = ms.decodeOctetString (73, "SWNG_ENTY_RMK_1_DESC");
		value.SWNG_ENTY_RMK_2_DESC = ms.decodeOctetString (73, "SWNG_ENTY_RMK_2_DESC");
		{ 
			ms.startSequence ("IECData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.IECData = new com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.IECData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_tMsg.decodeIECDataItem_t (ms, "IECData");
			} 
		ms.endSequence ("IECData", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwngEntyDataItem_t (ms, value, tag); 
	}
	static public void encodeSwngEntyDataItem_t (MMarshalStrategy ms, SwngEntyDataItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWNG_ENTY_RMK_1_DESC, 73, "SWNG_ENTY_RMK_1_DESC");
	ms.encode (value.SWNG_ENTY_RMK_2_DESC, 73, "SWNG_ENTY_RMK_2_DESC");
{ 
	ms.startSequence ("IECData", true);
	ms.encode (value.IECData.length, "m_length", true);
	for (int __i = 0; __i < value.IECData.length; __i++) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_tMsg.encodeIECDataItem_t (ms, value.IECData[__i], "IECData");
	}
	ms.endSequence ("IECData", true);
}
ms.endStruct (tag, true); 
}
public static SwngEntyDataItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeSwngEntyDataItem_t (ms, "SwngEntyDataItem_t"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tHelper.type(); 
}
public static byte [] toOctet (SwngEntyDataItem_t val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeSwngEntyDataItem_t (ms, val, "SwngEntyDataItem_t");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
