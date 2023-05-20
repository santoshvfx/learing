// $Id: _PREMISSERVER_ConstImplBase.java,v 1.1 2002/09/29 04:28:18 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import org.omg.CORBA.portable.*;
import org.omg.CORBA.*;
  import com.sbc.vicunalite.api.orb.*;
  import com.sbc.vicunalite.api.*;

public abstract class _PREMISSERVER_ConstImplBase extends DynamicImplementation implements PREMISSERVER_Const { 
	private static final String _type_ids[] = { 
		"IDL:com/sbc/gwsvcs/service/premisserver/interfaces/PREMISSERVER_Const:1.0" 
	};
	private static java.util.Dictionary _methods = new java.util.Hashtable();
	static { 
	}
	public _PREMISSERVER_ConstImplBase () { }
	public String[] _ids() { return (String[]) _type_ids.clone(); }
	public void invoke (ServerRequest req) { 
		switch (((java.lang.Integer) _methods.get(req.operation())).intValue()) { 
			default: 
				throw new BAD_OPERATION ();  
		} 
	}
}
