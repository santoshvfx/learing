package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class INRNG_tMsg implements MMarshalObject { 
	public INRNG_t value;

	public INRNG_tMsg () {
	}
	public INRNG_tMsg (INRNG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeINRNG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeINRNG_t (ms, tag); 
	}
	static public INRNG_t decodeINRNG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		INRNG_t value = create();
		ms.startStruct (tag, false);
		value.CA = ms.decodeOctetString (11, "CA");
		value.LPR = ms.decodeOctetString (5, "LPR");
		value.HPR = ms.decodeOctetString (5, "HPR");
		value.LSPBP = ms.decodeOctetString (5, "LSPBP");
		value.PRF = ms.decodeOctetString (2, "PRF");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeINRNG_t (MMarshalStrategy ms, INRNG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.LPR, 5, "LPR");
		ms.encode (value.HPR, 5, "HPR");
		ms.encode (value.LSPBP, 5, "LSPBP");
		ms.encode (value.PRF, 2, "PRF");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_tHelper.type(); 
	}
	public static byte [] toOctet (INRNG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeINRNG_t (ms, val, "INRNG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static INRNG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeINRNG_t (ms, "INRNG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_t();
		value.CA = new String ();
		value.LPR = new String ();
		value.HPR = new String ();
		value.LSPBP = new String ();
		value.PRF = new String ();
		return value; 
	} 
}
