package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ATTRNG_tMsg implements MMarshalObject { 
	public ATTRNG_t value;

	public ATTRNG_tMsg () {
	}
	public ATTRNG_tMsg (ATTRNG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeATTRNG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeATTRNG_t (ms, tag); 
	}
	static public ATTRNG_t decodeATTRNG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ATTRNG_t value = create();
		ms.startStruct (tag, false);
		value.CODE = ms.decodeOctetString (5, "CODE");
		value.CA = ms.decodeOctetString (11, "CA");
		value.LPR = ms.decodeOctetString (5, "LPR");
		value.HPR = ms.decodeOctetString (5, "HPR");
		value.BP = ms.decodeOctetString (5, "BP");
		value.RST = ms.decodeOctetString (21, "RST");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeATTRNG_t (MMarshalStrategy ms, ATTRNG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CODE, 5, "CODE");
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.LPR, 5, "LPR");
		ms.encode (value.HPR, 5, "HPR");
		ms.encode (value.BP, 5, "BP");
		ms.encode (value.RST, 21, "RST");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_tHelper.type(); 
	}
	public static byte [] toOctet (ATTRNG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeATTRNG_t (ms, val, "ATTRNG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ATTRNG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeATTRNG_t (ms, "ATTRNG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_t();
		value.CODE = new String ();
		value.CA = new String ();
		value.LPR = new String ();
		value.HPR = new String ();
		value.BP = new String ();
		value.RST = new String ();
		return value; 
	} 
}
