// $Id: TnCtgyCdItem_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnCtgyCdItem_tMsg implements MMarshalObject { 
	public TnCtgyCdItem_t value;

	public TnCtgyCdItem_tMsg () {
	}
	public TnCtgyCdItem_tMsg (TnCtgyCdItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_t();
	value.TN_CTGY_CD = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnCtgyCdItem_t (ms, tag); 
	}
	static public TnCtgyCdItem_t decodeTnCtgyCdItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnCtgyCdItem_t value = create();
		ms.startStruct (tag, false);
		value.TN_CTGY_CD = ms.decodeOctetString (13, "TN_CTGY_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnCtgyCdItem_t (ms, value, tag); 
	}
	static public void encodeTnCtgyCdItem_t (MMarshalStrategy ms, TnCtgyCdItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TN_CTGY_CD, 13, "TN_CTGY_CD");
	ms.endStruct (tag, true); 
}
public static TnCtgyCdItem_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeTnCtgyCdItem_t (ms, "TnCtgyCdItem_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_tHelper.type(); 
}
public static byte [] toOctet (TnCtgyCdItem_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeTnCtgyCdItem_t (ms, val, "TnCtgyCdItem_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
