// $Id: PremisTnMttReq_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnMttReq_tMsg implements MMarshalObject { 
	public PremisTnMttReq_t value;

	public PremisTnMttReq_tMsg () {
	}
	public PremisTnMttReq_tMsg (PremisTnMttReq_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttReq_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttReq_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnMttReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnMttReq_t (ms, tag); 
	}
	static public PremisTnMttReq_t decodePremisTnMttReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnMttReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnMttReq = com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_tMsg.decodeTNAMttReq_t (ms, "TnMttReq");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnMttReq_t (ms, value, tag); 
	}
	static public void encodePremisTnMttReq_t (MMarshalStrategy ms, PremisTnMttReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttReq_tMsg.encodeTNAMttReq_t (ms, value.TnMttReq, "TnMttReq");
		ms.endStruct (tag, true); 
	}
	public static PremisTnMttReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnMttReq_t (ms, "PremisTnMttReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttReq_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnMttReq_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnMttReq_t (ms, val, "PremisTnMttReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
