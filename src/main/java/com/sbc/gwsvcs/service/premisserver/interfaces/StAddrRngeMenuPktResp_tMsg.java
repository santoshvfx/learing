// $Id: StAddrRngeMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class StAddrRngeMenuPktResp_tMsg implements MMarshalObject { 
	public StAddrRngeMenuPktResp_t value;

	public StAddrRngeMenuPktResp_tMsg () {
	}
	public StAddrRngeMenuPktResp_tMsg (StAddrRngeMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.StAddrRngeMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeStAddrRngeMenuPktResp_t (ms, tag); 
	}
	static public StAddrRngeMenuPktResp_t decodeStAddrRngeMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		StAddrRngeMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("StAddrRngeMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.StAddrRngeMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.StAddrRngeMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_tMsg.decodeStAddrRngeMenuProcStatus_t (ms, "StAddrRngeMenuProcStatus");
			} 
		ms.endSequence ("StAddrRngeMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeStAddrRngeMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeStAddrRngeMenuPktResp_t (MMarshalStrategy ms, StAddrRngeMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("StAddrRngeMenuProcStatus", true);
			ms.encode (value.StAddrRngeMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.StAddrRngeMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_tMsg.encodeStAddrRngeMenuProcStatus_t (ms, value.StAddrRngeMenuProcStatus[__i], "StAddrRngeMenuProcStatus");
			}
			ms.endSequence ("StAddrRngeMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static StAddrRngeMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeStAddrRngeMenuPktResp_t (ms, "StAddrRngeMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (StAddrRngeMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeStAddrRngeMenuPktResp_t (ms, val, "StAddrRngeMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
