package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EPOEC_tMsg implements MMarshalObject { 
	public EPOEC_t value;

	public EPOEC_tMsg () {
	}
	public EPOEC_tMsg (EPOEC_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEPOEC_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEPOEC_t (ms, tag); 
	}
	static public EPOEC_t decodeEPOEC_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EPOEC_t value = create();
		ms.startStruct (tag, false);
		value.INVU = ms.decodeOctetString (3, "INVU");
		value.RTNN = ms.decodeOctetString (3, "RTNN");
		value.POUT = ms.decodeOctetString (3, "POUT");
		value.EXK = ms.decodeOctetString (7, "EXK");
		value.LPNAME = ms.decodeOctetString (5, "LPNAME");
		value.DAPROV = ms.decodeOctetString (5, "DAPROV");
		value.SMSC = ms.decodeOctetString (3, "SMSC");
		value.MANUAL = ms.decodeOctetString (2, "MANUAL");
		value.GRADE = ms.decodeOctetString (2, "GRADE");
		value.CLASS = ms.decodeOctetString (2, "CLASS");
		value.WIRES = ms.decodeOctetString (2, "WIRES");
		value.SWTCH = ms.decodeOctetString (2, "SWTCH");
		value.PGI = ms.decodeOctetString (2, "PGI");
		value.CAT = ms.decodeOctetString (2, "CAT");
		value.COTERM = ms.decodeOctetString (2, "COTERM");
		value.QUAL = ms.decodeOctetString (2, "QUAL");
		value.SIGNAL = ms.decodeOctetString (2, "SIGNAL");
		value.METAL = ms.decodeOctetString (2, "METAL");
		value.DDR = ms.decodeOctetString (2, "DDR");
		value.LATY = ms.decodeOctetString (3, "LATY");
		value.LQTY = ms.decodeOctetString (3, "LQTY");
		value.DSP = ms.decodeOctetString (3, "DSP");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEPOEC_t (MMarshalStrategy ms, EPOEC_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.INVU, 3, "INVU");
		ms.encode (value.RTNN, 3, "RTNN");
		ms.encode (value.POUT, 3, "POUT");
		ms.encode (value.EXK, 7, "EXK");
		ms.encode (value.LPNAME, 5, "LPNAME");
		ms.encode (value.DAPROV, 5, "DAPROV");
		ms.encode (value.SMSC, 3, "SMSC");
		ms.encode (value.MANUAL, 2, "MANUAL");
		ms.encode (value.GRADE, 2, "GRADE");
		ms.encode (value.CLASS, 2, "CLASS");
		ms.encode (value.WIRES, 2, "WIRES");
		ms.encode (value.SWTCH, 2, "SWTCH");
		ms.encode (value.PGI, 2, "PGI");
		ms.encode (value.CAT, 2, "CAT");
		ms.encode (value.COTERM, 2, "COTERM");
		ms.encode (value.QUAL, 2, "QUAL");
		ms.encode (value.SIGNAL, 2, "SIGNAL");
		ms.encode (value.METAL, 2, "METAL");
		ms.encode (value.DDR, 2, "DDR");
		ms.encode (value.LATY, 3, "LATY");
		ms.encode (value.LQTY, 3, "LQTY");
		ms.encode (value.DSP, 3, "DSP");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_tHelper.type(); 
	}
	public static byte [] toOctet (EPOEC_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEPOEC_t (ms, val, "EPOEC_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EPOEC_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEPOEC_t (ms, "EPOEC_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPOEC_t();
		value.INVU = new String ();
		value.RTNN = new String ();
		value.POUT = new String ();
		value.EXK = new String ();
		value.LPNAME = new String ();
		value.DAPROV = new String ();
		value.SMSC = new String ();
		value.MANUAL = new String ();
		value.GRADE = new String ();
		value.CLASS = new String ();
		value.WIRES = new String ();
		value.SWTCH = new String ();
		value.PGI = new String ();
		value.CAT = new String ();
		value.COTERM = new String ();
		value.QUAL = new String ();
		value.SIGNAL = new String ();
		value.METAL = new String ();
		value.DDR = new String ();
		value.LATY = new String ();
		value.LQTY = new String ();
		value.DSP = new String ();
		return value; 
	} 
}
