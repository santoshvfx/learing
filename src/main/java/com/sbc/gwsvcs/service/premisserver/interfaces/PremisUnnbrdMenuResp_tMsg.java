// $Id: PremisUnnbrdMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisUnnbrdMenuResp_tMsg implements MMarshalObject { 
	public PremisUnnbrdMenuResp_t value;

	public PremisUnnbrdMenuResp_tMsg () {
	}
	public PremisUnnbrdMenuResp_tMsg (PremisUnnbrdMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.UnnbrdMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisUnnbrdMenuResp_t (ms, tag); 
	}
	static public PremisUnnbrdMenuResp_t decodePremisUnnbrdMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisUnnbrdMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.UnnbrdMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_tMsg.decodeUnnbrdMenuPktResp_t (ms, "UnnbrdMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisUnnbrdMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisUnnbrdMenuResp_t (MMarshalStrategy ms, PremisUnnbrdMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_tMsg.encodeUnnbrdMenuPktResp_t (ms, value.UnnbrdMenuPktResp, "UnnbrdMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisUnnbrdMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisUnnbrdMenuResp_t (ms, "PremisUnnbrdMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisUnnbrdMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisUnnbrdMenuResp_t (ms, val, "PremisUnnbrdMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
