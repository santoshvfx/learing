// $Id: OvalsUspsRetrieveLocReq.java,v 1.3 2003/07/03 17:46:37 db4252 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsusps;

import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;

/**
 * This class extends RetrieveLocReq to specialize it for OVALSUSPS.
 * Creation date: (4/3/01 1:04:38 PM)
 * @author: David Brawley
 */
public class OvalsUspsRetrieveLocReq extends RetrieveLocReq
{
	protected AddressHandlerUSPS uspsAddr = null;
	protected OvalsUspsXMLParser parser = null;	
	/**
 	* OvalsUspsRetrieveLocReq constructor.
 	* @param utility LIMBase
 	* @param address AddressHandlerUSPS
 	*/
	public OvalsUspsRetrieveLocReq(LIMBase utility, AddressHandlerUSPS address, ProviderLocationProperties[] providerLocationProperties)
		throws SystemFailure
	{
		super(utility, address, providerLocationProperties);
		setUspsAddr(address);
        parser =
            new OvalsUspsXMLParser(
                utility,
                address,
                getLocationPropertiesRequested()
                    .isRetrieveCityStatePostalCodeValid());
	}
	/**
	 * Returns the uspsAddr.
	 * @return AddressHandlerUSPS
	 */
	public AddressHandlerUSPS getUspsAddr() {
		return uspsAddr;
	}

	/**
	 * Sets the uspsAddr.
	 * @param uspsAddr The uspsAddr to set
	 */
	public void setUspsAddr(AddressHandlerUSPS uspsAddr) {
		this.uspsAddr = uspsAddr;
	}
	/**
	 * Returns the parser.
	 * @return OvalsUspsXMLParser
	 */
	public OvalsUspsXMLParser getParser() {
		return parser;
	}

}
