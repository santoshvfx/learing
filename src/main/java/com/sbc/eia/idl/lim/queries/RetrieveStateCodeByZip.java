// $Id: RetrieveStateCodeByZip.java,v 1.6 2003/05/09 20:27:54 as5472 Exp $

package com.sbc.eia.idl.lim.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * This class retrieves state code by zip code from an ZC table.
 * Creation date: (4/17/01 8:58:41 AM)
 * @author: Donald W. Lee
 */
public class RetrieveStateCodeByZip {

/**
 * Gets a database connection and calls getStateCodeByZip method to 
 * retrieve the State Code by Zip Code from the ZC table.
 * If the requested Zip Code is found, the corresponding State Code is 
 * returned to the client.
 * The following JDBC properties are required in the properties file:
 * <p>
 * jdbcDATA_SOURCE_NAME = Data Source Name <br>
 * jdbcUSERID = User Id <br>
 * jdbcPASSWORD = Password <br>
 * jdbcDRIVER = Drive name <br>
 * jdbcINITIAL_CONTEXT_FACTORY = jndi.CNInitialContextFactory package name <br>
 * jdbcCONTEXT_PROVIDER_URL = Database location for WebSphere to access <br>
 * jdbcURL = Database location for local driver <br>
 * jdbcUSE_CONNECTION_POOL = True or False <p>
 * Creation date: (4/16/01 11:08:36 AM)
 * @return String
 * @param aFile String
 * @param aZip String
 * @param aLogger Logger
 * @exception SQLException If the DBConnection method or the getStateCodeByZip
 * method detects an exception, SQLException will be thrown or rethrown.
 */
public static String retrieveStateCodeByZip(String aFile, String aZip, Logger aLogger) 
throws SQLException
{
	DBConnection dbConn = null;
	DataSource dsHome = null;
	Connection tblConnection = null;
	
	String retVal = null;

	try {
		/**
		 * Passes the JDBC access related properties in aFile to DBConnection method
		 * to access the Oracle database.
		 */
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Calling DBConnection... ");

		if ( dsHome == null ) {
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "Getting a new DBConnection... ");
			dbConn = new DBConnection( aFile, aLogger );
			dsHome = dbConn.getDsHome();				// save as cache entry
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
               "DBConnection::getConnection()|PRE");           
            tblConnection = dbConn.getConnection();
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
               "DBConnection::getConnection()|POST");           

		} else {
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection... ");
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
               "DBConnection::getDSConnection()|PRE");           
			
            tblConnection = dbConn.getDSConnection( "", 
										     		dbConn.getUserId(),
										     		dbConn.getPassWord()
										     		//"",
										     		//"" 
										     		);
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
               "DBConnection::getDSConnection()|POST");           

		}
        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
            "RetrieveStateCodeByZip::getStateCodeByZip()|PRE");           
		retVal = getStateCodeByZip( aZip, aLogger, dbConn, tblConnection );
        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
            "RetrieveStateCodeByZip::getStateCodeByZip()|POST");           

	}
	catch (SQLException e) {
		dsHome = null ;								// clear out cache
		throw e;
	}
	catch (Exception e) {
		e.printStackTrace();
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get DBConnection failed... ");
		throw new SQLException("DBConnection failed. " + e.getMessage());	
	}
	finally {
		try {
			dbConn.disconnect();
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "DBConnection disconnected. Done.");
		} catch (Exception e) {
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveStateCodeByZip: DBConnection disconnect failed in finally with message " + e.getMessage());
		}
	}
	return retVal;
}
/**
 * Gets a database connection and calls getStateCodeByZip method to 
 * retrieve the State Code by Zip Code from the ZC table.
 * If the requested Zip Code is found, the corresponding State Code is
 * returned to the client. <p>
 * The following JDBC properties are required in the properties file:
 * <p>
 * jdbcDATA_SOURCE_NAME = Data Source Name <br>
 * jdbcUSERID = User Id <br>
 * jdbcPASSWORD = Password <br>
 * jdbcDRIVER = Drive name <br>
 * jdbcURL = Database location for local driver <br>
 * jdbcUSE_CONNECTION_POOL = True or False <p>
 * Creation date: (4/18/01 3:14:59 PM)
 * @return String
 * @param aProperty Properties
 * @param aZip String
 * @param aLogger Logger
 * @exception SQLException If the DBConnection method or the getStateCodeByZip
 * method detects an exception, SQLException will be thrown or rethrown.
 * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
 */
public static String retrieveStateCodeByZip(Properties aProperty, String aZip, Logger aLogger) 
throws SQLException 
{
	DBConnection dbConn = null;
	DataSource dsHome = null;
	Connection tblConnection = null;
	
	String jdbcDataSourceName = "";
	String jdbcUsrId = "";
	String jdbcPassWord = "";
	String jdbcDriver = "";
	String useDataSourcePool = "";
	String jdbcUrl = "";

	try {
		jdbcDataSourceName = aProperty.getProperty("jdbcDATA_SOURCE_NAME").trim();
		jdbcUsrId = aProperty.getProperty("jdbcUSERID").trim();
		jdbcPassWord = aProperty.getProperty("jdbcPASSWORD").trim();
		jdbcDriver = aProperty.getProperty("jdbcDRIVER").trim();
		useDataSourcePool = aProperty.getProperty("jdbcUSE_CONNECTION_POOL").trim();
		jdbcUrl = aProperty.getProperty("jdbcURL").trim();
	} catch (Exception e) {
		aLogger.log(
			LogEventId.DEBUG_LEVEL_2,
			"Get JDBC properties failed. JDBC required tags are not defined in properties file. ");
		throw new SQLException("Get JDBC properties failed. JDBC required tags are not defined in properties file.");
	}

	String retVal = null;

	boolean bRetry  = true;
	int iRetryCount = 0;

	try {
		while( bRetry ) {
			try {
				/**
				 * Passes the JDBC access related properties to DBConnection() to
				 * access the Oracle database.
				 */
				aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveStateCodeByZip: Calling DBConnection...");

				if ( dsHome == null ) {
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "Getting a new DBConnection...");
					dbConn = new DBConnection(  jdbcDataSourceName,
												jdbcUsrId,
												jdbcPassWord,
												jdbcUrl,
												jdbcDriver,
												useDataSourcePool,
												aLogger);
					//dsHome = dbConn.getDsHome();				// Don't save as cache entry
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
                        "DBConnection::getConnection()|PRE");           
                    tblConnection = dbConn.getConnection();
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
                        "DBConnection::getConnection()|POST");           
				} 
                else {
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection...");
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
                        "DBConnection::getDSConnection()|PRE");           
                    tblConnection = dbConn.getDSConnection( jdbcDataSourceName, 
										     				jdbcUsrId,
										     				jdbcPassWord );
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
                        "DBConnection::getDSConnection()|POST");           
				}
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
                    "RetrieveStateCodeByZip::getStateCodeByZip()|PRE");           
				retVal = getStateCodeByZip( aZip, aLogger, dbConn, tblConnection );
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveStateCodeByZip::retrieveStateCodeByZip()|" +
                    "RetrieveStateCodeByZip::getStateCodeByZip()|POST");           

				bRetry = false;									// done
			}
			catch (StaleConnectionException e) {
				aLogger.log( LogEventId.INFO_LEVEL_2, "retrieveStateCodeByZip: Get DBConnection failed with StaleConnection exception. RetryCount<" + iRetryCount + ">");
				if ( iRetryCount++ > 1) {
					bRetry = false;								// done
					e.printStackTrace();
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveStateCodeByZip: Get DBConnection failed with StaleConnection exception.");
					throw new SQLException("DBConnection failed. " + e.getMessage());
				}
			}
			catch (SQLException e) {
				dsHome = null ;									// clear out cache
				bRetry = false;									// done
				throw e;
			}
		} // while
	}
	finally {
		try {
			dbConn.disconnect();
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "DBConnection disconnected. Done.");
		} catch (Exception e) {
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveStateCodeByZip: DBConnection disconnect failed in finally with message " + e.getMessage());
		}
	}
	return retVal;
}

/**
 * Gets State Code by a corresponding zip code from the ZC Oracle table.
 * Creation date: (4/18/01 4:02:35 PM)
 * @return String
 * @param aZip String
 * @param aLogger Logger
 * @exception SQLException If the query command fails, it will throw a SQLException.
 */
public static String getStateCodeByZip(String aZip, Logger aLogger, DBConnection dbConn, Connection tblConnection) 
throws SQLException 
{
	PreparedStatement preparedSql = null;
	ResultSet rsltSet   = null;
	String retVal = null; 

	String SQLstatement = "select STATE_CD from ZC where ZC=?";
	
	//log variable
	int index = SQLstatement.indexOf((int)'?');
	StringBuffer logableStmt = new StringBuffer(SQLstatement);				
	logableStmt.deleteCharAt(index); logableStmt.insert(index,aZip);
	
	aLogger.log( LogEventId.DEBUG_LEVEL_2, "SQL cmd=<"+logableStmt+">" );

	try {
		/**
		 * access the Oracle database.
		 */ 
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Looking for PostalCode=" + aZip );
		preparedSql = tblConnection.prepareStatement(SQLstatement);
		preparedSql.setString(1, aZip.toUpperCase().trim());
		
        aLogger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                dbConn.getUserId(), logableStmt.toString());
		rsltSet = preparedSql.executeQuery();
        aLogger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                dbConn.getUserId(), logableStmt.toString());
		
		rsltSet.next();
		retVal = rsltSet.getString(1);
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Found PostalCode=" + aZip + " State=" + retVal );
	}
	catch (SQLException e) {
		e.printStackTrace();
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get state_cd by Zip failed...aZip=" + aZip );
		throw e;
			
	} finally {
		try {
			if (rsltSet != null)
				rsltSet.close();
			if (preparedSql != null)
				preparedSql.close();
		} catch (Exception e) {
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "getStateCodeByZip: Exception occured in finally with message " + e.getMessage());
		}
	}
	
	return retVal;
}
}