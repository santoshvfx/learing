// $Id: StNbrId_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class StNbrId_tMsg implements MMarshalObject { 
	public StNbrId_t value;

	public StNbrId_tMsg () {
	}
	public StNbrId_tMsg (StNbrId_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t();
value.SAD_HOUS_PRFX = new String ();
value.SAD_HOUS_NBR = new String ();
value.SAD_HOUS_NBR_SUFX = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeStNbrId_t (ms, tag); 
	}
	static public StNbrId_t decodeStNbrId_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		StNbrId_t value = create();
		ms.startStruct (tag, false);
		value.SAD_HOUS_PRFX = ms.decodeOctetString (6, "SAD_HOUS_PRFX");
		value.SAD_HOUS_NBR = ms.decodeOctetString (9, "SAD_HOUS_NBR");
		value.SAD_HOUS_NBR_SUFX = ms.decodeOctetString (6, "SAD_HOUS_NBR_SUFX");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeStNbrId_t (ms, value, tag); 
	}
	static public void encodeStNbrId_t (MMarshalStrategy ms, StNbrId_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAD_HOUS_PRFX, 6, "SAD_HOUS_PRFX");
	ms.encode (value.SAD_HOUS_NBR, 9, "SAD_HOUS_NBR");
ms.encode (value.SAD_HOUS_NBR_SUFX, 6, "SAD_HOUS_NBR_SUFX");
ms.endStruct (tag, true); 
}
public static StNbrId_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeStNbrId_t (ms, "StNbrId_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tHelper.type(); 
}
public static byte [] toOctet (StNbrId_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeStNbrId_t (ms, val, "StNbrId_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
