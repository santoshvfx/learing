// $Id: PremisTnStaResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnStaResp_tMsg implements MMarshalObject { 
	public PremisTnStaResp_t value;

	public PremisTnStaResp_tMsg () {
	}
	public PremisTnStaResp_tMsg (PremisTnStaResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnStaResp = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnStaResp_t (ms, tag); 
	}
	static public PremisTnStaResp_t decodePremisTnStaResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnStaResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnStaResp = com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_tMsg.decodeTNAStaResp_t (ms, "TnStaResp");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnStaResp_t (ms, value, tag); 
	}
	static public void encodePremisTnStaResp_t (MMarshalStrategy ms, PremisTnStaResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAStaResp_tMsg.encodeTNAStaResp_t (ms, value.TnStaResp, "TnStaResp");
		ms.endStruct (tag, true); 
	}
	public static PremisTnStaResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnStaResp_t (ms, "PremisTnStaResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnStaResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnStaResp_t (ms, val, "PremisTnStaResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
