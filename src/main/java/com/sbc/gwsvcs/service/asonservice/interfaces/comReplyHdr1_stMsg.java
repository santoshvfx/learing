// $Id: comReplyHdr1_stMsg.java,v 1.1 2002/09/29 03:53:48 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class comReplyHdr1_stMsg implements MMarshalObject { 
	public comReplyHdr1_st value;

	public comReplyHdr1_stMsg () {
	}
	public comReplyHdr1_stMsg (comReplyHdr1_st initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st create () { 
com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st value = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st();
value.MsgLength = new String ();
value.RequestDateYYYYMMDD = new String ();
value.RequestTimeHHMMSSCC = new String ();
value.ReplyDateYYYYMMDD = new String ();
value.ReplyTimeHHMMSSCC = new String ();
value.MsgCode = new String ();
value.MsgText = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodecomReplyHdr1_st (ms, tag); 
	}
	static public comReplyHdr1_st decodecomReplyHdr1_st (MMarshalStrategy ms, String tag) throws MMarshalException { 
		comReplyHdr1_st value = create();
		ms.startStruct (tag, false);
		value.ReplyCode = ms.decodeShort ("ReplyCode");
		value.MsgLength = ms.decodeOctetString (9, "MsgLength");
		value.TmfAction = ms.decodeChar ("TmfAction");
		value.RequestDateYYYYMMDD = ms.decodeOctetString (9, "RequestDateYYYYMMDD");
		value.RequestTimeHHMMSSCC = ms.decodeOctetString (9, "RequestTimeHHMMSSCC");
		value.ReplyDateYYYYMMDD = ms.decodeOctetString (9, "ReplyDateYYYYMMDD");
		value.ReplyTimeHHMMSSCC = ms.decodeOctetString (9, "ReplyTimeHHMMSSCC");
		value.MsgCode = ms.decodeOctetString (5, "MsgCode");
		value.MsgDelimiter = ms.decodeChar ("MsgDelimiter");
		value.MsgText = ms.decodeOctetString (31, "MsgText");
		value.XDRalignFiller = ms.decodeChar ("XDRalignFiller");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodecomReplyHdr1_st (ms, value, tag); 
	}
	static public void encodecomReplyHdr1_st (MMarshalStrategy ms, comReplyHdr1_st value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ReplyCode, "ReplyCode");
		ms.encode (value.MsgLength, 9, "MsgLength");
	ms.encode (value.TmfAction, "TmfAction");
	ms.encode (value.RequestDateYYYYMMDD, 9, "RequestDateYYYYMMDD");
ms.encode (value.RequestTimeHHMMSSCC, 9, "RequestTimeHHMMSSCC");
ms.encode (value.ReplyDateYYYYMMDD, 9, "ReplyDateYYYYMMDD");
ms.encode (value.ReplyTimeHHMMSSCC, 9, "ReplyTimeHHMMSSCC");
ms.encode (value.MsgCode, 5, "MsgCode");
ms.encode (value.MsgDelimiter, "MsgDelimiter");
ms.encode (value.MsgText, 31, "MsgText");
ms.encode (value.XDRalignFiller, "XDRalignFiller");
ms.endStruct (tag, true); 
}
public static comReplyHdr1_st fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodecomReplyHdr1_st (ms, "comReplyHdr1_st"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stHelper.type(); 
}
public static byte [] toOctet (comReplyHdr1_st val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodecomReplyHdr1_st (ms, val, "comReplyHdr1_st");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
