// $Id: TandemDgSrvErrMsg.java,v 1.1 2002/09/29 03:53:01 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TandemDgSrvErrMsg implements MMarshalObject { 
	public TandemDgSrvErr value;

	public TandemDgSrvErrMsg () {
	}
	public TandemDgSrvErrMsg (TandemDgSrvErr initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr create () { 
	com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErr();
	value.advisoryMsg = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTandemDgSrvErr (ms, tag); 
	}
	static public TandemDgSrvErr decodeTandemDgSrvErr (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TandemDgSrvErr value = create();
		ms.startStruct (tag, false);
		value.advisoryMsg = ms.decodeOctetString (220, "advisoryMsg");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTandemDgSrvErr (ms, value, tag); 
	}
	static public void encodeTandemDgSrvErr (MMarshalStrategy ms, TandemDgSrvErr value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.advisoryMsg, 220, "advisoryMsg");
	ms.endStruct (tag, true); 
}
public static TandemDgSrvErr fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeTandemDgSrvErr (ms, "TandemDgSrvErr"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.asonservice.interfaces.TandemDgSrvErrHelper.type(); 
}
public static byte [] toOctet (TandemDgSrvErr val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeTandemDgSrvErr (ms, val, "TandemDgSrvErr");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
