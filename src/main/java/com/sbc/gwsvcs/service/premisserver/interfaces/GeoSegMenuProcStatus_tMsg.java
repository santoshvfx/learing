// $Id: GeoSegMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GeoSegMenuProcStatus_tMsg implements MMarshalObject { 
	public GeoSegMenuProcStatus_t value;

	public GeoSegMenuProcStatus_tMsg () {
	}
	public GeoSegMenuProcStatus_tMsg (GeoSegMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t();
	value.RTCD = new String ();
	value.Gsgm = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGeoSegMenuProcStatus_t (ms, tag); 
	}
	static public GeoSegMenuProcStatus_t decodeGeoSegMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GeoSegMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		value.Gsgm = com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tMsg.decodeUnadrmGsgm_t (ms, "Gsgm");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGeoSegMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeGeoSegMenuProcStatus_t (MMarshalStrategy ms, GeoSegMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tMsg.encodeUnadrmGsgm_t (ms, value.Gsgm, "Gsgm");
	ms.endStruct (tag, true); 
}
public static GeoSegMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeGeoSegMenuProcStatus_t (ms, "GeoSegMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (GeoSegMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeGeoSegMenuProcStatus_t (ms, val, "GeoSegMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
