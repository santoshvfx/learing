package com.sbc.gwsvcs.service.facsaccess.interfaces;
import com.sbc.vicunalite.api.*;
import org.omg.CORBA.*;

public class TrnsptType_eMsg implements MMarshalObject { 
	public TrnsptType_e value;
	public TrnsptType_eMsg () {
	}
	public TrnsptType_eMsg (TrnsptType_e initial) { 
		value = initial; 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTrnsptType_e (ms, tag); 
	}
	static public TrnsptType_e decodeTrnsptType_e (MMarshalStrategy ms, String tag) throws MMarshalException { 
		int enumVal = ms.decodeLong (tag);
		try { 
			return com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e.from_int (enumVal); 
		} catch (BAD_PARAM e) { 
			throw new MMarshalException ("Bad enumeration value", e); 
		} 
	}

	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTrnsptType_e (ms, value, tag); 
	}
	public static void encodeTrnsptType_e (MMarshalStrategy ms, TrnsptType_e val, String tag) throws MMarshalException { 
		ms.encode (val.value(), tag); 
	}
	public TypeCode getType() { 
		return TrnsptType_eHelper.type(); 
	}
	public static byte [] toOctet (TrnsptType_e val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTrnsptType_e (ms, val, "TrnsptType_e");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TrnsptType_e fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTrnsptType_e (ms, "TrnsptType_e"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e create () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e.RPC_TRNSPT; 
	} 
}
