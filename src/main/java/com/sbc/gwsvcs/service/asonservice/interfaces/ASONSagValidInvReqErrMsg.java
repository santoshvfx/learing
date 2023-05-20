// $Id: ASONSagValidInvReqErrMsg.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValidInvReqErrMsg implements MMarshalObject { 
	public ASONSagValidInvReqErr value;

	public ASONSagValidInvReqErrMsg () {
	}
	public ASONSagValidInvReqErrMsg (ASONSagValidInvReqErr initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidInvReqErr create () { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidInvReqErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidInvReqErr();
		value.comReplyHdr1 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValidInvReqErr (ms, tag); 
	}
	static public ASONSagValidInvReqErr decodeASONSagValidInvReqErr (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValidInvReqErr value = create();
		ms.startStruct (tag, false);
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.decodecomReplyHdr1_st (ms, "comReplyHdr1");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValidInvReqErr (ms, value, tag); 
	}
	static public void encodeASONSagValidInvReqErr (MMarshalStrategy ms, ASONSagValidInvReqErr value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.encodecomReplyHdr1_st (ms, value.comReplyHdr1, "comReplyHdr1");
		ms.endStruct (tag, true); 
	}
	public static ASONSagValidInvReqErr fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeASONSagValidInvReqErr (ms, "ASONSagValidInvReqErr"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidInvReqErrHelper.type(); 
	}
	public static byte [] toOctet (ASONSagValidInvReqErr val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeASONSagValidInvReqErr (ms, val, "ASONSagValidInvReqErr");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
