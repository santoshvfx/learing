// $Id: LnDataItem_t.java,v 1.1 2002/09/29 04:28:11 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class LnDataItem_t implements java.io.Serializable { 
	public com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t LnInfo;
	public String MODULR_WIRE_ID;
	public String DISCT_REAS_DESC;
	public com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t ActvtyDt;
	public char CONN_THRU_IND;
	public char CONN_FAC_IND;
	public char DED_INSD_PLNT_IND;
	public String COS;

	public LnDataItem_t () {
	}
	public LnDataItem_t (com.sbc.gwsvcs.service.premisserver.interfaces.LnInfo_t LnInfo, String MODULR_WIRE_ID, String DISCT_REAS_DESC, com.sbc.gwsvcs.service.premisserver.interfaces.Dt_t ActvtyDt, char CONN_THRU_IND, char CONN_FAC_IND, char DED_INSD_PLNT_IND, String COS) { 
		this.LnInfo = LnInfo;
		this.MODULR_WIRE_ID = MODULR_WIRE_ID;
		this.DISCT_REAS_DESC = DISCT_REAS_DESC;
		this.ActvtyDt = ActvtyDt;
		this.CONN_THRU_IND = CONN_THRU_IND;
		this.CONN_FAC_IND = CONN_FAC_IND;
		this.DED_INSD_PLNT_IND = DED_INSD_PLNT_IND;
		this.COS = COS;

	}
}
