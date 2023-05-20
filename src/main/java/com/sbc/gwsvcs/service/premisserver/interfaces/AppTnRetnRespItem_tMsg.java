// $Id: AppTnRetnRespItem_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AppTnRetnRespItem_tMsg implements MMarshalObject { 
	public AppTnRetnRespItem_t value;

	public AppTnRetnRespItem_tMsg () {
	}
	public AppTnRetnRespItem_tMsg (AppTnRetnRespItem_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_t();
		value.TnRetnReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_t();
		value.TnaFacActnLnItem = new com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAppTnRetnRespItem_t (ms, tag); 
	}
	static public AppTnRetnRespItem_t decodeAppTnRetnRespItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AppTnRetnRespItem_t value = create();
		ms.startStruct (tag, false);
		value.TnRetnReq = com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_tMsg.decodeTnRetnReq_t (ms, "TnRetnReq");
		value.TnaFacActnLnItem = com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tMsg.decodeTnaFacActnLnItem_t (ms, "TnaFacActnLnItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAppTnRetnRespItem_t (ms, value, tag); 
	}
	static public void encodeAppTnRetnRespItem_t (MMarshalStrategy ms, AppTnRetnRespItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_tMsg.encodeTnRetnReq_t (ms, value.TnRetnReq, "TnRetnReq");
		com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tMsg.encodeTnaFacActnLnItem_t (ms, value.TnaFacActnLnItem, "TnaFacActnLnItem");
		ms.endStruct (tag, true); 
	}
	public static AppTnRetnRespItem_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAppTnRetnRespItem_t (ms, "AppTnRetnRespItem_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRetnRespItem_tHelper.type(); 
	}
	public static byte [] toOctet (AppTnRetnRespItem_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAppTnRetnRespItem_t (ms, val, "AppTnRetnRespItem_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
