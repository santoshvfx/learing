// $Id: PremisTnRetnReq_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnRetnReq_tMsg implements MMarshalObject { 
	public PremisTnRetnReq_t value;

	public PremisTnRetnReq_tMsg () {
	}
	public PremisTnRetnReq_tMsg (PremisTnRetnReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnReq_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnReq_t();
	value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
	value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
	value.TnRetnReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_t();
	value.HOST_NM = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnRetnReq_t (ms, tag); 
	}
	static public PremisTnRetnReq_t decodePremisTnRetnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnRetnReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.TnRetnReq = com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_tMsg.decodeTnRetnReq_t (ms, "TnRetnReq");
		value.HOST_NM = ms.decodeOctetString (41, "HOST_NM");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnRetnReq_t (ms, value, tag); 
	}
	static public void encodePremisTnRetnReq_t (MMarshalStrategy ms, PremisTnRetnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.TnRetnReq_tMsg.encodeTnRetnReq_t (ms, value.TnRetnReq, "TnRetnReq");
		ms.encode (value.HOST_NM, 41, "HOST_NM");
	ms.endStruct (tag, true); 
}
public static PremisTnRetnReq_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodePremisTnRetnReq_t (ms, "PremisTnRetnReq_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnReq_tHelper.type(); 
}
public static byte [] toOctet (PremisTnRetnReq_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodePremisTnRetnReq_t (ms, val, "PremisTnRetnReq_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
