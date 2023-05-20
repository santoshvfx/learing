// $Id: TnMatchMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnMatchMenuPktResp_tMsg implements MMarshalObject { 
	public TnMatchMenuPktResp_t value;

	public TnMatchMenuPktResp_tMsg () {
	}
	public TnMatchMenuPktResp_tMsg (TnMatchMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.TnMatchMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnMatchMenuPktResp_t (ms, tag); 
	}
	static public TnMatchMenuPktResp_t decodeTnMatchMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnMatchMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("TnMatchMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.TnMatchMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.TnMatchMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_tMsg.decodeTnMatchMenuProcStatus_t (ms, "TnMatchMenuProcStatus");
			} 
		ms.endSequence ("TnMatchMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnMatchMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeTnMatchMenuPktResp_t (MMarshalStrategy ms, TnMatchMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("TnMatchMenuProcStatus", true);
			ms.encode (value.TnMatchMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.TnMatchMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_tMsg.encodeTnMatchMenuProcStatus_t (ms, value.TnMatchMenuProcStatus[__i], "TnMatchMenuProcStatus");
			}
			ms.endSequence ("TnMatchMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static TnMatchMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTnMatchMenuPktResp_t (ms, "TnMatchMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (TnMatchMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTnMatchMenuPktResp_t (ms, val, "TnMatchMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
