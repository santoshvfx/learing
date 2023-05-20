// $Id: Pos_tHelper.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StructMember;

public class Pos_tHelper { 
	private static TypeCode myTc = null;
	private Pos_tHelper () { }
	public static char extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return a.extract_char (); 
	}
	public static String id() { return "IDL:com.sbc.gwsvcs.service.premisserver.interfaces.Pos_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, char t) { 
		a.insert_char (t); 
	}
	public static TypeCode type() { 
		if (myTc == null)
		{
			StructMember members[] = new StructMember[1];
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.Pos_tHelper.id(), "Pos_t", members[0].type);
			myTc = members[0].type;
		}
		return myTc; 
	}
}
