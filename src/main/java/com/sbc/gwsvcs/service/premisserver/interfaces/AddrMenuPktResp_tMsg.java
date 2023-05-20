// $Id: AddrMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddrMenuPktResp_tMsg implements MMarshalObject { 
	public AddrMenuPktResp_t value;

	public AddrMenuPktResp_tMsg () {
	}
	public AddrMenuPktResp_tMsg (AddrMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.AddrMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddrMenuPktResp_t (ms, tag); 
	}
	static public AddrMenuPktResp_t decodeAddrMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddrMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("AddrMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.AddrMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AddrMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_tMsg.decodeAddrMenuProcStatus_t (ms, "AddrMenuProcStatus");
			} 
		ms.endSequence ("AddrMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddrMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeAddrMenuPktResp_t (MMarshalStrategy ms, AddrMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("AddrMenuProcStatus", true);
			ms.encode (value.AddrMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.AddrMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_tMsg.encodeAddrMenuProcStatus_t (ms, value.AddrMenuProcStatus[__i], "AddrMenuProcStatus");
			}
			ms.endSequence ("AddrMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static AddrMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAddrMenuPktResp_t (ms, "AddrMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (AddrMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAddrMenuPktResp_t (ms, val, "AddrMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
