//$Id: RetrieveLocResp.java,v 1.5 2008/01/17 22:08:19 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim.ServiceAddressMatchResult;
import com.sbc.eia.idl.lim_types.ServiceLocation;
import com.sbc.eia.idl.lim.PostalAddressMatchResult;

/**
 * Abstract superclass for handling host responses for RetrieveLocation transactions.
 * Subclasses of RetrieveLocResp must implement one of the following interfaces:
 * - ExactMatchResp
 * - AlternativeAddressesResp
 * (See method "toRetrieveLocation()")
 *
 * RetrieveLocResp has no host-specific knowledge.
 *
 * Creation date: (3/6/01 3:08:56 PM)
 * @author: David Prager
 */
abstract public class RetrieveLocResp
{
	protected LIMBase utility = null;
	public final static int NO_SIZE_LIMIT = 0;
	protected int maxAddresses = NO_SIZE_LIMIT;
	protected int maxUnits = NO_SIZE_LIMIT;
    
    /**
     * Create and return a RetrieveLocationForAddressReturn IDL object for the client.
     * Creation date: (3/7/01 9:21:37 AM)
     * @return RetrieveLocationForAddressReturn
     */
    
    /**
     * Create and return a RetrieveLocationForTelephoneNumberReturn IDL object for the client.
     * @return RetrieveLocationForTelephoneNumberReturn 
     */
    public RetrieveLocationForTelephoneNumberReturn toTelephoneNumberReturn()
    {
        RetrieveLocationForTelephoneNumberReturn rv =
            new RetrieveLocationForTelephoneNumberReturn(
                    getUtility().getCallerContext(),
            		((ServiceAddressExactMatchResp) this).toExactMatch());
        
        getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForTelephoneNumber: " + this.toString());
        
        return rv;
    }
 
     /**
     * Create and return a RetrieveLocationForServiceReturn IDL object for the client.
     * Creation date: (3/29/01 1:43:59 PM)
     * @return RetrieveLocationForServiceReturn
     */

    /**
     * Create and return a RetrieveLocationForServiceAddressReturn IDL object for the client.
     * Creation date: (7/22/07 9:21:37 AM)
     * @return RetrieveLocationForServiceAddressReturn
     */
    
    public RetrieveLocationForServiceAddressReturn toServiceAddressReturn()
    {
    	RetrieveLocationForServiceAddressReturn rv =
    		new RetrieveLocationForServiceAddressReturn(getUtility().getCallerContext(),
    			toServiceAddressMatchResult());
    	
    	getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForServiceAddress: " + this.toString());
    
    	return rv;
    }
 
    /**
     * Create and return a RetrieveLocationForPostalAddressReturn IDL object for the client.
     * Creation date: (7/22/07 9:21:37 AM)
     * @return RetrieveLocationForPostalAddressReturn
     */
    
    public RetrieveLocationForPostalAddressReturn toPostalAddressReturn()
    {
        RetrieveLocationForPostalAddressReturn rv =
    		new RetrieveLocationForPostalAddressReturn(getUtility().getCallerContext(),
    		    toPostalAddressMatchResult());
    	
    	getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForPostalAddress: " + this.toString());
    
    	return rv;
    }
    
    /**
     * Create and return a RetrieveLocationForPostalAddressReturn IDL object for the client.
     * Creation date: (7/22/07 9:21:37 AM)
     * @return RetrieveLocationForPostalAddressReturn
     */
    
//    public RetrieveLocationForPostalAddressReturn toPostalAddressReturn()
//    {
//        RetrieveLocationForPostalAddressReturn rv =
//    		new RetrieveLocationForPostalAddressReturn(getUtility().getCallerContext(),
//    		    toPostalAddressMatchResult());
//    	
//    	getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForPostalAddress: " + this.toString());
//    
//    	return rv;
//    }
    
    /**
     * Create and return a ServiceAddressMatchResult object to be used in the IDL response to the client.
     * Creation date: (7/22/07 1:59:40 PM)
     * @return ServiceAddressMatchResult
     */
    protected ServiceAddressMatchResult toServiceAddressMatchResult()
    {
        ServiceAddressMatchResult rl = new ServiceAddressMatchResult();
    
        if (this instanceof ServiceAddressExactMatchResp)
        {
            rl.aServiceLocation(((ServiceAddressExactMatchResp) this).toExactMatch());
        }
        else
        {
        	rl.aAlternativeServiceAddresses(((ServiceAddressAlternativeAddressesResp) this).toAltAddr());
        }
        return rl;
    }
    
    /**
     * Create and return a PostalAddressMatchResult object to be used in the IDL response to the client.
     * Creation date: (9/18/07 1:59:40 PM)
     * @return PostalAddressMatchResult
     */
    protected PostalAddressMatchResult toPostalAddressMatchResult()
    {
        PostalAddressMatchResult rl = new PostalAddressMatchResult();
    
        if (this instanceof PostalAddressExactMatchResp)
        {
            rl.aPostalLocation(((PostalAddressExactMatchResp) this).toExactMatch());
        }
        else
        {
        	rl.aAlternativePostalAddressResult(((PostalAddressAlternativeAddressesResultResp) this).toAltAddrResult());
        }
        return rl;
    }
    
    /**
     * RetrieveLocResp constructor.
     * Creation date: (3/14/01 10:58:43 AM)
     * @param utility LIMBase
     */
    public RetrieveLocResp(LIMBase utility)
    {
    	setUtility(utility);
    }
    
    /**
     * Getter method for the LIMBase object.
     * Creation date: (3/19/01 4:02:57 PM)
     * @return LIMBase
     * @see #setUtility(LIMBase)
     */
    public LIMBase getUtility()
    {
    	return utility;
    }
    
    /**
     * Setter method for the LIMBase object.
     * Creation date: (3/19/01 4:02:57 PM)
     * @param newUtility LIMBase
     * @see #getUtility
     */
    public void setUtility(LIMBase newUtility)
    {
    	utility = newUtility;
    }
    
    /**
     * Getter method for the max number of addresses to return.
     * Creation date: (3/26/01 2:51:47 PM)
     * @return int
     * @see #setMaxAddresses(int)
     */
    public int getMaxAddresses()
    {
    	return maxAddresses;
    }
    
    /**
     * Getter method for the max number of units to return.
     * Creation date: (3/26/01 2:52:06 PM)
     * @return int
     * @see #setMaxUnits(int)
     */
    public int getMaxUnits()
    {
    	return maxUnits;
    }
    
    /**
     * Setter method for the max number of addresses to return.
     * Creation date: (3/26/01 2:51:47 PM)
     * @param newMaxAddresses int
     * @see #getMaxAddresses
     */
    public void setMaxAddresses(int newMaxAddresses)
    {
    	maxAddresses = (newMaxAddresses < 0) ? NO_SIZE_LIMIT : newMaxAddresses;
    }
    
    /**
     * Setter method for the max number of units to return.
     * Creation date: (3/26/01 2:52:06 PM)
     * @param newMaxUnits int
     * @see #getMaxUnits
     */
    public void setMaxUnits(int newMaxUnits)
    {
    	maxUnits = (newMaxUnits < 0) ? NO_SIZE_LIMIT : newMaxUnits;
    }
       
}