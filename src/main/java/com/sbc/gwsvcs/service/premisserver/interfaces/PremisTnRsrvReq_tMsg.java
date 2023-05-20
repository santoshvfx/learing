// $Id: PremisTnRsrvReq_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnRsrvReq_tMsg implements MMarshalObject { 
	public PremisTnRsrvReq_t value;

	public PremisTnRsrvReq_tMsg () {
	}
	public PremisTnRsrvReq_tMsg (PremisTnRsrvReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvReq_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvReq_t();
	value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
	value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
	value.TnRsrvReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_t();
	value.HOST_NM = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnRsrvReq_t (ms, tag); 
	}
	static public PremisTnRsrvReq_t decodePremisTnRsrvReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnRsrvReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.TnRsrvReq = com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tMsg.decodeTnRsrvReq_t (ms, "TnRsrvReq");
		value.HOST_NM = ms.decodeOctetString (41, "HOST_NM");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnRsrvReq_t (ms, value, tag); 
	}
	static public void encodePremisTnRsrvReq_t (MMarshalStrategy ms, PremisTnRsrvReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.TnRsrvReq_tMsg.encodeTnRsrvReq_t (ms, value.TnRsrvReq, "TnRsrvReq");
		ms.encode (value.HOST_NM, 41, "HOST_NM");
	ms.endStruct (tag, true); 
}
public static PremisTnRsrvReq_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodePremisTnRsrvReq_t (ms, "PremisTnRsrvReq_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvReq_tHelper.type(); 
}
public static byte [] toOctet (PremisTnRsrvReq_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodePremisTnRsrvReq_t (ms, val, "PremisTnRsrvReq_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
