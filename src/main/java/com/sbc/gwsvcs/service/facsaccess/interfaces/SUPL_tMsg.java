package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SUPL_tMsg implements MMarshalObject { 
	public SUPL_t value;

	public SUPL_tMsg () {
	}
	public SUPL_tMsg (SUPL_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSUPL_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSUPL_t (ms, tag); 
	}
	static public SUPL_t decodeSUPL_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SUPL_t value = create();
		ms.startStruct (tag, false);
		value.STYP = ms.decodeOctetString (5, "STYP");
		value.SID = ms.decodeOctetString (11, "SID");
		value.ETYP = ms.decodeOctetString (5, "ETYP");
		value.EID = ms.decodeOctetString (11, "EID");
		value.UTYP = ms.decodeOctetString (5, "UTYP");
		value.UID = ms.decodeOctetString (11, "UID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSUPL_t (MMarshalStrategy ms, SUPL_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.STYP, 5, "STYP");
		ms.encode (value.SID, 11, "SID");
		ms.encode (value.ETYP, 5, "ETYP");
		ms.encode (value.EID, 11, "EID");
		ms.encode (value.UTYP, 5, "UTYP");
		ms.encode (value.UID, 11, "UID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_tHelper.type(); 
	}
	public static byte [] toOctet (SUPL_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSUPL_t (ms, val, "SUPL_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SUPL_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSUPL_t (ms, "SUPL_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.SUPL_t();
		value.STYP = new String ();
		value.SID = new String ();
		value.ETYP = new String ();
		value.EID = new String ();
		value.UTYP = new String ();
		value.UID = new String ();
		return value; 
	} 
}
