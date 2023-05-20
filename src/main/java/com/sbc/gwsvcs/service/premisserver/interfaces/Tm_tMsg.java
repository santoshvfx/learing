// $Id: Tm_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Tm_tMsg implements MMarshalObject { 
	public Tm_t value;

	public Tm_tMsg () {
	}
	public Tm_tMsg (Tm_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.Tm_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.Tm_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.Tm_t();
value.HR = new String ();
value.MTE = new String ();
value.SECS = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTm_t (ms, tag); 
	}
	static public Tm_t decodeTm_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Tm_t value = create();
		ms.startStruct (tag, false);
		value.HR = ms.decodeOctetString (3, "HR");
		value.MTE = ms.decodeOctetString (3, "MTE");
		value.SECS = ms.decodeOctetString (3, "SECS");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTm_t (ms, value, tag); 
	}
	static public void encodeTm_t (MMarshalStrategy ms, Tm_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.HR, 3, "HR");
	ms.encode (value.MTE, 3, "MTE");
ms.encode (value.SECS, 3, "SECS");
ms.endStruct (tag, true); 
}
public static Tm_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTm_t (ms, "Tm_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.Tm_tHelper.type(); 
}
public static byte [] toOctet (Tm_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTm_t (ms, val, "Tm_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
