//$Id: PostalAddressExactMatchResp.java,v 1.1 2008/01/17 21:40:17 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import com.sbc.eia.idl.lim_types.PostalLocation;
/**
 * This interface is implemented by classes that know how to convert a host
 * ExactMatch response to an IDL PostalAddressMatchResult.
 * Creation date: (9/13/07 9:12:20 AM)
 */
public interface PostalAddressExactMatchResp 
{

    /**
     * Instantiate and return a PostalLocation object from contained host data.
     * Creation date: (9/13/07 9:13:38 AM)
     * @return PostalLocation
     */
    PostalLocation toExactMatch();

}
