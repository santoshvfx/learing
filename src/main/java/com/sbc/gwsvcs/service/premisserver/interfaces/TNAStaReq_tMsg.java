// $Id: TNAStaReq_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TNAStaReq_tMsg implements MMarshalObject { 
	public TNAStaReq_t value;

	public TNAStaReq_tMsg () {
	}
	public TNAStaReq_tMsg (TNAStaReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t();
	value.TN = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t();
	value.WC_CD = new String ();
	value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTNAStaReq_t (ms, tag); 
	}
	static public TNAStaReq_t decodeTNAStaReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TNAStaReq_t value = create();
		ms.startStruct (tag, false);
		value.TN = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "TN");
		value.WC_CD = ms.decodeOctetString (9, "WC_CD");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTNAStaReq_t (ms, value, tag); 
	}
	static public void encodeTNAStaReq_t (MMarshalStrategy ms, TNAStaReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.TN, "TN");
		ms.encode (value.WC_CD, 9, "WC_CD");
	com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
	ms.endStruct (tag, true); 
}
public static TNAStaReq_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeTNAStaReq_t (ms, "TNAStaReq_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_tHelper.type(); 
}
public static byte [] toOctet (TNAStaReq_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeTNAStaReq_t (ms, val, "TNAStaReq_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
