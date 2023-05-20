// $Id: infoline_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class infoline_stMsg implements MMarshalObject { 
	public infoline_st value;

	public infoline_stMsg () {
	}
	public infoline_stMsg (infoline_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st create () { 
	com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st();
	value.infoLine = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeinfoline_st (ms, tag); 
	}
	static public infoline_st decodeinfoline_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		infoline_st value = create();
		ms.startStruct (tag, false);
		value.infoLine = ms.decodeOctetString (80, "infoLine");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeinfoline_st (ms, value, tag); 
	}
	static public void encodeinfoline_st (MMarshalStrategy ms, infoline_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.infoLine, 80, "infoLine");
	ms.endStruct (tag, true); 
}
public static infoline_st fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeinfoline_st (ms, "infoline_st"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.asonservice.interfaces.infoline_stHelper.type(); 
}
public static byte [] toOctet (infoline_st val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeinfoline_st (ms, val, "infoline_st");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
