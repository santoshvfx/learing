// $Id: PremisComboResp.java,v 1.5 2007/10/06 01:04:24 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.transactions.common.ServiceAddressAlternativeAddressesResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.lim_types.AlternativeServiceAddress;

/**
 * This class manages Premis responses that may require a mixture of address types in the
 * response:  matches, lists of addresses, and lists of address ranges.
 * Creation date: (3/16/01 9:49:27 AM)
 * @author: David Prager
 */
public class PremisComboResp extends RetrieveLocResp implements ServiceAddressAlternativeAddressesResp
{
	protected List matches = new ArrayList();
	protected List lists   = new ArrayList();
	protected List ranges  = new ArrayList();

    /**
     * Package-access constructor.
     * Creation date: (3/27/01 9:55:15 AM)
     * @param utility LIMBase
     */
    PremisComboResp(LIMBase utility)
    {
    	super(utility);
    }
    /**
     * Append the Premis Address List response to the internal list of addresses.
     * Creation date: (3/27/01 10:24:56 AM)
     * @param list PremisAddrListResp
     */
    public void add(PremisAddrListResp list)
    {
    	lists.add(list);
    }
    /**
     * Append the Premis Address Range response to the internal list of address ranges.
     * Creation date: (3/27/01 10:04:41 AM)
     * @param range PremisAddrRangeResp
     */
    public void add(PremisAddrRangeResp range)
    {
    	ranges.add(range);
    }
    /**
     * Append the Premis Hit response to the internal list of matches.
     * Creation date: (3/27/01 10:26:16 AM)
     * @param match PremisHit
     */
    public void add(PremisHit match)
    {
    	matches.add(match);
    }
    /**
     * Compares two objects for equality. Returns a boolean that indicates
     * whether this object is equivalent to the specified object. This method
     * is used when an object is stored in a hashtable.
     * @param obj the Object to compare with
     * @return true if these Objects are equal; false otherwise.
     * @see java.util.Hashtable
     */
    public boolean equals(Object obj)
    {
    	if (obj instanceof PremisComboResp)
    	{
    		PremisComboResp pcr = (PremisComboResp) obj;
    		return ( lists.equals(pcr.lists)
    			&&   ranges.equals(pcr.ranges)
    			&&   matches.equals(pcr.matches) );
    	}
    	return false;
    }
    /**
     * Automaticly executed once PremisComboResp completes.
     * Creation date: (10/4/01 1:03:39 PM)
     */
    protected void finalize() {
    	matches.clear();
    	lists.clear();
    	ranges.clear();
    }
    /**
     * Generates a hash code for the receiver.
     * This method is supported primarily for
     * hash tables, such as those provided in java.util.
     * @return an integer hash code for the receiver
     * @see java.util.Hashtable
     */
    public int hashCode()
    {
    	return lists.hashCode() + ranges.hashCode() + matches.hashCode();
    }
    /**
     * Create an array of AlternativeAddresses from the internal lists.  This method implements
     * the AlternativeAddressesResp interface.
     * Creation date: (3/16/01 10:40:51 AM)
     * @return AlternativeAddress[]
     */
    public AlternativeServiceAddress[] toAltAddr()
    {
    	List allArrays = new ArrayList();
    	
    	Iterator it = matches.iterator();
    	while (it.hasNext())
    	{
    	    AlternativeServiceAddress aa = new AlternativeServiceAddress();
    		//Jamie: Based on the old version of codes, here only need to return a list of Address without properties under ServiceLocation.
    	    //So don't use PremisHit.getServiceLocation() method in here.
    		aa.aServiceLocation(LIMIDLUtil.formatServiceLocation(((PremisHit) it.next()).getServiceAddress().getAddress2_HitResponse()));
    		allArrays.add(aa);
    	}
    
    	it = lists.iterator();
    	while (it.hasNext())
    	{
    		allArrays.addAll(Arrays.asList(((PremisAddrListResp) it.next()).toAltAddr()));
    	}
    	
    	it = ranges.iterator();
    	while (it.hasNext())
    	{
    		PremisAddrRangeResp rangeResp = (PremisAddrRangeResp) it.next();
    		while (it.hasNext())
    		{
    			rangeResp.addToList(((PremisAddrRangeResp) it.next()).getRangeList());
    		}
    		allArrays.addAll(Arrays.asList(rangeResp.toAltAddr()));
    	}
    
    	int listSize = Math.min(allArrays.size(),(getMaxAddresses() == NO_SIZE_LIMIT) ? allArrays.size() : getMaxAddresses());
    	return (AlternativeServiceAddress[]) allArrays.subList(0,listSize).toArray(new AlternativeServiceAddress[listSize]);
    }
    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
    	return "Matches " + matches.toString()
    		+ ", Lists " + lists.toString()
    		+ ", Ranges " + ranges.toString();
    }
}
