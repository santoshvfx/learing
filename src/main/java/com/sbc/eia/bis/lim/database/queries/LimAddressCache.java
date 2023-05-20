//$Id: LimAddressCache.java,v 1.16 2003/03/21 19:24:37 jd3462 Exp $
package com.sbc.eia.bis.lim.database.queries;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationException;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationHandler;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim.AddressMatchResult;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;

/**
 * @author as2362
 *
 * This class provides the DB connection and operations for LIM Address Caching.
 * Creation date: (1/30/03 2:10:35 PM)
 */
public class LimAddressCache
{	
    DBConnection dbConn = null;
    Connection tblConnection = null;
    Properties properties = null; 
    Logger logger = null;
    DataSource dsHome = null;
    
    /**
     * Gets a database connection and calls getBlobByLocationId/ServiceId method to 
     * retrieve the Location Data by locationId/Service from the LIM_ADDRESS_CACHE table.
     * If the Location Data is found for a locationId/serviceId, it is 
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
     * Creation date: (1/30/03 3:00:03 PM)
     * @param aProperty Properties
     * @param aLogger Logger
     * @exception SQLException If the DBConnection method or the getBlobByLocationId
     * method detects an exception, SQLException will be thrown or rethrown.
     * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
     * @exception RetrieveLocationException If the deserialization process fails
     */
    public LimAddressCache (Properties aProperty, Logger aLogger) 
        throws 
            SQLException, 
            RetrieveLocationException, 
            Exception
    {
    	properties = aProperty;
    	logger = aLogger;
    	
    	String jdbcDataSourceName = "";
    	String jdbcUsrId = "";
    	String jdbcPassWord = "";
    	String jdbcDriver = "";
    	String useDataSourcePool = "";
    	String jdbcUrl = "";
    
    	try 
    	{
    		jdbcDataSourceName = properties.getProperty("jdbcDATA_SOURCE_NAME").trim();
    		jdbcUsrId = properties.getProperty("jdbcUSERID").trim();
    		jdbcPassWord = properties.getProperty("jdbcPASSWORD").trim();
    		jdbcDriver = properties.getProperty("jdbcDRIVER").trim();
    		useDataSourcePool = properties.getProperty("jdbcUSE_CONNECTION_POOL").trim();
    		jdbcUrl = properties.getProperty("jdbcURL").trim();
    		
    		logger.log( LogEventId.DEBUG_LEVEL_2, "LimAddressCache: Calling DBConnection...");
    		dbConn = new DBConnection(  jdbcDataSourceName,
    											jdbcUsrId,
    											jdbcPassWord,
    											jdbcUrl,
    											jdbcDriver,
    											useDataSourcePool,
    											logger);
    		dsHome = dbConn.getDsHome();
    	} 
    	catch (Exception e) 
    	{
    		logger.log(
    			LogEventId.DEBUG_LEVEL_2,
    			"Get DBConnection failed. ");
    		e.printStackTrace();
    		throw new SQLException("Get DBConnection failed." + e.getMessage());
    	}
    }
    
    /**
     * Connect fron the database.
     */	
    public void connect ()
        throws SQLException
    {
    	boolean bRetry  = true;
    	int iRetryCount = 0;
    	
    	while( bRetry ) 
    	{	
    		try 
    		{
    			logger.log( LogEventId.DEBUG_LEVEL_2, "*** Connecting to the Cache Database...");
    			tblConnection = dbConn.getConnection();
    			logger.log( LogEventId.DEBUG_LEVEL_2, "DBConnection for Address Cache. Done.");
    		} 
    		catch (StaleConnectionException e) 
    		{
    			logger.log( LogEventId.DEBUG_LEVEL_2, "connect: Get Connection failed with StaleConnection exception.");
    			e.printStackTrace();
    			if ( iRetryCount++ > 1) 
    			{
    				dsHome = null;	
    				bRetry = false;
    				throw new SQLException("Connection failed. " + e.getMessage());
    			}							// done
    		}
    		catch (SQLException e) 
    		{
    			logger.log( LogEventId.DEBUG_LEVEL_2, "connect: Get Connection failed with SQL exception.");
    			e.printStackTrace();
    			dsHome = null ;
    			bRetry = false;									// clear out cache
    			throw e;
    		}
    		bRetry = false;
    	}
    	return;
    } 
    
    /**
     * Disconnect fron the database.
     */	
    public void disconnect ()
    {
    	try 
    	{
    		dbConn.disconnect();
    		logger.log( LogEventId.DEBUG_LEVEL_2, "DBConnection for Address Cache disconnected. Done.");
    	} 
    	catch (Exception e) 
    	{
    		logger.log(LogEventId.DEBUG_LEVEL_2, "retrieveLocationCacheByLocId:  DBConnection disconnect failed in finally with message " + e.getMessage());
    	}
    } 
    
    /**
     * Gets Location Data Blob by LocationId from the LIM_ADDRESS_CACHE table.
     * Creation date: (1/30/03 2:10:35 PM)
     * @return Byte Location Data
     * @param locationId String
     * @exception SQLException If the query command fails, it will throw a SQLException.
     * @author as2362
     */	
    public byte [] getBlobByLocationId (String locationId) 
        throws SQLException 
    {
    	
    	PreparedStatement preparedSql = null;
    	ResultSet rsltSet   = null;
    	Blob retVal = null; 
    
    	String SQLstatement =
    			"select LOCATION_DATA from LIM_ADDRESS_CACHE where LOCATION_ID = ?";
    			
    	//log variable
    	int index = SQLstatement.indexOf((int)'?');
    	StringBuffer logableStmt = new StringBuffer(SQLstatement);				
    	logableStmt.deleteCharAt(index); logableStmt.insert(index,locationId);
    	try 
        {
    		/**
    		 * access the Oracle database.
    		 */ 
    		logger.log( LogEventId.DEBUG_LEVEL_2, "Looking for locationId <" + locationId + ">");
    		preparedSql = tblConnection.prepareStatement(SQLstatement);
    		preparedSql.setString(1,locationId);
            
            logger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), logableStmt.toString());
    		rsltSet = preparedSql.executeQuery();
            logger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), logableStmt.toString());
    
    		if(rsltSet.next())
    		{
    			retVal = rsltSet.getBlob(1);
    			logger.log( LogEventId.INFO_LEVEL_1, "Found Location Data Blob for LocationId <" +locationId+ ">" );
    		}
            else
            {
                logger.log( LogEventId.INFO_LEVEL_1, "LocationId <" +locationId+"> not found in LIM_ADDRESS_CACHE");
            }
    	}
    	catch (SQLException e) 
        {
    		e.printStackTrace();
    		logger.log( LogEventId.DEBUG_LEVEL_2, "Get Blob by LocationId failed...LocationId=" + locationId );
    		throw e;
    			
    	} 
        finally 
        {
    		try 
            {
    			if (rsltSet != null)
    				rsltSet.close();
    			if (preparedSql != null)
    				preparedSql.close();
    		} 
            catch (Exception e) 
            {
    			logger.log(LogEventId.DEBUG_LEVEL_2, "getBlobByLocationId: Exception occurred in finally with message " + e.getMessage());
    		}
    	}
    	
    	if (retVal != null)
    	{
    		int len = (int)retVal.length();
    		return retVal.getBytes(1,len);
    	}
    	else
    		return null;
    }
    
    /**
     * Gets Location Data Blob by ServiceId from the LIM_ADDRESS_CACHE table.
     * Creation date: (1/30/03 2:15:35 PM)
     * @return Byte Location Data
     * @param serviceId String
     * @exception SQLException If the query command fails, it will throw a SQLException.
     * @author as2362
     */	
    public byte [] getBlobByServiceId (String serviceId) 
        throws SQLException 
    {
    	PreparedStatement preparedSql = null;
    	ResultSet rsltSet   = null;
    	Blob retVal = null; 
    
    	String SQLstatement =
    			"select LOCATION_DATA from LIM_ADDRESS_CACHE where SERVICE_ID = ?";	
    	
    	//log variable
    	int index = SQLstatement.indexOf((int)'?');
    	StringBuffer logableStmt = new StringBuffer(SQLstatement);				
    	logableStmt.deleteCharAt(index); logableStmt.insert(index,serviceId);
    	
    	try 
        {
    		/**
    		 * access the Oracle database.
    		 */ 
    		logger.log( LogEventId.DEBUG_LEVEL_2, "Looking for serviceId <" + serviceId+">");
    		preparedSql = tblConnection.prepareStatement(SQLstatement);
    		preparedSql.setString(1,serviceId);
            
            logger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), logableStmt.toString());
    		rsltSet = preparedSql.executeQuery();
            logger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), logableStmt.toString());
    		
    		// gets the 1st one in rsltSet
    		if(rsltSet.next())
    		{
    			retVal = rsltSet.getBlob(1);
    			logger.log( LogEventId.INFO_LEVEL_1, "Found Location Data Blob for ServiceId <" +serviceId+ ">" );
    		}else
    			logger.log( LogEventId.INFO_LEVEL_1, "ServiceId <" +serviceId+ "> not found in LIM_ADDRESS_CACHE");
    
    		// log occurence of multiple service_ids
    		if(rsltSet.next()) 
    			logger.log( LogEventId.INFO_LEVEL_1, "Multiple entries for Service_Id <" +serviceId+ "> found in LIM_ADDRESS_CACHE");
    	}
    	catch (SQLException e) 
        {
    		e.printStackTrace();
    		logger.log( LogEventId.DEBUG_LEVEL_2, "getBlobByServiceId failed...ServiceId=" + serviceId );
    		throw e;
    	} 
        finally 
        {
    		try 
            {
    			if (rsltSet != null)
    				rsltSet.close();
    			if (preparedSql != null)
    				preparedSql.close();
    		} 
            catch (Exception e) 
            {
    			logger.log(LogEventId.DEBUG_LEVEL_2, "getBlobByServiceId: Exception occurred in finally with message " + e.getMessage());
    		}
    	}
    	
    	int len = (int)retVal.length();
    	return retVal.getBytes(1,len);
    }
    
    
    /**
     * Inserts Location Data Blob to the LIM_ADDRESS_CACHE table.
     * Creation date: (1/30/03 2:15:35 PM)
     * @param locationId String
     * @param serviceId String
     * @param state String
     * @param locData byte []
     * @exception SQLException If the query command fails, it will throw a SQLException.
     * @author as2362
     */	
    public void insertLocDataBlob(String locationId, String serviceId, String state, byte [] locData) 
        throws Exception
    {
    	PreparedStatement preparedSql = null;
    	int rowCount = 0;
    	InputStream inStream = null;
    	OutputStream outStream = null;
    	int length = -1;
    	Date date = new Date(System.currentTimeMillis());
    	ResultSet lobDetails = null;
    	
    	String
            sqlNewRow = "insert into LIM_ADDRESS_CACHE (LOCATION_ID,SERVICE_ID,UPDATE_DATE,STATE,LOCATION_DATA) values (?, ?, ?, ?, EMPTY_BLOB())",
            sqlLockRow = "select LOCATION_DATA from LIM_ADDRESS_CACHE where LOCATION_ID = ? for update";
    		
    	logger.log(LogEventId.DEBUG_LEVEL_2, "locationId=<" + locationId + "> serviceId=<" + serviceId + "> state=<" + state + ">");
    	try 
        {
    		/**
    		 * access the Oracle database.
    		 */ 
    		tblConnection.setAutoCommit(false);
    
    		preparedSql = tblConnection.prepareStatement(sqlNewRow);
    		
    		preparedSql.setString(1, locationId);
    		
    		if(serviceId == null || serviceId == "")
    			preparedSql.setString(2, null);
    		else 
    			preparedSql.setString(2, serviceId);
    		
    		preparedSql.setDate(3, date);
    		preparedSql.setString(4, state);
            
            logger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), sqlNewRow);
    		rowCount = preparedSql.executeUpdate();
    		logger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), sqlNewRow);
           	
           	// lock row
           	preparedSql = tblConnection.prepareStatement(sqlLockRow);
           	preparedSql.setString(1,locationId);
           	logger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), sqlLockRow);
           	lobDetails = preparedSql.executeQuery();
    		logger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(), 
                    dbConn.getUserId(), sqlLockRow);
    		
    		// update Blob
         	lobDetails.next();
         	oracle.sql.BLOB dbBlob = (oracle.sql.BLOB)lobDetails.getBlob(1);
         	inStream = new ByteArrayInputStream(locData);
         	outStream = dbBlob.getBinaryOutputStream();
         	
         	int size = dbBlob.getBufferSize();
         	byte[] buffer = new byte[size];
    		length = -1;
    		
    		while ((length = inStream.read(buffer)) != -1)
    				outStream.write(buffer, 0, length);
    
    		inStream.close();
    		outStream.close();
    		
    		tblConnection.commit();
         	
    		lobDetails.close();
    
    		if(rowCount > 0)
    			logger.log( LogEventId.DEBUG_LEVEL_2, "Location Data Blob insertion successfully executed");
    		else
    			logger.log( LogEventId.DEBUG_LEVEL_2, "Location Data Blob insertion failed to execute");
    	} 
        catch (IOException e) 
        {
    		logger.log( LogEventId.DEBUG_LEVEL_2, "insertLocDataBlob failed with IOException:" + e.getMessage());
    		throw e;
    	} 
        catch (SQLException e) 
        {
    		tblConnection.rollback();
    		if(e.toString().indexOf("DuplicateKeyException")==-1)
    		{
    			logger.log( LogEventId.DEBUG_LEVEL_2, "insertLocDataBlob failed with a SQLException:" + e.getMessage());
    			e.printStackTrace();
    		}
    		else
    			logger.log( LogEventId.DEBUG_LEVEL_2, "insertLocDataBlob failed with a DuplicateKeyException");
    		throw e;
    	} 
        finally 
        {
    		try 
            {
    			if (lobDetails != null)
    				lobDetails.close();
    			if (preparedSql != null)
    				preparedSql.close();
    		} 
            catch (Exception e) 
            {
    			logger.log( LogEventId.DEBUG_LEVEL_2, "insertLocDataBlob failed: " + e.getMessage());
    			throw e;
    		}
    	}
    }
}
