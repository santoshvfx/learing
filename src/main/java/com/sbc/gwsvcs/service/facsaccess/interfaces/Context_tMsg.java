package com.sbc.gwsvcs.service.facsaccess.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Context_tMsg implements MMarshalObject { 
	public String value;
	public Context_tMsg() { }
	public Context_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeContext_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeContext_t (ms, tag); 
	}
	static public void encodeContext_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 31, tag);
	}
	public static String decodeContext_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (31, tag);
		return value; 
	}
	public TypeCode getType () {
		return Context_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeContext_t(ms, val, "Context_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String fromOctet (byte [] val) throws MMarshalException {
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeContext_t (ms, "Context_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
