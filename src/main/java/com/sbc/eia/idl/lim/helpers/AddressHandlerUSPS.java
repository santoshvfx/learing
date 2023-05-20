// $Id: AddressHandlerUSPS.java,v 1.18 2007/04/27 17:13:13 rz7367 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

/** Description
 *  This file contains the AddressHandlerUSPS class which formats addresses
 *  for use with the OvalsUsps validation system.
 *  Description
 */

package com.sbc.eia.idl.lim.helpers;

import java.util.ArrayList;

import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;

/**
 * AddressHandlerUSPS is derived from AddressHandler.
 * Creation date: (07/23/03 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */
public class AddressHandlerUSPS extends AddressHandlerOvals 
{
    transient protected String OVALSUSPSline[] =
        new String[OVALSUSPSline_len];
    transient final static int OVALSUSPSline_len = 5;
    
	private static ArrayList unitList = null;
	private static ArrayList levelList = null;
	private static ArrayList structureList = null;
    transient protected String m_attention = "";

    static { 
        unitList = new ArrayList ();
        unitList.addAll(FieldedAddressList.getUnitNameList());
    };
    
    static { 
        levelList = new ArrayList ();
        levelList.addAll(FieldedAddressList.getLevelNameList());
        levelList.add("DEPT");
    };

    static {
        structureList = new ArrayList ();
        structureList.addAll(FieldedAddressList.getStructureNameList());
    };
    
    /**
     * AddressHandlerUSPS constructor.
     */
    public AddressHandlerUSPS() {
    	super();
    }
    
    /**
     * AddressHandlerUSPS constructor.
     * Creation date: (07/23/03 12:20:56 PM)
     * @param address Address
     * @param line1 String
     * @exception AddressHandlerException
     */
    public AddressHandlerUSPS (Address address) 
    throws AddressHandlerException
    {
    	super (address);

        //set attention
        if (isFielded())
        {
            try { m_attention = theAddress.aFieldedAddress().aAttention.theValue(); } 
            catch (org.omg.CORBA.BAD_OPERATION e) {}
            catch (java.lang.NullPointerException e) {}
        }
        else
        {
            try { m_attention = theAddress.aUnfieldedAddress().aAttention.theValue(); } 
            catch (org.omg.CORBA.BAD_OPERATION e) {}
            catch (java.lang.NullPointerException e) {}
        }

        for (int lk = 0; lk < OVALSUSPSline_len; lk++)
            OVALSUSPSline [lk] = "";
    	
    	if ( !isFielded() )
    	{
            for (int ij=0; ij < address.aUnfieldedAddress().aAddressLines.theValue().length; ij++) 
            {
                try 
                { 
                    if (ij < OVALSUSPSline_len)
                        OVALSUSPSline[ij] = address.aUnfieldedAddress().aAddressLines.theValue()[ij].trim();
                }
                catch (org.omg.CORBA.BAD_OPERATION e) {}
                catch (java.lang.NullPointerException e) {}
            }

    		if ((OVALSUSPSline[4] != null && !OVALSUSPSline[4].equals("")) && (!aCity.equals("") || !aState.equals("") || !aPostalCode.equals("")))
    		{
    			// Exception - "LIM-Business Violation-01624" will be thrown when edit checks is done !
    		}
     		else if (OVALSUSPSline[4] == null || OVALSUSPSline[4].equals(""))
     		{
     			if (!aCity.equals("") && !aState.equals(""))
    				OVALSUSPSline[4] = aCity + " " + aState + "  ";
    			else if (!aCity.equals(""))
    				OVALSUSPSline[4] = aCity + "  ";
    			else if (!aState.equals(""))
    				OVALSUSPSline[4] = aState + "  ";
    			if (!aPostalCode.equals("")) {
                    
                    if ( !m_postalCodePlus4.equals("") ) {
        				OVALSUSPSline[4] += aPostalCode + "-" + m_postalCodePlus4;
                    } else {
                        OVALSUSPSline[4] += aPostalCode;
                    }
                }
                
    			OVALSUSPSline[4] = OVALSUSPSline[4].trim();
     		} 		
    	}
    	else	// FieldedAddress
    	{
    		// Populate the Attention to line 1
    		//
    		if ( getAttention() != null && !getAttention().equals(""))
    			OVALSUSPSline[0] = getAttention().trim();
    			
    		// populate the street address, line 4
    		//
    		if (!aHouseNumberPrefix.equals(""))
    			OVALSUSPSline[3] = aHouseNumberPrefix + " ";
    		if (!aHouseNumber.equals("") && !aHouseNumberSuffix.equals(""))
    			OVALSUSPSline[3] += aHouseNumber + " " + aHouseNumberSuffix + " ";
    		else if (!aHouseNumber.equals(""))
    			OVALSUSPSline[3] += aHouseNumber + " ";
    		if (!aStreetDirection.equals(""))
    			OVALSUSPSline[3] += aStreetDirection + " ";
    		if (!aStreetName.equals(""))
    			OVALSUSPSline[3] += aStreetName + " ";
    		if (!aStreetThoroughfare.equals(""))
    			OVALSUSPSline[3] += aStreetThoroughfare + " ";
    		if (!aStreetNameSuffix.equals(""))
    			OVALSUSPSline[3] += aStreetNameSuffix;
    		OVALSUSPSline[3] = OVALSUSPSline[3].trim();
    		
    		// populate the po box, line 3
    		//
    		if (!aRoute.equals(""))
    			OVALSUSPSline[2] = "RR " + aRoute + " ";
    		if (!aBox.equals(""))
    			OVALSUSPSline[2] += "BOX " + aBox; 
    		OVALSUSPSline[2] = OVALSUSPSline[2].trim();
    		
    		// populate the BusinessName, line 2
    		//
//    		if (!aAdditionalAddressInformation.equals(""))
//    			OVALSUSPSline[1] = aAdditionalAddressInformation;
//    		OVALSUSPSline[1] = OVALSUSPSline[1].trim();
            if ( getBusinessName() != null && !getBusinessName().equals(""))
                OVALSUSPSline[1] = getBusinessName().trim();
                
    		
    		// populate the city, state and postal code, line 5
    		//
    		if (!aCity.equals("") && !aState.equals(""))
    			OVALSUSPSline[4] = aCity + " " + aState + "  ";
    		else if (!aCity .equals(""))
    			OVALSUSPSline[4] = aCity + "  ";
    		else if (!aState.equals(""))
    			OVALSUSPSline[4] = aState + "  ";
    		if (!aPostalCode.equals("")) {
                if ( !m_postalCodePlus4.equals("") ) {
        			OVALSUSPSline[4] += aPostalCode + "-" + m_postalCodePlus4;
                } else {
                    OVALSUSPSline[4] += aPostalCode;
                }
            }
            
    		OVALSUSPSline[4] = OVALSUSPSline[4].trim();
    		
    		// populate the unit, line 3
    		//
    		String st = getStructType();
    		String sv = getStructValue();
    		String lt = getLevelType();
    		String lv = getLevelValue();
    		String ut = getUnitType();
    		String uv = getUnitValue();
    		
    		if (!st.equals("") && !sv.equals(""))
    			OVALSUSPSline[2] = st + " " + sv + " ";
    		if (!lt.equals("") && !lv.equals(""))
    			OVALSUSPSline[2] += lt + " " + lv + " ";
    		if (!ut.equals("") && !uv.equals(""))
    			OVALSUSPSline[2] += ut + " " + uv;
    		OVALSUSPSline[2] = OVALSUSPSline[2].trim();
    	} // end FieldedAddress
    }

    /**
     * Gets OVALSUSPS address fields and creates Address Object.
     * Creation date: (07/28/03 12:20:56 PM)
     * @param city
     * @param countyName
     * @param houseNumber
     * @param streetDirection
     * @param streetNameSuffix
     * @param state
     * @param streetName
     * @param streetType
     * @param unitNumber
     * @param unitType
     * @param postalCode
     * @param postalCodePlus4
     * @param cassAddressLines
     * @param auxiliaryAddressLines
     * @param cassAdditionalInfo
     * @throws AddressHandlerException
     */
    public AddressHandlerUSPS(
        String city,
        String countyName,
        String houseNumber,
        String streetDirection,
        String streetNameSuffix,
        String state,
        String streetName,
        String streetType,
        String unitNumber,
        String unitType,
        String postalCode,
        String postalCodePlus4,
        String[] cassAddressLines,
        String[] auxiliaryAddressLines,
        String cassAdditionalInfo)
        throws AddressHandlerException
    {
        this(
            city,
            countyName,
            houseNumber,
            streetDirection,
            streetNameSuffix,
            state,
            streetName,
            streetType,
            unitNumber,
            null,
            unitType,
            null,
            postalCode,
            postalCodePlus4,
            cassAddressLines,
            auxiliaryAddressLines,
            cassAdditionalInfo);
    }

    /**
     * Gets OVALSUSPS address fields and creates Address Object.
     * Creation date: (07/28/03 12:20:56 PM)
     * @param city
     * @param countyName
     * @param houseNumber
     * @param streetDirection
     * @param streetNameSuffix
     * @param state
     * @param streetName
     * @param streetType
     * @param unitNumber
     * @param unitNumber2
     * @param unitType
     * @param unitType2
     * @param postalCode
     * @param postalCodePlus4
     * @param cassAddressLines
     * @param auxiliaryAddressLines
     * @param cassAdditionalInfo
     * @throws AddressHandlerException
     */
    public AddressHandlerUSPS(
        String city,
        String countyName,
        String houseNumber,
        String streetDirection,
        String streetNameSuffix,
        String state,
        String streetName,
        String streetType,
        String unitNumber,
        String unitNumber2,
        String unitType,
        String unitType2,
        String postalCode,
        String postalCodePlus4,
        String[] cassAddressLines,
        String[] auxiliaryAddressLines,
        String cassAdditionalInfo)
        throws AddressHandlerException
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
            streetType,
            streetNameSuffix,
            city,
            state,
            postalCode,
            postalCodePlus4, 
            countyName,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            cassAddressLines,
            auxiliaryAddressLines,
            cassAdditionalInfo,
            null,
            null,
            null);

        boolean isUnitTypeValueThere = (unitType != null && !unitType.trim().equals(""));
        boolean isUnitNumberValueThere = (unitNumber != null && !unitNumber.trim().equals(""));
        boolean isUnitType2ValueThere = (unitType2 != null && !unitType2.trim().equals(""));
        boolean isUnitNumber2ValueThere = (unitNumber2 != null && !unitNumber2.trim().equals(""));

        String type = (unitType == null ? unitType : unitType.trim());
        String value = (unitNumber == null ? unitNumber : unitNumber.trim());
        
        String type2 = (unitType2 == null ? unitType2 : unitType2.trim());
        String value2 = (unitNumber2 == null ? unitNumber2 : unitNumber2.trim());
        
        boolean isUnitSet = false;
        boolean isLevelSet = false;
        boolean isStructSet = false;

		if(isUnitTypeValueThere)
		{
            if(structureList.contains (type))
            {
                m_structType = type;
                m_structValue = value;
                theAddress.aFieldedAddress().aStructureType.theValue(m_structType);
                theAddress.aFieldedAddress().aStructureValue.theValue(m_structValue);
                isStructSet = true;
            }
            else if (levelList.contains (type))
            {
                m_levelType = type;
                m_levelValue = value;
                theAddress.aFieldedAddress().aLevelType.theValue(m_levelType);
                theAddress.aFieldedAddress().aLevelValue.theValue(m_levelValue);
                isLevelSet = true;
            }
            else
            {
                m_unitType = type;
                m_unitValue = value;
                theAddress.aFieldedAddress().aUnitType.theValue(m_unitType);
                theAddress.aFieldedAddress().aUnitValue.theValue(m_unitValue);
                isUnitSet = true;
            }
            
            if( !isUnitType2ValueThere )
            {
                if(isUnitSet)
                {
                    m_levelType = type2;
                    m_levelValue = value2;
                    theAddress.aFieldedAddress().aLevelType.theValue(m_levelType);
                    theAddress.aFieldedAddress().aLevelValue.theValue(m_levelValue);
                }

                if(isLevelSet)
                {
                    m_structType = type2;
                    m_structValue = value2;
                    theAddress.aFieldedAddress().aStructureType.theValue(m_structType);
                    theAddress.aFieldedAddress().aStructureValue.theValue(m_structValue);
                }

                if(isStructSet)
                {
                    m_unitType = type2;
                    m_unitValue = value2;
                    theAddress.aFieldedAddress().aUnitType.theValue(m_unitType);
                    theAddress.aFieldedAddress().aUnitValue.theValue(m_unitValue);
                }

            }
        }
        
        if(isUnitType2ValueThere)
        {
            if(!isUnitTypeValueThere)
            {
                if(structureList.contains (type2))
                {
                    m_structType = type2;
                    m_structValue = value2;
                    theAddress.aFieldedAddress().aStructureType.theValue(m_structType);
                    theAddress.aFieldedAddress().aStructureValue.theValue(m_structValue);
                }
                else if (levelList.contains (type2))
                {
                    m_levelType = type2;
                    m_levelValue = value2;
                    theAddress.aFieldedAddress().aLevelType.theValue(m_levelType);
                    theAddress.aFieldedAddress().aLevelValue.theValue(m_levelValue);
                }
                else
                {
                    m_unitType = type2;
                    m_unitValue = value2;
                    theAddress.aFieldedAddress().aUnitType.theValue(m_unitType);
                    theAddress.aFieldedAddress().aUnitValue.theValue(m_unitValue);
                }
            }
            else
            {
                if(structureList.contains (type2) && !isStructSet)
                {
                    m_structType = type2;
                    m_structValue = value2;
                    theAddress.aFieldedAddress().aStructureType.theValue(m_structType);
                    theAddress.aFieldedAddress().aStructureValue.theValue(m_structValue);
                }
                else if (levelList.contains (type2) && !isLevelSet)
                {
                    m_levelType = type2;
                    m_levelValue = value2;
                    theAddress.aFieldedAddress().aLevelType.theValue(m_levelType);
                    theAddress.aFieldedAddress().aLevelValue.theValue(m_levelValue);
                }
                else
                {
                    if (isStructSet)
                    {
                        m_unitType = type2;
                        m_unitValue = value2;
                        theAddress.aFieldedAddress().aUnitType.theValue(m_unitType);
                        theAddress.aFieldedAddress().aUnitValue.theValue(m_unitValue);
                    }
                    else if (isLevelSet)
                    {
                        m_structType = type2;
                        m_structValue = value2;
                        theAddress.aFieldedAddress().aStructureType.theValue(m_structType);
                        theAddress.aFieldedAddress().aStructureValue.theValue(m_structValue);
                    }
                    else if(isUnitSet)
                    {
                        m_levelType = type2;
                        m_levelValue = value2;
                        theAddress.aFieldedAddress().aLevelType.theValue(m_levelType);
                        theAddress.aFieldedAddress().aLevelValue.theValue(m_levelValue);
                    }
                }
            }
        }
        
        if( !isUnitTypeValueThere && !isUnitType2ValueThere )
        {
            if((value != null && value.trim().length() > 0 ) || (value2 != null && value2.trim().length() > 0))
            {
                if((value != null && value.trim().length() > 0 ) && (value2 != null && value2.trim().length() > 0))
                {
                    m_unitValue = value;
                    m_levelValue = value2;
                }
                else if ((value != null && value.trim().length() > 0 ))
                {
                    m_unitValue = value;
                }
                else
                {
                    m_unitValue = value2;
                }
            }
        }
    }

    /**
     * returns line1 in a format needed by OVALSUSPS.
     * @return String
     */
    public String getLine1 () 
    {
    	if (OVALSUSPSline[0] == null)
    		return "";
    	else
    		return OVALSUSPSline[0];
    }
    
    /**
     * returns line2 in a format needed by OVALSUSPS.
     * @return String
     */
    public String getLine2 () 
    {
    	if (OVALSUSPSline[1] == null)
    		return "";
    	else
    		return OVALSUSPSline[1];
    }
    
    /**
     * returns line3 in a format needed by OVALSUSPS.
     * @return String
     */
    public String getLine3 () 
    {
    	if (OVALSUSPSline[2] == null)
    		return "";
    	else
    		return OVALSUSPSline[2];
    }
    
    /**
     * returns line4 in a format needed by OVALSUSPS.
     * @return String
     */
    public String getLine4 () 
    {
    	if (OVALSUSPSline[3] == null)
    		return "";
    	else
    		return OVALSUSPSline[3];
    }
    
    /**
     * returns line5 in a format needed by OVALSUSPS.
     * @return String
     */
    public String getLine5 () 
    {
    	if (OVALSUSPSline[4] == null)
    		return "";
    	else
    		return OVALSUSPSline[4];
    }
    
    /**
     * Overriding parseUnit to handle unit information differently.  The unit
     * information should not be truncated as is often done for other backends.
     * @see com.sbc.eia.idl.lim.helpers.AddressHandler#parseUnit(AddressHandler)
     */
    public void parseUnit(AddressHandler ah)
    {
        if(ah.m_structType != null)
        {
            ah.m_structType = ah.m_structType.trim();  
        }
        
        if(ah.m_structValue != null)
        {
            ah.m_structValue = ah.m_structValue.trim();
        }

        if(ah.m_levelType != null)
        {
            ah.m_levelType = ah.m_levelType.trim();
        }
        
        if(ah.m_levelValue != null)
        {
            ah.m_levelValue = ah.m_levelValue.trim();
        }

        if(ah.m_unitType != null)
        {
            ah.m_unitType = ah.m_unitType.trim();
        }
        
        if(ah.m_unitValue != null)
        {
            ah.m_unitValue = ah.m_unitValue.trim();
        }
    
        return;
    }

    /**
     * Method setAttention.
     * @param attention The attention to set
     */
    public void setAttention(String attention)
    {
        if (attention != null)
        {
            m_attention = attention;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aAttention.theValue( m_attention );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aAttention.theValue( m_attention );
                    break;
            }
        
        }
    }

    /**
     * Method getAttention.
     * @return String
     */
    public String getAttention()
    {
        return trimString(m_attention);
    }

}
