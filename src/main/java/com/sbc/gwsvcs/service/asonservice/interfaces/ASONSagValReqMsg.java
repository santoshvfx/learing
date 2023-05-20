// $Id: ASONSagValReqMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValReqMsg implements MMarshalObject { 
	public ASONSagValReq value;

	public ASONSagValReqMsg () {
	}
	public ASONSagValReqMsg (ASONSagValReq initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.commandLine = new com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st();
value.dateKey = new String ();
value.functionKeyDepressed = new String ();
value.idGroup = new String ();
value.idTerminal = new String ();
value.idTypist = new String ();
value.regionalAreaId = new String ();
value.timeKey = new String ();
value.addressName = new String ();
value.zipCode = new String ();
value.community = new String ();
value.holdSagKey = new String ();
value.sentEndString = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValReq (ms, tag); 
	}
	static public ASONSagValReq decodeASONSagValReq (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValReq value = create();
		ms.startStruct (tag, false);
		value.tagInformation = com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.decodetaginformation_st (ms, "tagInformation");
		value.requestCode = ms.decodeShort ("requestCode");
		value.commandLine = com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stMsg.decodecommandline_st (ms, "commandLine");
		value.dateKey = ms.decodeOctetString (7, "dateKey");
		value.functionKeyDepressed = ms.decodeOctetString (3, "functionKeyDepressed");
		value.idGroup = ms.decodeOctetString (6, "idGroup");
		value.idTerminal = ms.decodeOctetString (16, "idTerminal");
		value.idTypist = ms.decodeOctetString (4, "idTypist");
		value.regionalAreaId = ms.decodeOctetString (3, "regionalAreaId");
		value.timeKey = ms.decodeOctetString (9, "timeKey");
		value.addressName = ms.decodeOctetString (70, "addressName");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.community = ms.decodeOctetString (21, "community");
		value.holdSagKey = ms.decodeOctetString (107, "holdSagKey");
		value.sentEndString = ms.decodeOctetString (4, "sentEndString");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValReq (ms, value, tag); 
	}
	static public void encodeASONSagValReq (MMarshalStrategy ms, ASONSagValReq value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stMsg.encodetaginformation_st (ms, value.tagInformation, "tagInformation");
		ms.encode (value.requestCode, "requestCode");
		com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stMsg.encodecommandline_st (ms, value.commandLine, "commandLine");
		ms.encode (value.dateKey, 7, "dateKey");
	ms.encode (value.functionKeyDepressed, 3, "functionKeyDepressed");
ms.encode (value.idGroup, 6, "idGroup");
ms.encode (value.idTerminal, 16, "idTerminal");
ms.encode (value.idTypist, 4, "idTypist");
ms.encode (value.regionalAreaId, 3, "regionalAreaId");
ms.encode (value.timeKey, 9, "timeKey");
ms.encode (value.addressName, 70, "addressName");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.community, 21, "community");
ms.encode (value.holdSagKey, 107, "holdSagKey");
ms.encode (value.sentEndString, 4, "sentEndString");
ms.endStruct (tag, true); 
}
public static ASONSagValReq fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONSagValReq (ms, "ASONSagValReq"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReqHelper.type(); 
}
public static byte [] toOctet (ASONSagValReq val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONSagValReq (ms, val, "ASONSagValReq");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
