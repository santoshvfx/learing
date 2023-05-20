// $Id: TnMatchMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnMatchMenuItem_tMsg implements MMarshalObject { 
	public TnMatchMenuItem_t value;

	public TnMatchMenuItem_tMsg () {
	}
	public TnMatchMenuItem_tMsg (TnMatchMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t();
value.BascAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t();
value.SuppAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t();
value.ZC = new String ();
value.SPL_INSTR = new String ();
value.LtdLnInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnMatchMenuItem_t (ms, tag); 
	}
	static public TnMatchMenuItem_t decodeTnMatchMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnMatchMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.BascAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tMsg.decodeBascAddrInfo_t (ms, "BascAddrInfo");
		value.SuppAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.decodeSuppAddrInfo_t (ms, "SuppAddrInfo");
		value.ZC = ms.decodeOctetString (6, "ZC");
		value.SPL_INSTR = ms.decodeOctetString (101, "SPL_INSTR");
		value.LtdLnInfo = com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tMsg.decodeLtdLnInfo_t (ms, "LtdLnInfo");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnMatchMenuItem_t (ms, value, tag); 
	}
	static public void encodeTnMatchMenuItem_t (MMarshalStrategy ms, TnMatchMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tMsg.encodeBascAddrInfo_t (ms, value.BascAddrInfo, "BascAddrInfo");
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.encodeSuppAddrInfo_t (ms, value.SuppAddrInfo, "SuppAddrInfo");
		ms.encode (value.ZC, 6, "ZC");
	ms.encode (value.SPL_INSTR, 101, "SPL_INSTR");
com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tMsg.encodeLtdLnInfo_t (ms, value.LtdLnInfo, "LtdLnInfo");
ms.endStruct (tag, true); 
}
public static TnMatchMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeTnMatchMenuItem_t (ms, "TnMatchMenuItem_t"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_tHelper.type(); 
}
public static byte [] toOctet (TnMatchMenuItem_t val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeTnMatchMenuItem_t (ms, val, "TnMatchMenuItem_t");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
