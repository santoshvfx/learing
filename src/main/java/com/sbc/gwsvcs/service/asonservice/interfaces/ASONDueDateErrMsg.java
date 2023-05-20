// $Id: ASONDueDateErrMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONDueDateErrMsg implements MMarshalObject { 
	public ASONDueDateErr value;

	public ASONDueDateErrMsg () {
	}
	public ASONDueDateErrMsg (ASONDueDateErr initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateErr create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateErr();
value.advisoryMsg = new String ();
value.cmdName = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONDueDateErr (ms, tag); 
	}
	static public ASONDueDateErr decodeASONDueDateErr (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONDueDateErr value = create();
		ms.startStruct (tag, false);
		value.replyCode = ms.decodeShort ("replyCode");
		value.advisoryMsg = ms.decodeOctetString (36, "advisoryMsg");
		value.cmdName = ms.decodeOctetString (4, "cmdName");
		value.codeDisplay = ms.decodeChar ("codeDisplay");
		value.indInvInput = ms.decodeChar ("indInvInput");
		value.indSystemStatus = ms.decodeChar ("indSystemStatus");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONDueDateErr (ms, value, tag); 
	}
	static public void encodeASONDueDateErr (MMarshalStrategy ms, ASONDueDateErr value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.replyCode, "replyCode");
		ms.encode (value.advisoryMsg, 36, "advisoryMsg");
	ms.encode (value.cmdName, 4, "cmdName");
ms.encode (value.codeDisplay, "codeDisplay");
ms.encode (value.indInvInput, "indInvInput");
ms.encode (value.indSystemStatus, "indSystemStatus");
ms.endStruct (tag, true); 
}
public static ASONDueDateErr fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeASONDueDateErr (ms, "ASONDueDateErr"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONDueDateErrHelper.type(); 
}
public static byte [] toOctet (ASONDueDateErr val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeASONDueDateErr (ms, val, "ASONDueDateErr");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
