// $Id: PremisAddrRngeMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisAddrRngeMenuResp_tMsg implements MMarshalObject { 
	public PremisAddrRngeMenuResp_t value;

	public PremisAddrRngeMenuResp_tMsg () {
	}
	public PremisAddrRngeMenuResp_tMsg (PremisAddrRngeMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrRngeMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrRngeMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrRngeMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.AddrRngeMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisAddrRngeMenuResp_t (ms, tag); 
	}
	static public PremisAddrRngeMenuResp_t decodePremisAddrRngeMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisAddrRngeMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.AddrRngeMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuPktResp_tMsg.decodeAddrRngeMenuPktResp_t (ms, "AddrRngeMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisAddrRngeMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisAddrRngeMenuResp_t (MMarshalStrategy ms, PremisAddrRngeMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuPktResp_tMsg.encodeAddrRngeMenuPktResp_t (ms, value.AddrRngeMenuPktResp, "AddrRngeMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisAddrRngeMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisAddrRngeMenuResp_t (ms, "PremisAddrRngeMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrRngeMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisAddrRngeMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisAddrRngeMenuResp_t (ms, val, "PremisAddrRngeMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
