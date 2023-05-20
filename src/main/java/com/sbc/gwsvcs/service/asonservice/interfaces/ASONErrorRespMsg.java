// $Id: ASONErrorRespMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONErrorRespMsg implements MMarshalObject { 
	public ASONErrorResp value;

	public ASONErrorRespMsg () {
	}
	public ASONErrorRespMsg (ASONErrorResp initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorResp();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.advisoryMsg = new String ();
value.sendEndString = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONErrorResp (ms, tag); 
	}
	static public ASONErrorResp decodeASONErrorResp (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONErrorResp value = create();
		ms.startStruct (tag, false);
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.decodetaginformation_st (ms, "tagInformation");
		value.replyCode = ms.decodeShort ("replyCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.sendEndString = ms.decodeOctetString (4, "sendEndString");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONErrorResp (ms, value, tag); 
	}
	static public void encodeASONErrorResp (MMarshalStrategy ms, ASONErrorResp value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.encodetaginformation_st (ms, value.tagInformation, "tagInformation");
		ms.encode (value.replyCode, "replyCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.sendEndString, 4, "sendEndString");
ms.endStruct (tag, true); 
}
public static ASONErrorResp fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeASONErrorResp (ms, "ASONErrorResp"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONErrorRespHelper.type(); 
}
public static byte [] toOctet (ASONErrorResp val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeASONErrorResp (ms, val, "ASONErrorResp");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
