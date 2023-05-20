// $Id: PremisSagaMenuResp_tMsg.java,v 1.1 2002/09/29 04:28:12 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PremisSagaMenuResp_tMsg implements MMarshalObject { 
	public PremisSagaMenuResp_t value;

	public PremisSagaMenuResp_tMsg () {
	}
	public PremisSagaMenuResp_tMsg (PremisSagaMenuResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PremisSagaMenuResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PremisSagaMenuResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PremisSagaMenuResp_t();
		value.Header = new com.sbc.gwsvcs.service.premisserver.interfaces.Header_t();
		value.Scratch = new com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t();
		value.SagaMenuPktResp = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuPktResp_t();
		value.AppPrmRespItem = new com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePremisSagaMenuResp_t (ms, tag); 
	}
	static public PremisSagaMenuResp_t decodePremisSagaMenuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PremisSagaMenuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Scratch = com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.decodeScratch_t (ms, "Scratch");
		value.SagaMenuPktResp = com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuPktResp_tMsg.decodeSagaMenuPktResp_t (ms, "SagaMenuPktResp");
		value.AppPrmRespItem = com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.decodeAppPrmRespItem_t (ms, "AppPrmRespItem");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePremisSagaMenuResp_t (ms, value, tag); 
	}
	static public void encodePremisSagaMenuResp_t (MMarshalStrategy ms, PremisSagaMenuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_tMsg.encodeScratch_t (ms, value.Scratch, "Scratch");
		com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuPktResp_tMsg.encodeSagaMenuPktResp_t (ms, value.SagaMenuPktResp, "SagaMenuPktResp");
		com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_tMsg.encodeAppPrmRespItem_t (ms, value.AppPrmRespItem, "AppPrmRespItem");
		ms.endStruct (tag, true); 
	}
	public static PremisSagaMenuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePremisSagaMenuResp_t (ms, "PremisSagaMenuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PremisSagaMenuResp_tHelper.type(); 
	}
	public static byte [] toOctet (PremisSagaMenuResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePremisSagaMenuResp_t (ms, val, "PremisSagaMenuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
