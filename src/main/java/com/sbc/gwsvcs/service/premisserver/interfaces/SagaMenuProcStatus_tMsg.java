// $Id: SagaMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SagaMenuProcStatus_tMsg implements MMarshalObject { 
	public SagaMenuProcStatus_t value;

	public SagaMenuProcStatus_tMsg () {
	}
	public SagaMenuProcStatus_tMsg (SagaMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.SagaMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSagaMenuProcStatus_t (ms, tag); 
	}
	static public SagaMenuProcStatus_t decodeSagaMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SagaMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("SagaMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SagaMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SagaMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_tMsg.decodeSagaMenuItem_t (ms, "SagaMenu");
			} 
		ms.endSequence ("SagaMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSagaMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeSagaMenuProcStatus_t (MMarshalStrategy ms, SagaMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("SagaMenu", true);
		ms.encode (value.SagaMenu.length, "m_length", true);
		for (int __i = 0; __i < value.SagaMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_tMsg.encodeSagaMenuItem_t (ms, value.SagaMenu[__i], "SagaMenu");
		}
		ms.endSequence ("SagaMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static SagaMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeSagaMenuProcStatus_t (ms, "SagaMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (SagaMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeSagaMenuProcStatus_t (ms, val, "SagaMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
