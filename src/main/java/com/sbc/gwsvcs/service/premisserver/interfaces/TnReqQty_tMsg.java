// $Id: TnReqQty_tMsg.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnReqQty_tMsg implements MMarshalObject { 
	public int value;
	
	public TnReqQty_tMsg() { }
	public TnReqQty_tMsg (int init) { value = init; }
	public static int create() {
		int ret = (int) 0;
		return ret;
		}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeTnReqQty_t (ms, tag); 
	}
	public static int decodeTnReqQty_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		int value;
		value = ms.decodeLong (tag);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnReqQty_t (ms, value, tag); 
	}
	static public void encodeTnReqQty_t (MMarshalStrategy ms, int value, String tag) throws MMarshalException { 
		ms.encode (value, tag);
	}
	public static int fromOctet (byte [] val) throws MMarshalException {
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeTnReqQty_t (ms, "TnReqQty_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public TypeCode getType () {
		return TnReqQty_tHelper.type();
	}
	public static byte[] toOctet (int val) throws MMarshalException { 
		try {
			        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				          new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTnReqQty_t(ms, val, "TnReqQty_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
}
