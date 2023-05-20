package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ECCABLE_Section_tMsg implements MMarshalObject { 
	public ECCABLE_Section_t value;

	public ECCABLE_Section_tMsg () {
	}
	public ECCABLE_Section_tMsg (ECCABLE_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeECCABLE_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeECCABLE_Section_t (ms, tag); 
	}
	static public ECCABLE_Section_t decodeECCABLE_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ECCABLE_Section_t value = create();
		ms.startStruct (tag, false);
		value.PRT = ms.decodeOctetString (5, "PRT");
		value.STEP = ms.decodeOctetString (8, "STEP");
		value.DREC = ms.decodeOctetString (2, "DREC");
		value.CA = ms.decodeOctetString (11, "CA");
		value.RMK = ms.decodeOctetString (51, "RMK");
		value.RULES = ms.decodeOctetString (14, "RULES");
		value.CNST = ms.decodeOctetString (5, "CNST");
		value.ORIG = ms.decodeOctetString (2, "ORIG");
		{ 
			ms.startSequence ("CARNG", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("CARNG", false);
			{ 
				value.CARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CARNG[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_tMsg.decodeCARNG_t (ms, "CARNG");
				} 
			}
			ms.endArray ("CARNG", false);
			ms.endSequence ("CARNG", false);
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
	static public void encodeECCABLE_Section_t (MMarshalStrategy ms, ECCABLE_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PRT, 5, "PRT");
		ms.encode (value.STEP, 8, "STEP");
		ms.encode (value.DREC, 2, "DREC");
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.RMK, 51, "RMK");
		ms.encode (value.RULES, 14, "RULES");
		ms.encode (value.CNST, 5, "CNST");
		ms.encode (value.ORIG, 2, "ORIG");
		{ 
			ms.startSequence ("CARNG", true);
			ms.encode (value.CARNG.length, "_sequenceLength", true);
			ms.startArray ("CARNG", true);
			{ 
				for (int __i0 = 0; __i0 < value.CARNG.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_tMsg.encodeCARNG_t (ms, value.CARNG[__i0], "CARNG");
				} 
			}
			ms.endArray ("CARNG", true);
			ms.endSequence ("CARNG", true);
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
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tHelper.type(); 
	}
	public static byte [] toOctet (ECCABLE_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeECCABLE_Section_t (ms, val, "ECCABLE_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ECCABLE_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeECCABLE_Section_t (ms, "ECCABLE_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t();
		value.PRT = new String ();
		value.STEP = new String ();
		value.DREC = new String ();
		value.CA = new String ();
		value.RMK = new String ();
		value.RULES = new String ();
		value.CNST = new String ();
		value.ORIG = new String ();
		int __seqLength = 0;
		value.CARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t[__seqLength];
		value.RTARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t[__seqLength];
		return value; 
	} 
}
