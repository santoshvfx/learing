package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class LSTFN_tMsg implements MMarshalObject { 
	public LSTFN_t value;

	public LSTFN_tMsg () {
	}
	public LSTFN_tMsg (LSTFN_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLSTFN_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLSTFN_t (ms, tag); 
	}
	static public LSTFN_t decodeLSTFN_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		LSTFN_t value = create();
		ms.startStruct (tag, false);
		value.LST = ms.decodeOctetString (3, "LST");
		value.ITM = ms.decodeOctetString (2, "ITM");
		value.CKID = ms.decodeOctetString (42, "CKID");
		value.TID = ms.decodeOctetString (28, "TID");
		value.FRCA = ms.decodeOctetString (11, "FRCA");
		value.FRPR = ms.decodeOctetString (5, "FRPR");
		value.FBP = ms.decodeOctetString (5, "FBP");
		value.FOBP = ms.decodeOctetString (17, "FOBP");
		value.TOCA = ms.decodeOctetString (11, "TOCA");
		value.TOPR = ms.decodeOctetString (5, "TOPR");
		value.TBP = ms.decodeOctetString (5, "TBP");
		value.TOBP = ms.decodeOctetString (17, "TOBP");
		value.LSTTE = ms.decodeOctetString (51, "LSTTE");
		value.FRLSTTE = ms.decodeOctetString (51, "FRLSTTE");
		value.TOLSTTE = ms.decodeOctetString (51, "TOLSTTE");
		value.LRMK = ms.decodeOctetString (51, "LRMK");
		{ 
			ms.startSequence ("ADDR", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ADDR", false);
			{ 
				value.ADDR = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ADDR[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_tMsg.decodeADDR_LSTFN_t (ms, "ADDR");
				} 
			}
			ms.endArray ("ADDR", false);
			ms.endSequence ("ADDR", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeLSTFN_t (MMarshalStrategy ms, LSTFN_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.LST, 3, "LST");
		ms.encode (value.ITM, 2, "ITM");
		ms.encode (value.CKID, 42, "CKID");
		ms.encode (value.TID, 28, "TID");
		ms.encode (value.FRCA, 11, "FRCA");
		ms.encode (value.FRPR, 5, "FRPR");
		ms.encode (value.FBP, 5, "FBP");
		ms.encode (value.FOBP, 17, "FOBP");
		ms.encode (value.TOCA, 11, "TOCA");
		ms.encode (value.TOPR, 5, "TOPR");
		ms.encode (value.TBP, 5, "TBP");
		ms.encode (value.TOBP, 17, "TOBP");
		ms.encode (value.LSTTE, 51, "LSTTE");
		ms.encode (value.FRLSTTE, 51, "FRLSTTE");
		ms.encode (value.TOLSTTE, 51, "TOLSTTE");
		ms.encode (value.LRMK, 51, "LRMK");
		{ 
			ms.startSequence ("ADDR", true);
			ms.encode (value.ADDR.length, "_sequenceLength", true);
			ms.startArray ("ADDR", true);
			{ 
				for (int __i0 = 0; __i0 < value.ADDR.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_tMsg.encodeADDR_LSTFN_t (ms, value.ADDR[__i0], "ADDR");
				} 
			}
			ms.endArray ("ADDR", true);
			ms.endSequence ("ADDR", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_tHelper.type(); 
	}
	public static byte [] toOctet (LSTFN_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeLSTFN_t (ms, val, "LSTFN_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static LSTFN_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeLSTFN_t (ms, "LSTFN_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_t();
		value.LST = new String ();
		value.ITM = new String ();
		value.CKID = new String ();
		value.TID = new String ();
		value.FRCA = new String ();
		value.FRPR = new String ();
		value.FBP = new String ();
		value.FOBP = new String ();
		value.TOCA = new String ();
		value.TOPR = new String ();
		value.TBP = new String ();
		value.TOBP = new String ();
		value.LSTTE = new String ();
		value.FRLSTTE = new String ();
		value.TOLSTTE = new String ();
		value.LRMK = new String ();
		int __seqLength = 0;
		value.ADDR = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t[__seqLength];
		return value; 
	} 
}
