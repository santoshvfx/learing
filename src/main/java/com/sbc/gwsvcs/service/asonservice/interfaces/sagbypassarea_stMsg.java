// $Id: sagbypassarea_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class sagbypassarea_stMsg implements MMarshalObject { 
	public sagbypassarea_st value;

	public sagbypassarea_stMsg () {
	}
	public sagbypassarea_stMsg (sagbypassarea_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st();
value.bpZipCode = new String ();
value.bpExchange = new String ();
value.bpCounty = new String ();
value.bpCommunity = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodesagbypassarea_st (ms, tag); 
	}
	static public sagbypassarea_st decodesagbypassarea_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		sagbypassarea_st value = create();
		ms.startStruct (tag, false);
		value.bpZipCode = ms.decodeOctetString (6, "bpZipCode");
		value.bpExchange = ms.decodeOctetString (5, "bpExchange");
		value.bpCounty = ms.decodeOctetString (5, "bpCounty");
		value.bpCommunity = ms.decodeOctetString (21, "bpCommunity");
		value.XDRalignFiller = ms.decodeChar ("XDRalignFiller");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodesagbypassarea_st (ms, value, tag); 
	}
	static public void encodesagbypassarea_st (MMarshalStrategy ms, sagbypassarea_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.bpZipCode, 6, "bpZipCode");
	ms.encode (value.bpExchange, 5, "bpExchange");
ms.encode (value.bpCounty, 5, "bpCounty");
ms.encode (value.bpCommunity, 21, "bpCommunity");
ms.encode (value.XDRalignFiller, "XDRalignFiller");
ms.endStruct (tag, true); 
}
public static sagbypassarea_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodesagbypassarea_st (ms, "sagbypassarea_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_stHelper.type(); 
}
public static byte [] toOctet (sagbypassarea_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodesagbypassarea_st (ms, val, "sagbypassarea_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
