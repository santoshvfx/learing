// $Id: BascAddrMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class BascAddrMenuItem_tMsg implements MMarshalObject { 
	public BascAddrMenuItem_t value;

	public BascAddrMenuItem_tMsg () {
	}
	public BascAddrMenuItem_tMsg (BascAddrMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t();
value.StNbrId = new com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t();
value.ASGND_HOUS_NBR_ID = new String ();
value.StNm = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t();
value.CMTY_NM = new String ();
value.SAG_RMK_1_DESC = new String ();
value.SAG_RMK_2_DESC = new String ();
value.SAG_RMK_3_DESC = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeBascAddrMenuItem_t (ms, tag); 
	}
	static public BascAddrMenuItem_t decodeBascAddrMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		BascAddrMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.StNbrId = com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tMsg.decodeStNbrId_t (ms, "StNbrId");
		value.ASGND_HOUS_NBR_ID = ms.decodeOctetString (13, "ASGND_HOUS_NBR_ID");
		value.StNm = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.decodePrmStNm_t (ms, "StNm");
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.SAG_RMK_1_DESC = ms.decodeOctetString (73, "SAG_RMK_1_DESC");
		value.SAG_RMK_2_DESC = ms.decodeOctetString (73, "SAG_RMK_2_DESC");
		value.SAG_RMK_3_DESC = ms.decodeOctetString (73, "SAG_RMK_3_DESC");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeBascAddrMenuItem_t (ms, value, tag); 
	}
	static public void encodeBascAddrMenuItem_t (MMarshalStrategy ms, BascAddrMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_tMsg.encodeStNbrId_t (ms, value.StNbrId, "StNbrId");
		ms.encode (value.ASGND_HOUS_NBR_ID, 13, "ASGND_HOUS_NBR_ID");
	com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.encodePrmStNm_t (ms, value.StNm, "StNm");
	ms.encode (value.CMTY_NM, 33, "CMTY_NM");
ms.encode (value.SAG_RMK_1_DESC, 73, "SAG_RMK_1_DESC");
ms.encode (value.SAG_RMK_2_DESC, 73, "SAG_RMK_2_DESC");
ms.encode (value.SAG_RMK_3_DESC, 73, "SAG_RMK_3_DESC");
ms.endStruct (tag, true); 
}
public static BascAddrMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeBascAddrMenuItem_t (ms, "BascAddrMenuItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_tHelper.type(); 
}
public static byte [] toOctet (BascAddrMenuItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeBascAddrMenuItem_t (ms, val, "BascAddrMenuItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
