// $Id: AddrMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddrMenuProcStatus_tMsg implements MMarshalObject { 
	public AddrMenuProcStatus_t value;

	public AddrMenuProcStatus_tMsg () {
	}
	public AddrMenuProcStatus_tMsg (AddrMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.AddrMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddrMenuProcStatus_t (ms, tag); 
	}
	static public AddrMenuProcStatus_t decodeAddrMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddrMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("AddrMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.AddrMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AddrMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_tMsg.decodeAddrMenuItem_t (ms, "AddrMenu");
			} 
		ms.endSequence ("AddrMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddrMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeAddrMenuProcStatus_t (MMarshalStrategy ms, AddrMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("AddrMenu", true);
		ms.encode (value.AddrMenu.length, "m_length", true);
		for (int __i = 0; __i < value.AddrMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_tMsg.encodeAddrMenuItem_t (ms, value.AddrMenu[__i], "AddrMenu");
		}
		ms.endSequence ("AddrMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static AddrMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeAddrMenuProcStatus_t (ms, "AddrMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (AddrMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeAddrMenuProcStatus_t (ms, val, "AddrMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
