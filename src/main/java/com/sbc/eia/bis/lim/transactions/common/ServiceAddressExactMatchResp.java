//$Id: ServiceAddressExactMatchResp.java,v 1.1 2007/10/05 17:59:02 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import com.sbc.eia.idl.lim_types.ServiceLocation;
/**
 * This interface is implemented by classes that know how to convert a host
 * ExactMatch response to an IDL ProviderLocationPropertySeq.
 * Creation date: (4/3/01 9:12:20 AM)
 * @author: David Prager
 */
public interface ServiceAddressExactMatchResp
{
    /**
     * Instantiate and return a Location object from contained host data.
     * Creation date: (4/3/01 9:13:38 AM)
     * @return Location
     */
    ServiceLocation toExactMatch();
}
