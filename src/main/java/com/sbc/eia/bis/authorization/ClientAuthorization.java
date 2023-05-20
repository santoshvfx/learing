// $ Id$
package com.sbc.eia.bis.authorization;
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

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import com.sbc.bccs.utilities.*;
import com.sbc.eia.bis.framework.logging.LogEventId;
import java.sql.SQLException;
import java.io.*;

/**
 * Class used to check if a user is an authorized user. 
 * 
 * Cache memory (authorizedList) is used to gain faster access to
 * user records.
 * 
 * Each time isAuthorized() is called, search() is called to look for
 * user record in cache memory. If it is found and timeout not expired, 
 * return true to the caller.  Otherwise, if record found and 
 * timeout expired, remove matching records or if record not found,
 * retrieve user records from Database.  Store new records in
 * cache memory and return true to the caller.
 * 
 * Creation date: (3/2/04 3:11:04 PM)
 * @author vc7563
 * 
 *  History :
 *  Date      	| Author        	| Version	| Notes
 *  ----------------------------------------------------------------------------
 * 	03/02/2004	  Vickie Ng 	      1.0		  Creation.
 *	06/29/2004    Rajarsi Sarkar	  1.0		  Modified
 */

public class ClientAuthorization
{
	private  static final Cache authorizedList = new Cache();
    	
	static final int RECORD_FOUND = 0 ;
	static final int EXCLUDED_RECORD_FOUND = 1;
	static final int GET_DB_RECORD = 2 ;
	static final int NO_RECORD_FOUND = 3;
	static final long ZERO_TIMEOUT = 0;

	/**
	 * Constructor for ClientAuthorization.
	 */
	private ClientAuthorization()
	{
		super();
	}
	
	/**  
	 * isAuthorized: Validates if a user is an authorized User.
	 * 				 Client data are loaded into the cache as 
	 * 			 	 they are requested.  All client data pertaining
	 * 				 to the  BIS can be loaded into the cache initially 
	 * 				 by specifying AUTHORIZATION_INITIAL_LOAD in the 
	 * 			     properties file.
     * @param    	methodName, application, businessUnit, serviceCenter, 
     * 				groupId, bisOwner, properties, logger			
     * @return   	boolean. true if user record is found, false otherwise.
     * @throws		AuthorizationException	
     * @author      vc7563 May 5, 2004 2:18:40 PM
     */
	public static boolean isAuthorized(String methodName,String application,String businessUnit,
	String serviceCenter,String groupId,Hashtable properties,Logger logger)
		throws AuthorizationException
	{
		logger.log(LogEventId.DEBUG_LEVEL_1,"ClientAuthorization:isAuthorized()" );
		// Create input object
		BisAuthorizationRow bisAuthorizationRow = new BisAuthorizationRow();
		bisAuthorizationRow.set_bisOwner("");
		
		return isAllowAccess( 
			methodName,
		 	application,
		 	businessUnit,
		 	serviceCenter,
		 	groupId,
		 	bisAuthorizationRow, 
		 	properties,
		 	logger) ;
	}
	
	/** 
	 * @deprecated  bisOwner is no longer used in the new schema. 
	 * 				 BIS owner is uniquely identified by the BIS 
	 * 				 table name. 
	 * isAuthorized: Validates if a user is an authorized User.
	 * 				 Client data are loaded into the cache as 
	 * 			 	 they are requested.  All client data pertaining
	 * 				 to the  BIS can be loaded into the cache initially 
	 * 				 by specifying AUTHORIZATION_INITIAL_LOAD in the 
	 * 			     properties file.
     * @param    	methodName, application, businessUnit, serviceCenter, 
     * 				groupId, bisOwner, properties, logger			
     * @return   	boolean. true if user record is found, false otherwise.
     * @throws		AuthorizationException	
     * @author      vc7563 May 5, 2004 2:18:40 PM
     */
	public static boolean isAuthorized(
		String methodName,
		String application,
		String businessUnit,
		String serviceCenter,
		String groupId,
		String bisOwner,
		Hashtable properties,
		Logger logger)
		throws AuthorizationException
	{
		logger.log(LogEventId.DEBUG_LEVEL_1,"ClientAuthorization:isAuthorized()" );

		// Create input object
		BisAuthorizationRow bisAuthorizationRow = new BisAuthorizationRow();
		
		if(bisOwner==null)
		bisOwner="";
		bisAuthorizationRow.set_bisOwner( bisOwner );
		
		return isAllowAccess( 
			methodName,
		 	application,
		 	businessUnit,
		 	serviceCenter,
		 	groupId,
		 	bisAuthorizationRow, 
		 	properties,
		 	logger) ;
	}
	
	/** 
	 * isAllowAccess: validate a client via the cache memory.
	 * 				 Client data are loaded into the cache as 
	 * 			 	 they are requested.  All client data pertaining
	 * 				 to the  BIS can be loaded into the cache initially 
	 * 				 by specifying AUTHORIZATION_INITIAL_LOAD in the 
	 * 			     properties file.
     * @param    	methodName, application, businessUnit, serviceCenter, 
     * 				groupId, bisOwner, properties, logger			
     * @return   	boolean. true if user record is found, false otherwise.
     * @throws		AuthorizationException	
     * @author      vc7563 May 5, 2004 2:18:40 PM
     */
	private static boolean isAllowAccess(
		String methodName,
		String application,
		String businessUnit,
		String serviceCenter,
		String groupId,
		BisAuthorizationRow bisAuthorizationRow, 
		Hashtable aProperties,
		Logger aLogger) 
	throws AuthorizationException
	{
		aLogger.log(LogEventId.DEBUG_LEVEL_1,"ClientAuthorization:isAllowAccess()" );

//---------------add to Ver I------------
		if(methodName == null || methodName.trim().length() == 0 ||
			application == null || application.trim().length() == 0)
		return false;
//---------------------------------------

		bisAuthorizationRow.set_methodName( methodName );
		bisAuthorizationRow.set_application( application );
		bisAuthorizationRow.set_businessUnit( businessUnit );
		bisAuthorizationRow.set_serviceCenter( serviceCenter );
		bisAuthorizationRow.set_groupId( groupId );

		if(authorizedList.isEmpty() && (String) aProperties.get( "AUTHORIZATION_INITIAL_LOAD" ) != null )
		{
			String bisOwner = bisAuthorizationRow.get_bisOwner();
			authorizedList.initialLoad(bisAuthorizationRow, aLogger, aProperties);
		}
	
		int rc = NO_RECORD_FOUND;

		rc = authorizedList.exists(bisAuthorizationRow, aLogger, aProperties);
		
		if ( rc == GET_DB_RECORD)
		{
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "Refresh from DB" );
			return false;
		}
		
		if ( rc == NO_RECORD_FOUND)
		{
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "No record found" );
			return false;
		}
		
		if ( rc == EXCLUDED_RECORD_FOUND )
		{
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "Excluded record found" );
			return false;
		}

		// record found.
		return true;
	}
	 
}
