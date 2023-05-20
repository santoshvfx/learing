// $Id: LtdLnInfo_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class LtdLnInfo_tMsg implements MMarshalObject { 
	public LtdLnInfo_t value;

	public LtdLnInfo_tMsg () {
	}
	public LtdLnInfo_tMsg (LtdLnInfo_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_t();
value.LN_STS_ID = new String ();
value.LSTD_NM = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLtdLnInfo_t (ms, tag); 
	}
	static public LtdLnInfo_t decodeLtdLnInfo_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		LtdLnInfo_t value = create();
		ms.startStruct (tag, false);
		value.LN_STS_ID = ms.decodeOctetString (9, "LN_STS_ID");
		value.LSTD_NM = ms.decodeOctetString (61, "LSTD_NM");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLtdLnInfo_t (ms, value, tag); 
	}
	static public void encodeLtdLnInfo_t (MMarshalStrategy ms, LtdLnInfo_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.LN_STS_ID, 9, "LN_STS_ID");
	ms.encode (value.LSTD_NM, 61, "LSTD_NM");
ms.endStruct (tag, true); 
}
public static LtdLnInfo_t fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeLtdLnInfo_t (ms, "LtdLnInfo_t"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.LtdLnInfo_tHelper.type(); 
}
public static byte [] toOctet (LtdLnInfo_t val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeLtdLnInfo_t (ms, val, "LtdLnInfo_t");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
