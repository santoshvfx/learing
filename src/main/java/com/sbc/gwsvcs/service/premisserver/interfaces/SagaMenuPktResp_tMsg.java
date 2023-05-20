// $Id: SagaMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SagaMenuPktResp_tMsg implements MMarshalObject { 
	public SagaMenuPktResp_t value;

	public SagaMenuPktResp_tMsg () {
	}
	public SagaMenuPktResp_tMsg (SagaMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.SagaMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSagaMenuPktResp_t (ms, tag); 
	}
	static public SagaMenuPktResp_t decodeSagaMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SagaMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("SagaMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SagaMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SagaMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_tMsg.decodeSagaMenuProcStatus_t (ms, "SagaMenuProcStatus");
			} 
		ms.endSequence ("SagaMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSagaMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeSagaMenuPktResp_t (MMarshalStrategy ms, SagaMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("SagaMenuProcStatus", true);
			ms.encode (value.SagaMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.SagaMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_tMsg.encodeSagaMenuProcStatus_t (ms, value.SagaMenuProcStatus[__i], "SagaMenuProcStatus");
			}
			ms.endSequence ("SagaMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static SagaMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSagaMenuPktResp_t (ms, "SagaMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (SagaMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSagaMenuPktResp_t (ms, val, "SagaMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
