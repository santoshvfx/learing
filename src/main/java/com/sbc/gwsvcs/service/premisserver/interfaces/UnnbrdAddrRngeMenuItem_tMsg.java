// $Id: UnnbrdAddrRngeMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdAddrRngeMenuItem_tMsg implements MMarshalObject { 
	public UnnbrdAddrRngeMenuItem_t value;

	public UnnbrdAddrRngeMenuItem_tMsg () {
	}
	public UnnbrdAddrRngeMenuItem_tMsg (UnnbrdAddrRngeMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t();
value.ASGND_HOUS_NBR_ID = new String ();
value.SuppAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t();
value.CMTY_NM = new String ();
value.STATE_CD = new String ();
value.ZC = new String ();
value.SPL_INSTR = new String ();
value.LnInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnnbrdAddrRngeMenuItem_t (ms, tag); 
	}
	static public UnnbrdAddrRngeMenuItem_t decodeUnnbrdAddrRngeMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnnbrdAddrRngeMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.ASGND_HOUS_NBR_ID = ms.decodeOctetString (13, "ASGND_HOUS_NBR_ID");
		value.SuppAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.decodeSuppAddrInfo_t (ms, "SuppAddrInfo");
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		value.ZC = ms.decodeOctetString (6, "ZC");
		value.SPL_INSTR = ms.decodeOctetString (101, "SPL_INSTR");
		value.LnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tMsg.decodeLnInfo_t (ms, "LnInfo");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnnbrdAddrRngeMenuItem_t (ms, value, tag); 
	}
	static public void encodeUnnbrdAddrRngeMenuItem_t (MMarshalStrategy ms, UnnbrdAddrRngeMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ASGND_HOUS_NBR_ID, 13, "ASGND_HOUS_NBR_ID");
	com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.encodeSuppAddrInfo_t (ms, value.SuppAddrInfo, "SuppAddrInfo");
	ms.encode (value.CMTY_NM, 33, "CMTY_NM");
ms.encode (value.STATE_CD, 3, "STATE_CD");
ms.encode (value.ZC, 6, "ZC");
ms.encode (value.SPL_INSTR, 101, "SPL_INSTR");
com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tMsg.encodeLnInfo_t (ms, value.LnInfo, "LnInfo");
ms.endStruct (tag, true); 
}
public static UnnbrdAddrRngeMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeUnnbrdAddrRngeMenuItem_t (ms, "UnnbrdAddrRngeMenuItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tHelper.type(); 
}
public static byte [] toOctet (UnnbrdAddrRngeMenuItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeUnnbrdAddrRngeMenuItem_t (ms, val, "UnnbrdAddrRngeMenuItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
