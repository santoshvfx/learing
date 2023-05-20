// $Id: openDates_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class openDates_stMsg implements MMarshalObject { 
	public openDates_st value;

	public openDates_stMsg () {
	}
	public openDates_stMsg (openDates_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.openDates_st();
value.weekDay1 = new String ();
value.mmDD1 = new String ();
value.weekDay2 = new String ();
value.mmDD2 = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeopenDates_st (ms, tag); 
	}
	static public openDates_st decodeopenDates_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		openDates_st value = create();
		ms.startStruct (tag, false);
		value.weekDay1 = ms.decodeOctetString (6, "weekDay1");
		value.mmDD1 = ms.decodeOctetString (6, "mmDD1");
		value.filler1 = ms.decodeChar ("filler1");
		value.amOrPmOrAll1 = ms.decodeChar ("amOrPmOrAll1");
		value.filler2 = ms.decodeChar ("filler2");
		value.weekDay2 = ms.decodeOctetString (6, "weekDay2");
		value.mmDD2 = ms.decodeOctetString (6, "mmDD2");
		value.filler3 = ms.decodeChar ("filler3");
		value.amOrPmOrAll2 = ms.decodeChar ("amOrPmOrAll2");
		value.filler4 = ms.decodeChar ("filler4");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeopenDates_st (ms, value, tag); 
	}
	static public void encodeopenDates_st (MMarshalStrategy ms, openDates_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.weekDay1, 6, "weekDay1");
	ms.encode (value.mmDD1, 6, "mmDD1");
ms.encode (value.filler1, "filler1");
ms.encode (value.amOrPmOrAll1, "amOrPmOrAll1");
ms.encode (value.filler2, "filler2");
ms.encode (value.weekDay2, 6, "weekDay2");
ms.encode (value.mmDD2, 6, "mmDD2");
ms.encode (value.filler3, "filler3");
ms.encode (value.amOrPmOrAll2, "amOrPmOrAll2");
ms.encode (value.filler4, "filler4");
ms.endStruct (tag, true); 
}
public static openDates_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeopenDates_st (ms, "openDates_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.openDates_stHelper.type(); 
}
public static byte [] toOctet (openDates_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeopenDates_st (ms, val, "openDates_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
