package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class INQ_FASG_Section_tMsg implements MMarshalObject { 
	public INQ_FASG_Section_t value;

	public INQ_FASG_Section_tMsg () {
	}
	public INQ_FASG_Section_tMsg (INQ_FASG_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeINQ_FASG_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeINQ_FASG_Section_t (ms, tag); 
	}
	static public INQ_FASG_Section_t decodeINQ_FASG_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		INQ_FASG_Section_t value = create();
		ms.startStruct (tag, false);
		value.EMP = ms.decodeOctetString (8, "EMP");
		value.BAD = ms.decodeOctetString (14, "BAD");
		value.STR = ms.decodeOctetString (51, "STR");
		value.UID = ms.decodeOctetString (11, "UID");
		value.UTYP = ms.decodeOctetString (5, "UTYP");
		value.EID = ms.decodeOctetString (11, "EID");
		value.ETYP = ms.decodeOctetString (5, "ETYP");
		value.SID = ms.decodeOctetString (11, "SID");
		value.STYP = ms.decodeOctetString (5, "STYP");
		value.CNA = ms.decodeOctetString (33, "CNA");
		value.STN = ms.decodeOctetString (3, "STN");
		value.CKID = ms.decodeOctetString (42, "CKID");
		value.TID = ms.decodeOctetString (28, "TID");
		value.CA = ms.decodeOctetString (11, "CA");
		value.PR = ms.decodeOctetString (5, "PR");
		value.PND = ms.decodeOctetString (2, "PND");
		value.WW = ms.decodeOctetString (13, "WW");
		value.POS = ms.decodeOctetString (6, "POS");
		value.JACK = ms.decodeOctetString (6, "JACK");
		value.TEA = ms.decodeOctetString (51, "TEA");
		value.ASGBP = ms.decodeOctetString (5, "ASGBP");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeINQ_FASG_Section_t (MMarshalStrategy ms, INQ_FASG_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.EMP, 8, "EMP");
		ms.encode (value.BAD, 14, "BAD");
		ms.encode (value.STR, 51, "STR");
		ms.encode (value.UID, 11, "UID");
		ms.encode (value.UTYP, 5, "UTYP");
		ms.encode (value.EID, 11, "EID");
		ms.encode (value.ETYP, 5, "ETYP");
		ms.encode (value.SID, 11, "SID");
		ms.encode (value.STYP, 5, "STYP");
		ms.encode (value.CNA, 33, "CNA");
		ms.encode (value.STN, 3, "STN");
		ms.encode (value.CKID, 42, "CKID");
		ms.encode (value.TID, 28, "TID");
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.PR, 5, "PR");
		ms.encode (value.PND, 2, "PND");
		ms.encode (value.WW, 13, "WW");
		ms.encode (value.POS, 6, "POS");
		ms.encode (value.JACK, 6, "JACK");
		ms.encode (value.TEA, 51, "TEA");
		ms.encode (value.ASGBP, 5, "ASGBP");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_tHelper.type(); 
	}
	public static byte [] toOctet (INQ_FASG_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeINQ_FASG_Section_t (ms, val, "INQ_FASG_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static INQ_FASG_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeINQ_FASG_Section_t (ms, "INQ_FASG_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_t();
		value.EMP = new String ();
		value.BAD = new String ();
		value.STR = new String ();
		value.UID = new String ();
		value.UTYP = new String ();
		value.EID = new String ();
		value.ETYP = new String ();
		value.SID = new String ();
		value.STYP = new String ();
		value.CNA = new String ();
		value.STN = new String ();
		value.CKID = new String ();
		value.TID = new String ();
		value.CA = new String ();
		value.PR = new String ();
		value.PND = new String ();
		value.WW = new String ();
		value.POS = new String ();
		value.JACK = new String ();
		value.TEA = new String ();
		value.ASGBP = new String ();
		return value; 
	} 
}
