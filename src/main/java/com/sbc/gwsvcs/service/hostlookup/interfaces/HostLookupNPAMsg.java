package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupNPAMsg implements MMarshalObject { 
	public HostLookupNPA value;

	public HostLookupNPAMsg () {
	}
	public HostLookupNPAMsg (HostLookupNPA initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookupNPA (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookupNPA (ms, tag); 
	}
	static public HostLookupNPA decodeHostLookupNPA (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookupNPA value = create();
		ms.startStruct (tag, false);
		value.npa = ms.decodeOctetString (4, "npa");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookupNPA (MMarshalStrategy ms, HostLookupNPA value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.npa, 4, "npa");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupNPAHelper.type(); 
	}
	public static byte [] toOctet (HostLookupNPA val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookupNPA (ms, val, "HostLookupNPA");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookupNPA fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookupNPA (ms, "HostLookupNPA"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupNPA create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupNPA value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupNPA();
		value.npa = new String ();
		return value; 
	} 
}
