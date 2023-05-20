package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TF_tMsg implements MMarshalObject { 
	public TF_t value;

	public TF_tMsg () {
	}
	public TF_tMsg (TF_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTF_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTF_t (ms, tag); 
	}
	static public TF_t decodeTF_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TF_t value = create();
		ms.startStruct (tag, false);
		value.TFN = ms.decodeOctetString (3, "TFN");
		value.TFCA = ms.decodeOctetString (11, "TFCA");
		value.TFPR = ms.decodeOctetString (5, "TFPR");
		value.TRC = ms.decodeOctetString (3, "TRC");
		value.TMED = ms.decodeOctetString (6, "TMED");
		value.FTE = ms.decodeOctetString (51, "FTE");
		value.FTP = ms.decodeOctetString (6, "FTP");
		value.FTEC = ms.decodeOctetString (12, "FTEC");
		{ 
			ms.startSequence ("OU", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("OU", false);
			{ 
				value.OU = new com.sbc.gwsvcs.service.facsaccess.interfaces.OU_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.OU[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.OU_tMsg.decodeOU_t (ms, "OU");
				} 
			}
			ms.endArray ("OU", false);
			ms.endSequence ("OU", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTF_t (MMarshalStrategy ms, TF_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TFN, 3, "TFN");
		ms.encode (value.TFCA, 11, "TFCA");
		ms.encode (value.TFPR, 5, "TFPR");
		ms.encode (value.TRC, 3, "TRC");
		ms.encode (value.TMED, 6, "TMED");
		ms.encode (value.FTE, 51, "FTE");
		ms.encode (value.FTP, 6, "FTP");
		ms.encode (value.FTEC, 12, "FTEC");
		{ 
			ms.startSequence ("OU", true);
			ms.encode (value.OU.length, "_sequenceLength", true);
			ms.startArray ("OU", true);
			{ 
				for (int __i0 = 0; __i0 < value.OU.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.OU_tMsg.encodeOU_t (ms, value.OU[__i0], "OU");
				} 
			}
			ms.endArray ("OU", true);
			ms.endSequence ("OU", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.TF_tHelper.type(); 
	}
	public static byte [] toOctet (TF_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTF_t (ms, val, "TF_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TF_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTF_t (ms, "TF_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.TF_t();
		value.TFN = new String ();
		value.TFCA = new String ();
		value.TFPR = new String ();
		value.TRC = new String ();
		value.TMED = new String ();
		value.FTE = new String ();
		value.FTP = new String ();
		value.FTEC = new String ();
		int __seqLength = 0;
		value.OU = new com.sbc.gwsvcs.service.facsaccess.interfaces.OU_t[__seqLength];
		return value; 
	} 
}
