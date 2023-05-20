// $Id: SuppAddrMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SuppAddrMenuPktResp_tMsg implements MMarshalObject { 
	public SuppAddrMenuPktResp_t value;

	public SuppAddrMenuPktResp_tMsg () {
	}
	public SuppAddrMenuPktResp_tMsg (SuppAddrMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.SuppAddrMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSuppAddrMenuPktResp_t (ms, tag); 
	}
	static public SuppAddrMenuPktResp_t decodeSuppAddrMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SuppAddrMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("SuppAddrMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SuppAddrMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SuppAddrMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_tMsg.decodeSuppAddrMenuProcStatus_t (ms, "SuppAddrMenuProcStatus");
			} 
		ms.endSequence ("SuppAddrMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSuppAddrMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeSuppAddrMenuPktResp_t (MMarshalStrategy ms, SuppAddrMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("SuppAddrMenuProcStatus", true);
			ms.encode (value.SuppAddrMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.SuppAddrMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_tMsg.encodeSuppAddrMenuProcStatus_t (ms, value.SuppAddrMenuProcStatus[__i], "SuppAddrMenuProcStatus");
			}
			ms.endSequence ("SuppAddrMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static SuppAddrMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSuppAddrMenuPktResp_t (ms, "SuppAddrMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (SuppAddrMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSuppAddrMenuPktResp_t (ms, val, "SuppAddrMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
