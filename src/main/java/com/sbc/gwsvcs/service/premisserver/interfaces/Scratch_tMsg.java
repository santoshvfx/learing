// $Id: Scratch_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Scratch_tMsg implements MMarshalObject { 
	public Scratch_t value;

	public Scratch_tMsg () {
	}
	public Scratch_tMsg (Scratch_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		int __seqLength = 0;
		value.SCRATCH = new char[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeScratch_t (ms, tag); 
	}
	static public Scratch_t decodeScratch_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Scratch_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("SCRATCH", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SCRATCH = new char[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SCRATCH[__i] = ms.decodeChar ("SCRATCH");
			} 
		ms.endSequence ("SCRATCH", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeScratch_t (ms, value, tag); 
	}
	static public void encodeScratch_t (MMarshalStrategy ms, Scratch_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("SCRATCH", true);
			ms.encode (value.SCRATCH.length, "m_length", true);
			for (int __i = 0; __i < value.SCRATCH.length; __i++) { 
				ms.encode (value.SCRATCH[__i], "SCRATCH");
			}
			ms.endSequence ("SCRATCH", true);
		}
		ms.endStruct (tag, true); 
	}
	public static Scratch_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeScratch_t (ms, "Scratch_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tHelper.type(); 
	}
	public static byte [] toOctet (Scratch_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeScratch_t (ms, val, "Scratch_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
