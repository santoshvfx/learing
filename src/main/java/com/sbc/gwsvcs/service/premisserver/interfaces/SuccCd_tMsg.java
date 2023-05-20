// $Id: SuccCd_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SuccCd_tMsg implements MMarshalObject { 
	public char value;
	
	public SuccCd_tMsg() { }
	public SuccCd_tMsg (char init) { value = init; }
	public static char create() {
		char ret = (char) 0;
		return ret;
		}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeSuccCd_t (ms, tag); 
	}
	public static char decodeSuccCd_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		char value;
		value = ms.decodeChar (tag);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSuccCd_t (ms, value, tag); 
	}
	static public void encodeSuccCd_t (MMarshalStrategy ms, char value, String tag) throws MMarshalException { 
		ms.encode (value, tag);
	}
	public static char fromOctet (byte [] val) throws MMarshalException {
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeSuccCd_t (ms, "SuccCd_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public TypeCode getType () {
		return SuccCd_tHelper.type();
	}
	public static byte[] toOctet (char val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSuccCd_t(ms, val, "SuccCd_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
}
