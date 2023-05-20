package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupST_RMsg implements MMarshalObject { 
	public HostLookupST_R value;

	public HostLookupST_RMsg () {
	}
	public HostLookupST_RMsg (HostLookupST_R initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookupST_R (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookupST_R (ms, tag); 
	}
	static public HostLookupST_R decodeHostLookupST_R (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookupST_R value = create();
		ms.startStruct (tag, false);
		value.state = ms.decodeOctetString (3, "state");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookupST_R (MMarshalStrategy ms, HostLookupST_R value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.state, 3, "state");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST_RHelper.type(); 
	}
	public static byte [] toOctet (HostLookupST_R val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookupST_R (ms, val, "HostLookupST_R");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookupST_R fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookupST_R (ms, "HostLookupST_R"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST_R create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST_R value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST_R();
		value.state = new String ();
		return value; 
	} 
}
