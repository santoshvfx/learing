// $Id: RmkData_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RmkData_tMsg implements MMarshalObject { 
	public RmkData_t value;

	public RmkData_tMsg () {
	}
	public RmkData_tMsg (RmkData_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t();
	value.BASC_ADDR_RMK = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRmkData_t (ms, tag); 
	}
	static public RmkData_t decodeRmkData_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RmkData_t value = create();
		ms.startStruct (tag, false);
		value.BASC_ADDR_RMK = ms.decodeOctetString (73, "BASC_ADDR_RMK");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRmkData_t (ms, value, tag); 
	}
	static public void encodeRmkData_t (MMarshalStrategy ms, RmkData_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.BASC_ADDR_RMK, 73, "BASC_ADDR_RMK");
	ms.endStruct (tag, true); 
}
public static RmkData_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeRmkData_t (ms, "RmkData_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_tHelper.type(); 
}
public static byte [] toOctet (RmkData_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeRmkData_t (ms, val, "RmkData_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
