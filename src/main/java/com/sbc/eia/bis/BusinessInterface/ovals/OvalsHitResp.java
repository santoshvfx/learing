// $Id: OvalsHitResp.java,v 1.4 2005/11/05 00:22:14 rz7367 Exp $

package com.sbc.eia.bis.BusinessInterface.ovals;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.*;
import com.sbc.eia.bis.lim.util.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.lim.helpers.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.bccs.idl.helpers.*;
/**
 * This class represents an address-match from Ovals
 * Creation date: (5/5/04 9:37:38 AM)
 * @author: Rachel Zadok
 */
public class OvalsHitResp extends AddrMatchResp
{

/**
 * @param utility LIMBase
 * @param providerLocationProperty ProviderLocationProperty
 * @exception SystemFailure
 */
public OvalsHitResp (LIMBase utility, ProviderLocationPropertyBuilder providerLocationProperty)
	throws SystemFailure
{
	super(utility);
	providerLocationPropertyArray = new ProviderLocationProperty[1];
	providerLocationPropertyArray[0] = providerLocationProperty.getProviderLocationProperty();
    providerLocationPropertyArray[0].aProviderName.theValue (LIMTag.DATA_SERVICES);   
}

protected void setProviderLocationPropertySeq()
{
	return;  
}

protected void setLocationId()
{
	locationId = IDLUtil.toOpt("");
}

}
