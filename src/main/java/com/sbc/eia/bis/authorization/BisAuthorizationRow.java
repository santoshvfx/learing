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

/**
 * Defines the BIS_AUTHORIZATION Table schema, plus the timeOutInMilliseconds
 * which is used as timer to inidicate the time to reload records from the database.
 * Creation date: (3/2/04 3:11:04 PM)
 * @author vc7563
 * 
 *  History :
 *  Date      	| Author        	| Version	| Notes
 *  ----------------------------------------------------------------------------
 * 	03/02/2004	  Vickie Ng 	      1.0		  Creation.
 *	
 */


public class BisAuthorizationRow
{
	private String methodName = "";
	private String application = "";
	private String businessUnit = "";
	private String serviceCenter = "";
	private String authorization = "";
	private String groupId = "";
	private String bisOwner = "";
	private long timeOutInMilliseconds;

	/**
	 * Constructor for BisAuthorizationRow.
	 */
	public BisAuthorizationRow()
	{
		super();
	}

	/**
	 * Returns the application.
	 * @return String
	 */
	public String get_application()
	{
		if ( application != null && application.trim().length() > 0 )
			return application.toUpperCase();
			
		return application;
	}

	/**
	 * Returns the authorization.
	 * @return String
	 */
	public String get_authorization()
	{
		if ( authorization != null && authorization.trim().length() > 0 )
			return authorization.toUpperCase();
			
		return authorization;
	}

	/**
	 * Returns the bisOwner.
	 * @return String
	 */
	public String get_bisOwner()
	{
		if ( bisOwner != null && bisOwner.trim().length() > 0 )
			return bisOwner.toUpperCase();
			
		return bisOwner;
	}

	/**
	 * Returns the businessUnit.
	 * @return String
	 */
	public String get_businessUnit()
	{
		if ( businessUnit != null && businessUnit.trim().length() > 0 )
			return businessUnit.toUpperCase();
			
		return businessUnit;
	}

	/**
	 * Returns the groupId.
	 * @return String
	 */
	public String get_groupId()
	{
		if ( groupId != null && groupId.trim().length() > 0 )
			return groupId.toUpperCase();
			
		return groupId;
	}

	/**
	 * Returns the methodName.
	 * @return String
	 */
	public String get_methodName()
	{
		if ( methodName != null && methodName.trim().length() > 0 )
			return methodName.toUpperCase();
			
		return methodName;
	}

	/**
	 * Returns the serviceCenter.
	 * @return String
	 */
	public String get_serviceCenter()
	{
		if ( serviceCenter != null && serviceCenter.trim().length() > 0 )
			return serviceCenter.toUpperCase();
			
		return serviceCenter;
	}

	/**
	 * Sets the application.
	 * @param application The application to set
	 */
	public void set_application( String application )
	{
		this.application = application;
	}

	/**
	 * Sets the authorization.
	 * @param authorization The authorization to set
	 */
	public void set_authorization( String authorization )
	{
		this.authorization = authorization;
	}

	/**
	 * Sets the bisOwner.
	 * @param bisOwner The bisOwner to set
	 */
	public void set_bisOwner( String bisOwner )
	{
		this.bisOwner = bisOwner;
	}

	/**
	 * Sets the businessUnit.
	 * @param businessUnit The businessUnit to set
	 */
	public void set_businessUnit( String businessUnit )
	{
		this.businessUnit = businessUnit;
	}

	/**
	 * Sets the groupId.
	 * @param groupId The groupId to set
	 */
	public void set_groupId( String groupId )
	{
		this.groupId = groupId;
	}

	/**
	 * Sets the methodName.
	 * @param methodName The methodName to set
	 */
	public void set_methodName( String methodName )
	{
		this.methodName = methodName;
	}

	/**
	 * Sets the serviceCenter.
	 * @param serviceCenter The serviceCenter to set
	 */
	public void set_serviceCenter( String serviceCenter )
	{
		this.serviceCenter = serviceCenter;
	}

	/**
	 * Returns the TimeOutInMilliseconds.
	 * @return long
	 */
	public long get_timeOutInMilliseconds()
	{
		return timeOutInMilliseconds;
	}

	/**
	 * Sets the TimeOutInMilliseconds.
	 * @param TimeOutInMilliseconds The TimeOutInMilliseconds to set
	 */
	public void set_timeOutInMilliseconds( long TimeOutInMilliseconds )
	{
		this.timeOutInMilliseconds = TimeOutInMilliseconds;
	}

	
	/**
	 * Retuns a string representation of the BisAuthorizationRow class
	 * @@return String
	 */
	public String toString()
	{
		return methodName
			+ "|"
			+ application
			+ "|"
			+ businessUnit
			+ "|"
			+ serviceCenter
			+ "|"
			+ authorization
			+ "|"
			+ groupId
			+ "|"
			+ timeOutInMilliseconds;
	}
}
