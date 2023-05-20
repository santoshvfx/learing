// $Id: AddlLnDataItem_tMsg.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddlLnDataItem_tMsg implements MMarshalObject { 
	public AddlLnDataItem_t value;

	public AddlLnDataItem_tMsg () {
	}
	public AddlLnDataItem_tMsg (AddlLnDataItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_t();
	value.ADDL_TN = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddlLnDataItem_t (ms, tag); 
	}
	static public AddlLnDataItem_t decodeAddlLnDataItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddlLnDataItem_t value = create();
		ms.startStruct (tag, false);
		value.ADDL_TN = ms.decodeOctetString (14, "ADDL_TN");
		value.ADDL_LN_ID = ms.decodeChar ("ADDL_LN_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddlLnDataItem_t (ms, value, tag); 
	}
	static public void encodeAddlLnDataItem_t (MMarshalStrategy ms, AddlLnDataItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ADDL_TN, 14, "ADDL_TN");
	ms.encode (value.ADDL_LN_ID, "ADDL_LN_ID");
	ms.endStruct (tag, true); 
}
public static AddlLnDataItem_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeAddlLnDataItem_t (ms, "AddlLnDataItem_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.AddlLnDataItem_tHelper.type(); 
}
public static byte [] toOctet (AddlLnDataItem_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeAddlLnDataItem_t (ms, val, "AddlLnDataItem_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
