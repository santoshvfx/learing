// $Id: SagInfo_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SagInfo_tMsg implements MMarshalObject { 
	public SagInfo_t value;

	public SagInfo_tMsg () {
	}
	public SagInfo_tMsg (SagInfo_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t();
value.EXCH_ID = new String ();
value.WC_CD = new String ();
value.RT_ZONE_CD = new String ();
value.TAX_AREA_CD = new String ();
value.ZC = new String ();
int __seqLength = 0;
value.NPA_LST = new String[__seqLength];
value.TEL_FEAT_ID = new String ();
value.BUS_OFC_CD = new String ();
value.DIR_CD = new String ();
value.CO_ID = new String ();
value.LATA_PREMIS = new String ();
value.TERMN_TRAF_AREA_CD = new String ();
value.ATTN = new char[__seqLength];
value.GEO_SEG_RMK_DESC = new String ();
value.SAG_RMK_1_DESC = new String ();
value.SAG_RMK_2_DESC = new String ();
value.SAG_RMK_3_DESC = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSagInfo_t (ms, tag); 
	}
	static public SagInfo_t decodeSagInfo_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SagInfo_t value = create();
		ms.startStruct (tag, false);
		value.EXCH_ID = ms.decodeOctetString (5, "EXCH_ID");
		value.WC_CD = ms.decodeOctetString (9, "WC_CD");
		value.RT_ZONE_CD = ms.decodeOctetString (6, "RT_ZONE_CD");
		value.TAX_AREA_CD = ms.decodeOctetString (7, "TAX_AREA_CD");
		value.ZC = ms.decodeOctetString (6, "ZC");
		{ 
			ms.startSequence ("NPA_LST", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.NPA_LST = new String[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.NPA_LST[__i] = ms.decodeOctetString (4, "NPA_LST");
			} 
		ms.endSequence ("NPA_LST", false);
		}
		value.TEL_FEAT_ID = ms.decodeOctetString (6, "TEL_FEAT_ID");
		value.HGRP_AVAIL_IND = ms.decodeChar ("HGRP_AVAIL_IND");
		value.BUS_OFC_CD = ms.decodeOctetString (10, "BUS_OFC_CD");
		value.DIR_CD = ms.decodeOctetString (10, "DIR_CD");
		value.CO_ID = ms.decodeOctetString (10, "CO_ID");
		value.LATA_PREMIS = ms.decodeOctetString (10, "LATA_PREMIS");
		value.TERMN_TRAF_AREA_CD = ms.decodeOctetString (4, "TERMN_TRAF_AREA_CD");
		{ 
			ms.startSequence ("ATTN", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.ATTN = new char[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.ATTN[__i] = ms.decodeChar ("ATTN");
			} 
		ms.endSequence ("ATTN", false);
		}
		value.GEO_SEG_RMK_DESC = ms.decodeOctetString (11, "GEO_SEG_RMK_DESC");
		value.SAG_RMK_1_DESC = ms.decodeOctetString (73, "SAG_RMK_1_DESC");
		value.SAG_RMK_2_DESC = ms.decodeOctetString (73, "SAG_RMK_2_DESC");
		value.SAG_RMK_3_DESC = ms.decodeOctetString (73, "SAG_RMK_3_DESC");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSagInfo_t (ms, value, tag); 
	}
	static public void encodeSagInfo_t (MMarshalStrategy ms, SagInfo_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.EXCH_ID, 5, "EXCH_ID");
	ms.encode (value.WC_CD, 9, "WC_CD");
ms.encode (value.RT_ZONE_CD, 6, "RT_ZONE_CD");
ms.encode (value.TAX_AREA_CD, 7, "TAX_AREA_CD");
ms.encode (value.ZC, 6, "ZC");
{ 
ms.startSequence ("NPA_LST", true);
ms.encode (value.NPA_LST.length, "m_length", true);
for (int __i = 0; __i < value.NPA_LST.length; __i++) { 
ms.encode (value.NPA_LST[__i], 4, "NPA_LST");
}
ms.endSequence ("NPA_LST", true);
}
ms.encode (value.TEL_FEAT_ID, 6, "TEL_FEAT_ID");
ms.encode (value.HGRP_AVAIL_IND, "HGRP_AVAIL_IND");
ms.encode (value.BUS_OFC_CD, 10, "BUS_OFC_CD");
ms.encode (value.DIR_CD, 10, "DIR_CD");
ms.encode (value.CO_ID, 10, "CO_ID");
ms.encode (value.LATA_PREMIS, 10, "LATA_PREMIS");
ms.encode (value.TERMN_TRAF_AREA_CD, 4, "TERMN_TRAF_AREA_CD");
{ 
ms.startSequence ("ATTN", true);
ms.encode (value.ATTN.length, "m_length", true);
for (int __i = 0; __i < value.ATTN.length; __i++) { 
ms.encode (value.ATTN[__i], "ATTN");
}
ms.endSequence ("ATTN", true);
}
ms.encode (value.GEO_SEG_RMK_DESC, 11, "GEO_SEG_RMK_DESC");
ms.encode (value.SAG_RMK_1_DESC, 73, "SAG_RMK_1_DESC");
ms.encode (value.SAG_RMK_2_DESC, 73, "SAG_RMK_2_DESC");
ms.encode (value.SAG_RMK_3_DESC, 73, "SAG_RMK_3_DESC");
ms.endStruct (tag, true); 
}
public static SagInfo_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeSagInfo_t (ms, "SagInfo_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tHelper.type(); 
}
public static byte [] toOctet (SagInfo_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeSagInfo_t (ms, val, "SagInfo_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
