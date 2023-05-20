// $Id: PrmAddr_tMsg.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PrmAddr_tMsg implements MMarshalObject { 
	public PrmAddr_t value;

	public PrmAddr_tMsg () {
	}
	public PrmAddr_tMsg (PrmAddr_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t();
		value.BascAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t();
		value.SuppAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t();
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePrmAddr_t (ms, tag); 
	}
	static public PrmAddr_t decodePrmAddr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PrmAddr_t value = create();
		ms.startStruct (tag, false);
		value.BascAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tMsg.decodeBascAddrInfo_t (ms, "BascAddrInfo");
		value.SuppAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.decodeSuppAddrInfo_t (ms, "SuppAddrInfo");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePrmAddr_t (ms, value, tag); 
	}
	static public void encodePrmAddr_t (MMarshalStrategy ms, PrmAddr_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_tMsg.encodeBascAddrInfo_t (ms, value.BascAddrInfo, "BascAddrInfo");
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.encodeSuppAddrInfo_t (ms, value.SuppAddrInfo, "SuppAddrInfo");
		ms.endStruct (tag, true); 
	}
	public static PrmAddr_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePrmAddr_t (ms, "PrmAddr_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tHelper.type(); 
	}
	public static byte [] toOctet (PrmAddr_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePrmAddr_t (ms, val, "PrmAddr_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
