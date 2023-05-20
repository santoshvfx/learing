// $Id: LFACSSagLu.java,v 1.3 2008/02/29 23:27:20 jd3462 Exp $
package com.sbc.eia.bis.lim.database.queries;

import com.sbc.eia.idl.lim_types.Address;

/**
 * This is the LFACSSagLu class which contains methods to set and 
 * get address data returned from the lfacs_sag_lu table
 * Creation date: (9/18/02 6:23:07 AM)
 * @author: David Brawley
 */
public class LFACSSagLu 
{
	protected Address address = null;
	protected String circuitId = null;
	protected String lpStatus = null;

    public LFACSSagLu() 
    {
        super();
    }
    /**
     * LFACSSagLu constructor.
     * Creation date: (9/18/02 6:36:58 AM)
     * @param aAddress com.sbc.eia.idl.lim_types.Address
     * @param aCircuitId java.lang.String
     * @param aLpStat java.lang.String
     */
    public LFACSSagLu(Address aAddress, String aCircuitId, String aLpStatus) 
    {
    	super();
    	
    	setAddress(aAddress);
    	
    	if ((aCircuitId != null) && (!aCircuitId.trim().equals("")))
    		setCircuitId(aCircuitId);
    
    	if ((aLpStatus != null) && (!aLpStatus.trim().equals("")))
    		setLpStatus(aLpStatus);	
    }
    /**
     * Getter method for address.
     * Creation date: (9/18/02 6:26:51 AM)
     * @return com.sbc.eia.idl.lim_types.Address
     */
    public Address getAddress() 
    {
    	return address;
    }
    /**
     * Getter method for circuitId.
     * Creation date: (9/18/02 6:30:23 AM)
     * @return java.lang.String
     */
    public String getCircuitId() 
    {
    	return circuitId;
    }
    /**
     * Getter method for lpStatus.
     * Creation date: (9/18/02 6:30:45 AM)
     * @return java.lang.String
     */
    public String getLpStatus() 
    {
    	return lpStatus;
    }
    /**
     * Setter method for address.
     * Creation date: (9/18/02 6:26:51 AM)
     * @param newAddress com.sbc.eia.idl.lim_types.Address
     */
    public void setAddress(Address newAddress) 
    {
    	address = newAddress;
    }
    /**
     * Setter method for circuitId.
     * Creation date: (9/18/02 6:30:23 AM)
     * @param newCircuitId java.lang.String
     */
    public void setCircuitId(String newCircuitId) 
    {
    	circuitId = newCircuitId;
    }
    /**
     * Setter method for lpStatus.
     * Creation date: (9/18/02 6:30:45 AM)
     * @param newLpStat java.lang.String
     */
    public void setLpStatus(String newLpStatus) 
    {
    	lpStatus = newLpStatus;
    }
    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString() 
    {
    	// Insert code to print the receiver here.
    	// This implementation forwards the message to super. You may replace or supplement this.
    	return super.toString();
    }
}
