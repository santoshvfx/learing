// $Id: GeoSegMenuPktResp_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GeoSegMenuPktResp_tMsg implements MMarshalObject { 
	public GeoSegMenuPktResp_t value;

	public GeoSegMenuPktResp_tMsg () {
	}
	public GeoSegMenuPktResp_tMsg (GeoSegMenuPktResp_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuPktResp_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuPktResp_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuPktResp_t();
		value.TS = new com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_t();
		int __seqLength = 0;
		value.GeoSegMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGeoSegMenuPktResp_t (ms, tag); 
	}
	static public GeoSegMenuPktResp_t decodeGeoSegMenuPktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GeoSegMenuPktResp_t value = create();
		ms.startStruct (tag, false);
		value.TS = com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "TS");
		{ 
			ms.startSequence ("GeoSegMenuProcStatus", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.GeoSegMenuProcStatus = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.GeoSegMenuProcStatus[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_tMsg.decodeGeoSegMenuProcStatus_t (ms, "GeoSegMenuProcStatus");
			} 
		ms.endSequence ("GeoSegMenuProcStatus", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGeoSegMenuPktResp_t (ms, value, tag); 
	}
	static public void encodeGeoSegMenuPktResp_t (MMarshalStrategy ms, GeoSegMenuPktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.TS, "TS");
		{ 
			ms.startSequence ("GeoSegMenuProcStatus", true);
			ms.encode (value.GeoSegMenuProcStatus.length, "m_length", true);
			for (int __i = 0; __i < value.GeoSegMenuProcStatus.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_tMsg.encodeGeoSegMenuProcStatus_t (ms, value.GeoSegMenuProcStatus[__i], "GeoSegMenuProcStatus");
			}
			ms.endSequence ("GeoSegMenuProcStatus", true);
		}
		ms.endStruct (tag, true); 
	}
	public static GeoSegMenuPktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeGeoSegMenuPktResp_t (ms, "GeoSegMenuPktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuPktResp_tHelper.type(); 
	}
	public static byte [] toOctet (GeoSegMenuPktResp_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeGeoSegMenuPktResp_t (ms, val, "GeoSegMenuPktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
