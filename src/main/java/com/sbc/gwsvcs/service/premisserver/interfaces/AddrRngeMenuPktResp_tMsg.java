// $Id: AddrRngeMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AddrRngeMenuPktResp_tMsg implements MMarshalObject { 
	public AddrRngeMenuPktResp_t value;

	public AddrRngeMenuPktResp_tMsg () {
	}
	public AddrRngeMenuPktResp_tMsg (AddrRngeMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.AddrRngeMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAddrRngeMenuPktResp_t (ms, tag); 
	}
	static public AddrRngeMenuPktResp_t decodeAddrRngeMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AddrRngeMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("AddrRngeMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.AddrRngeMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AddrRngeMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_tMsg.decodeAddrRngeMenuProcStatus_t (ms, "AddrRngeMenuProcStatus");
			} 
		ms.endSequence ("AddrRngeMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAddrRngeMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeAddrRngeMenuPktResp_t (MMarshalStrategy ms, AddrRngeMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("AddrRngeMenuProcStatus", true);
			ms.encode (value.AddrRngeMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.AddrRngeMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_tMsg.encodeAddrRngeMenuProcStatus_t (ms, value.AddrRngeMenuProcStatus[__i], "AddrRngeMenuProcStatus");
			}
			ms.endSequence ("AddrRngeMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static AddrRngeMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAddrRngeMenuPktResp_t (ms, "AddrRngeMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (AddrRngeMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAddrRngeMenuPktResp_t (ms, val, "AddrRngeMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
