package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RESP_tMsg implements MMarshalObject { 
	public RESP_t value;

	public RESP_tMsg () {
	}
	public RESP_tMsg (RESP_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRESP_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRESP_t (ms, tag); 
	}
	static public RESP_t decodeRESP_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RESP_t value = create();
		ms.startStruct (tag, false);
		value.STAT = ms.decodeOctetString (8, "STAT");
		value.ETYP = ms.decodeOctetString (9, "ETYP");
		value.ERRMSG = ms.decodeOctetString (121, "ERRMSG");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeRESP_t (MMarshalStrategy ms, RESP_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.STAT, 8, "STAT");
		ms.encode (value.ETYP, 9, "ETYP");
		ms.encode (value.ERRMSG, 121, "ERRMSG");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tHelper.type(); 
	}
	public static byte [] toOctet (RESP_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeRESP_t (ms, val, "RESP_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static RESP_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeRESP_t (ms, "RESP_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t();
		value.STAT = new String ();
		value.ETYP = new String ();
		value.ERRMSG = new String ();
		return value; 
	} 
}
