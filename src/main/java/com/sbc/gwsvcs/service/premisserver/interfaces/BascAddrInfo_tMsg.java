// $Id: BascAddrInfo_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class BascAddrInfo_tMsg implements MMarshalObject { 
	public BascAddrInfo_t value;

	public BascAddrInfo_tMsg () {
	}
	public BascAddrInfo_tMsg (BascAddrInfo_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t();
value.StNbrId = new com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t();
value.ASGND_HOUS_NBR_ID = new String ();
value.StNm = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t();
value.CMTY_NM = new String ();
value.STATE_CD = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeBascAddrInfo_t (ms, tag); 
	}
	static public BascAddrInfo_t decodeBascAddrInfo_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		BascAddrInfo_t value = create();
		ms.startStruct (tag, false);
		value.StNbrId = com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tMsg.decodeStNbrId_t (ms, "StNbrId");
		value.ASGND_HOUS_NBR_ID = ms.decodeOctetString (13, "ASGND_HOUS_NBR_ID");
		value.StNm = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.decodePrmStNm_t (ms, "StNm");
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeBascAddrInfo_t (ms, value, tag); 
	}
	static public void encodeBascAddrInfo_t (MMarshalStrategy ms, BascAddrInfo_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tMsg.encodeStNbrId_t (ms, value.StNbrId, "StNbrId");
		ms.encode (value.ASGND_HOUS_NBR_ID, 13, "ASGND_HOUS_NBR_ID");
	com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.encodePrmStNm_t (ms, value.StNm, "StNm");
	ms.encode (value.CMTY_NM, 33, "CMTY_NM");
ms.encode (value.STATE_CD, 3, "STATE_CD");
ms.endStruct (tag, true); 
}
public static BascAddrInfo_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeBascAddrInfo_t (ms, "BascAddrInfo_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tHelper.type(); 
}
public static byte [] toOctet (BascAddrInfo_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeBascAddrInfo_t (ms, val, "BascAddrInfo_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
