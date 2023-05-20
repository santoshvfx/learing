//$Id: RetrieveAddrCountByWireCntrAndStNm.java,v 1.11 2005/05/03 22:14:12 jd3462 Exp $

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
 * Contains methods to retrieve addresses data by Wire Center and Street Name,
 * from the lfacs_sag_lu table.
 * Creation date: (01/16/02 8:58:41 AM)
 * @author: David Brawley
 */
public class RetrieveAddrCountByWireCntrAndStNm 
{
    /**
     * Contains methods to retrieve address array by WireCenter and SteetName from an lfacs_sag_lu table.
     * Creation date: 01/16/02 4:02:35 PM)
     * @return Address[]
     * @param wireCenter String
     * @param streetName String
     * @param houseNumber String
     * @param city String
     * @param unit Unit[]
     * @param aLogger Logger
     * @exception SQLException If the query command fails, it will throw a SQLException.
     */
    public static int getAddrCountByWireCntrAndStNm (
    	String wireCenter, 
    	String streetName, 
    	String houseNumber, 
    	String city, 
    	Logger aLogger,
    	DBConnection dbConn,
    	Connection tblConnection) 
    throws SQLException 
    {
    	PreparedStatement preparedSql = null;
    	ResultSet rsltSet   = null;
    	String cityNm = "";
    	
    	StringBuffer whereClause = new StringBuffer("WHERE LFACS_WIRECENTER=? AND STREET_NAME=?");
    		
    	if (houseNumber != null)
    		whereClause.append(" AND STREET_NUMBER" + (((houseNumber.trim()).length() > 0) ? "='" + houseNumber.toUpperCase().trim() + "'" : " IS NULL"));
    	else
    		whereClause.append(" AND STREET_NUMBER IS NULL");
    
    
    	if (city != null){
    		cityNm = sqlMarkUp(city);
    		whereClause.append(" AND TOWN" + (((cityNm.trim()).length() > 0) ? "='" + cityNm.toUpperCase().trim() + "'" : " IS NULL"));
    	}
    	else
    		whereClause.append(" AND TOWN IS NULL");
    		
    	String sqlSelect = "SELECT COUNT(*) FROM LFACS_SAG_LU_STATUS ";
    	
    	String SQLstatement = sqlSelect + whereClause;
    	
    	//log variable
    	int index = whereClause.toString().indexOf((int)'?');
    	int index2 = whereClause.toString().indexOf((int)'?',index+1);
    	StringBuffer logableStmt = whereClause;				
    	logableStmt.deleteCharAt(index); logableStmt.insert(index,wireCenter.toUpperCase()+' ');
    	logableStmt.deleteCharAt(index2+wireCenter.length()); logableStmt.insert(index2+wireCenter.length(),streetName.toUpperCase());
    	
    	aLogger.log( LogEventId.DEBUG_LEVEL_2, "SQL cmd=<"+sqlSelect+logableStmt+">" );
    	
    		
    	try {
    		// access the Oracle lfacs_sag_lu_status table.
    		//
    		aLogger.log (LogEventId.DEBUG_LEVEL_2, "Looking for: " + logableStmt);  
    		preparedSql = tblConnection.prepareStatement (SQLstatement);
    		preparedSql.setString(1, wireCenter.toUpperCase().trim()); // lfacs_wirecenter var
    		preparedSql.setString(2, streetName.toUpperCase().trim()); // street_name var
    
            aLogger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(),
                              dbConn.getUserId(), sqlSelect+logableStmt);
    		rsltSet = preparedSql.executeQuery();
    		
            aLogger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(),
                              dbConn.getUserId(), sqlSelect+logableStmt);
    		
    		while (rsltSet.next())
    		{
    			aLogger.log (LogEventId.DEBUG_LEVEL_2, "Address Count = " + rsltSet.getInt(1));
    			return rsltSet.getInt(1);
    		}
    	} // end try
    	catch (SQLException e) {
    		e.printStackTrace();
    		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get Address Count by Wire Center and Street Name failed." );
    		throw e;
    			
    	} finally {
    		try {
    			if (rsltSet != null)
    				rsltSet.close();
    			if (preparedSql != null)
    				preparedSql.close();
    		} catch (Exception e) {
    			aLogger.log(LogEventId.DEBUG_LEVEL_2, "getAddrCountByWireCntrAndStNm: Exception occured in finally with message " + e.getMessage());
    		}
    	}
    	return 0;
    }
    
    /**
     * WARNING --- In order to use this method, you'll need to create new properties file.
     * =======
     * Gets a database connection and calls getAddrCountByWireCntrAndStNm method to retrieve
     * the Address Count by Street Name and Wire Center from the lfacs_sag_lu_status table
     * If the requested Address is found, the corresponding Data or array of data is returned to the client. 
     * The following JDBC properties are required in the properties file:
     * <P>
     * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
     * jdbcUSERID = User Id<BR>
     * jdbcPASSWORD = Password<BR>
     * jdbcDRIVER = Drive name<BR>
     * jdbcURL = Database location for local driver<BR>
     * jdbcUSE_CONNECTION_POOL = True or False
     * <P>
     * Creation date: (08/23/01 11:08:36 AM)
     * @return int
     * @param aFile String
     * @param wireCenter String
     * @param streetName String
     * @param houseNumber String
     * @param city String
     * @param units Unit[]
     * @param aLogger Logger
     * @exception SQLException If the DBConnection method or the getAddrCountByWireCntrAndStNm
     * method detects an exception, SQLException will be thrown or rethrown.
     */
    public static int retrieveAddrCountByWireCntrAndStNm (
    		String aFile,  
    		String wireCenter, 
    		String streetName, 
    		String houseNumber, 
    		String city, 
    		Logger aLogger) 
    throws SQLException
    {
    	
    	DBConnection dbConn = null;
    	DataSource dsHome = null;
    	Connection tblConnection = null;
    	
    	int retVal = 0;
    	
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
    			
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                   "DBConnection::getConnection()|PRE");           
                tblConnection = dbConn.getConnection();
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                   "DBConnection::getConnection()|POST");
    		} else {
    			aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection... ");
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                   "DBConnection::getDSConnection()|PRE");			
                tblConnection = dbConn.getDSConnection( "", 
    										     		dbConn.getUserId(),
    										     		dbConn.getPassWord()
    										     		//"",
    										     		//"" 
    										     		);
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                   "DBConnection::getDSConnection()|POST");
    		}
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                "RetrieveAddrCountByWireCntrAndStNm::getAddrCountByWireCntrAndStNm()|PRE");		
            retVal = getAddrCountByWireCntrAndStNm (wireCenter, streetName, houseNumber, city, aLogger, dbConn, tblConnection);
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                "RetrieveAddrCountByWireCntrAndStNm::getAddrCountByWireCntrAndStNm()|POST");     
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
    			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveAddrCountByWireCntrAndStNm: DBConnection disconnect failed in finally with message " + e.getMessage());
    		}
    	}
    	return retVal;
    }
    
    /**
     * Gets a database connection and calls getAddrCountByWireCntrAndStNm method to retrieve
     * the Address Count by Street Name and Wire Center from the lfacs_sag_lu_status table.
     * If the requested Address is found, the corresponding Data or array of data is returned to the client.
     * The following JDBC properties are required in the properties file:
     * <P>
     * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
     * jdbcUSERID = User Id<BR>
     * jdbcPASSWORD = Password<BR>
     * jdbcDRIVER = Drive name<BR>
     * jdbcURL = Database location for local driver<BR>
     * jdbcUSE_CONNECTION_POOL = True or False
     * <P>
     * Creation date: (08/23/01 3:14:59 PM)
     * @return int
     * @param aProperty Properties
     * @param wireCenter String
     * @param streetName String
     * @param houseNumber String
     * @param city String
     * @param units Unit[]
     * @param aLogger Logger
     * @exception SQLException If the DBConnection method or the getAddrCountByWireCntrAndStNm
     * method detects an exception, SQLException will be thrown or rethrown.
     * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
     */
    public static int retrieveAddrCountByWireCntrAndStNm (
    		Properties aProperty, 
    		String wireCenter, 
    		String streetName, 
    		String houseNumber, 
    		String city, 
    		Logger aLogger) 
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
    	int retVal = 0;
    
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
    
    
    
    	boolean bRetry  = true;
    	int iRetryCount = 0;
    
    	try {
    		while( bRetry ) {
    			try {
    				/**
    				 * Passes the JDBC access related properties to DBConnection() to
    				 * access the SYBASE-MORTEL database.
    				 */
    				aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveAddrCountByWireCntrAndStNm: Calling DBConnection...");
    
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
                        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                            "DBConnection::getConnection()|PRE");           
    					tblConnection = dbConn.getConnection();
                       aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                            "DBConnection::getConnection()|POST");           
    				} 
                    else {
    					aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection...");
                        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                            "DBConnection::getDSConnection()|PRE");          
                        tblConnection = dbConn.getDSConnection( jdbcDataSourceName, 
    										     				jdbcUsrId,
    										     				jdbcPassWord );
                        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                            "DBConnection::getDSConnection()|POST");          
    				}
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                        "RetrieveAddrCountByWireCntrAndStNm::getAddrCountByWireCntrAndStNm()|PRE");     
    				retVal = getAddrCountByWireCntrAndStNm (wireCenter, streetName, houseNumber, city, aLogger, dbConn, tblConnection);
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrCountByWireCntrAndStNm::retrieveAddrCountByWireCntrAndStNm()|" +
                        "RetrieveAddrCountByWireCntrAndStNm::getAddrCountByWireCntrAndStNm()|POST");     
    				bRetry = false;					
                    // done
    			}
    			catch (StaleConnectionException e) {
    				aLogger.log( LogEventId.INFO_LEVEL_2, "retrieveAddrCountByWireCntrAndStNm: Get DBConnection failed with StaleConnection exception. RetryCount<" + iRetryCount + ">");
    				if ( iRetryCount++ > 1) {
    					bRetry = false;								// done
    					e.printStackTrace();
    					aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveAddrCountByWireCntrAndStNm: Get DBConnection failed with StaleConnection exception.");
    					throw new SQLException("DBConnection failed. " + e.getMessage());
    				}
    			}
    			catch (SQLException e) {
    				bRetry = false;									// done
    				dsHome = null ;									// clear out cache
    				throw e;
    			}
    		} // while
    	}
    	finally {
    		try {
    			dbConn.disconnect();
    			aLogger.log( LogEventId.DEBUG_LEVEL_2, "DBConnection disconnected. Done.");
    		} catch (Exception e) {
    			aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveAddrCountByWireCntrAndStNm: DBConnection disconnect failed in finally with message " +  e.getMessage());
    		}
    	}
    	return retVal;
    }
    
    /**
     * Insert single quote where imbedded in string value.
     * Creation date: (3/8/02 12:19:14 PM)
     * @return String
     * @param value String
     */
    private static String sqlMarkUp(String value) {
    
    	if ((value == null) ||
    		(value.length() == 0))
    		return value;
    
    	String temp = new String(value);
    	StringBuffer valueBuffer = new StringBuffer(value);
    	int i = 0;
    		
    	while(i < temp.length()){
    		if (temp.indexOf("'", i) >= 0){
    			i = temp.indexOf("'", i);
    			valueBuffer.insert(i, "'");
    			temp = valueBuffer.toString();
    			i = i + 2;
    		}
    		else
    			i = temp.length();
    	} // end while
    	
    	return valueBuffer.toString();
    }  
}