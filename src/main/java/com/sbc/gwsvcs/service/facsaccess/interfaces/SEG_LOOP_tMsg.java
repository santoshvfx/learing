package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SEG_LOOP_tMsg implements MMarshalObject { 
	public SEG_LOOP_t value;

	public SEG_LOOP_tMsg () {
	}
	public SEG_LOOP_tMsg (SEG_LOOP_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSEG_LOOP_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSEG_LOOP_t (ms, tag); 
	}
	static public SEG_LOOP_t decodeSEG_LOOP_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SEG_LOOP_t value = create();
		ms.startStruct (tag, false);
		value.SEGNO = ms.decodeOctetString (2, "SEGNO");
		value.CA = ms.decodeOctetString (11, "CA");
		value.PR = ms.decodeOctetString (5, "PR");
		value.LSTAT = ms.decodeOctetString (5, "LSTAT");
		value.BP = ms.decodeOctetString (17, "BP");
		value.OBP = ms.decodeOctetString (13, "OBP");
		value.LPORG = ms.decodeOctetString (8, "LPORG");
		value.RLOE = ms.decodeOctetString (8, "RLOE");
		value.RLOA = ms.decodeOctetString (51, "RLOA");
		value.RLOC = ms.decodeOctetString (12, "RLOC");
		value.RLC = ms.decodeOctetString (12, "RLC");
		value.LOTI = ms.decodeOctetString (3, "LOTI");
		value.TEA = ms.decodeOctetString (51, "TEA");
		value.TP = ms.decodeOctetString (6, "TP");
		value.COMM = ms.decodeOctetString (2, "COMM");
		value.UF = ms.decodeOctetString (5, "UF");
		value.SC = ms.decodeOctetString (4, "SC");
		value.TETE = ms.decodeOctetString (14, "TETE");
		value.CQ = ms.decodeOctetString (10, "CQ");
		value.DEF = ms.decodeOctetString (4, "DEF");
		value.DEFD = ms.decodeOctetString (9, "DEFD");
		value.CTT = ms.decodeOctetString (10, "CTT");
		value.DEFL = ms.decodeOctetString (51, "DEFL");
		value.LT = ms.decodeOctetString (6, "LT");
		value.LNOP = ms.decodeOctetString (3, "LNOP");
		value.SYSTP = ms.decodeOctetString (10, "SYSTP");
		value.PGSNO = ms.decodeOctetString (5, "PGSNO");
		value.FLDLTS = ms.decodeOctetString (5, "FLDLTS");
		value.COLTS = ms.decodeOctetString (5, "COLTS");
		value.CNST = ms.decodeOctetString (5, "CNST");
		value.ORIG = ms.decodeOctetString (2, "ORIG");
		value.MPROV = ms.decodeOctetString (5, "MPROV");
		value.MCLLI = ms.decodeOctetString (12, "MCLLI");
		value.MLOC = ms.decodeOctetString (4, "MLOC");
		value.MCA = ms.decodeOctetString (11, "MCA");
		value.MPR = ms.decodeOctetString (5, "MPR");
		value.RLA = ms.decodeOctetString (51, "RLA");
		value.SDP = ms.decodeOctetString (2, "SDP");
		value.TSB = ms.decodeOctetString (4, "TSB");
		value.TPR = ms.decodeOctetString (7, "TPR");
		value.LMURMK = ms.decodeOctetString (19, "LMURMK");
		value.EWOID = ms.decodeOctetString (13, "EWOID");
		value.EWODD = ms.decodeOctetString (9, "EWODD");
		value.LSTID = ms.decodeOctetString (8, "LSTID");
		value.SOLST = ms.decodeOctetString (3, "SOLST");
		value.SOITM = ms.decodeOctetString (2, "SOITM");
		value.SOLSTDD = ms.decodeOctetString (9, "SOLSTDD");
		value.RSVINFO = ms.decodeOctetString (76, "RSVINFO");
		value.RSVDAT = ms.decodeOctetString (9, "RSVDAT");
		value.RSVRMK = ms.decodeOctetString (51, "RSVRMK");
		value.RSTTE = ms.decodeOctetString (21, "RSTTE");
		value.PERM = ms.decodeOctetString (2, "PERM");
		value.XRST = ms.decodeOctetString (21, "XRST");
		value.RMK0TE = ms.decodeOctetString (51, "RMK0TE");
		value.RMK0PR = ms.decodeOctetString (51, "RMK0PR");
		value.CQC = ms.decodeOctetString (2, "CQC");
		value.CDC = ms.decodeOctetString (3, "CDC");
		value.ASGBPR = ms.decodeOctetString (17, "ASGBPR");
		value.ASBPSTAT = ms.decodeOctetString (5, "ASBPSTAT");
		value.PGSTP = ms.decodeOctetString (10, "PGSTP");
		value.LTS = ms.decodeOctetString (5, "LTS");
		value.DLE = ms.decodeOctetString (2, "DLE");
		value.TSI = ms.decodeOctetString (2, "TSI");
		value.DLERMK = ms.decodeOctetString (51, "DLERMK");
		value.DLERST = ms.decodeOctetString (21, "DLERST");
		value.DLEONU = ms.decodeOctetString (51, "DLEONU");
		value.ONURST = ms.decodeOctetString (21, "ONURST");
		value.ONUXRST = ms.decodeOctetString (21, "ONUXRST");
		value.ONURMK = ms.decodeOctetString (51, "ONURMK");
		value.TFCA1 = ms.decodeOctetString (11, "TFCA1");
		value.TFCA2 = ms.decodeOctetString (11, "TFCA2");
		value.TFPR1 = ms.decodeOctetString (5, "TFPR1");
		value.TFPR2 = ms.decodeOctetString (5, "TFPR2");
		value.FICTMED1 = ms.decodeOctetString (6, "FICTMED1");
		value.FICTMED2 = ms.decodeOctetString (6, "FICTMED2");
		value.FICTEA1 = ms.decodeOctetString (51, "FICTEA1");
		value.FICTEA2 = ms.decodeOctetString (51, "FICTEA2");
		value.FICTYPE1 = ms.decodeOctetString (6, "FICTYPE1");
		value.FICTYPE2 = ms.decodeOctetString (6, "FICTYPE2");
		value.ABPRSVINFO = ms.decodeOctetString (76, "ABPRSVINFO");
		value.ABPRSVDAT = ms.decodeOctetString (9, "ABPRSVDAT");
		value.ABPRSVRMK = ms.decodeOctetString (51, "ABPRSVRMK");
		{ 
			ms.startSequence ("ASGBP", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ASGBP", false);
			{ 
				value.ASGBP = new com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ASGBP[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_tMsg.decodeASGBP_t (ms, "ASGBP");
				} 
			}
			ms.endArray ("ASGBP", false);
			ms.endSequence ("ASGBP", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSEG_LOOP_t (MMarshalStrategy ms, SEG_LOOP_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SEGNO, 2, "SEGNO");
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.PR, 5, "PR");
		ms.encode (value.LSTAT, 5, "LSTAT");
		ms.encode (value.BP, 17, "BP");
		ms.encode (value.OBP, 13, "OBP");
		ms.encode (value.LPORG, 8, "LPORG");
		ms.encode (value.RLOE, 8, "RLOE");
		ms.encode (value.RLOA, 51, "RLOA");
		ms.encode (value.RLOC, 12, "RLOC");
		ms.encode (value.RLC, 12, "RLC");
		ms.encode (value.LOTI, 3, "LOTI");
		ms.encode (value.TEA, 51, "TEA");
		ms.encode (value.TP, 6, "TP");
		ms.encode (value.COMM, 2, "COMM");
		ms.encode (value.UF, 5, "UF");
		ms.encode (value.SC, 4, "SC");
		ms.encode (value.TETE, 14, "TETE");
		ms.encode (value.CQ, 10, "CQ");
		ms.encode (value.DEF, 4, "DEF");
		ms.encode (value.DEFD, 9, "DEFD");
		ms.encode (value.CTT, 10, "CTT");
		ms.encode (value.DEFL, 51, "DEFL");
		ms.encode (value.LT, 6, "LT");
		ms.encode (value.LNOP, 3, "LNOP");
		ms.encode (value.SYSTP, 10, "SYSTP");
		ms.encode (value.PGSNO, 5, "PGSNO");
		ms.encode (value.FLDLTS, 5, "FLDLTS");
		ms.encode (value.COLTS, 5, "COLTS");
		ms.encode (value.CNST, 5, "CNST");
		ms.encode (value.ORIG, 2, "ORIG");
		ms.encode (value.MPROV, 5, "MPROV");
		ms.encode (value.MCLLI, 12, "MCLLI");
		ms.encode (value.MLOC, 4, "MLOC");
		ms.encode (value.MCA, 11, "MCA");
		ms.encode (value.MPR, 5, "MPR");
		ms.encode (value.RLA, 51, "RLA");
		ms.encode (value.SDP, 2, "SDP");
		ms.encode (value.TSB, 4, "TSB");
		ms.encode (value.TPR, 7, "TPR");
		ms.encode (value.LMURMK, 19, "LMURMK");
		ms.encode (value.EWOID, 13, "EWOID");
		ms.encode (value.EWODD, 9, "EWODD");
		ms.encode (value.LSTID, 8, "LSTID");
		ms.encode (value.SOLST, 3, "SOLST");
		ms.encode (value.SOITM, 2, "SOITM");
		ms.encode (value.SOLSTDD, 9, "SOLSTDD");
		ms.encode (value.RSVINFO, 76, "RSVINFO");
		ms.encode (value.RSVDAT, 9, "RSVDAT");
		ms.encode (value.RSVRMK, 51, "RSVRMK");
		ms.encode (value.RSTTE, 21, "RSTTE");
		ms.encode (value.PERM, 2, "PERM");
		ms.encode (value.XRST, 21, "XRST");
		ms.encode (value.RMK0TE, 51, "RMK0TE");
		ms.encode (value.RMK0PR, 51, "RMK0PR");
		ms.encode (value.CQC, 2, "CQC");
		ms.encode (value.CDC, 3, "CDC");
		ms.encode (value.ASGBPR, 17, "ASGBPR");
		ms.encode (value.ASBPSTAT, 5, "ASBPSTAT");
		ms.encode (value.PGSTP, 10, "PGSTP");
		ms.encode (value.LTS, 5, "LTS");
		ms.encode (value.DLE, 2, "DLE");
		ms.encode (value.TSI, 2, "TSI");
		ms.encode (value.DLERMK, 51, "DLERMK");
		ms.encode (value.DLERST, 21, "DLERST");
		ms.encode (value.DLEONU, 51, "DLEONU");
		ms.encode (value.ONURST, 21, "ONURST");
		ms.encode (value.ONUXRST, 21, "ONUXRST");
		ms.encode (value.ONURMK, 51, "ONURMK");
		ms.encode (value.TFCA1, 11, "TFCA1");
		ms.encode (value.TFCA2, 11, "TFCA2");
		ms.encode (value.TFPR1, 5, "TFPR1");
		ms.encode (value.TFPR2, 5, "TFPR2");
		ms.encode (value.FICTMED1, 6, "FICTMED1");
		ms.encode (value.FICTMED2, 6, "FICTMED2");
		ms.encode (value.FICTEA1, 51, "FICTEA1");
		ms.encode (value.FICTEA2, 51, "FICTEA2");
		ms.encode (value.FICTYPE1, 6, "FICTYPE1");
		ms.encode (value.FICTYPE2, 6, "FICTYPE2");
		ms.encode (value.ABPRSVINFO, 76, "ABPRSVINFO");
		ms.encode (value.ABPRSVDAT, 9, "ABPRSVDAT");
		ms.encode (value.ABPRSVRMK, 51, "ABPRSVRMK");
		{ 
			ms.startSequence ("ASGBP", true);
			ms.encode (value.ASGBP.length, "_sequenceLength", true);
			ms.startArray ("ASGBP", true);
			{ 
				for (int __i0 = 0; __i0 < value.ASGBP.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_tMsg.encodeASGBP_t (ms, value.ASGBP[__i0], "ASGBP");
				} 
			}
			ms.endArray ("ASGBP", true);
			ms.endSequence ("ASGBP", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_tHelper.type(); 
	}
	public static byte [] toOctet (SEG_LOOP_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSEG_LOOP_t (ms, val, "SEG_LOOP_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SEG_LOOP_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSEG_LOOP_t (ms, "SEG_LOOP_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_LOOP_t();
		value.SEGNO = new String ();
		value.CA = new String ();
		value.PR = new String ();
		value.LSTAT = new String ();
		value.BP = new String ();
		value.OBP = new String ();
		value.LPORG = new String ();
		value.RLOE = new String ();
		value.RLOA = new String ();
		value.RLOC = new String ();
		value.RLC = new String ();
		value.LOTI = new String ();
		value.TEA = new String ();
		value.TP = new String ();
		value.COMM = new String ();
		value.UF = new String ();
		value.SC = new String ();
		value.TETE = new String ();
		value.CQ = new String ();
		value.DEF = new String ();
		value.DEFD = new String ();
		value.CTT = new String ();
		value.DEFL = new String ();
		value.LT = new String ();
		value.LNOP = new String ();
		value.SYSTP = new String ();
		value.PGSNO = new String ();
		value.FLDLTS = new String ();
		value.COLTS = new String ();
		value.CNST = new String ();
		value.ORIG = new String ();
		value.MPROV = new String ();
		value.MCLLI = new String ();
		value.MLOC = new String ();
		value.MCA = new String ();
		value.MPR = new String ();
		value.RLA = new String ();
		value.SDP = new String ();
		value.TSB = new String ();
		value.TPR = new String ();
		value.LMURMK = new String ();
		value.EWOID = new String ();
		value.EWODD = new String ();
		value.LSTID = new String ();
		value.SOLST = new String ();
		value.SOITM = new String ();
		value.SOLSTDD = new String ();
		value.RSVINFO = new String ();
		value.RSVDAT = new String ();
		value.RSVRMK = new String ();
		value.RSTTE = new String ();
		value.PERM = new String ();
		value.XRST = new String ();
		value.RMK0TE = new String ();
		value.RMK0PR = new String ();
		value.CQC = new String ();
		value.CDC = new String ();
		value.ASGBPR = new String ();
		value.ASBPSTAT = new String ();
		value.PGSTP = new String ();
		value.LTS = new String ();
		value.DLE = new String ();
		value.TSI = new String ();
		value.DLERMK = new String ();
		value.DLERST = new String ();
		value.DLEONU = new String ();
		value.ONURST = new String ();
		value.ONUXRST = new String ();
		value.ONURMK = new String ();
		value.TFCA1 = new String ();
		value.TFCA2 = new String ();
		value.TFPR1 = new String ();
		value.TFPR2 = new String ();
		value.FICTMED1 = new String ();
		value.FICTMED2 = new String ();
		value.FICTEA1 = new String ();
		value.FICTEA2 = new String ();
		value.FICTYPE1 = new String ();
		value.FICTYPE2 = new String ();
		value.ABPRSVINFO = new String ();
		value.ABPRSVDAT = new String ();
		value.ABPRSVRMK = new String ();
		int __seqLength = 0;
		value.ASGBP = new com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t[__seqLength];
		return value; 
	} 
}
