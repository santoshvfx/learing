// $Id: SuppAddrMenuItem_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SuppAddrMenuItem_tMsg implements MMarshalObject { 
	public SuppAddrMenuItem_t value;

	public SuppAddrMenuItem_tMsg () {
	}
	public SuppAddrMenuItem_tMsg (SuppAddrMenuItem_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t();
		value.SuppAddrInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t();
		int __seqLength = 0;
		value.SuppLnInfoItem = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSuppAddrMenuItem_t (ms, tag); 
	}
	static public SuppAddrMenuItem_t decodeSuppAddrMenuItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SuppAddrMenuItem_t value = create();
		ms.startStruct (tag, false);
		value.SuppAddrInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.decodeSuppAddrInfo_t (ms, "SuppAddrInfo");
		{ 
			ms.startSequence ("SuppLnInfoItem", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SuppLnInfoItem = new com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SuppLnInfoItem[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_tMsg.decodeSuppLnInfoItem_t (ms, "SuppLnInfoItem");
			} 
		ms.endSequence ("SuppLnInfoItem", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSuppAddrMenuItem_t (ms, value, tag); 
	}
	static public void encodeSuppAddrMenuItem_t (MMarshalStrategy ms, SuppAddrMenuItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_tMsg.encodeSuppAddrInfo_t (ms, value.SuppAddrInfo, "SuppAddrInfo");
		{ 
			ms.startSequence ("SuppLnInfoItem", true);
			ms.encode (value.SuppLnInfoItem.length, "m_length", true);
			for (int __i = 0; __i < value.SuppLnInfoItem.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfoItem_tMsg.encodeSuppLnInfoItem_t (ms, value.SuppLnInfoItem[__i], "SuppLnInfoItem");
			}
			ms.endSequence ("SuppLnInfoItem", true);
		}
		ms.endStruct (tag, true); 
	}
	public static SuppAddrMenuItem_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSuppAddrMenuItem_t (ms, "SuppAddrMenuItem_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_tHelper.type(); 
	}
	public static byte [] toOctet (SuppAddrMenuItem_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSuppAddrMenuItem_t (ms, val, "SuppAddrMenuItem_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
