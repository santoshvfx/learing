// $Id: TnSaveReq_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnSaveReq_tMsg implements MMarshalObject { 
	public TnSaveReq_t value;

	public TnSaveReq_tMsg () {
	}
	public TnSaveReq_tMsg (TnSaveReq_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_t();
	value.SAGA = new String ();
	value.PrmAddr = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t();
	value.NpaPrfxLn = new com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnSaveReq_t (ms, tag); 
	}
	static public TnSaveReq_t decodeTnSaveReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnSaveReq_t value = create();
		ms.startStruct (tag, false);
		value.SAGA = ms.decodeOctetString (5, "SAGA");
		value.PrmAddr = com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.decodePrmAddr_t (ms, "PrmAddr");
		value.NpaPrfxLn = com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "NpaPrfxLn");
		value.LN_ID = ms.decodeChar ("LN_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnSaveReq_t (ms, value, tag); 
	}
	static public void encodeTnSaveReq_t (MMarshalStrategy ms, TnSaveReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAGA, 5, "SAGA");
	com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.encodePrmAddr_t (ms, value.PrmAddr, "PrmAddr");
	com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.NpaPrfxLn, "NpaPrfxLn");
	ms.encode (value.LN_ID, "LN_ID");
	ms.endStruct (tag, true); 
}
public static TnSaveReq_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeTnSaveReq_t (ms, "TnSaveReq_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.TnSaveReq_tHelper.type(); 
}
public static byte [] toOctet (TnSaveReq_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeTnSaveReq_t (ms, val, "TnSaveReq_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
