// $Id: PremisBascAddrMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisBascAddrMenuResp_tMsg implements MMarshalObject { 
	public PremisBascAddrMenuResp_t value;

	public PremisBascAddrMenuResp_tMsg () {
	}
	public PremisBascAddrMenuResp_tMsg (PremisBascAddrMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisBascAddrMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisBascAddrMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisBascAddrMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.BascAddrMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisBascAddrMenuResp_t (ms, tag); 
	}
	static public PremisBascAddrMenuResp_t decodePremisBascAddrMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisBascAddrMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.BascAddrMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_tMsg.decodeBascAddrMenuPktResp_t (ms, "BascAddrMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisBascAddrMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisBascAddrMenuResp_t (MMarshalStrategy ms, PremisBascAddrMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_tMsg.encodeBascAddrMenuPktResp_t (ms, value.BascAddrMenuPktResp, "BascAddrMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisBascAddrMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisBascAddrMenuResp_t (ms, "PremisBascAddrMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisBascAddrMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisBascAddrMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisBascAddrMenuResp_t (ms, val, "PremisBascAddrMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
