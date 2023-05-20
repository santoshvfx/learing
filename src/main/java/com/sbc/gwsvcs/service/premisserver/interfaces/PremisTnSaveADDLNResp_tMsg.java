// $Id: PremisTnSaveADDLNResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnSaveADDLNResp_tMsg implements MMarshalObject { 
	public PremisTnSaveADDLNResp_t value;

	public PremisTnSaveADDLNResp_tMsg () {
	}
	public PremisTnSaveADDLNResp_tMsg (PremisTnSaveADDLNResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveADDLNResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveADDLNResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveADDLNResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.TnSavePktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t();
		value.AppTnSaveADDLNRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnSaveADDLNResp_t (ms, tag); 
	}
	static public PremisTnSaveADDLNResp_t decodePremisTnSaveADDLNResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnSaveADDLNResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnSavePktResp = com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.decodeTNAPktResp_t (ms, "TnSavePktResp");
		value.AppTnSaveADDLNRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_tMsg.decodeAppTnSaveRespItem_t (ms, "AppTnSaveADDLNRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnSaveADDLNResp_t (ms, value, tag); 
	}
	static public void encodePremisTnSaveADDLNResp_t (MMarshalStrategy ms, PremisTnSaveADDLNResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tMsg.encodeTNAPktResp_t (ms, value.TnSavePktResp, "TnSavePktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_tMsg.encodeAppTnSaveRespItem_t (ms, value.AppTnSaveADDLNRespItem, "AppTnSaveADDLNRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisTnSaveADDLNResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisTnSaveADDLNResp_t (ms, "PremisTnSaveADDLNResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveADDLNResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisTnSaveADDLNResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisTnSaveADDLNResp_t (ms, val, "PremisTnSaveADDLNResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
