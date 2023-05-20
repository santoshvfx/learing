// $Id: Addr_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Addr_tMsg implements MMarshalObject { 
	public Addr_t value;

	public Addr_tMsg () {
	}
	public Addr_tMsg (Addr_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t();
	value.BascAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t();
	value.SuppAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t();
	value.UnnbrdAddrIdent = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t();
	value.DESC_ADDR = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddr_t (ms, tag); 
	}
	static public Addr_t decodeAddr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Addr_t value = create();
		ms.startStruct (tag, false);
		value.BascAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tMsg.decodeBascAddrInfo_t (ms, "BascAddrInfo");
		value.SuppAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.decodeSuppAddrInfo_t (ms, "SuppAddrInfo");
		value.UnnbrdAddrIdent = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_tMsg.decodeUnnbrdAddrIdent_t (ms, "UnnbrdAddrIdent");
		value.DESC_ADDR = ms.decodeOctetString (51, "DESC_ADDR");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddr_t (ms, value, tag); 
	}
	static public void encodeAddr_t (MMarshalStrategy ms, Addr_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tMsg.encodeBascAddrInfo_t (ms, value.BascAddrInfo, "BascAddrInfo");
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.encodeSuppAddrInfo_t (ms, value.SuppAddrInfo, "SuppAddrInfo");
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_tMsg.encodeUnnbrdAddrIdent_t (ms, value.UnnbrdAddrIdent, "UnnbrdAddrIdent");
		ms.encode (value.DESC_ADDR, 51, "DESC_ADDR");
	ms.endStruct (tag, true); 
}
public static Addr_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeAddr_t (ms, "Addr_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.Addr_tHelper.type(); 
}
public static byte [] toOctet (Addr_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeAddr_t (ms, val, "Addr_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
