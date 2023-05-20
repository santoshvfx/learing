// $Id: TnRsrvReq_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnRsrvReq_tMsg implements MMarshalObject { 
	public TnRsrvReq_t value;

	public TnRsrvReq_tMsg () {
	}
	public TnRsrvReq_tMsg (TnRsrvReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t();
value.SAGA = new String ();
value.PrmAddr = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t();
value.GEO_SEG_ID = new String ();
value.BLG_DT = new String ();
value.NPA_CD = new String ();
value.PRFX_CD = new String ();
value.TN_CTGY_CD = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnRsrvReq_t (ms, tag); 
	}
	static public TnRsrvReq_t decodeTnRsrvReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnRsrvReq_t value = create();
		ms.startStruct (tag, false);
		value.SAGA = ms.decodeOctetString (5, "SAGA");
		value.PrmAddr = com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.decodePrmAddr_t (ms, "PrmAddr");
		value.GEO_SEG_ID = ms.decodeOctetString (5, "GEO_SEG_ID");
		value.BLG_DT = ms.decodeOctetString (3, "BLG_DT");
		value.NPA_CD = ms.decodeOctetString (4, "NPA_CD");
		value.PRFX_CD = ms.decodeOctetString (4, "PRFX_CD");
		value.TN_CTGY_CD = ms.decodeOctetString (13, "TN_CTGY_CD");
		value.TN_REQ_QTY = ms.decodeLong ("TN_REQ_QTY");
		value.LN_ID = ms.decodeChar ("LN_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnRsrvReq_t (ms, value, tag); 
	}
	static public void encodeTnRsrvReq_t (MMarshalStrategy ms, TnRsrvReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAGA, 5, "SAGA");
	com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.encodePrmAddr_t (ms, value.PrmAddr, "PrmAddr");
	ms.encode (value.GEO_SEG_ID, 5, "GEO_SEG_ID");
ms.encode (value.BLG_DT, 3, "BLG_DT");
ms.encode (value.NPA_CD, 4, "NPA_CD");
ms.encode (value.PRFX_CD, 4, "PRFX_CD");
ms.encode (value.TN_CTGY_CD, 13, "TN_CTGY_CD");
ms.encode (value.TN_REQ_QTY, "TN_REQ_QTY");
ms.encode (value.LN_ID, "LN_ID");
ms.endStruct (tag, true); 
}
public static TnRsrvReq_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTnRsrvReq_t (ms, "TnRsrvReq_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tHelper.type(); 
}
public static byte [] toOctet (TnRsrvReq_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTnRsrvReq_t (ms, val, "TnRsrvReq_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
