// $Id: PremisTnRsrvResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnRsrvResp_tMsg implements MMarshalObject { 
	public PremisTnRsrvResp_t value;

	public PremisTnRsrvResp_tMsg () {
	}
	public PremisTnRsrvResp_tMsg (PremisTnRsrvResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnRsrvPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t();
		value.AppTnRsrvRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnRsrvResp_t (ms, tag); 
	}
	static public PremisTnRsrvResp_t decodePremisTnRsrvResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnRsrvResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnRsrvPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.decodeTNAPktResp_t (ms, "TnRsrvPktResp");
		value.AppTnRsrvRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_tMsg.decodeAppTnRsrvRespItem_t (ms, "AppTnRsrvRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnRsrvResp_t (ms, value, tag); 
	}
	static public void encodePremisTnRsrvResp_t (MMarshalStrategy ms, PremisTnRsrvResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.encodeTNAPktResp_t (ms, value.TnRsrvPktResp, "TnRsrvPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_tMsg.encodeAppTnRsrvRespItem_t (ms, value.AppTnRsrvRespItem, "AppTnRsrvRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisTnRsrvResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnRsrvResp_t (ms, "PremisTnRsrvResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnRsrvResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnRsrvResp_t (ms, val, "PremisTnRsrvResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
