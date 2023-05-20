// $Id: PrmStNm_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PrmStNm_tMsg implements MMarshalObject { 
	public PrmStNm_t value;

	public PrmStNm_tMsg () {
	}
	public PrmStNm_tMsg (PrmStNm_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t();
value.SAD_ST_DRCTL = new String ();
value.SAD_ST_NM = new String ();
value.SAD_THRFR = new String ();
value.SAD_ST_SUFX = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePrmStNm_t (ms, tag); 
	}
	static public PrmStNm_t decodePrmStNm_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PrmStNm_t value = create();
		ms.startStruct (tag, false);
		value.SAD_ST_DRCTL = ms.decodeOctetString (3, "SAD_ST_DRCTL");
		value.SAD_ST_NM = ms.decodeOctetString (51, "SAD_ST_NM");
		value.SAD_THRFR = ms.decodeOctetString (11, "SAD_THRFR");
		value.SAD_ST_SUFX = ms.decodeOctetString (5, "SAD_ST_SUFX");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePrmStNm_t (ms, value, tag); 
	}
	static public void encodePrmStNm_t (MMarshalStrategy ms, PrmStNm_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAD_ST_DRCTL, 3, "SAD_ST_DRCTL");
	ms.encode (value.SAD_ST_NM, 51, "SAD_ST_NM");
ms.encode (value.SAD_THRFR, 11, "SAD_THRFR");
ms.encode (value.SAD_ST_SUFX, 5, "SAD_ST_SUFX");
ms.endStruct (tag, true); 
}
public static PrmStNm_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodePrmStNm_t (ms, "PrmStNm_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tHelper.type(); 
}
public static byte [] toOctet (PrmStNm_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodePrmStNm_t (ms, val, "PrmStNm_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
