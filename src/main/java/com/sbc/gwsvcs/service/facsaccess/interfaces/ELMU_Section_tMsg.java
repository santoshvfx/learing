package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ELMU_Section_tMsg implements MMarshalObject { 
	public ELMU_Section_t value;

	public ELMU_Section_tMsg () {
	}
	public ELMU_Section_tMsg (ELMU_Section_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeELMU_Section_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeELMU_Section_t (ms, tag); 
	}
	static public ELMU_Section_t decodeELMU_Section_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ELMU_Section_t value = create();
		ms.startStruct (tag, false);
		value.PRT = ms.decodeOctetString (5, "PRT");
		value.STEP = ms.decodeOctetString (8, "STEP");
		value.TEA = ms.decodeOctetString (51, "TEA");
		value.DREC = ms.decodeOctetString (2, "DREC");
		{ 
			ms.startSequence ("ELPMKP", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ELPMKP", false);
			{ 
				value.ELPMKP = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ELPMKP[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tMsg.decodeELPMKP_t (ms, "ELPMKP");
				} 
			}
			ms.endArray ("ELPMKP", false);
			ms.endSequence ("ELPMKP", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeELMU_Section_t (MMarshalStrategy ms, ELMU_Section_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PRT, 5, "PRT");
		ms.encode (value.STEP, 8, "STEP");
		ms.encode (value.TEA, 51, "TEA");
		ms.encode (value.DREC, 2, "DREC");
		{ 
			ms.startSequence ("ELPMKP", true);
			ms.encode (value.ELPMKP.length, "_sequenceLength", true);
			ms.startArray ("ELPMKP", true);
			{ 
				for (int __i0 = 0; __i0 < value.ELPMKP.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_tMsg.encodeELPMKP_t (ms, value.ELPMKP[__i0], "ELPMKP");
				} 
			}
			ms.endArray ("ELPMKP", true);
			ms.endSequence ("ELPMKP", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tHelper.type(); 
	}
	public static byte [] toOctet (ELMU_Section_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeELMU_Section_t (ms, val, "ELMU_Section_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ELMU_Section_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeELMU_Section_t (ms, "ELMU_Section_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t();
		value.PRT = new String ();
		value.STEP = new String ();
		value.TEA = new String ();
		value.DREC = new String ();
		int __seqLength = 0;
		value.ELPMKP = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELPMKP_t[__seqLength];
		return value; 
	} 
}
