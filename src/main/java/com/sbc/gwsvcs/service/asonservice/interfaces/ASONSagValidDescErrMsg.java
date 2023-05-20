// $Id: ASONSagValidDescErrMsg.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValidDescErrMsg implements MMarshalObject { 
	public ASONSagValidDescErr value;

	public ASONSagValidDescErrMsg () {
	}
	public ASONSagValidDescErrMsg (ASONSagValidDescErr initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr();
value.comReplyHdr1 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st();
value.comReplyHdr2 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st();
value.comReplyHdr3 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st();
value.descAddrRmk1 = new String ();
value.descAddrRmk2 = new String ();
value.descAddrRmk3 = new String ();
value.descAddrRmk4 = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValidDescErr (ms, tag); 
	}
	static public ASONSagValidDescErr decodeASONSagValidDescErr (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValidDescErr value = create();
		ms.startStruct (tag, false);
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.decodecomReplyHdr1_st (ms, "comReplyHdr1");
		value.comReplyHdr2 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.decodecomReplyHdr2_st (ms, "comReplyHdr2");
		value.comReplyHdr3 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stMsg.decodecomReplyHdr3_st (ms, "comReplyHdr3");
		value.descAddrRmk1 = ms.decodeOctetString (73, "descAddrRmk1");
		value.descAddrRmk2 = ms.decodeOctetString (73, "descAddrRmk2");
		value.descAddrRmk3 = ms.decodeOctetString (73, "descAddrRmk3");
		value.descAddrRmk4 = ms.decodeOctetString (73, "descAddrRmk4");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValidDescErr (ms, value, tag); 
	}
	static public void encodeASONSagValidDescErr (MMarshalStrategy ms, ASONSagValidDescErr value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.encodecomReplyHdr1_st (ms, value.comReplyHdr1, "comReplyHdr1");
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.encodecomReplyHdr2_st (ms, value.comReplyHdr2, "comReplyHdr2");
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stMsg.encodecomReplyHdr3_st (ms, value.comReplyHdr3, "comReplyHdr3");
		ms.encode (value.descAddrRmk1, 73, "descAddrRmk1");
	ms.encode (value.descAddrRmk2, 73, "descAddrRmk2");
ms.encode (value.descAddrRmk3, 73, "descAddrRmk3");
ms.encode (value.descAddrRmk4, 73, "descAddrRmk4");
ms.endStruct (tag, true); 
}
public static ASONSagValidDescErr fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeASONSagValidDescErr (ms, "ASONSagValidDescErr"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErrHelper.type(); 
}
public static byte [] toOctet (ASONSagValidDescErr val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeASONSagValidDescErr (ms, val, "ASONSagValidDescErr");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
