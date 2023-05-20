// $Id: PremisUnadrmGsgmResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisUnadrmGsgmResp_tMsg implements MMarshalObject { 
	public PremisUnadrmGsgmResp_t value;

	public PremisUnadrmGsgmResp_tMsg () {
	}
	public PremisUnadrmGsgmResp_tMsg (PremisUnadrmGsgmResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnadrmGsgmResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnadrmGsgmResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnadrmGsgmResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.UnadrmGsgmPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisUnadrmGsgmResp_t (ms, tag); 
	}
	static public PremisUnadrmGsgmResp_t decodePremisUnadrmGsgmResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisUnadrmGsgmResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.UnadrmGsgmPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmPktResp_tMsg.decodeUnadrmGsgmPktResp_t (ms, "UnadrmGsgmPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisUnadrmGsgmResp_t (ms, value, tag); 
	}
	static public void encodePremisUnadrmGsgmResp_t (MMarshalStrategy ms, PremisUnadrmGsgmResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmPktResp_tMsg.encodeUnadrmGsgmPktResp_t (ms, value.UnadrmGsgmPktResp, "UnadrmGsgmPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisUnadrmGsgmResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisUnadrmGsgmResp_t (ms, "PremisUnadrmGsgmResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnadrmGsgmResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisUnadrmGsgmResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisUnadrmGsgmResp_t (ms, val, "PremisUnadrmGsgmResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
