// $Id: DescNmMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DescNmMenuProcStatus_tMsg implements MMarshalObject { 
	public DescNmMenuProcStatus_t value;

	public DescNmMenuProcStatus_tMsg () {
	}
	public DescNmMenuProcStatus_tMsg (DescNmMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.DescNmMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDescNmMenuProcStatus_t (ms, tag); 
	}
	static public DescNmMenuProcStatus_t decodeDescNmMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DescNmMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("DescNmMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.DescNmMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.DescNmMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuItem_tMsg.decodeDescNmMenuItem_t (ms, "DescNmMenu");
			} 
		ms.endSequence ("DescNmMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDescNmMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeDescNmMenuProcStatus_t (MMarshalStrategy ms, DescNmMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("DescNmMenu", true);
		ms.encode (value.DescNmMenu.length, "m_length", true);
		for (int __i = 0; __i < value.DescNmMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuItem_tMsg.encodeDescNmMenuItem_t (ms, value.DescNmMenu[__i], "DescNmMenu");
		}
		ms.endSequence ("DescNmMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static DescNmMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeDescNmMenuProcStatus_t (ms, "DescNmMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (DescNmMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeDescNmMenuProcStatus_t (ms, val, "DescNmMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
