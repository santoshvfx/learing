// $Id: IECDataItem_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class IECDataItem_tMsg implements MMarshalObject { 
	public IECDataItem_t value;

	public IECDataItem_tMsg () {
	}
	public IECDataItem_tMsg (IECDataItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t();
value.IEC_CD = new String ();
value.IEC_RMK_1_DESC = new String ();
value.IEC_RMK_2_DESC = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeIECDataItem_t (ms, tag); 
	}
	static public IECDataItem_t decodeIECDataItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		IECDataItem_t value = create();
		ms.startStruct (tag, false);
		value.IEC_CD = ms.decodeOctetString (6, "IEC_CD");
		value.IEC_RMK_1_DESC = ms.decodeOctetString (66, "IEC_RMK_1_DESC");
		value.IEC_RMK_2_DESC = ms.decodeOctetString (66, "IEC_RMK_2_DESC");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeIECDataItem_t (ms, value, tag); 
	}
	static public void encodeIECDataItem_t (MMarshalStrategy ms, IECDataItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.IEC_CD, 6, "IEC_CD");
	ms.encode (value.IEC_RMK_1_DESC, 66, "IEC_RMK_1_DESC");
ms.encode (value.IEC_RMK_2_DESC, 66, "IEC_RMK_2_DESC");
ms.endStruct (tag, true); 
}
public static IECDataItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeIECDataItem_t (ms, "IECDataItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_tHelper.type(); 
}
public static byte [] toOctet (IECDataItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeIECDataItem_t (ms, val, "IECDataItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
