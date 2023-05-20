// $Id: UnnbrdAddrRngeMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdAddrRngeMenuPktResp_tMsg implements MMarshalObject { 
	public UnnbrdAddrRngeMenuPktResp_t value;

	public UnnbrdAddrRngeMenuPktResp_tMsg () {
	}
	public UnnbrdAddrRngeMenuPktResp_tMsg (UnnbrdAddrRngeMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.UnnbrdAddrRngeMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnnbrdAddrRngeMenuPktResp_t (ms, tag); 
	}
	static public UnnbrdAddrRngeMenuPktResp_t decodeUnnbrdAddrRngeMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnnbrdAddrRngeMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("UnnbrdAddrRngeMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.UnnbrdAddrRngeMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.UnnbrdAddrRngeMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_tMsg.decodeUnnbrdAddrRngeMenuProcStatus_t (ms, "UnnbrdAddrRngeMenuProcStatus");
			} 
		ms.endSequence ("UnnbrdAddrRngeMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnnbrdAddrRngeMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeUnnbrdAddrRngeMenuPktResp_t (MMarshalStrategy ms, UnnbrdAddrRngeMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("UnnbrdAddrRngeMenuProcStatus", true);
			ms.encode (value.UnnbrdAddrRngeMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.UnnbrdAddrRngeMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_tMsg.encodeUnnbrdAddrRngeMenuProcStatus_t (ms, value.UnnbrdAddrRngeMenuProcStatus[__i], "UnnbrdAddrRngeMenuProcStatus");
			}
			ms.endSequence ("UnnbrdAddrRngeMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static UnnbrdAddrRngeMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUnnbrdAddrRngeMenuPktResp_t (ms, "UnnbrdAddrRngeMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (UnnbrdAddrRngeMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUnnbrdAddrRngeMenuPktResp_t (ms, val, "UnnbrdAddrRngeMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
