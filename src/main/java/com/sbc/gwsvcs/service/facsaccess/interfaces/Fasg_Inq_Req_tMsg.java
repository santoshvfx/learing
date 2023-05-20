package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Fasg_Inq_Req_tMsg implements MMarshalObject { 
	public Fasg_Inq_Req_t value;

	public Fasg_Inq_Req_tMsg () {
	}
	public Fasg_Inq_Req_tMsg (Fasg_Inq_Req_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeFasg_Inq_Req_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeFasg_Inq_Req_t (ms, tag); 
	}
	static public Fasg_Inq_Req_t decodeFasg_Inq_Req_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Fasg_Inq_Req_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.C1 = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.decodeC1_Section_t (ms, "C1");
		value.CTL = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.decodeCTL_Section_t (ms, "CTL");
		value.INQFASG = com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_tMsg.decodeINQ_FASG_Section_t (ms, "INQFASG");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeFasg_Inq_Req_t (MMarshalStrategy ms, Fasg_Inq_Req_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.encodeC1_Section_t (ms, value.C1, "C1");
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.encodeCTL_Section_t (ms, value.CTL, "CTL");
		com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_tMsg.encodeINQ_FASG_Section_t (ms, value.INQFASG, "INQFASG");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_tHelper.type(); 
	}
	public static byte [] toOctet (Fasg_Inq_Req_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeFasg_Inq_Req_t (ms, val, "Fasg_Inq_Req_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Fasg_Inq_Req_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeFasg_Inq_Req_t (ms, "Fasg_Inq_Req_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_t();
		value.Header = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		value.C1 = new com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t();
		value.CTL = new com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t();
		value.INQFASG = new com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_t();
		return value; 
	} 
}
