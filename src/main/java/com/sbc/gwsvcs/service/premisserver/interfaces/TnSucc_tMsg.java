// $Id: TnSucc_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnSucc_tMsg implements MMarshalObject { 
	public TnSucc_t value;

	public TnSucc_tMsg () {
	}
	public TnSucc_tMsg (TnSucc_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_t();
		value.NpaPrfxLn = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnSucc_t (ms, tag); 
	}
	static public TnSucc_t decodeTnSucc_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnSucc_t value = create();
		ms.startStruct (tag, false);
		value.NpaPrfxLn = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "NpaPrfxLn");
		value.SUCC_CD = ms.decodeChar ("SUCC_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnSucc_t (ms, value, tag); 
	}
	static public void encodeTnSucc_t (MMarshalStrategy ms, TnSucc_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.NpaPrfxLn, "NpaPrfxLn");
		ms.encode (value.SUCC_CD, "SUCC_CD");
		ms.endStruct (tag, true); 
	}
	public static TnSucc_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTnSucc_t (ms, "TnSucc_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.TnSucc_tHelper.type(); 
	}
	public static byte [] toOctet (TnSucc_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTnSucc_t (ms, val, "TnSucc_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
