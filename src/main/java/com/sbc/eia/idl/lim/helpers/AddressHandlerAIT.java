// $Id: AddressHandlerAIT.java,v 1.11 2008/04/04 21:28:36 jh9785 Exp $

package com.sbc.eia.idl.lim.helpers;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;

/**
 * An AddressHandler subclass that can handle addresses specific to AIT.
 * The differences with AddressHandler.java
 * 1. If no qualify HouseNumber on the street name, it always picks the first one as house number. Ex: Main ST => HouseNumber=Main
 * 2. HouseNumberSuffix has "-". Ex: 123-1/2 => HouseNumberSuffix = -1/2
 * 3. AIT has special alpha house number: "FIRE NUMBER", "LOT NUMBER", "FOOT OF"
 * Creation date: (08/09/01 10:30:41 AM)
 * @author: Rachel Zadok - Local
 */
public class AddressHandlerAIT extends AddressHandler {

    /**
     * AddressHandlerAIT constructor.
     */
    public AddressHandlerAIT() {
    	super();
    }
    
    /**
     * AddressHandlerASON constructor .
     * @param address Address
     * @throws AddressHandlerException
     */
    public AddressHandlerAIT(Address address) 
    throws AddressHandlerException
    {
    	theAddress = address;
    	
    	switch (address.discriminator().value())
    	{
    		case AddressChoice._UNFIELDED_ADDRESS:
    		
    			aAddressLine = "";
    			for (int ij=0; ij < theAddress.aUnfieldedAddress().aAddressLines.theValue().length; ij++) 
    			{
    				try { aAddressLine = aAddressLine + theAddress.aUnfieldedAddress().aAddressLines.theValue()[ij].trim() + " "; }
    				catch (org.omg.CORBA.BAD_OPERATION e) {}
    			}
    			aAddressLine = aAddressLine.trim ();
    			try { aCity = theAddress.aUnfieldedAddress().aCity.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aState = theAddress.aUnfieldedAddress().aState.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aPostalCode = theAddress.aUnfieldedAddress().aPostalCode.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aCounty = theAddress.aUnfieldedAddress().aCounty.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aCountry = theAddress.aUnfieldedAddress().aCountry.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aAdditionalAddressInformation = theAddress.aUnfieldedAddress().aAdditionalInfo.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}

                try { m_postalCodePlus4 = theAddress.aUnfieldedAddress().aPostalCodePlus4.theValue().trim(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_businessName = theAddress.aUnfieldedAddress().aBusinessName.theValue().trim(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_structType = theAddress.aUnfieldedAddress().aStructureType.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_structValue = theAddress.aUnfieldedAddress().aStructureValue.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_levelType = theAddress.aUnfieldedAddress().aLevelType.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_levelValue = theAddress.aUnfieldedAddress().aLevelValue.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_unitType = theAddress.aUnfieldedAddress().aUnitType.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_unitValue = theAddress.aUnfieldedAddress().aUnitValue.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
    
    			// clean the fields which may have been populated by a call to parseUFAddr from AddressHandler class.
    			//
      			aHouseNumberPrefix = "";
      			aHouseNumber = "";
      			aHouseNumberSuffix = "";
      			aStreetDirection = "";
      			aStreetName = "";
      			aStreetThoroughfare = "";
      			aStreetNameSuffix = "";

        		if (aAddressLine != null && !aAddressLine.equals (""))
        		{
        			parseUFAddrPlusLU (aAddressLine, " \t\n\r\f,;");
        			if (m_addressLineNoLU != null && !m_addressLineNoLU.equals (""))
         					parseUFAddr (m_addressLineNoLU, FieldedAddressList.getStreetDirSufList());
        		}
     			break;
     			
    		case AddressChoice._FIELDED_ADDRESS:
    			try { aRoute = theAddress.aFieldedAddress().aRoute.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aBox = theAddress.aFieldedAddress().aBox.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aHouseNumberPrefix = theAddress.aFieldedAddress().aHouseNumberPrefix.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aHouseNumber = theAddress.aFieldedAddress().aHouseNumber.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aAssignedHouseNumber = theAddress.aFieldedAddress().aAssignedHouseNumber.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aHouseNumberSuffix = theAddress.aFieldedAddress().aHouseNumberSuffix.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aStreetDirection = theAddress.aFieldedAddress().aStreetDirection.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aStreetName = theAddress.aFieldedAddress().aStreetName.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aStreetThoroughfare = theAddress.aFieldedAddress().aStreetThoroughfare.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aStreetNameSuffix = theAddress.aFieldedAddress().aStreetNameSuffix.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aCity = theAddress.aFieldedAddress().aCity.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aState = theAddress.aFieldedAddress().aState.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aPostalCode = theAddress.aFieldedAddress().aPostalCode.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aCounty = theAddress.aFieldedAddress().aCounty.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aCountry = theAddress.aFieldedAddress().aCountry.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
    			try { aAdditionalAddressInformation = theAddress.aFieldedAddress().aAdditionalInfo.theValue().trim(); } 
    			catch (org.omg.CORBA.BAD_OPERATION e) {}
                
                try { m_originalStreetDirection = theAddress.aFieldedAddress().aOriginalStreetDirection.theValue().trim(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_originalStreetNameSuffix = theAddress.aFieldedAddress().aOriginalStreetNameSuffix.theValue().trim(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_postalCodePlus4 = theAddress.aFieldedAddress().aPostalCodePlus4.theValue().trim(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_businessName = theAddress.aFieldedAddress().aBusinessName.theValue().trim(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_cassAddressLines = theAddress.aFieldedAddress().aCassAddressLines.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_auxiliaryAddressLines = theAddress.aFieldedAddress().aAuxiliaryAddressLines.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_cassAdditionalInfo = theAddress.aFieldedAddress().aCassAdditionalInfo.theValue().trim(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                
                try { m_structType = theAddress.aFieldedAddress().aStructureType.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_structValue = theAddress.aFieldedAddress().aStructureValue.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_levelType = theAddress.aFieldedAddress().aLevelType.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_levelValue = theAddress.aFieldedAddress().aLevelValue.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_unitType = theAddress.aFieldedAddress().aUnitType.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                try { m_unitValue = theAddress.aFieldedAddress().aUnitValue.theValue(); } 
                catch (org.omg.CORBA.BAD_OPERATION e) {}
    
    
    			if (aStreetName.startsWith (ad_sign))
    			{
    				aStreetName = aStreetName.substring (2, aStreetName.length ());
    				theAddress.aFieldedAddress().aStreetName.theValue( aStreetName );
    				streetNmInd = true;
    			}
    			break;
    
    		default:
    			throw new AddressHandlerException ("Not a valid Address Object, needs to be Fielded or Unfielded.");
    	}
    	
    
    	parseUnit (this);
    
    }

    /**
	 * AddressHandlerAIT constructor that initializes the instance
	 * variables of a new AddressHandlerAIT object by deferring to super
	 * constructor.
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
    public AddressHandlerAIT(
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
        String additionalAddressInformation)
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
    }

		
    /**
     * returns a ArrayList with a list of allowed Street Direction/Suffix Names.
     * Creation date: (5/3/01 12:20:56 PM)
     * @return ArrayList
     */
    
    public final static ArrayList getStreetDirSufList() {
    	ArrayList street_direction_suffix_arraylist = new ArrayList();
    	street_direction_suffix_arraylist.addAll( FieldedAddressList.getStreetDirSufList() );
    
    	street_direction_suffix_arraylist.add("N*E");
    	street_direction_suffix_arraylist.add("N*W");
    	street_direction_suffix_arraylist.add("S*E");
    	street_direction_suffix_arraylist.add("S*W");
    
    	return street_direction_suffix_arraylist;
    }
    /**
     * Parse house number into HouseNumberPrefix and HouseNumber..
     * Creation date: (09/18/01 12:00:00 PM)
     * @param houseNumber String
     */
    
    public void parseHouseNbr (String houseNumber)
    {
    	if (houseNumber == null)
    		return;
    		
    	int index = 0;
    	String[] item = new String[2];
    	StringTokenizer st = new StringTokenizer (houseNumber, " ");
    	
    	while (st.hasMoreTokens() && index < 2) 
    		item[index++] = st.nextToken();
    
    	if (index == 0)
    		return;
    
    	/* If there are more than 2 tokens or only one token, do not try to parse it, just return. */
    	if (st.hasMoreTokens() || index == 1)
    	{
    		aHouseNumberPrefix = "";
    		aHouseNumber = houseNumber;
    		return;
    	}
    	 	
    	/*
    	 * If the first character of the street number is N, S, E or W and is immediately followed (no space) by a 
    	 * numeric or series of numerics, and then a single space is encountered followed by N, S, E or W, 
    	 * immediately followed (no space) by one or more numerics, then there is a double grid street number
    	 */
    	if (index == 2)
    	{
    		if ((item[0].toUpperCase().charAt(0) == 'N' || item[0].toUpperCase().charAt(0) == 'S' || 
    			item[0].toUpperCase().charAt(0) == 'E' || item[0].toUpperCase().charAt(0) == 'W') &&
    			(item[1].toUpperCase().charAt(0) == 'N' || item[1].toUpperCase().charAt(0) == 'S' ||
    			item[1].toUpperCase().charAt(0) == 'E' || item[1].toUpperCase().charAt(0) == 'W'))
    		{
    			boolean numberException = true;
     			for (int i = 1; i < item[0].length(); i++)
     			{
     				if (!Character.isDigit (item[0].charAt(i)))
     				{
    	 				numberException = false;
    	 				break;
     				}
     			}
     			if (numberException)
     			{
     				for (int j = 1; j < item[1].length(); j++)
     				{
     					if (!Character.isDigit (item[1].charAt(j)))
     					{
    	 					numberException = false;
    	 					break;
     					}
     				}
     			}
     			if (numberException)
    			{
    				aHouseNumberPrefix = item[0];
    				aHouseNumber = item[1];
    				return;
    			}
    		}
    	} // end if (index == 2)
    
    	aHouseNumberPrefix = "";
    	aHouseNumber = houseNumber;
    	return;
    }
    /**
     * Parse an unfielded address into fielded address.
     * Creation date: (07/27/01 12:00:00 PM)
     * @param ufAddr String
     * @param streetDirSuffixList ArrayList
     * @exception AddressHandlerException
     */
    
    protected void parseUFAddr (String ufAddr, ArrayList streetDirSuffixList)
    throws AddressHandlerException
    {
    	try
    	{		
    		String[] item = stringToTokens( ufAddr );
    
    		if ( item == null )
    			return;
    			
    	 	int index = 0;
    		int dashIndex = -1;
    		boolean numberException = false;
    
    		/*
    		 * The first token in the street address is HouseNumber except the following 3 exceptions:
    		 * 1. "FIRE NUMBER", 2. "LOT NUMBER" 3. "FOOT OF"
    		 */

    		if (item.length > 1)
    		{
    			if ((item[0].equals ("FIRE") &&  item[1].equals ("NUMBER")) || 
    				(item[0].equals ("LOT") &&  item[1].equals ("NUMBER")) || 
    				(item[0].equals ("FOOT") &&  item[1].equals ("OF")))
    			{
    				aHouseNumber = item[0] + " " + item[1];
    				index = 2;
    				numberException = true;
    			}
    		}
    
    		/* 
    		 * If the first character of the street number is N, S, E or W and is immediately followed (no space) by a 
    		 * numeric or series of numerics, and then a single space is encountered followed by N, S, E or W, 
    		 * immediately followed (no space) by one or more numerics, then there is a double grid street number
    		 */
    		 
    		if (item.length > 1 && index == 0)
    		{
    			
    		// Get the value of item 0  & item 1's charAt 0 before compare
    		char direction1 = (char) item[0].toUpperCase().charAt(0);
    		char direction2 = (char) item[1].toUpperCase().charAt(0);
    		
    			// Compare the values of direction to N, S, E & W
    			if (direction1 == 'N' || direction1 == 'S' || direction1 == 'E' || direction1 == 'W')
    			{
    				// Compare the values of direction to N, S, E & W and if item 1 length > 2 it's aHouseNumber (Fix for Bug 6919)
    				if ((item[1].length() > 2) && (direction2 == 'N' || direction2 == 'S' || direction2 == 'E' || direction2 == 'W')) 
    				{
	    				numberException = true;
	    				
	    	 			for (int i = 1; i < item[0].length(); i++)
	    	 			{
	    	 				if (!Character.isDigit (item[0].charAt(i)))
	    	 				{
	    		 				numberException = false;
	    		 				break;
	    	 				} 
	    	 			} // End of for loop statement
	    	 			
	    	 			if (numberException)
	    	 			{
	    	 				for (int j = 1; j < item[1].length(); j++)
	    	 				{
	    	 					if (item[1].charAt(j) == '-')
	    	 					{
	    	 						dashIndex = j;
	    	 						break;
	    	 					} 
	    	 					else if (!Character.isDigit (item[1].charAt(j)))
	    	 					{
	    		 					numberException = false;
	    		 					break;
	    	 					} 
	    	 				} 
	    	 			} // End of if numberException
	    	 			
	    	 			if (numberException && dashIndex > -1)
	    	 			{
	    	 				aHouseNumberPrefix = item[0];
	    					aHouseNumber = item[1].substring(0,dashIndex);
	    					aHouseNumberSuffix = item[1].substring(dashIndex, item[1].length());    					
	    					index = 2;
	    					dashIndex = -1;
	    	 			} // End of if numberException && dashIndex > -1	
	    	 			
	    	 			else if (numberException)
	    				{
	    					aHouseNumberPrefix = item[0];
	    					aHouseNumber = item[1];
	    					index = 2;
	    				} // End of else if numberException
	    				
    				} // End of if statement item1 length > 2 & getChar2 = N, S, E & W
    			} // End of if statement getChar1= N, S, E & W
    		} // End of if statement item.length > 1 & index = 0
    		
    		if (index == 0)
    			dashIndex = item[index].indexOf('-');
    		
    		/*
    		 * We assume that the first token is house number. We need to find if item[0] is HouseNumber only or 
    		 * HouseNumber and HouseNumberSuffix. If item[0] have '-' it is HouseNumber only.
    		 */
    		 
    		if (dashIndex > -1)
    		{
    			aHouseNumber = item[index].substring(0,dashIndex);
    			aHouseNumberSuffix = item[index].substring(dashIndex, item[index].length());
    		 	index++;
    		}
    		
    		if (dashIndex == -1 && !numberException)
    		{
    	 		aHouseNumber = item[index];
    	 		index++;
    		}
    
    		if (index < item.length)
    			parseStDirStNmStThoStSuf (item, index, streetDirSuffixList);
    
    		setStreetDirection (aStreetDirection);
    		setStreetNmSuffix (aStreetNameSuffix);
    	} // end try
    
    	catch( Exception e )
    	{
    		// e.printStackTrace();
    		throw new AddressHandlerException (e.getMessage());
    	}
    }
    /**
     * Set StreetDirection to be standard OBF.
     * Creation date: (8/16/01 4:54:04 PM)
     * @param streetDir String
     */
    public void setStreetDirection (String streetDir) 
    {
    	if (streetDir != null)
    		if (!streetDir.equals (""))
    		{
    			if (streetDir.equals ("N*E"))
    				aStreetDirection = "NE";
    			else if (streetDir.equals ("N*W"))
    				aStreetDirection = "NW";
    			else if (streetDir.equals ("S*E"))
    				aStreetDirection = "SE";
    			else if (streetDir.equals ("S*W"))
    				aStreetDirection = "SW";
    			else
    				aStreetDirection = streetDir;
    		} 
    }
    /**
     * Set StreetNameSuffix to be standard OBF.
     * Creation date: (8/16/01 4:55:28 PM)
     * @param stNmSuffix String
     */
    public void setStreetNmSuffix (String stNmSuffix) 
    {
    	if (stNmSuffix != null)
    		if (!stNmSuffix.equals (""))
    		{
    			if (stNmSuffix.equals ("N*E"))
    				aStreetNameSuffix = "NE";
    			else if (stNmSuffix.equals ("N*W"))
    				aStreetNameSuffix = "NW";
    			else if (stNmSuffix.equals ("S*E"))
    				aStreetNameSuffix = "SE";
    			else if (stNmSuffix.equals ("S*W"))
    				aStreetNameSuffix = "SW";
    			else
    				aStreetNameSuffix = stNmSuffix;
    		} 
    }
    
}
