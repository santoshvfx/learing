// $Id: DescNmMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:10 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DescNmMenuPktResp_tMsg implements MMarshalObject { 
	public DescNmMenuPktResp_t value;

	public DescNmMenuPktResp_tMsg () {
	}
	public DescNmMenuPktResp_tMsg (DescNmMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.DescNmMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDescNmMenuPktResp_t (ms, tag); 
	}
	static public DescNmMenuPktResp_t decodeDescNmMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DescNmMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("DescNmMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.DescNmMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.DescNmMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_tMsg.decodeDescNmMenuProcStatus_t (ms, "DescNmMenuProcStatus");
			} 
		ms.endSequence ("DescNmMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDescNmMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeDescNmMenuPktResp_t (MMarshalStrategy ms, DescNmMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("DescNmMenuProcStatus", true);
			ms.encode (value.DescNmMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.DescNmMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_tMsg.encodeDescNmMenuProcStatus_t (ms, value.DescNmMenuProcStatus[__i], "DescNmMenuProcStatus");
			}
			ms.endSequence ("DescNmMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static DescNmMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeDescNmMenuPktResp_t (ms, "DescNmMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (DescNmMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeDescNmMenuPktResp_t (ms, val, "DescNmMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
