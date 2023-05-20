// $Id: AddrRngeMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddrRngeMenuProcStatus_tMsg implements MMarshalObject { 
	public AddrRngeMenuProcStatus_t value;

	public AddrRngeMenuProcStatus_tMsg () {
	}
	public AddrRngeMenuProcStatus_tMsg (AddrRngeMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.AddrRngeMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddrRngeMenuProcStatus_t (ms, tag); 
	}
	static public AddrRngeMenuProcStatus_t decodeAddrRngeMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddrRngeMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("AddrRngeMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.AddrRngeMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AddrRngeMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_tMsg.decodeAddrRngeMenuItem_t (ms, "AddrRngeMenu");
			} 
		ms.endSequence ("AddrRngeMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddrRngeMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeAddrRngeMenuProcStatus_t (MMarshalStrategy ms, AddrRngeMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("AddrRngeMenu", true);
		ms.encode (value.AddrRngeMenu.length, "m_length", true);
		for (int __i = 0; __i < value.AddrRngeMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_tMsg.encodeAddrRngeMenuItem_t (ms, value.AddrRngeMenu[__i], "AddrRngeMenu");
		}
		ms.endSequence ("AddrRngeMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static AddrRngeMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeAddrRngeMenuProcStatus_t (ms, "AddrRngeMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (AddrRngeMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeAddrRngeMenuProcStatus_t (ms, val, "AddrRngeMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
