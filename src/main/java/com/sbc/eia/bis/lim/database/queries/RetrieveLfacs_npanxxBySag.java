//$Id: RetrieveLfacs_npanxxBySag.java,v 1.11 2008/02/29 23:27:20 jd3462 Exp $
package com.sbc.eia.bis.lim.database.queries;

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
 * This class is used for retrieving the LFACS_npanxx
 * by SAG from the SAG_LFACS_WIRECENTER table.
 * Creation date: (1/11/02 2:57:22 PM)
 * @author: Sanjeev Verma
 */
public class RetrieveLfacs_npanxxBySag {

/**
 * Retrieves LFACS_NPANXX from the SAG_LFACS_WIRECENTER table by a SAG.
 * @return String
 * @param aSag String
 * @param aLogger Logger
 * @exception SQLException If the query command fails, it will throw a SQLException.
 */
public static String getLfacs_npanxxBySag(String aSag, Logger aLogger, DBConnection dbConn, Connection tblConnection) 
throws SQLException 
{
	PreparedStatement preparedSql = null;
	ResultSet rsltSet   = null;
	String retVal = null; 

	String SQLstatement = "select LFACS_NPANXX from SAG_LFACS_WIRECENTER where SAG_WIRECENTER = ?";
	
	//log variable
	int index = SQLstatement.indexOf((int)'?');
	StringBuffer logableStmt = new StringBuffer(SQLstatement);				
	logableStmt.deleteCharAt(index); logableStmt.insert(index,aSag);
	
	aLogger.log( LogEventId.DEBUG_LEVEL_2, "SQL cmd=<"+logableStmt+">" );
	
	try {
		/**
		 * access the Oracle database.
		 */ 
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Looking for Sag =" + aSag );
		preparedSql = tblConnection.prepareStatement(SQLstatement);
		preparedSql.setString(1, aSag.toUpperCase().trim());
        
        aLogger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
             dbConn.getUserId(), logableStmt.toString());
		rsltSet = preparedSql.executeQuery();
        aLogger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
             dbConn.getUserId(), logableStmt.toString());

		rsltSet.next();
		retVal = rsltSet.getString(1);
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Found LFACS_NPANXX=" + retVal + " SAG_WIRECENTER=" + aSag );
	}
	catch (SQLException e) {
		e.printStackTrace();
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get  Lfacs_npanxx By Sag failed...Sag =" + aSag );
		throw e;
			
	} finally {
		try {
			if (rsltSet != null)
				rsltSet.close();
			if (preparedSql != null)
				preparedSql.close();
		} catch (Exception e) {
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "getLfacs_npanxxBySag: Exception occured in finally with message " + e.getMessage());
		}
	}
	
	return retVal;
}
/**
 * Gets an database connection and calls getLfacs_npanxxBySag method to 
 * retrieve the  Lfacs_npanxx By Sag from the sag_lfacs_wirecenter table.
 * If the requested Lfacs_npanxx is found, that is 
 * returned to the client.
 * The following JDBC properties are required in the properties file:
 * <P>
 * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
 * jdbcUSERID = User Id<BR>
 * jdbcPASSWORD = Password<BR>
 * jdbcDRIVER = Drive name<BR>
 * jdbcURL = Database location for local driver<BR>
 * jdbcUSE_CONNECTION_POOL = True or False
 * <P>
 * @return String lfacs_npanxx
 * @param aProperty Properties
 * @param aSag String
 * @param aLogger Logger
 * @exception SQLException If the DBConnection method or the getLfacs_npanxxBySag
 * method detects an exception, SQLException will be thrown or rethrown.
 * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
 */
public static String retrieveLfacs_npanxxBySag(Properties aProperty, String aSag, Logger aLogger) 
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
				aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveLfacs_npanxxBySag: Calling DBConnection...");

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
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLfacs_npanxxBySag::retrieveLfacs_npanxxBySag()|" +
                        "DBConnection::getConnection()|PRE");           
                    tblConnection = dbConn.getConnection();	
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLfacs_npanxxBySag::retrieveLfacs_npanxxBySag()|" +
                        "DBConnection::getConnection()|POST");           
                } 
                else {
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection...");
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLfacs_npanxxBySag::retrieveLfacs_npanxxBySag()|" +
                        "DBConnection::getDSConnection()|PRE");           
                    tblConnection = dbConn.getDSConnection( jdbcDataSourceName, 
										     				jdbcUsrId,
										     				jdbcPassWord );
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLfacs_npanxxBySag::retrieveLfacs_npanxxBySag()|" +
                        "DBConnection::getDSConnection()|POST");           
				}	
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLfacs_npanxxBySag::retrieveLfacs_npanxxBySag()|" +
                    "RetrieveLfacs_npanxxBySag::getLfacs_npanxxBySag()|PRE");           
                retVal = getLfacs_npanxxBySag( aSag, aLogger, dbConn, tblConnection );
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLfacs_npanxxBySag::retrieveLfacs_npanxxBySag()|" +
                    "RetrieveLfacs_npanxxBySag::getLfacs_npanxxBySag()|POST");           
				
                bRetry = false;									// done
			}
			catch (StaleConnectionException e) {
				aLogger.log( LogEventId.INFO_LEVEL_2, "retrieveLfacs_npanxxBySag: Get DBConnection failed with StaleConnection exception. RetryCount<" + iRetryCount + ">");
				if ( iRetryCount++ > 1) {
					bRetry = false;								// done
					e.printStackTrace();
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveLfacs_npanxxBySag: Get DBConnection failed with StaleConnection exception.");
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
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveLfacs_npanxxBySag: DBConnection disconnect failed in finally with message " + e.getMessage());
		}
	}
	return retVal;
}
}