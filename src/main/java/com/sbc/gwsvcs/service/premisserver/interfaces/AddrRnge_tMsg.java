// $Id: AddrRnge_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddrRnge_tMsg implements MMarshalObject { 
	public AddrRnge_t value;

	public AddrRnge_tMsg () {
	}
	public AddrRnge_tMsg (AddrRnge_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_t();
value.LOW_HOUS_NBR_VALU_ID = new String ();
value.HI_HOUS_NBR_VALU_ID = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddrRnge_t (ms, tag); 
	}
	static public AddrRnge_t decodeAddrRnge_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddrRnge_t value = create();
		ms.startStruct (tag, false);
		value.LOW_HOUS_NBR_VALU_ID = ms.decodeOctetString (14, "LOW_HOUS_NBR_VALU_ID");
		value.HI_HOUS_NBR_VALU_ID = ms.decodeOctetString (14, "HI_HOUS_NBR_VALU_ID");
		value.RNGE_IND = ms.decodeChar ("RNGE_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddrRnge_t (ms, value, tag); 
	}
	static public void encodeAddrRnge_t (MMarshalStrategy ms, AddrRnge_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.LOW_HOUS_NBR_VALU_ID, 14, "LOW_HOUS_NBR_VALU_ID");
	ms.encode (value.HI_HOUS_NBR_VALU_ID, 14, "HI_HOUS_NBR_VALU_ID");
ms.encode (value.RNGE_IND, "RNGE_IND");
ms.endStruct (tag, true); 
}
public static AddrRnge_t fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeAddrRnge_t (ms, "AddrRnge_t"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.AddrRnge_tHelper.type(); 
}
public static byte [] toOctet (AddrRnge_t val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeAddrRnge_t (ms, val, "AddrRnge_t");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
