// $Id: PremisGeoSegMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisGeoSegMenuResp_tMsg implements MMarshalObject { 
	public PremisGeoSegMenuResp_t value;

	public PremisGeoSegMenuResp_tMsg () {
	}
	public PremisGeoSegMenuResp_tMsg (PremisGeoSegMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisGeoSegMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisGeoSegMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisGeoSegMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.GeoSegMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisGeoSegMenuResp_t (ms, tag); 
	}
	static public PremisGeoSegMenuResp_t decodePremisGeoSegMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisGeoSegMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.GeoSegMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuPktResp_tMsg.decodeGeoSegMenuPktResp_t (ms, "GeoSegMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisGeoSegMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisGeoSegMenuResp_t (MMarshalStrategy ms, PremisGeoSegMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuPktResp_tMsg.encodeGeoSegMenuPktResp_t (ms, value.GeoSegMenuPktResp, "GeoSegMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisGeoSegMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisGeoSegMenuResp_t (ms, "PremisGeoSegMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisGeoSegMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisGeoSegMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisGeoSegMenuResp_t (ms, val, "PremisGeoSegMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
