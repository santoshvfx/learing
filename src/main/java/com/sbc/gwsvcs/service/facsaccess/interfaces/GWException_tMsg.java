package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GWException_tMsg implements MMarshalObject { 
	public GWException_t value;

	public GWException_tMsg () {
	}
	public GWException_tMsg (GWException_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGWException_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGWException_t (ms, tag); 
	}
	static public GWException_t decodeGWException_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GWException_t value = create();
		ms.startStruct (tag, false);
		value.ERR_CD = ms.decodeLong ("ERR_CD");
		value.ERR_TX = ms.decodeOctetString (1025, "ERR_TX");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeGWException_t (MMarshalStrategy ms, GWException_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ERR_CD, "ERR_CD");
		ms.encode (value.ERR_TX, 1025, "ERR_TX");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_tHelper.type(); 
	}
	public static byte [] toOctet (GWException_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeGWException_t (ms, val, "GWException_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static GWException_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeGWException_t (ms, "GWException_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t();
		value.ERR_TX = new String ();
		return value; 
	} 
}
