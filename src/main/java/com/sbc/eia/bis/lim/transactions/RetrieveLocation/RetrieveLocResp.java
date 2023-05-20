// $Id: RetrieveLocResp.java,v 1.17 2007/10/07 23:20:18 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.AddressMatchResult;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ExtensionProperty;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.lim_types.AlternativeAddressResult;
import com.sbc.eia.idl.lim_types.SubmittedAddressExceptionSeqOpt;
import com.sbc.eia.idl.lim_types.AlternativeAddress;
import com.sbc.eia.idl.lim_types.AlternativeAddressSeqOpt;

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
    
    public RetrieveLocationForAddressReturn toAddressReturn()
    {
    	RetrieveLocationForAddressReturn rv =
    		new RetrieveLocationForAddressReturn(getUtility().getCallerContext(),
    			toAddressMatchResult());
    	
    	getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForAddress: " + this.toString());
    
    	return rv;
    }
 
     /**
     * Create and return a RetrieveLocationForServiceReturn IDL object for the client.
     * Creation date: (3/29/01 1:43:59 PM)
     * @return RetrieveLocationForServiceReturn
     */
    public RetrieveLocationForServiceReturn toServiceReturn()
    {
        RetrieveLocationForServiceReturn rv =
            new RetrieveLocationForServiceReturn(getUtility().getCallerContext(),
                toAddressMatchResult());
    
        getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForService: " + this.toString());
        
        return rv;
    }

    /**
     * Create and return a RetrieveLocation object to be used in the IDL response to the client.
     * Creation date: (3/29/01 1:59:40 PM)
     * @return AddressMatchResult
     */
    protected AddressMatchResult toAddressMatchResult()
    {
        AddressMatchResult rl = new AddressMatchResult();
    
        if (this instanceof ExactMatchResp)
        {
            rl.aLocation(((ExactMatchResp) this).toExactMatch());
        }
        else if (this instanceof AlternativeAddressResultResp)
        	rl.aAlternativeAddressResult (((AlternativeAddressResultResp) this).toAltAddrResult());
        else   //it's an AlternativeAddressesResp
        {
        	AlternativeAddress [] altAddresses = ((AlternativeAddressesResp) this).toAltAddr();
        	AlternativeAddressSeqOpt altAddrSeqOpt = new AlternativeAddressSeqOpt ();
        	altAddrSeqOpt.theValue (altAddresses);
        	AlternativeAddressResult altAddrRes = new AlternativeAddressResult();
        	SubmittedAddressExceptionSeqOpt submitAddrSeqOpt = new SubmittedAddressExceptionSeqOpt ();
        	submitAddrSeqOpt.__default();
        	altAddrRes.aSubmittedAddressExceptions = submitAddrSeqOpt; 
        	altAddrRes.aAlternativeAddresses = altAddrSeqOpt;
        	rl.aAlternativeAddressResult (altAddrRes);
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