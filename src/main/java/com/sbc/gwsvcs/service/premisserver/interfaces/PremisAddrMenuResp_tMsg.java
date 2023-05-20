// $Id: PremisAddrMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisAddrMenuResp_tMsg implements MMarshalObject { 
	public PremisAddrMenuResp_t value;

	public PremisAddrMenuResp_tMsg () {
	}
	public PremisAddrMenuResp_tMsg (PremisAddrMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.AddrMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisAddrMenuResp_t (ms, tag); 
	}
	static public PremisAddrMenuResp_t decodePremisAddrMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisAddrMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.AddrMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_tMsg.decodeAddrMenuPktResp_t (ms, "AddrMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisAddrMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisAddrMenuResp_t (MMarshalStrategy ms, PremisAddrMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_tMsg.encodeAddrMenuPktResp_t (ms, value.AddrMenuPktResp, "AddrMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisAddrMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisAddrMenuResp_t (ms, "PremisAddrMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisAddrMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisAddrMenuResp_t (ms, val, "PremisAddrMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
