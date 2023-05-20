// $Id: PremisUnnbrdAddrRngeMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisUnnbrdAddrRngeMenuResp_tMsg implements MMarshalObject { 
	public PremisUnnbrdAddrRngeMenuResp_t value;

	public PremisUnnbrdAddrRngeMenuResp_tMsg () {
	}
	public PremisUnnbrdAddrRngeMenuResp_tMsg (PremisUnnbrdAddrRngeMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdAddrRngeMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdAddrRngeMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdAddrRngeMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.UnnbrdAddrRngeMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisUnnbrdAddrRngeMenuResp_t (ms, tag); 
	}
	static public PremisUnnbrdAddrRngeMenuResp_t decodePremisUnnbrdAddrRngeMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisUnnbrdAddrRngeMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.UnnbrdAddrRngeMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuPktResp_tMsg.decodeUnnbrdAddrRngeMenuPktResp_t (ms, "UnnbrdAddrRngeMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisUnnbrdAddrRngeMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisUnnbrdAddrRngeMenuResp_t (MMarshalStrategy ms, PremisUnnbrdAddrRngeMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuPktResp_tMsg.encodeUnnbrdAddrRngeMenuPktResp_t (ms, value.UnnbrdAddrRngeMenuPktResp, "UnnbrdAddrRngeMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisUnnbrdAddrRngeMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisUnnbrdAddrRngeMenuResp_t (ms, "PremisUnnbrdAddrRngeMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdAddrRngeMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisUnnbrdAddrRngeMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisUnnbrdAddrRngeMenuResp_t (ms, val, "PremisUnnbrdAddrRngeMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
