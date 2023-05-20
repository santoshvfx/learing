package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPINVM_Section_tMsg implements MMarshalObject { 
	public EPINVM_Section_t value;

	public EPINVM_Section_tMsg () {
	}
	public EPINVM_Section_tMsg (EPINVM_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEPINVM_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEPINVM_Section_t (ms, tag); 
	}
	static public EPINVM_Section_t decodeEPINVM_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EPINVM_Section_t value = create();
		ms.startStruct (tag, false);
		value.PRT = ms.decodeOctetString (5, "PRT");
		value.STEP = ms.decodeOctetString (8, "STEP");
		value.ACT = ms.decodeOctetString (7, "ACT");
		value.CHOSEN = ms.decodeOctetString (11, "CHOSEN");
		value.TEA = ms.decodeOctetString (51, "TEA");
		value.CA = ms.decodeOctetString (11, "CA");
		value.PR = ms.decodeOctetString (5, "PR");
		value.LPR = ms.decodeOctetString (5, "LPR");
		value.HPR = ms.decodeOctetString (5, "HPR");
		value.NO = ms.decodeOctetString (14, "NO");
		value.ST = ms.decodeOctetString (51, "ST");
		value.UNTYP = ms.decodeOctetString (5, "UNTYP");
		value.UNIT = ms.decodeOctetString (11, "UNIT");
		value.ELEVTYP = ms.decodeOctetString (4, "ELEVTYP");
		value.FLR = ms.decodeOctetString (11, "FLR");
		value.STRUCTYP = ms.decodeOctetString (5, "STRUCTYP");
		value.BLDG = ms.decodeOctetString (11, "BLDG");
		value.COM = ms.decodeOctetString (33, "COM");
		value.STATE = ms.decodeOctetString (3, "STATE");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEPINVM_Section_t (MMarshalStrategy ms, EPINVM_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PRT, 5, "PRT");
		ms.encode (value.STEP, 8, "STEP");
		ms.encode (value.ACT, 7, "ACT");
		ms.encode (value.CHOSEN, 11, "CHOSEN");
		ms.encode (value.TEA, 51, "TEA");
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.PR, 5, "PR");
		ms.encode (value.LPR, 5, "LPR");
		ms.encode (value.HPR, 5, "HPR");
		ms.encode (value.NO, 14, "NO");
		ms.encode (value.ST, 51, "ST");
		ms.encode (value.UNTYP, 5, "UNTYP");
		ms.encode (value.UNIT, 11, "UNIT");
		ms.encode (value.ELEVTYP, 4, "ELEVTYP");
		ms.encode (value.FLR, 11, "FLR");
		ms.encode (value.STRUCTYP, 5, "STRUCTYP");
		ms.encode (value.BLDG, 11, "BLDG");
		ms.encode (value.COM, 33, "COM");
		ms.encode (value.STATE, 3, "STATE");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPINVM_Section_tHelper.type(); 
	}
	public static byte [] toOctet (EPINVM_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEPINVM_Section_t (ms, val, "EPINVM_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EPINVM_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEPINVM_Section_t (ms, "EPINVM_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPINVM_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPINVM_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPINVM_Section_t();
		value.PRT = new String ();
		value.STEP = new String ();
		value.ACT = new String ();
		value.CHOSEN = new String ();
		value.TEA = new String ();
		value.CA = new String ();
		value.PR = new String ();
		value.LPR = new String ();
		value.HPR = new String ();
		value.NO = new String ();
		value.ST = new String ();
		value.UNTYP = new String ();
		value.UNIT = new String ();
		value.ELEVTYP = new String ();
		value.FLR = new String ();
		value.STRUCTYP = new String ();
		value.BLDG = new String ();
		value.COM = new String ();
		value.STATE = new String ();
		return value; 
	} 
}
