// $Id: OvalsRetrieveLocReq.java,v 1.4 2004/05/10 17:34:45 rz7367 Exp $

package com.sbc.eia.bis.BusinessInterface.ovals;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;

/**
 * This class extends RetrieveLocReq to specialize it for OVALS.
 * Creation date: (12/5/01 3:09:55 PM)
 * @author: Rachel Zadok - Local
 */
public class OvalsRetrieveLocReq extends RetrieveLocReq
{
/**
 * OvalsRetrieveLocReq constructor.
 * Creation date: (12/5/01 3:39:30 PM)
 * @param utility LIMBase
 * @param service TN
 * @param propertiesToGet ProviderLocationProperties
 */
public OvalsRetrieveLocReq (LIMBase utility, TN service, ProviderLocationProperties [] propertiesToGet) 
{
	super(utility, service, propertiesToGet);	
}
/**
 * OvalsRetrieveLocReq constructor.
 * @param utility LIMBase
 * @param address AddressHandler
 * @param propertiesToGet ProviderLocationProperties
 */
public OvalsRetrieveLocReq (LIMBase utility, AddressHandler address, ProviderLocationProperties [] propertiesToGet) 
{
	super (utility, address, propertiesToGet);
}


}
