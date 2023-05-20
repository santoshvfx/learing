package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class FA_Add_Req_tMsg implements MMarshalObject { 
	public FA_Add_Req_t value;

	public FA_Add_Req_tMsg () {
	}
	public FA_Add_Req_tMsg (FA_Add_Req_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeFA_Add_Req_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeFA_Add_Req_t (ms, tag); 
	}
	static public FA_Add_Req_t decodeFA_Add_Req_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		FA_Add_Req_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.C1 = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.decodeC1_Section_t (ms, "C1");
		value.CTL = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.decodeCTL_Section_t (ms, "CTL");
		value.EPLU = com.sbc.gwsvcs.service.facsaccess.interfaces.EPLU_Section_tMsg.decodeEPLU_Section_t (ms, "EPLU");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeFA_Add_Req_t (MMarshalStrategy ms, FA_Add_Req_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.encodeC1_Section_t (ms, value.C1, "C1");
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.encodeCTL_Section_t (ms, value.CTL, "CTL");
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPLU_Section_tMsg.encodeEPLU_Section_t (ms, value.EPLU, "EPLU");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.FA_Add_Req_tHelper.type(); 
	}
	public static byte [] toOctet (FA_Add_Req_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeFA_Add_Req_t (ms, val, "FA_Add_Req_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static FA_Add_Req_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeFA_Add_Req_t (ms, "FA_Add_Req_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.FA_Add_Req_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.FA_Add_Req_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.FA_Add_Req_t();
		value.Header = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		value.C1 = new com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t();
		value.CTL = new com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t();
		value.EPLU = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPLU_Section_t();
		return value; 
	} 
}
