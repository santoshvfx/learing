// $Id: AppTnSaveRespItem_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AppTnSaveRespItem_tMsg implements MMarshalObject { 
	public AppTnSaveRespItem_t value;

	public AppTnSaveRespItem_tMsg () {
	}
	public AppTnSaveRespItem_tMsg (AppTnSaveRespItem_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_t();
		value.TnSaveReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_t();
		value.TnaFacActnLnItem = new com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAppTnSaveRespItem_t (ms, tag); 
	}
	static public AppTnSaveRespItem_t decodeAppTnSaveRespItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AppTnSaveRespItem_t value = create();
		ms.startStruct (tag, false);
		value.TnSaveReq = com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_tMsg.decodeTnSaveReq_t (ms, "TnSaveReq");
		value.TnaFacActnLnItem = com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tMsg.decodeTnaFacActnLnItem_t (ms, "TnaFacActnLnItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAppTnSaveRespItem_t (ms, value, tag); 
	}
	static public void encodeAppTnSaveRespItem_t (MMarshalStrategy ms, AppTnSaveRespItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_tMsg.encodeTnSaveReq_t (ms, value.TnSaveReq, "TnSaveReq");
		com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tMsg.encodeTnaFacActnLnItem_t (ms, value.TnaFacActnLnItem, "TnaFacActnLnItem");
		ms.endStruct (tag, true); 
	}
	public static AppTnSaveRespItem_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAppTnSaveRespItem_t (ms, "AppTnSaveRespItem_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.AppTnSaveRespItem_tHelper.type(); 
	}
	public static byte [] toOctet (AppTnSaveRespItem_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAppTnSaveRespItem_t (ms, val, "AppTnSaveRespItem_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
