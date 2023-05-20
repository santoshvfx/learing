package com.sbc.gwsvcs.service.facsaccess.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TranID_tMsg implements MMarshalObject { 
	public String value;
	public TranID_tMsg() { }
	public TranID_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTranID_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeTranID_t (ms, tag); 
	}
	static public void encodeTranID_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 41, tag);
	}
	public static String decodeTranID_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (41, tag);
		return value; 
	}
	public TypeCode getType () {
		return TranID_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTranID_t(ms, val, "TranID_t");
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
			return decodeTranID_t (ms, "TranID_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
