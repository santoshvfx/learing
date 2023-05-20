// $Id: PremisTnMttResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnMttResp_tMsg implements MMarshalObject { 
	public PremisTnMttResp_t value;

	public PremisTnMttResp_tMsg () {
	}
	public PremisTnMttResp_tMsg (PremisTnMttResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnMttResp = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnMttResp_t (ms, tag); 
	}
	static public PremisTnMttResp_t decodePremisTnMttResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnMttResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnMttResp = com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_tMsg.decodeTNAMttResp_t (ms, "TnMttResp");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnMttResp_t (ms, value, tag); 
	}
	static public void encodePremisTnMttResp_t (MMarshalStrategy ms, PremisTnMttResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAMttResp_tMsg.encodeTNAMttResp_t (ms, value.TnMttResp, "TnMttResp");
		ms.endStruct (tag, true); 
	}
	public static PremisTnMttResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnMttResp_t (ms, "PremisTnMttResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnMttResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnMttResp_t (ms, val, "PremisTnMttResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
