// $Id: comReplyHdr2_stMsg.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class comReplyHdr2_stMsg implements MMarshalObject { 
	public comReplyHdr2_st value;

	public comReplyHdr2_stMsg () {
	}
	public comReplyHdr2_stMsg (comReplyHdr2_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st();
value.addressName = new String ();
value.community = new String ();
value.zipCode = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodecomReplyHdr2_st (ms, tag); 
	}
	static public comReplyHdr2_st decodecomReplyHdr2_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		comReplyHdr2_st value = create();
		ms.startStruct (tag, false);
		value.addressName = ms.decodeOctetString (70, "addressName");
		value.community = ms.decodeOctetString (21, "community");
		value.zipCode = ms.decodeOctetString (6, "zipCode");
		value.descriptiveAddrInd = ms.decodeChar ("descriptiveAddrInd");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodecomReplyHdr2_st (ms, value, tag); 
	}
	static public void encodecomReplyHdr2_st (MMarshalStrategy ms, comReplyHdr2_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.addressName, 70, "addressName");
	ms.encode (value.community, 21, "community");
ms.encode (value.zipCode, 6, "zipCode");
ms.encode (value.descriptiveAddrInd, "descriptiveAddrInd");
ms.endStruct (tag, true); 
}
public static comReplyHdr2_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodecomReplyHdr2_st (ms, "comReplyHdr2_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stHelper.type(); 
}
public static byte [] toOctet (comReplyHdr2_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodecomReplyHdr2_st (ms, val, "comReplyHdr2_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
