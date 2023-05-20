// $Id: OvalsUspsRetrieveLocReq.java,v 1.1 2007/09/25 19:54:30 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;

/**
 * This class extends RetrieveLocReq to specialize it for OVALSUSPS.
 * Creation date: (4/3/01 1:04:38 PM)
 * @author: David Brawley
 */
public class OvalsUspsRetrieveLocReq
{
    protected LIMBase utility = null;
	protected AddressHandlerUSPS uspsAddr = null;
	protected OvalsUspsXMLParser parser = null;
	protected boolean isCSZ = false;
	
	/**
 	* OvalsUspsRetrieveLocReq constructor.
 	* @param utility LIMBase
 	* @param address AddressHandlerUSPS
 	*/
	public OvalsUspsRetrieveLocReq(LIMBase utility, AddressHandlerUSPS address, boolean m_isCSZ)
		throws SystemFailure
	{
		setUtility(utility);
		setUspsAddr(address);
		setIsCSZ(m_isCSZ);
        parser =
            new OvalsUspsXMLParser(
                utility,
                address,
                getIsCSZ());
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

	/**
     * Log Address Request.
     * Creation date: (3/28/01 4:29:37 PM)
     * @return AddressHandler
     * @see #setAddr(AddressHandler)
     */
    public void logAddressReq()
    {
        getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForPostalAddress: \n" + toString());
    }
	
	/**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
    	return getUspsAddr().toString();
    }
    
    /**
     * Setter method for Utility.
     * Creation date: (3/1/01 5:39:48 PM)
     * @param newUtility LIMBase
     * @see #getUtility
     */
    public void setUtility(LIMBase newUtility)
    {
    	utility = newUtility;
    }
    
    /**
     * Getter method for Utility.
     * Creation date: (3/1/01 5:39:48 PM)
     * @return LIMBase
     * @see #setUtility(LIMBase)
     */
    public LIMBase getUtility()
    {
    	return utility;
    }
    
    /**
     * @return Returns the isCSZ.
     */
    public boolean getIsCSZ() {
        return isCSZ;
    }
    /**
     * @param isCSZ The isCSZ to set.
     */
    public void setIsCSZ(boolean iscsz) {
        this.isCSZ = iscsz;
    }
}
