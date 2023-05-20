package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CARNG_tMsg implements MMarshalObject { 
	public CARNG_t value;

	public CARNG_tMsg () {
	}
	public CARNG_tMsg (CARNG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCARNG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCARNG_t (ms, tag); 
	}
	static public CARNG_t decodeCARNG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CARNG_t value = create();
		ms.startStruct (tag, false);
		value.TYPE = ms.decodeOctetString (10, "TYPE");
		value.LPR = ms.decodeOctetString (5, "LPR");
		value.HPR = ms.decodeOctetString (5, "HPR");
		value.BZ = ms.decodeOctetString (2, "BZ");
		value.LTC = ms.decodeOctetString (5, "LTC");
		value.LTF = ms.decodeOctetString (5, "LTF");
		value.SDP = ms.decodeOctetString (2, "SDP");
		value.TSB = ms.decodeOctetString (2, "TSB");
		value.PGS = ms.decodeOctetString (5, "PGS");
		value.RTA = ms.decodeOctetString (3, "RTA");
		value.DLE = ms.decodeOctetString (2, "DLE");
		value.TSI = ms.decodeOctetString (2, "TSI");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCARNG_t (MMarshalStrategy ms, CARNG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TYPE, 10, "TYPE");
		ms.encode (value.LPR, 5, "LPR");
		ms.encode (value.HPR, 5, "HPR");
		ms.encode (value.BZ, 2, "BZ");
		ms.encode (value.LTC, 5, "LTC");
		ms.encode (value.LTF, 5, "LTF");
		ms.encode (value.SDP, 2, "SDP");
		ms.encode (value.TSB, 2, "TSB");
		ms.encode (value.PGS, 5, "PGS");
		ms.encode (value.RTA, 3, "RTA");
		ms.encode (value.DLE, 2, "DLE");
		ms.encode (value.TSI, 2, "TSI");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_tHelper.type(); 
	}
	public static byte [] toOctet (CARNG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCARNG_t (ms, val, "CARNG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CARNG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCARNG_t (ms, "CARNG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t();
		value.TYPE = new String ();
		value.LPR = new String ();
		value.HPR = new String ();
		value.BZ = new String ();
		value.LTC = new String ();
		value.LTF = new String ();
		value.SDP = new String ();
		value.TSB = new String ();
		value.PGS = new String ();
		value.RTA = new String ();
		value.DLE = new String ();
		value.TSI = new String ();
		return value; 
	} 
}
