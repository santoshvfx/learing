// $Id: SuppAddrMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SuppAddrMenuProcStatus_tMsg implements MMarshalObject { 
	public SuppAddrMenuProcStatus_t value;

	public SuppAddrMenuProcStatus_tMsg () {
	}
	public SuppAddrMenuProcStatus_tMsg (SuppAddrMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.SuppAddrMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSuppAddrMenuProcStatus_t (ms, tag); 
	}
	static public SuppAddrMenuProcStatus_t decodeSuppAddrMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SuppAddrMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("SuppAddrMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SuppAddrMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SuppAddrMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_tMsg.decodeSuppAddrMenuItem_t (ms, "SuppAddrMenu");
			} 
		ms.endSequence ("SuppAddrMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSuppAddrMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeSuppAddrMenuProcStatus_t (MMarshalStrategy ms, SuppAddrMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("SuppAddrMenu", true);
		ms.encode (value.SuppAddrMenu.length, "m_length", true);
		for (int __i = 0; __i < value.SuppAddrMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_tMsg.encodeSuppAddrMenuItem_t (ms, value.SuppAddrMenu[__i], "SuppAddrMenu");
		}
		ms.endSequence ("SuppAddrMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static SuppAddrMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeSuppAddrMenuProcStatus_t (ms, "SuppAddrMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (SuppAddrMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeSuppAddrMenuProcStatus_t (ms, val, "SuppAddrMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
