// $Id: commandline_stMsg.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class commandline_stMsg implements MMarshalObject { 
	public commandline_st value;

	public commandline_stMsg () {
	}
	public commandline_stMsg (commandline_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st();
value.commandName = new String ();
value.commandFiller = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodecommandline_st (ms, tag); 
	}
	static public commandline_st decodecommandline_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		commandline_st value = create();
		ms.startStruct (tag, false);
		value.commandName = ms.decodeOctetString (4, "commandName");
		value.commandFiller = ms.decodeOctetString (33, "commandFiller");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodecommandline_st (ms, value, tag); 
	}
	static public void encodecommandline_st (MMarshalStrategy ms, commandline_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.commandName, 4, "commandName");
	ms.encode (value.commandFiller, 33, "commandFiller");
ms.endStruct (tag, true); 
}
public static commandline_st fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodecommandline_st (ms, "commandline_st"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.commandline_stHelper.type(); 
}
public static byte [] toOctet (commandline_st val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodecommandline_st (ms, val, "commandline_st");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
