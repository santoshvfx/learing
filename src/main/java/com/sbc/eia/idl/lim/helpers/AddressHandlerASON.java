// $Id: AddressHandlerASON.java,v 1.7 2007/10/16 21:42:50 gg2712 Exp $

package com.sbc.eia.idl.lim.helpers;

import java.util.ArrayList;

import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Address2;
import com.sbc.eia.idl.lim_types.AddressChoice;
/**
 * AddressHandler class for ASON.
 * Creation date: (08/09/01 10:30:41 AM)
 * @author: Rachel Zadok - Local
 */ 
public class AddressHandlerASON extends AddressHandlerAIT {
    
    /**
     * AddressHandlerASON constructor.
     */
    public AddressHandlerASON() {
    	super();
    }
    
    /**
     * AddressHandlerASON constructor.
     * Creation date: (10/10/01 7:55:41 PM)
     * @param address Address
     * @exception AddressHandlerException
     */
    public AddressHandlerASON(Address address) 
    throws AddressHandlerException
    {
    	super(address);
    }
    
    /**
	 * Gets ASON address fields and creates Address Object.
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
     */
    public AddressHandlerASON(
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
            // aCity = city;
        }

        if ((streetDirection.equals ("N*E")) || 
            (streetDirection.equals ("N*W")) || 
            (streetDirection.equals ("S*E")) || 
            (streetDirection.equals ("S*W"))) 
            setOriginalStreetDirection(streetDirection); 
            
        setStreetDirection (streetDirection);
        theAddress.aFieldedAddress().aStreetDirection.theValue(aStreetDirection);

        if ((aStreetNameSuffix.equals ("N*E")) || 
            (aStreetNameSuffix.equals ("N*W")) || 
            (aStreetNameSuffix.equals ("S*E")) || 
            (aStreetNameSuffix.equals ("S*W"))) 
            setOriginalStreetNameSuffix(streetNameSuffix); 
        
        setStreetNmSuffix (streetNameSuffix);
        theAddress.aFieldedAddress().aStreetNameSuffix.theValue(aStreetNameSuffix);
    }

    /**
     * returns HouseNumber in a format needed by ASON.
     * Creation date: (08/10/01 12:15:00 PM)
     * @return String
     */
    
    public String getHouseNumber() 
    {		
    	if (aHouseNumberPrefix != null)
    		if (!aHouseNumberPrefix.equals(""))
    		{
    			return (aHouseNumberPrefix.trim() + " " + aHouseNumber.trim());
    		}
    			
    	return aHouseNumber;
    }
    /**
     * returns StreetAddress in a format needed by ASON.
     * Creation date: (08/14/01 12:15:00 PM)
     * @param stNmSfxInd boolean
     * @return String
     */
    
    public String getStreetAddr (boolean stNmSfxInd) 
    {		
    	String stAddr = "";
    
    	if (aHouseNumberPrefix != null)
    		if (!aHouseNumberPrefix.trim().equals(""))
    			stAddr += aHouseNumberPrefix.trim() + " ";
    			
    	if (aHouseNumber != null)
    		if(!aHouseNumber.trim().equals(""))
    		{
    			stAddr += aHouseNumber.trim();
    			if (aHouseNumberSuffix != null)
    				if (!aHouseNumberSuffix.trim().equals(""))
    					if (aHouseNumberSuffix.trim().startsWith ("-"))
    						stAddr += aHouseNumberSuffix.trim();
    				else
    					stAddr = stAddr + "-" + aHouseNumberSuffix.trim();
    			stAddr += " ";
    		}
    	
    	if (aStreetDirection != null)
    		if (!aStreetDirection.equals(""))
    		{
    			stAddr += getStreetDir().trim();
    			stAddr += " ";
    		}
    
    	if (aStreetName != null)
    		if (!aStreetName.equals(""))
    		{
    			stAddr += aStreetName.trim();
    			stAddr += " ";
    		}
    			
    	if (aStreetThoroughfare != null)
    		if (!aStreetThoroughfare.trim().equals(""))
    		{
    			stAddr += aStreetThoroughfare.trim();
    			stAddr += " ";
    		}
    
    	if (aStreetNameSuffix != null)
    		if (!aStreetNameSuffix.trim().equals(""))
    		{
    			if (stNmSfxInd)
    				stAddr += getStreetNameSfx().trim();
    			else
    				stAddr += getStNameSfx().trim();
    		}
    	
    	return stAddr.trim();
    }
    /**
     * returns StreetDirection.
     * Creation date: (08/09/01 12:15:00 PM)
     * @return String
     */
    
    public String getStreetDir() 
    {
    	if (aStreetDirection.toUpperCase ().equals ("NE"))
    		return ("N*E");
    	else if (aStreetDirection.toUpperCase ().equals ("NW"))
    		return ("N*W");
    	else if (aStreetDirection.toUpperCase ().equals ("SE"))
    		return ("S*E");
    	else if (aStreetDirection.toUpperCase ().equals ("SW"))
    		return ("S*W");
    	else
    		return aStreetDirection;
    }
    /**
     * returns StreetName in a format needed by ASON.
     * Creation date: (08/09/01 12:15:00 PM)
     * @return String
     */
    
    public String getStreetName() 
    {		
    	String stNm = "";
    	boolean addBlank = false;
    
    	if (aStreetName != null)
    		if (!aStreetName.equals(""))
    		{
    			stNm += aStreetName.trim();
    			addBlank = true;
    		}
    			
    	if (aStreetThoroughfare != null)
    		if (!aStreetThoroughfare.trim().equals(""))
    		{
    			if (addBlank)
    				stNm += " ";
    			stNm += aStreetThoroughfare.trim();
    			addBlank = true;
    		}
    
    	if (aStreetNameSuffix != null)
    		if (!aStreetNameSuffix.trim().equals(""))
    		{
    			if (addBlank)
    				stNm += " ";
    			stNm += aStreetNameSuffix.trim();
    		}
    	
    	return stNm;
    }
    /**
     * returns StreetNameSuffix.
     * Creation date: (08/16/01 12:15:00 PM)
     * @return String
     */
    
    public String getStreetNameSfx() 
    {	
    	if (aStreetNameSuffix.toUpperCase ().equals ("NE"))
    		return ("N*E");
    	else if (aStreetNameSuffix.toUpperCase ().equals ("NW"))
    		return ("N*W");
    	else if (aStreetNameSuffix.toUpperCase ().equals ("SE"))
    		return ("S*E");
    	else if (aStreetNameSuffix.toUpperCase ().equals ("SW"))
    		return ("S*W");
    	else
    		return aStreetNameSuffix;
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
    
    /**
     * Gets ASON address fields, parses them and set the AddressHandler class fields. 
     * Creation date: (08/10/01 12:00:00 PM)
     * @param houseNumber String
     * @param houseNumberSuffix String
     * @param streetDirection String
     * @param streetName String
     * @param city String
     * @param state String
     * @param postalCode String
     * @param county String
     * @param houseNumberInd String
     * @throws AddressHandlerException
     */
    
    public void setASONFields (String houseNumber, String houseNumberSuffix, String streetDirection, 
    	String streetName, String city, String state, String postalCode, String county, String houseNumberInd,
        String structType, String structValue, String levelType, String levelValue, String unitType, String unitValue)
    throws AddressHandlerException
    {
    	ArrayList streetDirSufSufList = getStreetDirSufList();
    
    	// First clear all fields.
    	//
    	aRoute = "";
      	aBox = "";
      	aHouseNumberPrefix = "";
      	aHouseNumber = "";
      	aAssignedHouseNumber = "";
      	aHouseNumberSuffix = "";
      	aStreetDirection = "";
      	aStreetName = "";
      	aStreetThoroughfare = "";
      	aStreetNameSuffix = "";
      	aCity = "";
      	aState = "";
      	aPostalCode = "";
      	aCounty = "";
      	aCountry = "";
        m_structType = "";
        m_structValue = "";
        m_levelType = "";
        m_levelValue = "";
        m_unitType = "";
        m_unitValue = "";
    
    	if (houseNumberInd == null || !houseNumberInd.equals ("Y"))
    	{
    		if (houseNumber != null)
    			parseHouseNbr (houseNumber);
    		if (houseNumberSuffix != null)				
    			aHouseNumberSuffix = houseNumberSuffix;
    	}
    	else if (houseNumber != null)
    		aAssignedHouseNumber = houseNumber;

    	
        if (streetDirection != null)
    	   aStreetDirection = streetDirection;
    
    	if (streetName != null)
    		if (!streetName.equals (""))
    		{
    		try
    		{
     			String [] item = stringToTokens( streetName );
     
     			if ( item != null && item.length > 0 )
    				parseStNmStThoStSuf (item, 0, streetDirSufSufList, true);
    		
    		} // try
    		catch( Exception e )
    		{
    			// e.printStackTrace();
    			throw new AddressHandlerException (e.getMessage());
    		}
    	} // !streetName.equals ("")
    		
    	if (city != null)
    		aCity = city;
            
    	if (state != null)	
    		aState = state;
            
    	if (postalCode !=null)
    		aPostalCode = postalCode;
            
    	if (county !=null)
    		aCounty = county;
            
        if (structType != null)
            m_structType = structType;
            
         if (structValue != null)
            m_structValue = structValue;
            
        if (levelType != null)
            m_levelType = levelType;
            
        if (levelValue != null)
            m_levelValue = levelValue;
            
        if (unitType != null)
            m_unitType = unitType;
            
        if (unitValue != null)
            m_unitValue = unitValue;
            
           
            
            
    }
    /**
     * Gets ASON address fields, and set the AddressHandler class fields. 
     * Creation date: (08/31/01 12:00:00 PM)
     * @param houseNumberPrfx     a String
     * @param houseNumber String
     * @param houseNumberSuffix String
     * @param streetDirection String
     * @param streetName String
     * @param streetThoroughfare String
     * @param streetNmSuffix String
     * @param city String
     * @param state String
     * @param postalCode String
     * @param county String
     * @param houseNumberInd String
     * @throws AddressHandlerException
     */
    public void setASONFields (String houseNumberPrfx, String houseNumber, String houseNumberSuffix, String streetDirection, 
    	String streetName, String streetThoroughfare, String streetNmSuffix, String city, String state, 
    	String postalCode, String county, String houseNumberInd)
    throws AddressHandlerException
    {
    	// First clear all fields.
    	//
    	aRoute = "";
      	aBox = "";
      	aHouseNumberPrefix = "";
      	aHouseNumber = "";
      	aAssignedHouseNumber = "";
      	aHouseNumberSuffix = "";
      	aStreetDirection = "";
      	aStreetName = "";
      	aStreetThoroughfare = "";
      	aStreetNameSuffix = "";
      	aCity = "";
      	aState = "";
      	aPostalCode = "";
      	aCounty = "";
      	aCountry = "";
      	aAdditionalAddressInformation = "";
    
    	if (houseNumberInd == null || !houseNumberInd.equals ("Y"))
    	{
    		if (houseNumberPrfx != null)
    			aHouseNumberPrefix = houseNumberPrfx;
    		if (houseNumber != null)
    			aHouseNumber = houseNumber;
    		if (houseNumberSuffix != null)				
    			aHouseNumberSuffix = houseNumberSuffix;
    	}
    	else if (houseNumber != null)
    		aAssignedHouseNumber = houseNumber;
    
    	if (streetDirection != null)
    		aStreetDirection = streetDirection;
    		
    	if (streetName != null)
    		aStreetName = streetName;
    
    	if (streetThoroughfare != null)
    		aStreetThoroughfare = streetThoroughfare;
    
    	if (streetNmSuffix != null)
    		aStreetNameSuffix = streetNmSuffix;
    		
    	if (city != null)
    		aCity = city;
    	if (state != null)	
    		aState = state;
    	if (postalCode !=null)
    		aPostalCode = postalCode;
    	if (county !=null)
    		aCounty = county;
    }
}
