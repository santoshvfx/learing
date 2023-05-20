package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ADDR_SO_tMsg implements MMarshalObject { 
	public ADDR_SO_t value;

	public ADDR_SO_tMsg () {
	}
	public ADDR_SO_tMsg (ADDR_SO_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeADDR_SO_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeADDR_SO_t (ms, tag); 
	}
	static public ADDR_SO_t decodeADDR_SO_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ADDR_SO_t value = create();
		ms.startStruct (tag, false);
		value.TEA = ms.decodeOctetString (51, "TEA");
		value.TP = ms.decodeOctetString (6, "TP");
		value.TEC = ms.decodeOctetString (12, "TEC");
		value.XRST = ms.decodeOctetString (21, "XRST");
		value.PTR = ms.decodeOctetString (16, "PTR");
		value.RT = ms.decodeOctetString (6, "RT");
		value.RZ = ms.decodeOctetString (3, "RZ");
		value.ICSW = ms.decodeOctetString (5, "ICSW");
		value.TYPE = ms.decodeOctetString (6, "TYPE");
		value.RSTTE = ms.decodeOctetString (21, "RSTTE");
		value.RSTLU = ms.decodeOctetString (21, "RSTLU");
		value.RMK0TE = ms.decodeOctetString (51, "RMK0TE");
		value.RMK0LU = ms.decodeOctetString (51, "RMK0LU");
		{ 
			ms.startSequence ("BADR", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("BADR", false);
			{ 
				value.BADR = new com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.BADR[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tMsg.decodeBADR_t (ms, "BADR");
				} 
			}
			ms.endArray ("BADR", false);
			ms.endSequence ("BADR", false);
		}
		{ 
			ms.startSequence ("SUPL", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SUPL", false);
			{ 
				value.SUPL = new com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SUPL[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tMsg.decodeSUPL_t (ms, "SUPL");
				} 
			}
			ms.endArray ("SUPL", false);
			ms.endSequence ("SUPL", false);
		}
		{ 
			ms.startSequence ("MISCLU", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("MISCLU", false);
			{ 
				value.MISCLU = new com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.MISCLU[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_tMsg.decodeMISCLU_t (ms, "MISCLU");
				} 
			}
			ms.endArray ("MISCLU", false);
			ms.endSequence ("MISCLU", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeADDR_SO_t (MMarshalStrategy ms, ADDR_SO_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TEA, 51, "TEA");
		ms.encode (value.TP, 6, "TP");
		ms.encode (value.TEC, 12, "TEC");
		ms.encode (value.XRST, 21, "XRST");
		ms.encode (value.PTR, 16, "PTR");
		ms.encode (value.RT, 6, "RT");
		ms.encode (value.RZ, 3, "RZ");
		ms.encode (value.ICSW, 5, "ICSW");
		ms.encode (value.TYPE, 6, "TYPE");
		ms.encode (value.RSTTE, 21, "RSTTE");
		ms.encode (value.RSTLU, 21, "RSTLU");
		ms.encode (value.RMK0TE, 51, "RMK0TE");
		ms.encode (value.RMK0LU, 51, "RMK0LU");
		{ 
			ms.startSequence ("BADR", true);
			ms.encode (value.BADR.length, "_sequenceLength", true);
			ms.startArray ("BADR", true);
			{ 
				for (int __i0 = 0; __i0 < value.BADR.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tMsg.encodeBADR_t (ms, value.BADR[__i0], "BADR");
				} 
			}
			ms.endArray ("BADR", true);
			ms.endSequence ("BADR", true);
		}
		{ 
			ms.startSequence ("SUPL", true);
			ms.encode (value.SUPL.length, "_sequenceLength", true);
			ms.startArray ("SUPL", true);
			{ 
				for (int __i0 = 0; __i0 < value.SUPL.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tMsg.encodeSUPL_t (ms, value.SUPL[__i0], "SUPL");
				} 
			}
			ms.endArray ("SUPL", true);
			ms.endSequence ("SUPL", true);
		}
		{ 
			ms.startSequence ("MISCLU", true);
			ms.encode (value.MISCLU.length, "_sequenceLength", true);
			ms.startArray ("MISCLU", true);
			{ 
				for (int __i0 = 0; __i0 < value.MISCLU.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_tMsg.encodeMISCLU_t (ms, value.MISCLU[__i0], "MISCLU");
				} 
			}
			ms.endArray ("MISCLU", true);
			ms.endSequence ("MISCLU", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_tHelper.type(); 
	}
	public static byte [] toOctet (ADDR_SO_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeADDR_SO_t (ms, val, "ADDR_SO_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ADDR_SO_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeADDR_SO_t (ms, "ADDR_SO_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_t();
		value.TEA = new String ();
		value.TP = new String ();
		value.TEC = new String ();
		value.XRST = new String ();
		value.PTR = new String ();
		value.RT = new String ();
		value.RZ = new String ();
		value.ICSW = new String ();
		value.TYPE = new String ();
		value.RSTTE = new String ();
		value.RSTLU = new String ();
		value.RMK0TE = new String ();
		value.RMK0LU = new String ();
		int __seqLength = 0;
		value.BADR = new com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[__seqLength];
		value.SUPL = new com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[__seqLength];
		value.MISCLU = new com.sbc.gwsvcs.service.facsaccess.interfaces.MISCLU_t[__seqLength];
		return value; 
	} 
}
