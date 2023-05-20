package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ADDR_LSTFN_tMsg implements MMarshalObject { 
	public ADDR_LSTFN_t value;

	public ADDR_LSTFN_tMsg () {
	}
	public ADDR_LSTFN_tMsg (ADDR_LSTFN_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeADDR_LSTFN_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeADDR_LSTFN_t (ms, tag); 
	}
	static public ADDR_LSTFN_t decodeADDR_LSTFN_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ADDR_LSTFN_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("BADR", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("BADR", false);
			{ 
				value.BADR = new com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.BADR[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tMsg.decodeBADR_t (ms, "BADR");
				} 
			}
			ms.endArray ("BADR", false);
			ms.endSequence ("BADR", false);
		}
		{ 
			ms.startSequence ("SUPL", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SUPL", false);
			{ 
				value.SUPL = new com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SUPL[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tMsg.decodeSUPL_t (ms, "SUPL");
				} 
			}
			ms.endArray ("SUPL", false);
			ms.endSequence ("SUPL", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeADDR_LSTFN_t (MMarshalStrategy ms, ADDR_LSTFN_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("BADR", true);
			ms.encode (value.BADR.length, "_sequenceLength", true);
			ms.startArray ("BADR", true);
			{ 
				for (int __i0 = 0; __i0 < value.BADR.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_tMsg.encodeBADR_t (ms, value.BADR[__i0], "BADR");
				} 
			}
			ms.endArray ("BADR", true);
			ms.endSequence ("BADR", true);
		}
		{ 
			ms.startSequence ("SUPL", true);
			ms.encode (value.SUPL.length, "_sequenceLength", true);
			ms.startArray ("SUPL", true);
			{ 
				for (int __i0 = 0; __i0 < value.SUPL.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tMsg.encodeSUPL_t (ms, value.SUPL[__i0], "SUPL");
				} 
			}
			ms.endArray ("SUPL", true);
			ms.endSequence ("SUPL", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_tHelper.type(); 
	}
	public static byte [] toOctet (ADDR_LSTFN_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeADDR_LSTFN_t (ms, val, "ADDR_LSTFN_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ADDR_LSTFN_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeADDR_LSTFN_t (ms, "ADDR_LSTFN_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ADDR_LSTFN_t();
		int __seqLength = 0;
		value.BADR = new com.sbc.gwsvcs.service.facsaccess.interfaces.BADR_t[__seqLength];
		value.SUPL = new com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t[__seqLength];
		return value; 
	} 
}
