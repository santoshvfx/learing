// $Id: SagaMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SagaMenuItem_tMsg implements MMarshalObject { 
	public SagaMenuItem_t value;

	public SagaMenuItem_tMsg () {
	}
	public SagaMenuItem_tMsg (SagaMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_t();
	value.SAGA = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSagaMenuItem_t (ms, tag); 
	}
	static public SagaMenuItem_t decodeSagaMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SagaMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.SAGA = ms.decodeOctetString (5, "SAGA");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSagaMenuItem_t (ms, value, tag); 
	}
	static public void encodeSagaMenuItem_t (MMarshalStrategy ms, SagaMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAGA, 5, "SAGA");
	ms.endStruct (tag, true); 
}
public static SagaMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeSagaMenuItem_t (ms, "SagaMenuItem_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_tHelper.type(); 
}
public static byte [] toOctet (SagaMenuItem_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeSagaMenuItem_t (ms, val, "SagaMenuItem_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
