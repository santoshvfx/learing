// $Id: StNbrId_tHelper.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

  import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
  import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class StNbrId_tHelper { 
	private static TypeCode myTc = null;
	private StNbrId_tHelper () {
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}
	public static String id() { return "IDL:com/sbc/gwsvcs/service/premisserver/interfaces/StNbrId_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}
	public static com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t value = new com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t();
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SAD_HOUS_PRFX = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SAD_HOUS_NBR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SAD_HOUS_NBR_SUFX = new String (_bytes, 0, _j);
		}
		return value; 
	}
	synchronized public static TypeCode type() { 
		if (myTc == null) { 
			StructMember members[] = new StructMember[3];
			members[0] = new StructMember();
			members[0].name = "SAD_HOUS_PRFX";
			members[0].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SadHousPrfx_tHelper.id(), "SadHousPrfx_t", members[0].type);
			members[1] = new StructMember();
			members[1].name = "SAD_HOUS_NBR";
			members[1].type = ORB.init().create_array_tc (
				9, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SadHousNbr_tHelper.id(), "SadHousNbr_t", members[1].type);
			members[2] = new StructMember();
			members[2].name = "SAD_HOUS_NBR_SUFX";
			members[2].type = ORB.init().create_array_tc (
				6, ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet));
			members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.SadHousNbrSufx_tHelper.id(), "SadHousNbrSufx_t", members[2].type);
			myTc = ORB.init().create_struct_tc (id(), "StNbrId_t", members); 
		}
		return myTc; 
	}
	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t value) { 
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.SAD_HOUS_PRFX.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.SAD_HOUS_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.SAD_HOUS_NBR_SUFX.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
	}
}
