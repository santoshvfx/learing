// $Id: StAddrRngeMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class StAddrRngeMenuProcStatus_tMsg implements MMarshalObject { 
	public StAddrRngeMenuProcStatus_t value;

	public StAddrRngeMenuProcStatus_tMsg () {
	}
	public StAddrRngeMenuProcStatus_tMsg (StAddrRngeMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.StAddrRngeMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeStAddrRngeMenuProcStatus_t (ms, tag); 
	}
	static public StAddrRngeMenuProcStatus_t decodeStAddrRngeMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		StAddrRngeMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("StAddrRngeMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.StAddrRngeMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.StAddrRngeMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_tMsg.decodeStAddrRngeMenuItem_t (ms, "StAddrRngeMenu");
			} 
		ms.endSequence ("StAddrRngeMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeStAddrRngeMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeStAddrRngeMenuProcStatus_t (MMarshalStrategy ms, StAddrRngeMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("StAddrRngeMenu", true);
		ms.encode (value.StAddrRngeMenu.length, "m_length", true);
		for (int __i = 0; __i < value.StAddrRngeMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_tMsg.encodeStAddrRngeMenuItem_t (ms, value.StAddrRngeMenu[__i], "StAddrRngeMenu");
		}
		ms.endSequence ("StAddrRngeMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static StAddrRngeMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeStAddrRngeMenuProcStatus_t (ms, "StAddrRngeMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (StAddrRngeMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeStAddrRngeMenuProcStatus_t (ms, val, "StAddrRngeMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
