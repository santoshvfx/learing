package com.sbc.eia.idl.lim.helpers.queries;

import com.sbc.bccs.utilities.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.FieldedAddressList;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.bccs.idl.helpers.*;
import com.sbc.eia.bis.BusinessInterface.*;
import java.util.*;

import java.sql.SQLException;
import com.sbc.eia.idl.lim.helpers.queries.*;
import com.sbc.eia.idl.lim.queries.*;

import com.sbc.eia.bis.framework.logging.*;
import com.sbc.eia.bis.framework.*;
import com.sbc.logging.*;
import com.sbc.eia.idl.exception_types.*;
import com.sbc.logging.message.*;

/**
 * AddressHandlerCRM is derived from AddressHandler, it creates Address Object.
 * Creation date: (6/29/04 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */
public class AddressHandlerCRM extends AddressHandler {
/**
 * AddressHandlerCRM constructor.
 */

public AddressHandlerCRM() 
{
	super();
}

/**
 * Parses CRM String addresses and creates Address Object.
 * @param stAddr String
 * @param livingUnit String
 * @param city String
 * @param state String
 * @param zip String
 * @exception AddressHandlerException
 */

public AddressHandlerCRM (String stAddr, String livingUnit, String city, String state, String zip, Properties properties, Logger myLogger)
throws AddressHandlerException
{
	theLogger = myLogger;
	
	if (stAddr != null)
	{
		parseUFAddr (stAddr.trim(), FieldedAddressList.getStreetDirSufList());
	}
	
	ArrayList unitStructureNameList = FieldedAddressList.getStructureNameList();
	ArrayList unitLevelNameList = FieldedAddressList.getLevelNameList();
	ArrayList unitUnitNameList = FieldedAddressList.getUnitNameList();
	if (livingUnit != null)
	{
		String s_unit = livingUnit;

		// If the different living units are not seperated by "," => add comma.
		//
		String lu_s = "";
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
	
		handleUnit (lu_s, ",");
	}
	//if (livingUnit != null)
	//{
	//	aUnit = handleUnit (livingUnit.trim (), " ");
	//}
	
 	if (city != null)
		aCity = city.trim();
  	
 	if (state != null)
		aState = state.trim ();
		
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
 * Parses CRM String addresses and Living Unit and creates Address Object.
 * @param ufAddrPlusLU String
 * @param city String
 * @param state String
 * @param postalCode String
 * @exception AddressHandlerException
 */
public AddressHandlerCRM (String ufAddrPlusLU, String city, String state, String postalCode)
throws AddressHandlerException
{
	ArrayList unitStructureNameList = FieldedAddressList.getStructureNameList();
	ArrayList unitLevelNameList = FieldedAddressList.getLevelNameList();
	ArrayList unitUnitNameList = FieldedAddressList.getUnitNameList();

	String livingUnitCRM = "";
	String streetAddrCRM = "";
	
    if (ufAddrPlusLU != null)
    {
    	StringTokenizer tmp = new java.util.StringTokenizer (ufAddrPlusLU.trim(), " ");
    	String s_tmp = "";
    	boolean streetAddr = true;
    	
    	while (tmp.hasMoreTokens())
    	{
    	 	s_tmp = tmp.nextToken ();
			if (!streetAddr || unitStructureNameList.contains (s_tmp) ||
				unitLevelNameList.contains (s_tmp) || unitUnitNameList.contains (s_tmp))
			{
				streetAddr = false;
				livingUnitCRM = livingUnitCRM + " " + s_tmp;
			}
			else
				streetAddrCRM = streetAddrCRM + " " + s_tmp;
    	}
    	
    	if (!streetAddrCRM.trim().equals (""))
    		parseUFAddr (streetAddrCRM.trim(), FieldedAddressList.getStreetDirSufList());

		if (!livingUnitCRM.trim().equals (""))
		{			
			// If the different living units are not seperated by "," => add comma.
			//
			String lu_s = "";
			StringTokenizer lu_tmp = new java.util.StringTokenizer (livingUnitCRM.trim(), " ");
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
				lu_s = livingUnitCRM;
					
			handleUnit (lu_s, ",");
		}
    } // end if (ufAddrPlusLU != null)
    
 	if (city != null)
		aCity = city.trim();
  	
 	if (state != null)
		aState = state.trim ();
		
	parseZIP (postalCode);
	
	initializeFieldedAddress();
}

}

