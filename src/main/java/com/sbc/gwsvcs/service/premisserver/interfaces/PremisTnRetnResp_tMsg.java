// $Id: PremisTnRetnResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnRetnResp_tMsg implements MMarshalObject { 
	public PremisTnRetnResp_t value;

	public PremisTnRetnResp_tMsg () {
	}
	public PremisTnRetnResp_tMsg (PremisTnRetnResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnRetnPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t();
		value.AppTnRetnRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnRetnResp_t (ms, tag); 
	}
	static public PremisTnRetnResp_t decodePremisTnRetnResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnRetnResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnRetnPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.decodeTNAPktResp_t (ms, "TnRetnPktResp");
		value.AppTnRetnRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_tMsg.decodeAppTnRetnRespItem_t (ms, "AppTnRetnRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnRetnResp_t (ms, value, tag); 
	}
	static public void encodePremisTnRetnResp_t (MMarshalStrategy ms, PremisTnRetnResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.encodeTNAPktResp_t (ms, value.TnRetnPktResp, "TnRetnPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_tMsg.encodeAppTnRetnRespItem_t (ms, value.AppTnRetnRespItem, "AppTnRetnRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisTnRetnResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnRetnResp_t (ms, "PremisTnRetnResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnRetnResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnRetnResp_t (ms, val, "PremisTnRetnResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
