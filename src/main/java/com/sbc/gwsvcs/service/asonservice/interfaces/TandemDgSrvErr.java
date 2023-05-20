// $Id: TandemDgSrvErr.java,v 1.1 2002/09/29 03:53:01 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class TandemDgSrvErr implements java.io.Serializable { 
	public String advisoryMsg;

	public TandemDgSrvErr () {
	}
	public TandemDgSrvErr (String advisoryMsg) { 
		this.advisoryMsg = advisoryMsg;

	}
}
