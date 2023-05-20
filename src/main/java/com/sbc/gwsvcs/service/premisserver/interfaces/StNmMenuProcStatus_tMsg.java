// $Id: StNmMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class StNmMenuProcStatus_tMsg implements MMarshalObject { 
	public StNmMenuProcStatus_t value;

	public StNmMenuProcStatus_tMsg () {
	}
	public StNmMenuProcStatus_tMsg (StNmMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.StNmMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeStNmMenuProcStatus_t (ms, tag); 
	}
	static public StNmMenuProcStatus_t decodeStNmMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		StNmMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("StNmMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.StNmMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.StNmMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuItem_tMsg.decodeStNmMenuItem_t (ms, "StNmMenu");
			} 
		ms.endSequence ("StNmMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeStNmMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeStNmMenuProcStatus_t (MMarshalStrategy ms, StNmMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("StNmMenu", true);
		ms.encode (value.StNmMenu.length, "m_length", true);
		for (int __i = 0; __i < value.StNmMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuItem_tMsg.encodeStNmMenuItem_t (ms, value.StNmMenu[__i], "StNmMenu");
		}
		ms.endSequence ("StNmMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static StNmMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeStNmMenuProcStatus_t (ms, "StNmMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (StNmMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeStNmMenuProcStatus_t (ms, val, "StNmMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
