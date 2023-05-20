// $Id: TNAMttReq_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TNAMttReq_tMsg implements MMarshalObject { 
	public TNAMttReq_t value;

	public TNAMttReq_tMsg () {
	}
	public TNAMttReq_tMsg (TNAMttReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_t();
value.WC_CD = new String ();
value.TERMN_TRAF_AREA_CD = new String ();
value.TN_LIST_2_NBR = new String ();
value.TN = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t();
value.TN_CTGY_CD = new String ();
value.ADDR_TX = new String ();
value.SO_DDT = new com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t();
value.SO_3_NBR = new String ();
value.COS = new String ();
value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTNAMttReq_t (ms, tag); 
	}
	static public TNAMttReq_t decodeTNAMttReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TNAMttReq_t value = create();
		ms.startStruct (tag, false);
		value.WC_CD = ms.decodeOctetString (9, "WC_CD");
		value.TERMN_TRAF_AREA_CD = ms.decodeOctetString (4, "TERMN_TRAF_AREA_CD");
		value.TN_LIST_2_NBR = ms.decodeOctetString (13, "TN_LIST_2_NBR");
		value.TN = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "TN");
		value.TN_CTGY_CD = ms.decodeOctetString (13, "TN_CTGY_CD");
		value.ADDR_TX = ms.decodeOctetString (71, "ADDR_TX");
		value.SO_DDT = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.decodeDt_t (ms, "SO_DDT");
		value.SO_3_NBR = ms.decodeOctetString (14, "SO_3_NBR");
		value.COS = ms.decodeOctetString (6, "COS");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTNAMttReq_t (ms, value, tag); 
	}
	static public void encodeTNAMttReq_t (MMarshalStrategy ms, TNAMttReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.WC_CD, 9, "WC_CD");
	ms.encode (value.TERMN_TRAF_AREA_CD, 4, "TERMN_TRAF_AREA_CD");
ms.encode (value.TN_LIST_2_NBR, 13, "TN_LIST_2_NBR");
com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.TN, "TN");
ms.encode (value.TN_CTGY_CD, 13, "TN_CTGY_CD");
ms.encode (value.ADDR_TX, 71, "ADDR_TX");
com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.SO_DDT, "SO_DDT");
ms.encode (value.SO_3_NBR, 14, "SO_3_NBR");
ms.encode (value.COS, 6, "COS");
com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
ms.endStruct (tag, true); 
}
public static TNAMttReq_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTNAMttReq_t (ms, "TNAMttReq_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_tHelper.type(); 
}
public static byte [] toOctet (TNAMttReq_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTNAMttReq_t (ms, val, "TNAMttReq_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
