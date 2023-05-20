// $Id: commandline_st.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class commandline_st implements java.io.Serializable { 
	public String commandName;
	public String commandFiller;

	public commandline_st () {
	}
	public commandline_st (String commandName, String commandFiller) { 
		this.commandName = commandName;
		this.commandFiller = commandFiller;

	}
}
