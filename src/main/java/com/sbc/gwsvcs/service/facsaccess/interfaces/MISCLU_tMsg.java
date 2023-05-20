package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class MISCLU_tMsg implements MMarshalObject { 
	public MISCLU_t value;

	public MISCLU_tMsg () {
	}
	public MISCLU_tMsg (MISCLU_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeMISCLU_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeMISCLU_t (ms, tag); 
	}
	static public MISCLU_t decodeMISCLU_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		MISCLU_t value = create();
		ms.startStruct (tag, false);
		value.CANSO = ms.decodeOctetString (24, "CANSO");
		value.CANLI = ms.decodeOctetString (26, "CANLI");
		value.LURSV = ms.decodeOctetString (36, "LURSV");
		value.PNDELU = ms.decodeOctetString (13, "PNDELU");
		value.RULE = ms.decodeOctetString (34, "RULE");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeMISCLU_t (MMarshalStrategy ms, MISCLU_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CANSO, 24, "CANSO");
		ms.encode (value.CANLI, 26, "CANLI");
		ms.encode (value.LURSV, 36, "LURSV");
		ms.encode (value.PNDELU, 13, "PNDELU");
		ms.encode (value.RULE, 34, "RULE");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_tHelper.type(); 
	}
	public static byte [] toOctet (MISCLU_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeMISCLU_t (ms, val, "MISCLU_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static MISCLU_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeMISCLU_t (ms, "MISCLU_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t();
		value.CANSO = new String ();
		value.CANLI = new String ();
		value.LURSV = new String ();
		value.PNDELU = new String ();
		value.RULE = new String ();
		return value; 
	} 
}
