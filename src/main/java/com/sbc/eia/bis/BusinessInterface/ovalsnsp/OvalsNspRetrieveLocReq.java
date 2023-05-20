//$Id: OvalsNspRetrieveLocReq.java,v 1.4 2008/02/29 23:26:48 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerOvalsNSP;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author gg2712
 */
public class OvalsNspRetrieveLocReq extends RetrieveLocReq
{
	protected int maximumAlternateAddress = 10;
	
	/**
	 * Constructor for OvalsNspRetrieveLocReq.
	 * @param utility
	 * @param aAddressHandlerOvalsNSP
	 * @param aPropertiesToGet
	 */
	public OvalsNspRetrieveLocReq(LIMBase utility, AddressHandlerOvalsNSP aAddressHandlerOvalsNSP, ProviderLocationProperties[] aPropertiesToGet, StringOpt aExchangeCode, int maxAltAddr)
	{
		super(utility, aAddressHandlerOvalsNSP, aPropertiesToGet, aExchangeCode);
		setMaximumAlternateAddress(maxAltAddr);
	}
	
	/**
	 * Returns the maximumAlternateAddress.
	 * @return int
	 */
	public int getMaximumAlternateAddress() 
	{
		return maximumAlternateAddress;
	}

	/**
	 * Sets the maximumAlternateAddress.
	 * @param maximumAlternateAddress The maximumAlternateAddress to set
	 */
	public void setMaximumAlternateAddress(int maximumAlternateAddress) 
	{
		this.maximumAlternateAddress = maximumAlternateAddress;
	}

}
