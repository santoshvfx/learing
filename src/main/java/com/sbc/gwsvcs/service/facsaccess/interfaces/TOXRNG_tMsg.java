package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOXRNG_tMsg implements MMarshalObject { 
	public TOXRNG_t value;

	public TOXRNG_tMsg () {
	}
	public TOXRNG_tMsg (TOXRNG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTOXRNG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTOXRNG_t (ms, tag); 
	}
	static public TOXRNG_t decodeTOXRNG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TOXRNG_t value = create();
		ms.startStruct (tag, false);
		value.CA = ms.decodeOctetString (11, "CA");
		value.PR = ms.decodeOctetString (5, "PR");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTOXRNG_t (MMarshalStrategy ms, TOXRNG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.PR, 5, "PR");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_tHelper.type(); 
	}
	public static byte [] toOctet (TOXRNG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTOXRNG_t (ms, val, "TOXRNG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TOXRNG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTOXRNG_t (ms, "TOXRNG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_t();
		value.CA = new String ();
		value.PR = new String ();
		return value; 
	} 
}
