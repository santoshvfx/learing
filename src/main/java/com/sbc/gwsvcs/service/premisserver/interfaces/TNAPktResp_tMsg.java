// $Id: TNAPktResp_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TNAPktResp_tMsg implements MMarshalObject { 
	public TNAPktResp_t value;

	public TNAPktResp_tMsg () {
	}
	public TNAPktResp_tMsg (TNAPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.TNAProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTNAPktResp_t (ms, tag); 
	}
	static public TNAPktResp_t decodeTNAPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TNAPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("TNAProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.TNAProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.TNAProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_tMsg.decodeTNAProcStatus_t (ms, "TNAProcStatus");
			} 
		ms.endSequence ("TNAProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTNAPktResp_t (ms, value, tag); 
	}
	static public void encodeTNAPktResp_t (MMarshalStrategy ms, TNAPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("TNAProcStatus", true);
			ms.encode (value.TNAProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.TNAProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_tMsg.encodeTNAProcStatus_t (ms, value.TNAProcStatus[__i], "TNAProcStatus");
			}
			ms.endSequence ("TNAProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static TNAPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTNAPktResp_t (ms, "TNAPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.TNAPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (TNAPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTNAPktResp_t (ms, val, "TNAPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
