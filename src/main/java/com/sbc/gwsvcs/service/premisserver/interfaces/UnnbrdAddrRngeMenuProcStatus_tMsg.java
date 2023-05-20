// $Id: UnnbrdAddrRngeMenuProcStatus_tMsg.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UnnbrdAddrRngeMenuProcStatus_tMsg implements MMarshalObject { 
	public UnnbrdAddrRngeMenuProcStatus_t value;

	public UnnbrdAddrRngeMenuProcStatus_tMsg () {
	}
	public UnnbrdAddrRngeMenuProcStatus_tMsg (UnnbrdAddrRngeMenuProcStatus_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t create () { 
	com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t();
	value.RTCD = new String ();
	value.Unadrm = new com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_t();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUnnbrdAddrRngeMenuProcStatus_t (ms, tag); 
	}
	static public UnnbrdAddrRngeMenuProcStatus_t decodeUnnbrdAddrRngeMenuProcStatus_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UnnbrdAddrRngeMenuProcStatus_t value = create();
		ms.startStruct (tag, false);
		value.RTCD = ms.decodeOctetString (7, "RTCD");
		value.Unadrm = com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tMsg.decodeUnadrmGsgm_t (ms, "Unadrm");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUnnbrdAddrRngeMenuProcStatus_t (ms, value, tag); 
	}
	static public void encodeUnnbrdAddrRngeMenuProcStatus_t (MMarshalStrategy ms, UnnbrdAddrRngeMenuProcStatus_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTCD, 7, "RTCD");
	com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgm_tMsg.encodeUnadrmGsgm_t (ms, value.Unadrm, "Unadrm");
	ms.endStruct (tag, true); 
}
public static UnnbrdAddrRngeMenuProcStatus_t fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeUnnbrdAddrRngeMenuProcStatus_t (ms, "UnnbrdAddrRngeMenuProcStatus_t"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_tHelper.type(); 
}
public static byte [] toOctet (UnnbrdAddrRngeMenuProcStatus_t val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeUnnbrdAddrRngeMenuProcStatus_t (ms, val, "UnnbrdAddrRngeMenuProcStatus_t");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
