// $Id: PremisTnStaReq_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnStaReq_tMsg implements MMarshalObject { 
	public PremisTnStaReq_t value;

	public PremisTnStaReq_tMsg () {
	}
	public PremisTnStaReq_tMsg (PremisTnStaReq_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnStaReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnStaReq_t (ms, tag); 
	}
	static public PremisTnStaReq_t decodePremisTnStaReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnStaReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnStaReq = com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_tMsg.decodeTNAStaReq_t (ms, "TnStaReq");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnStaReq_t (ms, value, tag); 
	}
	static public void encodePremisTnStaReq_t (MMarshalStrategy ms, PremisTnStaReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaReq_tMsg.encodeTNAStaReq_t (ms, value.TnStaReq, "TnStaReq");
		ms.endStruct (tag, true); 
	}
	public static PremisTnStaReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnStaReq_t (ms, "PremisTnStaReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnStaReq_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnStaReq_t (ms, val, "PremisTnStaReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
