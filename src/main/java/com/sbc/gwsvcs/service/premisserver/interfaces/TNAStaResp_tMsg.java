// $Id: TNAStaResp_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TNAStaResp_tMsg implements MMarshalObject { 
	public TNAStaResp_t value;

	public TNAStaResp_tMsg () {
	}
	public TNAStaResp_tMsg (TNAStaResp_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t();
value.TN = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t();
value.WC_CD = new String ();
value.TERMN_TRAF_AREA_CD = new String ();
value.TN_LIST_2_NBR = new String ();
value.TN_STS_CD = new String ();
value.TN_STS_2_DT = new com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t();
value.SO_DDT = new com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t();
value.SO_3_NBR = new String ();
value.COS = new String ();
value.ADDR_TX = new String ();
value.HTG_TN_2_ID = new String[4];
for (int i0 = 0; i0 < 4; i0++) { 
value.HTG_TN_2_ID[i0]= new String();
}
value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTNAStaResp_t (ms, tag); 
	}
	static public TNAStaResp_t decodeTNAStaResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TNAStaResp_t value = create();
		ms.startStruct (tag, false);
		value.TN = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "TN");
		value.WC_CD = ms.decodeOctetString (9, "WC_CD");
		value.TERMN_TRAF_AREA_CD = ms.decodeOctetString (4, "TERMN_TRAF_AREA_CD");
		value.TN_LIST_2_NBR = ms.decodeOctetString (13, "TN_LIST_2_NBR");
		value.TN_STS_CD = ms.decodeOctetString (2, "TN_STS_CD");
		value.TN_STS_2_DT = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.decodeDt_t (ms, "TN_STS_2_DT");
		value.SO_DDT = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.decodeDt_t (ms, "SO_DDT");
		value.SO_3_NBR = ms.decodeOctetString (14, "SO_3_NBR");
		value.COS = ms.decodeOctetString (6, "COS");
		value.ADDR_TX = ms.decodeOctetString (71, "ADDR_TX");
		ms.startArray ("HTG_TN_2_ID", false);
		{ 
			value.HTG_TN_2_ID = new String[4];
			for (int __i0 = 0; __i0 < 4; __i0++) { 
				value.HTG_TN_2_ID[__i0] = ms.decodeOctetString (11, "HTG_TN_2_ID");
			} 
		}
		ms.endArray ("HTG_TN_2_ID", false);
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTNAStaResp_t (ms, value, tag); 
	}
	static public void encodeTNAStaResp_t (MMarshalStrategy ms, TNAStaResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.TN, "TN");
		ms.encode (value.WC_CD, 9, "WC_CD");
	ms.encode (value.TERMN_TRAF_AREA_CD, 4, "TERMN_TRAF_AREA_CD");
ms.encode (value.TN_LIST_2_NBR, 13, "TN_LIST_2_NBR");
ms.encode (value.TN_STS_CD, 2, "TN_STS_CD");
com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.TN_STS_2_DT, "TN_STS_2_DT");
com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.SO_DDT, "SO_DDT");
ms.encode (value.SO_3_NBR, 14, "SO_3_NBR");
ms.encode (value.COS, 6, "COS");
ms.encode (value.ADDR_TX, 71, "ADDR_TX");
ms.startArray ("HTG_TN_2_ID", true);
{ 
for (int __i0 = 0; __i0 < 4; __i0++) { 
ms.encode (value.HTG_TN_2_ID[__i0], 11, "HTG_TN_2_ID");
} 
}
ms.endArray ("HTG_TN_2_ID", true);
com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
ms.endStruct (tag, true); 
}
public static TNAStaResp_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTNAStaResp_t (ms, "TNAStaResp_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_tHelper.type(); 
}
public static byte [] toOctet (TNAStaResp_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTNAStaResp_t (ms, val, "TNAStaResp_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
