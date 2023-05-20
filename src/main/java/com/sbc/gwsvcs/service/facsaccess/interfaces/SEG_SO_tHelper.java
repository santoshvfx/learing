package com.sbc.gwsvcs.service.facsaccess.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SEG_SO_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SEG_SO_tHelper () {
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t();
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SEGNO = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LSTAT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[17];
			i.read_octet_array (_bytes, 0, 17);
			int _j;
			for (_j = 0; _j < 17; _j++)
				if (_bytes[_j] == 0)
					break;
			value.BP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.OBP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LPORG = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RLOE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RLOA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RLOC = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TEA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.COMM = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.UF = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[14];
			i.read_octet_array (_bytes, 0, 14);
			int _j;
			for (_j = 0; _j < 14; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TETE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CQ = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DEF = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DEFD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CTT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DEFL = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LNOP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SYSTP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PGSNO = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FLDLTS = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.COLTS = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CNST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORIG = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MPROV = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[12];
			i.read_octet_array (_bytes, 0, 12);
			int _j;
			for (_j = 0; _j < 12; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MCLLI = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MLOC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MCA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MPR = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SDP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TSB = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[19];
			i.read_octet_array (_bytes, 0, 19);
			int _j;
			for (_j = 0; _j < 19; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LMURMK = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.EWOID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.EWODD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[8];
			i.read_octet_array (_bytes, 0, 8);
			int _j;
			for (_j = 0; _j < 8; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LSTID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SOLST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SOITM = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SOLSTDD = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[76];
			i.read_octet_array (_bytes, 0, 76);
			int _j;
			for (_j = 0; _j < 76; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RSVINFO = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RSVDAT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RSVRMK = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RSTTE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PERM = new String (_bytes, 0, _j);
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
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RMK0TE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.RMK0PR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CQC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[3];
			i.read_octet_array (_bytes, 0, 3);
			int _j;
			for (_j = 0; _j < 3; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CDC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[17];
			i.read_octet_array (_bytes, 0, 17);
			int _j;
			for (_j = 0; _j < 17; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASGBPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ASBPSTAT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[10];
			i.read_octet_array (_bytes, 0, 10);
			int _j;
			for (_j = 0; _j < 10; _j++)
				if (_bytes[_j] == 0)
					break;
			value.PGSTP = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.LTS = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DLE = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TSI = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DLERMK = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DLERST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.DLEONU = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ONURST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[21];
			i.read_octet_array (_bytes, 0, 21);
			int _j;
			for (_j = 0; _j < 21; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ONUXRST = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ONURMK = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TFCA1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TFCA2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TFPR1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TFPR2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTMED1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTMED2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTEA1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTEA2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTYPE1 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTYPE2 = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[76];
			i.read_octet_array (_bytes, 0, 76);
			int _j;
			for (_j = 0; _j < 76; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ABPRSVINFO = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ABPRSVDAT = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ABPRSVRMK = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[11];
			i.read_octet_array (_bytes, 0, 11);
			int _j;
			for (_j = 0; _j < 11; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TFCA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[5];
			i.read_octet_array (_bytes, 0, 5);
			int _j;
			for (_j = 0; _j < 5; _j++)
				if (_bytes[_j] == 0)
					break;
			value.TFPR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTMED = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[51];
			i.read_octet_array (_bytes, 0, 51);
			int _j;
			for (_j = 0; _j < 51; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTEA = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[6];
			i.read_octet_array (_bytes, 0, 6);
			int _j;
			for (_j = 0; _j < 6; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FICTYPE = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.ASGBP = new com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.ASGBP[__i] = com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t value) { 
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.SEGNO.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.CA.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.PR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.LSTAT.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[17];
			byte[] _bytes_src = value.BP.getBytes();
			int _j;
			for (_j = 0; _j < 17 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 17);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.OBP.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.LPORG.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.RLOE.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RLOA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.RLOC.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
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
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.TEA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.TP.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.COMM.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.UF.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.SC.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[14];
			byte[] _bytes_src = value.TETE.getBytes();
			int _j;
			for (_j = 0; _j < 14 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 14);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.CQ.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.DEF.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.DEFD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.CTT.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.DEFL.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.LT.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.LNOP.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.SYSTP.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.PGSNO.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.FLDLTS.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.COLTS.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.CNST.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.ORIG.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.MPROV.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[12];
			byte[] _bytes_src = value.MCLLI.getBytes();
			int _j;
			for (_j = 0; _j < 12 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 12);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.MLOC.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.MCA.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.MPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
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
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.SDP.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.TSB.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
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
			byte[] _bytes = new byte[19];
			byte[] _bytes_src = value.LMURMK.getBytes();
			int _j;
			for (_j = 0; _j < 19 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 19);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.EWOID.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.EWODD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[8];
			byte[] _bytes_src = value.LSTID.getBytes();
			int _j;
			for (_j = 0; _j < 8 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 8);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.SOLST.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.SOITM.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.SOLSTDD.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[76];
			byte[] _bytes_src = value.RSVINFO.getBytes();
			int _j;
			for (_j = 0; _j < 76 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 76);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.RSVDAT.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RSVRMK.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.RSTTE.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.PERM.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
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
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RMK0TE.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.RMK0PR.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.CQC.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[3];
			byte[] _bytes_src = value.CDC.getBytes();
			int _j;
			for (_j = 0; _j < 3 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 3);
		}
		{
			byte[] _bytes = new byte[17];
			byte[] _bytes_src = value.ASGBPR.getBytes();
			int _j;
			for (_j = 0; _j < 17 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 17);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.ASBPSTAT.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[10];
			byte[] _bytes_src = value.PGSTP.getBytes();
			int _j;
			for (_j = 0; _j < 10 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 10);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.LTS.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.DLE.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.TSI.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.DLERMK.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.DLERST.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.DLEONU.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.ONURST.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[21];
			byte[] _bytes_src = value.ONUXRST.getBytes();
			int _j;
			for (_j = 0; _j < 21 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 21);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.ONURMK.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TFCA1.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TFCA2.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TFPR1.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TFPR2.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.FICTMED1.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.FICTMED2.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.FICTEA1.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.FICTEA2.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.FICTYPE1.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.FICTYPE2.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[76];
			byte[] _bytes_src = value.ABPRSVINFO.getBytes();
			int _j;
			for (_j = 0; _j < 76 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 76);
		}
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.ABPRSVDAT.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.ABPRSVRMK.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[11];
			byte[] _bytes_src = value.TFCA.getBytes();
			int _j;
			for (_j = 0; _j < 11 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 11);
		}
		{
			byte[] _bytes = new byte[5];
			byte[] _bytes_src = value.TFPR.getBytes();
			int _j;
			for (_j = 0; _j < 5 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 5);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.FICTMED.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{
			byte[] _bytes = new byte[51];
			byte[] _bytes_src = value.FICTEA.getBytes();
			int _j;
			for (_j = 0; _j < 51 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 51);
		}
		{
			byte[] _bytes = new byte[6];
			byte[] _bytes_src = value.FICTYPE.getBytes();
			int _j;
			for (_j = 0; _j < 6 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 6);
		}
		{ 
			o.write_long (value.ASGBP.length);
			for (int __i = 0; __i < value.ASGBP.length; __i++) { 
				com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_tHelper.write (o, value.ASGBP[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SEG_SO_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[88];
				members[0] = new StructMember();
				members[0].name = "SEGNO";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (2, members[0].type);
				members[1] = new StructMember();
				members[1].name = "CA";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (11, members[1].type);
				members[2] = new StructMember();
				members[2].name = "PR";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (5, members[2].type);
				members[3] = new StructMember();
				members[3].name = "LSTAT";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (5, members[3].type);
				members[4] = new StructMember();
				members[4].name = "BP";
				members[4].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[4].type = ORB.init().create_array_tc (17, members[4].type);
				members[5] = new StructMember();
				members[5].name = "OBP";
				members[5].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[5].type = ORB.init().create_array_tc (13, members[5].type);
				members[6] = new StructMember();
				members[6].name = "LPORG";
				members[6].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[6].type = ORB.init().create_array_tc (8, members[6].type);
				members[7] = new StructMember();
				members[7].name = "RLOE";
				members[7].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[7].type = ORB.init().create_array_tc (8, members[7].type);
				members[8] = new StructMember();
				members[8].name = "RLOA";
				members[8].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[8].type = ORB.init().create_array_tc (51, members[8].type);
				members[9] = new StructMember();
				members[9].name = "RLOC";
				members[9].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[9].type = ORB.init().create_array_tc (12, members[9].type);
				members[10] = new StructMember();
				members[10].name = "RLC";
				members[10].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[10].type = ORB.init().create_array_tc (12, members[10].type);
				members[11] = new StructMember();
				members[11].name = "LOTI";
				members[11].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[11].type = ORB.init().create_array_tc (3, members[11].type);
				members[12] = new StructMember();
				members[12].name = "TEA";
				members[12].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[12].type = ORB.init().create_array_tc (51, members[12].type);
				members[13] = new StructMember();
				members[13].name = "TP";
				members[13].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[13].type = ORB.init().create_array_tc (6, members[13].type);
				members[14] = new StructMember();
				members[14].name = "COMM";
				members[14].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[14].type = ORB.init().create_array_tc (2, members[14].type);
				members[15] = new StructMember();
				members[15].name = "UF";
				members[15].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[15].type = ORB.init().create_array_tc (5, members[15].type);
				members[16] = new StructMember();
				members[16].name = "SC";
				members[16].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[16].type = ORB.init().create_array_tc (4, members[16].type);
				members[17] = new StructMember();
				members[17].name = "TETE";
				members[17].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[17].type = ORB.init().create_array_tc (14, members[17].type);
				members[18] = new StructMember();
				members[18].name = "CQ";
				members[18].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[18].type = ORB.init().create_array_tc (10, members[18].type);
				members[19] = new StructMember();
				members[19].name = "DEF";
				members[19].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[19].type = ORB.init().create_array_tc (4, members[19].type);
				members[20] = new StructMember();
				members[20].name = "DEFD";
				members[20].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[20].type = ORB.init().create_array_tc (9, members[20].type);
				members[21] = new StructMember();
				members[21].name = "CTT";
				members[21].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[21].type = ORB.init().create_array_tc (10, members[21].type);
				members[22] = new StructMember();
				members[22].name = "DEFL";
				members[22].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[22].type = ORB.init().create_array_tc (51, members[22].type);
				members[23] = new StructMember();
				members[23].name = "LT";
				members[23].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[23].type = ORB.init().create_array_tc (6, members[23].type);
				members[24] = new StructMember();
				members[24].name = "LNOP";
				members[24].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[24].type = ORB.init().create_array_tc (3, members[24].type);
				members[25] = new StructMember();
				members[25].name = "SYSTP";
				members[25].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[25].type = ORB.init().create_array_tc (10, members[25].type);
				members[26] = new StructMember();
				members[26].name = "PGSNO";
				members[26].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[26].type = ORB.init().create_array_tc (5, members[26].type);
				members[27] = new StructMember();
				members[27].name = "FLDLTS";
				members[27].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[27].type = ORB.init().create_array_tc (5, members[27].type);
				members[28] = new StructMember();
				members[28].name = "COLTS";
				members[28].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[28].type = ORB.init().create_array_tc (5, members[28].type);
				members[29] = new StructMember();
				members[29].name = "CNST";
				members[29].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[29].type = ORB.init().create_array_tc (5, members[29].type);
				members[30] = new StructMember();
				members[30].name = "ORIG";
				members[30].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[30].type = ORB.init().create_array_tc (2, members[30].type);
				members[31] = new StructMember();
				members[31].name = "MPROV";
				members[31].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[31].type = ORB.init().create_array_tc (5, members[31].type);
				members[32] = new StructMember();
				members[32].name = "MCLLI";
				members[32].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[32].type = ORB.init().create_array_tc (12, members[32].type);
				members[33] = new StructMember();
				members[33].name = "MLOC";
				members[33].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[33].type = ORB.init().create_array_tc (4, members[33].type);
				members[34] = new StructMember();
				members[34].name = "MCA";
				members[34].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[34].type = ORB.init().create_array_tc (11, members[34].type);
				members[35] = new StructMember();
				members[35].name = "MPR";
				members[35].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[35].type = ORB.init().create_array_tc (5, members[35].type);
				members[36] = new StructMember();
				members[36].name = "RLA";
				members[36].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[36].type = ORB.init().create_array_tc (51, members[36].type);
				members[37] = new StructMember();
				members[37].name = "SDP";
				members[37].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[37].type = ORB.init().create_array_tc (2, members[37].type);
				members[38] = new StructMember();
				members[38].name = "TSB";
				members[38].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[38].type = ORB.init().create_array_tc (4, members[38].type);
				members[39] = new StructMember();
				members[39].name = "TPR";
				members[39].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[39].type = ORB.init().create_array_tc (7, members[39].type);
				members[40] = new StructMember();
				members[40].name = "LMURMK";
				members[40].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[40].type = ORB.init().create_array_tc (19, members[40].type);
				members[41] = new StructMember();
				members[41].name = "EWOID";
				members[41].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[41].type = ORB.init().create_array_tc (13, members[41].type);
				members[42] = new StructMember();
				members[42].name = "EWODD";
				members[42].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[42].type = ORB.init().create_array_tc (9, members[42].type);
				members[43] = new StructMember();
				members[43].name = "LSTID";
				members[43].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[43].type = ORB.init().create_array_tc (8, members[43].type);
				members[44] = new StructMember();
				members[44].name = "SOLST";
				members[44].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[44].type = ORB.init().create_array_tc (3, members[44].type);
				members[45] = new StructMember();
				members[45].name = "SOITM";
				members[45].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[45].type = ORB.init().create_array_tc (2, members[45].type);
				members[46] = new StructMember();
				members[46].name = "SOLSTDD";
				members[46].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[46].type = ORB.init().create_array_tc (9, members[46].type);
				members[47] = new StructMember();
				members[47].name = "RSVINFO";
				members[47].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[47].type = ORB.init().create_array_tc (76, members[47].type);
				members[48] = new StructMember();
				members[48].name = "RSVDAT";
				members[48].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[48].type = ORB.init().create_array_tc (9, members[48].type);
				members[49] = new StructMember();
				members[49].name = "RSVRMK";
				members[49].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[49].type = ORB.init().create_array_tc (51, members[49].type);
				members[50] = new StructMember();
				members[50].name = "RSTTE";
				members[50].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[50].type = ORB.init().create_array_tc (21, members[50].type);
				members[51] = new StructMember();
				members[51].name = "PERM";
				members[51].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[51].type = ORB.init().create_array_tc (2, members[51].type);
				members[52] = new StructMember();
				members[52].name = "XRST";
				members[52].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[52].type = ORB.init().create_array_tc (21, members[52].type);
				members[53] = new StructMember();
				members[53].name = "RMK0TE";
				members[53].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[53].type = ORB.init().create_array_tc (51, members[53].type);
				members[54] = new StructMember();
				members[54].name = "RMK0PR";
				members[54].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[54].type = ORB.init().create_array_tc (51, members[54].type);
				members[55] = new StructMember();
				members[55].name = "CQC";
				members[55].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[55].type = ORB.init().create_array_tc (2, members[55].type);
				members[56] = new StructMember();
				members[56].name = "CDC";
				members[56].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[56].type = ORB.init().create_array_tc (3, members[56].type);
				members[57] = new StructMember();
				members[57].name = "ASGBPR";
				members[57].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[57].type = ORB.init().create_array_tc (17, members[57].type);
				members[58] = new StructMember();
				members[58].name = "ASBPSTAT";
				members[58].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[58].type = ORB.init().create_array_tc (5, members[58].type);
				members[59] = new StructMember();
				members[59].name = "PGSTP";
				members[59].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[59].type = ORB.init().create_array_tc (10, members[59].type);
				members[60] = new StructMember();
				members[60].name = "LTS";
				members[60].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[60].type = ORB.init().create_array_tc (5, members[60].type);
				members[61] = new StructMember();
				members[61].name = "DLE";
				members[61].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[61].type = ORB.init().create_array_tc (2, members[61].type);
				members[62] = new StructMember();
				members[62].name = "TSI";
				members[62].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[62].type = ORB.init().create_array_tc (2, members[62].type);
				members[63] = new StructMember();
				members[63].name = "DLERMK";
				members[63].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[63].type = ORB.init().create_array_tc (51, members[63].type);
				members[64] = new StructMember();
				members[64].name = "DLERST";
				members[64].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[64].type = ORB.init().create_array_tc (21, members[64].type);
				members[65] = new StructMember();
				members[65].name = "DLEONU";
				members[65].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[65].type = ORB.init().create_array_tc (51, members[65].type);
				members[66] = new StructMember();
				members[66].name = "ONURST";
				members[66].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[66].type = ORB.init().create_array_tc (21, members[66].type);
				members[67] = new StructMember();
				members[67].name = "ONUXRST";
				members[67].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[67].type = ORB.init().create_array_tc (21, members[67].type);
				members[68] = new StructMember();
				members[68].name = "ONURMK";
				members[68].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[68].type = ORB.init().create_array_tc (51, members[68].type);
				members[69] = new StructMember();
				members[69].name = "TFCA1";
				members[69].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[69].type = ORB.init().create_array_tc (11, members[69].type);
				members[70] = new StructMember();
				members[70].name = "TFCA2";
				members[70].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[70].type = ORB.init().create_array_tc (11, members[70].type);
				members[71] = new StructMember();
				members[71].name = "TFPR1";
				members[71].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[71].type = ORB.init().create_array_tc (5, members[71].type);
				members[72] = new StructMember();
				members[72].name = "TFPR2";
				members[72].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[72].type = ORB.init().create_array_tc (5, members[72].type);
				members[73] = new StructMember();
				members[73].name = "FICTMED1";
				members[73].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[73].type = ORB.init().create_array_tc (6, members[73].type);
				members[74] = new StructMember();
				members[74].name = "FICTMED2";
				members[74].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[74].type = ORB.init().create_array_tc (6, members[74].type);
				members[75] = new StructMember();
				members[75].name = "FICTEA1";
				members[75].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[75].type = ORB.init().create_array_tc (51, members[75].type);
				members[76] = new StructMember();
				members[76].name = "FICTEA2";
				members[76].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[76].type = ORB.init().create_array_tc (51, members[76].type);
				members[77] = new StructMember();
				members[77].name = "FICTYPE1";
				members[77].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[77].type = ORB.init().create_array_tc (6, members[77].type);
				members[78] = new StructMember();
				members[78].name = "FICTYPE2";
				members[78].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[78].type = ORB.init().create_array_tc (6, members[78].type);
				members[79] = new StructMember();
				members[79].name = "ABPRSVINFO";
				members[79].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[79].type = ORB.init().create_array_tc (76, members[79].type);
				members[80] = new StructMember();
				members[80].name = "ABPRSVDAT";
				members[80].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[80].type = ORB.init().create_array_tc (9, members[80].type);
				members[81] = new StructMember();
				members[81].name = "ABPRSVRMK";
				members[81].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[81].type = ORB.init().create_array_tc (51, members[81].type);
				members[82] = new StructMember();
				members[82].name = "TFCA";
				members[82].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[82].type = ORB.init().create_array_tc (11, members[82].type);
				members[83] = new StructMember();
				members[83].name = "TFPR";
				members[83].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[83].type = ORB.init().create_array_tc (5, members[83].type);
				members[84] = new StructMember();
				members[84].name = "FICTMED";
				members[84].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[84].type = ORB.init().create_array_tc (6, members[84].type);
				members[85] = new StructMember();
				members[85].name = "FICTEA";
				members[85].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[85].type = ORB.init().create_array_tc (51, members[85].type);
				members[86] = new StructMember();
				members[86].name = "FICTYPE";
				members[86].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[86].type = ORB.init().create_array_tc (6, members[86].type);
				members[87] = new StructMember();
				members[87].name = "ASGBP";
				members[87].type = com.sbc.gwsvcs.service.facsaccess.interfaces.ASGBP_tHelper.type();
				members[87].type = ORB.init().create_sequence_tc (0, members[87].type);
				myTc = ORB.init().create_struct_tc (id(), "SEG_SO_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/facsaccess/interfaces/SEG_SO_t:1.0"; } 
}
