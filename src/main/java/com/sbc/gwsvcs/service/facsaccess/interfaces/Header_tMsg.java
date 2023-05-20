package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Header_tMsg implements MMarshalObject { 
	public Header_t value;

	public Header_tMsg () {
	}
	public Header_tMsg (Header_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeHeader_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeHeader_t (ms, tag); 
	}
	static public Header_t decodeHeader_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Header_t value = create();
		ms.startStruct (tag, false);
		value.CLNT_UUID = ms.decodeOctetString (15, "CLNT_UUID");
		value.CLNT_CONTEXT = ms.decodeOctetString (31, "CLNT_CONTEXT");
		value.HOST_NAME = ms.decodeOctetString (41, "HOST_NAME");
		value.TRNSACT_ID = ms.decodeOctetString (41, "TRNSACT_ID");
		value.TRNSPT_CD = com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eMsg.decodeTrnsptType_e (ms, "TRNSPT_CD");
		value.RPLY_DEST = ms.decodeOctetString (121, "RPLY_DEST");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeHeader_t (MMarshalStrategy ms, Header_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CLNT_UUID, 15, "CLNT_UUID");
		ms.encode (value.CLNT_CONTEXT, 31, "CLNT_CONTEXT");
		ms.encode (value.HOST_NAME, 41, "HOST_NAME");
		ms.encode (value.TRNSACT_ID, 41, "TRNSACT_ID");
		com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_eMsg.encodeTrnsptType_e (ms, value.TRNSPT_CD, "TRNSPT_CD");
		ms.encode (value.RPLY_DEST, 121, "RPLY_DEST");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tHelper.type(); 
	}
	public static byte [] toOctet (Header_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeHeader_t (ms, val, "Header_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Header_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeHeader_t (ms, "Header_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		value.CLNT_UUID = new String ();
		value.CLNT_CONTEXT = new String ();
		value.HOST_NAME = new String ();
		value.TRNSACT_ID = new String ();
		value.TRNSPT_CD = com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e.RPC_TRNSPT;
		value.RPLY_DEST = new String ();
		return value; 
	} 
}
