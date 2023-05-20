package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPRMK_tMsg implements MMarshalObject { 
	public EPRMK_t value;

	public EPRMK_tMsg () {
	}
	public EPRMK_tMsg (EPRMK_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEPRMK_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEPRMK_t (ms, tag); 
	}
	static public EPRMK_t decodeEPRMK_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EPRMK_t value = create();
		ms.startStruct (tag, false);
		value.RMK2 = ms.decodeOctetString (51, "RMK2");
		value.RMK3 = ms.decodeOctetString (51, "RMK3");
		value.RMK4 = ms.decodeOctetString (51, "RMK4");
		value.RMK5 = ms.decodeOctetString (51, "RMK5");
		value.RMK6 = ms.decodeOctetString (51, "RMK6");
		value.RMK7 = ms.decodeOctetString (51, "RMK7");
		value.RMK8 = ms.decodeOctetString (51, "RMK8");
		value.RMK9 = ms.decodeOctetString (51, "RMK9");
		value.RMK10 = ms.decodeOctetString (51, "RMK10");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEPRMK_t (MMarshalStrategy ms, EPRMK_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RMK2, 51, "RMK2");
		ms.encode (value.RMK3, 51, "RMK3");
		ms.encode (value.RMK4, 51, "RMK4");
		ms.encode (value.RMK5, 51, "RMK5");
		ms.encode (value.RMK6, 51, "RMK6");
		ms.encode (value.RMK7, 51, "RMK7");
		ms.encode (value.RMK8, 51, "RMK8");
		ms.encode (value.RMK9, 51, "RMK9");
		ms.encode (value.RMK10, 51, "RMK10");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_tHelper.type(); 
	}
	public static byte [] toOctet (EPRMK_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEPRMK_t (ms, val, "EPRMK_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EPRMK_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEPRMK_t (ms, "EPRMK_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_t();
		value.RMK2 = new String ();
		value.RMK3 = new String ();
		value.RMK4 = new String ();
		value.RMK5 = new String ();
		value.RMK6 = new String ();
		value.RMK7 = new String ();
		value.RMK8 = new String ();
		value.RMK9 = new String ();
		value.RMK10 = new String ();
		return value; 
	} 
}
