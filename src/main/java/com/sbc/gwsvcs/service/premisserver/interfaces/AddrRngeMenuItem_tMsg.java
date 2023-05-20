// $Id: AddrRngeMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddrRngeMenuItem_tMsg implements MMarshalObject { 
	public AddrRngeMenuItem_t value;

	public AddrRngeMenuItem_tMsg () {
	}
	public AddrRngeMenuItem_tMsg (AddrRngeMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t();
value.AddrRnge = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t();
value.CMTY_NM = new String ();
value.STATE_CD = new String ();
value.ZC = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddrRngeMenuItem_t (ms, tag); 
	}
	static public AddrRngeMenuItem_t decodeAddrRngeMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddrRngeMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.AddrRnge = com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_tMsg.decodeAddrRnge_t (ms, "AddrRnge");
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		value.ZC = ms.decodeOctetString (6, "ZC");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddrRngeMenuItem_t (ms, value, tag); 
	}
	static public void encodeAddrRngeMenuItem_t (MMarshalStrategy ms, AddrRngeMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_tMsg.encodeAddrRnge_t (ms, value.AddrRnge, "AddrRnge");
		ms.encode (value.CMTY_NM, 33, "CMTY_NM");
	ms.encode (value.STATE_CD, 3, "STATE_CD");
ms.encode (value.ZC, 6, "ZC");
ms.endStruct (tag, true); 
}
public static AddrRngeMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeAddrRngeMenuItem_t (ms, "AddrRngeMenuItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_tHelper.type(); 
}
public static byte [] toOctet (AddrRngeMenuItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeAddrRngeMenuItem_t (ms, val, "AddrRngeMenuItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
