// $Id: ZipMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:18 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZipMenuItem_tMsg implements MMarshalObject { 
	public ZipMenuItem_t value;

	public ZipMenuItem_tMsg () {
	}
	public ZipMenuItem_tMsg (ZipMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_t();
	value.SAGA = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZipMenuItem_t (ms, tag); 
	}
	static public ZipMenuItem_t decodeZipMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZipMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.SAGA = ms.decodeOctetString (5, "SAGA");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZipMenuItem_t (ms, value, tag); 
	}
	static public void encodeZipMenuItem_t (MMarshalStrategy ms, ZipMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAGA, 5, "SAGA");
	ms.endStruct (tag, true); 
}
public static ZipMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeZipMenuItem_t (ms, "ZipMenuItem_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_tHelper.type(); 
}
public static byte [] toOctet (ZipMenuItem_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeZipMenuItem_t (ms, val, "ZipMenuItem_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
