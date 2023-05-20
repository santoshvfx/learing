// $Id: ASONSagValidErrMsg.java,v 1.1 2002/09/29 03:53:47 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ASONSagValidErrMsg implements MMarshalObject { 
	public ASONSagValidErr value;

	public ASONSagValidErrMsg () {
	}
	public ASONSagValidErrMsg (ASONSagValidErr initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr create () { 
		com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr value = new com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr();
		value.comReplyHdr1 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_st();
		value.comReplyHdr2 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_st();
		value.comReplyHdr3 = new com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_st();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeASONSagValidErr (ms, tag); 
	}
	static public ASONSagValidErr decodeASONSagValidErr (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ASONSagValidErr value = create();
		ms.startStruct (tag, false);
		value.comReplyHdr1 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.decodecomReplyHdr1_st (ms, "comReplyHdr1");
		value.comReplyHdr2 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.decodecomReplyHdr2_st (ms, "comReplyHdr2");
		value.comReplyHdr3 = com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stMsg.decodecomReplyHdr3_st (ms, "comReplyHdr3");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeASONSagValidErr (ms, value, tag); 
	}
	static public void encodeASONSagValidErr (MMarshalStrategy ms, ASONSagValidErr value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr1_stMsg.encodecomReplyHdr1_st (ms, value.comReplyHdr1, "comReplyHdr1");
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr2_stMsg.encodecomReplyHdr2_st (ms, value.comReplyHdr2, "comReplyHdr2");
		com.sbc.gwsvcs.service.asonservice.interfaces.comReplyHdr3_stMsg.encodecomReplyHdr3_st (ms, value.comReplyHdr3, "comReplyHdr3");
		ms.endStruct (tag, true); 
	}
	public static ASONSagValidErr fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeASONSagValidErr (ms, "ASONSagValidErr"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErrHelper.type(); 
	}
	public static byte [] toOctet (ASONSagValidErr val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeASONSagValidErr (ms, val, "ASONSagValidErr");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
