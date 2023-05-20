package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupWCMsg implements MMarshalObject { 
	public HostLookupWC value;

	public HostLookupWCMsg () {
	}
	public HostLookupWCMsg (HostLookupWC initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookupWC (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookupWC (ms, tag); 
	}
	static public HostLookupWC decodeHostLookupWC (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookupWC value = create();
		ms.startStruct (tag, false);
		value.wc = ms.decodeOctetString (7, "wc");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookupWC (MMarshalStrategy ms, HostLookupWC value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.wc, 7, "wc");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWCHelper.type(); 
	}
	public static byte [] toOctet (HostLookupWC val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookupWC (ms, val, "HostLookupWC");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookupWC fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookupWC (ms, "HostLookupWC"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC();
		value.wc = new String ();
		return value; 
	} 
}
