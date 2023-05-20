//$Id: AddressHandlerRSAG.java,v 1.8 2007/11/10 04:23:43 gg2712 Exp $

package com.sbc.eia.idl.lim.helpers;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Address2;
import com.sbc.eia.idl.lim_types.AddressChoice;

/**
 * Description
 * This file contains the AddressHandlerRSAG class which formats addresses
 * for use with the RSag validation system.
 */
public class AddressHandlerRSAG extends AddressHandler
{
    private final int HOUSENUMBER = 0;
    private final int HOUSENUMBERLOW = 1;
    private final int HOUSENUMBERHIGH = 2;
    private String aHouseNumberWithPrefixSuffix = "";
    private String aRSAGAddresLine = "";
    private String aHouseNumberLow = "";
    private String aHouseNumberPrefixLow = "";
    private String aHouseNumberSuffixLow = "";
    private String aHouseNumberHigh = "";
    private String aHouseNumberPrefixHigh = "";
    private String aHouseNumberSuffixHigh = "";
    
    private static ArrayList unitList = null;
	private static ArrayList levelList = null;
	private static ArrayList structureList = null;
	
	static 
	{ 
        unitList = new ArrayList ();
        unitList.addAll(FieldedAddressList.getUnitNameList());
        unitList.add("APARTMENT");
        unitList.add("ROOM");
        unitList.add("SUITE");
        unitList.add("UNT");
    };
    
    static 
    { 
        levelList = new ArrayList ();
        levelList.addAll(FieldedAddressList.getLevelNameList());
        levelList.add("FLOOR");
    };

    static 
    {
        structureList = new ArrayList ();
        structureList.addAll(FieldedAddressList.getStructureNameList());
        structureList.add("BUILDING");
    };
    
    /**
     * Default Constructor
     */
    public AddressHandlerRSAG() 
    {
    	super();
    }
    
    /**
     * Constructor
     * @param aAddress
     * @throws AddressHandlerException
     */
    public AddressHandlerRSAG(Address aAddress)
    	throws
    	AddressHandlerException 		
    {
        super(aAddress);
        
        if (!isFielded())
        {
            processAHN();
            processRouteBox();
        }
        
        formatHouseNumberWithPrefixSuffix();
        
        if (aAssignedHouseNumber.length() > 0)
        {
            aHouseNumberWithPrefixSuffix = "@" + aHouseNumberWithPrefixSuffix;
        }
        
        formatRSAGAddressLine();
    }
    
    /**
     * Constructor
     * @param ufAddress
     * @param city
     * @param state
     * @param postalCode
     * @throws AddressHandlerException
     */
    public AddressHandlerRSAG(String ufAddress, String city, String state, String postalCode)
            throws 
            AddressHandlerException
    {
        super(ufAddress, city, state, postalCode);
    }
    
    /**
     * Constructor
     * Used by formatting RSAG Response
     * Gets RSAG address fields and creates Address Object.
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
    public AddressHandlerRSAG(String route, String box, String houseNumberPrefix, String houseNumber, String assignedHouseNumber,
    	String houseNumberSuffix, String streetDirection, String streetName, String streetThoroughfare, String streetNameSuffix,
    	String city, String state, String postalCode, String county, String country,
        String unitStructType, String unitStructValue, String unitLevelType, 
        String unitLevelValue, String unitUnitType, String unitUnitValue,
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
    	
    	setHouseNumberWithPrefixSuffix(houseNumber);
        parseResponseHouseNbr(aHouseNumber, HOUSENUMBER);
    }
    
    /**
     * processAHN
     * Only for Unfielded Address
     * 
     * If the value "AHN" is found in the AddressLines field, 
     * then map the AHN value to the CBS AssignedHouseNumber 
     * (addresses[].basicAddr.assignedHouseNumber) field (removing the value "AHN=").   
     * For example, if the input AddressLines="AHN=123 JEFFERSON ST" is found, 
     * then map as CBS AssignedHouseNumber="123" and AddressName="JEFFERSON ST"
     */
    private void processAHN()
    {
        boolean findAssignedHouseNumber = false;
        StringBuffer tmpStreetName = new StringBuffer();
        String[] item = getTokens(aStreetName, " ");
        if (item.length > 0)
        {
            for (int i = 0; i < item.length; i++)
            {
                int AHN_Position = item[i].indexOf("AHN=");
                
                if (AHN_Position > -1)
                {
                    findAssignedHouseNumber = true;
                    if (item[i].length() > (AHN_Position + 4))
                    {
                        setAssignedHouseNumber(item[i].substring(AHN_Position + 4));
                    }
                }
                else
                {
                    tmpStreetName.append(item[i]).append(" ");
                }
            }
            if (findAssignedHouseNumber)
            {
                setStreetName(tmpStreetName.toString().trim());
            }
        }
    }
    
    /**
     * processRouteBox
     * Only for Unfielded Address
     * 
     * If value "Rural Route" or "R. R." or "R.R." or "RR" or "R R" or "Route"
     * is found in the AddressLines field, then map the value trailing the found value 
     * to the CBS RuralRoute (addresses[].basicAddr.ruralRoute).
     * If value "Box" and value "Rural Route" or "RR" or "Route", then map the value following the Box to the CBS RuralBox (addresses[].basicAddr.ruralBox).
     * For example, if the AddressLines=”RR 66 BOX 123”, then CBS RuralRoute=”66” and CBS “RuralBox=123”.
     * It must follow the pattern: ROUTE routevalue BOX boxvalue. If BOX shows before ROUTE, then treats address line as street name.
     * Only one token after ROUTE is treated as route value; however, all the tokens after BOX are treated as box value.
     * @throws AddressHandlerException
     */
    private void processRouteBox()
    	throws 
    	AddressHandlerException
    {
        boolean findRoute = false;
        boolean findBox = false;
        String routeValue = "";
        String boxValue = "";
        
        String[] item = getTokens(aStreetName, " \t\n\r\f");
        try
        {
            if (item.length > 0)
            {
                int i = 0;
                
                if (item[i].equalsIgnoreCase("Route") ||
                    item[i].equalsIgnoreCase("R.R.") ||
                    item[i].equalsIgnoreCase("RR"))
                {
                    if (item.length > i + 1)
                    {
                        routeValue = item[i + 1];
                        i = i + 2;
                        findRoute = true;
                    }
                }
                else if (item[i].equalsIgnoreCase("Rural"))
                {
                    if (item.length > i + 1 && item[i + 1].equalsIgnoreCase("Route"))
                    {
                        if (item.length > i + 2)
                        {
                            routeValue = item[i + 2];
                            i = i + 3;
                            findRoute = true;
                        }
                    }
                }
                else if (item[i].equalsIgnoreCase("R"))
                {
                    if (item.length > i + 1 && item[i + 1].equalsIgnoreCase("R"))
                    {
                        if (item.length > i + 2)
                        {
                            routeValue = item[i + 2];
                            i = i + 3;
                            findRoute = true;
                        }
                    }
                }
                else if (item[i].equalsIgnoreCase("R."))
                {
                    if (item.length > i + 1 && item[i + 1].equalsIgnoreCase("R."))
                    {
                        if (item.length > i + 2)
                        {
                            routeValue = item[i + 2];
                            i = i + 3;
                            findRoute = true;
                        }
                    }
                }
                else
                {
                    //Non-Route data
                    return;
                }
                
                
                if (item.length > i && findRoute)
                {
                    if (item[i].equalsIgnoreCase("Box"))
                    {
                        //All the tokens after BOX will be the box value
                        if (item.length > i + 1)
                        {
                            findBox = true;
                            i++;
                            StringBuffer temp = new StringBuffer();
                            while (item.length > i)
                            {
                                temp.append(item[i]).append(" ");
                                i++;
                            }
                            boxValue = temp.toString().trim();
                        }
                    }
                }
                else
                {
                    //Route and routevalue don't show before BOX
                    return;
                }
            
    
                if (findRoute && findBox)
                {
                    setRoute(routeValue);
                    setBox(boxValue);
                    setStreetName("");
                }
            } 
    	}
        catch (Exception e)
        {
            //e.printStackTrace();
    		throw new AddressHandlerException (e.getMessage());
        }
    }
    
    /**
     * getTokens
	 * Tokenize a String and load tokens into a String array
	 * @parm s String
	 * @parm delims String
	 * @return String[]
	 */
	private static String[] getTokens(String s, String delims)
	{
    	StringTokenizer tokenizer = new java.util.StringTokenizer(s, delims);
    	int tokenCount = tokenizer.countTokens();
		
   		String[] item = new String[tokenCount];
		int counter = 0;
		
    	while (tokenizer.hasMoreTokens())
    	{
			item[counter] = tokenizer.nextToken();
			counter++;
		}	
		
		return item;	
	}
	
	/**
     * formatHouseNumberWithPrefixSuffix
     * CBS Address Number = HouseNumberPrefix + " " + HouseNumber + "-" + HouseNumberSuffix
	 */
	private void formatHouseNumberWithPrefixSuffix()
    {
	    StringBuffer temp = new StringBuffer();
	    //10/18/07 Per Sue Ann for fielded address, if clients accidental send LIM HouseNumberPrefix, it needs to be included in CBS Address Number field.
	    //Normally clients should not send House Nubmer Prefix for SE region
	    if (aHouseNumberPrefix.length() > 0)
	    {
	        //HouseNumberPrefix + a space
	        temp.append(aHouseNumberPrefix).append(" ");
	    }
	    
        if (aHouseNumberSuffix.length() > 0)
        {
            if (aHouseNumberSuffix.startsWith("-"))
            {
                temp.append(aHouseNumber).append(aHouseNumberSuffix);    
            }
            else
            {
                temp.append(aHouseNumber).append("-").append(aHouseNumberSuffix);
            }
        }
        else
        {
            temp.append(aHouseNumber);
        }
        
        aHouseNumberWithPrefixSuffix = temp.toString().trim();
    }
    
	/**
     * formatRSAGAddressLine
     * RSAGAddresLine = Directional Prefix + Street Name + Thoroughfare + Directional Suffix
	 */
	private void formatRSAGAddressLine()
	{
	    StringBuffer temp = new StringBuffer();
    
	    if (aStreetDirection.length() > 0)
	        temp.append(aStreetDirection).append(" ");
	    if (aStreetName.length() > 0)
	        temp.append(aStreetName).append(" ");
	    if (aStreetThoroughfare.length() > 0)
	        temp.append(aStreetThoroughfare).append(" ");
	    if (aStreetNameSuffix.length() > 0)
	        temp.append(aStreetNameSuffix);
    
	    aRSAGAddresLine = temp.toString().trim();
	}
    
    /**
     * getAddress2_ForFieldedAddress
     * returns Address2 Object.
     * Creation date: (8/21/07 12:15:44 PM)
     * @return Address2
     */ 
    public Address2 getAddress2_ForFieldedAddress()
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
     * setRSagFields
     * Used by formatting RSAG Response
     * @parm houseNumber String
     * @parm houseNumberLow String
     * @parm houseNumberHigh String
     * @parm streetDirection String
     * @parm streetName String
     * @parm streetThoroughfare String
     * @parm streetNameSuffix String
     * @parm city String
     * @parm state String
     * @parm postalCode String
     * @parm county String
     */
     public void setRSagFields(String houseNumber,
                              String houseNumberLow,
                              String houseNumberHigh,
                              String streetDirection,
                              String streetName,
                              String streetThoroughfare,
                              String streetNameSuffix,
                              String city,
                              String state,
                              String postalCode,
                              String county)
     {
         ArrayList streetDirSufSufList = FieldedAddressList.getStreetDirSufList();
      
         // First clear all fields.
         aRoute = "";
         aBox = "";
            aHouseNumber = "";
            aHouseNumberPrefix = "";
            aHouseNumberSuffix = "";
            aAssignedHouseNumber = "";
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
            //For Ranged Address
            aHouseNumberLow = "";
         aHouseNumberPrefixLow = "";
         aHouseNumberSuffixLow = "";
         aHouseNumberHigh = "";
         aHouseNumberPrefixHigh = "";
         aHouseNumberSuffixHigh = "";
          
            boolean isRoute = false;
      
            if (houseNumber != null)
                parseResponseHouseNbr(houseNumber, HOUSENUMBER);
            
            if (houseNumberLow != null)
                parseResponseHouseNbr(houseNumberLow, HOUSENUMBERLOW);
        
            if (houseNumberHigh != null)
                parseResponseHouseNbr(houseNumberHigh, HOUSENUMBERHIGH);

            if (streetDirection != null)
                aStreetDirection = streetDirection.trim();
        
            if (streetName != null ) 
                aStreetName = streetName.trim();
            
            if (streetThoroughfare != null)
                aStreetThoroughfare = streetThoroughfare.trim();
        
            if (streetNameSuffix != null)
                aStreetNameSuffix = streetNameSuffix.trim();
        
            if (city != null)
                aCity = city.trim();
            
            if (state != null)  
                aState = state.trim();
            
            if (postalCode !=null)
                aPostalCode = postalCode.trim();
        
            if (county != null)
                aCounty = county.trim();
     }

     /**
     * parseResponseHouseNbr
     * Parse house number into HouseNumber and HouseNumberSuffix.
     * @parm houseNbr String
     * @parm option int
     */ 
    private void parseResponseHouseNbr(String houseNbr, int option)
    {
     	
        if (houseNbr == null || houseNbr.length() == 0)
        {
            if (option == HOUSENUMBERLOW)
            {
                aHouseNumberLow = "";
                aHouseNumberPrefixLow = "";
                aHouseNumberSuffixLow = "";
            }
            else if (option == HOUSENUMBERHIGH)
            {
                aHouseNumberHigh = "";
                aHouseNumberPrefixHigh = "";
                aHouseNumberSuffixHigh = "";
            }
            else
            {
                aHouseNumber = "";
                aHouseNumberPrefix = "";
                aHouseNumberSuffix = "";
            }
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
                if (option == HOUSENUMBERLOW)
                {
                    aHouseNumberPrefixLow = "";
                    aHouseNumberLow = houseNbr.substring(0,dashIndex);
                    aHouseNumberSuffixLow = houseNbr.substring(dashIndex+1);
                }
                else if (option == HOUSENUMBERHIGH)
                {
                    aHouseNumberPrefixHigh = "";
                    aHouseNumberHigh = houseNbr.substring(0,dashIndex);
                    aHouseNumberSuffixHigh = houseNbr.substring(dashIndex+1);
                }
                else
                {
                    aHouseNumberPrefix = "";
                    aHouseNumber = houseNbr.substring(0,dashIndex);
                    aHouseNumberSuffix = houseNbr.substring(dashIndex+1);
                }
     			
                return;
            }
        }
     	
        if (option == HOUSENUMBERLOW)
        {
            aHouseNumberPrefixLow = "";
            aHouseNumberLow = houseNbr;
            aHouseNumberSuffixLow = "";
        }
        else if (option == HOUSENUMBERHIGH)
        {
            aHouseNumberPrefixHigh = "";
            aHouseNumberHigh = houseNbr;
            aHouseNumberSuffixHigh = "";
        }
        else
        {
            aHouseNumberPrefix = "";
            aHouseNumber = houseNbr;
            aHouseNumberSuffix = "";
        }
     	
        return;
    }
    
    /**
     * getHouseNumberWithPrefixSuffix
     * @return Returns the aHouseNumberWithPrefixSuffix.
     */
    public String getHouseNumberWithPrefixSuffix() 
    {
        return aHouseNumberWithPrefixSuffix;
    }
    
    /**
     * getRSAGAddresLine
     * @return Returns the aRSAGAddresLine.
     */
    public String getRSAGAddresLine() 
    {
        return aRSAGAddresLine;
    }
    
    /**
     * getHouseNumberHigh
     * @return Returns the aHouseNumberHigh.
     */
    public String getHouseNumberHigh()
    {
        return aHouseNumberHigh;
    }
    
    /**
     * getHouseNumberLow
     * @return Returns the aHouseNumberLow.
     */
    public String getHouseNumberLow()
    {
        return aHouseNumberLow;
    }
    
    /**
     * getHouseNumberPrefixHigh
     * @return Returns the aHouseNumberPrefixHigh.
     */
    public String getHouseNumberPrefixHigh()
    {
        return aHouseNumberPrefixHigh;
    }
    
    /**
     * getHouseNumberPrefixLow
     * @return Returns the aHouseNumberPrefixLow.
     */
    public String getHouseNumberPrefixLow()
    {
        return aHouseNumberPrefixLow;
    }
    
    /**
     * getHouseNumberSuffixHigh
     * @return Returns the aHouseNumberSuffixHigh.
     */
    public String getHouseNumberSuffixHigh()
    {
        return aHouseNumberSuffixHigh;
    }
    
    /**
     * getHouseNumberSuffixLow
     * @return Returns the aHouseNumberSuffixLow.
     */
    public String getHouseNumberSuffixLow()
    {
        return aHouseNumberSuffixLow;
    }
    
    /**
     * parseUFAddrPlusLU
     * Due to adding more supplemental types, this same method name is for masking 
     * the parseUFAddrPlusLU() in AddressHandler.java
     * @param addressLine String
     * @param delimList String
     * @exception AddressHandlerException
     */
	public void parseUFAddrPlusLU(String addressLine, String delimList)
    throws AddressHandlerException
	{
    	StringTokenizer tmp = new java.util.StringTokenizer(addressLine.trim(), delimList);
    	String addressToken[] = null;
    	String addressTokenUpperCase[] = null;
    	StringBuffer tempValue = new StringBuffer();
    	boolean findSupplementalTypeKeyword = false;
    	boolean findThoroughfare = false;
    	boolean findSupplementalLocation = false;
    	int ind_Thoroughfare = 0;
    	int index = 0;
    	
    	if (tmp.countTokens() == 0) return;
    	
    	addressToken = new String[tmp.countTokens()];
    	addressTokenUpperCase = new String[tmp.countTokens()];
    	try
    	{
    		while (tmp.hasMoreTokens())
    		{
    			//Load everything into a String Array
    			//Retain the original character case before passing to downstream function
    			addressToken[index] = tmp.nextToken().trim();
    		
    			//Create an image array with all upper case
    			//It can reduce the toUpperCase() function call when it needs to be checked with each individual list.
    			addressTokenUpperCase[index] = addressToken[index].toUpperCase();
    			
    			if (unitList.contains(addressTokenUpperCase[index]) ||
    				levelList.contains(addressTokenUpperCase[index]) ||
    				structureList.contains(addressTokenUpperCase[index]))
    			{
    				findSupplementalTypeKeyword = true;
    			}
    		
    			index++;
    		}
    		
    		if (!findSupplementalTypeKeyword)
    		{
    			m_addressLineNoLU = addressLine;
    			return;
    		}
    		
    		for (int i = index - 1; i > -1; i--)
    		{
    			if (FieldedAddressList.getStreetThoroughfareList().contains(addressTokenUpperCase[i]))
    			{
    				if ((i == index - 1) ||
    					(i == index - 2 && FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[index - 1])))
    				{
    					//(Last token is thoroughfare) or (i-2 is thorough and i-1 is street direction suffix) 
    					m_addressLineNoLU = addressLine;
    					return;
    				}
    				else
    				{
    					if (Character.isDigit(addressToken[0].charAt(0)))
    					{	
    						if (FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[i + 1]))
    						{
    							if (i < index - 3 && i > 1)
    							{
    								if (unitList.contains(addressTokenUpperCase[i + 2]) ||
    							  		levelList.contains(addressTokenUpperCase[i + 2]) ||
    							  		structureList.contains(addressTokenUpperCase[i + 2]))
    								{
    									findThoroughfare = true;
    									ind_Thoroughfare = i + 1;
    									break;
    								}
    							}
    						}
    						else
    						{
    							if (i < index - 2 && i > 1)
    							{
    								if (unitList.contains(addressTokenUpperCase[i + 1]) ||
    							  		levelList.contains(addressTokenUpperCase[i + 1]) ||
    							  		structureList.contains(addressTokenUpperCase[i + 1]))
    								{
    									findThoroughfare = true;
    									ind_Thoroughfare = i;
    									break;
    								}
    							}
    						}    						
    					}
    					else
    					{
    						if ((i + 1 < index) && FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[i + 1]))
    						{
    							if (i < index - 3 && i > 0)
    							{
    								if (unitList.contains(addressTokenUpperCase[i + 2]) ||
    							  		levelList.contains(addressTokenUpperCase[i + 2]) ||
    							  		structureList.contains(addressTokenUpperCase[i + 2]))
    								{
    									findThoroughfare = true;
    									ind_Thoroughfare = i + 1;
    									break;
    								}
    							}
    						}
    						else
    						{
    							if (i < index - 2 && i > 0)
    							{
    								if (unitList.contains(addressTokenUpperCase[i + 1]) ||
    							  		levelList.contains(addressTokenUpperCase[i + 1]) ||
    							  		structureList.contains(addressTokenUpperCase[i + 1]))
    								{
    									findThoroughfare = true;
    									ind_Thoroughfare = i;
    									break;
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    		
    		//startPoint is the first position after thoroughfare. If no thoroughfare found, startPoint = 0.
    		int startPoint = (ind_Thoroughfare > 0?ind_Thoroughfare + 1:0);
    		
    		if (findThoroughfare)
    		{
    			if (startPoint < index)
    			{
    				StringBuffer sb = new StringBuffer();
    				for (int i = startPoint; i < index; i++)
    				{
    					if (unitList.contains(addressTokenUpperCase[i]))
    					{
    						findSupplementalLocation = true;
    						m_unitType = addressToken[i];
    						i++;
    						tempValue = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								tempValue.append(addressToken[i]).append(" ");
    							}
    							else
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_unitValue = tempValue.toString().trim();
    					}
    					else if (levelList.contains(addressTokenUpperCase[i]))
    					{
    						findSupplementalLocation = true;
    						m_levelType = addressToken[i];
    						i++;
    						tempValue = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								tempValue.append(addressToken[i]).append(" ");
    							}
    							else 
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_levelValue = tempValue.toString().trim();
    					}
    					else if (structureList.contains(addressTokenUpperCase[i]))
    					{
    						findSupplementalLocation = true;
    						m_structType = addressToken[i];
    						i++;
    						tempValue = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								tempValue.append(addressToken[i]).append(" ");
    							}
    							else 
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_structValue = tempValue.toString().trim();
    					}
    					else
    					{
    						//just throw away non-supplemental location data showed after Thoroughfare
    						sb.append(addressToken[i]).append(" ");
    					}
    				}
    				
    				tempValue = new StringBuffer();
    				
    				for (int i = 0; i < startPoint; i++)
    				{
    					tempValue.append(addressToken[i]).append(" ");
    				}
    				
    				if (sb.toString().trim().length() > 0)
    				{
    					tempValue.append(sb.toString().trim());
    				}
    				
    				m_addressLineNoLU = tempValue.toString().trim();
    			}
    		}
    		else
    		{
    			tempValue = new StringBuffer();
    			
    			if (Character.isDigit (addressToken[0].charAt(0)))
    			{
    				if (index > 1 && 
    					(FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[1]) ||
    					 FieldedAddressList.getStreetThoroughfareList().contains(addressTokenUpperCase[1])))
    				{
    					startPoint = 3;
    				}
    				else
    				{
    					startPoint = 2;
    				}
    			}
    			else
    			{
    				if (FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[0]) ||
    					FieldedAddressList.getStreetThoroughfareList().contains(addressTokenUpperCase[0]))
    				{
    					startPoint = 2;
    				}
    				else
    				{
    					startPoint = 1;
    				}
    			}
    			
    			for (int i = 0; i < index && i < startPoint; i++)
    			{
    				tempValue.append(addressToken[i]).append(" ");
    			}
    			
    			for (int i = startPoint; i < index; i++)
    			{
    				if (unitList.contains(addressTokenUpperCase[i]))
    				{				
    					if (findSupplementalLocation)
    					{
    						m_unitType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								sb.append(addressToken[i]).append(" ");
    							}
    							else
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_unitValue = sb.toString().trim();
    					}
    					else if (((i + 1) < index) &&
    							!unitList.contains (addressTokenUpperCase[i + 1]) && 
    					 		!levelList.contains(addressTokenUpperCase[i + 1]) &&
    					 		!structureList.contains(addressTokenUpperCase[i + 1]))
    					{
    						findSupplementalLocation = true;
    						m_unitType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								sb.append(addressToken[i]).append(" ");
    							}
    							else
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_unitValue = sb.toString().trim();
    					
    					}
    					else
    					{
    						tempValue.append(addressToken[i]).append(" ");
    					}
    				}
    				else if (levelList.contains(addressTokenUpperCase[i]))
    				{
    					if (findSupplementalLocation)
    					{
    						m_levelType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								sb.append(addressToken[i]).append(" ");
    							}
    							else 
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_levelValue = sb.toString().trim();
    					}
    					else if (((i + 1) < index) && 
    					 		!unitList.contains (addressTokenUpperCase[i + 1]) &&
    					 		!levelList.contains(addressTokenUpperCase[i + 1]) &&
    					 		!structureList.contains(addressTokenUpperCase[i + 1]))
    					{
    						findSupplementalLocation = true;
    						m_levelType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								sb.append(addressToken[i]).append(" ");
    							}
    							else 
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_levelValue = sb.toString().trim();
    					}
    					else
    					{
    						tempValue.append(addressToken[i]).append(" ");
    					}
    				}
    				else if (structureList.contains (addressTokenUpperCase[i]))
    				{
    					if (findSupplementalLocation)
    					{
    						m_structType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								sb.append(addressToken[i]).append(" ");
    							}
    							else 
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_structValue = sb.toString().trim();
    					}		
    					else if (((i + 1) < index) && 
    							!unitList.contains (addressTokenUpperCase[i + 1]) &&
    							!levelList.contains(addressTokenUpperCase[i + 1]) &&
    							!structureList.contains(addressTokenUpperCase[i + 1]))
    					{
    						findSupplementalLocation = true;
    						m_structType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!unitList.contains(addressTokenUpperCase[i]) &&
    								!levelList.contains(addressTokenUpperCase[i]) &&
    								!structureList.contains(addressTokenUpperCase[i]))
    							{
    								sb.append(addressToken[i]).append(" ");
    							}
    							else 
    							{
    								break;
    							}
    							i++;
    						}
    						i--;
    						m_structValue = sb.toString().trim();
    					}
    					else
    					{
    						tempValue.append(addressToken[i]).append(" ");
    					}
    					
    				}
    				else
    				{
    					//just throw away non-supplemental location data after finding supplemental location
    					if (!findSupplementalLocation)
    					{
    						tempValue.append(addressToken[i]).append(" ");
    					}
    				}	
    			}
    			
    			m_addressLineNoLU = tempValue.toString().trim(); 
    		}
    	}
    	catch ( Exception e )
    	{
    		// e.printStackTrace();
    		throw new AddressHandlerException (e.getMessage());
    	}
    	
    	if (!findSupplementalLocation)
    	{
    		//If no data massage on Supplemental Location, just pass addressLine to next function parseUFAddr()
    		//It can make sure the original non-changed addressLine flowing into downstream function.
    		m_addressLineNoLU = addressLine;
    	}  	
	}
    
    /**
     * This method compares the alternative addresses
     * @param prevRsag
     * @return
     */
    public boolean equalsAlternativeAddress(AddressHandlerRSAG prevRsag)
    {
        boolean retValue = false;
        if (prevRsag == null)
        {
            return retValue;
        }
        try
        {
            if(getRoute().equalsIgnoreCase(prevRsag.getRoute()) &&
                getBox().equalsIgnoreCase(prevRsag.getBox()) &&
                getHouseNumberWithPrefixSuffix().equalsIgnoreCase(prevRsag.getHouseNumberWithPrefixSuffix()) &&
                getAssignedHouseNumber().equalsIgnoreCase(prevRsag.getAssignedHouseNumber()) &&
                getStDir().equalsIgnoreCase(prevRsag.getStDir()) &&
                getStName().equalsIgnoreCase(prevRsag.getStName()) &&
                getStThorofare().equalsIgnoreCase(prevRsag.getStThorofare()) &&
                getStNameSfx().equalsIgnoreCase(prevRsag.getStNameSfx()) &&
                getCity().equalsIgnoreCase(prevRsag.getCity()) &&
                getState().equalsIgnoreCase(prevRsag.getState()) &&
                getPostalCode().equalsIgnoreCase(prevRsag.getPostalCode()) &&
                getCounty().equalsIgnoreCase(prevRsag.getCounty()) &&
                getStructType().equalsIgnoreCase(prevRsag.getStructType()) &&
                getStructValue().equalsIgnoreCase(prevRsag.getStructValue()) &&
                getLevelType().equalsIgnoreCase(prevRsag.getLevelType()) &&
                getLevelValue().equalsIgnoreCase(prevRsag.getLevelValue()) &&
                getUnitType().equalsIgnoreCase(prevRsag.getUnitType()) &&
                getUnitValue().equalsIgnoreCase(prevRsag.getUnitValue()) &&
                getAdditionalInfo().equalsIgnoreCase(prevRsag.getAdditionalInfo()))
            {
                retValue = true;
            }
        }
        catch (java.lang.NullPointerException e)
        {
        }
        return retValue;
    }

    /**
     * Compare the Ranged Address
     * equalsRangedAddress
     * @return boolean
     */
    public boolean equalsRangedAddress(AddressHandlerRSAG prevRSag)
    {
        boolean retValue = false;
        
        if (prevRSag == null)
        {
            return retValue;
        }
        
        try
        {
            if(getStDir().equalsIgnoreCase(prevRSag.getStDir()) &&
               getStName().equalsIgnoreCase(prevRSag.getStName()) &&
               getStThorofare().equalsIgnoreCase(prevRSag.getStThorofare()) &&
               getStNameSfx().equalsIgnoreCase(prevRSag.getStNameSfx()) &&
               getHouseNumberPrefixLow().equalsIgnoreCase(prevRSag.getHouseNumberPrefixLow()) &&
               getHouseNumberLow().equalsIgnoreCase(prevRSag.getHouseNumberLow()) &&
               getHouseNumberSuffixLow().equalsIgnoreCase(prevRSag.getHouseNumberSuffixLow()) &&
               getHouseNumberPrefixHigh().equalsIgnoreCase(prevRSag.getHouseNumberPrefixHigh()) &&
               getHouseNumberHigh().equalsIgnoreCase(prevRSag.getHouseNumberHigh()) &&
               getHouseNumberSuffixHigh().equalsIgnoreCase(prevRSag.getHouseNumberSuffixHigh()) &&
               getCity().equalsIgnoreCase(prevRSag.getCity()) &&
               getState().equalsIgnoreCase(prevRSag.getState()) &&
               getPostalCode().equalsIgnoreCase(prevRSag.getPostalCode()) &&
               getCounty().equalsIgnoreCase(prevRSag.getCounty()))
            {
                retValue = true;
            }
        }
        catch (java.lang.NullPointerException e) {}
        
        return retValue;
    }
       
    /**
     * This method compares the alternative addresses
     * @param prevRsag
     * @return
     */
    public boolean isAlternativeLU(AddressHandlerRSAG prevRsag)
    {
        boolean retValue = false;
        if (prevRsag == null)
        {
            return retValue;
        }
        try
        {
            if(getHouseNumberWithPrefixSuffix().equalsIgnoreCase(prevRsag.getHouseNumberWithPrefixSuffix()) &&
                getAssignedHouseNumber().equalsIgnoreCase(prevRsag.getAssignedHouseNumber()) &&
                getStDir().equalsIgnoreCase(prevRsag.getStDir()) &&
                getStName().equalsIgnoreCase(prevRsag.getStName()) &&
                getStThorofare().equalsIgnoreCase(prevRsag.getStThorofare()) &&
                getStNameSfx().equalsIgnoreCase(prevRsag.getStNameSfx()) &&
                getCity().equalsIgnoreCase(prevRsag.getCity()) &&
                getState().equalsIgnoreCase(prevRsag.getState()) &&
                getPostalCode().equalsIgnoreCase(prevRsag.getPostalCode()) &&
                getCounty().equalsIgnoreCase(prevRsag.getCounty()) &&
                getAdditionalInfo().equalsIgnoreCase(prevRsag.getAdditionalInfo()))
            {
                if (getStructType().equalsIgnoreCase(prevRsag.getStructType()) &&
                getStructValue().equalsIgnoreCase(prevRsag.getStructValue()) &&
                getLevelType().equalsIgnoreCase(prevRsag.getLevelType()) &&
                getLevelValue().equalsIgnoreCase(prevRsag.getLevelValue()) &&
                getUnitType().equalsIgnoreCase(prevRsag.getUnitType()) &&
                getUnitValue().equalsIgnoreCase(prevRsag.getUnitValue()))
                {
                    //this is duplicate address, shouldn't go here
                    retValue = false;
                }
                else
                {
                    retValue = true;
                }
            }
        }
        catch (java.lang.NullPointerException e)
        {
        }
        return retValue;
    }

    /**
     * @param houseNumberWithPrefixSuffix The aHouseNumberWithPrefixSuffix to set.
     */
    public void setHouseNumberWithPrefixSuffix(String houseNumberWithPrefixSuffix)
    {
        aHouseNumberWithPrefixSuffix = houseNumberWithPrefixSuffix.trim();
    }
}

