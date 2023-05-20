package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupTNMsg implements MMarshalObject { 
	public HostLookupTN value;

	public HostLookupTNMsg () {
	}
	public HostLookupTNMsg (HostLookupTN initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookupTN (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookupTN (ms, tag); 
	}
	static public HostLookupTN decodeHostLookupTN (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookupTN value = create();
		ms.startStruct (tag, false);
		value.tn = ms.decodeOctetString (11, "tn");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookupTN (MMarshalStrategy ms, HostLookupTN value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.tn, 11, "tn");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTNHelper.type(); 
	}
	public static byte [] toOctet (HostLookupTN val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookupTN (ms, val, "HostLookupTN");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookupTN fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookupTN (ms, "HostLookupTN"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTN create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTN value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTN();
		value.tn = new String ();
		return value; 
	} 
}
