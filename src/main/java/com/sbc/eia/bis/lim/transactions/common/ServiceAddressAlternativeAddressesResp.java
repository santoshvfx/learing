//$Id: ServiceAddressAlternativeAddressesResp.java,v 1.1 2007/10/05 17:59:02 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import com.sbc.eia.idl.lim_types.AlternativeServiceAddress;

/**
 * This interface is implemented by classes that know how to convert a host
 * RetrieveLocation response to an IDL AlternativeAddress[].
 * Creation date: (3/16/01 10:33:40 AM)
 * @author: David Prager
 */
public interface ServiceAddressAlternativeAddressesResp
{
    /**
     * Instantiate and return an AlternativeAddress array from contained host data.
     * Creation date: (3/16/01 10:35:47 AM)
     * @return AlternativeAddress[]
     */
    AlternativeServiceAddress[] toAltAddr();
}