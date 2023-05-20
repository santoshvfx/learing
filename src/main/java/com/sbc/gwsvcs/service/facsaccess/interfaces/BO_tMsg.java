package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class BO_tMsg implements MMarshalObject { 
	public BO_t value;

	public BO_tMsg () {
	}
	public BO_tMsg (BO_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeBO_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeBO_t (ms, tag); 
	}
	static public BO_t decodeBO_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		BO_t value = create();
		ms.startStruct (tag, false);
		value.BORES = ms.decodeOctetString (6, "BORES");
		value.BOCAP = ms.decodeOctetString (6, "BOCAP");
		value.BOOFF = ms.decodeOctetString (10, "BOOFF");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeBO_t (MMarshalStrategy ms, BO_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.BORES, 6, "BORES");
		ms.encode (value.BOCAP, 6, "BOCAP");
		ms.encode (value.BOOFF, 10, "BOOFF");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.BO_tHelper.type(); 
	}
	public static byte [] toOctet (BO_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeBO_t (ms, val, "BO_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static BO_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeBO_t (ms, "BO_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t();
		value.BORES = new String ();
		value.BOCAP = new String ();
		value.BOOFF = new String ();
		return value; 
	} 
}
