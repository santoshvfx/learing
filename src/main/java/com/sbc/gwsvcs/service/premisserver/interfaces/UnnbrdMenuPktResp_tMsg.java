// $Id: UnnbrdMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdMenuPktResp_tMsg implements MMarshalObject { 
	public UnnbrdMenuPktResp_t value;

	public UnnbrdMenuPktResp_tMsg () {
	}
	public UnnbrdMenuPktResp_tMsg (UnnbrdMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.UnnbrdMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnnbrdMenuPktResp_t (ms, tag); 
	}
	static public UnnbrdMenuPktResp_t decodeUnnbrdMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnnbrdMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("UnnbrdMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.UnnbrdMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.UnnbrdMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_tMsg.decodeUnnbrdMenuProcStatus_t (ms, "UnnbrdMenuProcStatus");
			} 
		ms.endSequence ("UnnbrdMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnnbrdMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeUnnbrdMenuPktResp_t (MMarshalStrategy ms, UnnbrdMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("UnnbrdMenuProcStatus", true);
			ms.encode (value.UnnbrdMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.UnnbrdMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_tMsg.encodeUnnbrdMenuProcStatus_t (ms, value.UnnbrdMenuProcStatus[__i], "UnnbrdMenuProcStatus");
			}
			ms.endSequence ("UnnbrdMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static UnnbrdMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUnnbrdMenuPktResp_t (ms, "UnnbrdMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (UnnbrdMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUnnbrdMenuPktResp_t (ms, val, "UnnbrdMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
