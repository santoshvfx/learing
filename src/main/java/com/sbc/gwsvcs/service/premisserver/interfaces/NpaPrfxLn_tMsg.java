// $Id: NpaPrfxLn_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class NpaPrfxLn_tMsg implements MMarshalObject { 
	public NpaPrfxLn_t value;

	public NpaPrfxLn_tMsg () {
	}
	public NpaPrfxLn_tMsg (NpaPrfxLn_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t();
value.NPA = new String ();
value.PRFX_CD = new String ();
value.LN = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeNpaPrfxLn_t (ms, tag); 
	}
	static public NpaPrfxLn_t decodeNpaPrfxLn_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		NpaPrfxLn_t value = create();
		ms.startStruct (tag, false);
		value.NPA = ms.decodeOctetString (4, "NPA");
		value.PRFX_CD = ms.decodeOctetString (4, "PRFX_CD");
		value.LN = ms.decodeOctetString (5, "LN");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeNpaPrfxLn_t (ms, value, tag); 
	}
	static public void encodeNpaPrfxLn_t (MMarshalStrategy ms, NpaPrfxLn_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.NPA, 4, "NPA");
	ms.encode (value.PRFX_CD, 4, "PRFX_CD");
ms.encode (value.LN, 5, "LN");
ms.endStruct (tag, true); 
}
public static NpaPrfxLn_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeNpaPrfxLn_t (ms, "NpaPrfxLn_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tHelper.type(); 
}
public static byte [] toOctet (NpaPrfxLn_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeNpaPrfxLn_t (ms, val, "NpaPrfxLn_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
