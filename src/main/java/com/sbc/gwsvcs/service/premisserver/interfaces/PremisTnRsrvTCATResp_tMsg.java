// $Id: PremisTnRsrvTCATResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnRsrvTCATResp_tMsg implements MMarshalObject { 
	public PremisTnRsrvTCATResp_t value;

	public PremisTnRsrvTCATResp_tMsg () {
	}
	public PremisTnRsrvTCATResp_tMsg (PremisTnRsrvTCATResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvTCATResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvTCATResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvTCATResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnRsrvTCATPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t();
		value.AppTnRsrvRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnRsrvTCATResp_t (ms, tag); 
	}
	static public PremisTnRsrvTCATResp_t decodePremisTnRsrvTCATResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnRsrvTCATResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnRsrvTCATPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.decodeTNAPktResp_t (ms, "TnRsrvTCATPktResp");
		value.AppTnRsrvRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_tMsg.decodeAppTnRsrvRespItem_t (ms, "AppTnRsrvRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnRsrvTCATResp_t (ms, value, tag); 
	}
	static public void encodePremisTnRsrvTCATResp_t (MMarshalStrategy ms, PremisTnRsrvTCATResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.encodeTNAPktResp_t (ms, value.TnRsrvTCATPktResp, "TnRsrvTCATPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_tMsg.encodeAppTnRsrvRespItem_t (ms, value.AppTnRsrvRespItem, "AppTnRsrvRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisTnRsrvTCATResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnRsrvTCATResp_t (ms, "PremisTnRsrvTCATResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvTCATResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnRsrvTCATResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnRsrvTCATResp_t (ms, val, "PremisTnRsrvTCATResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
