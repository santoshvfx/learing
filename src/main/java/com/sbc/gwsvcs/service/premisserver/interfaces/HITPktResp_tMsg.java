// $Id: HITPktResp_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HITPktResp_tMsg implements MMarshalObject { 
	public HITPktResp_t value;

	public HITPktResp_tMsg () {
	}
	public HITPktResp_tMsg (HITPktResp_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.HITPktResp_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.HITPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.HITPktResp_t();
	value.RTCD = new String ();
	value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHITPktResp_t (ms, tag); 
	}
	static public HITPktResp_t decodeHITPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		HITPktResp_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHITPktResp_t (ms, value, tag); 
	}
	static public void encodeHITPktResp_t (MMarshalStrategy ms, HITPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
	ms.endStruct (tag, true); 
}
public static HITPktResp_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeHITPktResp_t (ms, "HITPktResp_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.HITPktResp_tHelper.type(); 
}
public static byte [] toOctet (HITPktResp_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeHITPktResp_t (ms, val, "HITPktResp_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
