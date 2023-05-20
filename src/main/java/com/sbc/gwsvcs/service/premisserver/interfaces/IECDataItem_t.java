// $Id: IECDataItem_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class IECDataItem_t implements java.io.Serializable { 
	public String IEC_CD;
	public String IEC_RMK_1_DESC;
	public String IEC_RMK_2_DESC;

	public IECDataItem_t () {
	}
	public IECDataItem_t (String IEC_CD, String IEC_RMK_1_DESC, String IEC_RMK_2_DESC) { 
		this.IEC_CD = IEC_CD;
		this.IEC_RMK_1_DESC = IEC_RMK_1_DESC;
		this.IEC_RMK_2_DESC = IEC_RMK_2_DESC;

	}
}
