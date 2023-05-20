// $Id: _PREMISSERVER_ConstStub.java,v 1.1 2002/09/29 04:28:18 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import org.omg.CORBA.portable.*;
import org.omg.CORBA.*;
import com.sbc.vicunalite.api.orb.*;
	import com.sbc.vicunalite.api.*;

public class _PREMISSERVER_ConstStub extends ObjectImpl implements PREMISSERVER_Const { 
	private static final String _type_ids[] = { 
		"IDL:com/sbc/gwsvcs/service/premisserver/interfaces/PREMISSERVER_Const:1.0"
	};
	public _PREMISSERVER_ConstStub (Delegate d) { _set_delegate (d); }
	public String[] _ids() { return (String[]) _type_ids.clone(); }
}
