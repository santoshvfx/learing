// $Id: StNbrId_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class StNbrId_t implements java.io.Serializable { 
	public String SAD_HOUS_PRFX;
	public String SAD_HOUS_NBR;
	public String SAD_HOUS_NBR_SUFX;

	public StNbrId_t () {
	}
	public StNbrId_t (String SAD_HOUS_PRFX, String SAD_HOUS_NBR, String SAD_HOUS_NBR_SUFX) { 
		this.SAD_HOUS_PRFX = SAD_HOUS_PRFX;
		this.SAD_HOUS_NBR = SAD_HOUS_NBR;
		this.SAD_HOUS_NBR_SUFX = SAD_HOUS_NBR_SUFX;

	}
}
