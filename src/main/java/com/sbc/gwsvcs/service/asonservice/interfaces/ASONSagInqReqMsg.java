// $Id: ASONSagInqReqMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagInqReqMsg implements MMarshalObject { 
	public ASONSagInqReq value;

	public ASONSagInqReqMsg () {
	}
	public ASONSagInqReqMsg (ASONSagInqReq initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqReq create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqReq();
value.tagInformation = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.commandLine = new com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st();
value.dateKey = new String ();
value.functionKeyDepressed = new String ();
value.idGroup = new String ();
value.idTerminal = new String ();
value.idTypist = new String ();
value.regionalAreaId = new String ();
value.timeKey = new String ();
value.sagAreaId = new String ();
value.sagDirectional = new String ();
value.addressName = new String ();
value.zipCode = new String ();
value.savedSagKey = new String ();
value.savedSagScreenInd = new String ();
value.exactPositioningInd = new String ();
value.sentEndString = new String ();
value.maxAddressReturnLimit = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagInqReq (ms, tag); 
	}
	static public ASONSagInqReq decodeASONSagInqReq (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagInqReq value = create();
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
		value.sagAreaId = ms.decodeOctetString (2, "sagAreaId");
		value.sagDirectional = ms.decodeOctetString (4, "sagDirectional");
		value.addressName = ms.decodeOctetString (21, "addressName");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.savedSagKey = ms.decodeOctetString (107, "savedSagKey");
		value.savedSagScreenInd = ms.decodeOctetString (2, "savedSagScreenInd");
		value.exactPositioningInd = ms.decodeOctetString (2, "exactPositioningInd");
		value.sentEndString = ms.decodeOctetString (4, "sentEndString");
		value.maxAddressReturnLimit = ms.decodeOctetString (4, "maxAddressReturnLimit");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagInqReq (ms, value, tag); 
	}
	static public void encodeASONSagInqReq (MMarshalStrategy ms, ASONSagInqReq value, String tag) throws MMarshalException { 
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
ms.encode (value.sagAreaId, 2, "sagAreaId");
ms.encode (value.sagDirectional, 4, "sagDirectional");
ms.encode (value.addressName, 21, "addressName");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.savedSagKey, 107, "savedSagKey");
ms.encode (value.savedSagScreenInd, 2, "savedSagScreenInd");
ms.encode (value.exactPositioningInd, 2, "exactPositioningInd");
ms.encode (value.sentEndString, 4, "sentEndString");
ms.encode (value.maxAddressReturnLimit, 4, "maxAddressReturnLimit");
ms.endStruct (tag, true); 
}
public static ASONSagInqReq fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONSagInqReq (ms, "ASONSagInqReq"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqReqHelper.type(); 
}
public static byte [] toOctet (ASONSagInqReq val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONSagInqReq (ms, val, "ASONSagInqReq");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
