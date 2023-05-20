// $Id: AddressHandlerCRIS.java,v 1.3 2003/06/04 21:59:49 rz7367 Exp $

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
 * AddressHandlerCRIS is derived from AddressHandler, it creates Address Object for CRIS SWBT addresses.
 * Creation date: (5/3/01 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */
public class AddressHandlerCRIS extends AddressHandler {
        
    /**
     * AddressHandlerCRIS constructor.
     */
    public AddressHandlerCRIS() {
    	super();
    }
    
    /**
     * Parses CRIS String addresses and creates Address Object.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param addrAndZip String
     * @param livingUnit String
     * @exception AddressHandlerException
     */
    public AddressHandlerCRIS (String addrAndZip, String livingUnit, String ahn, Properties properties, Logger myLogger)
    throws AddressHandlerException
    {
    	theLogger = myLogger;
    	
    	int zipIndx = -1;
    	String zip = "";
    	String addr = "";
    
    	if (addrAndZip != null)
    		if ((zipIndx = addrAndZip.indexOf ("/DZIP")) > -1)
    		{
    			zip = addrAndZip.substring (zipIndx+5, addrAndZip.length ()).trim ();
    			addr = addrAndZip.substring (0, zipIndx).trim ();
    			if (!zip.equalsIgnoreCase("NONE"))
    				parseZIP (zip);
    		}
    		else
    			addr = addrAndZip;
    	
    	if (addr != null)
    		handleAddr (addr);
    	
    	if (livingUnit != null)	
    		handleUnit (livingUnit, ";");
    
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
    	if (ahn != null)
    		aAssignedHouseNumber = ahn;
    	
    	initializeFieldedAddress();	
    }
    
    /**
     * Parses CRIS String addresses and creates Address Object.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param addr String
     * @param zip String
     * @param livingUnit String
     * @exception AddressHandlerException
     */
    public AddressHandlerCRIS (String addr, String zip, String livingUnit, String ahn, Properties properties, Logger myLogger)
    throws AddressHandlerException
    {
    	theLogger = myLogger;
    	
    	if (zip != null)
    		if (!zip.trim ().equals ("NONE"))
    			parseZIP (zip);
    
    	if (addr != null)
    		handleAddr (addr);
    
    	if (livingUnit != null)
    		handleUnit (livingUnit, ";");
    
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
    	if (ahn != null)	
    		aAssignedHouseNumber = ahn;
    	
    	initializeFieldedAddress();
    }
    
    /**
     * Parses CRIS address into the AddressHandler street elements.
     * Creation date: (5/3/01 12:20:56 PM)
     * @param addr String
     * @exception AddressHandlerException
     */
    private void handleAddr (String addr)
    throws AddressHandlerException
    {
    	boolean physAddr = true;
    	boolean noStAddr = false;
    	boolean pobox = false;
    	String s_tmp = addr;
    	
    	// if (OAD) is found in the street address => remove the (OAD) part and parse the rest of the address.
    	//
    	if (addr.trim().startsWith ("(OAD)"))
    	{
    		int ij = addr.indexOf(")");
    		s_tmp = addr.substring (ij+1, addr.length ());
    	}
    
    	String stAddr = "";
    
    	int rlIndx = -1;
    	// if an /RL entry is found, it is ignored and everything after it is also ignored.
    	//
    	if ((rlIndx = s_tmp.indexOf ("/RL")) > -1)
    		s_tmp = s_tmp.substring (0, rlIndx).trim ();
    	else
    		s_tmp = s_tmp.trim ();
    	
    	if (s_tmp.startsWith (","))
    		noStAddr = true;
    			
    	StringTokenizer tmp = new java.util.StringTokenizer (s_tmp, ",");
    	if (tmp.hasMoreTokens())
    	{
    		if (noStAddr)
    		{
    			aCity = tmp.nextToken ();
    			if (tmp.hasMoreTokens())
    			{
    				while (tmp.hasMoreTokens())
    				{
    					String tmp_s = tmp.nextToken ().trim ();
    		 			try
    		 			{
    			 			State stateCode = new State (tmp_s);
    			 			aState = tmp_s;
    		 			}
    		 			catch (Exception e)	// if it is not valid state code, it is a county
    		 			{
    			 			aCounty = tmp_s;
    		 			}
    				}
    			}
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
    
    	// @ or (1) is found at the begining, it is removed, if PO Box or Post Office Box entry is found,
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
    	else if (stAddr.startsWith ("(1)"))
    	{
    		stAddr = stAddr.substring (3, stAddr.length ()).trim ();
    		physAddr = false;
    	}
    
    	// If the stAddr contains one of the following Listing Instruction Codes: (DFX), (HFX), (PFX), (SFX),
    	// (DNA) or (DNO) they are populated into the AdditionalAddressInformation field , and also everything
    	// following those codes are populated into the AdditionalAddressInformation field. It is assumed
    	// that they are the last entry in the stAddr.
    	//
    	int indx = -1;
    	if (physAddr)
    	{
    		if ((indx = stAddr.indexOf ("(DFX)")) > -1)
    		{
    			aAdditionalAddressInformation = stAddr.substring (indx, stAddr.length ()).trim () + " ";
    			stAddr = stAddr.substring (0, indx).trim ();
    		}
    		else if ((indx = stAddr.indexOf ("(HFX)")) > -1)
    		{
    			aAdditionalAddressInformation = stAddr.substring (indx, stAddr.length ()).trim () + " ";
    			stAddr = stAddr.substring (0, indx).trim ();
    		}
    		else if ((indx = stAddr.indexOf ("(PFX)")) > -1)
    		{
    			aAdditionalAddressInformation = stAddr.substring (indx, stAddr.length ()).trim () + " ";
    			stAddr = stAddr.substring (0, indx).trim ();
    		}
    		else if ((indx = stAddr.indexOf ("(SFX)")) > -1)
    		{
    			aAdditionalAddressInformation = stAddr.substring (indx, stAddr.length ()).trim () + " ";
    			stAddr = stAddr.substring (0, indx).trim ();
    		}
    		else if ((indx = stAddr.indexOf ("(DNA)")) > -1)
    		{
    			aAdditionalAddressInformation = stAddr.substring (indx, stAddr.length ()).trim () + " ";
    			stAddr = stAddr.substring (0, indx).trim ();
    		}
    		else if ((indx = stAddr.indexOf ("(DNO)")) > -1)
    		{
    			aAdditionalAddressInformation = stAddr.substring (indx, stAddr.length ()).trim () + " ";
    			stAddr = stAddr.substring (0, indx).trim ();
    		}
    	}
    
    	try
    	{
    		if (physAddr)
    			parseUFAddr (stAddr, getStreetDirSufList());
    		else if (!pobox)
    			aStreetName = stAddr;	
    
    		// if the street direction or street name suffix has '*' => remove it.
    		if (aStreetDirection.length () == 3)
    			aStreetDirection = aStreetDirection.substring(0,1) + aStreetDirection.substring(2,3);
    		if (aStreetNameSuffix.length () == 3)
    			aStreetNameSuffix = aStreetNameSuffix.substring (0,1) + aStreetNameSuffix.substring (2,3);
    	}
    	catch (AddressHandlerException e)
    	{
    		throw e;
    	}
    	return;
    }
    
    /**
     * returns a ArrayList with a list of allowed Street Direction/Suffix Names.
     * Creation date: (12/3/03 12:20:56 PM)
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
}
