// $Id: SagaMenuProcStatus_t.java,v 1.1 2002/09/29 04:28:13 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SagaMenuProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_t SagaMenu[];

	public SagaMenuProcStatus_t () {
	}
	public SagaMenuProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuItem_t SagaMenu[]) { 
		this.RTCD = RTCD;
		this.SagaMenu = SagaMenu;

	}
}
