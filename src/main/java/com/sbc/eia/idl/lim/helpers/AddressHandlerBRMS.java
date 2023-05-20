// $Id: AddressHandlerBRMS.java,v 1.12 2007/10/16 21:42:50 gg2712 Exp $

package com.sbc.eia.idl.lim.helpers;

import java.util.ArrayList;

import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;
import com.sbc.eia.idl.lim_types.Address2;
/**
 * AddressHandler class for BRMS.
 * Creation date: (02/07/02 10:30:41 AM)
 * @author: David Brawley
 */ 
public class AddressHandlerBRMS extends AddressHandler 
{    
	public static final String ROUTE = "Route";
    
    /**
     * AddressHandlerBRMS constructor.
     */
    public AddressHandlerBRMS() 
    {
    	super();
    }
    
    /**
     * AddressHandlerBRMS constructor.
     * Creation date: (10/10/01 7:55:41 PM)
     * @param ah AddressHandler
     * @exception AddressHandlerException
     */
    public AddressHandlerBRMS (AddressHandler ah) 
    throws AddressHandlerException
    {
    	super (ah.getAddress());
    	this.streetNmInd = ah.streetNmInd;
    	this.cityNmInd = ah.cityNmInd; 
    }
    
    /**
     * AddressHandlerBRMS constructor.
     * Creation date: (10/10/01 7:55:41 PM)
     * @param address Address
     * @exception AddressHandlerException
     */
    public AddressHandlerBRMS (Address address) 
    throws AddressHandlerException
    {
    	super (address);
    }
    
    /**
     * Gets BRMS address fields and creates Address Object.
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
    public AddressHandlerBRMS (String route, String box, String houseNumberPrefix, String houseNumber, String assignedHouseNumber,
    	String houseNumberSuffix, String streetDirection, String streetName, String streetThoroughfare, String streetNameSuffix,
    	String city, String state, String postalCode, String county, String country,
        String unitStructType, String unitStructValue, String unitLevelType, 
        String unitLevelValue, String unitUnitType, String unitUnitValue,
    	String additionalAddressInformation, boolean stNmInd)
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
    			theAddress.aFieldedAddress().aStreetName.theValue(streetName);
    		}
    	}
    	else
    	{
    		aStreetName = streetName;
    		theAddress.aFieldedAddress().aStreetName.theValue(streetName);
    	}
    }
    
    /**
     * Returns HouseNumber in a format needed by BRMS.
     * Creation date: (08/09/01 12:15:00 PM)
     * @return String
     */
    
    public String getBrmsHouseNumber () 
    {		
    	if (aHouseNumberSuffix != null)
    		if (!aHouseNumberSuffix.trim().equals(""))
    		{
    			if (aHouseNumberSuffix.trim().startsWith ("-"))
    				return (aHouseNumber + aHouseNumberSuffix);
    			else
    				return (aHouseNumber + "-" + aHouseNumberSuffix);
    		}
    	return aHouseNumber;
    }
    
    /**
     * Returns StreetName in a format needed by BRMS.
     * Creation date: (08/09/01 12:15:00 PM)
     * @return String
     */
    
    public String getBrmsStreetRoute () 
    {		    		
    	String stNm = "";
    	boolean addBlank = false;

		if ((aRoute == null || aRoute.equals ("")) && (aStreetName == null || aStreetName.equals ("")))
			return "";
			
    	if (aRoute != null)
    		if (!aRoute.trim().equals(""))
    		{
    			stNm += ROUTE + " " + aRoute.trim();
    			addBlank = true;
    		}
    
    	if (aStreetDirection != null)
    		if (!aStreetDirection.trim().equals(""))
    		{
    			stNm += aStreetDirection.trim();
    			addBlank = true;
    		}
    
    	if (aStreetName != null)
    		if (!aStreetName.trim().equals(""))
    		{
    			if (addBlank)
    				stNm += " ";
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
    			addBlank = true;
    		}
    		
    	return stNm.trim();
    }
    
    public String getStreetNmDir() 
    {		
    	String stNm = "";
    	boolean addBlank = false;
    	
    	if (aRoute != null)
    		if (!aRoute.trim().equals(""))
    		{
    			stNm += ROUTE + " " + aRoute.trim();
    			addBlank = true;
    		}
    
    	if (aStreetDirection != null)
    		if (!aStreetDirection.trim().equals(""))
    		{
    			stNm += aStreetDirection.trim();
    			addBlank = true;
    		}
    
    	if (aStreetName != null)
    		if (!aStreetName.trim().equals(""))
    		{
    			if (addBlank)
    				stNm += " ";
    			stNm += aStreetName.trim();
    			addBlank = true;
    		}
    			
    	return stNm.trim();
    }    
    
    /**
     * Parse house number into HouseNumber and HouseNumberSuffix.
     * Creation date: (09/18/01 12:00:00 PM)
     * @param houseNbr String
     */
    
    private void parseHouseNbr (String houseNbr)
    {
    	
    	if (houseNbr == null || houseNbr.length() == 0){
    		aHouseNumberPrefix = "";
    		aHouseNumber = "";
    		aHouseNumberSuffix = "";
    		return;
    	}
    
    	boolean suffixNum = false;		
    	int dashIndex = houseNbr.indexOf('-');
    		
    	// We need to find if housNbr is HouseNumber only or HouseNumber and HouseNumberSuffix.
    	// If houseNbr does not have '-' it is HouseNumber only. If the characters after the '-'
    	// are only digits it is HouseNumber only. Else it is HouseNumber and HouseNumberSuffix.
    	//
    	if ((dashIndex > 0)	&& 
    		(dashIndex < houseNbr.length())	&& 
    		(Character.isDigit (houseNbr.charAt(0))))
    	{
     		for (int i = dashIndex+1; i < houseNbr.length(); i++)
    		{
    			if (!Character.isDigit (houseNbr.charAt(i)))
    			{
    	 			suffixNum = true;
    	 			break;
    			}
     		}
     		if (suffixNum)
     		{
    			aHouseNumberPrefix = "";
    			aHouseNumber = houseNbr.substring(0,dashIndex);
    			aHouseNumberSuffix = houseNbr.substring(dashIndex+1);
    			return;
    		}
    	}
    	
    	aHouseNumberPrefix = "";
    	aHouseNumber = houseNbr;
    	aHouseNumberSuffix = "";
    	return;
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
     * Gets BRMS address fields, parses them and set the AddressHandler class fields. 
     * Creation date: (08/10/01 12:00:00 PM)
     * @param streetName String
     * @param city String
     * @param state String
     * @param postalCode String
     * @exception AddressHandlerException
     */
    
    public void setBRMSFields (String streetName, String city, String state, String postalCode)
    throws AddressHandlerException
    {
      	boolean isRoute = false;
      	// boolean streetThoroughfareFound = false;
    	// ArrayList streetThoroughfareList = FieldedAddressList.getStreetThoroughfareList();
    	// ArrayList streetDirSufSufList = FieldedAddressList.getStreetDirSufList();
    	
    	if (streetName != null && streetName.indexOf("/") > 0) 
    		streetName = streetName.substring(0,streetName.indexOf("/"));
    	parseUFAddr(streetName, FieldedAddressList.getStreetDirSufList());

    	if (city != null)
    		aCity = city;
    		
    	if (state != null)	
    		aState = state;
    		
    	if (postalCode !=null)
    		aPostalCode = postalCode;
    }
    
    /**
     * Gets BRMS address fields, parses them and set the AddressHandler class fields. 
     * Creation date: (08/10/01 12:00:00 PM)
     * @param houseNumber
     * @param streetName
     * @param city
     * @param state
     * @param postalCode
     * @param structType
     * @param structValue
     * @param levelType
     * @param levelValue
     * @param unitType
     * @param unitValue
     * @throws AddressHandlerException
     */
    public void setBRMSFields (String houseNumber, String streetName, String city, String state, String postalCode, String structType, String structValue, String levelType, String levelValue, String unitType, String unitValue)
    throws AddressHandlerException
    {
    	ArrayList streetDirSufSufList = FieldedAddressList.getStreetDirSufList();
    
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

        m_structType = "";
        m_structValue = "";
        m_levelType = "";
        m_levelValue = "";
        m_unitType = "";
        m_unitValue = "";
        
      	boolean isRoute = false;
    
    	if (houseNumber != null)
    		parseHouseNbr (houseNumber);

    	if (streetName != null && streetName.indexOf("/") > 0) 
    		streetName = streetName.substring(0,streetName.indexOf("/"));
    		
    	parseUFAddr(streetName, FieldedAddressList.getStreetDirSufList());
   		
    	if (city != null)
    		aCity = city;
    		
    	if (state != null)	
    		aState = state;
    		
    	if (postalCode !=null)
    		aPostalCode = postalCode;
            
        if ( structType != null )
        {
            m_structType = structType;
        }
        
        if ( structValue != null )
        {
            m_structValue = structValue;
        }
        
        if ( levelType != null )
        {
            m_levelType = levelType;
        }
        
        if ( levelValue != null )
        {
            m_levelValue = levelValue;
        }
        
        if ( unitType != null )
        {
            m_unitType = unitType;
        }
        
        if ( unitValue != null )
        {
            m_unitValue = unitValue;
        }          
    }
    
    /**
     * Gets address fields, parses them and set the AddressHandler class fields. 
     * Creation date: (08/10/01 12:00:00 PM)
     * @param addr Address
     * @exception AddressHandlerException
     */
    
    public void setBRMSFields (Address addr)
    throws AddressHandlerException
    {
    	theAddress = addr;
    	
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
        aAdditionalAddressInformation="";
        
        m_structType = "";
        m_structValue = "";
        m_levelType = "";
        m_levelValue = "";
        m_unitType = "";
        m_unitValue = "";
    
    	try { aRoute = addr.aFieldedAddress().aRoute.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aBox = addr.aFieldedAddress().aBox.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aHouseNumberPrefix = addr.aFieldedAddress().aHouseNumberPrefix.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aHouseNumber = addr.aFieldedAddress().aHouseNumber.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aAssignedHouseNumber = addr.aFieldedAddress().aAssignedHouseNumber.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aHouseNumberSuffix = addr.aFieldedAddress().aHouseNumberSuffix.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aStreetDirection = addr.aFieldedAddress().aStreetDirection.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aStreetName = addr.aFieldedAddress().aStreetName.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aStreetThoroughfare = addr.aFieldedAddress().aStreetThoroughfare.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aStreetNameSuffix = addr.aFieldedAddress().aStreetNameSuffix.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aCity = addr.aFieldedAddress().aCity.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aState = addr.aFieldedAddress().aState.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aPostalCode = addr.aFieldedAddress().aPostalCode.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aCounty = addr.aFieldedAddress().aCounty.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aCountry = addr.aFieldedAddress().aCountry.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { aAdditionalAddressInformation = addr.aFieldedAddress().aAdditionalInfo.theValue().trim(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
    	
    	try { m_structType = addr.aFieldedAddress().aStructureType.theValue(); } 
    	catch (org.omg.CORBA.BAD_OPERATION e) {}
    	catch (java.lang.NullPointerException e) {}
        
        try { m_structValue = addr.aFieldedAddress().aStructureValue.theValue(); } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
        
        try { m_levelType = addr.aFieldedAddress().aLevelType.theValue(); } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
        
        try { m_levelValue = addr.aFieldedAddress().aLevelValue.theValue(); } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
    
        try { m_unitType = addr.aFieldedAddress().aUnitType.theValue(); } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
        
        try { m_unitValue = addr.aFieldedAddress().aUnitValue.theValue(); } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
    }
    
    /**
     * Set Postal Code.
     * Creation date: (5/24/01 9:42:04 AM)
     * @param postalCode String
     */
    public void setPostalCode(String postalCode) 
    {
    	if (postalCode != null)
    	{
    		aPostalCode = postalCode;
    	}
    }
}
