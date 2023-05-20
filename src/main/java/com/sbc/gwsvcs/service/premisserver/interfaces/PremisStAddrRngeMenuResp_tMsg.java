// $Id: PremisStAddrRngeMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisStAddrRngeMenuResp_tMsg implements MMarshalObject { 
	public PremisStAddrRngeMenuResp_t value;

	public PremisStAddrRngeMenuResp_tMsg () {
	}
	public PremisStAddrRngeMenuResp_tMsg (PremisStAddrRngeMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisStAddrRngeMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisStAddrRngeMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisStAddrRngeMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.StAddrRngeMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisStAddrRngeMenuResp_t (ms, tag); 
	}
	static public PremisStAddrRngeMenuResp_t decodePremisStAddrRngeMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisStAddrRngeMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.StAddrRngeMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuPktResp_tMsg.decodeStAddrRngeMenuPktResp_t (ms, "StAddrRngeMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisStAddrRngeMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisStAddrRngeMenuResp_t (MMarshalStrategy ms, PremisStAddrRngeMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuPktResp_tMsg.encodeStAddrRngeMenuPktResp_t (ms, value.StAddrRngeMenuPktResp, "StAddrRngeMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisStAddrRngeMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisStAddrRngeMenuResp_t (ms, "PremisStAddrRngeMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisStAddrRngeMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisStAddrRngeMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisStAddrRngeMenuResp_t (ms, val, "PremisStAddrRngeMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
