package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class OU_tMsg implements MMarshalObject { 
	public OU_t value;

	public OU_tMsg () {
	}
	public OU_tMsg (OU_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeOU_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeOU_t (ms, tag); 
	}
	static public OU_t decodeOU_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		OU_t value = create();
		ms.startStruct (tag, false);
		value.OUN = ms.decodeOctetString (3, "OUN");
		value.OUBP = ms.decodeOctetString (5, "OUBP");
		value.GRP1 = ms.decodeOctetString (6, "GRP1");
		value.GRP2 = ms.decodeOctetString (6, "GRP2");
		value.GRP3 = ms.decodeOctetString (6, "GRP3");
		value.GRP4 = ms.decodeOctetString (6, "GRP4");
		value.CAP1 = ms.decodeOctetString (5, "CAP1");
		value.CAP2 = ms.decodeOctetString (5, "CAP2");
		value.CAP3 = ms.decodeOctetString (5, "CAP3");
		value.CAP4 = ms.decodeOctetString (5, "CAP4");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeOU_t (MMarshalStrategy ms, OU_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.OUN, 3, "OUN");
		ms.encode (value.OUBP, 5, "OUBP");
		ms.encode (value.GRP1, 6, "GRP1");
		ms.encode (value.GRP2, 6, "GRP2");
		ms.encode (value.GRP3, 6, "GRP3");
		ms.encode (value.GRP4, 6, "GRP4");
		ms.encode (value.CAP1, 5, "CAP1");
		ms.encode (value.CAP2, 5, "CAP2");
		ms.encode (value.CAP3, 5, "CAP3");
		ms.encode (value.CAP4, 5, "CAP4");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.OU_tHelper.type(); 
	}
	public static byte [] toOctet (OU_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeOU_t (ms, val, "OU_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static OU_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeOU_t (ms, "OU_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.OU_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.OU_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.OU_t();
		value.OUN = new String ();
		value.OUBP = new String ();
		value.GRP1 = new String ();
		value.GRP2 = new String ();
		value.GRP3 = new String ();
		value.GRP4 = new String ();
		value.CAP1 = new String ();
		value.CAP2 = new String ();
		value.CAP3 = new String ();
		value.CAP4 = new String ();
		return value; 
	} 
}
