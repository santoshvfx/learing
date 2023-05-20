// $Id: PremisTnSaveResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnSaveResp_tMsg implements MMarshalObject { 
	public PremisTnSaveResp_t value;

	public PremisTnSaveResp_tMsg () {
	}
	public PremisTnSaveResp_tMsg (PremisTnSaveResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnSavePktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t();
		value.AppTnSaveRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnSaveResp_t (ms, tag); 
	}
	static public PremisTnSaveResp_t decodePremisTnSaveResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnSaveResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnSavePktResp = com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.decodeTNAPktResp_t (ms, "TnSavePktResp");
		value.AppTnSaveRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_tMsg.decodeAppTnSaveRespItem_t (ms, "AppTnSaveRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnSaveResp_t (ms, value, tag); 
	}
	static public void encodePremisTnSaveResp_t (MMarshalStrategy ms, PremisTnSaveResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.encodeTNAPktResp_t (ms, value.TnSavePktResp, "TnSavePktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_tMsg.encodeAppTnSaveRespItem_t (ms, value.AppTnSaveRespItem, "AppTnSaveRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisTnSaveResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnSaveResp_t (ms, "PremisTnSaveResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnSaveResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnSaveResp_t (ms, val, "PremisTnSaveResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
