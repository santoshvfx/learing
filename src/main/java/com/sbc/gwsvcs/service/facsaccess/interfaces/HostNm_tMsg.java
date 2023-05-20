package com.sbc.gwsvcs.service.facsaccess.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class HostNm_tMsg implements MMarshalObject { 
	public String value;
	public HostNm_tMsg() { }
	public HostNm_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHostNm_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeHostNm_t (ms, tag); 
	}
	static public void encodeHostNm_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 41, tag);
	}
	public static String decodeHostNm_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (41, tag);
		return value; 
	}
	public TypeCode getType () {
		return HostNm_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHostNm_t(ms, val, "HostNm_t");
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
			return decodeHostNm_t (ms, "HostNm_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
