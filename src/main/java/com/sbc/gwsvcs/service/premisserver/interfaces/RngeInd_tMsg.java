// $Id: RngeInd_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RngeInd_tMsg implements MMarshalObject { 
	public char value;
	
	public RngeInd_tMsg() { }
	public RngeInd_tMsg (char init) { value = init; }
	public static char create() {
		char ret = (char) 0;
		return ret;
		}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeRngeInd_t (ms, tag); 
	}
	public static char decodeRngeInd_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		char value;
		value = ms.decodeChar (tag);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRngeInd_t (ms, value, tag); 
	}
	static public void encodeRngeInd_t (MMarshalStrategy ms, char value, String tag) throws MMarshalException { 
		ms.encode (value, tag);
	}
	public static char fromOctet (byte [] val) throws MMarshalException {
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeRngeInd_t (ms, "RngeInd_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public TypeCode getType () {
		return RngeInd_tHelper.type();
	}
	public static byte[] toOctet (char val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeRngeInd_t(ms, val, "RngeInd_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
}
