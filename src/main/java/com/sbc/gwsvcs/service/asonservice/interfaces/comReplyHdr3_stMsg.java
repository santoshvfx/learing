// $Id: comReplyHdr3_stMsg.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class comReplyHdr3_stMsg implements MMarshalObject { 
	public comReplyHdr3_st value;

	public comReplyHdr3_stMsg () {
	}
	public comReplyHdr3_stMsg (comReplyHdr3_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st create () { 
	com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st();
	value.sagErrorCode = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodecomReplyHdr3_st (ms, tag); 
	}
	static public comReplyHdr3_st decodecomReplyHdr3_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		comReplyHdr3_st value = create();
		ms.startStruct (tag, false);
		value.sagErrorCode = ms.decodeOctetString (6, "sagErrorCode");
		value.saiPrimary = ms.decodeChar ("saiPrimary");
		value.saiAlt1 = ms.decodeChar ("saiAlt1");
		value.saiAlt2 = ms.decodeChar ("saiAlt2");
		value.saiAlt3 = ms.decodeChar ("saiAlt3");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodecomReplyHdr3_st (ms, value, tag); 
	}
	static public void encodecomReplyHdr3_st (MMarshalStrategy ms, comReplyHdr3_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.sagErrorCode, 6, "sagErrorCode");
	ms.encode (value.saiPrimary, "saiPrimary");
	ms.encode (value.saiAlt1, "saiAlt1");
	ms.encode (value.saiAlt2, "saiAlt2");
	ms.encode (value.saiAlt3, "saiAlt3");
	ms.endStruct (tag, true); 
}
public static comReplyHdr3_st fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodecomReplyHdr3_st (ms, "comReplyHdr3_st"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stHelper.type(); 
}
public static byte [] toOctet (comReplyHdr3_st val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodecomReplyHdr3_st (ms, val, "comReplyHdr3_st");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
