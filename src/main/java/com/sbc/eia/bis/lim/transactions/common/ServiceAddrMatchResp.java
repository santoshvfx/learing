//$Id: ServiceAddrMatchResp.java,v 1.4 2007/10/17 19:06:13 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import java.util.HashSet;
import java.util.Set;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.lim_types.ServiceLocation;

/**
 * Clone from AddrMatchResp.java. Represents a Match response from a host, but this abstract class has NO host-specific
 * knowledge, and must be subclassed by a host-specific class.
 * @author jh9785
 *
 */
abstract public class ServiceAddrMatchResp extends RetrieveLocResp implements ServiceAddressExactMatchResp
{
    protected Set workingTnSet = new HashSet();
	protected ServiceLocation serviceLocation = null;
	protected ProviderLocationProperty[] providerLocationPropertyArray = null;
    
    /**
     * ServiceAddrMatchResp constructor.
     * Creation date: (3/14/01 11:43:45 AM)
     * @param utility LIMBase
     */
    public ServiceAddrMatchResp(LIMBase utility)
    {
        super(utility);
    }

    /**
     * Create and return an Location.  This method calls several abstract methods
     * which must be implemented in the subclass.  This method implements the
     * ExactMatchResp interface.
     * Creation date: (3/7/01 9:45:31 AM)
     * @return Location
     */
    public ServiceLocation toExactMatch()
    {
    	return getServiceLocation();
    }
    
    /**
     * getServiceLocation
     * @return
     */
    private ServiceLocation getServiceLocation()
    {
        if(serviceLocation == null)
        {
            setServiceLocation();
        }
        return serviceLocation;
    }

    /**
     * Add a working TN to the internal Set.
     * Creation date: (3/15/01 8:40:33 AM)
     * @param npa String
     * @param nxx String
     * @param line String
     */
    public void addWorkingTn(String npa, String nxx, String line)
    {
        workingTnSet.add(new TN(npa,nxx,line,null));
    }
    
    /**
     * Return the TNs from the internal Set as a TN array.
     * Creation date: (3/15/01 8:35:18 AM)
     * @return TN[]
     */
    protected TN[] getWorkingTnList()
    {
        return (TN[]) workingTnSet.toArray(new TN[workingTnSet.size()]);
    }
    
    /**
     * Return the Working TN Set
     * Creation date: (04/22/03 12:00:00 PM)
     * @return Set
     */
    public Set getWorkingTnSet()
    { 
        return workingTnSet;
    }
    
    /**
     * This abstract method should construct and store a ProviderLocationPropertySeq,
     * from the host data.
     */
    protected abstract void setServiceLocation();
}
