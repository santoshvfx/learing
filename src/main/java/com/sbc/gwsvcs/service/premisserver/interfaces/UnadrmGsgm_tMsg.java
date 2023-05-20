// $Id: UnadrmGsgm_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnadrmGsgm_tMsg implements MMarshalObject { 
	public UnadrmGsgm_t value;

	public UnadrmGsgm_tMsg () {
	}
	public UnadrmGsgm_tMsg (UnadrmGsgm_t initial) { 
		value = initial; 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t create () { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t();
		int __seqLength = 0;
		value.UnnbrdAddrRngeMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t[__seqLength];
		value.GeoSegMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t[__seqLength];
		return value; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnadrmGsgm_t (ms, tag); 
	}
	static public UnadrmGsgm_t decodeUnadrmGsgm_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnadrmGsgm_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("UnnbrdAddrRngeMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.UnnbrdAddrRngeMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.UnnbrdAddrRngeMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tMsg.decodeUnnbrdAddrRngeMenuItem_t (ms, "UnnbrdAddrRngeMenu");
			} 
		ms.endSequence ("UnnbrdAddrRngeMenu", false);
		}
		{ 
			ms.startSequence ("GeoSegMenu", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.GeoSegMenu = new com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.GeoSegMenu[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_tMsg.decodeGeoSegMenuItem_t (ms, "GeoSegMenu");
			} 
		ms.endSequence ("GeoSegMenu", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnadrmGsgm_t (ms, value, tag); 
	}
	static public void encodeUnadrmGsgm_t (MMarshalStrategy ms, UnadrmGsgm_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("UnnbrdAddrRngeMenu", true);
			ms.encode (value.UnnbrdAddrRngeMenu.length, "m_length", true);
			for (int __i = 0; __i < value.UnnbrdAddrRngeMenu.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_tMsg.encodeUnnbrdAddrRngeMenuItem_t (ms, value.UnnbrdAddrRngeMenu[__i], "UnnbrdAddrRngeMenu");
			}
			ms.endSequence ("UnnbrdAddrRngeMenu", true);
		}
		{ 
			ms.startSequence ("GeoSegMenu", true);
			ms.encode (value.GeoSegMenu.length, "m_length", true);
			for (int __i = 0; __i < value.GeoSegMenu.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_tMsg.encodeGeoSegMenuItem_t (ms, value.GeoSegMenu[__i], "GeoSegMenu");
			}
			ms.endSequence ("GeoSegMenu", true);
		}
		ms.endStruct (tag, true); 
	}
	public static UnadrmGsgm_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUnadrmGsgm_t (ms, "UnadrmGsgm_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tHelper.type(); 
	}
	public static byte [] toOctet (UnadrmGsgm_t val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUnadrmGsgm_t (ms, val, "UnadrmGsgm_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
}
