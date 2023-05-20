// $Id: SuppLnInfoItem_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SuppLnInfoItem_tMsg implements MMarshalObject { 
	public SuppLnInfoItem_t value;

	public SuppLnInfoItem_tMsg () {
	}
	public SuppLnInfoItem_tMsg (SuppLnInfoItem_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_t();
		value.SuppLnInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSuppLnInfoItem_t (ms, tag); 
	}
	static public SuppLnInfoItem_t decodeSuppLnInfoItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SuppLnInfoItem_t value = create();
		ms.startStruct (tag, false);
		value.SuppLnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tMsg.decodeSuppLnInfo_t (ms, "SuppLnInfo");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSuppLnInfoItem_t (ms, value, tag); 
	}
	static public void encodeSuppLnInfoItem_t (MMarshalStrategy ms, SuppLnInfoItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_tMsg.encodeSuppLnInfo_t (ms, value.SuppLnInfo, "SuppLnInfo");
		ms.endStruct (tag, true); 
	}
	public static SuppLnInfoItem_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSuppLnInfoItem_t (ms, "SuppLnInfoItem_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_tHelper.type(); 
	}
	public static byte [] toOctet (SuppLnInfoItem_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSuppLnInfoItem_t (ms, val, "SuppLnInfoItem_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
