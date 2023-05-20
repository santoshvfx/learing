// $Id: AddressHandlerPremis.java,v 1.6 2007/10/16 21:48:31 gg2712 Exp $

package com.sbc.eia.idl.lim.helpers;

import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Address2;
import com.sbc.eia.idl.lim_types.AddressChoice;
/**
 * AddressHandlerPremis is derived from AddressHandler, it parses Address Object for Premis.
 * Creation date: (5/3/01 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */
public class AddressHandlerPremis extends AddressHandler {
	static final long serialVersionUID = 2900419043055666474L;
	
    /**
     * AddressHandlerPremis constructor.
     */
     public AddressHandlerPremis() {
    	super();
    }
    
    /**
     * AddressHandlerPremis constructor.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param address Address
     * @exception AddressHandlerException
     */
    
    public AddressHandlerPremis(Address address) 
    throws AddressHandlerException
    {
    	super(address);
    	AHNoption = 1;
    	if (aHouseNumberSuffix != null)
    	{
    		int ij = -1;
    		ij = aHouseNumberSuffix.indexOf ("-");
    		if (ij >= 0)
    		{
    			aHouseNumberSuffix = aHouseNumberSuffix.substring (0,ij) +
    				"/" + aHouseNumberSuffix.substring (ij+1, aHouseNumberSuffix.length ());
    			theAddress.aFieldedAddress().aHouseNumberSuffix.theValue( aHouseNumberSuffix );
    		}
    	}
    }

    /**
	 * Gets Premis address fields and creates Address Object.
     * @param route
     * @param box
     * @param houseNumberPrefix
     * @param houseNumber
     * @param assignedHouseNumber
     * @param houseNumberSuffix
     * @param streetDirection
     * @param streetName
     * @param streetThoroughfare
     * @param streetNameSuffix
     * @param city
     * @param state
     * @param postalCode
     * @param county
     * @param country
     * @param unitStructType
     * @param unitStructValue
     * @param unitLevelType
     * @param unitLevelValue
     * @param unitUnitType
     * @param unitUnitValue
     * @param additionalAddressInformation
     * @param stNmInd
     */
    public AddressHandlerPremis(
        String route,
        String box,
        String houseNumberPrefix,
        String houseNumber,
        String assignedHouseNumber,
        String houseNumberSuffix,
        String streetDirection,
        String streetName,
        String streetThoroughfare,
        String streetNameSuffix,
        String city,
        String state,
        String postalCode,
        String county,
        String country,
        String unitStructType,
        String unitStructValue,
        String unitLevelType,
        String unitLevelValue,
        String unitUnitType,
        String unitUnitValue,
        String additionalAddressInformation,
        boolean stNmInd)
    {
        super(
            route,
            box,
            houseNumberPrefix,
            houseNumber,
            assignedHouseNumber,
            houseNumberSuffix,
            streetDirection,
            streetName,
            streetThoroughfare,
            streetNameSuffix,
            city,
            state,
            postalCode,
            county,
            country,
            unitStructType,
            unitStructValue,
            unitLevelType,
            unitLevelValue,
            unitUnitType,
            unitUnitValue,
            additionalAddressInformation);
        
        city = city.trim();
        if (city.startsWith ("$$$") || city.startsWith ("***"))
            city = city.substring (3,city.length ());
    
        aCity = city;
        theAddress.aFieldedAddress().aCity.theValue(aCity);
            
        if (stNmInd)
        {
            if (streetName != null)
            {
                aStreetName = ad_sign + streetName.trim ();
                theAddress.aFieldedAddress().aStreetName.theValue(aStreetName);
            }
        }
        else
        {
            aStreetName = streetName;
            theAddress.aFieldedAddress().aStreetName.theValue(aStreetName);
        }
    }
    
    /**
     * returns HouseNumberSuffix in a format needed by PREMIS.
     * Creation date: (5/30/02 10:36:57 PM)
     * @param aHouseNbrSfx String
     * @return String
     */
    
    public static String getHouseNumberSuffix(String aHouseNbrSfx) 
    {
    	if (aHouseNbrSfx != null)
    	{
    		int ij = -1;
    		ij = aHouseNbrSfx.indexOf ("-");
    		if (ij >= 0)
    		{
    			aHouseNbrSfx = aHouseNbrSfx.substring (0,ij) +
    				"/" + aHouseNbrSfx.substring (ij+1, aHouseNbrSfx.length ());
    		}
    	}
    	return aHouseNbrSfx;
    }
    /**
     * return UnFielded Address. 
     * This method was designed for RM/NAM-TNA going to Premis APP MTT transaction.
     * Creation date: (9/24/01 2:43:41 PM)
     * @return String
     */
    public String getUFAddr() 
    {
    	String ufAddr = "";
    	
    	if (m_addressLineNoLU != null)
    	{
    		if (!m_addressLineNoLU.equals (""))
    		{
    			ufAddr = m_addressLineNoLU.trim () + "//";
    		}		
    	}
    	
    	if (ufAddr.equals (""))
    	{
    		ufAddr = getStreetNumber() + " " + getStreetName().trim() + "//";
    	}
    	
    	ufAddr = ufAddr + getCity();
    
    	ufAddr += "//";
    	boolean addBlank = false;
    	if( m_structType != null )
    	{
    		ufAddr += m_structType + " " + m_structValue;
    		addBlank = true;
    	}
    
    	if ( m_levelType != null )
    	{
    		if (addBlank)
    			ufAddr +=" ";
    		ufAddr += m_levelType + " " + m_levelValue;
       		addBlank = true;
    	}
    
    	if ( m_unitType != null )
    	{
    		if (addBlank)
    			ufAddr +=" ";
    		ufAddr += m_unitType + " " + m_unitValue;
    	}
    
    	return ufAddr.trim();				
    }
    
    /**
     * returns Address2 Object.
     * Creation date: (8/21/07 12:15:44 PM)
     * @return Address2
     */ 
    public Address2 getAddress2_HitResponse()
    {
        Address2 newAddress = new Address2();
        try
        {
            if (theAddress.discriminator().value() == AddressChoice._FIELDED_ADDRESS)
            {
                newAddress.aFieldedAddress(theAddress.aFieldedAddress());
            }
        }
        catch (Exception e) {}
        
    	return newAddress;
    }
}
