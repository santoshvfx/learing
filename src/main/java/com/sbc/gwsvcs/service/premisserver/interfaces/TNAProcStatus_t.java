// $Id: TNAProcStatus_t.java,v 1.1 2002/09/29 04:28:14 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.interfaces;

import com.sbc.vicunalite.api.*;

final public class TNAProcStatus_t implements java.io.Serializable { 
	public String RTCD;
	public com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_t TnCtgyCdItem[];
	public char INWD_TN[];
	public char INWTN_ADDR[];

	public TNAProcStatus_t () {
	}
	public TNAProcStatus_t (String RTCD, com.sbc.gwsvcs.service.premisserver.interfaces.TnCtgyCdItem_t TnCtgyCdItem[], char INWD_TN[], char INWTN_ADDR[]) { 
		this.RTCD = RTCD;
		this.TnCtgyCdItem = TnCtgyCdItem;
		this.INWD_TN = INWD_TN;
		this.INWTN_ADDR = INWTN_ADDR;

	}
}
