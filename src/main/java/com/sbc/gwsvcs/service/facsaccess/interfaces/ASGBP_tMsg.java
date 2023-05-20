package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASGBP_tMsg implements MMarshalObject { 
	public ASGBP_t value;

	public ASGBP_tMsg () {
	}
	public ASGBP_tMsg (ASGBP_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASGBP_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASGBP_t (ms, tag); 
	}
	static public ASGBP_t decodeASGBP_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASGBP_t value = create();
		ms.startStruct (tag, false);
		value.ABP = ms.decodeOctetString (5, "ABP");
		value.LTS = ms.decodeOctetString (5, "LTS");
		value.RST = ms.decodeOctetString (21, "RST");
		value.DEF = ms.decodeOctetString (4, "DEF");
		value.RMK = ms.decodeOctetString (51, "RMK");
		{ 
			ms.startSequence ("TF", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("TF", false);
			{ 
				value.TF = new com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.TF[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.TF_tMsg.decodeTF_t (ms, "TF");
				} 
			}
			ms.endArray ("TF", false);
			ms.endSequence ("TF", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeASGBP_t (MMarshalStrategy ms, ASGBP_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ABP, 5, "ABP");
		ms.encode (value.LTS, 5, "LTS");
		ms.encode (value.RST, 21, "RST");
		ms.encode (value.DEF, 4, "DEF");
		ms.encode (value.RMK, 51, "RMK");
		{ 
			ms.startSequence ("TF", true);
			ms.encode (value.TF.length, "_sequenceLength", true);
			ms.startArray ("TF", true);
			{ 
				for (int __i0 = 0; __i0 < value.TF.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.TF_tMsg.encodeTF_t (ms, value.TF[__i0], "TF");
				} 
			}
			ms.endArray ("TF", true);
			ms.endSequence ("TF", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_tHelper.type(); 
	}
	public static byte [] toOctet (ASGBP_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeASGBP_t (ms, val, "ASGBP_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ASGBP_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeASGBP_t (ms, "ASGBP_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t();
		value.ABP = new String ();
		value.LTS = new String ();
		value.RST = new String ();
		value.DEF = new String ();
		value.RMK = new String ();
		int __seqLength = 0;
		value.TF = new com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t[__seqLength];
		return value; 
	} 
}
