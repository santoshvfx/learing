package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class LOOP_Section_tMsg implements MMarshalObject { 
	public LOOP_Section_t value;

	public LOOP_Section_tMsg () {
	}
	public LOOP_Section_tMsg (LOOP_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLOOP_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLOOP_Section_t (ms, tag); 
	}
	static public LOOP_Section_t decodeLOOP_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		LOOP_Section_t value = create();
		ms.startStruct (tag, false);
		value.DCAPR = ms.decodeOctetString (16, "DCAPR");
		value.LPNO = ms.decodeOctetString (3, "LPNO");
		value.CKID = ms.decodeOctetString (42, "CKID");
		value.TID = ms.decodeOctetString (28, "TID");
		value.STAT = ms.decodeOctetString (5, "STAT");
		value.DATE = ms.decodeOctetString (9, "DATE");
		value.USOC = ms.decodeOctetString (6, "USOC");
		value.WOL = ms.decodeOctetString (4, "WOL");
		value.ADL = ms.decodeOctetString (4, "ADL");
		value.PTP = ms.decodeOctetString (4, "PTP");
		value.OWS = ms.decodeOctetString (2, "OWS");
		value.SSC = ms.decodeOctetString (2, "SSC");
		value.RTF = ms.decodeOctetString (3, "RTF");
		value.RLTNF = ms.decodeOctetString (10, "RLTNF");
		value.TSP = ms.decodeOctetString (3, "TSP");
		value.SSP = ms.decodeOctetString (4, "SSP");
		value.SSM = ms.decodeOctetString (4, "SSM");
		value.ESL = ms.decodeOctetString (4, "ESL");
		value.MKSG = ms.decodeOctetString (2, "MKSG");
		value.ACCT = ms.decodeOctetString (5, "ACCT");
		value.SUS = ms.decodeOctetString (2, "SUS");
		value.ADSR = ms.decodeOctetString (5, "ADSR");
		value.SUBL = ms.decodeOctetString (5, "SUBL");
		value.CSWEX = ms.decodeOctetString (2, "CSWEX");
		value.TRM = ms.decodeOctetString (2, "TRM");
		value.POS = ms.decodeOctetString (6, "POS");
		value.JACK = ms.decodeOctetString (6, "JACK");
		value.WW = ms.decodeOctetString (13, "WW");
		value.TFRMK = ms.decodeOctetString (51, "TFRMK");
		value.EXK = ms.decodeOctetString (7, "EXK");
		value.INVU = ms.decodeOctetString (3, "INVU");
		value.POUT = ms.decodeOctetString (3, "POUT");
		value.RTNN = ms.decodeOctetString (3, "RTNN");
		value.LPNAME = ms.decodeOctetString (5, "LPNAME");
		value.DAPROV = ms.decodeOctetString (5, "DAPROV");
		value.SMSC = ms.decodeOctetString (3, "SMSC");
		value.MORESO = ms.decodeOctetString (2, "MORESO");
		value.MORESOLP = ms.decodeOctetString (2, "MORESOLP");
		{ 
			ms.startSequence ("ADDR", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ADDR", false);
			{ 
				value.ADDR = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ADDR[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_tMsg.decodeADDR_LOOP_t (ms, "ADDR");
				} 
			}
			ms.endArray ("ADDR", false);
			ms.endSequence ("ADDR", false);
		}
		{ 
			ms.startSequence ("OEC", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("OEC", false);
			{ 
				value.OEC = new com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.OEC[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_tMsg.decodeOEC_t (ms, "OEC");
				} 
			}
			ms.endArray ("OEC", false);
			ms.endSequence ("OEC", false);
		}
		{ 
			ms.startSequence ("SEG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SEG", false);
			{ 
				value.SEG = new com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SEG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_tMsg.decodeSEG_LOOP_t (ms, "SEG");
				} 
			}
			ms.endArray ("SEG", false);
			ms.endSequence ("SEG", false);
		}
		{ 
			ms.startSequence ("SO", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SO", false);
			{ 
				value.SO = new com.sbc.gwsvcs.service.facsaccess.interfaces.SO_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SO[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.SO_tMsg.decodeSO_t (ms, "SO");
				} 
			}
			ms.endArray ("SO", false);
			ms.endSequence ("SO", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeLOOP_Section_t (MMarshalStrategy ms, LOOP_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.DCAPR, 16, "DCAPR");
		ms.encode (value.LPNO, 3, "LPNO");
		ms.encode (value.CKID, 42, "CKID");
		ms.encode (value.TID, 28, "TID");
		ms.encode (value.STAT, 5, "STAT");
		ms.encode (value.DATE, 9, "DATE");
		ms.encode (value.USOC, 6, "USOC");
		ms.encode (value.WOL, 4, "WOL");
		ms.encode (value.ADL, 4, "ADL");
		ms.encode (value.PTP, 4, "PTP");
		ms.encode (value.OWS, 2, "OWS");
		ms.encode (value.SSC, 2, "SSC");
		ms.encode (value.RTF, 3, "RTF");
		ms.encode (value.RLTNF, 10, "RLTNF");
		ms.encode (value.TSP, 3, "TSP");
		ms.encode (value.SSP, 4, "SSP");
		ms.encode (value.SSM, 4, "SSM");
		ms.encode (value.ESL, 4, "ESL");
		ms.encode (value.MKSG, 2, "MKSG");
		ms.encode (value.ACCT, 5, "ACCT");
		ms.encode (value.SUS, 2, "SUS");
		ms.encode (value.ADSR, 5, "ADSR");
		ms.encode (value.SUBL, 5, "SUBL");
		ms.encode (value.CSWEX, 2, "CSWEX");
		ms.encode (value.TRM, 2, "TRM");
		ms.encode (value.POS, 6, "POS");
		ms.encode (value.JACK, 6, "JACK");
		ms.encode (value.WW, 13, "WW");
		ms.encode (value.TFRMK, 51, "TFRMK");
		ms.encode (value.EXK, 7, "EXK");
		ms.encode (value.INVU, 3, "INVU");
		ms.encode (value.POUT, 3, "POUT");
		ms.encode (value.RTNN, 3, "RTNN");
		ms.encode (value.LPNAME, 5, "LPNAME");
		ms.encode (value.DAPROV, 5, "DAPROV");
		ms.encode (value.SMSC, 3, "SMSC");
		ms.encode (value.MORESO, 2, "MORESO");
		ms.encode (value.MORESOLP, 2, "MORESOLP");
		{ 
			ms.startSequence ("ADDR", true);
			ms.encode (value.ADDR.length, "_sequenceLength", true);
			ms.startArray ("ADDR", true);
			{ 
				for (int __i0 = 0; __i0 < value.ADDR.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_tMsg.encodeADDR_LOOP_t (ms, value.ADDR[__i0], "ADDR");
				} 
			}
			ms.endArray ("ADDR", true);
			ms.endSequence ("ADDR", true);
		}
		{ 
			ms.startSequence ("OEC", true);
			ms.encode (value.OEC.length, "_sequenceLength", true);
			ms.startArray ("OEC", true);
			{ 
				for (int __i0 = 0; __i0 < value.OEC.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_tMsg.encodeOEC_t (ms, value.OEC[__i0], "OEC");
				} 
			}
			ms.endArray ("OEC", true);
			ms.endSequence ("OEC", true);
		}
		{ 
			ms.startSequence ("SEG", true);
			ms.encode (value.SEG.length, "_sequenceLength", true);
			ms.startArray ("SEG", true);
			{ 
				for (int __i0 = 0; __i0 < value.SEG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_tMsg.encodeSEG_LOOP_t (ms, value.SEG[__i0], "SEG");
				} 
			}
			ms.endArray ("SEG", true);
			ms.endSequence ("SEG", true);
		}
		{ 
			ms.startSequence ("SO", true);
			ms.encode (value.SO.length, "_sequenceLength", true);
			ms.startArray ("SO", true);
			{ 
				for (int __i0 = 0; __i0 < value.SO.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.SO_tMsg.encodeSO_t (ms, value.SO[__i0], "SO");
				} 
			}
			ms.endArray ("SO", true);
			ms.endSequence ("SO", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_tHelper.type(); 
	}
	public static byte [] toOctet (LOOP_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeLOOP_Section_t (ms, val, "LOOP_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static LOOP_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeLOOP_Section_t (ms, "LOOP_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t();
		value.DCAPR = new String ();
		value.LPNO = new String ();
		value.CKID = new String ();
		value.TID = new String ();
		value.STAT = new String ();
		value.DATE = new String ();
		value.USOC = new String ();
		value.WOL = new String ();
		value.ADL = new String ();
		value.PTP = new String ();
		value.OWS = new String ();
		value.SSC = new String ();
		value.RTF = new String ();
		value.RLTNF = new String ();
		value.TSP = new String ();
		value.SSP = new String ();
		value.SSM = new String ();
		value.ESL = new String ();
		value.MKSG = new String ();
		value.ACCT = new String ();
		value.SUS = new String ();
		value.ADSR = new String ();
		value.SUBL = new String ();
		value.CSWEX = new String ();
		value.TRM = new String ();
		value.POS = new String ();
		value.JACK = new String ();
		value.WW = new String ();
		value.TFRMK = new String ();
		value.EXK = new String ();
		value.INVU = new String ();
		value.POUT = new String ();
		value.RTNN = new String ();
		value.LPNAME = new String ();
		value.DAPROV = new String ();
		value.SMSC = new String ();
		value.MORESO = new String ();
		value.MORESOLP = new String ();
		int __seqLength = 0;
		value.ADDR = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LOOP_t[__seqLength];
		value.OEC = new com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_t[__seqLength];
		value.SEG = new com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_t[__seqLength];
		value.SO = new com.sbc.gwsvcs.service.facsaccess.interfaces.SO_t[__seqLength];
		return value; 
	} 
}
