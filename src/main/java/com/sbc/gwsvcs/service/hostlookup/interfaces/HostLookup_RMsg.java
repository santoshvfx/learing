package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookup_RMsg implements MMarshalObject { 
	public HostLookup_R value;

	public HostLookup_RMsg () {
	}
	public HostLookup_RMsg (HostLookup_R initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookup_R (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookup_R (ms, tag); 
	}
	static public HostLookup_R decodeHostLookup_R (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookup_R value = create();
		ms.startStruct (tag, false);
		value.wc = ms.decodeOctetString (7, "wc");
		value.facs = ms.decodeOctetString (4, "facs");
		value.lmos = ms.decodeOctetString (7, "lmos");
		value.cosmos = ms.decodeOctetString (6, "cosmos");
		value.premis = ms.decodeOctetString (4, "premis");
		value.saga = ms.decodeOctetString (4, "saga");
		value.sord = ms.decodeOctetString (6, "sord");
		value.tirks = ms.decodeOctetString (6, "tirks");
		value.nsdb = ms.decodeOctetString (6, "nsdb");
		value.boss = ms.decodeOctetString (6, "boss");
		value.march = ms.decodeOctetString (6, "march");
		value.swtch = ms.decodeOctetString (6, "swtch");
		value.swtch_entity = ms.decodeOctetString (2, "swtch_entity");
		value.wfado = ms.decodeOctetString (6, "wfado");
		value.wc_alpha = ms.decodeOctetString (4, "wc_alpha");
		value.div_code = ms.decodeOctetString (2, "div_code");
		value.pics = ms.decodeOctetString (10, "pics");
		value.pacbell_mi = ms.decodeOctetString (10, "pacbell_mi");
		value.split = ms.decodeOctetString (10, "split");
		value.location = ms.decodeOctetString (10, "location");
		value.rfs_alpha = ms.decodeOctetString (10, "rfs_alpha");
		value.OrigEvent = ms.decodeLong ("OrigEvent");
		value.ErrorMsg = ms.decodeOctetString (100, "ErrorMsg");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookup_R (MMarshalStrategy ms, HostLookup_R value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.wc, 7, "wc");
		ms.encode (value.facs, 4, "facs");
		ms.encode (value.lmos, 7, "lmos");
		ms.encode (value.cosmos, 6, "cosmos");
		ms.encode (value.premis, 4, "premis");
		ms.encode (value.saga, 4, "saga");
		ms.encode (value.sord, 6, "sord");
		ms.encode (value.tirks, 6, "tirks");
		ms.encode (value.nsdb, 6, "nsdb");
		ms.encode (value.boss, 6, "boss");
		ms.encode (value.march, 6, "march");
		ms.encode (value.swtch, 6, "swtch");
		ms.encode (value.swtch_entity, 2, "swtch_entity");
		ms.encode (value.wfado, 6, "wfado");
		ms.encode (value.wc_alpha, 4, "wc_alpha");
		ms.encode (value.div_code, 2, "div_code");
		ms.encode (value.pics, 10, "pics");
		ms.encode (value.pacbell_mi, 10, "pacbell_mi");
		ms.encode (value.split, 10, "split");
		ms.encode (value.location, 10, "location");
		ms.encode (value.rfs_alpha, 10, "rfs_alpha");
		ms.encode (value.OrigEvent, "OrigEvent");
		ms.encode (value.ErrorMsg, 100, "ErrorMsg");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_RHelper.type(); 
	}
	public static byte [] toOctet (HostLookup_R val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookup_R (ms, val, "HostLookup_R");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookup_R fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookup_R (ms, "HostLookup_R"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_R create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_R value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_R();
		value.wc = new String ();
		value.facs = new String ();
		value.lmos = new String ();
		value.cosmos = new String ();
		value.premis = new String ();
		value.saga = new String ();
		value.sord = new String ();
		value.tirks = new String ();
		value.nsdb = new String ();
		value.boss = new String ();
		value.march = new String ();
		value.swtch = new String ();
		value.swtch_entity = new String ();
		value.wfado = new String ();
		value.wc_alpha = new String ();
		value.div_code = new String ();
		value.pics = new String ();
		value.pacbell_mi = new String ();
		value.split = new String ();
		value.location = new String ();
		value.rfs_alpha = new String ();
		value.ErrorMsg = new String ();
		return value; 
	} 
}
