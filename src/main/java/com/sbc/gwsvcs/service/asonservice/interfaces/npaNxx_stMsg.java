// $Id: npaNxx_stMsg.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class npaNxx_stMsg implements MMarshalObject { 
	public npaNxx_st value;

	public npaNxx_stMsg () {
	}
	public npaNxx_stMsg (npaNxx_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_st();
value.npa = new String ();
value.nxx = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodenpaNxx_st (ms, tag); 
	}
	static public npaNxx_st decodenpaNxx_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		npaNxx_st value = create();
		ms.startStruct (tag, false);
		value.npa = ms.decodeOctetString (4, "npa");
		value.nxx = ms.decodeOctetString (4, "nxx");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodenpaNxx_st (ms, value, tag); 
	}
	static public void encodenpaNxx_st (MMarshalStrategy ms, npaNxx_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.npa, 4, "npa");
	ms.encode (value.nxx, 4, "nxx");
ms.endStruct (tag, true); 
}
public static npaNxx_st fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodenpaNxx_st (ms, "npaNxx_st"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.npaNxx_stHelper.type(); 
}
public static byte [] toOctet (npaNxx_st val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodenpaNxx_st (ms, val, "npaNxx_st");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
