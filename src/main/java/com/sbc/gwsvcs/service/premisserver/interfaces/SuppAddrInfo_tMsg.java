// $Id: SuppAddrInfo_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SuppAddrInfo_tMsg implements MMarshalObject { 
	public SuppAddrInfo_t value;

	public SuppAddrInfo_tMsg () {
	}
	public SuppAddrInfo_tMsg (SuppAddrInfo_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t();
value.STRUCT_ID = new String ();
value.STRUCT_TYPE = new String ();
value.ELEV_ID = new String ();
value.ELEV_TYPE = new String ();
value.UNIT_ID = new String ();
value.UNIT_TYPE = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSuppAddrInfo_t (ms, tag); 
	}
	static public SuppAddrInfo_t decodeSuppAddrInfo_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SuppAddrInfo_t value = create();
		ms.startStruct (tag, false);
		value.STRUCT_ID = ms.decodeOctetString (11, "STRUCT_ID");
		value.STRUCT_TYPE = ms.decodeOctetString (5, "STRUCT_TYPE");
		value.ELEV_ID = ms.decodeOctetString (11, "ELEV_ID");
		value.ELEV_TYPE = ms.decodeOctetString (4, "ELEV_TYPE");
		value.UNIT_ID = ms.decodeOctetString (11, "UNIT_ID");
		value.UNIT_TYPE = ms.decodeOctetString (5, "UNIT_TYPE");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSuppAddrInfo_t (ms, value, tag); 
	}
	static public void encodeSuppAddrInfo_t (MMarshalStrategy ms, SuppAddrInfo_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.STRUCT_ID, 11, "STRUCT_ID");
	ms.encode (value.STRUCT_TYPE, 5, "STRUCT_TYPE");
ms.encode (value.ELEV_ID, 11, "ELEV_ID");
ms.encode (value.ELEV_TYPE, 4, "ELEV_TYPE");
ms.encode (value.UNIT_ID, 11, "UNIT_ID");
ms.encode (value.UNIT_TYPE, 5, "UNIT_TYPE");
ms.endStruct (tag, true); 
}
public static SuppAddrInfo_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeSuppAddrInfo_t (ms, "SuppAddrInfo_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tHelper.type(); 
}
public static byte [] toOctet (SuppAddrInfo_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeSuppAddrInfo_t (ms, val, "SuppAddrInfo_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
