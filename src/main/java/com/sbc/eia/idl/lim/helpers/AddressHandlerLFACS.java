// $Id: AddressHandlerLFACS.java,v 1.4 2004/04/29 20:32:27 as5472 Exp $

package com.sbc.eia.idl.lim.helpers;

import com.sbc.eia.idl.lim_types.Address;
/**
 * AddressHandlerLFACS is derived from AddressHandler, it parses Address Object for LFACS.
 * Creation date: (5/3/01 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */
public class AddressHandlerLFACS extends AddressHandler 
{
    /**
     * AddressHandlerLFACS constructor.
     */
    
     public AddressHandlerLFACS() {
    	super();
    }
    
    /**
     * AddressHandlerLFACS constructor.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param address Address
     * @exception AddressHandlerException
     */
    
    public AddressHandlerLFACS(Address address) 
    throws AddressHandlerException
    {
    	super (address);
    	AHNoption = 2;
    	if (streetNmInd)
    	{
    		aStreetName = ad_sign + aStreetName.trim ();
    		theAddress.aFieldedAddress().aStreetName.theValue( aStreetName );
    	}
    }
    
    /**
     * returns StreetNumber in a format needed by AEMS.
     * Creation date: (01/23/03 12:15:44 PM)
     * @return String
     */
    
    public String getHousNbrONLY()
    {
    	String stNmbr = "";
    
    	if (aAssignedHouseNumber.trim ().startsWith ("*"))
    		return stNmbr;
    	
    	if (aHouseNumber == null || aHouseNumber.equals(""))
    		stNmbr = aAssignedHouseNumber.trim();
    	else
    		stNmbr = aHouseNumber.trim();	
    	return stNmbr;
    }
    
    /**
     * returns the first type of the living unit which is not null or empty.
     * Creation date: (21/01/02 12:15:44 PM)
     * @return String
     */
    
    public String getLivingUnitDescription () 
    {
    	if (m_structType != null && !m_structType.equals (""))
    		return m_structType;
    	else if (m_levelType !=null && !m_levelType.equals (""))
    	{
    		return m_levelType;
    	}
    	else if (m_unitType != null && !m_unitType.equals (""))
    	{
    		return m_unitType;
    	}
    	else
    		return "";
    }
    
    /**
     * returns the first value of the living unit which is not null or empty.
     * Creation date: (21/01/02 12:15:44 PM)
     * @return String
     */
    
    public String getLivingUnitValue () 
    {
    	if (m_structValue != null && !m_structValue.equals(""))
    		return (m_structValue);
    	else if (m_levelValue != null && !m_levelValue.equals(""))
    	{
    		return (m_levelValue);
    	}
    	else if (m_unitValue != null && !m_unitValue.equals(""))
    	{
    		return (m_unitValue);
    	}
    	else
    		return "";
    }

}
