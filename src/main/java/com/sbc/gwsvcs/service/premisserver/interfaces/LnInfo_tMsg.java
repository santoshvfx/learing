// $Id: LnInfo_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class LnInfo_tMsg implements MMarshalObject { 
	public LnInfo_t value;

	public LnInfo_tMsg () {
	}
	public LnInfo_tMsg (LnInfo_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t();
		value.SuppLnInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLnInfo_t (ms, tag); 
	}
	static public LnInfo_t decodeLnInfo_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		LnInfo_t value = create();
		ms.startStruct (tag, false);
		value.LN_ID = ms.decodeChar ("LN_ID");
		value.SuppLnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tMsg.decodeSuppLnInfo_t (ms, "SuppLnInfo");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLnInfo_t (ms, value, tag); 
	}
	static public void encodeLnInfo_t (MMarshalStrategy ms, LnInfo_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.LN_ID, "LN_ID");
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tMsg.encodeSuppLnInfo_t (ms, value.SuppLnInfo, "SuppLnInfo");
		ms.endStruct (tag, true); 
	}
	public static LnInfo_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeLnInfo_t (ms, "LnInfo_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_tHelper.type(); 
	}
	public static byte [] toOctet (LnInfo_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeLnInfo_t (ms, val, "LnInfo_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
