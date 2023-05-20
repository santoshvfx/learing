package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ExceptionResp_tMsg implements MMarshalObject { 
	public ExceptionResp_t value;

	public ExceptionResp_tMsg () {
	}
	public ExceptionResp_tMsg (ExceptionResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeExceptionResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeExceptionResp_t (ms, tag); 
	}
	static public ExceptionResp_t decodeExceptionResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ExceptionResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.OutExcp = com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_tMsg.decodeGWException_t (ms, "OutExcp");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeExceptionResp_t (MMarshalStrategy ms, ExceptionResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_tMsg.encodeGWException_t (ms, value.OutExcp, "OutExcp");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ExceptionResp_tHelper.type(); 
	}
	public static byte [] toOctet (ExceptionResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeExceptionResp_t (ms, val, "ExceptionResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ExceptionResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeExceptionResp_t (ms, "ExceptionResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ExceptionResp_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ExceptionResp_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ExceptionResp_t();
		value.Header = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		value.OutExcp = new com.sbc.gwsvcs.service.facsaccess.interfaces.GWException_t();
		return value; 
	} 
}
