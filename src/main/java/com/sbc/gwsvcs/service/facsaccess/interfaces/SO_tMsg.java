package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SO_tMsg implements MMarshalObject { 
	public SO_t value;

	public SO_tMsg () {
	}
	public SO_tMsg (SO_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSO_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSO_t (ms, tag); 
	}
	static public SO_t decodeSO_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SO_t value = create();
		ms.startStruct (tag, false);
		value.ORD = ms.decodeOctetString (15, "ORD");
		value.ORD_VER = ms.decodeOctetString (3, "ORD_VER");
		value.DD = ms.decodeOctetString (9, "DD");
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
		value.DCAPR = ms.decodeOctetString (16, "DCAPR");
		value.LSTF = ms.decodeOctetString (23, "LSTF");
		value.RMA = ms.decodeOctetString (17, "RMA");
		{ 
			ms.startSequence ("ADDR", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ADDR", false);
			{ 
				value.ADDR = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ADDR[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_tMsg.decodeADDR_SO_t (ms, "ADDR");
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
				value.SEG = new com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SEG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_tMsg.decodeSEG_SO_t (ms, "SEG");
				} 
			}
			ms.endArray ("SEG", false);
			ms.endSequence ("SEG", false);
		}
		{ 
			ms.startSequence ("LSTFN", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("LSTFN", false);
			{ 
				value.LSTFN = new com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.LSTFN[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_tMsg.decodeLSTFN_t (ms, "LSTFN");
				} 
			}
			ms.endArray ("LSTFN", false);
			ms.endSequence ("LSTFN", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSO_t (MMarshalStrategy ms, SO_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ORD, 15, "ORD");
		ms.encode (value.ORD_VER, 3, "ORD_VER");
		ms.encode (value.DD, 9, "DD");
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
		ms.encode (value.DCAPR, 16, "DCAPR");
		ms.encode (value.LSTF, 23, "LSTF");
		ms.encode (value.RMA, 17, "RMA");
		{ 
			ms.startSequence ("ADDR", true);
			ms.encode (value.ADDR.length, "_sequenceLength", true);
			ms.startArray ("ADDR", true);
			{ 
				for (int __i0 = 0; __i0 < value.ADDR.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_tMsg.encodeADDR_SO_t (ms, value.ADDR[__i0], "ADDR");
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
					com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_tMsg.encodeSEG_SO_t (ms, value.SEG[__i0], "SEG");
				} 
			}
			ms.endArray ("SEG", true);
			ms.endSequence ("SEG", true);
		}
		{ 
			ms.startSequence ("LSTFN", true);
			ms.encode (value.LSTFN.length, "_sequenceLength", true);
			ms.startArray ("LSTFN", true);
			{ 
				for (int __i0 = 0; __i0 < value.LSTFN.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_tMsg.encodeLSTFN_t (ms, value.LSTFN[__i0], "LSTFN");
				} 
			}
			ms.endArray ("LSTFN", true);
			ms.endSequence ("LSTFN", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.SO_tHelper.type(); 
	}
	public static byte [] toOctet (SO_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSO_t (ms, val, "SO_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SO_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSO_t (ms, "SO_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SO_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SO_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.SO_t();
		value.ORD = new String ();
		value.ORD_VER = new String ();
		value.DD = new String ();
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
		value.DCAPR = new String ();
		value.LSTF = new String ();
		value.RMA = new String ();
		int __seqLength = 0;
		value.ADDR = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_SO_t[__seqLength];
		value.OEC = new com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_t[__seqLength];
		value.SEG = new com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t[__seqLength];
		value.LSTFN = new com.sbc.gwsvcs.service.facsaccess.interfaces.LSTFN_t[__seqLength];
		return value; 
	} 
}
