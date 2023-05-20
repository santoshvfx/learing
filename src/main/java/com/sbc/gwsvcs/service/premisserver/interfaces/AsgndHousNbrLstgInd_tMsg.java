// $Id: AsgndHousNbrLstgInd_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AsgndHousNbrLstgInd_tMsg implements MMarshalObject { 
	public char value;
	
	public AsgndHousNbrLstgInd_tMsg() { }
	public AsgndHousNbrLstgInd_tMsg (char init) { value = init; }
	public static char create() {
		char ret = (char) 0;
		return ret;
		}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeAsgndHousNbrLstgInd_t (ms, tag); 
	}
	public static char decodeAsgndHousNbrLstgInd_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		char value;
		value = ms.decodeChar (tag);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAsgndHousNbrLstgInd_t (ms, value, tag); 
	}
	static public void encodeAsgndHousNbrLstgInd_t (MMarshalStrategy ms, char value, String tag) throws MMarshalException { 
		ms.encode (value, tag);
	}
	public static char fromOctet (byte [] val) throws MMarshalException {
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeAsgndHousNbrLstgInd_t (ms, "AsgndHousNbrLstgInd_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public TypeCode getType () {
		return AsgndHousNbrLstgInd_tHelper.type();
	}
	public static byte[] toOctet (char val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAsgndHousNbrLstgInd_t(ms, val, "AsgndHousNbrLstgInd_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
}
