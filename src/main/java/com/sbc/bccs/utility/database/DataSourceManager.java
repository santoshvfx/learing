// $Id: DataSourceManager.java,v 1.3 2003/04/01 21:36:50 ml2917 Exp $

package com.sbc.bccs.utility.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Manage Datasource for database connection pool.
 * Creation date: (4/27/01 11:12:27 AM)
 * @author: Hongmei Parkin
 
#   History :
#   Date      	| Author        | Version	| Notes
#   ----------------------------------------------------------------------------
# 	04/01/2003	Mark Liljequist 2.0			Update for datasource lookup.
#	
*/


public class DataSourceManager {

	private String dataSourceName = "";
	private String userId = "";
	private String passWord = "";
	private DataSource dsHome = null;
	private Logger logger; 
/**
 * Insert the method's description here.
 * Creation date: (4/27/01 12:08:24 PM)
 */
public DataSourceManager(Logger aLogger) {
	logger = aLogger;
}
/**
 * Take input and create Data Source
 * 
 */

public DataSourceManager(
	String aDataSourceName,
	String aUserId,
	String aPassWord,
	Logger aLogger) throws SQLException {

   this.logger = aLogger;
   this.dataSourceName = aDataSourceName;
   this.userId = aUserId;
   this.passWord = aPassWord;

   dsHome = createDataSource(aDataSourceName);
	
}

/**
 * Create Datasource.
 * Creation date: (4/26/01 5:22:59 PM)
 * @return javax.sql.DataSource
 */
public DataSource createDataSource(
	String aDataSourceName)
	throws SQLException {

	try {
		if (dsHome == null) {
			logger.log(LogEventId.DEBUG_LEVEL_1, "Get JDBC Data Source Name...");

			InitialContext initialDBContext = new InitialContext();

			logger.log(LogEventId.DEBUG_LEVEL_2, "Get Connection Pool...");
			dsHome = (DataSource) initialDBContext.lookup(aDataSourceName);
		}
	} catch (Exception e) {
		dsHome = null;

		throw new SQLException(e.getMessage());
	}

	return dsHome;

}
/**
 * Insert the method's description here.
 * Creation date: (4/27/01 11:44:23 AM)
 */
public DataSource getDsHome(){

	return dsHome;
}
/**
 * Get a new pool connection base on the created Data Source.
 * Creation date: (4/26/01 3:08:21 PM)
 */
public Connection getNewDSConnection() throws SQLException{
	
		if(dsHome != null) {
			try {
				return dsHome.getConnection(userId, passWord);
			} catch (SQLException e) {
				dsHome = null; // reset dsHome if failure
				logger.log(
					LogEventId.INFO_LEVEL_2,
					"Failed to get Database Connection Pool: " + e.getMessage());
				throw new SQLException("get Pool connection failed: " + e.getMessage());
			}
		}
		else
		throw new SQLException("Datasouce is null");
		
	}
}
