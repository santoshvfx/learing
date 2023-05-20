package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class C1_Section_tMsg implements MMarshalObject { 
	public C1_Section_t value;

	public C1_Section_tMsg () {
	}
	public C1_Section_tMsg (C1_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeC1_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeC1_Section_t (ms, tag); 
	}
	static public C1_Section_t decodeC1_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		C1_Section_t value = create();
		ms.startStruct (tag, false);
		value.FT = ms.decodeOctetString (4, "FT");
		value.TT = ms.decodeOctetString (4, "TT");
		value.OT = ms.decodeOctetString (2, "OT");
		value.ON = ms.decodeOctetString (12, "ON");
		value.CS = ms.decodeOctetString (2, "CS");
		value.VN = ms.decodeOctetString (4, "VN");
		value.WC = ms.decodeOctetString (7, "WC");
		value.OR = ms.decodeOctetString (9, "OR");
		value.DT = ms.decodeOctetString (9, "DT");
		value.PR = ms.decodeOctetString (3, "PR");
		value.DD = ms.decodeOctetString (7, "DD");
		value.DC = ms.decodeOctetString (2, "DC");
		value.SU = ms.decodeOctetString (2, "SU");
		value.ST = ms.decodeOctetString (2, "ST");
		value.MA = ms.decodeOctetString (2, "MA");
		value.MF = ms.decodeOctetString (2, "MF");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeC1_Section_t (MMarshalStrategy ms, C1_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.FT, 4, "FT");
		ms.encode (value.TT, 4, "TT");
		ms.encode (value.OT, 2, "OT");
		ms.encode (value.ON, 12, "ON");
		ms.encode (value.CS, 2, "CS");
		ms.encode (value.VN, 4, "VN");
		ms.encode (value.WC, 7, "WC");
		ms.encode (value.OR, 9, "OR");
		ms.encode (value.DT, 9, "DT");
		ms.encode (value.PR, 3, "PR");
		ms.encode (value.DD, 7, "DD");
		ms.encode (value.DC, 2, "DC");
		ms.encode (value.SU, 2, "SU");
		ms.encode (value.ST, 2, "ST");
		ms.encode (value.MA, 2, "MA");
		ms.encode (value.MF, 2, "MF");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tHelper.type(); 
	}
	public static byte [] toOctet (C1_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeC1_Section_t (ms, val, "C1_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static C1_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeC1_Section_t (ms, "C1_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t();
		value.FT = new String ();
		value.TT = new String ();
		value.OT = new String ();
		value.ON = new String ();
		value.CS = new String ();
		value.VN = new String ();
		value.WC = new String ();
		value.OR = new String ();
		value.DT = new String ();
		value.PR = new String ();
		value.DD = new String ();
		value.DC = new String ();
		value.SU = new String ();
		value.ST = new String ();
		value.MA = new String ();
		value.MF = new String ();
		return value; 
	} 
}
