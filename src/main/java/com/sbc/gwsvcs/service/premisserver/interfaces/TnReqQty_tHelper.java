// $Id: TnReqQty_tHelper.java,v 1.1 2002/09/29 04:28:15 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;
import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.ORB;
import org.omg.CORBA.StructMember;

public class TnReqQty_tHelper { 
	private static TypeCode myTc = null;
	private TnReqQty_tHelper () { }
	public static int extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return a.extract_long (); 
	}
	public static String id() { return "IDL:com.sbc.gwsvcs.service.premisserver.interfaces.TnReqQty_t:1.0"; }
	public static void insert (org.omg.CORBA.Any a, int t) { 
		a.insert_long (t); 
	}
	public static TypeCode type() { 
		if (myTc == null)
		{
			StructMember members[] = new StructMember[1];
			members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
			members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.premisserver.interfaces.TnReqQty_tHelper.id(), "TnReqQty_t", members[0].type);
			myTc = members[0].type;
		}
		return myTc; 
	}
}
