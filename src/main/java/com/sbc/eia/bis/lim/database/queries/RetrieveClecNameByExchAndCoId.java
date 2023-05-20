// $Id: RetrieveClecNameByExchAndCoId.java,v 1.10 2004/12/06 19:21:16 biscvsid Exp $

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
 * This class retrieves Clec Name
 * by Exch and Co Id.
 * Creation date: (4/17/01 8:58:41 AM)
 * @author: David Brawley
 */
public class RetrieveClecNameByExchAndCoId {

    
/**
 * Gets the clec name for a exchange code  and co id from clec_exco table.
 * Creation date: (4/18/01 4:02:35 PM)
 * @return String a clec name
 * @param aExch String
 * @param aCoId String
 * @param aLogger Logger
 * @exception SQLException If the query command fails, it will throw a SQLException.
 */
public static String getClecNameByExchAndCoId(String aExch, String aCoId, Logger aLogger, DBConnection dbConn, Connection tblConnection) 
throws SQLException 
{
	PreparedStatement preparedSql = null;
	ResultSet rsltSet   = null;
	String clecName = "";
	
	String SQLstatement = "select CLEC_NAME from CLEC_EXCO where EXCH_ID=? and CO_ID=?";
	
	//log variable
	int index = SQLstatement.indexOf((int)'?');
	int index2 = SQLstatement.indexOf((int)'?',index+1);
	StringBuffer logableStmt = new StringBuffer(SQLstatement);				
	logableStmt.deleteCharAt(index); logableStmt.insert(index,aExch+' ');
	logableStmt.deleteCharAt(index2+aExch.length()); logableStmt.insert(index2+aExch.length(),aCoId);
	
	aLogger.log( LogEventId.DEBUG_LEVEL_2, "SQL cmd=<"+logableStmt+">" );

	try {
		/**
		 * access the Oracle database.
		 */ 
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Looking for Exch<" + aExch.toUpperCase() + 
			"> and Co Id<" + aCoId.toUpperCase() + ">");
		preparedSql = tblConnection.prepareStatement(SQLstatement);
		preparedSql.setString(1, aExch.toUpperCase().trim());
		preparedSql.setString(2, aCoId.toUpperCase().trim());

        aLogger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                dbConn.getUserId(), logableStmt.toString());
		rsltSet = preparedSql.executeQuery();
        aLogger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(),
                dbConn.getUserId(), logableStmt.toString());
	
		while (rsltSet.next())
		{
			aLogger.log (LogEventId.DEBUG_LEVEL_2, "Found Clec_Name<" + rsltSet.getString("CLEC_NAME") + ">");
			clecName = rsltSet.getString("CLEC_NAME");
		}// end while
	} // end try
	catch (SQLException e) {
		e.printStackTrace();
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get ClecName by Exch and CoId failed...aExch<" + 
			aExch + "> aCoId<" + aCoId + ">");
		throw e;
			
	} finally {
		try {
			if (rsltSet != null)
				rsltSet.close();
			if (preparedSql != null)
				preparedSql.close();
		} catch (Exception e) {
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "getClecNameByExchAndCoId: Exception occured in finally with message " + e.getMessage());
		}
	}
	
	return clecName.trim();
}
/**
 * Gets a database connection and calls getClecNameByExchAndCoId method to 
 * retrieve the clec name for a exch and co id from the clec_exco table
 * table.
 * If the requested clec name is found, returned to the client.
 * The following JDBC properties are required in the properties file:
 * <P>
 * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
 * jdbcUSERID = User Id<BR>
 * jdbcPASSWORD = Password<BR>
 * jdbcDRIVER = Drive name<BR>
 * jdbcURL = Database location for local driver<BR>
 * jdbcUSE_CONNECTION_POOL = True or False
 * <P>
 * Creation date: (4/16/01 11:08:36 AM)
 * @return String a clec name
 * @param aFile String
 * @param aExch String
 * @param aCoId String
 * @param aLogger Logger
 * @exception SQLException If the DBConnection method or the getClecNameByExchAndCoId
 * method detects an exception, SQLException will be thrown or rethrown.
 */
public static String retrieveClecNameByExchAndCoId(String aFile, String aExch, String aCoId, Logger aLogger) 
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
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                "DBConnection::getConnection()|PRE");			
            tblConnection = dbConn.getConnection();
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                "DBConnection::getConnection()|POST");           

		} else {
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection... ");
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                "DBConnection::getDSConnection()|PRE");           
            tblConnection = dbConn.getDSConnection( "", 
										     		dbConn.getUserId(),
										     		dbConn.getPassWord()
										     		//"",
										     		//"" 
										     		);
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                "DBConnection::getDSConnection()|POST");           

		}
        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
            "RetrieveClecNameByExchAndCoId::getClecNameByExchAndCoId()|PRE");           
		retVal = getClecNameByExchAndCoId( aExch, aCoId, aLogger, dbConn, tblConnection );
        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
            "RetrieveClecNameByExchAndCoId::getClecNameByExchAndCoId()|POST");           
	
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
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveClecNameByExchAndCoId: DBConnection disconnect failed in finally with message " + e.getMessage());
		}

	}
	return retVal;
}
/**
 * Gets a database connection and calls getClecNameByExchAndCoId method to 
 * retrieve the clec name for a exch and co id from the clec_exco table
 * table.
 * If the requested clec name is found, returned to the client.
 * The following JDBC properties are required in the properties file:
 * <P>
 * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
 * jdbcUSERID = User Id<BR>
 * jdbcPASSWORD = Password<BR>
 * jdbcDRIVER = Drive name<BR>
 * jdbcURL = Database location for local driver<BR>
 * jdbcUSE_CONNECTION_POOL = True or False<BR>
 * <P>
 * Creation date: (4/18/01 3:14:59 PM)
 * @return String a clec name
 * @param aProperty Properties
 * @param aExch String
 * @param aCoId String
 * @param aLogger Logger
 * @exception SQLException If the DBConnection method or the getClecNameByExchAndCoId
 * method detects an exception, SQLException will be thrown or rethrown.
 * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
 */
public static String retrieveClecNameByExchAndCoId(Properties aProperty, String aExch, String aCoId, Logger aLogger) 
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
	String jdbcContextFactory = "";
	String jdbcContextUrl = "";
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
				aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveClecNameByExchAndCoId: Calling DBConnection...");

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
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                        "DBConnection::getConnection()|PRE");           
                    tblConnection = dbConn.getConnection();
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                        "DBConnection::getConnection()|POST");           
	
				} 
                else {
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection...");
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                        "DBConnection::getDSConnection()|PRE");           
                    tblConnection = dbConn.getDSConnection( jdbcDataSourceName, 
										     				jdbcUsrId,
										     				jdbcPassWord );
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                        "DBConnection::getDSConnection()|POST");           
				}
                
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                    "RetrieveClecNameByExchAndCoId::getClecNameByExchAndCoId()|PRE");           
			    retVal = getClecNameByExchAndCoId( aExch, aCoId, aLogger, dbConn, tblConnection );
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveClecNameByExchAndCoId::retrieveClecNameByExchAndCoId()|" +
                    "RetrieveClecNameByExchAndCoId::getClecNameByExchAndCoId()|POST");           
                
                bRetry = false;									// done
			}
			catch (StaleConnectionException e) {
				aLogger.log( LogEventId.INFO_LEVEL_2, "retrieveClecNameByExchAndCoId: Get DBConnection failed with StaleConnection exception. RetryCount<" + iRetryCount + ">");
				if ( iRetryCount++ > 1) {
					bRetry = false;								// done
					e.printStackTrace();
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveClecNameByExchAndCoId: Get DBConnection failed with StaleConnection exception.");
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
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveClecNameByExchAndCoId: DBConnection disconnect failed in finally with message " + e.getMessage());
		}

	}
	return retVal;
}
}
