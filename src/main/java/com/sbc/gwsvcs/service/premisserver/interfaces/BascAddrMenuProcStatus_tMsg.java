// $Id: BascAddrMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class BascAddrMenuProcStatus_tMsg implements MMarshalObject { 
	public BascAddrMenuProcStatus_t value;

	public BascAddrMenuProcStatus_tMsg () {
	}
	public BascAddrMenuProcStatus_tMsg (BascAddrMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.BascAddrMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeBascAddrMenuProcStatus_t (ms, tag); 
	}
	static public BascAddrMenuProcStatus_t decodeBascAddrMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		BascAddrMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("BascAddrMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.BascAddrMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.BascAddrMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_tMsg.decodeBascAddrMenuItem_t (ms, "BascAddrMenu");
			} 
		ms.endSequence ("BascAddrMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeBascAddrMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeBascAddrMenuProcStatus_t (MMarshalStrategy ms, BascAddrMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("BascAddrMenu", true);
		ms.encode (value.BascAddrMenu.length, "m_length", true);
		for (int __i = 0; __i < value.BascAddrMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_tMsg.encodeBascAddrMenuItem_t (ms, value.BascAddrMenu[__i], "BascAddrMenu");
		}
		ms.endSequence ("BascAddrMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static BascAddrMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeBascAddrMenuProcStatus_t (ms, "BascAddrMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (BascAddrMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeBascAddrMenuProcStatus_t (ms, val, "BascAddrMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
