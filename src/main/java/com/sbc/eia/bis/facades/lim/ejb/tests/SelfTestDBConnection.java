//$Id: SelfTestDBConnection.java,v 1.2 2009/05/19 13:57:27 jr5306 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      � 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 01/17/2006  Doris Sunga             Creation.
//# 02/14/2008	Anderson Manilay	 	SelfTest Enhancements

package com.sbc.eia.bis.facades.lim.ejb.tests;


import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.selftest.SelfTestResult;
import com.sbc.eia.idl.bis_types.BisContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.sbc.bccs.utility.database.DBConnection;
import com.sun.java.util.collections.ArrayList;
import java.sql.ResultSet;


public class SelfTestDBConnection
{

	DBConnection conn = null;
	static final String NO_ROWS = "No rows found";
	

	/**
	 * Constructor for selfDBConnection.
	 */
	public SelfTestDBConnection(
			String fileName,
		    BisContext aContext,
		    Hashtable aProperties,
		    Logger aLogger,
		    BisLogger bisLogger,
		    ArrayList aResultList)
		{

			String jdbcDriver = null;
			String jdbcUrl= null;
			String jdbcUserId = null;
			String jdbcPassWord = null;
			String dataSourceName = null;
			String jdbcOption = null;
			
			String dbDescription = null;
			String dbPropertyFile = null;
			String tableName = null;
			
			String SQLstatement = null;
			Connection connection = null;
			PreparedStatement preparedSql = null;
			ResultSet resultSet = null;
			
			ArrayList dataSourceList = new ArrayList();
			ArrayList dbProperties = new ArrayList();
			ArrayList dbPropertiesArray = new ArrayList();
			String myMethodName = "selfTestDBConnection::selfTestDBConnection()";
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
			dataSourceList = FileLoader.loadXmlContents(fileName, bisLogger);
			
			
			// Getting the Description, Property File and Table Name from the SelfTest_DatabaseResources.xsl file
			
			for(int ctr=0;ctr<dataSourceList.size();ctr+=3)
			{
				
				
				dbDescription = dataSourceList.get(ctr).toString().trim();
				dbPropertyFile = dataSourceList.get(ctr+1).toString().trim();
				tableName = dataSourceList.get(ctr+2).toString().trim();
				
				dbPropertiesArray = FileLoader.loadDBProperties(dbPropertyFile,bisLogger);
				jdbcDriver = dbPropertiesArray.get(0).toString();
				jdbcUrl = dbPropertiesArray.get(1).toString();
				jdbcUserId = dbPropertiesArray.get(2).toString();
				jdbcPassWord = dbPropertiesArray.get(3).toString();
				dataSourceName = dbPropertiesArray.get(4).toString();
				jdbcOption = dbPropertiesArray.get(5).toString();
				
				bisLogger.log(LogEventId.DEBUG_LEVEL_1, jdbcDriver + " " +
				jdbcUrl + " "  +
				jdbcUserId + " " +
				dataSourceName + " " +
				
				dbDescription + " " +
				dbPropertyFile + " " +
				tableName + " "  + jdbcOption);
				
				StringTokenizer stringTokenizer = new StringTokenizer(tableName,",");
			    while(stringTokenizer.hasMoreTokens()){
			    try
				{
			    	tableName = stringTokenizer.nextToken();
			    	connection = getConnection(jdbcDriver,jdbcUrl,jdbcUserId,jdbcPassWord,dataSourceName,jdbcOption,bisLogger);
			    	SQLstatement = "select count(*) from " +  tableName;
			    	preparedSql = connection.prepareStatement(SQLstatement.toString());
			    	bisLogger.log(LogEventId.INFO_LEVEL_2,	"Testing Database Connection before executing sql. Is Connection closed - " + connection.isClosed() + " !");
			    	resultSet = preparedSql.executeQuery();

			    	if ( !resultSet.next())
			    	{
			    		throw new SQLException( NO_ROWS );
			    	}

			    	bisLogger.log(LogEventId.INFO_LEVEL_1, "Database Connection Tested Successfully. Total record count is " + resultSet.getInt(1) + '.');

			    	SelfTestResult.addResultToList(
			    			"DATABASE" ,
							"Database Connection Using  " +
							"Driver " + jdbcDriver +
							", URL " + jdbcUrl +
							", Data Source " +   dataSourceName + 
							", and Table " + tableName + 
							" Tested Successfully.",
							true,
							bisLogger,
							aResultList);
			 
					}
			
			    	catch ( SQLException e )
					{
			    			bisLogger.log(LogEventId.ERROR, "Error message [" + e.getMessage() + "]");
			    			bisLogger.log(LogEventId.INFO_LEVEL_1, "Testing Failed on Database Connection Using  Data Source " +   dataSourceName + ", and Table " + tableName );
			    			SelfTestResult.addResultToList(
			    					"DATABASE" ,
									"Failed Testing Database Connection Using  " +
									"Driver " + jdbcDriver +
									", URL " + jdbcUrl +
									", Data Source " +   dataSourceName + 
									", and Table " + tableName + 
									".",
									false,
									bisLogger,
									aResultList);
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
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}


	/**
	 * getConnection: Gets JDBC properties from Hash Table and connects to the database.
	 * @param    	props, logger
	 * @return   	Connection
	 * @throws SQLException
	 * @throws		SQLException
	 */
	public Connection getConnection(String jdbcDriver,String jdbcUrl,String jdbcUserId,String jdbcPassWord,String dataSourceName,String jdbcOption,  BisLogger bisLogger) throws SQLException
	{
		String myMethodName = "selfTestDBConnection::getDBConnection()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		bisLogger.log(LogEventId.INFO_LEVEL_2,	"<dataSourceName>" + dataSourceName + "<jdbcUserId>" + jdbcUserId + "<jdbcUrl>" + jdbcUrl + "<jdbcDriver>" + jdbcDriver);
		bisLogger.log(LogEventId.INFO_LEVEL_2,	"Getting connection from DataSource/DriverManager");
		conn = new DBConnection (dataSourceName, jdbcUserId, jdbcPassWord, jdbcUrl, jdbcDriver, jdbcOption, bisLogger);
		return conn.getConnection();
	}
}