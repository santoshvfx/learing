// $Id: HOSTLOOKUPAccess.java,v 1.2 2004/03/15 21:15:11 hw7243 Exp $

package com.sbc.gwsvcs.service.hostlookup;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import java.io.*;
import com.sbc.gwsvcs.service.hostlookup.exceptions.*;
import com.sbc.gwsvcs.service.hostlookup.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * This is the HOSTLOOKUP Access class.
 * Creation date: (4/26/00 11:36:38 AM)
 * @author: David Brawley
 */

 /*
  * Mark L.
  * Added the lookup for state code using the telephonenumber
  * 01/2002
  *
  * David Brawley
  * New version 6.0,
  *  structure for LOOKUP_FULL changed
  *  added LOOKUP_FULL_FICTITIOUS (same structure as LOOKUP_FULL) 
  * 02/2002
  *
  */
 
public class HOSTLOOKUPAccess extends ServiceAccess
{
	public final static String version = "7.0";
	public final static String name = "HOSTLOOKUP";

	public final static int HL_LOOKUP_WC_NBR 				= 20;
	public final static int HL_LOOKUP_FULL_NBR 				= 50;
	public final static int HL_LOOKUP_DIV_NBR 				= 80;
	public final static int HL_LOOKUP_R_NBR 				= 30;
	public final static int HL_LOOKUP_ERROR_NBR 			= 100;
	public final static int HL_OSS_LOOKUP_ERROR_NBR 		= 110;
	public final static int HL_LOOKUP_FULL_FICTITIOUS_NBR 	= 200;
	
	public final static int HL_LOOKUP_STATE_NBR 	    	= 300;
	public final static int HL_LOOKUP_STATE_R_NBR 	    	= 310;
	
	public final static MEventType HL_LOOKUP_WC			 		= new MEventType("HL_LOOKUP_WC");				// Event 20
	public final static MEventType HL_LOOKUP_FULL 		 		= new MEventType("HL_LOOKUP_FULL");				// Event 50
	public final static MEventType HL_LOOKUP_DIV 		 		= new MEventType("HL_LOOKUP_DIV");				// Event 80
	public final static MEventType HL_LOOKUP_R 			 		= new MEventType("HL_LOOKUP_R");				// Event 30
	public final static MEventType HL_LOOKUP_ERROR 		 		= new MEventType("HL_LOOKUP_ERROR");			// Event 100
	public final static MEventType HL_OSS_LOOKUP_ERROR 	 		= new MEventType("HL_OSS_LOOKUP_ERROR");		// Event 110
	public final static MEventType HL_LOOKUP_FULL_FICTITIOUS 	= new MEventType("HL_LOOKUP_FULL_FICTITIOUS");	// Event 200
	
	public final static MEventType HL_LOOKUP_STATE       		= new MEventType("HL_LOOKUP_STATE");			// Event 300
	public final static MEventType HL_LOOKUP_STATE_R     		= new MEventType("HL_LOOKUP_STATE_R");		    // Event 310		

/**
 * Construct a HOSTLOOKUPAccess object.
 * Creation date: (4/26/01 12:33:23 PM)
 * @param vicunaXmlFile String
 * @param serviceXmlDir String
 * @param aLogger jcom.sbc.bccs.utilities.Logger
 */
public HOSTLOOKUPAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
