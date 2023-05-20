// $Id: AppTnRsrvRespItem_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AppTnRsrvRespItem_tMsg implements MMarshalObject { 
	public AppTnRsrvRespItem_t value;

	public AppTnRsrvRespItem_tMsg () {
	}
	public AppTnRsrvRespItem_tMsg (AppTnRsrvRespItem_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_t();
		value.TnRsrvReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t();
		value.TnaFacActnLnItem = new com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAppTnRsrvRespItem_t (ms, tag); 
	}
	static public AppTnRsrvRespItem_t decodeAppTnRsrvRespItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AppTnRsrvRespItem_t value = create();
		ms.startStruct (tag, false);
		value.TnRsrvReq = com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tMsg.decodeTnRsrvReq_t (ms, "TnRsrvReq");
		value.TnaFacActnLnItem = com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tMsg.decodeTnaFacActnLnItem_t (ms, "TnaFacActnLnItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAppTnRsrvRespItem_t (ms, value, tag); 
	}
	static public void encodeAppTnRsrvRespItem_t (MMarshalStrategy ms, AppTnRsrvRespItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tMsg.encodeTnRsrvReq_t (ms, value.TnRsrvReq, "TnRsrvReq");
		com.sbc.gwsvcs.service.premisserver.interfaces.TnaFacActnLnItem_tMsg.encodeTnaFacActnLnItem_t (ms, value.TnaFacActnLnItem, "TnaFacActnLnItem");
		ms.endStruct (tag, true); 
	}
	public static AppTnRsrvRespItem_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAppTnRsrvRespItem_t (ms, "AppTnRsrvRespItem_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.AppTnRsrvRespItem_tHelper.type(); 
	}
	public static byte [] toOctet (AppTnRsrvRespItem_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAppTnRsrvRespItem_t (ms, val, "AppTnRsrvRespItem_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
