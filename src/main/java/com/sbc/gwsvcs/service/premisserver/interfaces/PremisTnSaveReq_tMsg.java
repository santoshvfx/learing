// $Id: PremisTnSaveReq_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisTnSaveReq_tMsg implements MMarshalObject { 
	public PremisTnSaveReq_t value;

	public PremisTnSaveReq_tMsg () {
	}
	public PremisTnSaveReq_tMsg (PremisTnSaveReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveReq_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveReq_t();
	value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
	value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
	value.TnSaveReq = new com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_t();
	value.HOST_NM = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisTnSaveReq_t (ms, tag); 
	}
	static public PremisTnSaveReq_t decodePremisTnSaveReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisTnSaveReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.TnSaveReq = com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_tMsg.decodeTnSaveReq_t (ms, "TnSaveReq");
		value.HOST_NM = ms.decodeOctetString (41, "HOST_NM");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisTnSaveReq_t (ms, value, tag); 
	}
	static public void encodePremisTnSaveReq_t (MMarshalStrategy ms, PremisTnSaveReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_tMsg.encodeTnSaveReq_t (ms, value.TnSaveReq, "TnSaveReq");
		ms.encode (value.HOST_NM, 41, "HOST_NM");
	ms.endStruct (tag, true); 
}
public static PremisTnSaveReq_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodePremisTnSaveReq_t (ms, "PremisTnSaveReq_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveReq_tHelper.type(); 
}
public static byte [] toOctet (PremisTnSaveReq_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodePremisTnSaveReq_t (ms, val, "PremisTnSaveReq_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
