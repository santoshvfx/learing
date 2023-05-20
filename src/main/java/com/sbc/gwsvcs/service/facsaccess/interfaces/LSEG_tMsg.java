package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class LSEG_tMsg implements MMarshalObject { 
	public LSEG_t value;

	public LSEG_tMsg () {
	}
	public LSEG_tMsg (LSEG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLSEG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLSEG_t (ms, tag); 
	}
	static public LSEG_t decodeLSEG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		LSEG_t value = create();
		ms.startStruct (tag, false);
		value.CA = ms.decodeOctetString (11, "CA");
		value.PR = ms.decodeOctetString (5, "PR");
		value.PCOM = ms.decodeOctetString (2, "PCOM");
		value.PRMK = ms.decodeOctetString (51, "PRMK");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeLSEG_t (MMarshalStrategy ms, LSEG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.PR, 5, "PR");
		ms.encode (value.PCOM, 2, "PCOM");
		ms.encode (value.PRMK, 51, "PRMK");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_tHelper.type(); 
	}
	public static byte [] toOctet (LSEG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeLSEG_t (ms, val, "LSEG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static LSEG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeLSEG_t (ms, "LSEG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_t();
		value.CA = new String ();
		value.PR = new String ();
		value.PCOM = new String ();
		value.PRMK = new String ();
		return value; 
	} 
}
