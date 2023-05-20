// $Id: PremisValdtAddrReq_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisValdtAddrReq_tMsg implements MMarshalObject { 
	public PremisValdtAddrReq_t value;

	public PremisValdtAddrReq_tMsg () {
	}
	public PremisValdtAddrReq_tMsg (PremisValdtAddrReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t();
	value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
	value.RapReq = new com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t();
	value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
	value.HOST_NM = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisValdtAddrReq_t (ms, tag); 
	}
	static public PremisValdtAddrReq_t decodePremisValdtAddrReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisValdtAddrReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.RapReq = com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_tMsg.decodeRapReq_t (ms, "RapReq");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.HOST_NM = ms.decodeOctetString (41, "HOST_NM");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisValdtAddrReq_t (ms, value, tag); 
	}
	static public void encodePremisValdtAddrReq_t (MMarshalStrategy ms, PremisValdtAddrReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_tMsg.encodeRapReq_t (ms, value.RapReq, "RapReq");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		ms.encode (value.HOST_NM, 41, "HOST_NM");
	ms.endStruct (tag, true); 
}
public static PremisValdtAddrReq_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodePremisValdtAddrReq_t (ms, "PremisValdtAddrReq_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_tHelper.type(); 
}
public static byte [] toOctet (PremisValdtAddrReq_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodePremisValdtAddrReq_t (ms, val, "PremisValdtAddrReq_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
