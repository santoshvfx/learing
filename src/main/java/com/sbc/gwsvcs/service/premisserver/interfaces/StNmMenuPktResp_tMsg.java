// $Id: StNmMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class StNmMenuPktResp_tMsg implements MMarshalObject { 
	public StNmMenuPktResp_t value;

	public StNmMenuPktResp_tMsg () {
	}
	public StNmMenuPktResp_tMsg (StNmMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.StNmMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeStNmMenuPktResp_t (ms, tag); 
	}
	static public StNmMenuPktResp_t decodeStNmMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		StNmMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("StNmMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.StNmMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.StNmMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_tMsg.decodeStNmMenuProcStatus_t (ms, "StNmMenuProcStatus");
			} 
		ms.endSequence ("StNmMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeStNmMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeStNmMenuPktResp_t (MMarshalStrategy ms, StNmMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("StNmMenuProcStatus", true);
			ms.encode (value.StNmMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.StNmMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_tMsg.encodeStNmMenuProcStatus_t (ms, value.StNmMenuProcStatus[__i], "StNmMenuProcStatus");
			}
			ms.endSequence ("StNmMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static StNmMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeStNmMenuPktResp_t (ms, "StNmMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (StNmMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeStNmMenuPktResp_t (ms, val, "StNmMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
