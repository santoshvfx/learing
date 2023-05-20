// $Id: UnadrmGsgmPktResp_tMsg.java,v 1.1 2002/09/29 04:28:16 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnadrmGsgmPktResp_tMsg implements MMarshalObject { 
	public UnadrmGsgmPktResp_t value;

	public UnadrmGsgmPktResp_tMsg () {
	}
	public UnadrmGsgmPktResp_tMsg (UnadrmGsgmPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.UnadrmGsgmProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnadrmGsgmPktResp_t (ms, tag); 
	}
	static public UnadrmGsgmPktResp_t decodeUnadrmGsgmPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnadrmGsgmPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("UnadrmGsgmProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.UnadrmGsgmProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.UnadrmGsgmProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_tMsg.decodeUnadrmGsgmProcStatus_t (ms, "UnadrmGsgmProcStatus");
			} 
		ms.endSequence ("UnadrmGsgmProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnadrmGsgmPktResp_t (ms, value, tag); 
	}
	static public void encodeUnadrmGsgmPktResp_t (MMarshalStrategy ms, UnadrmGsgmPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("UnadrmGsgmProcStatus", true);
			ms.encode (value.UnadrmGsgmProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.UnadrmGsgmProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_tMsg.encodeUnadrmGsgmProcStatus_t (ms, value.UnadrmGsgmProcStatus[__i], "UnadrmGsgmProcStatus");
			}
			ms.endSequence ("UnadrmGsgmProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static UnadrmGsgmPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUnadrmGsgmPktResp_t (ms, "UnadrmGsgmPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (UnadrmGsgmPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUnadrmGsgmPktResp_t (ms, val, "UnadrmGsgmPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
