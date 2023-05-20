package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SOP_tMsg implements MMarshalObject { 
	public SOP_t value;

	public SOP_tMsg () {
	}
	public SOP_tMsg (SOP_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSOP_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSOP_t (ms, tag); 
	}
	static public SOP_t decodeSOP_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SOP_t value = create();
		ms.startStruct (tag, false);
		value.GA = ms.decodeOctetString (8, "GA");
		value.LGTH = ms.decodeOctetString (10, "LGTH");
		value.UBA = ms.decodeOctetString (2, "UBA");
		value.CAPAC = ms.decodeOctetString (6, "CAPAC");
		value.BTOFF = ms.decodeOctetString (10, "BTOFF");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSOP_t (MMarshalStrategy ms, SOP_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.GA, 8, "GA");
		ms.encode (value.LGTH, 10, "LGTH");
		ms.encode (value.UBA, 2, "UBA");
		ms.encode (value.CAPAC, 6, "CAPAC");
		ms.encode (value.BTOFF, 10, "BTOFF");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_tHelper.type(); 
	}
	public static byte [] toOctet (SOP_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSOP_t (ms, val, "SOP_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SOP_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSOP_t (ms, "SOP_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t();
		value.GA = new String ();
		value.LGTH = new String ();
		value.UBA = new String ();
		value.CAPAC = new String ();
		value.BTOFF = new String ();
		return value; 
	} 
}
