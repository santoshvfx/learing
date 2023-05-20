//$Id: PostalAddrMatchResp.java,v 1.1 2008/01/17 21:40:17 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim_types.PostalLocation;

/**
 * Clone from AddrMatchResp.java. Represents a Match response from a host, but this abstract class has NO host-specific
 * knowledge, and must be subclassed by a host-specific class.
 * @author jh9785
 *
 */
abstract public class PostalAddrMatchResp extends RetrieveLocResp implements PostalAddressExactMatchResp
{   
    /**
     * PostalAddrMatchResp constructor.
     * Creation date: (9/14/07 11:43:45 AM)
     * @param utility LIMBase
     */
    public PostalAddrMatchResp(LIMBase utility) 
    {
        super(utility);
    }
    
    /**
     * Create and return an PostalLocation.  This method calls several abstract methods
     * which must be implemented in the subclass.  This method implements the
     * ExactMatchResp interface.
     * Creation date: (3/7/01 9:45:31 AM)
     * @return PostalLocation
     */
    public PostalLocation toExactMatch()
    {
    	return getPostalLocation();
    }
 
    /**
     * This abstract method should construct and store a ProviderLocationPropertySeq,
     * from the host data.
     */
    protected abstract PostalLocation getPostalLocation();
}
