package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ECTERM_Section_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ECTERM_Section_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t();
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PRT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.STEP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DREC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ZTEINT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PTEA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TYPE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.IND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RZ = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CZ = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CSA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PRQ = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RMK = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.VER_NO = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SW = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWRVW = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.XRST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.AC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TBLTP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TBLN = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BPI = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FABP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SEQ = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RLA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RLC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LOTI = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PEND = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[18];
			i.read_octet_array (_bytes, 0, 18);
			int _j;
			for (_j = 0; _j < 18; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RULES = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[16];
			i.read_octet_array (_bytes, 0, 16);
			int _j;
			for (_j = 0; _j < 16; _j++)
				if (_bytes[_j] == 0)
					break;
			value.QUALFLG = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.INRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.INRNG[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.OUTRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.OUTRNG[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.ATTRNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.ATTRNG[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.OCDAT = new com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.OCDAT[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.RTARNG = new com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.RTARNG[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t value) { 
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.PRT.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.STEP.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.DREC.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.TEA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ZTEINT.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.PTEA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.TYPE.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.IND.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.RT.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.RZ.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.CZ.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.CSA.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.TPR.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.PRQ.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RMK.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.VER_NO.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.SW.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.SWRVW.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.RST.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.XRST.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.PC.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.AC.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TBLTP.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TBLN.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.BPI.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.FABP.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.SEQ.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RLA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.RLC.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.LOTI.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.PEND.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		{
			byte[] _bytes = new byte[18];
			byte[] _bytes_src = value.RULES.getBytes();
			int _j;
			for (_j = 0; _j < 18 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 18);
		}
		{
			byte[] _bytes = new byte[16];
			byte[] _bytes_src = value.QUALFLG.getBytes();
			int _j;
			for (_j = 0; _j < 16 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 16);
		}
		{ 
			o.write_long (value.INRNG.length);
			for (int __i = 0; __i < value.INRNG.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_tHelper.write (o, value.INRNG[__i]);
			} 
		}
		{ 
			o.write_long (value.OUTRNG.length);
			for (int __i = 0; __i < value.OUTRNG.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tHelper.write (o, value.OUTRNG[__i]);
			} 
		}
		{ 
			o.write_long (value.ATTRNG.length);
			for (int __i = 0; __i < value.ATTRNG.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_tHelper.write (o, value.ATTRNG[__i]);
			} 
		}
		{ 
			o.write_long (value.OCDAT.length);
			for (int __i = 0; __i < value.OCDAT.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_tHelper.write (o, value.OCDAT[__i]);
			} 
		}
		{ 
			o.write_long (value.RTARNG.length);
			for (int __i = 0; __i < value.RTARNG.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tHelper.write (o, value.RTARNG[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ECTERM_Section_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[38];
				members[0] = new StructMember();
				members[0].name = "PRT";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (5, members[0].type);
				members[1] = new StructMember();
				members[1].name = "STEP";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (8, members[1].type);
				members[2] = new StructMember();
				members[2].name = "DREC";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (2, members[2].type);
				members[3] = new StructMember();
				members[3].name = "TEA";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (51, members[3].type);
				members[4] = new StructMember();
				members[4].name = "ZTEINT";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (13, members[4].type);
				members[5] = new StructMember();
				members[5].name = "PTEA";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (51, members[5].type);
				members[6] = new StructMember();
				members[6].name = "TYPE";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (6, members[6].type);
				members[7] = new StructMember();
				members[7].name = "IND";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (5, members[7].type);
				members[8] = new StructMember();
				members[8].name = "RT";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (6, members[8].type);
				members[9] = new StructMember();
				members[9].name = "RZ";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (3, members[9].type);
				members[10] = new StructMember();
				members[10].name = "CZ";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (3, members[10].type);
				members[11] = new StructMember();
				members[11].name = "CSA";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (2, members[11].type);
				members[12] = new StructMember();
				members[12].name = "TPR";
				members[12].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[12].type = ORB.init().create_array_tc (7, members[12].type);
				members[13] = new StructMember();
				members[13].name = "PRQ";
				members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[13].type = ORB.init().create_array_tc (2, members[13].type);
				members[14] = new StructMember();
				members[14].name = "RMK";
				members[14].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[14].type = ORB.init().create_array_tc (51, members[14].type);
				members[15] = new StructMember();
				members[15].name = "VER_NO";
				members[15].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[15].type = ORB.init().create_array_tc (2, members[15].type);
				members[16] = new StructMember();
				members[16].name = "SW";
				members[16].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[16].type = ORB.init().create_array_tc (8, members[16].type);
				members[17] = new StructMember();
				members[17].name = "SWRVW";
				members[17].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[17].type = ORB.init().create_array_tc (2, members[17].type);
				members[18] = new StructMember();
				members[18].name = "RST";
				members[18].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[18].type = ORB.init().create_array_tc (21, members[18].type);
				members[19] = new StructMember();
				members[19].name = "XRST";
				members[19].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[19].type = ORB.init().create_array_tc (21, members[19].type);
				members[20] = new StructMember();
				members[20].name = "PC";
				members[20].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[20].type = ORB.init().create_array_tc (5, members[20].type);
				members[21] = new StructMember();
				members[21].name = "AC";
				members[21].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[21].type = ORB.init().create_array_tc (5, members[21].type);
				members[22] = new StructMember();
				members[22].name = "TBLTP";
				members[22].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[22].type = ORB.init().create_array_tc (2, members[22].type);
				members[23] = new StructMember();
				members[23].name = "TBLN";
				members[23].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[23].type = ORB.init().create_array_tc (2, members[23].type);
				members[24] = new StructMember();
				members[24].name = "BPI";
				members[24].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[24].type = ORB.init().create_array_tc (2, members[24].type);
				members[25] = new StructMember();
				members[25].name = "FABP";
				members[25].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[25].type = ORB.init().create_array_tc (4, members[25].type);
				members[26] = new StructMember();
				members[26].name = "SEQ";
				members[26].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[26].type = ORB.init().create_array_tc (2, members[26].type);
				members[27] = new StructMember();
				members[27].name = "RLA";
				members[27].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[27].type = ORB.init().create_array_tc (51, members[27].type);
				members[28] = new StructMember();
				members[28].name = "RLC";
				members[28].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[28].type = ORB.init().create_array_tc (12, members[28].type);
				members[29] = new StructMember();
				members[29].name = "LOTI";
				members[29].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[29].type = ORB.init().create_array_tc (3, members[29].type);
				members[30] = new StructMember();
				members[30].name = "PEND";
				members[30].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[30].type = ORB.init().create_array_tc (14, members[30].type);
				members[31] = new StructMember();
				members[31].name = "RULES";
				members[31].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[31].type = ORB.init().create_array_tc (18, members[31].type);
				members[32] = new StructMember();
				members[32].name = "QUALFLG";
				members[32].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[32].type = ORB.init().create_array_tc (16, members[32].type);
				members[33] = new StructMember();
				members[33].name = "INRNG";
				members[33].type = com.sbc.gwsvcs.service.facsaccess.interfaces.INRNG_tHelper.type();
				members[33].type = ORB.init().create_sequence_tc (0, members[33].type);
				members[34] = new StructMember();
				members[34].name = "OUTRNG";
				members[34].type = com.sbc.gwsvcs.service.facsaccess.interfaces.OUTRNG_tHelper.type();
				members[34].type = ORB.init().create_sequence_tc (0, members[34].type);
				members[35] = new StructMember();
				members[35].name = "ATTRNG";
				members[35].type = com.sbc.gwsvcs.service.facsaccess.interfaces.ATTRNG_tHelper.type();
				members[35].type = ORB.init().create_sequence_tc (0, members[35].type);
				members[36] = new StructMember();
				members[36].name = "OCDAT";
				members[36].type = com.sbc.gwsvcs.service.facsaccess.interfaces.OCDAT_tHelper.type();
				members[36].type = ORB.init().create_sequence_tc (0, members[36].type);
				members[37] = new StructMember();
				members[37].name = "RTARNG";
				members[37].type = com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tHelper.type();
				members[37].type = ORB.init().create_sequence_tc (0, members[37].type);
				myTc = ORB.init().create_struct_tc (id(), "ECTERM_Section_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/ECTERM_Section_t:1.0"; } 
}
