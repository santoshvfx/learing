//$Id: RetrieveBRMSSagInfo.java,v 1.11 2005/05/03 22:14:12 jd3462 Exp $
package com.sbc.eia.bis.lim.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import javax.sql.DataSource;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * This class provides the DB Connection and database related methods
 * for retireve location for Address transactions.
 * Creation date: (08/16/02 8:58:41 AM)
 * @author: Rachel Zadok
 */
public class RetrieveBRMSSagInfo 
{
	DBConnection dbConn = null;
	Connection sqlConnection = null;
	Properties properties = null;
	Logger logger = null;
	DataSource dsHome = null;
	Vector infoVec = null;

	/**
	 * RetrieveBRMSSagInfo Constructor.
	 * The following JDBC properties are required in the properties file:
	 * <P>
	 * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
	 * jdbcUSERID = User Id<BR>
	 * jdbcPASSWORD = Password<BR>
	 * jdbcDRIVER = Drive name<BR>
	 * jdbcURL = Database location for local driver<BR>
	 * jdbcUSE_CONNECTION_POOL = True or False
	 * <P>
	 * @param aProperty Properties
	 * @param aLogger Logger
	 * @exception SQLException In the DBConnection method, SQLException will be
	 * thrown or rethrown. SQLException will be thrown if the required JDBC
	 * properties are not defined or in the case of StaleConnectionException .
	 */
	public RetrieveBRMSSagInfo (Properties aProperty, Logger aLogger)
		throws SQLException 
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
			jdbcDataSourceName = properties.getProperty("BRMS_DATA_SOURCE_NAME").trim();
			jdbcUsrId = properties.getProperty("BRMS_USERID").trim();
			jdbcPassWord = properties.getProperty("BRMS_PASSWORD").trim();
			jdbcDriver = properties.getProperty("BRMS_DRIVER").trim();
			useDataSourcePool = properties.getProperty("BRMS_USE_CONNECTION_POOL").trim();
			jdbcUrl = properties.getProperty("BRMS_URL").trim();

			logger.log (LogEventId.DEBUG_LEVEL_2, "RetrieveBRMSSagInfo: Calling DBConnection...");
			dbConn =
				new DBConnection(
					jdbcDataSourceName,
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
			logger.log (LogEventId.DEBUG_LEVEL_2, "RetrieveBRMSSagInfo: "
					+ "Get DBConnection failed. " + e.getMessage());
			throw new SQLException (
				"RetrieveBRMSSagInfo: Get DBConnection failed." + e.getMessage());
		}
	}

	/**
	 * Connect to the database.
	 */
	public void connect() throws SQLException 
	{
		boolean bRetry = true;
		int iRetryCount = 0;

		while (bRetry) 
		{
			try 
			{
				logger.log (LogEventId.DEBUG_LEVEL_2, "RetrieveBRMSSagInfo::connect()|PRE");
				sqlConnection = dbConn.getConnection();
				logger.log (LogEventId.DEBUG_LEVEL_2, "RetrieveBRMSSagInfo::connect()|POST");
			} 
			catch (StaleConnectionException e) 
			{
				logger.log (LogEventId.DEBUG_LEVEL_2, "RetrieveBRMSSagInfo::connect()|"
						+ " getConnection() failed with StaleConnection exception.");

				if (iRetryCount++ > 1) 
				{
					dsHome = null;
					bRetry = false;
					throw new SQLException ("Connection failed. " + e.getMessage());
				} // done
			} 
			catch (SQLException e) 
			{
				logger.log (LogEventId.DEBUG_LEVEL_2, "RetrieveBRMSSagInfo::connect()|"
					+ " Get Connection failed with SQL exception." + e.getMessage());
				dsHome = null;
				bRetry = false; // clear out cache
				throw e;
			}
			bRetry = false;
		}
		return;
	}

	/**
	 * Disconnect from the database.
	 */
	public void disconnect() {
		try {
			if (sqlConnection != null)
				sqlConnection.close();

			if (dbConn != null) 
			{
				dbConn.disconnect();
				dbConn = null;
			}
			logger.log (LogEventId.DEBUG_LEVEL_2, "RetrieveBRMSSagInfo::disconnect()| "
				+ "DBConnection disconnected. Done.");
		} 
		catch (Exception e) 
		{
			logger.log (LogEventId.DEBUG_LEVEL_2,
				"RetrieveBRMSSagInfo::disconnect()|" + " DBConnection disconnect failed in finally with message "
				+ e.getMessage());
		}
	}

	/**
	 * Method getBRMSSagInfo.
	 * @param streetName
	 * @param zipCode
	 * @param exchange
	 * @param communityInfo []
	 * @param isLike 
	 * @return BRMSSagInfo []
	 * @throws SQLException
	 */
	public BRMSSagInfo[] getBRMSSagInfo (String streetName, String exchange, CommunityInfo[] communityInfo, boolean isLike)
	throws SQLException 
	{
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		String sqlStatement = "";
		infoVec = new Vector();

		try 
		{
			if (communityInfo != null && communityInfo.length > 0) 
			{
				for (int i = 0; i < communityInfo.length; i++) 
				{
					if (streetName != null) 
					{							
						preparedStmt = getPreparedStmt (streetName.toUpperCase().trim(),
							communityInfo[i], null, isLike);
						if (preparedStmt != null) 
						{
							resultSet = preparedStmt.executeQuery();
							getBrmsInfo (resultSet);
						}
					} // end if for streetname check
				} //end for
			} //end if
			
			else if (exchange != null && !exchange.equals(""))
				if (streetName != null) 
				{							
					preparedStmt = getPreparedStmt (streetName.toUpperCase().trim(), null,
						exchange.trim(), isLike);
					if (preparedStmt != null) 
					{
						resultSet = preparedStmt.executeQuery();
						getBrmsInfo (resultSet);
					}
				} // end if for streetname check			
			
			logger.log (LogEventId.INFO_LEVEL_1, "RetrieveBRMSSagInfo::getBRMSSagInfo()| "
				+ (infoVec != null ? infoVec.size() : 0) + " Rows retrieved.");
		} // end try
		catch (SQLException sql) 
		{
			logger.log (LogEventId.DEBUG_LEVEL_2,
				"RetrieveBRMSSagInfo::getBRMSSagInfo()|Failed. " + sql.getMessage());
			throw sql;		
		} 
		finally 
		{
			try 
			{
				if (resultSet != null)
					resultSet.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} 
			catch (Exception e) 
			{
				logger.log (LogEventId.DEBUG_LEVEL_2,
					"RetrieveBRMSSagInfo::getBRMSSagInfo()|"
					+ "Exception occurred in finally with message."
					+ e.getMessage());
			}
		}

		if (infoVec != null && !infoVec.isEmpty()) 
		{
			BRMSSagInfo[] brmsSagInfo = new BRMSSagInfo[infoVec.size()];
			return (BRMSSagInfo[]) infoVec.toArray(brmsSagInfo);
		} 
		else
			return null;
	}

	/**
	 * Method getBrmsInfo.
	 * @param resultSet
	 * @return Vector
	 * @throws SQLException
	 */
	private void getBrmsInfo (ResultSet resultSet) throws SQLException 
	{
		boolean flag = resultSet.next();
		for (; flag; flag = resultSet.next()) 
		{
			BRMSSagInfo brmsInfo = new BRMSSagInfo();
			
			int recordId = resultSet.getInt("RECORD_ID");
			brmsInfo.recordId = recordId;

			int recordIdCtr = resultSet.getInt("RECORD_ID_CTR");
			brmsInfo.recordIdCtr = recordIdCtr;
			
			String street = resultSet.getString("STREET");
			brmsInfo.street = trim(street);

			String city = resultSet.getString("CITY");
			brmsInfo.city = trim(city);

			String exchange = resultSet.getString("EXCHANGE");
			brmsInfo.exchange = trim(exchange);

			String lowStreetRange = resultSet.getString("LOW_STREET_RANGE");
			brmsInfo.lowStreetRange = trim(lowStreetRange);

			String highStreetRange = resultSet.getString("HIGH_STREET_RANGE");
			brmsInfo.highStreetRange = trim(highStreetRange);

			String townTaxCode = resultSet.getString("TOWN_TAX_CODE");
			brmsInfo.townTaxCode = trim(townTaxCode);

			String zipCode = resultSet.getString("ZIP_CODE");
			brmsInfo.zipCode = trim(zipCode);

			if (trim(zipCode).length() > 0 && trim(zipCode).length() < 5)
				brmsInfo.zipCode = padStringStart(zipCode, 5, '0');

			String wireCenter = resultSet.getString("WIRE_CENTER");
			brmsInfo.wireCenter = trim(wireCenter);

			String remarks = resultSet.getString("REMARKS");
			brmsInfo.remarks = trim(remarks);

			String levelInd = resultSet.getString("LEVEL_IND");
			brmsInfo.levelInd = trim(levelInd);

			infoVec.add(brmsInfo);

			logger.log(LogEventId.DEBUG_LEVEL_2, logBrmsInfo (brmsInfo));
		}
		return;
	}

	/**
	 * Method logBrmsInfo.
	 * @param brmsInfo
	 * @return String
	 */
	private String logBrmsInfo(BRMSSagInfo brmsInfo) 
	{
			return (
			"Found the following Record: RecordId=[" + brmsInfo.recordId + "]" 
				+ "RecordIDctr=[" + brmsInfo.recordIdCtr + "]" 
				+ "StreetName=[" + brmsInfo.street + "]"
				+ "City=[" + brmsInfo.city + "]"
				+ "Exchange=[" + brmsInfo.exchange + "]"				
				+ "Lowstreetrange=[" + brmsInfo.lowStreetRange + "]"
				+ "Highstreetrange=[" + brmsInfo.highStreetRange + "]"
				+ "Towntaxcode=[" + brmsInfo.townTaxCode + "]"
				+ "ZipCode=[" + brmsInfo.zipCode + "]"
				+ "Wirecenter=[" + brmsInfo.wireCenter + "]"
				+ "Remarks=[" + brmsInfo.remarks + "]"
				+ "LevelInd=[" + brmsInfo.levelInd + "]");
	}

	/**
	 * Method trim.
	 * @param street
	 * @return String
	 */
	private String trim(String string) 
	{
		if (string != null && string.length() > 0)
			string = string.trim();
		else
			string = "";
		return string;
	}

	/**
	 * Method getPreparedStmt.
	 * @param streetName
	 * @param zipCode
	 * @param communityInfo
	 * @param exchange	
	 * @param isLike
	 * @param isZipCode
	 * @return PreparedStatement
	 */
	private PreparedStatement getPreparedStmt (String streetName,
		CommunityInfo communityInfo, String exchange, boolean isLike)
	throws SQLException 
	{
		String whereClause = "";
		PreparedStatement preparedStmt = null;

		String sqlStatement =
			"SELECT CS.record_id, SD.record_id_ctr, CS.street, CS.city, "
				+ " CS.exchange, C.town_tax_code, SD.low_street_range, "
				+ " SD.high_street_range, SD.level_ind, " 
				+ " SD.zip_code, SD.wire_center, SR.remarks"
				+ " FROM DB2LK001.City C, DB2LK001.City_Street CS, "
				+ " DB2LK001.Street_Details SD "
				+ " LEFT OUTER JOIN "
				+ " DB2LK001.Street_Remarks SR "
				+ " ON SR.record_id = SD.record_id "
				+ " AND SR.record_id_ctr = SD.record_id_ctr "
				+ " WHERE "
				+ " C.city = CS.city AND SD.record_id = CS.record_id ";
		
		if (isLike) {
			whereClause = " AND CS.street LIKE ?";
			streetName = streetName + "%";
		} else 
			whereClause = " AND CS.street = ?";		

		if (communityInfo != null && (communityInfo.town != null && communityInfo.town.length() > 0)
			&& (communityInfo.dflt_exchange != null && communityInfo.dflt_exchange.length() > 0)) 
		{
			whereClause =
				whereClause
					+ " AND CS.city = ? "
					+ " AND CS.exchange = ? "
					+ " ORDER BY CS.street, CS.city, CS.exchange, CS.record_id,"
					+ " SD.record_id_ctr, SR.remarks_ctr ";
			preparedStmt = sqlConnection.prepareStatement (sqlStatement.concat(whereClause));
			preparedStmt.setString(1, streetName.toUpperCase());
			preparedStmt.setString(2, communityInfo.town);
			preparedStmt.setString(3, communityInfo.dflt_exchange);
		} 
		else if (communityInfo != null &&
			communityInfo.town != null && communityInfo.town.length() > 0) 
		{
			whereClause =
				whereClause
					+ " AND CS.city = ? "
					+ " ORDER BY CS.street, CS.city, CS.exchange, CS.record_id,"
					+ " SD.record_id_ctr, SR.remarks_ctr ";
			preparedStmt = sqlConnection.prepareStatement (sqlStatement.concat(whereClause));

			preparedStmt.setString(1, streetName.toUpperCase());
			preparedStmt.setString(2, communityInfo.town);
		} 
		else if (communityInfo != null &&
			communityInfo.exchange != null && communityInfo.exchange.length() > 0) 
		{
			whereClause =
				whereClause
					+ " AND CS.exchange = ? "
					+ " ORDER BY CS.street, CS.city, CS.exchange, CS.record_id,"
					+ " SD.record_id_ctr, SR.remarks_ctr ";
			preparedStmt = sqlConnection.prepareStatement (sqlStatement.concat(whereClause));
			preparedStmt.setString(1, streetName.toUpperCase());
			preparedStmt.setString(2, communityInfo.exchange);
		} 
		else if (exchange != null && !exchange.equals ("")) 
		{
			whereClause =
				whereClause
					+ " AND CS.exchange = ? "
					+ " ORDER BY CS.street, CS.city, CS.exchange, CS.record_id,"
					+ " SD.record_id_ctr, SR.remarks_ctr ";
			preparedStmt = sqlConnection.prepareStatement (sqlStatement.concat(whereClause));
			preparedStmt.setString(1, streetName.toUpperCase());
			preparedStmt.setString(2, exchange);
		}

		logger.log (LogEventId.DEBUG_LEVEL_2,
			"RetrieveBRMSSagInfo::getBRMSSagInfo()| getPreparedStmt()|"
				+ "SQL cmd=<" + sqlStatement.concat(whereClause) + ">");
				
		return preparedStmt;
	}

	/**
	 * Method getTownDfltExchange.
	 * @param communityName String
	 * @return CommunityInfo []
	 * @throws SQLException
	 */
	public CommunityInfo[] getTownDfltExchange(String communityName)
		throws SQLException 
	{

		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		Vector retVec = new Vector();
		String sqlStatement =
			"SELECT DISTINCT town, dflt_exchange "
				+ " FROM DB2LK001.Community_Info "
				+ " WHERE name = ? AND NOT town = ''";
		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			"RetrieveBRMSSagInfo::getTownDfltExchange()| "
				+ "SQL cmd=<" + sqlStatement + ">");
		try 
		{
			getSqlResult (preparedStmt, resultSet, sqlStatement, communityName, retVec, false);
			logger.log (LogEventId.INFO_LEVEL_1, "RetrieveBRMSSagInfo::getTownDfltExchange()| "
					+ (retVec != null ? retVec.size() : 0) + " Rows retrieved.");
		} // end try
		catch (SQLException sql) 
		{
			logger.log (LogEventId.DEBUG_LEVEL_2,
				"RetrieveBRMSSagInfo::getTownDfltExchange()|Failed. " + sql.getMessage());
			throw sql;
		} 
		finally 
		{
			try 
			{
				if (resultSet != null)
					resultSet.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} 
			catch (Exception e) 
			{
				logger.log (LogEventId.DEBUG_LEVEL_2,
					"RetrieveBRMSSagInfo::getTownDfltExchange()|"
					+ "Exception occurred in finally with message." + e.getMessage());
			}
		}

		if (retVec != null && !retVec.isEmpty()) 
		{
			CommunityInfo[] communityInfo = new CommunityInfo[retVec.size()];
			return (CommunityInfo[]) retVec.toArray(communityInfo);
		} else
			return null;
	}

	/**
	 * Method getExchange.
	 * @param communityName String
	 * @return CommunityInfo []
	 * @throws SQLException
	 */
	public CommunityInfo[] getExchange (String communityName)
	throws SQLException 
	{
		PreparedStatement preparedStmt = null;
		ResultSet resultSet = null;
		Vector retVec = new Vector();
		String sqlStatement =
			"SELECT DISTINCT exchange "
				+ " FROM DB2LK001.Community_Info "
				+ " WHERE "
				+ " name = ? AND NOT exchange = ''";
		logger.log (LogEventId.DEBUG_LEVEL_2,
			"RetrieveBRMSSagInfo::getExchange()| "
			+ "SQL cmd=<" + sqlStatement + ">");
		try 
		{
			getSqlResult (preparedStmt, resultSet, sqlStatement, communityName, retVec, true);
			logger.log (LogEventId.INFO_LEVEL_1,
				"RetrieveBRMSSagInfo::getExchange()| " + (retVec != null ? retVec.size() : 0)
				+ " Rows retrieved.");
		} // end try
		catch (SQLException sql) 
		{
			logger.log (LogEventId.DEBUG_LEVEL_2,
				"RetrieveBRMSSagInfo::getExchange()|Failed. " + sql.getMessage());
			throw sql;
		} 
		finally 
		{
			try 
			{
				if (resultSet != null)
					resultSet.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} 
			catch (Exception e) 
			{
				logger.log (LogEventId.DEBUG_LEVEL_2,
					"RetrieveBRMSSagInfo::getExchange()|"
					+ "Exception occurred in finally with message." + e.getMessage());
			}
		}

		if (retVec != null && !retVec.isEmpty()) 
		{
			CommunityInfo[] communityInfo = new CommunityInfo[retVec.size()];
			return (CommunityInfo[]) retVec.toArray(communityInfo);
		} 
		else
			return null;
	}

	/**
	 * Method getSqlResult.
	 * @param preparedStmt PreparedStatement
	 * @param resultSet ResultSet
	 * @param sqlStatement String
	 * @param communityName String
	 * @param retVec Vector
	 * @param isExchange boolean
	 * @throws SQLException
	 */
	private void getSqlResult(
		PreparedStatement preparedStmt,
		ResultSet resultSet,
		String sqlStatement,
		String communityName,
		Vector retVec,
		boolean isExchange)
		throws SQLException {
		String town = null;
		String exchange = null;
		String dfltExchange = null;
		preparedStmt = sqlConnection.prepareStatement(sqlStatement);
		preparedStmt.setString(1, communityName);
		resultSet = preparedStmt.executeQuery();
		boolean flag = resultSet.next();
		logger.log(
			LogEventId.DEBUG_LEVEL_2,
			"CommunityName=[" + communityName + "]");

		for (; flag; flag = resultSet.next()) {

			CommunityInfo communityInfo = new CommunityInfo();
			if (!isExchange) {
				dfltExchange = resultSet.getString("DFLT_EXCHANGE");
				town = resultSet.getString("TOWN");
				if (dfltExchange != null)
					dfltExchange = dfltExchange.trim();
				if (town != null)
					town = town.trim();
				communityInfo.dflt_exchange = dfltExchange;
				communityInfo.town = town;
				communityInfo.exchange = "";
				logger.log(
					LogEventId.DEBUG_LEVEL_2,
					"TOWN=[" + town + "] DFLT_EXCHANGE=[" + dfltExchange + "]");
			} else {
				exchange = resultSet.getString("EXCHANGE");
				if (exchange != null)
					exchange = exchange.trim();
				communityInfo.exchange = exchange;
				communityInfo.dflt_exchange = "";
				communityInfo.town = "";
				logger.log(
					LogEventId.DEBUG_LEVEL_2,
					"EXCHANGE=[" + exchange + "]");
			}
			retVec.add(communityInfo);
		}
	}

	/**
	 * padStringStart() Pads string with char at beginning of string.
	 * Creation date: (11/11/03 3:41:22 PM)
	 * @param someString String
	 * @param length int
	 * @param pad char
	 * @return String
	 */
	private String padStringStart(String someString, int length, char pad) {
		if (someString == null)
			return someString;

		StringBuffer temp = new StringBuffer(someString);
		while (temp.length() < length)
			temp.insert(0, pad);
		return temp.toString();
	}	
}