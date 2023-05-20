// $Id: UnnbrdMenuItem_tHelper.java,v 1.1 2002/09/29 04:28:17 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class UnnbrdMenuItem_tHelper { 
	private static TypeCode myTc = null;
	private UnnbrdMenuItem_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/UnnbrdMenuItem_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t();
		value.StNm = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tHelper.read (i);
		{
			byte[] _bytes = new byte[33];
			i.read_octet_array (_bytes, 0, 33);
			int _j;
			for (_j = 0; _j < 33; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CMTY_NM = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STATE_CD = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.AsgndHousNbrRnge = new com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.AsgndHousNbrRnge[__i] = com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_tHelper.read (i);
			} 
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[4];
			members[0] = new StructMember();
			members[0].name = "StNm";
			members[0].type = com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tHelper.type();
			members[1] = new StructMember();
			members[1].name = "CMTY_NM";
			members[1].type = ORB.init().create_array_tc (
				33, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.CmtyNm_tHelper.id(), "CmtyNm_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "STATE_CD";
			members[2].type = ORB.init().create_array_tc (
				3, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.StateCd_tHelper.id(), "StateCd_t", members[2].type);
			members[3] = new StructMember();
			members[3].name = "AsgndHousNbrRnge";
			members[3].type = com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_tHelper.type();
			members[3].type = ORB.init().create_sequence_tc (0, members[3].type);
			myTc = ORB.init().create_struct_tc (id(), "UnnbrdMenuItem_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t value) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_tHelper.write (o, value.StNm);
		{
			byte[] _bytes = new byte[33];
			byte[] _bytes_src = value.CMTY_NM.getBytes();
			int _j;
			for (_j = 0; _j < 33 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 33);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.STATE_CD.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{ 
			o.write_long (value.AsgndHousNbrRnge.length);
			for (int __i = 0; __i < value.AsgndHousNbrRnge.length; __i++) { 
				com.sbc.gwsvcs.service.premisserver.interfaces.AsgndHousNbrRngeItem_tHelper.write (o, value.AsgndHousNbrRnge[__i]);
			} 
		}
	}
}
