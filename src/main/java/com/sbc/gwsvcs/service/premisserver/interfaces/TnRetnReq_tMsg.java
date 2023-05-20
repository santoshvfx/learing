// $Id: TnRetnReq_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnRetnReq_tMsg implements MMarshalObject { 
	public TnRetnReq_t value;

	public TnRetnReq_tMsg () {
	}
	public TnRetnReq_tMsg (TnRetnReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_t();
value.SAGA = new String ();
value.PrmAddr = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t();
value.GEO_SEG_ID = new String ();
int __seqLength = 0;
value.NpaPrfxLn = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t[__seqLength];
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnRetnReq_t (ms, tag); 
	}
	static public TnRetnReq_t decodeTnRetnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnRetnReq_t value = create();
		ms.startStruct (tag, false);
		value.SAGA = ms.decodeOctetString (5, "SAGA");
		value.PrmAddr = com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.decodePrmAddr_t (ms, "PrmAddr");
		value.GEO_SEG_ID = ms.decodeOctetString (5, "GEO_SEG_ID");
		{ 
			ms.startSequence ("NpaPrfxLn", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.NpaPrfxLn = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.NpaPrfxLn[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "NpaPrfxLn");
			} 
		ms.endSequence ("NpaPrfxLn", false);
		}
		value.LN_ID = ms.decodeChar ("LN_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnRetnReq_t (ms, value, tag); 
	}
	static public void encodeTnRetnReq_t (MMarshalStrategy ms, TnRetnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAGA, 5, "SAGA");
	com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.encodePrmAddr_t (ms, value.PrmAddr, "PrmAddr");
	ms.encode (value.GEO_SEG_ID, 5, "GEO_SEG_ID");
{ 
	ms.startSequence ("NpaPrfxLn", true);
	ms.encode (value.NpaPrfxLn.length, "m_length", true);
	for (int __i = 0; __i < value.NpaPrfxLn.length; __i++) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.NpaPrfxLn[__i], "NpaPrfxLn");
	}
	ms.endSequence ("NpaPrfxLn", true);
}
ms.encode (value.LN_ID, "LN_ID");
ms.endStruct (tag, true); 
}
public static TnRetnReq_t fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeTnRetnReq_t (ms, "TnRetnReq_t"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_tHelper.type(); 
}
public static byte [] toOctet (TnRetnReq_t val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeTnRetnReq_t (ms, val, "TnRetnReq_t");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
