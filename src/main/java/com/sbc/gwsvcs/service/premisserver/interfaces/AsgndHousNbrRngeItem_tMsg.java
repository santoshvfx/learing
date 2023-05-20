// $Id: AsgndHousNbrRngeItem_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AsgndHousNbrRngeItem_tMsg implements MMarshalObject { 
	public AsgndHousNbrRngeItem_t value;

	public AsgndHousNbrRngeItem_tMsg () {
	}
	public AsgndHousNbrRngeItem_tMsg (AsgndHousNbrRngeItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t();
value.LOW_ASGND_HOUS_NBR_VALU_ID = new String ();
value.HI_ASGND_HOUS_NBR_VALU_ID = new String ();
value.ZC = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAsgndHousNbrRngeItem_t (ms, tag); 
	}
	static public AsgndHousNbrRngeItem_t decodeAsgndHousNbrRngeItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AsgndHousNbrRngeItem_t value = create();
		ms.startStruct (tag, false);
		value.LOW_ASGND_HOUS_NBR_VALU_ID = ms.decodeOctetString (14, "LOW_ASGND_HOUS_NBR_VALU_ID");
		value.HI_ASGND_HOUS_NBR_VALU_ID = ms.decodeOctetString (14, "HI_ASGND_HOUS_NBR_VALU_ID");
		value.ASGND_HOUS_NBR_RNGE_IND = ms.decodeChar ("ASGND_HOUS_NBR_RNGE_IND");
		value.ZC = ms.decodeOctetString (6, "ZC");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAsgndHousNbrRngeItem_t (ms, value, tag); 
	}
	static public void encodeAsgndHousNbrRngeItem_t (MMarshalStrategy ms, AsgndHousNbrRngeItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.LOW_ASGND_HOUS_NBR_VALU_ID, 14, "LOW_ASGND_HOUS_NBR_VALU_ID");
	ms.encode (value.HI_ASGND_HOUS_NBR_VALU_ID, 14, "HI_ASGND_HOUS_NBR_VALU_ID");
ms.encode (value.ASGND_HOUS_NBR_RNGE_IND, "ASGND_HOUS_NBR_RNGE_IND");
ms.encode (value.ZC, 6, "ZC");
ms.endStruct (tag, true); 
}
public static AsgndHousNbrRngeItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeAsgndHousNbrRngeItem_t (ms, "AsgndHousNbrRngeItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_tHelper.type(); 
}
public static byte [] toOctet (AsgndHousNbrRngeItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeAsgndHousNbrRngeItem_t (ms, val, "AsgndHousNbrRngeItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
