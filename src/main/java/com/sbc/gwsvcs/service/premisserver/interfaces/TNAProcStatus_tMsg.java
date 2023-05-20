// $Id: TNAProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TNAProcStatus_tMsg implements MMarshalObject { 
	public TNAProcStatus_t value;

	public TNAProcStatus_tMsg () {
	}
	public TNAProcStatus_tMsg (TNAProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_t();
	value.RTCD = new String ();
	int __seqLength = 0;
	value.TnCtgyCdItem = new com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_t[__seqLength];
	value.INWD_TN = new char[__seqLength];
	value.INWTN_ADDR = new char[__seqLength];
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTNAProcStatus_t (ms, tag); 
	}
	static public TNAProcStatus_t decodeTNAProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TNAProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		{ 
			ms.startSequence ("TnCtgyCdItem", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.TnCtgyCdItem = new com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.TnCtgyCdItem[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_tMsg.decodeTnCtgyCdItem_t (ms, "TnCtgyCdItem");
			} 
		ms.endSequence ("TnCtgyCdItem", false);
		}
		{ 
			ms.startSequence ("INWD_TN", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.INWD_TN = new char[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.INWD_TN[__i] = ms.decodeChar ("INWD_TN");
			} 
		ms.endSequence ("INWD_TN", false);
		}
		{ 
			ms.startSequence ("INWTN_ADDR", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.INWTN_ADDR = new char[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.INWTN_ADDR[__i] = ms.decodeChar ("INWTN_ADDR");
			} 
		ms.endSequence ("INWTN_ADDR", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTNAProcStatus_t (ms, value, tag); 
	}
	static public void encodeTNAProcStatus_t (MMarshalStrategy ms, TNAProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	{ 
		ms.startSequence ("TnCtgyCdItem", true);
		ms.encode (value.TnCtgyCdItem.length, "m_length", true);
		for (int __i = 0; __i < value.TnCtgyCdItem.length; __i++) { 
			com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_tMsg.encodeTnCtgyCdItem_t (ms, value.TnCtgyCdItem[__i], "TnCtgyCdItem");
		}
		ms.endSequence ("TnCtgyCdItem", true);
	}
	{ 
		ms.startSequence ("INWD_TN", true);
		ms.encode (value.INWD_TN.length, "m_length", true);
		for (int __i = 0; __i < value.INWD_TN.length; __i++) { 
			ms.encode (value.INWD_TN[__i], "INWD_TN");
		}
		ms.endSequence ("INWD_TN", true);
	}
	{ 
		ms.startSequence ("INWTN_ADDR", true);
		ms.encode (value.INWTN_ADDR.length, "m_length", true);
		for (int __i = 0; __i < value.INWTN_ADDR.length; __i++) { 
			ms.encode (value.INWTN_ADDR[__i], "INWTN_ADDR");
		}
		ms.endSequence ("INWTN_ADDR", true);
	}
	ms.endStruct (tag, true); 
}
public static TNAProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeTNAProcStatus_t (ms, "TNAProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.TNAProcStatus_tHelper.type(); 
}
public static byte [] toOctet (TNAProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeTNAProcStatus_t (ms, val, "TNAProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
