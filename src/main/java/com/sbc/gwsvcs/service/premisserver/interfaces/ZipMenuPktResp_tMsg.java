// $Id: ZipMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:18 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZipMenuPktResp_tMsg implements MMarshalObject { 
	public ZipMenuPktResp_t value;

	public ZipMenuPktResp_tMsg () {
	}
	public ZipMenuPktResp_tMsg (ZipMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.ZipMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZipMenuPktResp_t (ms, tag); 
	}
	static public ZipMenuPktResp_t decodeZipMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZipMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("ZipMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.ZipMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.ZipMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_tMsg.decodeZipMenuProcStatus_t (ms, "ZipMenuProcStatus");
			} 
		ms.endSequence ("ZipMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZipMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeZipMenuPktResp_t (MMarshalStrategy ms, ZipMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("ZipMenuProcStatus", true);
			ms.encode (value.ZipMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.ZipMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_tMsg.encodeZipMenuProcStatus_t (ms, value.ZipMenuProcStatus[__i], "ZipMenuProcStatus");
			}
			ms.endSequence ("ZipMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static ZipMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeZipMenuPktResp_t (ms, "ZipMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (ZipMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeZipMenuPktResp_t (ms, val, "ZipMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
