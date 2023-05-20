//$Id: RsagRetrieveLocReq.java,v 1.4 2007/10/16 20:56:23 gg2712 Exp $
package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandler;

/**
 * @author GG2712
 */
public class RsagRetrieveLocReq extends RetrieveLocReq
{
    private String aCrossBoundaryState = null;
    private int aMaxBasicAddressAlternatives = 0;
    private int aMaxLivingUnitAlternatives = 0;
    
    /**
     * Constructor
     * @param utility
     * @param address
     * @param aLocationPropertiesToGet
     * @param aPreviousOwnerName
     */
    public RsagRetrieveLocReq(
            LIMBase utility, 
            AddressHandler address, 
            String[] locationPropertiesToGet,
            String previousOwnerName,
            String crossBoundaryState)
    {
        super(utility, address, locationPropertiesToGet, previousOwnerName);
        aCrossBoundaryState = crossBoundaryState;
    }
    
    /**
     * Constructor
     * @param utility
     * @param tn
     * @param aLocationPropertiesToGet
     */
    public RsagRetrieveLocReq(
            LIMBase utility, 
            TN tn, 
            String[] aLocationPropertiesToGet)
    {
        super(utility, tn, aLocationPropertiesToGet);
    }
    
    /**
     * @return String
     */
    public String getCrossBoundaryState()
    {
        return aCrossBoundaryState;
    }

    /**
     * @return Returns the aMaxBasicAddressAlternatives.
     */
    public int getMaxBasicAddressAlternatives()
    {
        return aMaxBasicAddressAlternatives;
    }
    /**
     * @param maxBasicAddressAlternatives The aMaxBasicAddressAlternatives to set.
     */
    public void setMaxBasicAddressAlternatives(int maxBasicAddressAlternatives)
    {
        aMaxBasicAddressAlternatives = maxBasicAddressAlternatives;
    }
    /**
     * @return Returns the aMaxLivingUnitAlternatives.
     */
    public int getMaxLivingUnitAlternatives()
    {
        return aMaxLivingUnitAlternatives;
    }
    /**
     * @param maxLivingUnitAlternatives The aMaxLivingUnitAlternatives to set.
     */
    public void setMaxLivingUnitAlternatives(int maxLivingUnitAlternatives)
    {
        aMaxLivingUnitAlternatives = maxLivingUnitAlternatives;
    }
}
