// $Id: FacActnLnItem_tMsg.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class FacActnLnItem_tMsg implements MMarshalObject { 
	public FacActnLnItem_t value;

	public FacActnLnItem_tMsg () {
	}
	public FacActnLnItem_tMsg (FacActnLnItem_t initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t create () { 
com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t();
value.SAGA = new String ();
value.PrmAddr = new com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_t();
value.ALT_CMTY_1_NM = new String ();
value.ALT_CMTY_2_NM = new String ();
value.SPL_INSTR = new String ();
value.ALT_ADDR_NM = new String ();
value.DESC_ADDR = new String ();
value.LOC_STD = new String ();
value.SagInfo = new com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_t();
value.TEL_FEAT_RMK_1_DESC = new String ();
value.TEL_FEAT_RMK_2_DESC = new String ();
value.TEL_FEAT_RMK_3_DESC = new String ();
value.TEL_FEAT_RMK_4_DESC = new String ();
int __seqLength = 0;
value.LnData = new com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t[__seqLength];
value.SwngEntyData = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t[__seqLength];
value.RmkData = new com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t[__seqLength];
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeFacActnLnItem_t (ms, tag); 
	}
	static public FacActnLnItem_t decodeFacActnLnItem_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		FacActnLnItem_t value = create();
		ms.startStruct (tag, false);
		value.SAGA = ms.decodeOctetString (5, "SAGA");
		value.PrmAddr = com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.decodePrmAddr_t (ms, "PrmAddr");
		value.ALT_CMTY_1_NM = ms.decodeOctetString (33, "ALT_CMTY_1_NM");
		value.ALT_CMTY_2_NM = ms.decodeOctetString (33, "ALT_CMTY_2_NM");
		value.SPL_INSTR = ms.decodeOctetString (101, "SPL_INSTR");
		value.ALT_ADDR_NM = ms.decodeOctetString (68, "ALT_ADDR_NM");
		value.DESC_ADDR = ms.decodeOctetString (51, "DESC_ADDR");
		value.LOC_STD = ms.decodeOctetString (51, "LOC_STD");
		value.SagInfo = com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tMsg.decodeSagInfo_t (ms, "SagInfo");
		value.CMTY_NM_RQRD_IND = ms.decodeChar ("CMTY_NM_RQRD_IND");
		value.STATE_NM_RQRD_IND = ms.decodeChar ("STATE_NM_RQRD_IND");
		value.TEL_FEAT_RMK_1_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_1_DESC");
		value.TEL_FEAT_RMK_2_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_2_DESC");
		value.TEL_FEAT_RMK_3_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_3_DESC");
		value.TEL_FEAT_RMK_4_DESC = ms.decodeOctetString (73, "TEL_FEAT_RMK_4_DESC");
		{ 
			ms.startSequence ("LnData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.LnData = new com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.LnData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_tMsg.decodeLnDataItem_t (ms, "LnData");
			} 
		ms.endSequence ("LnData", false);
		}
		{ 
			ms.startSequence ("SwngEntyData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.SwngEntyData = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SwngEntyData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tMsg.decodeSwngEntyDataItem_t (ms, "SwngEntyData");
			} 
		ms.endSequence ("SwngEntyData", false);
		}
		{ 
			ms.startSequence ("RmkData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.RmkData = new com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.RmkData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_tMsg.decodeRmkData_t (ms, "RmkData");
			} 
		ms.endSequence ("RmkData", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeFacActnLnItem_t (ms, value, tag); 
	}
	static public void encodeFacActnLnItem_t (MMarshalStrategy ms, FacActnLnItem_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SAGA, 5, "SAGA");
	com.sbc.gwsvcs.service.premisserver.interfaces.PrmAddr_tMsg.encodePrmAddr_t (ms, value.PrmAddr, "PrmAddr");
	ms.encode (value.ALT_CMTY_1_NM, 33, "ALT_CMTY_1_NM");
ms.encode (value.ALT_CMTY_2_NM, 33, "ALT_CMTY_2_NM");
ms.encode (value.SPL_INSTR, 101, "SPL_INSTR");
ms.encode (value.ALT_ADDR_NM, 68, "ALT_ADDR_NM");
ms.encode (value.DESC_ADDR, 51, "DESC_ADDR");
ms.encode (value.LOC_STD, 51, "LOC_STD");
com.sbc.gwsvcs.service.premisserver.interfaces.SagInfo_tMsg.encodeSagInfo_t (ms, value.SagInfo, "SagInfo");
ms.encode (value.CMTY_NM_RQRD_IND, "CMTY_NM_RQRD_IND");
ms.encode (value.STATE_NM_RQRD_IND, "STATE_NM_RQRD_IND");
ms.encode (value.TEL_FEAT_RMK_1_DESC, 73, "TEL_FEAT_RMK_1_DESC");
ms.encode (value.TEL_FEAT_RMK_2_DESC, 73, "TEL_FEAT_RMK_2_DESC");
ms.encode (value.TEL_FEAT_RMK_3_DESC, 73, "TEL_FEAT_RMK_3_DESC");
ms.encode (value.TEL_FEAT_RMK_4_DESC, 73, "TEL_FEAT_RMK_4_DESC");
{ 
ms.startSequence ("LnData", true);
ms.encode (value.LnData.length, "m_length", true);
for (int __i = 0; __i < value.LnData.length; __i++) { 
com.sbc.gwsvcs.service.premisserver.interfaces.LnDataItem_tMsg.encodeLnDataItem_t (ms, value.LnData[__i], "LnData");
}
ms.endSequence ("LnData", true);
}
{ 
ms.startSequence ("SwngEntyData", true);
ms.encode (value.SwngEntyData.length, "m_length", true);
for (int __i = 0; __i < value.SwngEntyData.length; __i++) { 
com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_tMsg.encodeSwngEntyDataItem_t (ms, value.SwngEntyData[__i], "SwngEntyData");
}
ms.endSequence ("SwngEntyData", true);
}
{ 
ms.startSequence ("RmkData", true);
ms.encode (value.RmkData.length, "m_length", true);
for (int __i = 0; __i < value.RmkData.length; __i++) { 
com.sbc.gwsvcs.service.premisserver.interfaces.RmkData_tMsg.encodeRmkData_t (ms, value.RmkData[__i], "RmkData");
}
ms.endSequence ("RmkData", true);
}
ms.endStruct (tag, true); 
}
public static FacActnLnItem_t fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeFacActnLnItem_t (ms, "FacActnLnItem_t"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_tHelper.type(); 
}
public static byte [] toOctet (FacActnLnItem_t val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeFacActnLnItem_t (ms, val, "FacActnLnItem_t");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
