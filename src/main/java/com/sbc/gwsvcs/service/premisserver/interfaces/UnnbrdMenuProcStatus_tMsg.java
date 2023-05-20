// $Id: UnnbrdMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdMenuProcStatus_tMsg implements MMarshalObject { 
	public UnnbrdMenuProcStatus_t value;

	public UnnbrdMenuProcStatus_tMsg () {
	}
	public UnnbrdMenuProcStatus_tMsg (UnnbrdMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.UnnbrdMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnnbrdMenuProcStatus_t (ms, tag); 
	}
	static public UnnbrdMenuProcStatus_t decodeUnnbrdMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnnbrdMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("UnnbrdMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.UnnbrdMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.UnnbrdMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_tMsg.decodeUnnbrdMenuItem_t (ms, "UnnbrdMenu");
			} 
		ms.endSequence ("UnnbrdMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnnbrdMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeUnnbrdMenuProcStatus_t (MMarshalStrategy ms, UnnbrdMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("UnnbrdMenu", true);
		ms.encode (value.UnnbrdMenu.length, "m_length", true);
		for (int __i = 0; __i < value.UnnbrdMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_tMsg.encodeUnnbrdMenuItem_t (ms, value.UnnbrdMenu[__i], "UnnbrdMenu");
		}
		ms.endSequence ("UnnbrdMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static UnnbrdMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeUnnbrdMenuProcStatus_t (ms, "UnnbrdMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (UnnbrdMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeUnnbrdMenuProcStatus_t (ms, val, "UnnbrdMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
