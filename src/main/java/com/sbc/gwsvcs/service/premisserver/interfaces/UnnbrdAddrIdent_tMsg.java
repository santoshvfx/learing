// $Id: UnnbrdAddrIdent_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdAddrIdent_tMsg implements MMarshalObject { 
	public UnnbrdAddrIdent_t value;

	public UnnbrdAddrIdent_tMsg () {
	}
	public UnnbrdAddrIdent_tMsg (UnnbrdAddrIdent_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t();
value.PSTL_RTE_CD = new String ();
value.BOX_CD = new String ();
value.LSTD_NM = new String ();
value.UNNBR_SRCH_STS_IND = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnnbrdAddrIdent_t (ms, tag); 
	}
	static public UnnbrdAddrIdent_t decodeUnnbrdAddrIdent_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnnbrdAddrIdent_t value = create();
		ms.startStruct (tag, false);
		value.PSTL_RTE_CD = ms.decodeOctetString (5, "PSTL_RTE_CD");
		value.BOX_CD = ms.decodeOctetString (9, "BOX_CD");
		value.LSTD_NM = ms.decodeOctetString (61, "LSTD_NM");
		value.UNNBR_SRCH_STS_IND = ms.decodeOctetString (3, "UNNBR_SRCH_STS_IND");
		value.GEO_SEG_REQ_IND = ms.decodeChar ("GEO_SEG_REQ_IND");
		value.ASGND_HOUS_NBR_LSTG_IND = ms.decodeChar ("ASGND_HOUS_NBR_LSTG_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnnbrdAddrIdent_t (ms, value, tag); 
	}
	static public void encodeUnnbrdAddrIdent_t (MMarshalStrategy ms, UnnbrdAddrIdent_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PSTL_RTE_CD, 5, "PSTL_RTE_CD");
	ms.encode (value.BOX_CD, 9, "BOX_CD");
ms.encode (value.LSTD_NM, 61, "LSTD_NM");
ms.encode (value.UNNBR_SRCH_STS_IND, 3, "UNNBR_SRCH_STS_IND");
ms.encode (value.GEO_SEG_REQ_IND, "GEO_SEG_REQ_IND");
ms.encode (value.ASGND_HOUS_NBR_LSTG_IND, "ASGND_HOUS_NBR_LSTG_IND");
ms.endStruct (tag, true); 
}
public static UnnbrdAddrIdent_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeUnnbrdAddrIdent_t (ms, "UnnbrdAddrIdent_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_tHelper.type(); 
}
public static byte [] toOctet (UnnbrdAddrIdent_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeUnnbrdAddrIdent_t (ms, val, "UnnbrdAddrIdent_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
