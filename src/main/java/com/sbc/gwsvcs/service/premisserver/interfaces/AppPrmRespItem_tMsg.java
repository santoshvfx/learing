// $Id: AppPrmRespItem_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AppPrmRespItem_tMsg implements MMarshalObject { 
	public AppPrmRespItem_t value;

	public AppPrmRespItem_tMsg () {
	}
	public AppPrmRespItem_tMsg (AppPrmRespItem_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		value.CntlData = new com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t();
		value.FacActnLn = new com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAppPrmRespItem_t (ms, tag); 
	}
	static public AppPrmRespItem_t decodeAppPrmRespItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AppPrmRespItem_t value = create();
		ms.startStruct (tag, false);
		value.CntlData = com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_tMsg.decodeRapReq_t (ms, "CntlData");
		value.FAC_ACTN_LN_CNT = ms.decodeShort ("FAC_ACTN_LN_CNT");
		value.FacActnLn = com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_tMsg.decodeFacActnLnItem_t (ms, "FacActnLn");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAppPrmRespItem_t (ms, value, tag); 
	}
	static public void encodeAppPrmRespItem_t (MMarshalStrategy ms, AppPrmRespItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_tMsg.encodeRapReq_t (ms, value.CntlData, "CntlData");
		ms.encode (value.FAC_ACTN_LN_CNT, "FAC_ACTN_LN_CNT");
		com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_tMsg.encodeFacActnLnItem_t (ms, value.FacActnLn, "FacActnLn");
		ms.endStruct (tag, true); 
	}
	public static AppPrmRespItem_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAppPrmRespItem_t (ms, "AppPrmRespItem_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tHelper.type(); 
	}
	public static byte [] toOctet (AppPrmRespItem_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAppPrmRespItem_t (ms, val, "AppPrmRespItem_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
