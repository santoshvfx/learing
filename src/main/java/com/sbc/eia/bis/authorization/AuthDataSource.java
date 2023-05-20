package com.sbc.eia.bis.authorization;

import java.util.*;
import java.sql.*;
//import javax.sql.DataSource;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class acts as the DataSource Cache for Authorize class. 
 * 
 * Creation date: (6/5/2004)
 * @author rs8434
 * 
 *  History :
 *  Date      	| Author        	| Version	| Notes
 *  ----------------------------------------------------------------------------
 * 	06/05/2004	  Rajarsi Sarkar        1.0		  Creation.
 *	
 */class AuthDataSource
{
	private static AuthDataSource m_dsm = new AuthDataSource();
	private static HashMap m_cache = new HashMap();
	//private DataSource dsHome = null;
	private static HashMap m_sysProp = null;
	
	private AuthDataSource()
	{}
	
	public static AuthDataSource getInstance()
	{
		return m_dsm;	
	}
	
	public DBConnection getConnection(String aJdbcUserId, String aJdbcPassword, String jdbcUrl, String aDataSourceName, Logger aLogger) throws SQLException// String aJNDIName, String aURL)
	{
		if(m_cache.containsKey(aDataSourceName))
		{
			DBConnection dbCon = (DBConnection)m_cache.get(aDataSourceName);
			if(dbCon.getConnection()!=null)
			dbCon.disconnect();
			aLogger.log(LogEventId.DEBUG_LEVEL_2,"Returning DBConnection from repository for " + aDataSourceName);
			return dbCon;
		}
		else
		{
			return createConnection(aDataSourceName, aJdbcUserId, aJdbcPassword, aLogger);
		}
	}
	
	private synchronized DBConnection createConnection(String aDataSourceName, String aJdbcUserid, String aJdbcPassword, Logger aLogger) throws SQLException
	{
		String jdbcOption = "DATASOURCE";
		DBConnection conn = null;
		
		if(m_cache.containsKey(aDataSourceName))
		{				
			conn = (DBConnection)m_cache.get(aDataSourceName);
		}
		else
		{
			try
			{
				conn = new DBConnection ( aDataSourceName, aJdbcUserid, aJdbcPassword, "", "", jdbcOption, aLogger);
				conn.disconnect();
			}
			catch(SQLException sqle)
			{
				throw sqle;	
			}
			aLogger.log(LogEventId.DEBUG_LEVEL_2,"Adding newly created DBConnection to repository for " + aDataSourceName);
			m_cache.put(aDataSourceName, conn);
		}
		aLogger.log(LogEventId.DEBUG_LEVEL_2,"Returning DBConnection from repository for " + aDataSourceName);
		return conn;
	}
}
