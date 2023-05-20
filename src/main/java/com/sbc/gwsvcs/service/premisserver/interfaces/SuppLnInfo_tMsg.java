// $Id: SuppLnInfo_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SuppLnInfo_tMsg implements MMarshalObject { 
	public SuppLnInfo_t value;

	public SuppLnInfo_tMsg () {
	}
	public SuppLnInfo_tMsg (SuppLnInfo_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t();
		value.LtdLnInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t();
		value.NpaPrfxLn = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSuppLnInfo_t (ms, tag); 
	}
	static public SuppLnInfo_t decodeSuppLnInfo_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SuppLnInfo_t value = create();
		ms.startStruct (tag, false);
		value.LtdLnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tMsg.decodeLtdLnInfo_t (ms, "LtdLnInfo");
		value.NpaPrfxLn = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_tMsg.decodeNpaPrfxLn_PR_t (ms, "NpaPrfxLn");
		value.NON_PUB_IND = ms.decodeChar ("NON_PUB_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSuppLnInfo_t (ms, value, tag); 
	}
	static public void encodeSuppLnInfo_t (MMarshalStrategy ms, SuppLnInfo_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tMsg.encodeLtdLnInfo_t (ms, value.LtdLnInfo, "LtdLnInfo");
		com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_PR_tMsg.encodeNpaPrfxLn_PR_t (ms, value.NpaPrfxLn, "NpaPrfxLn");
		ms.encode (value.NON_PUB_IND, "NON_PUB_IND");
		ms.endStruct (tag, true); 
	}
	public static SuppLnInfo_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSuppLnInfo_t (ms, "SuppLnInfo_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tHelper.type(); 
	}
	public static byte [] toOctet (SuppLnInfo_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSuppLnInfo_t (ms, val, "SuppLnInfo_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
