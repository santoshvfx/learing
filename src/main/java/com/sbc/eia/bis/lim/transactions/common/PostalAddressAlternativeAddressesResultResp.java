//$Id: PostalAddressAlternativeAddressesResultResp.java,v 1.1 2008/01/17 21:40:17 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import com.sbc.eia.idl.lim_types.AlternativePostalAddressResult;

/**
 * This interface is implemented by classes that know how to convert a host
 * PostalLocation response to an IDL PostalAddressMatchResult
 * Creation date: (9/16/07 10:33:40 AM)
 */
public interface PostalAddressAlternativeAddressesResultResp
{
    /**
     * Instantiate and return an AlternativePostalAddressResult from contained host data.
     * Creation date: (9/16/07 10:33:40 AM)
     * @return AlternativePostalAddressResult
     */
    AlternativePostalAddressResult toAltAddrResult();
}