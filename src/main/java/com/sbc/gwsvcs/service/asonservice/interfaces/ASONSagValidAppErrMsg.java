// $Id: ASONSagValidAppErrMsg.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValidAppErrMsg implements MMarshalObject { 
	public ASONSagValidAppErr value;

	public ASONSagValidAppErrMsg () {
	}
	public ASONSagValidAppErrMsg (ASONSagValidAppErr initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidAppErr create () { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidAppErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidAppErr();
		value.comReplyHdr1 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st();
		value.comReplyHdr2 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValidAppErr (ms, tag); 
	}
	static public ASONSagValidAppErr decodeASONSagValidAppErr (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValidAppErr value = create();
		ms.startStruct (tag, false);
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.decodecomReplyHdr1_st (ms, "comReplyHdr1");
		value.comReplyHdr2 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.decodecomReplyHdr2_st (ms, "comReplyHdr2");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValidAppErr (ms, value, tag); 
	}
	static public void encodeASONSagValidAppErr (MMarshalStrategy ms, ASONSagValidAppErr value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.encodecomReplyHdr1_st (ms, value.comReplyHdr1, "comReplyHdr1");
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.encodecomReplyHdr2_st (ms, value.comReplyHdr2, "comReplyHdr2");
		ms.endStruct (tag, true); 
	}
	public static ASONSagValidAppErr fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeASONSagValidAppErr (ms, "ASONSagValidAppErr"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidAppErrHelper.type(); 
	}
	public static byte [] toOctet (ASONSagValidAppErr val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeASONSagValidAppErr (ms, val, "ASONSagValidAppErr");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
