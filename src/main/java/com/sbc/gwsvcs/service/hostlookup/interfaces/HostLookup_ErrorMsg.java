package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookup_ErrorMsg implements MMarshalObject { 
	public HostLookup_Error value;

	public HostLookup_ErrorMsg () {
	}
	public HostLookup_ErrorMsg (HostLookup_Error initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookup_Error (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookup_Error (ms, tag); 
	}
	static public HostLookup_Error decodeHostLookup_Error (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookup_Error value = create();
		ms.startStruct (tag, false);
		value.tn = ms.decodeOctetString (11, "tn");
		value.ErrorMsg = ms.decodeOctetString (100, "ErrorMsg");
		value.OrigEvent = ms.decodeLong ("OrigEvent");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookup_Error (MMarshalStrategy ms, HostLookup_Error value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.tn, 11, "tn");
		ms.encode (value.ErrorMsg, 100, "ErrorMsg");
		ms.encode (value.OrigEvent, "OrigEvent");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_ErrorHelper.type(); 
	}
	public static byte [] toOctet (HostLookup_Error val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookup_Error (ms, val, "HostLookup_Error");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookup_Error fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookup_Error (ms, "HostLookup_Error"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Error create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Error value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Error();
		value.tn = new String ();
		value.ErrorMsg = new String ();
		return value; 
	} 
}
