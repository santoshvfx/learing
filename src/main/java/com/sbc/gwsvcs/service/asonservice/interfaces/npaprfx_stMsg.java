// $Id: npaprfx_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class npaprfx_stMsg implements MMarshalObject { 
	public npaprfx_st value;

	public npaprfx_stMsg () {
	}
	public npaprfx_stMsg (npaprfx_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_st();
value.npa = new String ();
value.nxx = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodenpaprfx_st (ms, tag); 
	}
	static public npaprfx_st decodenpaprfx_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		npaprfx_st value = create();
		ms.startStruct (tag, false);
		value.npa = ms.decodeOctetString (4, "npa");
		value.nxx = ms.decodeOctetString (4, "nxx");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodenpaprfx_st (ms, value, tag); 
	}
	static public void encodenpaprfx_st (MMarshalStrategy ms, npaprfx_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.npa, 4, "npa");
	ms.encode (value.nxx, 4, "nxx");
ms.endStruct (tag, true); 
}
public static npaprfx_st fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodenpaprfx_st (ms, "npaprfx_st"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.npaprfx_stHelper.type(); 
}
public static byte [] toOctet (npaprfx_st val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodenpaprfx_st (ms, val, "npaprfx_st");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
