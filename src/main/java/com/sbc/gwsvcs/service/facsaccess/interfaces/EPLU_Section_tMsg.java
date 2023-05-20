package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPLU_Section_tMsg implements MMarshalObject { 
	public EPLU_Section_t value;

	public EPLU_Section_tMsg () {
	}
	public EPLU_Section_tMsg (EPLU_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEPLU_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEPLU_Section_t (ms, tag); 
	}
	static public EPLU_Section_t decodeEPLU_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EPLU_Section_t value = create();
		ms.startStruct (tag, false);
		value.PRT = ms.decodeOctetString (5, "PRT");
		value.STEP = ms.decodeOctetString (8, "STEP");
		value.BLTYPE = ms.decodeOctetString (5, "BLTYPE");
		value.DREC = ms.decodeOctetString (2, "DREC");
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
		value.CSW = ms.decodeOctetString (5, "CSW");
		value.STEA = ms.decodeOctetString (51, "STEA");
		value.LRST = ms.decodeOctetString (21, "LRST");
		value.LRMK = ms.decodeOctetString (51, "LRMK");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEPLU_Section_t (MMarshalStrategy ms, EPLU_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PRT, 5, "PRT");
		ms.encode (value.STEP, 8, "STEP");
		ms.encode (value.BLTYPE, 5, "BLTYPE");
		ms.encode (value.DREC, 2, "DREC");
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
		ms.encode (value.CSW, 5, "CSW");
		ms.encode (value.STEA, 51, "STEA");
		ms.encode (value.LRST, 21, "LRST");
		ms.encode (value.LRMK, 51, "LRMK");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPLU_Section_tHelper.type(); 
	}
	public static byte [] toOctet (EPLU_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEPLU_Section_t (ms, val, "EPLU_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EPLU_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEPLU_Section_t (ms, "EPLU_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPLU_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPLU_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPLU_Section_t();
		value.PRT = new String ();
		value.STEP = new String ();
		value.BLTYPE = new String ();
		value.DREC = new String ();
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
		value.CSW = new String ();
		value.STEA = new String ();
		value.LRST = new String ();
		value.LRMK = new String ();
		return value; 
	} 
}
