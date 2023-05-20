package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ECPRAT_Section_tMsg implements MMarshalObject { 
	public ECPRAT_Section_t value;

	public ECPRAT_Section_tMsg () {
	}
	public ECPRAT_Section_tMsg (ECPRAT_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeECPRAT_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeECPRAT_Section_t (ms, tag); 
	}
	static public ECPRAT_Section_t decodeECPRAT_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ECPRAT_Section_t value = create();
		ms.startStruct (tag, false);
		value.PRT = ms.decodeOctetString (5, "PRT");
		value.STEP = ms.decodeOctetString (8, "STEP");
		value.EWO = ms.decodeOctetString (12, "EWO");
		value.TR = ms.decodeOctetString (8, "TR");
		value.DREC = ms.decodeOctetString (2, "DREC");
		value.CA = ms.decodeOctetString (11, "CA");
		value.PR = ms.decodeOctetString (5, "PR");
		value.TYPE = ms.decodeOctetString (10, "TYPE");
		value.PGSNO = ms.decodeOctetString (7, "PGSNO");
		value.LTC = ms.decodeOctetString (5, "LTC");
		value.LTF = ms.decodeOctetString (5, "LTF");
		value.FLAG = ms.decodeOctetString (2, "FLAG");
		value.RST = ms.decodeOctetString (21, "RST");
		value.RMK = ms.decodeOctetString (51, "RMK");
		{ 
			ms.startSequence ("EPRMK", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("EPRMK", false);
			{ 
				value.EPRMK = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.EPRMK[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_tMsg.decodeEPRMK_t (ms, "EPRMK");
				} 
			}
			ms.endArray ("EPRMK", false);
			ms.endSequence ("EPRMK", false);
		}
		value.TELEM = ms.decodeOctetString (5, "TELEM");
		value.EQUIP = ms.decodeOctetString (14, "EQUIP");
		value.MULTIP = ms.decodeOctetString (4, "MULTIP");
		value.LOADTP = ms.decodeOctetString (6, "LOADTP");
		value.PNTS = ms.decodeOctetString (3, "PNTS");
		value.UNI = ms.decodeOctetString (2, "UNI");
		value.COND = ms.decodeOctetString (5, "COND");
		value.STAT = ms.decodeOctetString (5, "STAT");
		value.USAGE = ms.decodeOctetString (4, "USAGE");
		value.STAT2 = ms.decodeOctetString (2, "STAT2");
		value.RSV = ms.decodeOctetString (13, "RSV");
		value.DEFTP = ms.decodeOctetString (4, "DEFTP");
		value.DATE = ms.decodeOctetString (9, "DATE");
		value.CTT = ms.decodeOctetString (12, "CTT");
		value.LOC = ms.decodeOctetString (51, "LOC");
		value.PNDSO = ms.decodeOctetString (16, "PNDSO");
		value.FRCA = ms.decodeOctetString (11, "FRCA");
		value.FRPR = ms.decodeOctetString (5, "FRPR");
		value.XCON = ms.decodeOctetString (2, "XCON");
		value.PNDLST = ms.decodeOctetString (16, "PNDLST");
		value.PNDWO = ms.decodeOctetString (16, "PNDWO");
		value.FLDXCON = ms.decodeOctetString (2, "FLDXCON");
		{ 
			ms.startSequence ("TOXRNG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("TOXRNG", false);
			{ 
				value.TOXRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.TOXRNG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_tMsg.decodeTOXRNG_t (ms, "TOXRNG");
				} 
			}
			ms.endArray ("TOXRNG", false);
			ms.endSequence ("TOXRNG", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeECPRAT_Section_t (MMarshalStrategy ms, ECPRAT_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PRT, 5, "PRT");
		ms.encode (value.STEP, 8, "STEP");
		ms.encode (value.EWO, 12, "EWO");
		ms.encode (value.TR, 8, "TR");
		ms.encode (value.DREC, 2, "DREC");
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.PR, 5, "PR");
		ms.encode (value.TYPE, 10, "TYPE");
		ms.encode (value.PGSNO, 7, "PGSNO");
		ms.encode (value.LTC, 5, "LTC");
		ms.encode (value.LTF, 5, "LTF");
		ms.encode (value.FLAG, 2, "FLAG");
		ms.encode (value.RST, 21, "RST");
		ms.encode (value.RMK, 51, "RMK");
		{ 
			ms.startSequence ("EPRMK", true);
			ms.encode (value.EPRMK.length, "_sequenceLength", true);
			ms.startArray ("EPRMK", true);
			{ 
				for (int __i0 = 0; __i0 < value.EPRMK.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_tMsg.encodeEPRMK_t (ms, value.EPRMK[__i0], "EPRMK");
				} 
			}
			ms.endArray ("EPRMK", true);
			ms.endSequence ("EPRMK", true);
		}
		ms.encode (value.TELEM, 5, "TELEM");
		ms.encode (value.EQUIP, 14, "EQUIP");
		ms.encode (value.MULTIP, 4, "MULTIP");
		ms.encode (value.LOADTP, 6, "LOADTP");
		ms.encode (value.PNTS, 3, "PNTS");
		ms.encode (value.UNI, 2, "UNI");
		ms.encode (value.COND, 5, "COND");
		ms.encode (value.STAT, 5, "STAT");
		ms.encode (value.USAGE, 4, "USAGE");
		ms.encode (value.STAT2, 2, "STAT2");
		ms.encode (value.RSV, 13, "RSV");
		ms.encode (value.DEFTP, 4, "DEFTP");
		ms.encode (value.DATE, 9, "DATE");
		ms.encode (value.CTT, 12, "CTT");
		ms.encode (value.LOC, 51, "LOC");
		ms.encode (value.PNDSO, 16, "PNDSO");
		ms.encode (value.FRCA, 11, "FRCA");
		ms.encode (value.FRPR, 5, "FRPR");
		ms.encode (value.XCON, 2, "XCON");
		ms.encode (value.PNDLST, 16, "PNDLST");
		ms.encode (value.PNDWO, 16, "PNDWO");
		ms.encode (value.FLDXCON, 2, "FLDXCON");
		{ 
			ms.startSequence ("TOXRNG", true);
			ms.encode (value.TOXRNG.length, "_sequenceLength", true);
			ms.startArray ("TOXRNG", true);
			{ 
				for (int __i0 = 0; __i0 < value.TOXRNG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_tMsg.encodeTOXRNG_t (ms, value.TOXRNG[__i0], "TOXRNG");
				} 
			}
			ms.endArray ("TOXRNG", true);
			ms.endSequence ("TOXRNG", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_tHelper.type(); 
	}
	public static byte [] toOctet (ECPRAT_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeECPRAT_Section_t (ms, val, "ECPRAT_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ECPRAT_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeECPRAT_Section_t (ms, "ECPRAT_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_t();
		value.PRT = new String ();
		value.STEP = new String ();
		value.EWO = new String ();
		value.TR = new String ();
		value.DREC = new String ();
		value.CA = new String ();
		value.PR = new String ();
		value.TYPE = new String ();
		value.PGSNO = new String ();
		value.LTC = new String ();
		value.LTF = new String ();
		value.FLAG = new String ();
		value.RST = new String ();
		value.RMK = new String ();
		int __seqLength = 0;
		value.EPRMK = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPRMK_t[__seqLength];
		value.TELEM = new String ();
		value.EQUIP = new String ();
		value.MULTIP = new String ();
		value.LOADTP = new String ();
		value.PNTS = new String ();
		value.UNI = new String ();
		value.COND = new String ();
		value.STAT = new String ();
		value.USAGE = new String ();
		value.STAT2 = new String ();
		value.RSV = new String ();
		value.DEFTP = new String ();
		value.DATE = new String ();
		value.CTT = new String ();
		value.LOC = new String ();
		value.PNDSO = new String ();
		value.FRCA = new String ();
		value.FRPR = new String ();
		value.XCON = new String ();
		value.PNDLST = new String ();
		value.PNDWO = new String ();
		value.FLDXCON = new String ();
		value.TOXRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.TOXRNG_t[__seqLength];
		return value; 
	} 
}
