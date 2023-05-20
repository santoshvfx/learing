package com.sbc.gwsvcs.service.facsaccess.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Tx1024_tMsg implements MMarshalObject { 
	public String value;
	public Tx1024_tMsg() { }
	public Tx1024_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTx1024_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeTx1024_t (ms, tag); 
	}
	static public void encodeTx1024_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 1025, tag);
	}
	public static String decodeTx1024_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (1025, tag);
		return value; 
	}
	public TypeCode getType () {
		return Tx1024_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTx1024_t(ms, val, "Tx1024_t");
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
			return decodeTx1024_t (ms, "Tx1024_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
