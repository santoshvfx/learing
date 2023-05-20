// $Id: SwngEntyDataItem_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class SwngEntyDataItem_t implements java.io.Serializable { 
	public String SWNG_ENTY_RMK_1_DESC;
	public String SWNG_ENTY_RMK_2_DESC;
	public com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t IECData[];

	public SwngEntyDataItem_t () {
	}
	public SwngEntyDataItem_t (String SWNG_ENTY_RMK_1_DESC, String SWNG_ENTY_RMK_2_DESC, com.sbc.gwsvcs.service.premisserver.interfaces.IECDataItem_t IECData[]) { 
		this.SWNG_ENTY_RMK_1_DESC = SWNG_ENTY_RMK_1_DESC;
		this.SWNG_ENTY_RMK_2_DESC = SWNG_ENTY_RMK_2_DESC;
		this.IECData = IECData;

	}
}
