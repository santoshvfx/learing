package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupSTMsg implements MMarshalObject { 
	public HostLookupST value;

	public HostLookupSTMsg () {
	}
	public HostLookupSTMsg (HostLookupST initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookupST (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookupST (ms, tag); 
	}
	static public HostLookupST decodeHostLookupST (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookupST value = create();
		ms.startStruct (tag, false);
		value.tn = ms.decodeOctetString (11, "tn");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookupST (MMarshalStrategy ms, HostLookupST value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.tn, 11, "tn");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupSTHelper.type(); 
	}
	public static byte [] toOctet (HostLookupST val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookupST (ms, val, "HostLookupST");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookupST fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookupST (ms, "HostLookupST"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST();
		value.tn = new String ();
		return value; 
	} 
}
