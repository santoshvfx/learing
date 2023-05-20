package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupDIVMsg implements MMarshalObject { 
	public HostLookupDIV value;

	public HostLookupDIVMsg () {
	}
	public HostLookupDIVMsg (HostLookupDIV initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookupDIV (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookupDIV (ms, tag); 
	}
	static public HostLookupDIV decodeHostLookupDIV (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookupDIV value = create();
		ms.startStruct (tag, false);
		value.div = ms.decodeOctetString (2, "div");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookupDIV (MMarshalStrategy ms, HostLookupDIV value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.div, 2, "div");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupDIVHelper.type(); 
	}
	public static byte [] toOctet (HostLookupDIV val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookupDIV (ms, val, "HostLookupDIV");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookupDIV fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookupDIV (ms, "HostLookupDIV"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupDIV create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupDIV value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupDIV();
		value.div = new String ();
		return value; 
	} 
}
