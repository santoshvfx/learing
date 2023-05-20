//$Id: CingularTableLookupService.java ,v 1.7 2008/02/20 21:40:17 jd3462 Exp $

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
 * This class is used to perform table lookups for cingular information.  
 * The class need not be backed by a database.
 */
package com.sbc.eia.bis.lim.transactions.Cingular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * This class is used to perform table lookups for cingular information.  
 * The class need not be backed by a database.
 */
public class CingularTableLookupService
{
    private Properties s_stateToCasRegionTable = null;
    private static final String STATE_TO_CAS_REGION_FILE_KEY =
        "STATE_TO_CAS_REGION_FILE";


    /**
     * Default constructor necessary to create an instance of this type,
     * and to allow it to keep a reference to internally initialized tables.
     */
    public CingularTableLookupService(){
    }
        
    /**
     * Method lookupCasRegionLookupServiceByState.  This method determines
     * a SINGLE CAS region code for a specific state (and for certain states
     * billingMarket as well).  This method will either; return a single CAS
     * region code, a null reference, or throw an exception.
     * @param i_logger Logger a BIS logger
     * @param configurationInfo Properties properties file containing
     * information necessary to configure the data source.
     * @param i_stateCode String the state to use in determining CAS region
     * @param i_billingMarket String billingMarket is optional and can be
     * null, certain state codes return different CAS regions based on
     * billingMarket, while with others billingMarket has no relevance.
     * @return String the CAS region code or null if not found
     * @throws Exception when any errors occur during the searching process.
     * However, it does not mean that the CAS region code was not found,
     * it simply means that an error occured while trying to determined
     * the CAS region code.
     */
    public String lookupCasRegionLookupServiceByState(
        Logger i_logger,
        Properties configurationInfo,
        String i_stateCode,
        String i_billingMarket)
        throws Exception
    {
        String stateCode = i_stateCode.trim().toUpperCase();

        String billingMarket = null;
        
        /**
         * For some states BillingMarket isn't a
         * determining factor, so even null is acceptable,
         * therefore we must handle them
         */
        if( i_billingMarket == null )
        {
            billingMarket = "";
        }
        else
        {
            billingMarket = i_billingMarket.trim().toUpperCase();
        }

        if (s_stateToCasRegionTable == null)
        {
            s_stateToCasRegionTable =
                PropertiesFileLoader.read(
                    configurationInfo.getProperty(
                        STATE_TO_CAS_REGION_FILE_KEY),
                    i_logger);
            i_logger.log(
                LogEventId.DEBUG_LEVEL_1,
                "State to cas region lookup table initialized.");
        }

        i_logger.log(
            LogEventId.INFO_LEVEL_1,
            "Searching table using state,billingMarket <"
                + stateCode
                + ","
                + billingMarket
                + ">");

        String casRegionCode =
            s_stateToCasRegionTable.getProperty(
                stateCode + "," + billingMarket);

        // "*" is used as a match for all, including when billingMarket is ""
        if (casRegionCode == null)
        {
            i_logger.log(
                LogEventId.DEBUG_LEVEL_1,
                "Couldn't find <"
                    + stateCode
                    + ","
                    + billingMarket
                    + ">, will try with <" + stateCode + "," + "*>");
            casRegionCode =
                s_stateToCasRegionTable.getProperty(stateCode + ",*");
        }
        
        if(casRegionCode != null)
        {
            i_logger.log(
                LogEventId.INFO_LEVEL_1,
                "CAS region code found is " + casRegionCode + ".");
        }
        else
        {
            i_logger.log(
                LogEventId.INFO_LEVEL_1,
                "CAS region code not found.");
            casRegionCode = " ";
        }

        return casRegionCode;
    }

    /**
     * Method isSellableToChannel.  This method determines if a particular
     * postal code and sales channel combination is sellable or not.
     * @param i_logger Logger  a BIS logger
     * @param i_configProperties Properties configuration information used
     * to configure the data source
     * @param i_postalCode String a postal code
     * @param i_cingularSalesChannel String a sales channel
     * @return boolean indicates whether its possible to sell to the channel
     * and postal code
     * @throws Exception in the event an error occurs in determining
     * whether the channel and postal code is sellable.  It doesn't
     * necessarily mean the combination is not sellable, it just means that
     * it can't be determined due to an error.
     */
    public static boolean isSellableToChannel(
        Logger i_logger,
        Properties i_configProperties,
        String i_postalCode,
        String i_cingularSalesChannel)
        throws Exception
    {
        i_logger.log(
            LogEventId.INFO_LEVEL_1,
            "Determining is sellable for sales channel <"
                + i_cingularSalesChannel
                + "> and postal code <"
                + i_postalCode
                + ">.");

        String sql = "select SALES_CHANNEL, POSTAL_CODE from CHANNEL_ZC_TAB where SALES_CHANNEL=? and POSTAL_CODE=?";
        
        boolean retry = true;
        int retryCount = 0;
        boolean isSellable = false;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try 
        {
            while(retry) 
            {
                try 
                {
                    i_logger.log(LogEventId.AUDIT_TRAIL,"CingularTableLookupService::isSellableToChannel()|" +
                        "CingularTableLookupService::getConnectionToDataBase()|PRE");          
                    conn = getConnectionToDataBase(i_logger, i_configProperties);
                    i_logger.log(LogEventId.AUDIT_TRAIL,"CingularTableLookupService::isSellableToChannel()|" +
                        "CingularTableLookupService::getConnectionToDataBase()|POST");     
                    conn.setAutoCommit(true);
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, i_cingularSalesChannel);
                    preparedStatement.setString(2, i_postalCode);
                    i_logger.log(LogEventId.AUDIT_TRAIL,"CingularTableLookupService::isSellableToChannel()|" +
                        "PreparedStatement::executeQuery()|POST");   
                    rs = preparedStatement.executeQuery();
                    i_logger.log(LogEventId.AUDIT_TRAIL,"CingularTableLookupService::isSellableToChannel()|" +
                        "PreparedStatement::executeQuery()|POST");   
                    isSellable = rs.next();
                    retry = false;
                    if(isSellable)
                    {
                        i_logger.log(
                            LogEventId.INFO_LEVEL_1,
                            "Is sellable to Sales channel <"
                                + i_cingularSalesChannel
                                + "> and postal code <"
                                + i_postalCode
                                + ">.");
                    }
                    else
                    {
                        i_logger.log(
                            LogEventId.INFO_LEVEL_1,
                            "Is NOT sellable to Sales channel <"
                                + i_cingularSalesChannel
                                + "> and postal code <"
                                + i_postalCode
                                + ">.");
                    }

                } 
                catch (StaleConnectionException e) 
                {
                    i_logger.log( LogEventId.INFO_LEVEL_2, "isSellableToChannel: Get Connection failed with StaleConnection exception. RetryCount<" + retryCount + ">");
                    if ( retryCount++ > 1) 
                    {
                        retry = false;                             // done
                        e.printStackTrace();
                        i_logger.log( LogEventId.DEBUG_LEVEL_2, "isSellableToChannel: Get Connection failed with StaleConnection exception.");
                        throw new SQLException("Connection failed. " + e.getMessage());
                    }
                } 
                catch (SQLException e) 
                {
                    retry = false;                                 // done
                    throw e;
                }
            } // while
        }
        finally 
        {
            try 
            {
                if (rs != null)
                {
                    try 
                    {
                        rs.close();
                    } 
                    catch (SQLException e) {}
                    rs = null;
                }
                if (preparedStatement != null)
                {
                    try 
                    {
                        preparedStatement.close();
                    } 
                    catch (SQLException e) {}
                    preparedStatement = null;
                }
                if (conn != null)
                {
                    try 
                    {
                        conn.close();
                    } 
                    catch (SQLException e) {}
                    conn = null;
                }
                i_logger.log( LogEventId.DEBUG_LEVEL_2, "ResultSet, PreparedStatement, and Connection closed. Done.");
            } 
            catch (Exception e) 
            {
                i_logger.log(LogEventId.DEBUG_LEVEL_2, "isSellableToChannel: Exception occurred in finally with message " + e.getMessage());
            }
        }
        
        return isSellable;
    }

    /**
     * Method getConnectionToDataBase.
     * @param i_logger
     * @param i_configProperties
     * @return Connection
     */
    private static Connection getConnectionToDataBase(
        Logger i_logger,
        Properties i_configProperties)
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
    
        try 
        {
            jdbcDataSourceName =
                i_configProperties
                    .getProperty("jdbcDATA_SOURCE_NAME")
                    .trim();
            jdbcUsrId = i_configProperties.getProperty("jdbcUSERID").trim();
            jdbcPassWord =
                i_configProperties.getProperty("jdbcPASSWORD").trim();
            jdbcDriver =
                i_configProperties.getProperty("jdbcDRIVER").trim();
            useDataSourcePool =
                i_configProperties
                    .getProperty("jdbcUSE_CONNECTION_POOL")
                    .trim();
            jdbcUrl = i_configProperties.getProperty("jdbcURL").trim();
            
            dbConn = new DBConnection(  jdbcDataSourceName,
                                        jdbcUsrId,
                                        jdbcPassWord,
                                        jdbcUrl,
                                        jdbcDriver,
                                        useDataSourcePool,
                                        i_logger);
                                        
            tblConnection = dbConn.getConnection();
        } 
        catch (Exception e) 
        {
            i_logger.log(
                LogEventId.DEBUG_LEVEL_2,
                "Get JDBC properties failed. JDBC required tags are not defined in properties file. ");
        }
        return tblConnection;
    }
}
