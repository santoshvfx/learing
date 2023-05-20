// $Id: GeoSegMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GeoSegMenuItem_tMsg implements MMarshalObject { 
	public GeoSegMenuItem_t value;

	public GeoSegMenuItem_tMsg () {
	}
	public GeoSegMenuItem_tMsg (GeoSegMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t();
value.GEO_SEG_ID = new String ();
value.LOW_ASGND_HOUS_NBR_VALU_ID = new String ();
value.HI_ASGND_HOUS_NBR_VALU_ID = new String ();
value.CMTY_NM = new String ();
value.STATE_CD = new String ();
value.SagInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGeoSegMenuItem_t (ms, tag); 
	}
	static public GeoSegMenuItem_t decodeGeoSegMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GeoSegMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.GEO_SEG_ID = ms.decodeOctetString (5, "GEO_SEG_ID");
		value.LOW_ASGND_HOUS_NBR_VALU_ID = ms.decodeOctetString (14, "LOW_ASGND_HOUS_NBR_VALU_ID");
		value.HI_ASGND_HOUS_NBR_VALU_ID = ms.decodeOctetString (14, "HI_ASGND_HOUS_NBR_VALU_ID");
		value.ASGND_HOUS_NBR_RNGE_IND = ms.decodeChar ("ASGND_HOUS_NBR_RNGE_IND");
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		value.SagInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tMsg.decodeSagInfo_t (ms, "SagInfo");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGeoSegMenuItem_t (ms, value, tag); 
	}
	static public void encodeGeoSegMenuItem_t (MMarshalStrategy ms, GeoSegMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.GEO_SEG_ID, 5, "GEO_SEG_ID");
	ms.encode (value.LOW_ASGND_HOUS_NBR_VALU_ID, 14, "LOW_ASGND_HOUS_NBR_VALU_ID");
ms.encode (value.HI_ASGND_HOUS_NBR_VALU_ID, 14, "HI_ASGND_HOUS_NBR_VALU_ID");
ms.encode (value.ASGND_HOUS_NBR_RNGE_IND, "ASGND_HOUS_NBR_RNGE_IND");
ms.encode (value.CMTY_NM, 33, "CMTY_NM");
ms.encode (value.STATE_CD, 3, "STATE_CD");
com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tMsg.encodeSagInfo_t (ms, value.SagInfo, "SagInfo");
ms.endStruct (tag, true); 
}
public static GeoSegMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeGeoSegMenuItem_t (ms, "GeoSegMenuItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_tHelper.type(); 
}
public static byte [] toOctet (GeoSegMenuItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeGeoSegMenuItem_t (ms, val, "GeoSegMenuItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
