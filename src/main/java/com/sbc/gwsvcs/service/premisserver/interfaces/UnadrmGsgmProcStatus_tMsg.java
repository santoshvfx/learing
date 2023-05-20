// $Id: UnadrmGsgmProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:16 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnadrmGsgmProcStatus_tMsg implements MMarshalObject { 
	public UnadrmGsgmProcStatus_t value;

	public UnadrmGsgmProcStatus_tMsg () {
	}
	public UnadrmGsgmProcStatus_tMsg (UnadrmGsgmProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t();
	value.RTCD = new String ();
	value.UnadrmGsgm = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnadrmGsgmProcStatus_t (ms, tag); 
	}
	static public UnadrmGsgmProcStatus_t decodeUnadrmGsgmProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnadrmGsgmProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		value.UnadrmGsgm = com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tMsg.decodeUnadrmGsgm_t (ms, "UnadrmGsgm");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnadrmGsgmProcStatus_t (ms, value, tag); 
	}
	static public void encodeUnadrmGsgmProcStatus_t (MMarshalStrategy ms, UnadrmGsgmProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tMsg.encodeUnadrmGsgm_t (ms, value.UnadrmGsgm, "UnadrmGsgm");
	ms.endStruct (tag, true); 
}
public static UnadrmGsgmProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeUnadrmGsgmProcStatus_t (ms, "UnadrmGsgmProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_tHelper.type(); 
}
public static byte [] toOctet (UnadrmGsgmProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeUnadrmGsgmProcStatus_t (ms, val, "UnadrmGsgmProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
