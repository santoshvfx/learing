package com.sbc.gwsvcs.service.facsaccess.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Destination_tMsg implements MMarshalObject { 
	public String value;
	public Destination_tMsg() { }
	public Destination_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDestination_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeDestination_t (ms, tag); 
	}
	static public void encodeDestination_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 121, tag);
	}
	public static String decodeDestination_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (121, tag);
		return value; 
	}
	public TypeCode getType () {
		return Destination_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeDestination_t(ms, val, "Destination_t");
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
			return decodeDestination_t (ms, "Destination_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
