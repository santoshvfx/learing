// $Id: AddrMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddrMenuItem_tMsg implements MMarshalObject { 
	public AddrMenuItem_t value;

	public AddrMenuItem_tMsg () {
	}
	public AddrMenuItem_tMsg (AddrMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t();
value.CMTY_NM = new String ();
value.ALT_CMTY_1_NM = new String ();
value.ALT_CMTY_2_NM = new String ();
value.STATE_CD = new String ();
value.ZC = new String ();
value.AddrRnge = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddrMenuItem_t (ms, tag); 
	}
	static public AddrMenuItem_t decodeAddrMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddrMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.ALT_CMTY_1_NM = ms.decodeOctetString (33, "ALT_CMTY_1_NM");
		value.ALT_CMTY_2_NM = ms.decodeOctetString (33, "ALT_CMTY_2_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		value.ZC = ms.decodeOctetString (6, "ZC");
		value.AddrRnge = com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_tMsg.decodeAddrRnge_t (ms, "AddrRnge");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddrMenuItem_t (ms, value, tag); 
	}
	static public void encodeAddrMenuItem_t (MMarshalStrategy ms, AddrMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CMTY_NM, 33, "CMTY_NM");
	ms.encode (value.ALT_CMTY_1_NM, 33, "ALT_CMTY_1_NM");
ms.encode (value.ALT_CMTY_2_NM, 33, "ALT_CMTY_2_NM");
ms.encode (value.STATE_CD, 3, "STATE_CD");
ms.encode (value.ZC, 6, "ZC");
com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_tMsg.encodeAddrRnge_t (ms, value.AddrRnge, "AddrRnge");
ms.endStruct (tag, true); 
}
public static AddrMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeAddrMenuItem_t (ms, "AddrMenuItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_tHelper.type(); 
}
public static byte [] toOctet (AddrMenuItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeAddrMenuItem_t (ms, val, "AddrMenuItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
