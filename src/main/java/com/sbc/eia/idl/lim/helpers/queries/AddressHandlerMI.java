// $Id: AddressHandlerMI.java,v 1.4 2003/09/12 19:19:37 rz7367 Exp $

package com.sbc.eia.idl.lim.helpers.queries;

import java.sql.SQLException;
import java.util.*;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.FieldedAddressList;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;

/**
 * AddressHandlerMI is derived from AddressHandler, it creates Address Object for MI PB addresses.
 * Creation date: (5/3/01 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */
public class AddressHandlerMI extends AddressHandler {
    
    /**
     * AddressHandlerMI constructor.
     */
    public AddressHandlerMI() {
    	super();
    }
    
    /**
     * Parses MI String address and creates Address Object.
     * Creation date: (6/11/01 12:20:56 PM)
     * @param address String
     * @exception AddressHandlerException
     */
    public AddressHandlerMI (String address, Properties properties, Logger myLogger)
    throws AddressHandlerException
    {
    	theLogger = myLogger;
    	
    	if (address == null || address.equals (""))
    		return;
    
    	address = address.trim ();
    	int currPos = -1;
    	int ij = -1;
    
    	// Find Street Number
    	//
    	if (Character.isDigit (address.charAt(0)))
    	{
    		for (ij = 0; ij < address.length (); ij++)
    		{
    			if (address.charAt(ij) == ' ' || address.charAt(ij) == '#')
    			{
    				currPos = ij;
    				break;
    			}
    			aHouseNumber += address.substring (ij,ij+1);
    			currPos = ij;
    		}
    	}
    
    	// Find Street Number Suffix
    	//
    	if (currPos > -1)
    	{
    		if (address.charAt(currPos) == ' ')
    		{
    			for (ij = currPos+1; ij < address.length (); ij++)
    			{
    				if (address.charAt(ij) == '#')
    				{
    					currPos = ij;
    					break;
    				}
    				aHouseNumberSuffix += address.substring (ij,ij+1);
    				currPos = ij;
    			}
    			aHouseNumberSuffix = aHouseNumberSuffix.trim ();
    		}
    	}
    
    	// Find Street Name
    	//
    	if (currPos == -1)
    	{
    		if (address.charAt (0) == '#')
    			currPos = 0;
    	}
    	
    	String street = "";
    	for (ij = currPos+1; ij < address.length (); ij++)
    	{
    		if (address.charAt(ij) == ',')
    		{
    			currPos = ij;
    			break;
    		}
    		street += address.substring (ij,ij+1);
    		currPos = ij;
    	}
    	
    	street = street.trim ();
    	String [] item = stringToTokens (street);
    	if (item != null)
    		parseStDirStNmStThoStSuf (item, 0, FieldedAddressList.getStreetDirSufList());
    	
    	// Find Community Name Abbreviation.
    	//
    	if (currPos == -1)
    	{
    		if (address.charAt (0) == ',')
    			currPos = 0;
    	}
    	
    	for (ij = currPos+1; ij < address.length (); ij++)
    	{
    		if (address.charAt(ij) == '(')
    		{
    			currPos = ij;
    			break;
    		}
    		aCity += address.substring (ij,ij+1);
    		currPos = ij;
    	}
    	aCity = aCity.trim ();
    
    	// Find the loc data or ANH
    	//
    	if (currPos == -1)
    	{
    		if (address.charAt (0) == '(')
    			currPos = 0;
    	}
    	
    	String loc = "";
    	for (ij = currPos+1; ij < address.length (); ij++)
    	{
    		if (address.charAt(ij) == ')')
    		{
    			currPos = ij;
    			break;
    		}
    		loc += address.substring (ij,ij+1);
    		currPos = ij;
    	}
    	loc = loc.trim ();
    
    	ArrayList unitStructureNameList = FieldedAddressList.getStructureNameList();
    	ArrayList unitLevelNameList = FieldedAddressList.getLevelNameList();
    	ArrayList unitUnitNameList = FieldedAddressList.getUnitNameList();
    	
    	if (loc != null)
    	{
    		// If the different living units are not seperated by "," => add comma.
    		//
    		String lu_s = "";
    		if (loc.indexOf (",") == -1)
    		{
    			StringTokenizer lu_tmp = new java.util.StringTokenizer (loc.trim(), " ");
    			if (lu_tmp.countTokens() > 2)
    			{
    				boolean start = true;
    				while (lu_tmp.hasMoreTokens())
    				{
    					if (!start)
    					{
    						String tmp_s = lu_tmp.nextToken ();
    						if (unitStructureNameList.contains (tmp_s) ||
    							unitLevelNameList.contains (tmp_s) ||
    							unitUnitNameList.contains (tmp_s))
    							lu_s = lu_s + "," + tmp_s;
    						else
    							lu_s = lu_s + " " + tmp_s;
    					}
    					else
    					{
    						lu_s = lu_s + " " + lu_tmp.nextToken ();
    						start = false;
    					}
    				}
    				lu_s = lu_s.trim();
    			}
    			else
    				lu_s = loc;
    		}
    		else
    			lu_s = loc;
    	
    		handleUnit (lu_s, ",");
    	}
    
    	try
    	{
    		if (aState == null || aState.equals(""))
    		{
    			if (aPostalCode != null)
    				if (!aPostalCode.equals (""))
    					aState = RetrieveStateCodeByZip.retrieveStateCodeByZip (properties, aPostalCode, theLogger);
    		}
    	}
    	catch( SQLException e )
    	{
    		if ( e.getMessage().indexOf("Exhausted Resultset") >= 0)
    		{
    			theLogger.log (LogEventId.FAILURE, "RetrieveStateCodeByZip-Exhausted Resultset: " + e.getMessage());			
    		}
    		else
    		{
    			theLogger.log (LogEventId.FAILURE, "RetrieveStateCodeByZip-SQL ERROR: " + e.getMessage());
    		}
    	}
    	initializeFieldedAddress();
    }
    
    /**
     * Parses MI String addresses and creates Address Object.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param stAddrAndCity String
     * @param livingUnit String
     * @param zip String
     * @exception AddressHandlerException
     */
    public AddressHandlerMI (String stAddrAndCity, String livingUnit, String zip, Properties properties, Logger myLogger)
    throws AddressHandlerException
    {
    	theLogger = myLogger;
    	
    	String s_tmp = "";
    	if (stAddrAndCity != null)
    	{
    		s_tmp = stAddrAndCity.trim ();
    		if (stAddrAndCity.startsWith("(OMIT)"))
    		{
    			int ij = stAddrAndCity.indexOf(")");
    			s_tmp = stAddrAndCity.substring (ij+1, stAddrAndCity.length ());
    		}
    	}
    	
    	String s = "";
    	StringTokenizer tmp = new java.util.StringTokenizer (s_tmp.trim(), "/", true);
    	if (tmp.hasMoreTokens())
    	{
    		s = tmp.nextToken ();
    		if (!s.equals ("/"))
    		{
    	 		aHouseNumber = s;
    		 	if (tmp.hasMoreTokens())
    		 		s = tmp.nextToken ();
    		}
    	 	if (tmp.hasMoreTokens())
    	 	{
    		 	s = tmp.nextToken ();
    		 	if (!s.equals ("/"))
    		 	{
    		 		aStreetDirection = s;
    		 		if (tmp.hasMoreTokens())
    		 			s = tmp.nextToken ();
    		 	}	
    		 	if (tmp.hasMoreTokens())
    		 	{
    		 		s = tmp.nextToken ();
    		 		if (!s.equals ("/"))
    		 		{
    			 		String street = s.trim();
    			 		String [] item = stringToTokens (street);
    			 		if (item != null)
    			 			parseStNmStThoStSuf (item, 0, FieldedAddressList.getStreetDirSufList(), true);
    		 			if (tmp.hasMoreTokens())
    		 				s = tmp.nextToken ();
    		 		}
    			 	if (tmp.hasMoreTokens())
    		 			aCity = tmp.nextToken ();
    		 	}
    	 	}
    	}
    
    	ArrayList unitStructureNameList = FieldedAddressList.getStructureNameList();
    	ArrayList unitLevelNameList = FieldedAddressList.getLevelNameList();
    	ArrayList unitUnitNameList = FieldedAddressList.getUnitNameList();
    	if (livingUnit != null)
    	{
    		String s_unit = "";
    		if (livingUnit.trim().startsWith ("("))
    			s_unit = livingUnit.trim().substring (1, livingUnit.length ()-1);
    		else
    			s_unit = livingUnit;	
    
    		// If the different living units are not seperated by "," => add comma.
    		//
    		String lu_s = "";
    		if (s_unit.indexOf (",") == -1)
    		{
    			StringTokenizer lu_tmp = new java.util.StringTokenizer (s_unit.trim(), " ");
    			if (lu_tmp.countTokens() > 2)
    			{
    				boolean start = true;
    				while (lu_tmp.hasMoreTokens())
    				{
    					if (!start)
    					{
    						String tmp_s = lu_tmp.nextToken ();
    						if (unitStructureNameList.contains (tmp_s) ||
    							unitLevelNameList.contains (tmp_s) ||
    							unitUnitNameList.contains (tmp_s))
    							lu_s = lu_s + "," + tmp_s;
    						else
    							lu_s = lu_s + " " + tmp_s;
    					}
    					else
    					{
    						lu_s = lu_s + " " + lu_tmp.nextToken ();
    						start = false;
    					}
    				}
    				lu_s = lu_s.trim();
    			}
    			else
    				lu_s = s_unit;
    		}
    		else
    			lu_s = s_unit;
    	
    		handleUnit (lu_s, ",");
    	}
    	
    	parseZIP (zip);
    
    	try
    	{
    		if (aState == null || aState.equals(""))
    		{
    			if (aPostalCode != null)
    				if (!aPostalCode.equals (""))
    					aState = RetrieveStateCodeByZip.retrieveStateCodeByZip (properties, aPostalCode, theLogger);
    		}
    	}
    	catch( SQLException e )
    	{
    		if ( e.getMessage().indexOf("Exhausted Resultset") >= 0)
    		{
    			theLogger.log (LogEventId.FAILURE, "RetrieveStateCodeByZip-Exhausted Resultset: " + e.getMessage());			
    		}
    		else
    		{
    			theLogger.log (LogEventId.FAILURE, "RetrieveStateCodeByZip-SQL ERROR: " + e.getMessage());
    		}
    	}		
    	initializeFieldedAddress();
    }
    
    /**
     * Parses MI String addresses and creates Address Object.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param stAddr String
     * @param cityState String
     * @param zip String
     * @param livingUnit String
     * @exception AddressHandlerException
     */
    public AddressHandlerMI (String stAddr, String cityState, String zip, String livingUnit, Properties properties, Logger myLogger)
    throws AddressHandlerException
    {
    	theLogger = myLogger;
    	
    	try	// rz do I need ???
    	{
    		if (stAddr != null)
    		{
    			stAddr = stAddr.trim ();
    			if (stAddr.toLowerCase().startsWith ("po box"))
    				aBox = stAddr.substring (6,stAddr.length ());
    			else if (stAddr.toLowerCase().startsWith ("p o box"))
    				aBox = stAddr.substring (7,stAddr.length ());
    			else if (stAddr.toLowerCase().startsWith ("p.o. box"))
    				aBox = stAddr.substring (8,stAddr.length ());
    			else if (stAddr.toLowerCase().startsWith ("p. o. box"))
    				aBox = stAddr.substring (9,stAddr.length ());
    			else
    				parseUFAddr (stAddr, FieldedAddressList.getStreetDirSufList());
    		}
    	}
    	catch (AddressHandlerException e)
    	{
    		throw e;
    	}
    
    	if (cityState != null)
    	{
    		StringTokenizer tmp = new java.util.StringTokenizer (cityState.trim(), " ");
    		String s_tmp = "";
    		while (tmp.hasMoreTokens())
    		{
    	 		s_tmp = tmp.nextToken ();
    	 		if (tmp.hasMoreTokens())
    	 		{
    	 			if (aCity.equals (""))
    	 				aCity += s_tmp;
    	 			else
    	 				aCity = aCity + " " + s_tmp;
    	 		}
    	 		else
    	 		{
    		 		try
    		 		{
    			 		State stateCode = new State (s_tmp);
    			 		aState = s_tmp;
    		 		}
    		 		catch (Exception e)	// if it is not valid state code, add to city name
    		 		{
    			 		aCity = aCity + " " + s_tmp;
    		 		}
    	 		}
    		}
    	}
    	
    	parseZIP (zip);
    
    	if (livingUnit != null)
    		aAdditionalAddressInformation = livingUnit;
    
    	try
    	{
    		if (aState == null || aState.equals(""))
    		{
    			if (aPostalCode != null)
    				if (!aPostalCode.equals (""))
    					aState = RetrieveStateCodeByZip.retrieveStateCodeByZip (properties, aPostalCode, theLogger);
    		}
    	}
    	catch( SQLException e )
    	{
    		if ( e.getMessage().indexOf("Exhausted Resultset") >= 0)
    		{
    			theLogger.log (LogEventId.FAILURE, "RetrieveStateCodeByZip-Exhausted Resultset: " + e.getMessage());			
    		}
    		else
    		{
    			theLogger.log (LogEventId.FAILURE, "RetrieveStateCodeByZip-SQL ERROR: " + e.getMessage());
    		}
    	}		
    	initializeFieldedAddress();
    }
}
