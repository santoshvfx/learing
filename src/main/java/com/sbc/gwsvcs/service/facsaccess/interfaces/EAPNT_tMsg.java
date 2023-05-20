package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EAPNT_tMsg implements MMarshalObject { 
	public EAPNT_t value;

	public EAPNT_tMsg () {
	}
	public EAPNT_tMsg (EAPNT_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEAPNT_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEAPNT_t (ms, tag); 
	}
	static public EAPNT_t decodeEAPNT_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EAPNT_t value = create();
		ms.startStruct (tag, false);
		value.NO = ms.decodeOctetString (14, "NO");
		value.ST = ms.decodeOctetString (51, "ST");
		value.UNTYP = ms.decodeOctetString (5, "UNTYP");
		value.UNIT = ms.decodeOctetString (11, "UNIT");
		value.ELEVTYP = ms.decodeOctetString (4, "ELEVTYP");
		value.FLR = ms.decodeOctetString (11, "FLR");
		value.STRUCTYP = ms.decodeOctetString (5, "STRUCTYP");
		value.BLDG = ms.decodeOctetString (11, "BLDG");
		value.NEWSTR = ms.decodeOctetString (2, "NEWSTR");
		value.COM = ms.decodeOctetString (33, "COM");
		value.STATE = ms.decodeOctetString (3, "STATE");
		value.STEA = ms.decodeOctetString (51, "STEA");
		value.LRST = ms.decodeOctetString (21, "LRST");
		value.LRMK = ms.decodeOctetString (51, "LRMK");
		value.WOL = ms.decodeOctetString (2, "WOL");
		value.DTEA = ms.decodeOctetString (51, "DTEA");
		value.CSWEX = ms.decodeOctetString (2, "CSWEX");
		value.TRM = ms.decodeOctetString (2, "TRM");
		value.BP = ms.decodeOctetString (5, "BP");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEAPNT_t (MMarshalStrategy ms, EAPNT_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.NO, 14, "NO");
		ms.encode (value.ST, 51, "ST");
		ms.encode (value.UNTYP, 5, "UNTYP");
		ms.encode (value.UNIT, 11, "UNIT");
		ms.encode (value.ELEVTYP, 4, "ELEVTYP");
		ms.encode (value.FLR, 11, "FLR");
		ms.encode (value.STRUCTYP, 5, "STRUCTYP");
		ms.encode (value.BLDG, 11, "BLDG");
		ms.encode (value.NEWSTR, 2, "NEWSTR");
		ms.encode (value.COM, 33, "COM");
		ms.encode (value.STATE, 3, "STATE");
		ms.encode (value.STEA, 51, "STEA");
		ms.encode (value.LRST, 21, "LRST");
		ms.encode (value.LRMK, 51, "LRMK");
		ms.encode (value.WOL, 2, "WOL");
		ms.encode (value.DTEA, 51, "DTEA");
		ms.encode (value.CSWEX, 2, "CSWEX");
		ms.encode (value.TRM, 2, "TRM");
		ms.encode (value.BP, 5, "BP");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_tHelper.type(); 
	}
	public static byte [] toOctet (EAPNT_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEAPNT_t (ms, val, "EAPNT_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EAPNT_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEAPNT_t (ms, "EAPNT_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_t();
		value.NO = new String ();
		value.ST = new String ();
		value.UNTYP = new String ();
		value.UNIT = new String ();
		value.ELEVTYP = new String ();
		value.FLR = new String ();
		value.STRUCTYP = new String ();
		value.BLDG = new String ();
		value.NEWSTR = new String ();
		value.COM = new String ();
		value.STATE = new String ();
		value.STEA = new String ();
		value.LRST = new String ();
		value.LRMK = new String ();
		value.WOL = new String ();
		value.DTEA = new String ();
		value.CSWEX = new String ();
		value.TRM = new String ();
		value.BP = new String ();
		return value; 
	} 
}
