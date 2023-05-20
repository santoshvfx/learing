// $Id: BascAddrMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class BascAddrMenuPktResp_tMsg implements MMarshalObject { 
	public BascAddrMenuPktResp_t value;

	public BascAddrMenuPktResp_tMsg () {
	}
	public BascAddrMenuPktResp_tMsg (BascAddrMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.BascAddrMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeBascAddrMenuPktResp_t (ms, tag); 
	}
	static public BascAddrMenuPktResp_t decodeBascAddrMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		BascAddrMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("BascAddrMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.BascAddrMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.BascAddrMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_tMsg.decodeBascAddrMenuProcStatus_t (ms, "BascAddrMenuProcStatus");
			} 
		ms.endSequence ("BascAddrMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeBascAddrMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeBascAddrMenuPktResp_t (MMarshalStrategy ms, BascAddrMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("BascAddrMenuProcStatus", true);
			ms.encode (value.BascAddrMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.BascAddrMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_tMsg.encodeBascAddrMenuProcStatus_t (ms, value.BascAddrMenuProcStatus[__i], "BascAddrMenuProcStatus");
			}
			ms.endSequence ("BascAddrMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static BascAddrMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeBascAddrMenuPktResp_t (ms, "BascAddrMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (BascAddrMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeBascAddrMenuPktResp_t (ms, val, "BascAddrMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
