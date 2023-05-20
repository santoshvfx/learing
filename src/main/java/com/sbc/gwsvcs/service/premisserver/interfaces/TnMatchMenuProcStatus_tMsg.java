// $Id: TnMatchMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnMatchMenuProcStatus_tMsg implements MMarshalObject { 
	public TnMatchMenuProcStatus_t value;

	public TnMatchMenuProcStatus_tMsg () {
	}
	public TnMatchMenuProcStatus_tMsg (TnMatchMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.TnMatchMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnMatchMenuProcStatus_t (ms, tag); 
	}
	static public TnMatchMenuProcStatus_t decodeTnMatchMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnMatchMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("TnMatchMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.TnMatchMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.TnMatchMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_tMsg.decodeTnMatchMenuItem_t (ms, "TnMatchMenu");
			} 
		ms.endSequence ("TnMatchMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnMatchMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeTnMatchMenuProcStatus_t (MMarshalStrategy ms, TnMatchMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("TnMatchMenu", true);
		ms.encode (value.TnMatchMenu.length, "m_length", true);
		for (int __i = 0; __i < value.TnMatchMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_tMsg.encodeTnMatchMenuItem_t (ms, value.TnMatchMenu[__i], "TnMatchMenu");
		}
		ms.endSequence ("TnMatchMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static TnMatchMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeTnMatchMenuProcStatus_t (ms, "TnMatchMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (TnMatchMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeTnMatchMenuProcStatus_t (ms, val, "TnMatchMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
