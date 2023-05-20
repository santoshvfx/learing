// $Id: sagline_stMsg.java,v 1.1 2002/09/29 03:53:01 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class sagline_stMsg implements MMarshalObject { 
	public sagline_st value;

	public sagline_stMsg () {
	}
	public sagline_stMsg (sagline_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st create () { 
	com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st();
	value.sagLine = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodesagline_st (ms, tag); 
	}
	static public sagline_st decodesagline_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		sagline_st value = create();
		ms.startStruct (tag, false);
		value.sagLine = ms.decodeOctetString (80, "sagLine");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodesagline_st (ms, value, tag); 
	}
	static public void encodesagline_st (MMarshalStrategy ms, sagline_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.sagLine, 80, "sagLine");
	ms.endStruct (tag, true); 
}
public static sagline_st fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodesagline_st (ms, "sagline_st"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.asonservice.interfaces.sagline_stHelper.type(); 
}
public static byte [] toOctet (sagline_st val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodesagline_st (ms, val, "sagline_st");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
