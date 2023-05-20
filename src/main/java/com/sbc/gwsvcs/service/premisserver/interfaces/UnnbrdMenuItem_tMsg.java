// $Id: UnnbrdMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdMenuItem_tMsg implements MMarshalObject { 
	public UnnbrdMenuItem_t value;

	public UnnbrdMenuItem_tMsg () {
	}
	public UnnbrdMenuItem_tMsg (UnnbrdMenuItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t();
value.StNm = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t();
value.CMTY_NM = new String ();
value.STATE_CD = new String ();
int __seqLength = 0;
value.AsgndHousNbrRnge = new com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t[__seqLength];
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnnbrdMenuItem_t (ms, tag); 
	}
	static public UnnbrdMenuItem_t decodeUnnbrdMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnnbrdMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.StNm = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.decodePrmStNm_t (ms, "StNm");
		value.CMTY_NM = ms.decodeOctetString (33, "CMTY_NM");
		value.STATE_CD = ms.decodeOctetString (3, "STATE_CD");
		{ 
			ms.startSequence ("AsgndHousNbrRnge", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.AsgndHousNbrRnge = new com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AsgndHousNbrRnge[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_tMsg.decodeAsgndHousNbrRngeItem_t (ms, "AsgndHousNbrRnge");
			} 
		ms.endSequence ("AsgndHousNbrRnge", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnnbrdMenuItem_t (ms, value, tag); 
	}
	static public void encodeUnnbrdMenuItem_t (MMarshalStrategy ms, UnnbrdMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tMsg.encodePrmStNm_t (ms, value.StNm, "StNm");
		ms.encode (value.CMTY_NM, 33, "CMTY_NM");
	ms.encode (value.STATE_CD, 3, "STATE_CD");
{ 
	ms.startSequence ("AsgndHousNbrRnge", true);
	ms.encode (value.AsgndHousNbrRnge.length, "m_length", true);
	for (int __i = 0; __i < value.AsgndHousNbrRnge.length; __i++) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_tMsg.encodeAsgndHousNbrRngeItem_t (ms, value.AsgndHousNbrRnge[__i], "AsgndHousNbrRnge");
	}
	ms.endSequence ("AsgndHousNbrRnge", true);
}
ms.endStruct (tag, true); 
}
public static UnnbrdMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeUnnbrdMenuItem_t (ms, "UnnbrdMenuItem_t"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_tHelper.type(); 
}
public static byte [] toOctet (UnnbrdMenuItem_t val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeUnnbrdMenuItem_t (ms, val, "UnnbrdMenuItem_t");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
