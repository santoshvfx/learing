package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class MobileCTNLookup_RMsg implements MMarshalObject { 
	public MobileCTNLookup_R value;

	public MobileCTNLookup_RMsg () {
	}
	public MobileCTNLookup_RMsg (MobileCTNLookup_R initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeMobileCTNLookup_R (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeMobileCTNLookup_R (ms, tag); 
	}
	static public MobileCTNLookup_R decodeMobileCTNLookup_R (MMarshalStrategy ms, String tag) throws MMarshalException { 
		MobileCTNLookup_R value = create();
		ms.startStruct (tag, false);
		value.tn = ms.decodeOctetString (11, "tn");
		value.property = ms.decodeOctetString (6, "property");
		value.OrigEvent = ms.decodeLong ("OrigEvent");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeMobileCTNLookup_R (MMarshalStrategy ms, MobileCTNLookup_R value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.tn, 11, "tn");
		ms.encode (value.property, 6, "property");
		ms.encode (value.OrigEvent, "OrigEvent");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_RHelper.type(); 
	}
	public static byte [] toOctet (MobileCTNLookup_R val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeMobileCTNLookup_R (ms, val, "MobileCTNLookup_R");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static MobileCTNLookup_R fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeMobileCTNLookup_R (ms, "MobileCTNLookup_R"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R value = new com.sbc.gwsvcs.service.hostlookup.interfaces.MobileCTNLookup_R();
		value.tn = new String ();
		value.property = new String ();
		return value; 
	} 
}
