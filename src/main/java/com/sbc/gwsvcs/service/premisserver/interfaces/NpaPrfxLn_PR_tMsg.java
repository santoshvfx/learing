// $Id: NpaPrfxLn_PR_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class NpaPrfxLn_PR_tMsg implements MMarshalObject { 
	public NpaPrfxLn_PR_t value;

	public NpaPrfxLn_PR_tMsg () {
	}
	public NpaPrfxLn_PR_tMsg (NpaPrfxLn_PR_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_t();
value.NPA = new String ();
value.PRFX_CD = new String ();
value.LN = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeNpaPrfxLn_PR_t (ms, tag); 
	}
	static public NpaPrfxLn_PR_t decodeNpaPrfxLn_PR_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		NpaPrfxLn_PR_t value = create();
		ms.startStruct (tag, false);
		value.NPA = ms.decodeOctetString (4, "NPA");
		value.PRFX_CD = ms.decodeOctetString (4, "PRFX_CD");
		value.LN = ms.decodeOctetString (5, "LN");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeNpaPrfxLn_PR_t (ms, value, tag); 
	}
	static public void encodeNpaPrfxLn_PR_t (MMarshalStrategy ms, NpaPrfxLn_PR_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.NPA, 4, "NPA");
	ms.encode (value.PRFX_CD, 4, "PRFX_CD");
ms.encode (value.LN, 5, "LN");
ms.endStruct (tag, true); 
}
public static NpaPrfxLn_PR_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeNpaPrfxLn_PR_t (ms, "NpaPrfxLn_PR_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_tHelper.type(); 
}
public static byte [] toOctet (NpaPrfxLn_PR_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeNpaPrfxLn_PR_t (ms, val, "NpaPrfxLn_PR_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
