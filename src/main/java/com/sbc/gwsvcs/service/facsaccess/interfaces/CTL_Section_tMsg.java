package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CTL_Section_tMsg implements MMarshalObject { 
	public CTL_Section_t value;

	public CTL_Section_tMsg () {
	}
	public CTL_Section_tMsg (CTL_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCTL_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCTL_Section_t (ms, tag); 
	}
	static public CTL_Section_t decodeCTL_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CTL_Section_t value = create();
		ms.startStruct (tag, false);
		value.TT = ms.decodeOctetString (9, "TT");
		value.DT = ms.decodeOctetString (7, "DT");
		value.TM = ms.decodeOctetString (7, "TM");
		value.SNDR = ms.decodeOctetString (9, "SNDR");
		value.DEST = ms.decodeOctetString (9, "DEST");
		value.WO = ms.decodeOctetString (13, "WO");
		value.MN = ms.decodeOctetString (13, "MN");
		value.EMP = ms.decodeOctetString (8, "EMP");
		value.WC = ms.decodeOctetString (9, "WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCTL_Section_t (MMarshalStrategy ms, CTL_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TT, 9, "TT");
		ms.encode (value.DT, 7, "DT");
		ms.encode (value.TM, 7, "TM");
		ms.encode (value.SNDR, 9, "SNDR");
		ms.encode (value.DEST, 9, "DEST");
		ms.encode (value.WO, 13, "WO");
		ms.encode (value.MN, 13, "MN");
		ms.encode (value.EMP, 8, "EMP");
		ms.encode (value.WC, 9, "WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tHelper.type(); 
	}
	public static byte [] toOctet (CTL_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCTL_Section_t (ms, val, "CTL_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CTL_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCTL_Section_t (ms, "CTL_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t();
		value.TT = new String ();
		value.DT = new String ();
		value.TM = new String ();
		value.SNDR = new String ();
		value.DEST = new String ();
		value.WO = new String ();
		value.MN = new String ();
		value.EMP = new String ();
		value.WC = new String ();
		return value; 
	} 
}
