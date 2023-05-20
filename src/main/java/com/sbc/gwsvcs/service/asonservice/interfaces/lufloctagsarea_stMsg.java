// $Id: lufloctagsarea_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class lufloctagsarea_stMsg implements MMarshalObject { 
	public lufloctagsarea_st value;

	public lufloctagsarea_stMsg () {
	}
	public lufloctagsarea_stMsg (lufloctagsarea_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st();
value.locTag1 = new String ();
value.locTag2 = new String ();
value.locTag3 = new String ();
value.locTag4 = new String ();
value.locTag5 = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodelufloctagsarea_st (ms, tag); 
	}
	static public lufloctagsarea_st decodelufloctagsarea_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		lufloctagsarea_st value = create();
		ms.startStruct (tag, false);
		value.locTag1 = ms.decodeOctetString (6, "locTag1");
		value.locTag2 = ms.decodeOctetString (6, "locTag2");
		value.locTag3 = ms.decodeOctetString (6, "locTag3");
		value.locTag4 = ms.decodeOctetString (6, "locTag4");
		value.locTag5 = ms.decodeOctetString (6, "locTag5");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodelufloctagsarea_st (ms, value, tag); 
	}
	static public void encodelufloctagsarea_st (MMarshalStrategy ms, lufloctagsarea_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.locTag1, 6, "locTag1");
	ms.encode (value.locTag2, 6, "locTag2");
ms.encode (value.locTag3, 6, "locTag3");
ms.encode (value.locTag4, 6, "locTag4");
ms.encode (value.locTag5, 6, "locTag5");
ms.endStruct (tag, true); 
}
public static lufloctagsarea_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodelufloctagsarea_st (ms, "lufloctagsarea_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_stHelper.type(); 
}
public static byte [] toOctet (lufloctagsarea_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodelufloctagsarea_st (ms, val, "lufloctagsarea_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
