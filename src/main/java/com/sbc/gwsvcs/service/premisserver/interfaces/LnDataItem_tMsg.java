// $Id: LnDataItem_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class LnDataItem_tMsg implements MMarshalObject { 
	public LnDataItem_t value;

	public LnDataItem_tMsg () {
	}
	public LnDataItem_tMsg (LnDataItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t();
value.LnInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t();
value.MODULR_WIRE_ID = new String ();
value.DISCT_REAS_DESC = new String ();
value.ActvtyDt = new com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t();
value.COS = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLnDataItem_t (ms, tag); 
	}
	static public LnDataItem_t decodeLnDataItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		LnDataItem_t value = create();
		ms.startStruct (tag, false);
		value.LnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tMsg.decodeLnInfo_t (ms, "LnInfo");
		value.MODULR_WIRE_ID = ms.decodeOctetString (21, "MODULR_WIRE_ID");
		value.DISCT_REAS_DESC = ms.decodeOctetString (6, "DISCT_REAS_DESC");
		value.ActvtyDt = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.decodeDt_t (ms, "ActvtyDt");
		value.CONN_THRU_IND = ms.decodeChar ("CONN_THRU_IND");
		value.CONN_FAC_IND = ms.decodeChar ("CONN_FAC_IND");
		value.DED_INSD_PLNT_IND = ms.decodeChar ("DED_INSD_PLNT_IND");
		value.COS = ms.decodeOctetString (6, "COS");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLnDataItem_t (ms, value, tag); 
	}
	static public void encodeLnDataItem_t (MMarshalStrategy ms, LnDataItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tMsg.encodeLnInfo_t (ms, value.LnInfo, "LnInfo");
		ms.encode (value.MODULR_WIRE_ID, 21, "MODULR_WIRE_ID");
	ms.encode (value.DISCT_REAS_DESC, 6, "DISCT_REAS_DESC");
com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.ActvtyDt, "ActvtyDt");
ms.encode (value.CONN_THRU_IND, "CONN_THRU_IND");
ms.encode (value.CONN_FAC_IND, "CONN_FAC_IND");
ms.encode (value.DED_INSD_PLNT_IND, "DED_INSD_PLNT_IND");
ms.encode (value.COS, 6, "COS");
ms.endStruct (tag, true); 
}
public static LnDataItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeLnDataItem_t (ms, "LnDataItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_tHelper.type(); 
}
public static byte [] toOctet (LnDataItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeLnDataItem_t (ms, val, "LnDataItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
