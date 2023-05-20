package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class OCDAT_tMsg implements MMarshalObject { 
	public OCDAT_t value;

	public OCDAT_tMsg () {
	}
	public OCDAT_tMsg (OCDAT_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeOCDAT_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeOCDAT_t (ms, tag); 
	}
	static public OCDAT_t decodeOCDAT_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		OCDAT_t value = create();
		ms.startStruct (tag, false);
		value.CA = ms.decodeOctetString (11, "CA");
		value.TYPE = ms.decodeOctetString (10, "TYPE");
		value.LPR = ms.decodeOctetString (5, "LPR");
		value.HPR = ms.decodeOctetString (5, "HPR");
		value.LSPBP = ms.decodeOctetString (5, "LSPBP");
		value.BZ = ms.decodeOctetString (2, "BZ");
		value.LTC = ms.decodeOctetString (5, "LTC");
		value.LTF = ms.decodeOctetString (5, "LTF");
		value.SDP = ms.decodeOctetString (2, "SDP");
		value.TSB = ms.decodeOctetString (2, "TSB");
		value.PGS = ms.decodeOctetString (5, "PGS");
		value.RTA = ms.decodeOctetString (3, "RTA");
		value.DLE = ms.decodeOctetString (2, "DLE");
		value.TSI = ms.decodeOctetString (2, "TSI");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeOCDAT_t (MMarshalStrategy ms, OCDAT_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CA, 11, "CA");
		ms.encode (value.TYPE, 10, "TYPE");
		ms.encode (value.LPR, 5, "LPR");
		ms.encode (value.HPR, 5, "HPR");
		ms.encode (value.LSPBP, 5, "LSPBP");
		ms.encode (value.BZ, 2, "BZ");
		ms.encode (value.LTC, 5, "LTC");
		ms.encode (value.LTF, 5, "LTF");
		ms.encode (value.SDP, 2, "SDP");
		ms.encode (value.TSB, 2, "TSB");
		ms.encode (value.PGS, 5, "PGS");
		ms.encode (value.RTA, 3, "RTA");
		ms.encode (value.DLE, 2, "DLE");
		ms.encode (value.TSI, 2, "TSI");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_tHelper.type(); 
	}
	public static byte [] toOctet (OCDAT_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeOCDAT_t (ms, val, "OCDAT_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static OCDAT_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeOCDAT_t (ms, "OCDAT_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_t();
		value.CA = new String ();
		value.TYPE = new String ();
		value.LPR = new String ();
		value.HPR = new String ();
		value.LSPBP = new String ();
		value.BZ = new String ();
		value.LTC = new String ();
		value.LTF = new String ();
		value.SDP = new String ();
		value.TSB = new String ();
		value.PGS = new String ();
		value.RTA = new String ();
		value.DLE = new String ();
		value.TSI = new String ();
		return value; 
	} 
}
