// $Id: IECRmkDesc_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class IECRmkDesc_tMsg implements MMarshalObject { 
	public String value;
	public IECRmkDesc_tMsg() { }
	public IECRmkDesc_tMsg (String init) { value = init; }
public static String create() {
	return new String();
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeIECRmkDesc_t (ms, tag); 
	}
public static String decodeIECRmkDesc_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
	String value;
	value = ms.decodeOctetString (66, tag);
	return value; 
}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeIECRmkDesc_t (ms, value, tag); 
	}
	static public void encodeIECRmkDesc_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 66, tag);
}
public static String fromOctet (byte [] val) throws MMarshalException {
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean(null));
		return decodeIECRmkDesc_t (ms, "IECRmkDesc_t");
	} catch (MBufferException e) {
		throw new MMarshalException ("Buffer error", e);
	}
}
public TypeCode getType () {
	return IECRmkDesc_tHelper.type();
}
public static byte[] toOctet (String val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeIECRmkDesc_t(ms, val, "IECRmkDesc_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition());
	} catch (MBufferException e) {
		throw new MMarshalException ("Buffer error", e);
	}
}
}
