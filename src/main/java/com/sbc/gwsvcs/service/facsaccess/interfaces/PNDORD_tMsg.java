package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PNDORD_tMsg implements MMarshalObject { 
	public PNDORD_t value;

	public PNDORD_tMsg () {
	}
	public PNDORD_tMsg (PNDORD_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePNDORD_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePNDORD_t (ms, tag); 
	}
	static public PNDORD_t decodePNDORD_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PNDORD_t value = create();
		ms.startStruct (tag, false);
		value.SOID = ms.decodeOctetString (3, "SOID");
		value.ORD = ms.decodeOctetString (15, "ORD");
		value.DD = ms.decodeOctetString (9, "DD");
		value.PNDLPS = ms.decodeOctetString (3, "PNDLPS");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodePNDORD_t (MMarshalStrategy ms, PNDORD_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SOID, 3, "SOID");
		ms.encode (value.ORD, 15, "ORD");
		ms.encode (value.DD, 9, "DD");
		ms.encode (value.PNDLPS, 3, "PNDLPS");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_tHelper.type(); 
	}
	public static byte [] toOctet (PNDORD_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePNDORD_t (ms, val, "PNDORD_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static PNDORD_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePNDORD_t (ms, "PNDORD_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.PNDORD_t();
		value.SOID = new String ();
		value.ORD = new String ();
		value.DD = new String ();
		value.PNDLPS = new String ();
		return value; 
	} 
}
