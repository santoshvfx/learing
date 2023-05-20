package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Result_tMsg implements MMarshalObject { 
	public Result_t value;

	public Result_tMsg () {
	}
	public Result_tMsg (Result_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeResult_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeResult_t (ms, tag); 
	}
	static public Result_t decodeResult_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Result_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.C1 = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.decodeC1_Section_t (ms, "C1");
		value.CTL = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.decodeCTL_Section_t (ms, "CTL");
		{ 
			ms.startSequence ("RESP", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("RESP", false);
			{ 
				value.RESP = new com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.RESP[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tMsg.decodeRESP_t (ms, "RESP");
				} 
			}
			ms.endArray ("RESP", false);
			ms.endSequence ("RESP", false);
		}
		value.ECTERM = com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_tMsg.decodeECTERM_Section_t (ms, "ECTERM");
		value.ECCABLE = com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tMsg.decodeECCABLE_Section_t (ms, "ECCABLE");
		value.ECPRAT = com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_tMsg.decodeECPRAT_Section_t (ms, "ECPRAT");
		value.ELMU = com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tMsg.decodeELMU_Section_t (ms, "ELMU");
		value.EPAF = com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tMsg.decodeEPAF_Section_t (ms, "EPAF");
		{ 
			ms.startSequence ("LOOP", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("LOOP", false);
			{ 
				value.LOOP = new com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.LOOP[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_tMsg.decodeLOOP_Section_t (ms, "LOOP");
				} 
			}
			ms.endArray ("LOOP", false);
			ms.endSequence ("LOOP", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeResult_t (MMarshalStrategy ms, Result_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.encodeC1_Section_t (ms, value.C1, "C1");
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.encodeCTL_Section_t (ms, value.CTL, "CTL");
		{ 
			ms.startSequence ("RESP", true);
			ms.encode (value.RESP.length, "_sequenceLength", true);
			ms.startArray ("RESP", true);
			{ 
				for (int __i0 = 0; __i0 < value.RESP.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tMsg.encodeRESP_t (ms, value.RESP[__i0], "RESP");
				} 
			}
			ms.endArray ("RESP", true);
			ms.endSequence ("RESP", true);
		}
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_tMsg.encodeECTERM_Section_t (ms, value.ECTERM, "ECTERM");
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_tMsg.encodeECCABLE_Section_t (ms, value.ECCABLE, "ECCABLE");
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_tMsg.encodeECPRAT_Section_t (ms, value.ECPRAT, "ECPRAT");
		com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_tMsg.encodeELMU_Section_t (ms, value.ELMU, "ELMU");
		com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_tMsg.encodeEPAF_Section_t (ms, value.EPAF, "EPAF");
		{ 
			ms.startSequence ("LOOP", true);
			ms.encode (value.LOOP.length, "_sequenceLength", true);
			ms.startArray ("LOOP", true);
			{ 
				for (int __i0 = 0; __i0 < value.LOOP.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_tMsg.encodeLOOP_Section_t (ms, value.LOOP[__i0], "LOOP");
				} 
			}
			ms.endArray ("LOOP", true);
			ms.endSequence ("LOOP", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Result_tHelper.type(); 
	}
	public static byte [] toOctet (Result_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeResult_t (ms, val, "Result_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Result_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeResult_t (ms, "Result_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Result_t();
		value.Header = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		value.C1 = new com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t();
		value.CTL = new com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t();
		int __seqLength = 0;
		value.RESP = new com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t[__seqLength];
		value.ECTERM = new com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t();
		value.ECCABLE = new com.sbc.gwsvcs.service.facsaccess.interfaces.ECCABLE_Section_t();
		value.ECPRAT = new com.sbc.gwsvcs.service.facsaccess.interfaces.ECPRAT_Section_t();
		value.ELMU = new com.sbc.gwsvcs.service.facsaccess.interfaces.ELMU_Section_t();
		value.EPAF = new com.sbc.gwsvcs.service.facsaccess.interfaces.EPAF_Section_t();
		value.LOOP = new com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t[__seqLength];
		return value; 
	} 
}
