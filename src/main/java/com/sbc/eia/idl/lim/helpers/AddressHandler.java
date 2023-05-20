// $Id: AddressHandler.java,v 1.54 2008/04/04 21:23:24 jh9785 Exp $

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
 *  This file contains the AddressHandler class for parsing Addresses for
 *  different regions and hosts.
 *  Description
 */

package com.sbc.eia.idl.lim.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import com.sbc.bccs.idl.helpers.IDLUtil;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.FieldedAddress;

/**
 * A class which handles the LIM Address Object.
 * Creation date: (5/3/01 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */
 
// WARNING If you add members to AddressHandler Class add them as transient, unless there is a need
// to serialize those members.
// 
public class AddressHandler implements java.io.Serializable{
	
	/**
     * Identify the Cingular Address Type.
     * @return String
     */
    public String getCingularAddressType ()
    {
    	if ((aStreetName == null || aStreetName.equals ("")) && (aBox == null || aBox.equals ("")))
    		return null;
    	
    	if ((aState == null || aState.equals ("") || aCity == null || aCity.equals ("")) && 
    		(aPostalCode == null || aPostalCode.equals ("")))
    		return null;
    		
    	if ((aCountry != null && !aCountry.equals("")) && (!aCountry.equalsIgnoreCase ("US") && 
    		!aCountry.equalsIgnoreCase ("USA") && !aCountry.equalsIgnoreCase ("United States")))
    		return "F";
    		
    	if ((aState != null && !aState.equals ("")) && (aState.equalsIgnoreCase ("AE") || 
    		aState.equalsIgnoreCase ("AP") || aState.equalsIgnoreCase ("AA")))
    		return "M";

    	if ((aStreetName == null || aStreetName.equals ("")) && (aRoute == null || aRoute.equals ("")) && 
    		(aBox != null && !aBox.equals ("")))
    		return "P";

    	if (aStreetName != null)
    	{
    		String upperCaseSN = aStreetName.toUpperCase();
    		if (upperCaseSN.startsWith ("PO BOX"))
    		{
    			StringTokenizer st1 = new StringTokenizer (upperCaseSN, " ");
    			if (st1.countTokens() == 3)
    				return "P";
    		}
    		else if (upperCaseSN.startsWith ("P O BOX"))
    		{
    			StringTokenizer st2 = new StringTokenizer (upperCaseSN, " ");
    			if (st2.countTokens() == 4)
    				return "P";
    		}
    		if (upperCaseSN.startsWith ("P. O. BOX"))
    		{
    			StringTokenizer st3 = new StringTokenizer (upperCaseSN, " ");
    			if (st3.countTokens() == 4)
    				return "P";
    		}
    		if (upperCaseSN.startsWith ("P.O. BOX"))
    		{
    			StringTokenizer st4 = new StringTokenizer (upperCaseSN, " ");
    			if (st4.countTokens() == 3)
    				return "P";
    		}
    	}
     		
     	if (aRoute != null && !aRoute.equals ("") && aBox != null && !aBox.equals ("") && 
     		(aStreetName == null || aStreetName.equals ("")))
     		return "R";
     		
    	StringTokenizer br = new StringTokenizer (aStreetName.toUpperCase(), " ");
    	boolean hasBox = false;
    	boolean hasRoute = false;
    	while (br.hasMoreTokens())
    	{
    		String nextTok = br.nextToken();
    		if (nextTok.equals ("BOX"))
    			hasBox = true;
    		else if (nextTok.equals ("RR") || nextTok.equals ("ROUTE"))
    			hasRoute = true;
    	}
    	
     	if (aStreetName != null && hasBox && hasRoute)
     		return "R";
     	   	
    	return "S";
    } 

    
    protected static interface UnitCategory {
        int _STRUCTURE = 0;
        int _LEVEL = 1;
        int _UNIT_CATEGORY = 2;
    }
    
	class MyUnit implements java.io.Serializable
	{
		static final long serialVersionUID = -1398534162999909740L;
		int category;
  		String type = null;
  		String value = null;
	}; 
    
	static final long serialVersionUID = -215115369206098442L;
	
    transient final static String nl = System.getProperty("line.separator","\n");
    transient final static String ad_sign = "@ ";
		
	protected String aRoute = "";
  	protected String aBox = "";
  	protected String aHouseNumberPrefix = "";
  	protected String aHouseNumber = "";
  	protected String aAssignedHouseNumber = "";
  	protected String aHouseNumberSuffix = "";
  	protected String aStreetDirection = "";
  	protected String aStreetName = "";
  	protected String aStreetThoroughfare = "";
  	protected String aStreetNameSuffix = "";
  	protected String aCity = "";
  	protected String aState = "";
  	protected String aPostalCode = "";
  	protected String aCounty = "";
  	protected String aCountry = "";
    protected String aAdditionalAddressInformation = "";
    protected String aAddressLine = "";

    // serializable version of unit information
    protected MyUnit [] myUnit = null;  
    
    // AddressLine may contain Living Unit in addition to Street Name
    // m_addressLineNoLU will contain the Street Number and Street Name portion of the AddressLine.
    transient protected String m_addressLineNoLU = "";
   
    transient protected String m_structType = "";
    transient protected String m_structValue = "";
    transient protected String m_levelType = "";
    transient protected String m_levelValue = "";
    transient protected String m_unitType = "";
    transient protected String m_unitValue = "";
    transient protected String m_postalCodePlus4 = "";
    transient protected String m_originalStreetDirection = "";
    transient protected String m_originalStreetNameSuffix = "";
    transient protected String m_businessName = "";
    transient protected String m_cassAdditionalInfo = "";
    transient public String[] m_addressLine = null;
    transient protected String[] m_cassAddressLines = null;
    transient protected String[] m_auxiliaryAddressLines = null;
    transient protected String m_addressId = "";
    transient protected String m_attention = "";

    transient protected boolean streetNmInd = false;
    transient protected boolean cityNmInd = false;
    transient protected int AHNoption = 0;
    transient private boolean isFieldedAddr = true;

    transient protected Logger theLogger = null;
    transient protected Address theAddress = new Address ();
	transient AddressHandler addressHandler = null;

    /**
     * AddressHandler constructor.
     */
    
     public AddressHandler() {
    	super();
    }
    /**
     * Creates an Address Object from a stream of bytes.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param addressBlob byte[]
     * @exception AddressHandlerException
     */

    public AddressHandler ( byte [] addressBlob) 
    throws AddressHandlerException
    {
		try
		{
			ByteArrayInputStream byteInStream = new ByteArrayInputStream (addressBlob);
			ObjectInputStream objectInStream = new ObjectInputStream (byteInStream);
			addressHandler = (AddressHandler) objectInStream.readObject ();
			byteInStream.close();
		}
		catch (NotSerializableException e)
		{
			throw new AddressHandlerException ("NotSerializableException caught while trying to deSerialize: " + e.getMessage());
			// System.err.println (e);
		}
		catch (IOException e)
		{
			throw new AddressHandlerException ("IOException caught while trying to deSerialize:: " + e.getMessage());
			// System.err.println (e);
		}
		catch (ClassNotFoundException e)
		{
			throw new AddressHandlerException ("ClassNotFoundException caught while trying to deSerialize:: " + e.getMessage());
			// System.err.println (e);
		} 
		catch (Exception e)
		{
			throw new AddressHandlerException ("Exception caught while trying to deSerialize:: " + e.getClass().getName() + e.getMessage());
			// System.err.println (e);
		} 
		// rz copy all fields to the latest address
		if (addressHandler.myUnit != null)
		{
	 		for (int ij = 0; ij < addressHandler.myUnit.length; ij++)
	 		{
		 		if (addressHandler.myUnit[ij].category == UnitCategory._STRUCTURE)
                {
					addressHandler.m_structType = addressHandler.myUnit[ij].type;
                    addressHandler.m_structValue = addressHandler.myUnit[ij].value;
                }
		 		else if (addressHandler.myUnit[ij].category == UnitCategory._LEVEL)
                {
                    addressHandler.m_levelType = addressHandler.myUnit[ij].type;
                    addressHandler.m_levelValue = addressHandler.myUnit[ij].value;
                }
		 		else if (addressHandler.myUnit[ij].category == UnitCategory._UNIT_CATEGORY)
                {
                    addressHandler.m_unitType = addressHandler.myUnit[ij].type;
                    addressHandler.m_unitValue = addressHandler.myUnit[ij].value;
                }
	 		}
	 	}
        
    	addressHandler.theAddress = new Address ();
    
    	/* 
    	 * Creates a fielded address for the addressHandler instance variable using its 
    	 * address values and then sets the fielded address to its address object.
    	 * 
    	 * Note:  initializeFieldedAddress() is called on addressHandler instance
    	 * variable, NOT this address handler.
    	 */
    	addressHandler.initializeFieldedAddress();
    	
    	parseUnit (addressHandler);
    
    	
    }
    
public AddressHandler (String ufAddress, String city, String state, String postalCode)
	throws AddressHandlerException
    {
    	try
    	{
    		if (ufAddress != null)
         		parseUFAddr (ufAddress, FieldedAddressList.getStreetDirSufList());
    	}
    	catch (AddressHandlerException ahe)
    	{
    		throw ahe;
    	}
    	
    	if (city != null)
        {
            aCity = city;
        }
        
        if (state != null)
        {
            aState = state;
        }
        
        if (postalCode != null)
        {
            aPostalCode = postalCode;
        }
        
        initializeFieldedAddress();

    }
    
    /**
     * Populate the Address Object elements into the AddressHandler class fields.
     * Creation date: (5/3/01 12:15:44 PM)
     * @param address Address
     * @exception AddressHandlerException
     */
    
    public AddressHandler (Address address)
    throws AddressHandlerException
    {	    		
    	theAddress = address;
    
    	try
    	{
        	switch (address.discriminator().value())
        	{
        		case AddressChoice._UNFIELDED_ADDRESS:
        
        			isFieldedAddr = false;
        			try
					{
        			m_addressLine = new String [theAddress.aUnfieldedAddress().aAddressLines.get___theValue().length];
        		    for ( int ik=0 ; ik < m_addressLine.length ; ik++ )
        		    {
        		    	m_addressLine[ik] = "";
        		    }
        			for (int ij=0; ij < theAddress.aUnfieldedAddress().aAddressLines.get___theValue().length; ij++) 
        			{
        				try 
        				{ 
        					aAddressLine = aAddressLine + theAddress.aUnfieldedAddress().aAddressLines.get___theValue()[ij].trim() + " "; 
        					m_addressLine[ij] = theAddress.aUnfieldedAddress().aAddressLines.get___theValue()[ij].trim();
        				}
        				catch (org.omg.CORBA.BAD_OPERATION e) {}
        				catch (java.lang.NullPointerException e) {}
        			}
					}
        			catch (Exception e) {}
        			try
					{
        				aAddressLine = aAddressLine.trim ();
					}
        			catch (Exception e) 
					{
        				theAddress.aUnfieldedAddress().aAddressLines = IDLUtil.toOpt (m_addressLine);
        			}
        			try { aCity = theAddress.aUnfieldedAddress().aCity.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aState = theAddress.aUnfieldedAddress().aState.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aPostalCode = theAddress.aUnfieldedAddress().aPostalCode.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aCounty = theAddress.aUnfieldedAddress().aCounty.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aCountry = theAddress.aUnfieldedAddress().aCountry.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aAdditionalAddressInformation = theAddress.aUnfieldedAddress().aAdditionalInfo.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { m_structType = theAddress.aUnfieldedAddress().aStructureType.get___theValue(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
                    try { m_structValue = theAddress.aUnfieldedAddress().aStructureValue.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_levelType = theAddress.aUnfieldedAddress().aLevelType.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_levelValue = theAddress.aUnfieldedAddress().aLevelValue.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_unitType = theAddress.aUnfieldedAddress().aUnitType.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_unitValue = theAddress.aUnfieldedAddress().aUnitValue.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        m_postalCodePlus4 =
                            theAddress.aUnfieldedAddress().aPostalCodePlus4.get___theValue();
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try {
                        m_businessName =
                            theAddress.aUnfieldedAddress().aBusinessName.get___theValue();
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
        			if (aAddressLine != null && !aAddressLine.equals (""))
        			{
        				parseUFAddrPlusLU (aAddressLine, " \t\n\r\f,;");
        				if (m_addressLineNoLU != null && !m_addressLineNoLU.equals (""))
         					parseUFAddr (m_addressLineNoLU, FieldedAddressList.getStreetDirSufList());
        			}
                    try {
                        m_attention =
                            theAddress.aUnfieldedAddress().aAttention.get___theValue();
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
        			parseUnit (this);
                    
                    // replace the parsed unit information in the unfielded address
        			try {
                        theAddress.aUnfieldedAddress().aStructureType = IDLUtil.toOpt (m_structType );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try {
                        theAddress.aUnfieldedAddress().aStructureValue = IDLUtil.toOpt (m_structValue );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try {
                        theAddress.aUnfieldedAddress().aLevelType = IDLUtil.toOpt (m_levelType );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aUnfieldedAddress().aLevelValue = IDLUtil.toOpt (m_levelValue );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aUnfieldedAddress().aUnitType = IDLUtil.toOpt (m_unitType );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aUnfieldedAddress().aUnitValue = IDLUtil.toOpt (m_unitValue );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                   
                    try { m_addressId = theAddress.aUnfieldedAddress().aAddressId.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}

         			break;
         			
        		case AddressChoice._FIELDED_ADDRESS:
        			try { aRoute = theAddress.aFieldedAddress().aRoute.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aBox = theAddress.aFieldedAddress().aBox.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aHouseNumberPrefix = theAddress.aFieldedAddress().aHouseNumberPrefix.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aHouseNumber = theAddress.aFieldedAddress().aHouseNumber.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aAssignedHouseNumber = theAddress.aFieldedAddress().aAssignedHouseNumber.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aHouseNumberSuffix = theAddress.aFieldedAddress().aHouseNumberSuffix.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aStreetDirection = theAddress.aFieldedAddress().aStreetDirection.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aStreetName = theAddress.aFieldedAddress().aStreetName.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aStreetThoroughfare = theAddress.aFieldedAddress().aStreetThoroughfare.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aStreetNameSuffix = theAddress.aFieldedAddress().aStreetNameSuffix.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aCity = theAddress.aFieldedAddress().aCity.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aState = theAddress.aFieldedAddress().aState.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aPostalCode = theAddress.aFieldedAddress().aPostalCode.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aCounty = theAddress.aFieldedAddress().aCounty.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aCountry = theAddress.aFieldedAddress().aCountry.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			try { aAdditionalAddressInformation = theAddress.aFieldedAddress().aAdditionalInfo.get___theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
                    try { m_structType = theAddress.aFieldedAddress().aStructureType.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_structValue = theAddress.aFieldedAddress().aStructureValue.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_levelType = theAddress.aFieldedAddress().aLevelType.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_levelValue = theAddress.aFieldedAddress().aLevelValue.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_unitType = theAddress.aFieldedAddress().aUnitType.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    try { m_unitValue = theAddress.aFieldedAddress().aUnitValue.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try { m_originalStreetDirection = theAddress.aFieldedAddress().aOriginalStreetDirection.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try { m_originalStreetNameSuffix = theAddress.aFieldedAddress().aOriginalStreetNameSuffix.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try { m_postalCodePlus4 = theAddress.aFieldedAddress().aPostalCodePlus4.get___theValue(); }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try { m_businessName = theAddress.aFieldedAddress().aBusinessName.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}

                    try { m_attention = theAddress.aFieldedAddress().aAttention.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}

                    try { m_cassAdditionalInfo = theAddress.aFieldedAddress().aCassAdditionalInfo.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try { m_cassAddressLines = theAddress.aFieldedAddress().aCassAddressLines.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try { m_auxiliaryAddressLines = theAddress.aFieldedAddress().aAuxiliaryAddressLines.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
                    try { m_addressId = theAddress.aFieldedAddress().aAddressId.get___theValue(); } 
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
                    
        			if (aStreetName.startsWith (ad_sign))
        			{
        				aStreetName = aStreetName.substring (2, aStreetName.length ());
        				theAddress.aFieldedAddress().aStreetName.theValue( aStreetName );
        				streetNmInd = true;
        			}
    
        			parseUnit (this);
                    
                    // replace the parsed unit information in the fielded address
                    try {
                        theAddress.aFieldedAddress().aStructureType = IDLUtil.toOpt (m_structType );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aFieldedAddress().aStructureValue = IDLUtil.toOpt (m_structValue );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aFieldedAddress().aLevelType = IDLUtil.toOpt (m_levelType );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aFieldedAddress().aLevelValue = IDLUtil.toOpt (m_levelValue );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aFieldedAddress().aUnitType = IDLUtil.toOpt (m_unitType );
                    }
                    catch (org.omg.CORBA.BAD_OPERATION e) {}
                    catch (java.lang.NullPointerException e) {}
        
                    try {
                        theAddress.aFieldedAddress().aUnitValue = IDLUtil.toOpt (m_unitValue );
                    }
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
                                
        			break;
        
        		default:
        			throw new AddressHandlerException ("Not a valid Address Object, needs to be Fielded or Unfielded.");
        	} // end switch
    	} // end try
    	catch (Exception e)
    	{
    		throw new AddressHandlerException ("Exception caught while trying to parse the Address Object. " + e.getMessage ());
    	}
    
    }
    /**
     * AddressHandler constructor that initializes the instance
     * variables of a new AddressHandler object with address information.
     * It also sets the address instance variable for the new instance.
     * Creation date: (12/19/01 6:43:08 PM)
     * @param route                         a String
     * @param box                           a String
     * @param houseNumberPrefix             a String
     * @param houseNumber                   a String
     * @param assignedHouseNumber           a String
     * @param houseNumberSuffix             a String
     * @param streetDirection               a String
     * @param streetName                    a String
     * @param streetThoroughfare            a String
     * @param streetNameSuffix              a String
     * @param city                          a String
     * @param state                         a String
     * @param postalCode                    a String
     * @param county                        a String
     * @param country                       a String
     * @param unitStructType                a String
     * @param unitStructValue               a String
     * @param unitLevelType                 a String
     * @param unitLevelValue                a String
     * @param unitUnitType                  a String
     * @param unitUnitValue                 a String
     * @param additionalAddressInformation  a String
     */
    public AddressHandler (String route, String box, String houseNumberPrefix, 
        String houseNumber, String assignedHouseNumber, String houseNumberSuffix, 
        String streetDirection, String streetName, String streetThoroughfare, 
        String streetNameSuffix, String city, String state, String postalCode, 
        String county, String country, String unitStructType,
        String unitStructValue, String unitLevelType, String unitLevelValue,
        String unitUnitType, String unitUnitValue,
        String additionalInfo)
    {
        this( route,
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
        null, 
        county,
        country,
        unitStructType,
        unitStructValue,
        unitLevelType,
        unitLevelValue,
        unitUnitType,
        unitUnitValue,
        null,
        null,
        null,
        null,
        null,
        additionalInfo,
        null,
        null );
    }

    /**
     * AddressHandler constructor that initializes the instance
     * variables of a new AddressHandler object with address information.
     * It also sets the address instance variable for the new instance.
     * @param addressLine               a String
     * @param city                      a String
     * @param state                     a String
     * @param postalCode                a String
     * @param postalCodePlus4           a String
     * @param structureType             a String
     * @param structureValue            a String
     * @param levelType                 a String
     * @param levelValue                a String
     * @param unitType                  a String
     * @param unitValue                 a String
     */
    public AddressHandler (String addressLine, String city, String county, String state, String postalCode, 
    	String postalCodePlus4, String structureType, String structureValue, String levelType, String levelValue, 
    	String unitType, String unitValue)
    	throws AddressHandlerException
    {
    	try
    	{
    		if (addressLine != null)
         		parseUFAddr (addressLine, FieldedAddressList.getStreetDirSufList());
    	}
    	catch (AddressHandlerException ahe)
    	{
    		throw ahe;
    	}		
                
        if (city != null)
        {
            aCity = city;
        }
        
        if (county != null)
        {
            aCounty = county;
        }

        if (state != null)
        {
            aState = state;
        }
        
        if (postalCode != null)
        {
            aPostalCode = postalCode;
        }
                        
        if( postalCodePlus4 != null)
        {
            m_postalCodePlus4 = postalCodePlus4;
        }
        
        if (structureType != null)
        {
           m_structType = structureType;
        }
        
        if (structureValue != null)
        {
            m_structValue = structureValue;
        }
            
        if (levelType != null)
        {
            m_levelType = levelType;
        }
        
        if (levelValue != null)
        {
            m_levelValue = levelValue;
        }
            
        if (unitType != null)
        {
            m_unitType = unitType; 
        }
        
        if (unitValue != null)
        {
            m_unitValue = unitValue;
        }
            
        initializeFieldedAddress();
    }

    /**
     * Method AddressHandler.
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
     * @param postalCodePlus4
     * @param county
     * @param country
     * @param unitStructType
     * @param unitStructValue
     * @param unitLevelType
     * @param unitLevelValue
     * @param unitUnitType
     * @param unitUnitValue
     * @param originalStreetDirection
     * @param originalStreetNameSuffix
     * @param cassAddressLines
     * @param auxiliaryAddressLines
     * @param cassAdditionalInfo
     * @param additionalInfo
     * @param businessName
     * @param attention
     */
     public AddressHandler(
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
        String postalCodePlus4, 
        String county,
        String country,
        String unitStructType,
        String unitStructValue,
        String unitLevelType,
        String unitLevelValue,
        String unitUnitType,
        String unitUnitValue,
        String originalStreetDirection,
        String originalStreetNameSuffix,
        String[] cassAddressLines,
        String[] auxiliaryAddressLines,
        String cassAdditionalInfo,
        String additionalInfo,
        String businessName,
        String attention)
    {
            
        if (route != null)
        {
            aRoute = route;
        }
        
        if (box != null)
        {
            aBox = box;
        }
        
        if (houseNumberPrefix != null)
        {
            aHouseNumberPrefix = houseNumberPrefix;
        }
        
        if (houseNumber != null)
        {
            aHouseNumber = houseNumber;
        }
        
        if (assignedHouseNumber != null)
        {
            aAssignedHouseNumber = assignedHouseNumber;
        }
        
        if (houseNumberSuffix != null)
        {
            aHouseNumberSuffix = houseNumberSuffix;
        }
    
        if (streetDirection != null)
        {
            aStreetDirection = streetDirection;
        }
        
        if (streetName != null)
        {
            aStreetName = streetName;
        }
        
        if (streetThoroughfare != null)
        {
            aStreetThoroughfare = streetThoroughfare;
        }
            
        if (streetNameSuffix != null)
        {
            aStreetNameSuffix = streetNameSuffix;
        }
        
        if (city != null)
        {
            aCity = city;
        }
        
        if (state != null)
        {
            aState = state;
        }
        
        if (postalCode != null)
        {
            aPostalCode = postalCode;
        }
        
        if (county != null)
        {
            aCounty = county;
        }
        
        if (country != null)
        {
            aCountry = country;
        }
        
        if (additionalInfo != null)
        {
            aAdditionalAddressInformation = additionalInfo;
        }

        if (unitStructType != null)
        {
           m_structType = unitStructType;
        }
        
        if (unitStructValue != null)
        {
            m_structValue = unitStructValue;
        }
            
        if (unitLevelType != null)
        {
            m_levelType = unitLevelType;
        }
        
        if (unitLevelValue != null)
        {
            m_levelValue = unitLevelValue;
        }
            
        if (unitUnitType != null)
        {
            m_unitType = unitUnitType; 
        }
        
        if (unitUnitValue != null)
        {
            m_unitValue = unitUnitValue;
        }
            
        if( originalStreetDirection != null)
        {
            m_originalStreetDirection = originalStreetDirection;
        }
            
        if( originalStreetNameSuffix != null)
        {
            m_originalStreetNameSuffix = originalStreetNameSuffix;
        }
            
        if( postalCodePlus4 != null)
        {
            m_postalCodePlus4 = postalCodePlus4;
        }
        
        if( businessName != null)
        {
            m_businessName = businessName;
        }
            
        if( cassAdditionalInfo != null)
        {
            m_cassAdditionalInfo = cassAdditionalInfo;
        }
            
        if( cassAddressLines != null)
        {
            m_cassAddressLines = cassAddressLines;
        }
            
        if( auxiliaryAddressLines != null)
        {
            m_auxiliaryAddressLines = auxiliaryAddressLines;
        }
            
        initializeFieldedAddress();
            
    }
    
    /**
     * Compare addressHandler fields with another addressHandler, excluding unit.
     * Creation date: (1/10/02 12:55:56 PM)
     * @return boolean
     * @param addrHndlr  an AddressHandler object
     */
    public boolean compareAddr(AddressHandler addrHndlr) {
    
    	if ((aRoute.equalsIgnoreCase(addrHndlr.getRoute())) &&
      		(aBox.equalsIgnoreCase(addrHndlr.getBox())) &&
      		(aHouseNumberPrefix.equalsIgnoreCase(addrHndlr.getHousNbrPfx())) &&
      		(aHouseNumber.equalsIgnoreCase(addrHndlr.getHousNbr())) &&
      		(aAssignedHouseNumber.equalsIgnoreCase(addrHndlr.getAasgndHousNbr())) &&
      		(aHouseNumberSuffix.equalsIgnoreCase(addrHndlr.getHousNbrSfx())) &&
      		(aStreetDirection.equalsIgnoreCase(addrHndlr.getStDir())) &&
      		(aStreetName.equalsIgnoreCase(addrHndlr.getStName())) &&
      		(aStreetThoroughfare.equalsIgnoreCase(addrHndlr.getStThorofare())) &&
      		(aStreetNameSuffix.equalsIgnoreCase(addrHndlr.getStNameSfx())) &&
      		(aCity.equalsIgnoreCase(addrHndlr.getCity())) &&
      		(aState.equalsIgnoreCase(addrHndlr.getState())) &&
      		(aPostalCode.equalsIgnoreCase(addrHndlr.getPostalCode())) &&
      		(aCounty.equalsIgnoreCase(addrHndlr.getCounty())) &&
      		(aCountry.equalsIgnoreCase(addrHndlr.getCountry())) &&
      		(aAdditionalAddressInformation.equalsIgnoreCase(addrHndlr.getAddAddrInfo())))
    		return true;
    	
    	return false;
    }
    /**
     * Compare addressHandler fields with another address, excluding unit.
     * Creation date: (1/10/02 12:55:56 PM)
     * @return boolean true if addresses match, false if any portion of the addresses are different
     * @param addr Address
     */
    public boolean compareAddr(Address addr) {
    
    	/* Return false if there are any mismatches */
    	try { if ( !addr.aFieldedAddress().aRoute.get___theValue().trim().equalsIgnoreCase( getRoute() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aBox.get___theValue().trim().equalsIgnoreCase( getBox() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( addr.aFieldedAddress().aHouseNumberPrefix.get___theValue().trim().equalsIgnoreCase( getHousNbrPfx() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aHouseNumber.get___theValue().trim().equalsIgnoreCase( getHousNbr() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aAssignedHouseNumber.get___theValue().trim().equalsIgnoreCase( getAasgndHousNbr() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aHouseNumberSuffix.get___theValue().trim().equalsIgnoreCase( getHousNbrSfx() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aStreetDirection.get___theValue().trim().equalsIgnoreCase( getStDir() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aStreetName.get___theValue().trim().equalsIgnoreCase( getStName() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aStreetThoroughfare.get___theValue().trim().equalsIgnoreCase( getStThorofare() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aStreetNameSuffix.get___theValue().trim().equalsIgnoreCase( getStNameSfx() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aCity.get___theValue().trim().equalsIgnoreCase( getCity() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aState.get___theValue().trim().equalsIgnoreCase( getState() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aPostalCode.get___theValue().trim().equalsIgnoreCase( getPostalCode() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aCounty.get___theValue().trim().equalsIgnoreCase( getCounty() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aCountry.get___theValue().trim().equalsIgnoreCase( getCountry() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; }
    	
    	try { if ( !addr.aFieldedAddress().aAdditionalInfo.get___theValue().trim().equalsIgnoreCase( getAddAddrInfo() )) return false; } 
    	catch (org.omg.CORBA.BAD_OPERATION e) { return false; }
    	catch (java.lang.NullPointerException e) { return false; } 
    
    	/* Everything matches */
    	return true;
    }
    /**
     * Compares two objects for equality. Returns a boolean that indicates
     * whether this object is equivalent to the specified object. This method
     * is used when an object is stored in a hashtable.
     * Creation date: (5/3/01 12:51:54 PM)
     * @param obj Object
     * @return boolean
     */
    
    public boolean equals(Object obj)
    {
    	if (obj instanceof AddressHandler)
    		return (hashString().equals(((AddressHandler) obj).hashString()));
    	
    	return false;
    }
    /**
     * returns AssignedHouseNumber.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getAasgndHousNbr() 
    {	
	    return trimString(aAssignedHouseNumber);
    }
    /**
     * returns AdditionalAddressInformation.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getAddAddrInfo() 
    {	
	    return trimString(aAdditionalAddressInformation);
    }
    /**
     * returns Address Object.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return Address
     */
    
    public Address getAddress()
    {
    	return theAddress;
    }
    /**
     * Serializes an AddressHandler Object to a stream of bytes.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return byte[]
     * @throws AddressHandlerException
     */
     
    public byte [] getAddressBlob()
    throws AddressHandlerException
    {
    	Vector units = new Vector();

        if((m_structType != null && m_structType.trim().length() > 0) && (m_structValue != null && m_structValue.trim().length() > 0))
        {
        	MyUnit structUnit = new MyUnit();
        	structUnit.category = UnitCategory._STRUCTURE;
            structUnit.type = m_structType.trim();
            structUnit.value = m_structValue.trim();
            units.add(structUnit);
        }
    
        if((m_levelType != null && m_levelType.trim().length() > 0) && (m_levelValue != null && m_levelValue.trim().length() > 0))
        {
            MyUnit levelUnit = new MyUnit();    
            levelUnit.category = UnitCategory._LEVEL;
            levelUnit.type = m_levelType.trim();
            levelUnit.value = m_levelValue.trim();
            units.add(levelUnit);
        }
        
        if((m_unitType != null && m_unitType.trim().length() >0) && (m_unitValue != null && m_unitValue.trim().length() > 0))
        {
            MyUnit unitUnit = new MyUnit();
            unitUnit.category = UnitCategory._UNIT_CATEGORY;
            unitUnit.type = m_unitType.trim();
            unitUnit.value = m_unitValue.trim();
            units.add(unitUnit);
        }
        
        if( units.size() > 0 )
        {
            myUnit = new MyUnit[units.size()];
            units.copyInto(myUnit);
        }
        
    	byte [] byteOut = null;
    	addressHandler = this;
    	
    	if (!addressHandler.getClass().getName (). equals ("com.sbc.eia.idl.lim.helpers.AddressHandler"))
    		throw new AddressHandlerException ("Object is not com.sbc.eia.idl.lim.helpers.AddressHandler. Object will not be serialized.");
    	try
    	{
    		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream ();
    		ObjectOutputStream objectOutStream = new ObjectOutputStream (byteOutStream);
    		objectOutStream.writeObject (addressHandler);
    		byteOut = new byte [byteOutStream.size()];
    		byteOut = byteOutStream.toByteArray ();
    		byteOutStream.close();
    	}
    	catch (NotSerializableException e)
    	{
    		throw new AddressHandlerException ("NotSerializableException caught while trying to Serialize: " + e.getMessage());
    		// System.err.println (e);
    	}
    	catch (IOException e)
    	{
    		throw new AddressHandlerException ("IOException caught while trying to Serialize: " + e.getMessage());
    		// System.err.println (e);
    	}
    	catch (Exception e)
    	{
    		throw new AddressHandlerException ("Exception caught while trying to Serialize: " + e.getMessage());
    		// System.err.println (e);
    	} 
    	return byteOut;
    }
    /**
     * returns AddressHandler Object.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return AddressHandler
     */
    
    public AddressHandler getAddressHandler()
    {
    	return addressHandler;
    }
    /**
     * returns AddressLine.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getAddressLine() 
    {	
        return trimString(aAddressLine);
    }
    /**
     * returns Box.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getBox() 
    {	
	    return trimString(aBox);
    }
    /**
     * returns City.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     * @see #setCity(String)
     */
    
    public String getCity() 
    {	
	    return trimString(aCity);
    }
    /**
     * returns Country.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getCountry() 
    {	
	    return trimString(aCountry);
    }
    /**
     * returns County.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getCounty() 
    {	
	    return trimString(aCounty);
    }
    /**
     * returns HouseNumber.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getHousNbr() 
    {	
        return trimString(aHouseNumber);
    }
    /**
     * returns HouseNumberPrefix.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getHousNbrPfx() 
    {	
	    return trimString(aHouseNumberPrefix);
    }
    /**
     * returns HouseNumberSuffix.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     * @see #setHousNbrSfx
     */
    
    public String getHousNbrSfx() 
    {	
	    return trimString(aHouseNumberSuffix);
    }
    /**
     * returns living unit level type.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getLevelType() 
    { 
       return trimString(m_levelType);
    }
    /**
     * returns living unit level value.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getLevelValue() 
    { 
        return trimString(m_levelValue);
    }
    /**
     * returns OriginalStreetDirection.
     * Creation date: (4/15/04 12:15:44 PM)
     * @return String
     */
    public String getOriginalStreetDirection() 
    {   
        return trimString(m_originalStreetDirection);
    }
    /**
     * returns OriginalStreetNameSuffix.
     * Creation date: (4/15/04 12:15:44 PM)
     * @return String
     */
    public String getOriginalStreetNameSuffix() 
    {   
        return trimString(m_originalStreetNameSuffix);
    }

    /**
     * returns PostalCode.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     * @see #setPostalCode(String)
     */
    
    public String getPostalCode() 
    {	
	    return trimString(aPostalCode);
    }
    /**
     * returns Route.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getRoute() 
    {	
	    return trimString(aRoute);
    }
    /**
     * returns State.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     * @see #setState(String)
     */
    
    public String getState() 
    {	
	    return trimString(aState);
    }
    /**
     * returns StreetDirection.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getStDir() 
    {	
	    return trimString(aStreetDirection);
    }
    /**
     * returns StreetName.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     * @see #setStName(String)
     */
    
    public String getStName() 
    {	
	    return trimString(aStreetName);
    }
    /**
     * returns StreetNameSuffix.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getStNameSfx() 
    {	
	    return trimString(aStreetNameSuffix);
    }
    /**
     * This method return a boolean indicating whether to add the @ sign to the street name.
     * Creation date: (8/8/01 11:29:51 AM)
     * @return boolean
     */
    public boolean getStNmInd() {
    	return streetNmInd;
    }
    /**
     * returns StreetName in a format needed by LFACS and PREMIS-APP MTT.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    public String getStreetName() 
    {	
    	String stNm = "";
    
    	if (AHNoption == 2)	// LFACS
    	{
    		if (aStreetName == null || aStreetName.trim().equals(""))
    		{
    			if (aAdditionalAddressInformation != null)
    				if (!aAdditionalAddressInformation.trim().equals(""))
    				{
    					stNm = aAdditionalAddressInformation.trim();
    					if (aAssignedHouseNumber != null)
    						if (!aAssignedHouseNumber.equals(""))
    							if (!stNm.startsWith (ad_sign))
    								return ad_sign + stNm;
    					return stNm;
    				}
    		}
    	}
    		
    	boolean addBlank = false;
    
    	if (aStreetDirection != null)
    		if (!aStreetDirection.trim().equals(""))
    		{
    			stNm += aStreetDirection.trim();
    			addBlank = true;
    		}
    
    	if (AHNoption == 2)	// LFACS
    	{
    		if (aRoute != null)
    		{
    			if (!aRoute.equals(""))
    			{
    				if (addBlank)
    					stNm += " ";
    				stNm = stNm + "Route " + aRoute.trim();
    				addBlank = true;
    			}
    		}
    		if (aBox != null)
    		{
    			if (!aBox.equals(""))
    			{
    				if (addBlank)
    					stNm += " ";
    				stNm = stNm + "Box " + aBox.trim();
    				addBlank = true;
    			}
    		}
    	}
    		
    	if (aStreetName != null)
    		if (!aStreetName.equals(""))
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
    		}
    
    	stNm = stNm.trim();
    	
    	return stNm;
    }
    /**
     * returns StreetNumber in a format needed by LFACS and PREMIS-APP MTT.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getStreetNumber ()
    {
    	String stNmbr = "";
    
    	if (AHNoption == 2)	// LFACS
    	{
    		if (aAssignedHouseNumber.trim ().startsWith ("*"))
    			return stNmbr;
    	}
    	
    	if (aHouseNumber == null || aHouseNumber.equals(""))
    	{
    		if (AHNoption == 1)	// PREMIS-APP MTT
    			stNmbr = aAssignedHouseNumber.trim() + "-" + "AHN";
    		else if (AHNoption == 2)	// LFACS
    			stNmbr = aAssignedHouseNumber.trim();
    			
    		return stNmbr;
    	}
    
    	if (aHouseNumberPrefix != null)
    		if (!aHouseNumberPrefix.trim().equals(""))
    		{
    			stNmbr += aHouseNumberPrefix.trim();
    			stNmbr += " ";
    		}
    		
    	if (aHouseNumber != null)
    		if (!aHouseNumber.trim().equals(""))
    		{
    			stNmbr += aHouseNumber.trim();
    		}
    		
    	if (aHouseNumberSuffix != null)
    		if (!aHouseNumberSuffix.trim().equals(""))
    			if (aHouseNumberSuffix.trim().startsWith ("-"))
    				stNmbr += aHouseNumberSuffix.trim();
    			else
    				stNmbr = stNmbr + "-" + aHouseNumberSuffix.trim();
    				
    	return (stNmbr);
    }
    /**
     * returns living unit structure type.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getStructType() 
    {
        return trimString(m_structType);
    }
    /**
     * returns living unit structure value.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getStructValue() 
    {
        return trimString(m_structValue);
    }
    /**
     * returns StreetThoroughfare.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getStThorofare() 
    {	
	    return trimString(aStreetThoroughfare);
    }
    
    /**
     * returns living unit unit type.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getUnitType() 
    {
        return trimString(m_unitType);
    }
    /**
     * returns living unit unit value.
     * Creation date: (5/3/01 12:15:44 PM)
     * @return String
     */
    
    public String getUnitValue() 
    {
	   return trimString(m_unitValue);
    }

    /**
     * Returns the m_addressId.
     * @return String
     */
    public String getAddressId()
    {
        return trimString(m_addressId);
    }

    /**
     * Parses an input string and populates the internal unit values.
     * This method is mainly used by SM.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param livingUnit String
     * @param delim String
     * @exception AddressHandlerException
     */
    
    protected void handleUnit (String livingUnit, String delim)
    throws AddressHandlerException
    { 
    
    	ArrayList unitStructureNameList = FieldedAddressList.getStructureNameList();
    	ArrayList unitLevelNameList = FieldedAddressList.getLevelNameList();
    	ArrayList unitUnitNameList = FieldedAddressList.getUnitNameList();
    
    	try{
    
    		String [] s_unit = new String [4];
    		int n_unit = 0;
    		String unit_val = "";
    		String unit_type = "";
    		
    		StringTokenizer unit_tmp = new java.util.StringTokenizer (livingUnit, delim);
    	 	while (unit_tmp.hasMoreTokens())
    	 	{
    			String s_tmp = unit_tmp.nextToken ().toUpperCase();
    			s_tmp = s_tmp.trim ();
    			
    	 		//if unit is spaces, don't do anything
    			if (s_tmp.length() > 0)
	   			{
    				/* if the unit type and value is closed by parenthesis => remove the parenthesis */
    				if (s_tmp.charAt (0) == '(')
    					if (s_tmp.charAt (s_tmp.length ()-1) == ')')
    						s_unit[n_unit++] = s_tmp.substring (1, s_tmp.length ()-1);
    					else
    						s_unit[n_unit++] = s_tmp.substring (1, s_tmp.length ());
    				else
    					s_unit[n_unit++] = s_tmp;
    			}
    		 	if (n_unit == 4)
    		 		break;
    	 	}

    	 	int cur_unit = 0;
    	 	
    	 	for (int ij = 0; ij < n_unit; ij++)
    	 	{
    			StringTokenizer s_unit_tmp = new java.util.StringTokenizer (s_unit[ij], " ");
    		 	boolean addToAAI = false;
    	 		if (s_unit_tmp.hasMoreTokens())
    	 		{
    		 		unit_type = s_unit_tmp.nextToken ().trim ();
    		 		unit_val = "";
    		 		while (s_unit_tmp.hasMoreTokens())
    		 		{
    			 		String nextVal = s_unit_tmp.nextToken ().trim ();
    			 		if ((!unit_val.equals ("") && nextVal.startsWith ("(")) || addToAAI)
    			 		{
    				 		addToAAI = true;
    			 			aAdditionalAddressInformation += nextVal + " ";
    			 		}
    			 		else
    		 				unit_val += " " + nextVal;
    		 		}
    		 		unit_val = unit_val.trim ();
    				if (unitStructureNameList.contains (unit_type) && !unit_val.equals (""))
    				{
    					m_structType = unit_type;
    					m_structValue = unit_val;
    				}
    				else if (unitLevelNameList.contains (unit_type) && !unit_val.equals (""))
    				{		 			
    					m_levelType = unit_type;
    					m_levelValue = unit_val;
    				}
    				else if (unitUnitNameList.contains (unit_type) && !unit_val.equals (""))
    				{		 			
    					m_unitType = unit_type;
    					m_unitValue = unit_val;
    				}
    				else if (unit_type.equals ("AHN"))
    				{
    					aAssignedHouseNumber = unit_val;	
    				}
    				else
    				{		 	
    					aAdditionalAddressInformation += s_unit[ij] + " ";	
    				}
    	 		}
    	 	}
    
    	 	aAdditionalAddressInformation = aAdditionalAddressInformation.trim ();
    	 	
    	}			
    	catch( Exception e )
    	{
    		e.printStackTrace();
    		throw new AddressHandlerException (e.getMessage());
    	}	
    }
 
    /**
     * Generates a hash code for the receiver. This method is supported primarily for hash tables,
     * such as those provided in java.util. @return an integer hash code for the receiver
     * @see java.util.Hashtable
     * Creation date: (5/3/01 12:20:56 PM)
     * @return int
     */
    
    public int hashCode()
    {
    	return hashString().hashCode();
    }
    /**
     * Creation date: (5/3/01 12:20:56 PM).
     * @return String
     */
    
    protected String hashString()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append(aRoute);
    	sb.append(aBox);
    	sb.append(aHouseNumberPrefix);
    	sb.append(aHouseNumber);
    	sb.append(aAssignedHouseNumber);
    	sb.append(aHouseNumberSuffix);
    	sb.append(aStreetDirection);
    	sb.append(aStreetName);
    	sb.append(aStreetThoroughfare);
    	sb.append(aStreetNameSuffix);
    	sb.append(aCity);
    	sb.append(aState);
    	sb.append(aPostalCode);
    	sb.append(aCounty);
    	sb.append(aCountry);
    	sb.append(aAdditionalAddressInformation);
    	
    	sb.append( m_structType );
        sb.append( m_structValue );
        sb.append( m_levelType );
        sb.append( m_levelValue );
        sb.append( m_unitType );
        sb.append( m_unitValue );
        sb.append( m_postalCodePlus4 );
        sb.append( m_originalStreetDirection );
        sb.append( m_originalStreetNameSuffix );
        sb.append( m_businessName );
        sb.append( m_cassAdditionalInfo );
        
        if( m_cassAddressLines != null )
        {
            for ( int i = 0 ; i < m_cassAddressLines.length ; i++ )
            {
                sb.append( m_cassAddressLines[i] );
            }
        }
        
        if( m_auxiliaryAddressLines != null )
        {
            for ( int i = 0 ; i < m_auxiliaryAddressLines.length ; i++ )
            {
                sb.append( m_auxiliaryAddressLines[i] );
            }
        }
    	
    	return sb.toString();
    }
    /**
     * Creates a fielded address for this address handler using its 
     * address values and then sets the fielded address to its address object.
     * Creation date: (5/6/2002 5:10:46 PM)
     */
    protected void initializeFieldedAddress() {

    	FieldedAddress fa = new FieldedAddress(
    		IDLUtil.toOpt( trimString(aRoute) ),
    		IDLUtil.toOpt( trimString(aBox) ),
    		IDLUtil.toOpt( trimString(aHouseNumberPrefix) ),
    		IDLUtil.toOpt( trimString(aHouseNumber) ),
    		IDLUtil.toOpt( trimString(aAssignedHouseNumber) ),
    		IDLUtil.toOpt( trimString(aHouseNumberSuffix) ),
    		IDLUtil.toOpt( trimString(aStreetDirection) ),
    		IDLUtil.toOpt( trimString(aStreetName) ),
    		IDLUtil.toOpt( trimString(aStreetThoroughfare) ),
    		IDLUtil.toOpt( trimString(aStreetNameSuffix) ),
    		IDLUtil.toOpt( trimString(aCity) ),
    		IDLUtil.toOpt( trimString(aState) ),
    		IDLUtil.toOpt( trimString(aPostalCode) ),
            IDLUtil.toOpt( trimString(m_postalCodePlus4) ),
    		IDLUtil.toOpt( trimString(aCounty) ),
    		IDLUtil.toOpt( trimString(aCountry) ),
    		IDLUtil.toOpt( trimString(m_structType) ),
            IDLUtil.toOpt( trimString(m_structValue) ),
            IDLUtil.toOpt( trimString(m_levelType) ),
            IDLUtil.toOpt( trimString(m_levelValue) ),
            IDLUtil.toOpt( trimString(m_unitType) ),
            IDLUtil.toOpt( trimString(m_unitValue) ),
            IDLUtil.toOpt( trimString(m_originalStreetDirection) ),
            IDLUtil.toOpt( trimString(m_originalStreetNameSuffix) ),   
            IDLUtil.toOpt( m_cassAddressLines ),
            IDLUtil.toOpt( m_auxiliaryAddressLines ),
            IDLUtil.toOpt( trimString(m_cassAdditionalInfo) ),
    		IDLUtil.toOpt( trimString(aAdditionalAddressInformation) ),
            IDLUtil.toOpt( trimString(m_businessName) ),
            IDLUtil.toOpt (""),
            IDLUtil.toOpt (""),
            IDLUtil.toOpt (""),
            IDLUtil.toOpt (""),
            IDLUtil.toOpt (""),
            IDLUtil.toOpt( trimString(m_attention) )
            );
            
    	theAddress.aFieldedAddress(fa);
    }
    /**
     * Indicates whether an address is fielded.
     * Creation date: (12/19/01 6:53:42 PM)
     * @return boolean
     */
    public boolean isFielded() {
    	return isFieldedAddr;
    }
    /**
     * Return a display formatted String of the given String array of address line.
     * Creation date: (5/2/01 10:40:50 AM)
     * @return String
     * @param aAddressLines String[]
     */
    public static String parseAddressLines(String[] aAddressLines)
    {
    	StringBuffer outptStr = new StringBuffer();
    	for (int i=0; i < aAddressLines.length; i++){
    		try{outptStr.append(aAddressLines[i] + " ");} catch (Exception e) { outptStr.append( " "); }
    	}
    	return outptStr.toString();
    }
    /**
     * Parse an unfielded address into StreetDirection, StreetName, StreetThoroughfare, StreetNameSuffix.
     * Creation date: (08/31/01 12:00:00 PM)
     * @param StDirStNmStThoStSuf [] String
     * @param index int
     * @param streetDirSufList ArrayList
     * @exception AddressHandlerException
     */
    
    protected void parseStDirStNmStThoStSuf (String [] StDirStNmStThoStSuf, int index, ArrayList streetDirSufList)
    throws AddressHandlerException
    {
    	ArrayList streetThoroughfareList = FieldedAddressList.getStreetThoroughfareList();
    	
    	if (StDirStNmStThoStSuf == null)
    		return;
    	int ufAddrLen = StDirStNmStThoStSuf.length;
    	if (ufAddrLen <= 0)
    		return;
    		
    	try
    	{		
    		if (index < ufAddrLen) 
    		{
    			if (streetDirSufList.contains(StDirStNmStThoStSuf[index].toUpperCase())) 
    			{
    				boolean addSTName = true;
    				if ((index+1) < ufAddrLen)
    				{
    					// Street Directional Exception: If an address contains only direction and thoroughfare, 
    					// for example E ST, E becomes Street Name and ST is thoroughfare.
    					if (streetThoroughfareList.contains(StDirStNmStThoStSuf[index+1].toUpperCase()) 
    						&& !(((index+2) < ufAddrLen) && !streetDirSufList.contains(StDirStNmStThoStSuf[index+2].toUpperCase()))) 
    					{
    						aStreetName = StDirStNmStThoStSuf[index];
    						index++;
    						aStreetThoroughfare = StDirStNmStThoStSuf[index].toUpperCase();
    						index ++;
    						if (index < ufAddrLen)
    						{
    							if (streetDirSufList.contains(StDirStNmStThoStSuf[index].toUpperCase())) 
    							{
    								if ((index + 1) < ufAddrLen)
    								{
    									aStreetName += " " + StDirStNmStThoStSuf[index-1];
    									aStreetName += " " + StDirStNmStThoStSuf[index];
    									aStreetThoroughfare = "";
    									addSTName = false;
    								}
    								else
    									aStreetNameSuffix = StDirStNmStThoStSuf[index].toUpperCase();	
    								index ++;
    							}
    							else
    							{
    								aStreetName += " " + StDirStNmStThoStSuf[index-1];
    								aStreetName += " " + StDirStNmStThoStSuf[index];
    								aStreetThoroughfare = "";
    								index++;
    								addSTName = false;
    							}								
    						}	
    						// if (index < ufAddrLen)
    						// throw new AddressHandlerException ("Not a valid AddressLine, can not have additional tokens after StreetNameSuffix or StreetThoroughfare.");
    					} // end if (streetThoroughfareVector.contains(StDirStNmStThoStSuf[index+1].toUpperCase())) 
    					else // no StreetThoroughfare after StreetDirection
    					{
    						if (StDirStNmStThoStSuf[index].length() == 1)
    						{
    							//E, S, W, N
    							if (StDirStNmStThoStSuf[index + 1].length() == 1)
    							{
    								//After E,S,W,N, the token is one character. ex: E A
    								if ((index + 2) < ufAddrLen &&
    									 streetThoroughfareList.contains(StDirStNmStThoStSuf[index + 2].toUpperCase()))
    								{
    									//Two single characters follow by thoroughfare. Ex: 123 E A ST.
    									if ((index + 3) < ufAddrLen)
    									{
    										//More token after thoroughfare
    										if ((index + 3) == (ufAddrLen - 1) &&
    											 streetDirSufList.contains(StDirStNmStThoStSuf[index + 3].toUpperCase()))
    										{
    											//Street Direction Suffix is the last character. Ex: 123 S E ST N
    											aStreetDirection = StDirStNmStThoStSuf[index++].toUpperCase();
    											aStreetName = StDirStNmStThoStSuf[index++];
    											aStreetThoroughfare = StDirStNmStThoStSuf[index++].toUpperCase();
    											aStreetNameSuffix = StDirStNmStThoStSuf[index++].toUpperCase();
    										}
    										else
    										{
    											//Street Direction Suffix is not the last character or have more token after
    											//Treat them as street name and keep moving on.
    											StringBuffer temp = new StringBuffer();
    											
    											temp.append(StDirStNmStThoStSuf[index++]).append(" ");
    											temp.append(StDirStNmStThoStSuf[index++]).append(" ");
    											temp.append(StDirStNmStThoStSuf[index++]);
    											aStreetName = temp.toString();
    											addSTName = false;
    											temp = null;
    										}
    									}
    									else
    									{
    										//Ex: 123 S E ST
    										aStreetDirection = StDirStNmStThoStSuf[index++].toUpperCase();
    										aStreetName = StDirStNmStThoStSuf[index++];
    										aStreetThoroughfare = StDirStNmStThoStSuf[index++].toUpperCase();
    									}
    								}
    								else
    								{
    									//Ex: 123 S O M Center...
    									StringBuffer temp = new StringBuffer();
    									temp.append(StDirStNmStThoStSuf[index++]).append(" ");
    									temp.append(StDirStNmStThoStSuf[index++]);
    									aStreetName = temp.toString();
    									temp = null;
    								}
    							}
    							else
    							{
    								//Ex: 123 N Main...
    								aStreetDirection = StDirStNmStThoStSuf[index].toUpperCase();	
    								index ++;
    							}
    						}
    						else
    						{
    							//Ex: 123 NW Main...
    							aStreetDirection = StDirStNmStThoStSuf[index].toUpperCase();	
    							index ++;
    						}
    					}
    				} // (index+1) < ufAddrLen
    				else
    				{
    					if (aStreetName.length() == 0)
    					{
    						aStreetName = StDirStNmStThoStSuf[index];
    					}
    					else
    					{
    						aStreetName += " " + StDirStNmStThoStSuf[index];
    					}
    					index++;
    					// throw new AddressHandlerException ("Not a valid AddressLine, can not have StreetDirection without Street Name.");
    				}
    				
    				if (addSTName && index < ufAddrLen)
    				{
    					if (aStreetName.length() == 0)
    					{
    						aStreetName = StDirStNmStThoStSuf[index];
    					}
    					else
    					{
    						aStreetName += " " + StDirStNmStThoStSuf[index];
    					}
    					index ++;
    				}
    			} // end if (streetDirSufList.contains(StDirStNmStThoStSuf[index].toUpperCase()))
    			else 
    			{
    				aStreetName = StDirStNmStThoStSuf[index];
    				index ++;
    			}
    		} // index < ufAddrLen
    
    		parseStNmStThoStSuf (StDirStNmStThoStSuf, index, streetDirSufList, false);
    		
    	}
    
    	catch( Exception e )
    	{
    		// e.printStackTrace();
    		throw new AddressHandlerException (e.getMessage());
    	}
    }
    
    /**
     * Parses array of strings into StreetName, StreetThoroughfareVector and StreetNameSuffix.
     * Creation date: (08/31/01 12:00:00 PM)
     * @param StNmStThoStSuf [] String
     * @param index int
     * @param streetDirSufList ArrayList
     * @param stNmInd boolean
     * @exception AddressHandlerException
     */
    
    protected void parseStNmStThoStSuf (String [] StNmStThoStSuf, int index, ArrayList streetDirSufList, boolean stNmInd)
    throws AddressHandlerException
    {
    	ArrayList streetThoroughfareList = FieldedAddressList.getStreetThoroughfareList();
    
    	if (StNmStThoStSuf == null)
    		return;
    	int streetLen = StNmStThoStSuf.length;
    	if (streetLen <= 0)
    		return;
    		
    	try
    	{		
    		if (index < streetLen) 
    		{
    			if (stNmInd)
    			{
    				aStreetName = StNmStThoStSuf[index];
    				index ++;
    			}
    			while (index < streetLen) 
    			{
    				if (streetThoroughfareList.contains(StNmStThoStSuf[index].toUpperCase())) 
    				{
    					aStreetThoroughfare = StNmStThoStSuf[index].toUpperCase();
    					index ++;
    					while (index < streetLen && streetThoroughfareList.contains(StNmStThoStSuf[index].toUpperCase())) 
    					{
    						aStreetName += " " + StNmStThoStSuf[index-1];
    						aStreetThoroughfare = StNmStThoStSuf[index].toUpperCase();	
    						index ++;
    					}
    					if (index < streetLen) 
    					{
    						if (streetDirSufList.contains(StNmStThoStSuf[index].toUpperCase())) 
    						{
    							if ((index + 1) < streetLen)
    							{
    								aStreetName += " " + StNmStThoStSuf[index-1];
    								aStreetName += " " + StNmStThoStSuf[index];
    								aStreetThoroughfare = "";
    							}
    							else
    								aStreetNameSuffix = StNmStThoStSuf[index].toUpperCase();	
    							index ++;
    						}
    						else 
    						{
    							aStreetName += " " + StNmStThoStSuf[index-1];
    							aStreetName += " " + StNmStThoStSuf[index];
    							aStreetThoroughfare = "";
    							index++;
    						}								
    					}
    				}	
    				else if (streetDirSufList.contains(StNmStThoStSuf[index].toUpperCase())) 
    				{
    					if ((index + 1) < streetLen)
    						aStreetName += " " + StNmStThoStSuf[index];
    					else
    						aStreetNameSuffix = StNmStThoStSuf[index].toUpperCase();
    					index ++;
    					// rz if (index < streetLen) 
    					// rz throw new AddressHandlerException ("Not a valid AddressLine, can not have additional tokens after StreetNameSuffix.");
    				}
    				else 
    				{
    					aStreetName += " " + StNmStThoStSuf[index];
    					index ++;
    				}
    			} // while
    		} //index < streetLen
    		
    		// Exception to the rules: if street name is AVENUE and there is no street thoroughfare =>
    		// if street name suffix is populated it will be part of street name. For example: 123 Avenue S
    		// Avenue S will be street name.
    		if (aStreetName.equalsIgnoreCase("avenue") && aStreetThoroughfare.equals(""))
    		{
    			aStreetName = (aStreetName + " " + aStreetNameSuffix).trim();
    			aStreetNameSuffix = "";
    		}
    
    
    	} // try
    	catch( Exception e )
    	{
    		// e.printStackTrace();
    		throw new AddressHandlerException (e.getMessage());
    	}
    }
    
    /**
     * Parse AddressLine into 2 parts, StreetName and Living Units
     * Creation date: (11/09/06)
     * @param addressLine String
     * @param delimList String
     * @exception AddressHandlerException
     */
	protected void parseUFAddrPlusLU (String addressLine, String delimList)
    throws AddressHandlerException
	{
    	StringTokenizer tmp = new StringTokenizer(addressLine.trim(), delimList);
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
    			
    			if (FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[index]) ||
    				FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[index]) ||
    				FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[index]))
    			{
    				findSupplementalTypeKeyword = true;
    			}
    		
    			index++;
    		}
    		
    		if (!findSupplementalTypeKeyword)
    		{
    			//If no Supplemental Type found on the street name, just finish this function.
    			m_addressLineNoLU = addressLine;
    			return;
    		}
    		
    		for (int i = index - 1; i > -1; i--)
    		{
    			//Do backward search on thoroughfare
    			if (FieldedAddressList.getStreetThoroughfareList().contains(addressTokenUpperCase[i]))
    			{
    				if ((i == index - 1) ||
    					(i == index - 2 && FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[index - 1])))
    				{
    					//(Last token is thoroughfare) or (i-2 is thoroughfare and i-1 is street direction suffix) 
    					m_addressLineNoLU = addressLine;
    					return;
    				}
    				else
    				{
    					if (Character.isDigit(addressToken[0].charAt(0)))
    					{	
    						//Find HouseNumber. The thoroughfare has to start from 2.
    						if (FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[i + 1]))
    						{
    							//StreetNameSuffix is after Thoroughfare.
    							if (i < index - 3 && i > 1)
    							{
    								//Ex: 123 Main ST N APT A
    								if (FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i + 2]) ||
    							  		FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i + 2]) ||
    							  		FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i + 2]))
    								{
    									findThoroughfare = true;
    									ind_Thoroughfare = i + 1;
    									break;
    								}
    							}
    						}
    						else
    						{
    							//No StreetNameSuffix
    							if (i < index - 2 && i > 1)
    							{
    								//Ex: 123 Main ST APT A
    								if (FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i + 1]) ||
    							  		FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i + 1]) ||
    							  		FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i + 1]))
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
    						//No HouseNumber. The thoroughfare position allows starting from 1.
    						if ((i + 1 < index) && FieldedAddressList.getStreetDirSufList().contains(addressTokenUpperCase[i + 1]))
    						{
    							//Ex: Main ST N APT A
    							if (i < index - 3 && i > 0)
    							{
    								if (FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i + 2]) ||
    							  		FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i + 2]) ||
    							  		FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i + 2]))
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
    								//EX: Main ST APT A
    								if (FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i + 1]) ||
    							  		FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i + 1]) ||
    							  		FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i + 1]))
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
    			//Start parsing the supplemental data after the identified thoroughfare position. 
    			//Allow multiple tokens for supplemental value.
    			if (startPoint < index)
    			{
    				StringBuffer sb = new StringBuffer();
    				for (int i = startPoint; i < index; i++)
    				{
    					if (FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]))
    					{
    						findSupplementalLocation = true;
    						m_unitType = addressToken[i];
    						i++;
    						tempValue = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    					else if (FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]))
    					{
    						findSupplementalLocation = true;
    						m_levelType = addressToken[i];
    						i++;
    						tempValue = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    					else if (FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
    					{
    						findSupplementalLocation = true;
    						m_structType = addressToken[i];
    						i++;
    						tempValue = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    			//If no thoroughfare found, try to identify valid supplement type and value combination.
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
    				if (FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]))
    				{				
    					if (findSupplementalLocation)
    					{
    						//After identify valid supplemental type and value combination.
    						m_unitType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    							!FieldedAddressList.getUnitNameList().contains (addressTokenUpperCase[i + 1]) && 
    					 		!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i + 1]) &&
    					 		!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i + 1]))
    					{
    						findSupplementalLocation = true;
    						m_unitType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    				else if (FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]))
    				{
    					if (findSupplementalLocation)
    					{
    						//After identify valid supplemental type and value combination.
    						m_levelType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    					 		!FieldedAddressList.getUnitNameList().contains (addressTokenUpperCase[i + 1]) &&
    					 		!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i + 1]) &&
    					 		!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i + 1]))
    					{
    						findSupplementalLocation = true;
    						m_levelType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    				else if (FieldedAddressList.getStructureNameList().contains (addressTokenUpperCase[i]))
    				{
    					if (findSupplementalLocation)
    					{
    						//After identify valid supplemental type and value combination.
    						m_structType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
    							!FieldedAddressList.getUnitNameList().contains (addressTokenUpperCase[i + 1]) &&
    							!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i + 1]) &&
    							!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i + 1]))
    					{
    						findSupplementalLocation = true;
    						m_structType = addressToken[i];
    						i++;
    						StringBuffer sb = new StringBuffer();
    						while (i < index)
    						{
    							if (!FieldedAddressList.getUnitNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getLevelNameList().contains(addressTokenUpperCase[i]) &&
    								!FieldedAddressList.getStructureNameList().contains(addressTokenUpperCase[i]))
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
     * Parse an unfielded address line into fielded address.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param ufAddr String
     * @exception AddressHandlerException
     */
    
    protected void parseUFAddr (String ufAddr, ArrayList streetDirSufList)
    throws AddressHandlerException
    {
    	// rz Vector streetNumberSuffixVector = FieldedAddressVector.getStreetNumberSuffixVector();
    	// rz ArrayList streetDirSufList = FieldedAddressList.getStreetDirSufList();
    
    	try
    	{
    
    		String[] item = stringToTokens( ufAddr );
    
    		if ( item == null )
    			return;
    		
    	 	int index = 0;
    		int dashIndex = -1;
    		boolean suffixNum = false;
    
    		/* We assume that if the first token is house number it must start with digit. */
    		if (Character.isDigit (item[index].charAt(0)))
    			dashIndex = item[index].indexOf('-');
    		
    		// if (dashIndex == 0 && Character.isDigit (item[index].charAt(0)))
    		// throw new AddressHandlerException ("Not a valid AddressLine, house number can not starts with '-'.");
    
    		/*
    		 * We need to find if item[index] is HouseNumber only or HouseNumber and HouseNumberSuffix.
    		 *   If item[index] does not have '-' it is HouseNumber only.
    		 *   If the characters after the '-' are only digits it is HouseNumber only. 
    		 *   Else it is HouseNumber and HouseNumberSuffix.
    		 */
    
    		if (dashIndex > -1 && Character.isDigit (item[index].charAt(0)))
    		{
    	 		for (int i = dashIndex+1; i < item[index].length(); i++)
    	 		{
    	 			if (!Character.isDigit (item[index].charAt(i)))
    	 			{
    		 			suffixNum = true;
    		 			break;
    	 			}
    	 		}
    	 		/*
    	 		 * aHouseNumberSuffix should not have a dash in it (ie, skip the dash).
    	 		 */
    	 		if (suffixNum)
    	 		{
    		 		if (dashIndex > 0)
    					aHouseNumber = item[index].substring(0,dashIndex);
    				aHouseNumberSuffix = item[index].substring(dashIndex+1, item[index].length());
    		 		index++;
    	 		}
    		}
    		if ((dashIndex == -1 || !suffixNum) && Character.isDigit (item[index].charAt(0)))
    		{
    	 		aHouseNumber = item[index];
    	 		index++;
    		}
    
    		parseStDirStNmStThoStSuf (item, index, streetDirSufList);
    		
    	}
    
    	catch( Exception e )
    	{
    		// e.printStackTrace();
    		throw new AddressHandlerException (e.getMessage());
    	}
    }
    /**
     * Given array of units of type Unit, will create String elements, type and value for each item in the array. 
     * Creation date: (5/3/01 12:20:56 PM)
     * @param currUnit Unit[]
     * @param ah AddressHandler
     */
    
    public void parseUnit( AddressHandler ah ) 
    {
        if(ah.m_structType != null)
        {
        	ah.m_structType = ah.m_structType.trim();
        }

        if(ah.m_structValue != null)
        {
            if ( ah.m_structValue.length() > 10 ) {
                ah.m_structValue = ah.m_structValue.substring(0,10).trim();
            } else {
                ah.m_structValue = ah.m_structValue.trim();
            }
        }
        
        if(ah.m_levelType != null)
        {
            ah.m_levelType = ah.m_levelType.trim();
        }
        
        if(ah.m_levelValue != null )
        {
            if ( ah.m_levelValue.length() > 10 ) {
                ah.m_levelValue = ah.m_levelValue.substring(0,10).trim();
            } else {
                ah.m_levelValue = ah.m_levelValue.trim();
            }
        }

        if(ah.m_unitType != null)
        {
        	ah.m_unitType = ah.m_unitType.trim();
        }
        
        if(ah.m_unitValue != null)
        {
            if ( ah.m_unitValue.length() > 10 ) {
                ah.m_unitValue = ah.m_unitValue.substring(0,10).trim();
            } else {
                ah.m_unitValue = ah.m_unitValue.trim();
            }
        }
    
    	return;
    }

    /**
     * Parse the ZIP into 2 elements: PostalCode and PostalCode+4
     * @param zip String
     */
    
    public void parseZIP( String zip ) 
    {
    	if (zip != null)
    	{
    		zip = zip.trim();
    		if (zip.length() > 5)
    		{
    			aPostalCode = zip.substring(0,5);
    			if (zip.substring(5,6).equals("-"))
    				m_postalCodePlus4 = zip.substring (6, Math.min(10, zip.length()));
    			else
    				m_postalCodePlus4 = zip.substring (5, Math.min(9, zip.length()));
    		}
    		else
    			aPostalCode = zip;
       } 
    	return;
    }

    /**
     * Set City.
     * Creation date: (12/11/01 9:42:04 AM)
     * @param city String
     * @see #getCity
     */
    public void setCity (String city) 
    {
    	if (city != null)
    	{
    		aCity = city;
    		
    		/**
    		 * Data after the "/" is not considered as part of a city name.
    		 */
    		int j = city.indexOf ('/');
    		if ( j > 0 )
    			aCity = city.substring (0, j);
    				
    		switch (theAddress.discriminator().value())
    		{
    			case AddressChoice._UNFIELDED_ADDRESS:
    				theAddress.aUnfieldedAddress().aCity.theValue( aCity );
    				break;
    				
    			case AddressChoice._FIELDED_ADDRESS:
    				theAddress.aFieldedAddress().aCity.theValue( aCity );
    				break;
    		}
    	}
    }
    
    /**
     * Set HouseNumberSuffix.
     * Creation date: (5/30/02 10:49:58 PM)
     * @param aHouseNbrSfx String
     * @see #getHousNbrSfx
     */
    public void setHousNbrSfx (String aHouseNbrSfx) 
    {
    	if (aHouseNbrSfx != null)
    	{
    		aHouseNumberSuffix = aHouseNbrSfx;
    		
    		switch (theAddress.discriminator().value())
    		{
    			case AddressChoice._FIELDED_ADDRESS:
    				theAddress.aFieldedAddress().aHouseNumberSuffix.theValue( aHouseNbrSfx );
    				break;
    		}		
    	}
    }
    /**
     * Set setAddAddrInfo
     * Creation date: (01/16/04 9:42:04 AM)
     * @param addAddrInfo String
     */
    public void setAddAddrInfo (String addAddrInfo) 
    {
    	if (addAddrInfo != null)
    	{
    		aAdditionalAddressInformation = addAddrInfo;
    		switch (theAddress.discriminator().value())
    		{
    			case AddressChoice._UNFIELDED_ADDRESS:
    				theAddress.aUnfieldedAddress().aAdditionalInfo.theValue( addAddrInfo );
    				break;
    				
    			case AddressChoice._FIELDED_ADDRESS:
    				theAddress.aFieldedAddress().aAdditionalInfo.theValue( addAddrInfo );
    				break;
    		}
    	
    	}
    }
    /**
     * Set OrginalStreetDirection.
     * Creation date: (4/14/04 9:42:04 AM)
     * @param streetDirection String
     */
    public void setOriginalStreetDirection(String streetDirection) 
    {
        if (streetDirection != null)
        {
            m_originalStreetDirection = streetDirection;
            
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aOriginalStreetDirection.theValue( m_originalStreetDirection );
                    break;
            }
        }
    }
    
    /**
     * Set OriginalStreetNameSuffix.
     * Creation date: (4/14/04 9:43:04 AM)
     * @param streetNameSuffix String
     */
    public void setOriginalStreetNameSuffix(String streetNameSuffix) 
    {
        if (streetNameSuffix != null)
        {
            m_originalStreetNameSuffix = streetNameSuffix;
            
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aOriginalStreetNameSuffix.theValue( m_originalStreetNameSuffix );
                    break;
            }
        }
    }

    /**
     * Set Postal Code.
     * Creation date: (5/24/01 9:42:04 AM)
     * @param postalCode String
     * @see #getPostalCode
     */
    public void setPostalCode(String postalCode) 
    {
    	if (postalCode != null)
    	{
    		aPostalCode = postalCode;
    		
    		switch (theAddress.discriminator().value())
    		{
    			case AddressChoice._UNFIELDED_ADDRESS:
    				theAddress.aUnfieldedAddress().aPostalCode.theValue( aPostalCode );
    				break;
    				
    			case AddressChoice._FIELDED_ADDRESS:
    				theAddress.aFieldedAddress().aPostalCode.theValue( aPostalCode );
    				break;
    		}
    	}
    }
    /**
     * Set State.
     * Creation date: (7/13/01 9:42:04 AM)
     * @param state String
     * @see #getState
     */
    public void setState(String state) 
    {
    	if (state != null)
    	{
    		aState = state;
    		switch (theAddress.discriminator().value())
    		{
    			case AddressChoice._UNFIELDED_ADDRESS:
    				theAddress.aUnfieldedAddress().aState.theValue( aState );
    				break;
    				
    			case AddressChoice._FIELDED_ADDRESS:
    				theAddress.aFieldedAddress().aState.theValue( aState );
    				break;
    		}
    	
    	}
    }
    /**
     * Set the street name.
     * @param StName String
     * @see #getStName
     */
    public void setStName(String StName) 
    {
    	if (StName != null)
    	{
    		aStreetName = StName;
    		
    		switch (theAddress.discriminator().value())
    		{
    			case AddressChoice._FIELDED_ADDRESS:
    				theAddress.aFieldedAddress().aStreetName.theValue( aStreetName );
    				break;
    			
    		}
    	}
    }
    
    
    /**
     * Converts a String delimited by spaces into an array of Strings, excluding the spaces.
     * Creation date: (8/31/01 12:00:00 PM)
     * @return String[]
     * @param str String
     */
    protected String[] stringToTokens(String str) 
    {
    
    	StringTokenizer str_tmp = new java.util.StringTokenizer (str, " ");
    	int tokenCount = str_tmp.countTokens();
    	
    	if ( tokenCount == 0 )
    		return null;
    
    	String[] item = new String[tokenCount];
    	int counter = 0;
    	while ( str_tmp.hasMoreTokens() ){
    		item[counter] = str_tmp.nextToken();
    		counter++;
    	}
    
    	return item;	
    }
    /**
     * Create AddressOpt type from an Address Object.
     * Creation date: (5/3/01 12:20:56 PM)
     * @return AddressOpt
     */
    
    public AddressOpt toAddressOpt ()
    {
    	return (AddressOpt) IDLUtil.toOpt (AddressOpt.class, getAddress());
    }
    /**
     * Create a String in order to display the Address Object.
     * Creation date: (5/3/01 12:20:56 PM)
     * @return String
     */
    
    public String toString()
    {
    	StringBuffer sb = new StringBuffer("AddressData[ ");
    	
    	sb.append(nl + "Route=[" + aRoute + "]");
    	sb.append(nl + "Box=[" + aBox + "]");
    	sb.append(nl + "HouseNumberPrefix=[" + aHouseNumberPrefix + "]");
    	sb.append(nl + "HouseNumber=[" + aHouseNumber + "]");
    	sb.append(nl + "AssignedHouseNumber=[" + aAssignedHouseNumber + "]");
    	sb.append(nl + "HouseNumberSuffix=[" + aHouseNumberSuffix + "]");
    	sb.append(nl + "StreetDirection=[" + aStreetDirection + "]");
    	sb.append(nl + "StreetName=[" + aStreetName + "]");
    	sb.append(nl + "StreetThoroughfare=[" + aStreetThoroughfare + "]");
    	sb.append(nl + "StreetNameSuffix=[" + aStreetNameSuffix + "]");
    	sb.append(nl + "City=[" + aCity + "]");
    	sb.append(nl + "State=[" + aState + "]");
    	sb.append(nl + "PostalCode=[" + aPostalCode + "]");
    	sb.append(nl + "County=[" + aCounty + "]");
    	sb.append(nl + "Country=[" + aCountry + "]");
    	sb.append(nl + "AdditionalInfo=[" + aAdditionalAddressInformation + "]");
        sb.append(nl + "StructureType=[" + m_structType + "]");
        sb.append(nl + "StructureValue=[" + m_structValue + "]");
        sb.append(nl + "LevelType=[" + m_levelType + "]");
        sb.append(nl + "LevelValue=[" + m_levelValue + "]");
        sb.append(nl + "UnitType=[" + m_unitType + "]");
        sb.append(nl + "UnitValue=[" + m_unitValue + "]");
        sb.append(nl + "PostalCodePlus4=[" + m_postalCodePlus4 + "]");
        sb.append(nl + "OriginalStreetDirection=[" + m_originalStreetDirection + "]");
        sb.append(nl + "OriginalStreetNameSuffix=[" + m_originalStreetNameSuffix + "]");
        
        if ( m_cassAddressLines != null && m_cassAddressLines.length > 0 ) {
            for ( int i = 0 ; i < m_cassAddressLines.length ; i++ ) {
                sb.append(nl + "CassAddressLine=[" + m_cassAddressLines[i] + "]");
            }
        } else {
            sb.append(nl + "CassAddressLine=[]");
        }
        
        sb.append(nl + "CassAdditionalInfo=[" + m_cassAdditionalInfo + "]");
        
        if ( m_auxiliaryAddressLines != null && m_auxiliaryAddressLines.length > 0 ) {
            for ( int i = 0 ; i < m_auxiliaryAddressLines.length ; i++ ) {
                sb.append(nl + "AuxiliaryAddressLine=[" + m_auxiliaryAddressLines[i] + "]");
            }
        } else {
            sb.append(nl + "AuxiliaryAddressLine=[]");
        }

        sb.append(nl + "BusinessName=[" + m_businessName + "] ]");
    
    	return sb.toString();
    }

    /**
     * Returns the auxiliaryAddressLines.
     * @return String[]
     */
    public String[] getAuxiliaryAddressLines()
    {
        return m_auxiliaryAddressLines;
    }

    /**
     * Returns the businessName.
     * @return String
     */
    public String getBusinessName()
    {
        return trimString(m_businessName);
    }

    /**
     * Returns the cassAdditionalInfo.
     * @return String
     */
    public String getCassAdditionalInfo()
    {
        return trimString(m_cassAdditionalInfo);
    }

    /**
     * Returns the cassAddressLines.
     * @return String[]
     */
    public String[] getCassAddressLines()
    {
        return m_cassAddressLines;
    }

    /**
     * Sets the auxiliaryAddressLines.
     * @param auxiliaryAddressLines The auxiliaryAddressLines to set
     */
    public void setAuxiliaryAddressLines(String[] auxiliaryAddressLines)
    {
        if (auxiliaryAddressLines != null)
        {
            m_auxiliaryAddressLines = auxiliaryAddressLines;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aAuxiliaryAddressLines.theValue( m_auxiliaryAddressLines );
                    break;
            }
        
        }
    }

    /**
     * Sets the businessName.
     * @param businessName The businessName to set
     */
    public void setBusinessName(String businessName)
    {
        if (businessName != null)
        {
            m_businessName = businessName;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aBusinessName.theValue( m_businessName );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aBusinessName.theValue( m_businessName );
                    break;
            }
        
        }
    }

    /**
     * Sets the cassAdditionalInfo.
     * @param cassAdditionalInfo The cassAdditionalInfo to set
     */
    public void setCassAdditionalInfo(String cassAdditionalInfo)
    {
        if (cassAdditionalInfo != null)
        {
            m_cassAdditionalInfo = cassAdditionalInfo;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aCassAdditionalInfo.theValue( m_cassAdditionalInfo );
                    break;
            }
        
        }
    }

    /**
     * Sets the cassAddressLines.
     * @param cassAddressLines The cassAddressLines to set
     */
    public void setCassAddressLines(String[] cassAddressLines)
    {
        if (cassAddressLines != null)
        {
            m_cassAddressLines = cassAddressLines;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aCassAddressLines.theValue( m_cassAddressLines );
                    break;
            }
        
        }
    }

    /**
     * Returns the postalCodePlus4.
     * @return String
     */
    public String getPostalCodePlus4()
    {
        return trimString(m_postalCodePlus4);
    }

    /**
     * Sets the postalCodePlus4.
     * @param postalCodePlus4 The postalCodePlus4 to set
     */
    public void setPostalCodePlus4(String postalCodePlus4)
    {
        if (postalCodePlus4 != null)
        {
            m_postalCodePlus4 = postalCodePlus4;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aPostalCodePlus4.theValue( m_postalCodePlus4 );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aPostalCodePlus4.theValue( m_postalCodePlus4 );
                    break;
            }
        
        }
    }

    /**
     * Sets the box.
     * @param box The box to set
     */
    public void setBox(String box)
    {
        if (box != null)
        {
            aBox = box;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aBox.theValue( box );
                    break;
            }
        
        }
    }

    /**
     * Sets the country.
     * @param country The country to set
     */
    public void setCountry(String country)
    {
        if (country != null)
        {
            aCountry = country;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aCountry.theValue( aCountry );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aCountry.theValue( aCountry );
                    break;
            }
        
        }
    }

    /**
     * Sets the county.
     * @param county The county to set
     */
    public void setCounty(String county)
    {
        if (county != null)
        {
            aCounty = county;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aCounty.theValue( aCounty );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aCounty.theValue( aCounty );
                    break;
            }
        
        }
    }

    /**
     * Sets the houseNumber.
     * @param houseNumber The houseNumber to set
     */
    public void setHouseNumber(String houseNumber)
    {
        if (houseNumber != null)
        {
            aHouseNumber = houseNumber;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aHouseNumber.theValue( aHouseNumber );
                    break;
            }
        
        }
    }

    /**
     * Sets the houseNumberPrefix.
     * @param houseNumberPrefix The houseNumberPrefix to set
     */
    public void setHouseNumberPrefix(String houseNumberPrefix)
    {
        if (houseNumberPrefix != null)
        {
            aHouseNumberPrefix = houseNumberPrefix;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aHouseNumberPrefix.theValue( aHouseNumberPrefix );
                    break;
            }
        
        }
    }

    /**
     * Sets the route.
     * @param route The route to set
     */
    public void setRoute(String route)
    {
        if (route != null)
        {
            aRoute = route;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aRoute.theValue( aRoute );
                    break;
            }
        
        }
    }

    /**
     * Sets the streetDirection.
     * @param streetDirection The streetDirection to set
     */
    public void setStreetDirection(String streetDirection)
    {
        if (streetDirection != null)
        {
            aStreetDirection = streetDirection;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aStreetDirection.theValue( aStreetDirection );
                    break;
            }
        
        }
    }

    /**
     * Sets the streetNameSuffix.
     * @param streetNameSuffix The streetNameSuffix to set
     */
    public void setStreetNameSuffix(String streetNameSuffix)
    {
        if (streetNameSuffix != null)
        {
            aStreetNameSuffix = streetNameSuffix;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aStreetNameSuffix.theValue( aStreetNameSuffix );
                    break;
            }
        
        }
    }

    /**
     * Sets the streetThoroughfare.
     * @param streetThoroughfare The streetThoroughfare to set
     */
    public void setStreetThoroughfare(String streetThoroughfare)
    {
        if (streetThoroughfare != null)
        {
            aStreetThoroughfare = streetThoroughfare;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aStreetThoroughfare.theValue( aStreetThoroughfare );
                    break;
            }
        
        }
    }

    /**
     * Sets the levelType.
     * @param levelType The levelType to set
     */
    public void setLevelType(String levelType)
    {
        if (levelType != null)
        {
            m_levelType = levelType;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aLevelType.theValue( m_levelType );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aLevelType.theValue( m_levelType );
                    break;
            }
        
        }
    }

    /**
     * Sets the levelValue.
     * @param levelValue The levelValue to set
     */
    public void setLevelValue(String levelValue)
    {
        if (levelValue != null)
        {
            m_levelValue = levelValue;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aLevelValue.theValue( m_levelValue );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aLevelValue.theValue( m_levelValue );
                    break;
            }
        
        }
    }

    /**
     * Sets the structType.
     * @param structType The structType to set
     */
    public void setStructType(String structType)
    {
        if (structType != null)
        {
            m_structType = structType;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aStructureType.theValue( m_structType );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aStructureType.theValue( m_structType );
                    break;
            }
        
        }
    }

    /**
     * Sets the structValue.
     * @param structValue The structValue to set
     */
    public void setStructValue(String structValue)
    {
        if (structValue != null)
        {
            m_structValue = structValue;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aStructureValue.theValue( m_structValue );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aStructureValue.theValue( m_structValue );
                    break;
            }
        
        }
    }

    /**
     * Sets the unitType.
     * @param unitType The unitType to set
     */
    public void setUnitType(String unitType)
    {
        if (unitType != null)
        {
            m_unitType = unitType;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aUnitType.theValue( m_unitType );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aUnitType.theValue( m_unitType );
                    break;
            }
        
        }
    }

    /**
     * Sets the unitValue.
     * @param unitValue The unitValue to set
     */
    public void setUnitValue(String unitValue)
    {
        if (unitValue != null)
        {
            m_unitValue = unitValue;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aUnitValue.theValue( m_unitValue );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aUnitValue.theValue( m_unitValue );
                    break;
            }
        
        }
    }

    /**
     * Sets the assignedHouseNumber.
     * @param assignedHouseNumber The assignedHouseNumber to set
     */
    public void setAssignedHouseNumber(String assignedHouseNumber)
    {
        if (assignedHouseNumber != null)
        {
            aAssignedHouseNumber = assignedHouseNumber;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aAssignedHouseNumber.theValue( aAssignedHouseNumber );
                    break;
            }
        
        }
    }

    /**
     * Returns the additionalAddressInformation.
     * @return String
     */
    public String getAdditionalInfo()
    {
        return trimString(aAdditionalAddressInformation);
    }

    /**
     * Returns the assignedHouseNumber.
     * @return String
     */
    public String getAssignedHouseNumber()
    {
        return trimString(aAssignedHouseNumber);
    }

    /**
     * Returns the houseNumber.
     * @return String
     */
    public String getHouseNumber()
    {
        return trimString(aHouseNumber);
    }

    /**
     * Returns the houseNumberPrefix.
     * @return String
     */
    public String getHouseNumberPrefix()
    {
        return trimString(aHouseNumberPrefix);
    }

    /**
     * Returns the houseNumberSuffix.
     * @return String
     */
    public String getHouseNumberSuffix()
    {
        return trimString(aHouseNumberSuffix);
    }

    /**
     * Returns the streetDirection.
     * @return String
     */
    public String getStreetDirection()
    {
        return trimString(aStreetDirection);
    }

    /**
     * Returns the streetNameSuffix.
     * @return String
     */
    public String getStreetNameSuffix()
    {
        return trimString(aStreetNameSuffix);
    }

    /**
     * Returns the streetThoroughfare.
     * @return String
     */
    public String getStreetThoroughfare()
    {
        return trimString(aStreetThoroughfare);
    }

    /**
     * Sets the additionalAddressInformation.
     * @param additionalAddressInformation The additionalAddressInformation to set
     */
    public void setAdditionalInfo(String additionalInfo)
    {
        if (additionalInfo != null)
        {
            aAdditionalAddressInformation = additionalInfo;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aAdditionalInfo.theValue( additionalInfo );
                    break;
            }
        
        }
    }

    /**
     * Sets the houseNumberSuffix.
     * @param houseNumberSuffix The houseNumberSuffix to set
     */
    public void setHouseNumberSuffix(String houseNumberSuffix)
    {
        if (houseNumberSuffix != null)
        {
            aHouseNumberSuffix = houseNumberSuffix;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aHouseNumberSuffix.theValue( aHouseNumberSuffix );
                    break;
            }
        
        }
    }

    /**
     * Sets the streetName.
     * @param streetName The streetName to set
     */
    public void setStreetName(String streetName)
    {
        if (streetName != null)
        {
            aStreetName = streetName;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aStreetName.theValue( aStreetName );
                    break;
            }
        
        }
    }
    
    /**
     * Sets the m_addressId.
     * @param m_addressId The m_addressId to set
     */
    public void setAddressId(String addressId)
    {
        if (addressId != null)
        {
            m_addressId = addressId;
            switch (theAddress.discriminator().value())
            {
                case AddressChoice._UNFIELDED_ADDRESS:
                    theAddress.aUnfieldedAddress().aAddressId.theValue( m_addressId );
                    break;
                    
                case AddressChoice._FIELDED_ADDRESS:
                    theAddress.aFieldedAddress().aAddressId.theValue( m_addressId );
                    break;
            }
        }       
    }

    /**
     * Method trimString checks if the string to trim is null.  If the string
     * is a null reference then return it, otherwise return the trimmed string.
     * @param stringToTrim
     * @return String
     */
    protected String trimString( String stringToTrim ) {
        if ( stringToTrim != null )
        {
            return stringToTrim.trim();
        }
        else
        {
            return stringToTrim;
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
     * returns StreetAddress which concatenates all address fileds into one line.
     * @return String
     */
    public String getStreetAddress ()
    {
    	String streetAddress = "";
    	    
    	if (aHouseNumberPrefix != null)
    		if (!aHouseNumberPrefix.trim().equals(""))
    		{
    			streetAddress += aHouseNumberPrefix.trim();
    			streetAddress += " ";
    		}
    		
    	if (aHouseNumber != null)
    		if (!aHouseNumber.trim().equals(""))
    		{
    			streetAddress += aHouseNumber.trim();
    			streetAddress += " ";
    		}
    		
    	if (aHouseNumberSuffix != null)
    		if (!aHouseNumberSuffix.trim().equals(""))
    		{
    			streetAddress = streetAddress.trim ();
    			if (aHouseNumberSuffix.trim().startsWith ("-"))
    				streetAddress += aHouseNumberSuffix.trim();
    			else
    				streetAddress =streetAddress + "-" + aHouseNumberSuffix.trim();
    			streetAddress += " ";
    		}
    				    		
    	if (aStreetDirection != null)
    		if (!aStreetDirection.trim().equals(""))
    		{
    			streetAddress += aStreetDirection.trim();
    			streetAddress += " ";
    		}
    
    	if (aStreetName != null)
    		if (!aStreetName.trim().equals(""))
    		{
     			streetAddress += aStreetName.trim();
    			streetAddress += " ";
    		}
    			
    	if (aStreetThoroughfare != null)
    		if (!aStreetThoroughfare.trim().equals(""))
    		{
    			streetAddress += aStreetThoroughfare.trim();
    			streetAddress += " ";
    		}
    
    	if (aStreetNameSuffix != null)
    		if (!aStreetNameSuffix.trim().equals(""))
    		{
   				streetAddress += aStreetNameSuffix.trim();
    		}
    
    	streetAddress = streetAddress.trim();
    	
    	return streetAddress;
    }
    

}
