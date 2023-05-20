package com.sbc.gwsvcs.service.hostlookup.interfaces;

import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
import com.sbc.vicunalite.api.marshal.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class HostLookupFull_RHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private HostLookupFull_RHelper () {
	}
	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R value = new com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R();
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.wc = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.facs = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.lmos = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.cosmos = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.premis = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.saga = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.sord = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.tirks = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.nsdb = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.boss = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.march = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.swtch = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.swtch_entity = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.wfado = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.wc_alpha = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.div_code = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.pics = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.pacbell_mi = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.property = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.split = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.lmos_pacbell = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.lmos_snet = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.acis = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.location = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.temp1 = new String (_bytes, 0, _j);
		}
		value.affiliate_ind = i.read_short ();
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.boss_2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.boss_3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.boss_4 = new String (_bytes, 0, _j);
		}
		value.multiple_boss_region_ind = i.read_short ();
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.rcrms = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.dial = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.rfs_alpha = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.temp3 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.temp4 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.nxx_split_ind = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.temp5 = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R value) { 
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.wc.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.facs.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.lmos.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.cosmos.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.premis.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.saga.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.sord.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.tirks.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.nsdb.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.boss.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.march.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.swtch.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.swtch_entity.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.wfado.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.wc_alpha.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.div_code.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.pics.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.pacbell_mi.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.property.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.split.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.lmos_pacbell.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.lmos_snet.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.acis.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.location.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.temp1.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		o.write_short(value.affiliate_ind);
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.boss_2.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.boss_3.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.boss_4.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		o.write_short(value.multiple_boss_region_ind);
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.rcrms.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.dial.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.rfs_alpha.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.temp3.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.temp4.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.nxx_split_ind.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.temp5.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[37];
				members[0] = new StructMember();
				members[0].name = "wc";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (10, members[0].type);
				members[1] = new StructMember();
				members[1].name = "facs";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (10, members[1].type);
				members[2] = new StructMember();
				members[2].name = "lmos";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (10, members[2].type);
				members[3] = new StructMember();
				members[3].name = "cosmos";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (10, members[3].type);
				members[4] = new StructMember();
				members[4].name = "premis";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (10, members[4].type);
				members[5] = new StructMember();
				members[5].name = "saga";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (10, members[5].type);
				members[6] = new StructMember();
				members[6].name = "sord";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (10, members[6].type);
				members[7] = new StructMember();
				members[7].name = "tirks";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (10, members[7].type);
				members[8] = new StructMember();
				members[8].name = "nsdb";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (10, members[8].type);
				members[9] = new StructMember();
				members[9].name = "boss";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (10, members[9].type);
				members[10] = new StructMember();
				members[10].name = "march";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (10, members[10].type);
				members[11] = new StructMember();
				members[11].name = "swtch";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (10, members[11].type);
				members[12] = new StructMember();
				members[12].name = "swtch_entity";
				members[12].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[12].type = ORB.init().create_array_tc (10, members[12].type);
				members[13] = new StructMember();
				members[13].name = "wfado";
				members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[13].type = ORB.init().create_array_tc (10, members[13].type);
				members[14] = new StructMember();
				members[14].name = "wc_alpha";
				members[14].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[14].type = ORB.init().create_array_tc (10, members[14].type);
				members[15] = new StructMember();
				members[15].name = "div_code";
				members[15].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[15].type = ORB.init().create_array_tc (10, members[15].type);
				members[16] = new StructMember();
				members[16].name = "pics";
				members[16].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[16].type = ORB.init().create_array_tc (10, members[16].type);
				members[17] = new StructMember();
				members[17].name = "pacbell_mi";
				members[17].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[17].type = ORB.init().create_array_tc (10, members[17].type);
				members[18] = new StructMember();
				members[18].name = "property";
				members[18].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[18].type = ORB.init().create_array_tc (10, members[18].type);
				members[19] = new StructMember();
				members[19].name = "split";
				members[19].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[19].type = ORB.init().create_array_tc (10, members[19].type);
				members[20] = new StructMember();
				members[20].name = "lmos_pacbell";
				members[20].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[20].type = ORB.init().create_array_tc (10, members[20].type);
				members[21] = new StructMember();
				members[21].name = "lmos_snet";
				members[21].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[21].type = ORB.init().create_array_tc (10, members[21].type);
				members[22] = new StructMember();
				members[22].name = "acis";
				members[22].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[22].type = ORB.init().create_array_tc (10, members[22].type);
				members[23] = new StructMember();
				members[23].name = "location";
				members[23].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[23].type = ORB.init().create_array_tc (10, members[23].type);
				members[24] = new StructMember();
				members[24].name = "temp1";
				members[24].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[24].type = ORB.init().create_array_tc (10, members[24].type);
				members[25] = new StructMember();
				members[25].name = "affiliate_ind";
				members[25].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
				members[26] = new StructMember();
				members[26].name = "boss_2";
				members[26].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[26].type = ORB.init().create_array_tc (10, members[26].type);
				members[27] = new StructMember();
				members[27].name = "boss_3";
				members[27].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[27].type = ORB.init().create_array_tc (10, members[27].type);
				members[28] = new StructMember();
				members[28].name = "boss_4";
				members[28].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[28].type = ORB.init().create_array_tc (10, members[28].type);
				members[29] = new StructMember();
				members[29].name = "multiple_boss_region_ind";
				members[29].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
				members[30] = new StructMember();
				members[30].name = "rcrms";
				members[30].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[30].type = ORB.init().create_array_tc (10, members[30].type);
				members[31] = new StructMember();
				members[31].name = "dial";
				members[31].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[31].type = ORB.init().create_array_tc (10, members[31].type);
				members[32] = new StructMember();
				members[32].name = "rfs_alpha";
				members[32].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[32].type = ORB.init().create_array_tc (10, members[32].type);
				members[33] = new StructMember();
				members[33].name = "temp3";
				members[33].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[33].type = ORB.init().create_array_tc (10, members[33].type);
				members[34] = new StructMember();
				members[34].name = "temp4";
				members[34].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[34].type = ORB.init().create_array_tc (10, members[34].type);
				members[35] = new StructMember();
				members[35].name = "nxx_split_ind";
				members[35].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[35].type = ORB.init().create_array_tc (2, members[35].type);
				members[36] = new StructMember();
				members[36].name = "temp5";
				members[36].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[36].type = ORB.init().create_array_tc (8, members[36].type);
				myTc = ORB.init().create_struct_tc (id(), "HostLookupFull_R", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/hostlookup/interfaces/HostLookupFull_R:1.0"; } 
}
