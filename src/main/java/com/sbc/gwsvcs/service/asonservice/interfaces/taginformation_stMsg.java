// $Id: taginformation_stMsg.java,v 1.1 2002/09/29 03:53:01 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class taginformation_stMsg implements MMarshalObject { 
	public taginformation_st value;

	public taginformation_stMsg () {
	}
	public taginformation_stMsg (taginformation_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st();
value.headerInfo1 = new String ();
value.sendingSystem = new String ();
value.headerInfo2 = new String ();
value.sendingDate = new String ();
value.headerInfo3 = new String ();
value.sendingTime = new String ();
value.headerInfo4 = new String ();
value.receivingSystem = new String ();
value.headerInfo5 = new String ();
value.receivingDate = new String ();
value.headerInfo6 = new String ();
value.receivingTime = new String ();
value.headerInfo7 = new String ();
value.tagErrCode = new String ();
value.headerInfo8 = new String ();
value.wordAlignmentByte = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodetaginformation_st (ms, tag); 
	}
	static public taginformation_st decodetaginformation_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		taginformation_st value = create();
		ms.startStruct (tag, false);
		value.headerInfo1 = ms.decodeOctetString (70, "headerInfo1");
		value.sendingSystem = ms.decodeOctetString (9, "sendingSystem");
		value.headerInfo2 = ms.decodeOctetString (8, "headerInfo2");
		value.sendingDate = ms.decodeOctetString (7, "sendingDate");
		value.headerInfo3 = ms.decodeOctetString (8, "headerInfo3");
		value.sendingTime = ms.decodeOctetString (9, "sendingTime");
		value.headerInfo4 = ms.decodeOctetString (7, "headerInfo4");
		value.receivingSystem = ms.decodeOctetString (9, "receivingSystem");
		value.headerInfo5 = ms.decodeOctetString (8, "headerInfo5");
		value.receivingDate = ms.decodeOctetString (7, "receivingDate");
		value.headerInfo6 = ms.decodeOctetString (8, "headerInfo6");
		value.receivingTime = ms.decodeOctetString (9, "receivingTime");
		value.headerInfo7 = ms.decodeOctetString (20, "headerInfo7");
		value.tagErrCode = ms.decodeOctetString (9, "tagErrCode");
		value.headerInfo8 = ms.decodeOctetString (16, "headerInfo8");
		value.wordAlignmentByte = ms.decodeOctetString (2, "wordAlignmentByte");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodetaginformation_st (ms, value, tag); 
	}
	static public void encodetaginformation_st (MMarshalStrategy ms, taginformation_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.headerInfo1, 70, "headerInfo1");
	ms.encode (value.sendingSystem, 9, "sendingSystem");
ms.encode (value.headerInfo2, 8, "headerInfo2");
ms.encode (value.sendingDate, 7, "sendingDate");
ms.encode (value.headerInfo3, 8, "headerInfo3");
ms.encode (value.sendingTime, 9, "sendingTime");
ms.encode (value.headerInfo4, 7, "headerInfo4");
ms.encode (value.receivingSystem, 9, "receivingSystem");
ms.encode (value.headerInfo5, 8, "headerInfo5");
ms.encode (value.receivingDate, 7, "receivingDate");
ms.encode (value.headerInfo6, 8, "headerInfo6");
ms.encode (value.receivingTime, 9, "receivingTime");
ms.encode (value.headerInfo7, 20, "headerInfo7");
ms.encode (value.tagErrCode, 9, "tagErrCode");
ms.encode (value.headerInfo8, 16, "headerInfo8");
ms.encode (value.wordAlignmentByte, 2, "wordAlignmentByte");
ms.endStruct (tag, true); 
}
public static taginformation_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodetaginformation_st (ms, "taginformation_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_stHelper.type(); 
}
public static byte [] toOctet (taginformation_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodetaginformation_st (ms, val, "taginformation_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
