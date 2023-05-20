package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostLookup_Nsdbt_NondesignMsg implements MMarshalObject { 
	public HostLookup_Nsdbt_Nondesign value;

	public HostLookup_Nsdbt_NondesignMsg () {
	}
	public HostLookup_Nsdbt_NondesignMsg (HostLookup_Nsdbt_Nondesign initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostLookup_Nsdbt_Nondesign (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHostLookup_Nsdbt_Nondesign (ms, tag); 
	}
	static public HostLookup_Nsdbt_Nondesign decodeHostLookup_Nsdbt_Nondesign (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HostLookup_Nsdbt_Nondesign value = create();
		ms.startStruct (tag, false);
		value.npa = ms.decodeOctetString (4, "npa");
		value.pri_ims = ms.decodeOctetString (10, "pri_ims");
		value.sec_ims = ms.decodeOctetString (10, "sec_ims");
		value.conv_ind = ms.decodeOctetString (2, "conv_ind");
		value.conv_dt = ms.decodeOctetString (10, "conv_dt");
		value.temp1 = ms.decodeOctetString (10, "temp1");
		value.temp2 = ms.decodeOctetString (10, "temp2");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHostLookup_Nsdbt_Nondesign (MMarshalStrategy ms, HostLookup_Nsdbt_Nondesign value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.npa, 4, "npa");
		ms.encode (value.pri_ims, 10, "pri_ims");
		ms.encode (value.sec_ims, 10, "sec_ims");
		ms.encode (value.conv_ind, 2, "conv_ind");
		ms.encode (value.conv_dt, 10, "conv_dt");
		ms.encode (value.temp1, 10, "temp1");
		ms.encode (value.temp2, 10, "temp2");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Nsdbt_NondesignHelper.type(); 
	}
	public static byte [] toOctet (HostLookup_Nsdbt_Nondesign val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostLookup_Nsdbt_Nondesign (ms, val, "HostLookup_Nsdbt_Nondesign");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static HostLookup_Nsdbt_Nondesign fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHostLookup_Nsdbt_Nondesign (ms, "HostLookup_Nsdbt_Nondesign"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Nsdbt_Nondesign create () { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Nsdbt_Nondesign value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Nsdbt_Nondesign();
		value.npa = new String ();
		value.pri_ims = new String ();
		value.sec_ims = new String ();
		value.conv_ind = new String ();
		value.conv_dt = new String ();
		value.temp1 = new String ();
		value.temp2 = new String ();
		return value; 
	} 
}
