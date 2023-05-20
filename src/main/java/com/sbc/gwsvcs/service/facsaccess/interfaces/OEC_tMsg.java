package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class OEC_tMsg implements MMarshalObject { 
	public OEC_t value;

	public OEC_tMsg () {
	}
	public OEC_tMsg (OEC_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeOEC_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeOEC_t (ms, tag); 
	}
	static public OEC_t decodeOEC_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		OEC_t value = create();
		ms.startStruct (tag, false);
		value.GRD = ms.decodeOctetString (2, "GRD");
		value.CLS = ms.decodeOctetString (2, "CLS");
		value.WIRES = ms.decodeOctetString (2, "WIRES");
		value.PGI = ms.decodeOctetString (2, "PGI");
		value.NLI = ms.decodeOctetString (2, "NLI");
		value.MI = ms.decodeOctetString (2, "MI");
		value.CTG = ms.decodeOctetString (2, "CTG");
		value.COTE = ms.decodeOctetString (2, "COTE");
		value.QUAL = ms.decodeOctetString (2, "QUAL");
		value.SIG = ms.decodeOctetString (2, "SIG");
		value.MET = ms.decodeOctetString (2, "MET");
		value.DDR = ms.decodeOctetString (2, "DDR");
		value.LATY = ms.decodeOctetString (3, "LATY");
		value.DSP = ms.decodeOctetString (3, "DSP");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeOEC_t (MMarshalStrategy ms, OEC_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.GRD, 2, "GRD");
		ms.encode (value.CLS, 2, "CLS");
		ms.encode (value.WIRES, 2, "WIRES");
		ms.encode (value.PGI, 2, "PGI");
		ms.encode (value.NLI, 2, "NLI");
		ms.encode (value.MI, 2, "MI");
		ms.encode (value.CTG, 2, "CTG");
		ms.encode (value.COTE, 2, "COTE");
		ms.encode (value.QUAL, 2, "QUAL");
		ms.encode (value.SIG, 2, "SIG");
		ms.encode (value.MET, 2, "MET");
		ms.encode (value.DDR, 2, "DDR");
		ms.encode (value.LATY, 3, "LATY");
		ms.encode (value.DSP, 3, "DSP");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_tHelper.type(); 
	}
	public static byte [] toOctet (OEC_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeOEC_t (ms, val, "OEC_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static OEC_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeOEC_t (ms, "OEC_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.OEC_t();
		value.GRD = new String ();
		value.CLS = new String ();
		value.WIRES = new String ();
		value.PGI = new String ();
		value.NLI = new String ();
		value.MI = new String ();
		value.CTG = new String ();
		value.COTE = new String ();
		value.QUAL = new String ();
		value.SIG = new String ();
		value.MET = new String ();
		value.DDR = new String ();
		value.LATY = new String ();
		value.DSP = new String ();
		return value; 
	} 
}
