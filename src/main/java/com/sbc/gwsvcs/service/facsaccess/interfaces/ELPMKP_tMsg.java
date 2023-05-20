package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ELPMKP_tMsg implements MMarshalObject { 
	public ELPMKP_t value;

	public ELPMKP_tMsg () {
	}
	public ELPMKP_tMsg (ELPMKP_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeELPMKP_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeELPMKP_t (ms, tag); 
	}
	static public ELPMKP_t decodeELPMKP_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ELPMKP_t value = create();
		ms.startStruct (tag, false);
		value.STAT = ms.decodeOctetString (7, "STAT");
		value.CA = ms.decodeOctetString (11, "CA");
		value.LPR = ms.decodeOctetString (5, "LPR");
		value.HPR = ms.decodeOctetString (5, "HPR");
		value.GHCPTR = ms.decodeOctetString (44, "GHCPTR");
		value.LUNIT = ms.decodeOctetString (3, "LUNIT");
		value.COIL = ms.decodeOctetString (5, "COIL");
		value.NLDS = ms.decodeOctetString (3, "NLDS");
		value.DEFLDSP = ms.decodeOctetString (56, "DEFLDSP");
		value.COES = ms.decodeOctetString (9, "COES");
		value.LDSP1 = ms.decodeOctetString (60, "LDSP1");
		value.LDSP2 = ms.decodeOctetString (60, "LDSP2");
		{ 
			ms.startSequence ("BO", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("BO", false);
			{ 
				value.BO = new com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.BO[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.BO_tMsg.decodeBO_t (ms, "BO");
				} 
			}
			ms.endArray ("BO", false);
			ms.endSequence ("BO", false);
		}
		{ 
			ms.startSequence ("SOP", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SOP", false);
			{ 
				value.SOP = new com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SOP[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_tMsg.decodeSOP_t (ms, "SOP");
				} 
			}
			ms.endArray ("SOP", false);
			ms.endSequence ("SOP", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeELPMKP_t (MMarshalStrategy ms, ELPMKP_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.STAT, 7, "STAT");
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.LPR, 5, "LPR");
		ms.encode (value.HPR, 5, "HPR");
		ms.encode (value.GHCPTR, 44, "GHCPTR");
		ms.encode (value.LUNIT, 3, "LUNIT");
		ms.encode (value.COIL, 5, "COIL");
		ms.encode (value.NLDS, 3, "NLDS");
		ms.encode (value.DEFLDSP, 56, "DEFLDSP");
		ms.encode (value.COES, 9, "COES");
		ms.encode (value.LDSP1, 60, "LDSP1");
		ms.encode (value.LDSP2, 60, "LDSP2");
		{ 
			ms.startSequence ("BO", true);
			ms.encode (value.BO.length, "_sequenceLength", true);
			ms.startArray ("BO", true);
			{ 
				for (int __i0 = 0; __i0 < value.BO.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.BO_tMsg.encodeBO_t (ms, value.BO[__i0], "BO");
				} 
			}
			ms.endArray ("BO", true);
			ms.endSequence ("BO", true);
		}
		{ 
			ms.startSequence ("SOP", true);
			ms.encode (value.SOP.length, "_sequenceLength", true);
			ms.startArray ("SOP", true);
			{ 
				for (int __i0 = 0; __i0 < value.SOP.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_tMsg.encodeSOP_t (ms, value.SOP[__i0], "SOP");
				} 
			}
			ms.endArray ("SOP", true);
			ms.endSequence ("SOP", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tHelper.type(); 
	}
	public static byte [] toOctet (ELPMKP_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeELPMKP_t (ms, val, "ELPMKP_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ELPMKP_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeELPMKP_t (ms, "ELPMKP_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t();
		value.STAT = new String ();
		value.CA = new String ();
		value.LPR = new String ();
		value.HPR = new String ();
		value.GHCPTR = new String ();
		value.LUNIT = new String ();
		value.COIL = new String ();
		value.NLDS = new String ();
		value.DEFLDSP = new String ();
		value.COES = new String ();
		value.LDSP1 = new String ();
		value.LDSP2 = new String ();
		int __seqLength = 0;
		value.BO = new com.sbc.gwsvcs.service.facsaccess.interfaces.BO_t[__seqLength];
		value.SOP = new com.sbc.gwsvcs.service.facsaccess.interfaces.SOP_t[__seqLength];
		return value; 
	} 
}
