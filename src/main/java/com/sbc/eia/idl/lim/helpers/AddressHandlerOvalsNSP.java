package com.sbc.eia.idl.lim.helpers;

/**
 * @author gg2712
 */
import java.util.StringTokenizer;

import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;

/** Description
 *  This file contains the AddressHandlerNSP class which formats addresses
 *  for use with the OvalsNsp validation system.
 *  Description
 */

public class AddressHandlerOvalsNSP extends AddressHandler
{
	transient protected String m_addressId = "";
  
    /**
     * AddressHandlerNSP constructor.
     */
    
    public AddressHandlerOvalsNSP() {
    	super();
    }

    /**
     * AddressHandlerOvalsNSP constructor.
     * @param address Address
     * @exception AddressHandlerException
     */
    public AddressHandlerOvalsNSP (Address address) 
    throws AddressHandlerException
    {
    	super (address);
    	
		// The house number may be of type N9999 W9999-1/2 normally used in WI state.
		// Check and try to parse this from the streetname
		
		// Added if statement for PR21492200 Don't parse StreetDirection out of the
		// StreetName when input is fieldedAddress
		if (!isFielded())
		{
			parseHouseNumber();
		}
   	} 
    	
    /**
     * Constructor for AddressHandlerOvalsNSP.
     * @param houseNumber
     * @param streetDirection
     * @param streetName
     * @param streetThoroughfare
     * @param streetNameSuffix
     * @param city
     * @param state
     */
    public AddressHandlerOvalsNSP(
        String houseNumber,
        String streetDirection,
        String streetName,
        String streetThoroughfare,
        String streetNameSuffix,
        String city,
        String state,
        String postalCode,
        String StructType,
        String StructValue,
        String LevelType,
        String LevelValue,
        String UnitType,
        String UnitValue)
    {
        super(
            null,
            null,
            null,
            houseNumber,
            null,
            null,
            streetDirection,
            streetName,
            streetThoroughfare,
            streetNameSuffix,
            city,
            state,
            postalCode,
            null,
            null,
            null,
            StructType,
            StructValue,
            LevelType,
            LevelValue,
            UnitType,
            UnitValue,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null);        	
    }   	

	public static void main(String[] args)
	{
		//parseHouseNumber();
	}
	
	/**
	 * Parses the houseNumber from the streetname
	 * The super class AddressHandler will not correctly parse WI double grid house numbers. Instead, these  
	 * become part of the street name. This method will try to parse double grid house numbers from
	 * the StreetName. See also AddressHandlerAIT for more info.
	 * 
	 * Example: 
	 * 
	 * Addressline: W1234 N5678-1/2 Longmeadow Pl
	 * 
	 * After calling the super class AddressHandler(aAddress) constructor,
	 * - HouseNumber=""
	 * - Streetname="W1234 N5678-1/2 Longmeadow"
	 * 
	 * After calling this method:
	 * - HouseNumberPrefix="W1234"
	 * - HouseNumber="N1234"
	 * - HousenumberSuffix="1/2"
	 * - StreetName=Longmeadow"
	 * 
	 * This will also parse the HouseNumberPrefix from the StreetName if present.
	 * 
	 * Example:
	 * 
	 * AddressLine: 1234 1/2 Longmeadow Pl
	 * 
	 * After calling the super class AddressHandler(aAddress) constructor,
	 * - HouseNumber="1234"
	 * - StreetName="1/2 Longmeadow"
	 * 
	 * After calling this method:
	 * - HouseNumber="1234"
	 * - HousenumberSuffix="1/2"
	 * - StreetName=Longmeadow"
	 * 
	 * @return void
	 */
	private void parseHouseNumber()
	{
		int lastItemProcessed = -1;
   		String[] item = getTokens(aStreetName, " -");
		
		if(item.length > 0)
		{
			char direction = (char) item[0].toUpperCase().charAt(0);
			
			if((direction == 'N' || direction == 'S' || direction == 'E' || direction == 'W')
				&&  isNumeric(item[0].substring(1))) 
			{
				// token 0 is a valid street number of type [N,S,E,W]9999 e.g. 3308, W1234
				
				if(item.length > 1)
				{
					direction = (char) item[1].toUpperCase().charAt(0);
					
					if((direction == 'N' || direction == 'S' || direction == 'E' || direction == 'W')
						&& isNumeric(item[1].substring(1)))
					{
						// token 1 is a valid street number of type [N,S,E,W]9999 e.g. W1234
						
						setHouseNumberPrefix(item[0]);
						setHouseNumber(item[1]);
						lastItemProcessed = 1;
						
						if(item.length > 2 && isHouseNumberSuffix(item[2]))
						{
							// token 2 is a streetNumberSuffix of type 99/99 e.g. 1/2, 1/3
							/////////////////////////////////////////////////////////////
			
							setHouseNumberSuffix(item[2]);
							lastItemProcessed = 2;
						}
					}
					else if(isHouseNumberSuffix(item[1]))
					{
						// token 1 is a streetNumberSuffix of type 99/99 e.g. 1/2, 1/3

						setHouseNumber(item[0]);
						setHouseNumberSuffix(item[1]);
						lastItemProcessed = 1;
					}
					else
					{
						setHouseNumber(item[0]);
						lastItemProcessed = 0;
					}	
				}
			}
			else if(isHouseNumberSuffix(item[0]))
			{
				// token 0 is a streetNumberSuffix of type 99/99 e.g. 1/2, 1/3
				setHouseNumberSuffix(item[0]);
				lastItemProcessed = 0;
			}
			
			StringBuffer sb = new StringBuffer();
			
			for(int i=lastItemProcessed+1; i < item.length; i++)
			{
				// First time through this loop,
				// check if StreetDirection
				
				if(i == (lastItemProcessed + 1) &&
					FieldedAddressList.getStreetDirSufList().contains(item[i].toUpperCase()))
				{
					setStreetDirection(item[i]);
				}
				else
				{
					sb.append(item[i]);
					sb.append(" ");
				}
			}
			
			setStreetName(sb.toString().trim());
		}
	}
	 
	/**
	 * Check if a String token is houseNumberPrefix of type 99/99, e.g. 1/2
	 * @parm s String
	 * @return boolean
	 */ 
	private static boolean isHouseNumberSuffix(String s)
	{
		boolean blnHseNbrSfx = false;
		String[] item = getTokens(s, "/");
		if(item.length > 1)
		{
			blnHseNbrSfx = isNumeric(item[0]) && isNumeric(item[1]);
		}
		return blnHseNbrSfx;
	}
	
	/**
	 * Check if a String is numeric
	 * @parm str String 
	 * @return boolean
	 */ 
	private static boolean isNumeric(String str)
	{
		if (str == null || str.length() == 0)
		{
			return false;
		}
		
		boolean blnNumeric = true;
		char chr[] = null;

		if(str != null)
		{
			chr = str.toCharArray();
		}
		
		for(int i=0; i < chr.length; i++)
		{
			if(chr[i] >= '0' && chr[i] <= '9')
			{
				continue;
			}
			else
			{
				blnNumeric = false;
				break;
			}
		}
		return blnNumeric;
	}

	/**
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
			//System.out.println(item[counter]);
			counter++;
		}	
		
		return item;	
	}
	 
	/**
	 * Get the StreetName. Overrides the super.getStreetname which formats street name 
	 * for LFACS and PREMIS-APP MTT 
	 * @return void
	 */
	public String getStreetName()
	{
		return trimString(aStreetName);
	}
}