// $Id: DtTm_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DtTm_tMsg implements MMarshalObject { 
	public DtTm_t value;

	public DtTm_tMsg () {
	}
	public DtTm_tMsg (DtTm_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
	value.DT = new com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t();
	value.TM = new com.sbc.gwsvcs.service.premisserver.interfaces.Tm_t();
	value.TM_OST = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDtTm_t (ms, tag); 
	}
	static public DtTm_t decodeDtTm_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DtTm_t value = create();
		ms.startStruct (tag, false);
		value.DT = com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.decodeDt_t (ms, "DT");
		value.TM = com.sbc.gwsvcs.service.premisserver.interfaces.Tm_tMsg.decodeTm_t (ms, "TM");
		value.TM_OST = ms.decodeOctetString (6, "TM_OST");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDtTm_t (ms, value, tag); 
	}
	static public void encodeDtTm_t (MMarshalStrategy ms, DtTm_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.DT, "DT");
		com.sbc.gwsvcs.service.premisserver.interfaces.Tm_tMsg.encodeTm_t (ms, value.TM, "TM");
		ms.encode (value.TM_OST, 6, "TM_OST");
	ms.endStruct (tag, true); 
}
public static DtTm_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeDtTm_t (ms, "DtTm_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tHelper.type(); 
}
public static byte [] toOctet (DtTm_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeDtTm_t (ms, val, "DtTm_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
