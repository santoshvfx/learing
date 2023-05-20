package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPTERM_Section_tMsg implements MMarshalObject { 
	public EPTERM_Section_t value;

	public EPTERM_Section_tMsg () {
	}
	public EPTERM_Section_tMsg (EPTERM_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEPTERM_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEPTERM_Section_t (ms, tag); 
	}
	static public EPTERM_Section_t decodeEPTERM_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EPTERM_Section_t value = create();
		ms.startStruct (tag, false);
		value.PRT = ms.decodeOctetString (5, "PRT");
		value.STEP = ms.decodeOctetString (8, "STEP");
		value.DREC = ms.decodeOctetString (2, "DREC");
		value.TEA = ms.decodeOctetString (51, "TEA");
		value.PTEA = ms.decodeOctetString (51, "PTEA");
		value.TYPE = ms.decodeOctetString (6, "TYPE");
		value.IND = ms.decodeOctetString (5, "IND");
		value.RT = ms.decodeOctetString (6, "RT");
		value.RZ = ms.decodeOctetString (3, "RZ");
		value.CZ = ms.decodeOctetString (3, "CZ");
		value.CSA = ms.decodeOctetString (2, "CSA");
		value.TPR = ms.decodeOctetString (7, "TPR");
		value.PRQ = ms.decodeOctetString (2, "PRQ");
		value.RMK = ms.decodeOctetString (51, "RMK");
		value.SW = ms.decodeOctetString (8, "SW");
		value.SWRVW = ms.decodeOctetString (2, "SWRVW");
		value.RST = ms.decodeOctetString (21, "RST");
		value.XRST = ms.decodeOctetString (21, "XRST");
		value.PC = ms.decodeOctetString (5, "PC");
		value.AC = ms.decodeOctetString (5, "AC");
		value.TBLTP = ms.decodeOctetString (2, "TBLTP");
		value.TBLN = ms.decodeOctetString (2, "TBLN");
		value.BPI = ms.decodeOctetString (2, "BPI");
		value.FABP = ms.decodeOctetString (4, "FABP");
		value.SEQ = ms.decodeOctetString (2, "SEQ");
		value.RLA = ms.decodeOctetString (51, "RLA");
		value.RLC = ms.decodeOctetString (12, "RLC");
		value.LOTI = ms.decodeOctetString (3, "LOTI");
		{ 
			ms.startSequence ("INRNG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("INRNG", false);
			{ 
				value.INRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.INRNG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_tMsg.decodeINRNG_t (ms, "INRNG");
				} 
			}
			ms.endArray ("INRNG", false);
			ms.endSequence ("INRNG", false);
		}
		{ 
			ms.startSequence ("OUTRNG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("OUTRNG", false);
			{ 
				value.OUTRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.OUTRNG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tMsg.decodeOUTRNG_t (ms, "OUTRNG");
				} 
			}
			ms.endArray ("OUTRNG", false);
			ms.endSequence ("OUTRNG", false);
		}
		{ 
			ms.startSequence ("ATTRNG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ATTRNG", false);
			{ 
				value.ATTRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ATTRNG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_tMsg.decodeATTRNG_t (ms, "ATTRNG");
				} 
			}
			ms.endArray ("ATTRNG", false);
			ms.endSequence ("ATTRNG", false);
		}
		{ 
			ms.startSequence ("OCDAT", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("OCDAT", false);
			{ 
				value.OCDAT = new com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.OCDAT[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_tMsg.decodeOCDAT_t (ms, "OCDAT");
				} 
			}
			ms.endArray ("OCDAT", false);
			ms.endSequence ("OCDAT", false);
		}
		{ 
			ms.startSequence ("RTARNG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("RTARNG", false);
			{ 
				value.RTARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.RTARNG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tMsg.decodeRTARNG_t (ms, "RTARNG");
				} 
			}
			ms.endArray ("RTARNG", false);
			ms.endSequence ("RTARNG", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEPTERM_Section_t (MMarshalStrategy ms, EPTERM_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PRT, 5, "PRT");
		ms.encode (value.STEP, 8, "STEP");
		ms.encode (value.DREC, 2, "DREC");
		ms.encode (value.TEA, 51, "TEA");
		ms.encode (value.PTEA, 51, "PTEA");
		ms.encode (value.TYPE, 6, "TYPE");
		ms.encode (value.IND, 5, "IND");
		ms.encode (value.RT, 6, "RT");
		ms.encode (value.RZ, 3, "RZ");
		ms.encode (value.CZ, 3, "CZ");
		ms.encode (value.CSA, 2, "CSA");
		ms.encode (value.TPR, 7, "TPR");
		ms.encode (value.PRQ, 2, "PRQ");
		ms.encode (value.RMK, 51, "RMK");
		ms.encode (value.SW, 8, "SW");
		ms.encode (value.SWRVW, 2, "SWRVW");
		ms.encode (value.RST, 21, "RST");
		ms.encode (value.XRST, 21, "XRST");
		ms.encode (value.PC, 5, "PC");
		ms.encode (value.AC, 5, "AC");
		ms.encode (value.TBLTP, 2, "TBLTP");
		ms.encode (value.TBLN, 2, "TBLN");
		ms.encode (value.BPI, 2, "BPI");
		ms.encode (value.FABP, 4, "FABP");
		ms.encode (value.SEQ, 2, "SEQ");
		ms.encode (value.RLA, 51, "RLA");
		ms.encode (value.RLC, 12, "RLC");
		ms.encode (value.LOTI, 3, "LOTI");
		{ 
			ms.startSequence ("INRNG", true);
			ms.encode (value.INRNG.length, "_sequenceLength", true);
			ms.startArray ("INRNG", true);
			{ 
				for (int __i0 = 0; __i0 < value.INRNG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_tMsg.encodeINRNG_t (ms, value.INRNG[__i0], "INRNG");
				} 
			}
			ms.endArray ("INRNG", true);
			ms.endSequence ("INRNG", true);
		}
		{ 
			ms.startSequence ("OUTRNG", true);
			ms.encode (value.OUTRNG.length, "_sequenceLength", true);
			ms.startArray ("OUTRNG", true);
			{ 
				for (int __i0 = 0; __i0 < value.OUTRNG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tMsg.encodeOUTRNG_t (ms, value.OUTRNG[__i0], "OUTRNG");
				} 
			}
			ms.endArray ("OUTRNG", true);
			ms.endSequence ("OUTRNG", true);
		}
		{ 
			ms.startSequence ("ATTRNG", true);
			ms.encode (value.ATTRNG.length, "_sequenceLength", true);
			ms.startArray ("ATTRNG", true);
			{ 
				for (int __i0 = 0; __i0 < value.ATTRNG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_tMsg.encodeATTRNG_t (ms, value.ATTRNG[__i0], "ATTRNG");
				} 
			}
			ms.endArray ("ATTRNG", true);
			ms.endSequence ("ATTRNG", true);
		}
		{ 
			ms.startSequence ("OCDAT", true);
			ms.encode (value.OCDAT.length, "_sequenceLength", true);
			ms.startArray ("OCDAT", true);
			{ 
				for (int __i0 = 0; __i0 < value.OCDAT.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_tMsg.encodeOCDAT_t (ms, value.OCDAT[__i0], "OCDAT");
				} 
			}
			ms.endArray ("OCDAT", true);
			ms.endSequence ("OCDAT", true);
		}
		{ 
			ms.startSequence ("RTARNG", true);
			ms.encode (value.RTARNG.length, "_sequenceLength", true);
			ms.startArray ("RTARNG", true);
			{ 
				for (int __i0 = 0; __i0 < value.RTARNG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tMsg.encodeRTARNG_t (ms, value.RTARNG[__i0], "RTARNG");
				} 
			}
			ms.endArray ("RTARNG", true);
			ms.endSequence ("RTARNG", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPTERM_Section_tHelper.type(); 
	}
	public static byte [] toOctet (EPTERM_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEPTERM_Section_t (ms, val, "EPTERM_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EPTERM_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEPTERM_Section_t (ms, "EPTERM_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPTERM_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPTERM_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPTERM_Section_t();
		value.PRT = new String ();
		value.STEP = new String ();
		value.DREC = new String ();
		value.TEA = new String ();
		value.PTEA = new String ();
		value.TYPE = new String ();
		value.IND = new String ();
		value.RT = new String ();
		value.RZ = new String ();
		value.CZ = new String ();
		value.CSA = new String ();
		value.TPR = new String ();
		value.PRQ = new String ();
		value.RMK = new String ();
		value.SW = new String ();
		value.SWRVW = new String ();
		value.RST = new String ();
		value.XRST = new String ();
		value.PC = new String ();
		value.AC = new String ();
		value.TBLTP = new String ();
		value.TBLN = new String ();
		value.BPI = new String ();
		value.FABP = new String ();
		value.SEQ = new String ();
		value.RLA = new String ();
		value.RLC = new String ();
		value.LOTI = new String ();
		int __seqLength = 0;
		value.INRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_t[__seqLength];
		value.OUTRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_t[__seqLength];
		value.ATTRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_t[__seqLength];
		value.OCDAT = new com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_t[__seqLength];
		value.RTARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t[__seqLength];
		return value; 
	} 
}
