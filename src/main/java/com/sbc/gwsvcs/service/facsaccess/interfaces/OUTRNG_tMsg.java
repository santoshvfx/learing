package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class OUTRNG_tMsg implements MMarshalObject { 
	public OUTRNG_t value;

	public OUTRNG_tMsg () {
	}
	public OUTRNG_tMsg (OUTRNG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeOUTRNG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeOUTRNG_t (ms, tag); 
	}
	static public OUTRNG_t decodeOUTRNG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		OUTRNG_t value = create();
		ms.startStruct (tag, false);
		value.CA = ms.decodeOctetString (11, "CA");
		value.LPR = ms.decodeOctetString (5, "LPR");
		value.HPR = ms.decodeOctetString (5, "HPR");
		value.LSPBP = ms.decodeOctetString (5, "LSPBP");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeOUTRNG_t (MMarshalStrategy ms, OUTRNG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.LPR, 5, "LPR");
		ms.encode (value.HPR, 5, "HPR");
		ms.encode (value.LSPBP, 5, "LSPBP");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tHelper.type(); 
	}
	public static byte [] toOctet (OUTRNG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeOUTRNG_t (ms, val, "OUTRNG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static OUTRNG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeOUTRNG_t (ms, "OUTRNG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_t();
		value.CA = new String ();
		value.LPR = new String ();
		value.HPR = new String ();
		value.LSPBP = new String ();
		return value; 
	} 
}
