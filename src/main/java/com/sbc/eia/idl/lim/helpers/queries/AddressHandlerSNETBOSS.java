// $Id: AddressHandlerSNETBOSS.java,v 1.5 2003/06/04 21:59:49 rz7367 Exp $

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
 * AddressHandlersnetboss is derived from AddressHandler, it creates Address Object for snet boss addresses.
 * Creation date: (04/17/02 12:00:00 PM)
 * @author: Rachel Zadok - Local
 */ 
public class AddressHandlerSNETBOSS extends AddressHandler {
    
	private boolean pupolateCity = true;

    /**
     * AddressHandlerAIT constructor.
     */
    public AddressHandlerSNETBOSS() {
    	super();
    }
    
    /**
     * Parses SNET BOSS String addresses and creates Address Object.
     * Creation date: (04/17/02 4:00:00 PM)
     * @param streetAddress String
     * @param cityStateZip String
     * @param loc String
     * @exception AddressHandlerException
     */
    public AddressHandlerSNETBOSS (String streetAddress, String cityStateZip, String loc, Properties properties, Logger myLogger) 
    throws AddressHandlerException
    {
    	theLogger = myLogger;
    	
    	if (streetAddress != null)
    	{
    		handleAddr (streetAddress);
    	}
    
    	if (cityStateZip != null)
    	{
    		StringTokenizer tmp = new java.util.StringTokenizer (cityStateZip.trim(), " ");
    		String s_tmp = "";
    		while (tmp.hasMoreTokens())
    		{
    	 		s_tmp = tmp.nextToken ();
    	 		boolean allDigitToken = true;
    	 		for (int ij = 0; ij < s_tmp.length (); ij++)
    	 		{
    	 			if (!Character.isDigit (s_tmp.charAt(ij)) && !(s_tmp.charAt(ij) == '-'))
    		 		{
    			 		allDigitToken = false;
    			 		break;
    		 		}
    	 		}
    	 		if (allDigitToken)
    	 			parseZIP (s_tmp);
    	 		else if (aCity.equals (""))
    	 		{
    		 		if (pupolateCity)
    	 				aCity += s_tmp;
    	 		}
    	 		else if (aState.equals (""))
    	 		{
    		 		try
    		 		{
    			 		State stateCode = new State (s_tmp);
    			 		aState = s_tmp;
    		 		}
    		 		catch (Exception e)	// if it is not valid state code, add to city name
    		 		{
    			 		if (pupolateCity)
    			 			aCity = aCity + " " + s_tmp;
    		 		}
    	 		}
    	 		else
    	 			aPostalCode = s_tmp;
    		} // end tmp.hasMoreTokens()
    		if (aState.equals ("") && !aCity.equals (""))
    		{
    			try
    			{
    				State stateCode = new State (aCity);
    				aState = aCity;
    				aCity = "";
    			}
    		 	catch (Exception e)	// if it is not valid state code, do nothing
    		 	{
    		 	}
    		}	
    	} // end cityStateZip != null
    
    	if (loc != null)
    		handleUnit (loc, ";");
    
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
     * Parses SNET BOSS address into the AddressHandler street elements.
     * Creation date: (04/17/02 12:00:00 PM)
     * @param addr String
     * @exception AddressHandlerException
     */
    private void handleAddr (String addr)
    throws AddressHandlerException
    {
    	String s_tmp = addr.trim ();
    	
    	// if (xxx) is found in the street address => remove the (xxx) part and parse the rest of the address.
    	//
    	int ij = -1;
    	boolean moreParenthesis = true;
    	try
    	{
    		while (moreParenthesis)
    		{
    			if (s_tmp.trim().startsWith ("("))
    			{
    				ij = s_tmp.indexOf (")");
    				s_tmp = s_tmp.substring (ij+1, s_tmp.length ());
    				s_tmp = s_tmp.trim ();
    			}
    			else
    				moreParenthesis = false;
    		}
    	}
    	catch (Exception e) { }
    	
    	try
    	{
    		if (s_tmp.startsWith ("RR"))
    		{
    			ij = ij + 2;
    			s_tmp = s_tmp.substring (ij+1, s_tmp.length ());
    			s_tmp = s_tmp.trim ();
    		}
    	}
    	catch (Exception e) { }
    	
    	int kj = -1;
    	boolean isFraction = false;
    	kj = s_tmp.indexOf ("/");
    	// If a fraction is found which is preceded by ' ' => add '-' before the fraction,
    	// so that the parseUFAddr will treat the fraction as HouseNumberSuffix.
    	//
    	try 
    	{
    		if (kj >= 0)
    			if (Character.isDigit (s_tmp.charAt(kj-1)) && Character.isDigit (s_tmp.charAt(kj+1)))
    			{
    				isFraction = true;
    				if (s_tmp.charAt(kj-2) == ' ')
    					s_tmp = s_tmp.substring(0,kj-2) + '-' + s_tmp.substring(kj-1, s_tmp.length ());
    			}
    	}
    	catch (Exception e) { }
    
    	int lj = kj;
    	// check for community, it should be after / or ,
    	// make sure that the / is not part of  house number suffix.
    	//
    	try
    	{
    		if (isFraction)
    		{
    			if (kj >= 0)
    				kj = s_tmp.substring(kj+1,s_tmp.length ()).indexOf ("/") + lj + 1;
    			else
    				kj = s_tmp.indexOf ("/");
    		}
    	}
    	catch (Exception e) { }
    	
    	if (kj == lj && isFraction) // this is the case we found / for fraction only
    		kj = -1;
    		
    	if (kj < 0)
    		kj = s_tmp.indexOf (",");
    	
    	try
    	{	
    		if (kj >= 0)
    		{
    			aCity = s_tmp.substring (kj+1, s_tmp.length ()).trim();
    			s_tmp = s_tmp.substring (0, kj);
    			pupolateCity = false;
    			s_tmp = s_tmp.trim ();
    		}
    	}
    	catch (Exception e) { }
    
    	try
    	{
    		parseUFAddr (s_tmp, FieldedAddressList.getStreetDirSufList());
    		
    		// add '-' to the number suffix if there is no '-'.
    		//
    		if (aHouseNumberSuffix != null)
    			if (!aHouseNumberSuffix.trim().equals(""))
    				if (!aHouseNumberSuffix.trim().startsWith("-"))
    					aHouseNumberSuffix = "-" + aHouseNumberSuffix.trim();
    	}
    	catch (AddressHandlerException e)
    	{
    		throw e;
    	}
    	
    	return;
    }
}
