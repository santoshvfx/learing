// $Id: ZipMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:18 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZipMenuProcStatus_tMsg implements MMarshalObject { 
	public ZipMenuProcStatus_t value;

	public ZipMenuProcStatus_tMsg () {
	}
	public ZipMenuProcStatus_tMsg (ZipMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.ZipMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_t[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZipMenuProcStatus_t (ms, tag); 
	}
	static public ZipMenuProcStatus_t decodeZipMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZipMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("ZipMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.ZipMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.ZipMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_tMsg.decodeZipMenuItem_t (ms, "ZipMenu");
			} 
		ms.endSequence ("ZipMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZipMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeZipMenuProcStatus_t (MMarshalStrategy ms, ZipMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("ZipMenu", true);
		ms.encode (value.ZipMenu.length, "m_length", true);
		for (int __i = 0; __i < value.ZipMenu.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuItem_tMsg.encodeZipMenuItem_t (ms, value.ZipMenu[__i], "ZipMenu");
		}
		ms.endSequence ("ZipMenu", true);
	}
	ms.endStruct (tag, true); 
}
public static ZipMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeZipMenuProcStatus_t (ms, "ZipMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (ZipMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeZipMenuProcStatus_t (ms, val, "ZipMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
