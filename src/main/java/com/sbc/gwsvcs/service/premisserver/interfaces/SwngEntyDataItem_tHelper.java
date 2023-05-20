// $Id: SwngEntyDataItem_tHelper.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwngEntyDataItem_tHelper { 
	private static TypeCode myTc = null;
	private SwngEntyDataItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/SwngEntyDataItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t();
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWNG_ENTY_RMK_1_DESC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[73];
			i.read_octet_array (_bytes, 0, 73);
			int _j;
			for (_j = 0; _j < 73; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWNG_ENTY_RMK_2_DESC = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.IECData = new com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.IECData[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_tHelper.read (i);
			} 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "SWNG_ENTY_RMK_1_DESC";
			members[0].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyRmkDesc_tHelper.id(), "SwngEntyRmkDesc_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "SWNG_ENTY_RMK_2_DESC";
			members[1].type = ORB.init().create_array_tc (
				73, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyRmkDesc_tHelper.id(), "SwngEntyRmkDesc_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "IECData";
			members[2].type = com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_tHelper.type();
			members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
			myTc = ORB.init().create_struct_tc (id(), "SwngEntyDataItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.SwngEntyDataItem_t value) { 
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.SWNG_ENTY_RMK_1_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{
			byte[] _bytes = new byte[73];
			byte[] _bytes_src = value.SWNG_ENTY_RMK_2_DESC.getBytes();
			int _j;
			for (_j = 0; _j < 73 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 73);
		}
		{ 
			o.write_long (value.IECData.length);
			for (int __i = 0; __i < value.IECData.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_tHelper.write (o, value.IECData[__i]);
			} 
		}
	}
}
