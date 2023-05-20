// $Id: ExactMatchResp.java,v 1.3 2004/04/15 15:13:40 db4252 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import com.sbc.eia.idl.lim_types.Location;
/**
 * This interface is implemented by classes that know how to convert a host
 * ExactMatch response to an IDL ProviderLocationPropertySeq.
 * Creation date: (4/3/01 9:12:20 AM)
 * @author: David Prager
 */
public interface ExactMatchResp
{
    /**
     * Instantiate and return a Location object from contained host data.
     * Creation date: (4/3/01 9:13:38 AM)
     * @return Location
     */
    Location toExactMatch();
}
