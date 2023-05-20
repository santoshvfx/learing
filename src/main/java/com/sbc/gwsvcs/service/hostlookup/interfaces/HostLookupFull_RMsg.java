package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookupFull_RMsg implements MMarshalObject { 
	public HostLookupFull_R value;

	public HostLookupFull_RMsg () {
	}
	public HostLookupFull_RMsg (HostLookupFull_R initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookupFull_R (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookupFull_R (ms, tag); 
	}
	static public HostLookupFull_R decodeHostLookupFull_R (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookupFull_R value = create();
		ms.startStruct (tag, false);
		value.wc = ms.decodeOctetString (10, "wc");
		value.facs = ms.decodeOctetString (10, "facs");
		value.lmos = ms.decodeOctetString (10, "lmos");
		value.cosmos = ms.decodeOctetString (10, "cosmos");
		value.premis = ms.decodeOctetString (10, "premis");
		value.saga = ms.decodeOctetString (10, "saga");
		value.sord = ms.decodeOctetString (10, "sord");
		value.tirks = ms.decodeOctetString (10, "tirks");
		value.nsdb = ms.decodeOctetString (10, "nsdb");
		value.boss = ms.decodeOctetString (10, "boss");
		value.march = ms.decodeOctetString (10, "march");
		value.swtch = ms.decodeOctetString (10, "swtch");
		value.swtch_entity = ms.decodeOctetString (10, "swtch_entity");
		value.wfado = ms.decodeOctetString (10, "wfado");
		value.wc_alpha = ms.decodeOctetString (10, "wc_alpha");
		value.div_code = ms.decodeOctetString (10, "div_code");
		value.pics = ms.decodeOctetString (10, "pics");
		value.pacbell_mi = ms.decodeOctetString (10, "pacbell_mi");
		value.property = ms.decodeOctetString (10, "property");
		value.split = ms.decodeOctetString (10, "split");
		value.lmos_pacbell = ms.decodeOctetString (10, "lmos_pacbell");
		value.lmos_snet = ms.decodeOctetString (10, "lmos_snet");
		value.acis = ms.decodeOctetString (10, "acis");
		value.location = ms.decodeOctetString (10, "location");
		value.temp1 = ms.decodeOctetString (10, "temp1");
		value.affiliate_ind = ms.decodeShort ("affiliate_ind");
		value.boss_2 = ms.decodeOctetString (10, "boss_2");
		value.boss_3 = ms.decodeOctetString (10, "boss_3");
		value.boss_4 = ms.decodeOctetString (10, "boss_4");
		value.multiple_boss_region_ind = ms.decodeShort ("multiple_boss_region_ind");
		value.rcrms = ms.decodeOctetString (10, "rcrms");
		value.dial = ms.decodeOctetString (10, "dial");
		value.rfs_alpha = ms.decodeOctetString (10, "rfs_alpha");
		value.temp3 = ms.decodeOctetString (10, "temp3");
		value.temp4 = ms.decodeOctetString (10, "temp4");
		value.nxx_split_ind = ms.decodeOctetString (2, "nxx_split_ind");
		value.temp5 = ms.decodeOctetString (8, "temp5");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookupFull_R (MMarshalStrategy ms, HostLookupFull_R value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.wc, 10, "wc");
		ms.encode (value.facs, 10, "facs");
		ms.encode (value.lmos, 10, "lmos");
		ms.encode (value.cosmos, 10, "cosmos");
		ms.encode (value.premis, 10, "premis");
		ms.encode (value.saga, 10, "saga");
		ms.encode (value.sord, 10, "sord");
		ms.encode (value.tirks, 10, "tirks");
		ms.encode (value.nsdb, 10, "nsdb");
		ms.encode (value.boss, 10, "boss");
		ms.encode (value.march, 10, "march");
		ms.encode (value.swtch, 10, "swtch");
		ms.encode (value.swtch_entity, 10, "swtch_entity");
		ms.encode (value.wfado, 10, "wfado");
		ms.encode (value.wc_alpha, 10, "wc_alpha");
		ms.encode (value.div_code, 10, "div_code");
		ms.encode (value.pics, 10, "pics");
		ms.encode (value.pacbell_mi, 10, "pacbell_mi");
		ms.encode (value.property, 10, "property");
		ms.encode (value.split, 10, "split");
		ms.encode (value.lmos_pacbell, 10, "lmos_pacbell");
		ms.encode (value.lmos_snet, 10, "lmos_snet");
		ms.encode (value.acis, 10, "acis");
		ms.encode (value.location, 10, "location");
		ms.encode (value.temp1, 10, "temp1");
		ms.encode (value.affiliate_ind, "affiliate_ind");
		ms.encode (value.boss_2, 10, "boss_2");
		ms.encode (value.boss_3, 10, "boss_3");
		ms.encode (value.boss_4, 10, "boss_4");
		ms.encode (value.multiple_boss_region_ind, "multiple_boss_region_ind");
		ms.encode (value.rcrms, 10, "rcrms");
		ms.encode (value.dial, 10, "dial");
		ms.encode (value.rfs_alpha, 10, "rfs_alpha");
		ms.encode (value.temp3, 10, "temp3");
		ms.encode (value.temp4, 10, "temp4");
		ms.encode (value.nxx_split_ind, 2, "nxx_split_ind");
		ms.encode (value.temp5, 8, "temp5");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_RHelper.type(); 
	}
	public static byte [] toOctet (HostLookupFull_R val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookupFull_R (ms, val, "HostLookupFull_R");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookupFull_R fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookupFull_R (ms, "HostLookupFull_R"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R();
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
		value.property = new String ();
		value.split = new String ();
		value.lmos_pacbell = new String ();
		value.lmos_snet = new String ();
		value.acis = new String ();
		value.location = new String ();
		value.temp1 = new String ();
		value.boss_2 = new String ();
		value.boss_3 = new String ();
		value.boss_4 = new String ();
		value.rcrms = new String ();
		value.dial = new String ();
		value.rfs_alpha = new String ();
		value.temp3 = new String ();
		value.temp4 = new String ();
		value.nxx_split_ind = new String ();
		value.temp5 = new String ();
		return value; 
	} 
}
