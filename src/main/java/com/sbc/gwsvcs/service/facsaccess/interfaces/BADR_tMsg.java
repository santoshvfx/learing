package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class BADR_tMsg implements MMarshalObject { 
	public BADR_t value;

	public BADR_tMsg () {
	}
	public BADR_tMsg (BADR_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeBADR_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeBADR_t (ms, tag); 
	}
	static public BADR_t decodeBADR_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		BADR_t value = create();
		ms.startStruct (tag, false);
		value.BAD = ms.decodeOctetString (14, "BAD");
		value.STR = ms.decodeOctetString (51, "STR");
		value.CNA = ms.decodeOctetString (33, "CNA");
		value.STN = ms.decodeOctetString (3, "STN");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeBADR_t (MMarshalStrategy ms, BADR_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.BAD, 14, "BAD");
		ms.encode (value.STR, 51, "STR");
		ms.encode (value.CNA, 33, "CNA");
		ms.encode (value.STN, 3, "STN");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tHelper.type(); 
	}
	public static byte [] toOctet (BADR_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeBADR_t (ms, val, "BADR_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static BADR_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeBADR_t (ms, "BADR_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t();
		value.BAD = new String ();
		value.STR = new String ();
		value.CNA = new String ();
		value.STN = new String ();
		return value; 
	} 
}
