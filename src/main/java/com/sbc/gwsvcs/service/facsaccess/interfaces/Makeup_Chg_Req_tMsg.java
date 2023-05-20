package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Makeup_Chg_Req_tMsg implements MMarshalObject { 
	public Makeup_Chg_Req_t value;

	public Makeup_Chg_Req_tMsg () {
	}
	public Makeup_Chg_Req_tMsg (Makeup_Chg_Req_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeMakeup_Chg_Req_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeMakeup_Chg_Req_t (ms, tag); 
	}
	static public Makeup_Chg_Req_t decodeMakeup_Chg_Req_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Makeup_Chg_Req_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.C1 = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.decodeC1_Section_t (ms, "C1");
		value.CTL = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.decodeCTL_Section_t (ms, "CTL");
		value.ELMU_Old = com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tMsg.decodeELMU_Section_t (ms, "ELMU_Old");
		value.ELMU_New = com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tMsg.decodeELMU_Section_t (ms, "ELMU_New");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeMakeup_Chg_Req_t (MMarshalStrategy ms, Makeup_Chg_Req_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.encodeC1_Section_t (ms, value.C1, "C1");
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.encodeCTL_Section_t (ms, value.CTL, "CTL");
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tMsg.encodeELMU_Section_t (ms, value.ELMU_Old, "ELMU_Old");
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tMsg.encodeELMU_Section_t (ms, value.ELMU_New, "ELMU_New");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Makeup_Chg_Req_tHelper.type(); 
	}
	public static byte [] toOctet (Makeup_Chg_Req_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeMakeup_Chg_Req_t (ms, val, "Makeup_Chg_Req_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Makeup_Chg_Req_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeMakeup_Chg_Req_t (ms, "Makeup_Chg_Req_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Makeup_Chg_Req_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Makeup_Chg_Req_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Makeup_Chg_Req_t();
		value.Header = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		value.C1 = new com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t();
		value.CTL = new com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t();
		value.ELMU_Old = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t();
		value.ELMU_New = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t();
		return value; 
	} 
}
