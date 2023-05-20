// $Id: RapReq_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RapReq_tMsg implements MMarshalObject { 
	public RapReq_t value;

	public RapReq_tMsg () {
	}
	public RapReq_tMsg (RapReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t();
value.SAGA = new String ();
value.ZC = new String ();
value.NpaPrfxLn = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t();
value.Addr = new com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRapReq_t (ms, tag); 
	}
	static public RapReq_t decodeRapReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RapReq_t value = create();
		ms.startStruct (tag, false);
		value.SAGA = ms.decodeOctetString (5, "SAGA");
		value.ZC = ms.decodeOctetString (6, "ZC");
		value.NpaPrfxLn = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "NpaPrfxLn");
		value.Addr = com.sbc.gwsvcs.service.premisserver.interfaces.Addr_tMsg.decodeAddr_t (ms, "Addr");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRapReq_t (ms, value, tag); 
	}
	static public void encodeRapReq_t (MMarshalStrategy ms, RapReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAGA, 5, "SAGA");
	ms.encode (value.ZC, 6, "ZC");
com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.NpaPrfxLn, "NpaPrfxLn");
com.sbc.gwsvcs.service.premisserver.interfaces.Addr_tMsg.encodeAddr_t (ms, value.Addr, "Addr");
ms.endStruct (tag, true); 
}
public static RapReq_t fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeRapReq_t (ms, "RapReq_t"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_tHelper.type(); 
}
public static byte [] toOctet (RapReq_t val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeRapReq_t (ms, val, "RapReq_t");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
