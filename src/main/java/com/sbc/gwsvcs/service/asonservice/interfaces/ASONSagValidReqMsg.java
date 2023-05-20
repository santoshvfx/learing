// $Id: ASONSagValidReqMsg.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValidReqMsg implements MMarshalObject { 
	public ASONSagValidReq value;

	public ASONSagValidReqMsg () {
	}
	public ASONSagValidReqMsg (ASONSagValidReq initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq();
value.MsgLength = new String ();
value.IdGroup = new String ();
value.IdTypist = new String ();
value.IdSystem = new String ();
value.RequestDateYYYYMMDD = new String ();
value.RequestTimeHHMMSSCC = new String ();
value.RegionalAreaId = new String ();
value.addressName = new String ();
value.community = new String ();
value.zipCode = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValidReq (ms, tag); 
	}
	static public ASONSagValidReq decodeASONSagValidReq (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValidReq value = create();
		ms.startStruct (tag, false);
		value.requestCode = ms.decodeShort ("requestCode");
		value.MsgLength = ms.decodeOctetString (9, "MsgLength");
		value.IdGroup = ms.decodeOctetString (6, "IdGroup");
		value.IdTypist = ms.decodeOctetString (4, "IdTypist");
		value.IdSystem = ms.decodeOctetString (9, "IdSystem");
		value.RequestDateYYYYMMDD = ms.decodeOctetString (9, "RequestDateYYYYMMDD");
		value.RequestTimeHHMMSSCC = ms.decodeOctetString (9, "RequestTimeHHMMSSCC");
		value.RegionalAreaId = ms.decodeOctetString (3, "RegionalAreaId");
		value.addressName = ms.decodeOctetString (70, "addressName");
		value.community = ms.decodeOctetString (21, "community");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.descriptiveAddrInd = ms.decodeChar ("descriptiveAddrInd");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValidReq (ms, value, tag); 
	}
	static public void encodeASONSagValidReq (MMarshalStrategy ms, ASONSagValidReq value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.requestCode, "requestCode");
		ms.encode (value.MsgLength, 9, "MsgLength");
	ms.encode (value.IdGroup, 6, "IdGroup");
ms.encode (value.IdTypist, 4, "IdTypist");
ms.encode (value.IdSystem, 9, "IdSystem");
ms.encode (value.RequestDateYYYYMMDD, 9, "RequestDateYYYYMMDD");
ms.encode (value.RequestTimeHHMMSSCC, 9, "RequestTimeHHMMSSCC");
ms.encode (value.RegionalAreaId, 3, "RegionalAreaId");
ms.encode (value.addressName, 70, "addressName");
ms.encode (value.community, 21, "community");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.descriptiveAddrInd, "descriptiveAddrInd");
ms.endStruct (tag, true); 
}
public static ASONSagValidReq fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONSagValidReq (ms, "ASONSagValidReq"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReqHelper.type(); 
}
public static byte [] toOctet (ASONSagValidReq val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONSagValidReq (ms, val, "ASONSagValidReq");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
