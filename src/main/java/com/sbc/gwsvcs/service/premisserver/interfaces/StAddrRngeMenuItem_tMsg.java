// $Id: StAddrRngeMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class StAddrRngeMenuItem_tMsg implements MMarshalObject { 
	public StAddrRngeMenuItem_t value;

	public StAddrRngeMenuItem_tMsg () {
	}
	public StAddrRngeMenuItem_tMsg (StAddrRngeMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t();
value.StNm = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t();
value.AddrRnge = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t();
value.CMTY_NM = new String ();
value.STATE_CD = new String ();
value.ZC = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeStAddrRngeMenuItem_t (ms, tag); 
	}
	static public StAddrRngeMenuItem_t decodeStAddrRngeMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		StAddrRngeMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.StNm = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.decodePrmStNm_t (ms, "StNm");
		value.AddrRnge = com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_tMsg.decodeAddrRnge_t (ms, "AddrRnge");
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		value.ZC = ms.decodeOctetString (6, "ZC");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeStAddrRngeMenuItem_t (ms, value, tag); 
	}
	static public void encodeStAddrRngeMenuItem_t (MMarshalStrategy ms, StAddrRngeMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.encodePrmStNm_t (ms, value.StNm, "StNm");
		com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_tMsg.encodeAddrRnge_t (ms, value.AddrRnge, "AddrRnge");
		ms.encode (value.CMTY_NM, 33, "CMTY_NM");
	ms.encode (value.STATE_CD, 3, "STATE_CD");
ms.encode (value.ZC, 6, "ZC");
ms.endStruct (tag, true); 
}
public static StAddrRngeMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeStAddrRngeMenuItem_t (ms, "StAddrRngeMenuItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_tHelper.type(); 
}
public static byte [] toOctet (StAddrRngeMenuItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeStAddrRngeMenuItem_t (ms, val, "StAddrRngeMenuItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
