package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPAF_Section_tMsg implements MMarshalObject { 
	public EPAF_Section_t value;

	public EPAF_Section_tMsg () {
	}
	public EPAF_Section_tMsg (EPAF_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEPAF_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEPAF_Section_t (ms, tag); 
	}
	static public EPAF_Section_t decodeEPAF_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EPAF_Section_t value = create();
		ms.startStruct (tag, false);
		value.PRT = ms.decodeOctetString (5, "PRT");
		value.STEP = ms.decodeOctetString (8, "STEP");
		value.DREC = ms.decodeOctetString (2, "DREC");
		value.BLTYPE = ms.decodeOctetString (5, "BLTYPE");
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
		value.MKSEG = ms.decodeOctetString (2, "MKSEG");
		value.ACCT = ms.decodeOctetString (5, "ACCT");
		value.CSW = ms.decodeOctetString (5, "CSW");
		value.STEA = ms.decodeOctetString (51, "STEA");
		value.LRST = ms.decodeOctetString (21, "LRST");
		value.LRMK = ms.decodeOctetString (51, "LRMK");
		value.CID = ms.decodeOctetString (41, "CID");
		value.CKT2 = ms.decodeOctetString (28, "CKT2");
		value.CLCI = ms.decodeOctetString (70, "CLCI");
		value.CKT3 = ms.decodeOctetString (70, "CKT3");
		value.USOC = ms.decodeOctetString (6, "USOC");
		value.MUR = ms.decodeOctetString (2, "MUR");
		value.AFSTAT = ms.decodeOctetString (5, "AFSTAT");
		value.DATE = ms.decodeOctetString (9, "DATE");
		value.ADL = ms.decodeOctetString (2, "ADL");
		value.SSP = ms.decodeOctetString (2, "SSP");
		value.SSM = ms.decodeOctetString (2, "SSM");
		value.ESL = ms.decodeOctetString (2, "ESL");
		value.ADSR = ms.decodeOctetString (2, "ADSR");
		value.SUBL = ms.decodeOctetString (2, "SUBL");
		value.SUS = ms.decodeOctetString (2, "SUS");
		value.TSP = ms.decodeOctetString (3, "TSP");
		value.BDG = ms.decodeOctetString (2, "BDG");
		value.SSC = ms.decodeOctetString (2, "SSC");
		value.RTF = ms.decodeOctetString (3, "RTF");
		value.OWS = ms.decodeOctetString (2, "OWS");
		value.WOL = ms.decodeOctetString (2, "WOL");
		value.DTEA = ms.decodeOctetString (51, "DTEA");
		value.CSWEX = ms.decodeOctetString (2, "CSWEX");
		value.TRM = ms.decodeOctetString (2, "TRM");
		value.WW = ms.decodeOctetString (13, "WW");
		value.POS = ms.decodeOctetString (6, "POS");
		value.JACK = ms.decodeOctetString (6, "JACK");
		value.BP = ms.decodeOctetString (5, "BP");
		{ 
			ms.startSequence ("LSEG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("LSEG", false);
			{ 
				value.LSEG = new com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.LSEG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_tMsg.decodeLSEG_t (ms, "LSEG");
				} 
			}
			ms.endArray ("LSEG", false);
			ms.endSequence ("LSEG", false);
		}
		{ 
			ms.startSequence ("EPOEC", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("EPOEC", false);
			{ 
				value.EPOEC = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.EPOEC[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_tMsg.decodeEPOEC_t (ms, "EPOEC");
				} 
			}
			ms.endArray ("EPOEC", false);
			ms.endSequence ("EPOEC", false);
		}
		{ 
			ms.startSequence ("EAPNT", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("EAPNT", false);
			{ 
				value.EAPNT = new com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.EAPNT[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_tMsg.decodeEAPNT_t (ms, "EAPNT");
				} 
			}
			ms.endArray ("EAPNT", false);
			ms.endSequence ("EAPNT", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEPAF_Section_t (MMarshalStrategy ms, EPAF_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PRT, 5, "PRT");
		ms.encode (value.STEP, 8, "STEP");
		ms.encode (value.DREC, 2, "DREC");
		ms.encode (value.BLTYPE, 5, "BLTYPE");
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
		ms.encode (value.MKSEG, 2, "MKSEG");
		ms.encode (value.ACCT, 5, "ACCT");
		ms.encode (value.CSW, 5, "CSW");
		ms.encode (value.STEA, 51, "STEA");
		ms.encode (value.LRST, 21, "LRST");
		ms.encode (value.LRMK, 51, "LRMK");
		ms.encode (value.CID, 41, "CID");
		ms.encode (value.CKT2, 28, "CKT2");
		ms.encode (value.CLCI, 70, "CLCI");
		ms.encode (value.CKT3, 70, "CKT3");
		ms.encode (value.USOC, 6, "USOC");
		ms.encode (value.MUR, 2, "MUR");
		ms.encode (value.AFSTAT, 5, "AFSTAT");
		ms.encode (value.DATE, 9, "DATE");
		ms.encode (value.ADL, 2, "ADL");
		ms.encode (value.SSP, 2, "SSP");
		ms.encode (value.SSM, 2, "SSM");
		ms.encode (value.ESL, 2, "ESL");
		ms.encode (value.ADSR, 2, "ADSR");
		ms.encode (value.SUBL, 2, "SUBL");
		ms.encode (value.SUS, 2, "SUS");
		ms.encode (value.TSP, 3, "TSP");
		ms.encode (value.BDG, 2, "BDG");
		ms.encode (value.SSC, 2, "SSC");
		ms.encode (value.RTF, 3, "RTF");
		ms.encode (value.OWS, 2, "OWS");
		ms.encode (value.WOL, 2, "WOL");
		ms.encode (value.DTEA, 51, "DTEA");
		ms.encode (value.CSWEX, 2, "CSWEX");
		ms.encode (value.TRM, 2, "TRM");
		ms.encode (value.WW, 13, "WW");
		ms.encode (value.POS, 6, "POS");
		ms.encode (value.JACK, 6, "JACK");
		ms.encode (value.BP, 5, "BP");
		{ 
			ms.startSequence ("LSEG", true);
			ms.encode (value.LSEG.length, "_sequenceLength", true);
			ms.startArray ("LSEG", true);
			{ 
				for (int __i0 = 0; __i0 < value.LSEG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_tMsg.encodeLSEG_t (ms, value.LSEG[__i0], "LSEG");
				} 
			}
			ms.endArray ("LSEG", true);
			ms.endSequence ("LSEG", true);
		}
		{ 
			ms.startSequence ("EPOEC", true);
			ms.encode (value.EPOEC.length, "_sequenceLength", true);
			ms.startArray ("EPOEC", true);
			{ 
				for (int __i0 = 0; __i0 < value.EPOEC.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_tMsg.encodeEPOEC_t (ms, value.EPOEC[__i0], "EPOEC");
				} 
			}
			ms.endArray ("EPOEC", true);
			ms.endSequence ("EPOEC", true);
		}
		{ 
			ms.startSequence ("EAPNT", true);
			ms.encode (value.EAPNT.length, "_sequenceLength", true);
			ms.startArray ("EAPNT", true);
			{ 
				for (int __i0 = 0; __i0 < value.EAPNT.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_tMsg.encodeEAPNT_t (ms, value.EAPNT[__i0], "EAPNT");
				} 
			}
			ms.endArray ("EAPNT", true);
			ms.endSequence ("EAPNT", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tHelper.type(); 
	}
	public static byte [] toOctet (EPAF_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEPAF_Section_t (ms, val, "EPAF_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EPAF_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEPAF_Section_t (ms, "EPAF_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t();
		value.PRT = new String ();
		value.STEP = new String ();
		value.DREC = new String ();
		value.BLTYPE = new String ();
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
		value.MKSEG = new String ();
		value.ACCT = new String ();
		value.CSW = new String ();
		value.STEA = new String ();
		value.LRST = new String ();
		value.LRMK = new String ();
		value.CID = new String ();
		value.CKT2 = new String ();
		value.CLCI = new String ();
		value.CKT3 = new String ();
		value.USOC = new String ();
		value.MUR = new String ();
		value.AFSTAT = new String ();
		value.DATE = new String ();
		value.ADL = new String ();
		value.SSP = new String ();
		value.SSM = new String ();
		value.ESL = new String ();
		value.ADSR = new String ();
		value.SUBL = new String ();
		value.SUS = new String ();
		value.TSP = new String ();
		value.BDG = new String ();
		value.SSC = new String ();
		value.RTF = new String ();
		value.OWS = new String ();
		value.WOL = new String ();
		value.DTEA = new String ();
		value.CSWEX = new String ();
		value.TRM = new String ();
		value.WW = new String ();
		value.POS = new String ();
		value.JACK = new String ();
		value.BP = new String ();
		int __seqLength = 0;
		value.LSEG = new com.sbc.gwsvcs.service.facsaccess.interfaces.LSEG_t[__seqLength];
		value.EPOEC = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_t[__seqLength];
		value.EAPNT = new com.sbc.gwsvcs.service.facsaccess.interfaces.EAPNT_t[__seqLength];
		return value; 
	} 
}
