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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import java.sql.Connection;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Contains methods to retrieve BIS_AUTHORIZATION from 
 * BIS_AUTHORIZATION table.
 * Creation date: (3/2/04 3:11:04 PM)
 * @author vc7563
 * 
 *  History :
 *  Date      	| Author        	| Version	| Notes
 *  ----------------------------------------------------------------------------
 * 	03/02/2004	  Vickie Ng 	      1.0		  Creation.
 *	
 */

public class BisAuthorizationTable
{
	DBConnection conn = null;
	static final long MILLISECOND_IN_MINUTE = 60 * 1000;
	static final String NO_ROWS = "No rows found";
	static String dataSourceName = null;
	static String jdbcUserId = null;
	static String jdbcPassWord = null;
	static String jdbcUrl= null;
	static String jdbcDriver = null;
	static String jdbcOption = null;


	/**
	 * Constructor for AuthorizationTable.
	 */
	public BisAuthorizationTable()
	{
		super();
	}

	/** 
	 * find: Returns a list of BIS_AUTHORIZATION records from DB
     * @param    	selectRow, properties, logger			
     * @return   	ArrayList
     * @throws		SQLException, AuthorizationException	
     * @author      vc7563 May 5, 2004 3:24:10 PM
     */
	private ArrayList find(
		BisAuthorizationRow selectRow,
		Hashtable properties,
		Logger logger)
		throws SQLException, AuthorizationException
	{
		logger.log(
			LogEventId.DEBUG_LEVEL_1,
			"BisAuthorizationTable:find()" );

		StringBuffer SQLstatement =
			new StringBuffer(
				"select METHOD_NAME, APPLICATION, BUSINESS_UNIT, SERVICE_CENTER, AUTHORIZATION, "
					+ "GROUP_ID");
		
		if ( selectRow.get_bisOwner().length() > 0 )
		{
			SQLstatement.append( ", BIS_OWNER from " );		
		}
		else
		{
			SQLstatement.append( " from " );
		}

		// get AUTHORIZATION properties			
		SQLstatement.append( (String) properties.get( "AUTHORIZATION_TABLE" ) );

		boolean fullLoadFlag = false;
		
		if ( ( selectRow.get_methodName().length() == 0 ) &&
			 ( selectRow.get_application().length() == 0 ) )
		{
			// get all rows that belong to this BIS
			if ( selectRow.get_bisOwner().length() > 0 )
			{
				SQLstatement.append( " where upper(BIS_OWNER) = upper(?) and upper(AUTHORIZATION) = 'Y' ");		
			}
			else
			{
				SQLstatement.append( " where upper(AUTHORIZATION) = 'Y' ");
			}
			
			fullLoadFlag = true;
		}
		else 
		{
			SQLstatement.append(
				" where upper(METHOD_NAME) = upper(?) and upper(APPLICATION) = upper(?) " );
					
			if ( selectRow.get_bisOwner().length() > 0 )
			{
				SQLstatement.append( "and upper(BIS_OWNER) = upper(?) " );	
			}
			
			SQLstatement.append( "and upper(AUTHORIZATION) = 'Y' " );
			
			// optional fields: businessUnit, serviceCenter, groupId.
			if ( selectRow.get_businessUnit() != null
				&& selectRow.get_businessUnit().trim().length() > 0 )
			{
				SQLstatement.append( "and (upper(BUSINESS_UNIT) = upper(?) or BUSINESS_UNIT like '!%' or BUSINESS_UNIT is null) " );
			}
			else 
			{
				SQLstatement.append( "and BUSINESS_UNIT is NULL " );
			}
		
			if ( selectRow.get_serviceCenter() != null
				&& selectRow.get_serviceCenter().trim().length() > 0 )
			{
				SQLstatement.append("and (upper(SERVICE_CENTER) = upper(?) or SERVICE_CENTER like '!%' or SERVICE_CENTER is null) ");
			}
			else 
			{
				SQLstatement.append( "and SERVICE_CENTER is NULL " );
			}
	
			if ( selectRow.get_groupId() != null
				&& selectRow.get_groupId().trim().length() > 0 )
			{
				SQLstatement.append( "and (upper(GROUP_ID) = upper(?) or GROUP_ID like '!%' or GROUP_ID is null) " );
			}
			else 
			{
				SQLstatement.append( "and GROUP_ID is NULL " );
			}
		}
		
		PreparedStatement preparedSql = null;
		ResultSet resultSet = null;
		ArrayList retVal = new ArrayList();
		
		logger.log(
			LogEventId.INFO_LEVEL_2,
				"SQLstatement = <" + SQLstatement + ">" );
			logger.log(
				LogEventId.INFO_LEVEL_2,
				"methodName <"
					+ selectRow.get_methodName()
					+ "> "
					+ "application <"
					+ selectRow.get_application()
					+ "> "
					+ "businessUnit <"
					+ selectRow.get_businessUnit()
					+ "> "
					+ "serviceCenter <"
					+ selectRow.get_serviceCenter()
					+ "> "
					+ "groupId <"
					+ selectRow.get_groupId()
					+ "> "
					+ "bisOwner <"
					+ selectRow.get_bisOwner()
					+ ">" );
							
		int reTryCnt = 0;
		Connection connection = null;
		while (true)
		{
			try
			{
				// connect to database. 
				connection = getConnection(properties, logger);
				preparedSql = connection.prepareStatement(SQLstatement.toString());	
				
				if ( fullLoadFlag )
				{
					// provide bisOwner only for deprecated isAuthorized(),
					// otherwise, nothing is provided to the query.
					if ( selectRow.get_bisOwner().length() > 0 )
					{
						preparedSql.setString( 1, selectRow.get_bisOwner() );
					}
				}
				else
				{
					preparedSql.setString( 1, selectRow.get_methodName() );
					preparedSql.setString( 2, selectRow.get_application() );
					int n = 3;
					
					if ( selectRow.get_bisOwner().length() > 0 )
					{
						preparedSql.setString( n++, selectRow.get_bisOwner() );		
					}
					
					if ( selectRow.get_businessUnit() != null
						&& selectRow.get_businessUnit().trim().length() > 0 )
					{
						preparedSql.setString( n++, selectRow.get_businessUnit() );
					}
		
					if ( selectRow.get_serviceCenter() != null
						&& selectRow.get_serviceCenter().trim().length() > 0 )
					{
						preparedSql.setString( n++, selectRow.get_serviceCenter() );
					}
		
					if ( selectRow.get_groupId() != null
						&& selectRow.get_groupId().trim().length() > 0 )
					{
						preparedSql.setString( n++, selectRow.get_groupId() );
					}
				}
				
				logger.log(LogEventId.INFO_LEVEL_2,	"Testing Connection before executing sql. Is Connection closed - " + connection.isClosed() + " !");
				
				resultSet = preparedSql.executeQuery();
	
				if ( !resultSet.next())
				{
					throw new SQLException( NO_ROWS );
				}
	
				long timeout;
				try 
				{
					timeout = Long.parseLong(
					 	(String) properties.get( "AUTHORIZATION_TIMEOUT" ) );
				}
				catch (NumberFormatException e )
				{
					throw new AuthorizationException ( "ERROR: NO AUTHORIZATION_TIMEOUT defined" );
				}
				
				// calculate timeout value
				long milliseconds =
					System.currentTimeMillis() +
						( MILLISECOND_IN_MINUTE * timeout );	
	
				do
				{
					BisAuthorizationRow bisAuthorizationRow =
						new BisAuthorizationRow();
	
					bisAuthorizationRow.set_methodName( resultSet.getString(1) );
					bisAuthorizationRow.set_application( resultSet.getString(2) );
					bisAuthorizationRow.set_businessUnit(
						resultSet.getString(3) );
					bisAuthorizationRow.set_serviceCenter( resultSet.getString(4) );
					bisAuthorizationRow.set_authorization(
						resultSet.getString(5) );
					bisAuthorizationRow.set_groupId( resultSet.getString(6) );
					if ( selectRow.get_bisOwner().length() > 0 )
					{
						bisAuthorizationRow.set_bisOwner( resultSet.getString(7) );	
					}
		
					bisAuthorizationRow.set_timeOutInMilliseconds( milliseconds );
	
					retVal.add( bisAuthorizationRow );
	
					logger.log(
						LogEventId.INFO_LEVEL_2,
						"SQL result: <" + bisAuthorizationRow.toString() + ">" ) ;
				}
				while ( resultSet.next() );
				
				return retVal;		
			}
			catch ( SQLException e )
			{
				// retry only once
				if ( reTryCnt == 0 &&
					(e.getMessage().equalsIgnoreCase("No more data to read from socket")))
				{
					reTryCnt++;
					logger.log(LogEventId.INFO_LEVEL_2,	"*****Getting Connection second time*****");
					continue;
				}

				throw e;
			}
			finally
			{
				try
				{
					resultSet.close();
				}
				catch ( Exception e )
				{
				}
				try
				{
					preparedSql.close();
				}
				catch ( Exception e )
				{
				}
				try
				{
					conn.disconnect();
				}
				catch ( Exception e )
				{ 
				}
	
				resultSet = null;
				preparedSql = null;
				conn = null;
			}
		}		
	}

	
	/** 
	 * getRecord: Gets User Authorization record
     * @param    	selectRow, logger, properties			
     * @return   	ArrayList
     * @throws		SQLException, AuthorizationException	
     * @author      vc7563 May 5, 2004 3:22:59 PM
     */
	public ArrayList getRecord(
		BisAuthorizationRow selectRow,
		Logger logger,
		Hashtable properties )
		throws SQLException, AuthorizationException
	{
		logger.log(
			LogEventId.DEBUG_LEVEL_1,
			"BisAuthorizationTable:getRecord()" );

		ArrayList row = null;
		BisAuthorizationTable bisUser = new BisAuthorizationTable();

		row = bisUser.find( selectRow, properties, logger );
		
		return row;
	}
	
	/** 
	 * getConnection: Gets JDBC properties from Hash Table and connects to the database.
     * @param    	props, logger			
     * @return   	Connection
     * @throws		SQLException	
     * @author      vc7563 May 5, 2004 3:21:50 PM
     */
	public Connection getConnection( Hashtable props, Logger logger) 
	throws SQLException
	{	
		try 
		{
			if ( dataSourceName == null )
			{
				dataSourceName = ((String) props.get( "jdbcDATA_SOURCE_NAME" )).trim();
			 	jdbcUserId = ((String) props.get( "jdbcUSERID" )).trim();
			 	jdbcPassWord = ((String) props.get( "jdbcPASSWORD" )).trim();
			 	jdbcUrl = ((String) props.get( "jdbcURL" )).trim();
			 	jdbcDriver = ((String) props.get( "jdbcDRIVER" )).trim();
			 	jdbcOption = ((String) props.get( "jdbcUSE_CONNECTION_POOL" )).trim();
			 	
			}
		}
		catch ( NullPointerException e )
		{	
			throw new SQLException(
				"Get JDBC properties failed. JDBC required tags are not defined in properties file." +
				e.getMessage() );
		}
		
		logger.log(LogEventId.INFO_LEVEL_2,	"<dataSourceName>" + dataSourceName + "<jdbcUserId>" + jdbcUserId + "<jdbcUrl>" + jdbcUrl + "<jdbcDriver>" + jdbcDriver);
				
		/*if(jdbcOption.equalsIgnoreCase("DATASOURCE"))
		{ 
			conn = (AuthDataSource.getInstance()).getConnection(jdbcUserId, jdbcPassWord, jdbcUrl, dataSourceName, logger);	
		}
		else if (jdbcOption.equalsIgnoreCase("BOTH"))
		{
			try
			{
				// DataSource
				conn = (AuthDataSource.getInstance()).getConnection(jdbcUserId, jdbcPassWord, jdbcUrl, dataSourceName, logger);	
			}
			catch(SQLException sqle)
			{
				logger.log(LogEventId.DEBUG_LEVEL_2,"Using JDBC Driver to get connection for " + dataSourceName);
				conn = new DBConnection ( dataSourceName, jdbcUserId, jdbcPassWord, 
					jdbcUrl, jdbcDriver, "JDBCDRIVER", logger);
			}	
		}*/
		
		logger.log(LogEventId.INFO_LEVEL_2,	"Getting connection from DataSource/DriverManager");
		conn = new DBConnection (dataSourceName, jdbcUserId, jdbcPassWord, jdbcUrl, jdbcDriver, jdbcOption, logger);
					
		return conn.getConnection();
	}


}
