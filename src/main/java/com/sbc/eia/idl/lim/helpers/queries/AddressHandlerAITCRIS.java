// $Id: AddressHandlerAITCRIS.java,v 1.3 2003/06/04 21:59:49 rz7367 Exp $

package com.sbc.eia.idl.lim.helpers.queries;

import java.sql.SQLException;
import java.util.*;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.helpers.AddressHandlerAIT;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;

/**
 * An AddressHandler subclass that can handle addresses specific to both AIT and CRIS.
 * Creation date: (3/27/01 10:30:41 AM)
 * @author: Rachel Zadok - Local
 */ 
public class AddressHandlerAITCRIS extends AddressHandlerAIT {
    
    /**
     * AddressHandlerAIT constructor.
     */
    public AddressHandlerAITCRIS() {
    	super();
    }
    
    /**
     * Parses AIT CRIS String addresses and creates Address Object.
     * Creation date: (7/16/01 4:04:29 PM)
     * @param address String
     * @param zip String
     * @param loc String
     * @exception AddressHandlerException
     */
    public AddressHandlerAITCRIS(String address, String zip, String loc, Properties properties, Logger myLogger) 
    throws AddressHandlerException
    {
    	theLogger = myLogger;
    	
    	parseZIP (zip);
    	
    	if (address != null)
    	{
    		handleAddr (address);
    	}
    	
    	if (loc != null)
    		handleUnit (loc, ";");
    
    	/**
    	 * Data after the "/" is not considered as part of a city name.
    	 */
    	if (aCity != null) {
    		int j = aCity.indexOf ('/');
    		if ( j > 0 )
    			aCity = aCity.substring (0, j);
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
     * Parses AIT CRIS address into the AddressHandler street elements.
     * Creation date: (07/27/01 12:00:00 PM)
     * @param addr String
     * @exception AddressHandlerException
     */
    
    private void handleAddr (String addr)
    throws AddressHandlerException
    {
    	boolean physAddr = true;
    	boolean noStAddr = false;
    	boolean pobox = false;
    	String s_tmp = addr.trim ();
    	
    	// if (OAD) is found in the street address => remove the (OAD) part and parse the rest of the address.
    	//
    	if (addr.trim().startsWith ("(OAD)"))
    	{
    		int ij = addr.indexOf(")");
    		s_tmp = addr.substring (ij+1, addr.length ());
    	}
    	s_tmp = s_tmp.trim ();
    	
    	// if degree of indention (1) thru (7) is found in the street address => remove it and parse the rest of the address.
    	//
    	if (s_tmp.startsWith ("(1)") || s_tmp.startsWith ("(2)") || s_tmp.startsWith ("(3)") ||
    		s_tmp.startsWith ("(4)") || s_tmp.startsWith ("(5)") || s_tmp.startsWith ("(6)") ||
    		s_tmp.startsWith ("(7)"))
    	{
    		s_tmp = s_tmp.substring (3, s_tmp.length ());
    	}
    	s_tmp = s_tmp.trim ();
    	
    	// If the addr contains one of the following Listing Instruction Codes: (DFX), (HFX), (PFX), (SFX),
    	// (DNA) or (DNO) they are removed and ignored.
    	//
    	int indx = -1;
    	if ((indx = s_tmp.indexOf ("(DFX)")) > -1)
    	{
    		s_tmp = s_tmp.substring (0, indx) + s_tmp.substring (indx+5, s_tmp.length ());
    	}
    	if ((indx = s_tmp.indexOf ("(HFX)")) > -1)
    	{
    		s_tmp = s_tmp.substring (0, indx) + s_tmp.substring (indx+5, s_tmp.length ());
    	}
    	if ((indx = s_tmp.indexOf ("(PFX)")) > -1)
    	{
    		s_tmp = s_tmp.substring (0, indx) + s_tmp.substring (indx+5, s_tmp.length ());
    	}
    	if ((indx = s_tmp.indexOf ("(SFX)")) > -1)
    	{
    		s_tmp = s_tmp.substring (0, indx) + s_tmp.substring (indx+5, s_tmp.length ());
    	}
    	if ((indx = s_tmp.indexOf ("(DNA)")) > -1)
    	{
    		s_tmp = s_tmp.substring (0, indx) + s_tmp.substring (indx+5, s_tmp.length ());
    	}
    	if ((indx = s_tmp.indexOf ("(DNO)")) > -1)
    	{
    		s_tmp = s_tmp.substring (0, indx) + s_tmp.substring (indx+5, s_tmp.length ());
    	}
    	s_tmp = s_tmp.trim ();
    	
    	String stAddr = "";
    	
    	if (s_tmp.startsWith (","))
    		noStAddr = true;
    			
    	StringTokenizer tmp = new java.util.StringTokenizer (s_tmp, ",");
    	if (tmp.hasMoreTokens())
    	{
    		if (noStAddr)
    		{
    			aCity = tmp.nextToken ();
    			if (tmp.hasMoreTokens())
    				aState = tmp.nextToken ().trim ();
    		}
    		else
    		{
    			stAddr = tmp.nextToken ().trim ();
    			if (tmp.hasMoreTokens())
    			{
    				aCity = tmp.nextToken ();
    				if (tmp.hasMoreTokens())
    					aState = tmp.nextToken ().trim ();
    			}
    		}
    	}
    
    	// if @ is found at the begining, it is removed, if PO Box or Post Office Box entry is found,
    	// the number following is treated as PO Box number, otherwise it is assumed that the address is
    	// descriptive address and the data following is to be treated as street name.
    	//
    	if (stAddr.startsWith ("@"))
    	{
    		stAddr = stAddr.substring (1, stAddr.length ()).trim ();
    		if (stAddr.toLowerCase().startsWith ("po box"))
    		{
    			aBox = stAddr.substring (6, stAddr.length ()).trim ();
    			pobox = true;
    		}
    		else if (stAddr.toLowerCase().startsWith ("post office box"))
    		{
    			aBox = stAddr.substring (15, stAddr.length ()).trim ();
    			pobox = true;
    		}
    		else if (stAddr.toLowerCase().startsWith ("p*o box"))
    		{
    			aBox = stAddr.substring (7, stAddr.length ()).trim ();
    			pobox = true;
    		}
    		physAddr = false;
    	}
    
    	try
    	{
    		if (physAddr)
    			parseUFAddr (stAddr, getStreetDirSufList());
    		else if (!pobox)
    			aStreetName = stAddr;	
    	}
    	catch (AddressHandlerException e)
    	{
    		throw e;
    	}
    	
    	return;
    }
    
    /**
     * Set AssignedHouseNumber.
     * @param ahn String
     */
    
    public void setAHN (String ahn)
    {
    	if (ahn != null && !ahn.equals (""))
    	{
    		aAssignedHouseNumber = ahn.trim();
    		theAddress.aFieldedAddress().aAssignedHouseNumber.theValue( ahn.trim() );
    		
    		aHouseNumber = "";
    		theAddress.aFieldedAddress().aHouseNumber.theValue( "" );
    	}
    }
}
