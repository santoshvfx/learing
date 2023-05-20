// $Id: RetrieveLFACSSagLuByWireCntrAndStNm.java,v 1.13 2005/01/10 23:23:02 biscvsid Exp $

package com.sbc.eia.bis.lim.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.BusinessInterface.brms.BRMS;
import com.sbc.eia.bis.BusinessInterface.brms.BRMSTag;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;

/**
 * Contains methods to retrieve addresses data by Wire Center and Street Name,
 * from the lfacs_sag_lu_status table.
 * Creation date: (01/16/02 8:58:41 AM)
 * @author: David Brawley
 */
public class RetrieveLFACSSagLuByWireCntrAndStNm {

/**
 * Contains methods to retrieve address array by WireCenter and SteetName from an lfacs_sag_lu_status table.
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
public static LFACSSagLu [] getLFACSSagLuByWireCntrAndStNm (String aWireCenter, String aStreetName, 
	String aHouseNumber, String aCity, String aStructType, String aStructValue, 
    String aLevelType, String aLevelValue, String aUnitType, String aUnitValue, 
    Logger aLogger, DBConnection dbConn, Connection tblConnection) 
throws SQLException 
{
	LFACSSagLu[] LFACSSagLuArray = null;
	ArrayList LFACSSagLuList = new ArrayList();
	ArrayList unitList = new ArrayList();
	PreparedStatement preparedSql = null;
	ResultSet rsltSet   = null;
	AddressHandlerBRMS brmsAddr = new AddressHandlerBRMS();
	
	StringBuffer whereClause = new StringBuffer("WHERE LFACS_WIRECENTER=? AND STREET_NAME=?");	

	if (aHouseNumber != null)
		whereClause.append(" AND STREET_NUMBER" + (((aHouseNumber.trim()).length() > 0) ? "='" + aHouseNumber.toUpperCase().trim() + "'" : " IS NULL"));
	else
		whereClause.append(" AND STREET_NUMBER IS NULL");

    if (aStructType  != null &&
        aStructValue != null)
        whereClause.append(" AND STRUCTURE_TYPE" + 
               ((aStructType.trim().length() > 0) ? "='" + sqlMarkUp(aStructType.toUpperCase().trim()) + "'" : " IS NULL") 
               +  " AND LU_BLDG" + 
               ((aStructValue.trim().length() > 0) ? "='" + sqlMarkUp(aStructValue.toUpperCase().trim()) + "'" : " IS NULL"));
    else
        whereClause.append(" AND STRUCTURE_TYPE IS NULL AND LU_BLDG IS NULL");

    if (aLevelType  != null &&
        aLevelValue != null)
        whereClause.append(" AND ELEVATION_TYPE" + 
               ((aLevelType.trim().length() > 0) ? "='" + sqlMarkUp(aLevelType.toUpperCase().trim()) + "'" : " IS NULL") 
               +  " AND LU_FLOOR" + 
               ((aLevelValue.trim().length() > 0) ? "='" + sqlMarkUp(aLevelValue.toUpperCase().trim()) + "'" : " IS NULL"));
    else
        whereClause.append(" AND ELEVATION_TYPE IS NULL AND LU_FLOOR IS NULL");

    if (aUnitType  != null &&
        aUnitValue != null)
        whereClause.append(" AND UNIT_TYPE" + 
               ((aUnitType.trim().length() > 0) ? "='" + sqlMarkUp(aUnitType.toUpperCase().trim()) + "'" : " IS NULL") 
               +  " AND LU_UNIT" + 
               ((aUnitValue.trim().length() > 0) ? "='" + sqlMarkUp(aUnitValue.toUpperCase().trim()) + "'" : " IS NULL"));
    else
        whereClause.append(" AND UNIT_TYPE IS NULL AND LU_UNIT IS NULL");

	// Currently LIM always enters NULL for city parameter, 
	// This statement provided to minimize changes if required at future date	
	if (aCity != null){
		whereClause.append(" AND TOWN" + (((aCity.trim()).length() > 0) ? "='" + sqlMarkUp(aCity.toUpperCase().trim()) + "'" : " IS NULL"));
	}
		
	String orderClause = " ORDER BY NVL(TOWN,' '), NVL(STREET_NUMBER_SIZE,' '), NVL(STREET_NUMBER,' '), NVL(LU_BLDG,' '), NVL(LU_FLOOR,' '), NVL(LU_UNIT,' ')";	
		
	String sqlSelect =
	"SELECT LFACS_WIRECENTER, STREET_NAME, STREET_NUMBER, " + 
	"STRUCTURE_TYPE, LU_BLDG, ELEVATION_TYPE, LU_FLOOR, UNIT_TYPE, LU_UNIT, " + 
	"TOWN, CIRCUIT_ID, LP_STATUS " +
	"FROM LFACS_SAG_LU_STATUS ";
	
	String SQLstatement = sqlSelect + whereClause + orderClause;
	
	//log variable
	int index = whereClause.toString().indexOf((int)'?');
	int index2 = whereClause.toString().indexOf((int)'?',index+1);
	StringBuffer logableStmt = whereClause;				
	logableStmt.deleteCharAt(index); logableStmt.insert(index,aWireCenter.toUpperCase()+' ');
	logableStmt.deleteCharAt(index2+ aWireCenter.length()); 
    logableStmt.insert(index2+ aWireCenter.length(), aStreetName.toUpperCase());

	aLogger.log( LogEventId.DEBUG_LEVEL_2, "SQL cmd=<"+sqlSelect+logableStmt+orderClause+">" );
			
	try {
		// access the Oracle lfacs_sag_lu table.
		//
		aLogger.log (LogEventId.DEBUG_LEVEL_2, "Looking for: " + logableStmt);  
		preparedSql = tblConnection.prepareStatement (SQLstatement);
		preparedSql.setString(1, aWireCenter.toUpperCase().trim()); //wireCenter var
		preparedSql.setString(2, aStreetName.toUpperCase().trim()); //streetName var
		
        aLogger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(),
                          dbConn.getUserId(), "SELECT FROM LFACS_SAG_LU_STATUS " + logableStmt);
		rsltSet = preparedSql.executeQuery();
		
        aLogger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(),
                          dbConn.getUserId(), "SELECT FROM LFACS_SAG_LU_STATUS " + logableStmt);

		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Found the following addresses:" );
        String wireCenter   = "";
		String stNbr		= "";
		String stNm			= "";
		String town			= "";
		String state		= "";
        String structType   = "";
        String structValue  = "";
        String levelType    = "";
        String levelValue   = "";
        String unitType     = "";
        String unitValue    = "";
		String circuitId	= "";
		String lpStatus		= "";
		
		while (rsltSet.next())
		{
			wireCenter  =  rsltSet.getString("LFACS_WIRECENTER");
            stNm        =  rsltSet.getString("STREET_NAME");
            stNbr       = (rsltSet.getString("STREET_NUMBER") == null) ? "" : rsltSet.getString("STREET_NUMBER");
			town		= (rsltSet.getString("TOWN") == null) ? "": rsltSet.getString("TOWN");
            structType  = (rsltSet.getString("STRUCTURE_TYPE") == null) ? "": rsltSet.getString("STRUCTURE_TYPE");
            structValue = (rsltSet.getString("LU_BLDG") == null) ? "": rsltSet.getString("LU_BLDG");
            levelType   = (rsltSet.getString("ELEVATION_TYPE") == null) ? "": rsltSet.getString("ELEVATION_TYPE");
            levelValue  = (rsltSet.getString("LU_FLOOR") == null) ? "": rsltSet.getString("LU_FLOOR");
            unitType    = (rsltSet.getString("UNIT_TYPE") == null) ? "": rsltSet.getString("UNIT_TYPE");
            unitValue   = (rsltSet.getString("LU_UNIT") == null) ? "": rsltSet.getString("LU_UNIT");
 			circuitId	= (rsltSet.getString("CIRCUIT_ID") == null) ? "" : rsltSet.getString("CIRCUIT_ID");
			lpStatus	= (rsltSet.getString("LP_STATUS") == null) ? "" : rsltSet.getString("LP_STATUS");
				
			aLogger.log (LogEventId.DEBUG_LEVEL_2, wireCenter + " " +
				stNbr + " " + stNm + " " + town + " " + state + " " +
				structType + " " + structValue + " " + levelType + " " + levelValue + " " +
				unitType   + " " + unitValue   + " " + circuitId + " " + lpStatus);

		  	brmsAddr.setBRMSFields(stNbr, stNm, town, BRMSTag.CT, "",
                structType, structValue, levelType, levelValue, unitType, unitValue);
			LFACSSagLuList.add(new LFACSSagLu(getAddress(brmsAddr), circuitId, lpStatus));
		} // end while

	
		if (LFACSSagLuList.size() > 0){
			LFACSSagLuArray = new LFACSSagLu [LFACSSagLuList.size()];
			for (int i = 0; i < LFACSSagLuList.size(); i++)
			{
				LFACSSagLuArray[i] = (LFACSSagLu) LFACSSagLuList.get (i);	
			}
			LFACSSagLuList.clear();
		}
		else{			
			aLogger.log (LogEventId.DEBUG_LEVEL_2, "No address records found for LFACS WireCenter=" + aWireCenter + 
				" HouseNumber=" + aHouseNumber + " StreetName=" + aStreetName + 
				" StructureType=" + aStructType + " StructureValue=" + aStructValue + 
                " LevelType=" + aLevelType + " LevelValue=" + aLevelValue +
                " UnitType=" + aUnitType + " UnitValue=" + aUnitValue );
			LFACSSagLuArray = new LFACSSagLu [0];
		}

	} // end try
	catch (AddressHandlerException ahe){}
	catch (SQLException e) {
		e.printStackTrace();
		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get Address by Wire Center and Street Name failed." );
		throw e;
			
	} finally {
		try {
			if (rsltSet != null)
				rsltSet.close();
			if (preparedSql != null)
				preparedSql.close();
		} catch (Exception e) {
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "getLFACSSagLuByWireCntrAndStNm: Exception occured in finally with message " + e.getMessage());
		}
	}
	
	return LFACSSagLuArray;
}

/**
 * WARNING --- In order to use this method, you'll need to create new properties file.
 * =======
 * Gets a database connection and calls getAddrByWireCntrAndStNm method to retrieve
 * the Address Data by Street Name and Wire Center from the lfacs_sag_lu_status table
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
 * @return Address[]
 * @param aFile String
 * @param wireCenter String
 * @param streetName String
 * @param houseNumber String
 * @param city String
 * @param units Unit[]
 * @param aLogger Logger
 * @exception SQLException If the DBConnection method or the getAddrByWireCntrAndStNm
 * method detects an exception, SQLException will be thrown or rethrown.
 */
public static LFACSSagLu [] retrieveLFACSSagLuByWireCntrAndStNm (String aFile,  
		String wireCenter, String streetName, String houseNumber, String city, 
        String structType, String structValue, String levelType, String levelValue,
        String unitType, String unitValue, Logger aLogger) 
throws SQLException
{
	DBConnection dbConn = null;
	DataSource dsHome = null;
	Connection tblConnection = null;
	
	LFACSSagLu [] retVal = null;
	
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
			
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                "DBConnection::getConnection()|PRE");           
            tblConnection = dbConn.getConnection();
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                "DBConnection::getConnection()|POST");           

		} else {
			aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection... ");
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                "DBConnection::getDSConnection()|PRE");           
            tblConnection = dbConn.getDSConnection( "", 
										     		dbConn.getUserId(),
										     		dbConn.getPassWord()
										     		//"",
										     		//"" 
										     		);
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                "DBConnection::getDSConnection()|POST");           
		
        }
        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
            "RetrieveLFACSSagLuByWireCntrAndStNm::getLFACSSagLuByWireCntrAndStNm()|PRE");           
		retVal = getLFACSSagLuByWireCntrAndStNm (wireCenter, streetName, houseNumber, city, 
                    structType, structValue, levelType, levelValue, unitType, unitValue, 
                    aLogger, dbConn, tblConnection );
        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
            "RetrieveLFACSSagLuByWireCntrAndStNm::getLFACSSagLuByWireCntrAndStNm()|POST");           

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
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveLFACSSagLuByWireCntrAndStNm: DBConnection disconnect failed in finally with message " + e.getMessage());
		}
	}
	return retVal;
}

/**
 * Gets a database connection and calls getAddrByWireCntrAndStNm method to retrieve
 * the Address Data by Street Name and Wire Center from the lfacs_sag_lu_status table.
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
 * @return Address[]
 * @param aProperty Properties
 * @param wireCenter String
 * @param streetName String
 * @param houseNumber String
 * @param city String
 * @param units Unit[]
 * @param aLogger Logger
 * @exception SQLException If the DBConnection method or the getAddrByWireCntrAndStNm
 * method detects an exception, SQLException will be thrown or rethrown.
 * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
 */
public static LFACSSagLu [] retrieveLFACSSagLuByWireCntrAndStNm (Properties aProperty, 
	String wireCenter, String streetName, String houseNumber, String city, 
    String structType, String structValue, String levelType, String levelValue,
    String unitType, String unitValue, Logger aLogger) 
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

	LFACSSagLu [] retVal = null;

	boolean bRetry  = true;
	int iRetryCount = 0;

	try {
		while( bRetry ) {
			try {
				/**
				 * Passes the JDBC access related properties to DBConnection() to
				 * access the SYBASE-MORTEL database.
				 */
				aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveAddrByWireCntrAndStNm: Calling DBConnection...");

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
					
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                        "DBConnection::getConnection()|PRE");           
                    tblConnection = dbConn.getConnection();
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                        "DBConnection::getConnection()|POST");           
				} 
                else {
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection...");
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                        "DBConnection::getDSConnection()|PRE");           
                    tblConnection = dbConn.getDSConnection( jdbcDataSourceName, 
										     				jdbcUsrId,
										     				jdbcPassWord );
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                        "DBConnection::getDSConnection()|POST");           

				}
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                    "RetrieveLFACSSagLuByWireCntrAndStNm::getLFACSSagLuByWireCntrAndStNm()|PRE");           
				retVal = getLFACSSagLuByWireCntrAndStNm (wireCenter, streetName, houseNumber, city, 
                            structType, structValue, levelType, levelValue, unitType, unitValue,
                            aLogger, dbConn, tblConnection );
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveLFACSSagLuByWireCntrAndStNm::retrieveLFACSSagLuByWireCntrAndStNm()|" +
                    "RetrieveLFACSSagLuByWireCntrAndStNm::getLFACSSagLuByWireCntrAndStNm()|POST");           

				bRetry = false;									// done
			}
			catch (StaleConnectionException e) {
				aLogger.log( LogEventId.INFO_LEVEL_2, "retrieveAddrByWireCntrAndStNm: Get DBConnection failed with StaleConnection exception. RetryCount<" + iRetryCount + ">");
				if ( iRetryCount++ > 1) {
					bRetry = false;								// done
					e.printStackTrace();
					aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveAddrByWireCntrAndStNm: Get DBConnection failed with StaleConnection exception.");
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
			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveLFACSSagLuByWireCntrAndStNm: DBConnection disconnect failed in finally with message " + e.getMessage());
		}
	}
	return retVal;
}

/**
 * Get Address from data extracted from the LFACS_SAG_LU_STATUS table.
 * Creation date: (1/22/02 12:32:35 PM)
 * @return Address
 * @param brmsAddr AddressHandlerBRMS
 * @param unitList ArrayList
 */
private static Address getAddress(AddressHandlerBRMS brmsAddr) 
{

    FieldedAddress fa = new FieldedAddress(
        IDLUtil.toOpt (brmsAddr.getRoute()),
        IDLUtil.toOpt (brmsAddr.getBox()),
        IDLUtil.toOpt (brmsAddr.getHousNbrPfx()),
        IDLUtil.toOpt (brmsAddr.getHousNbr()),
        IDLUtil.toOpt (brmsAddr.getAasgndHousNbr()),
        IDLUtil.toOpt (brmsAddr.getHousNbrSfx()),
        IDLUtil.toOpt (brmsAddr.getStDir()),
        IDLUtil.toOpt (brmsAddr.getStName()),
        IDLUtil.toOpt (brmsAddr.getStThorofare()),
        IDLUtil.toOpt (brmsAddr.getStNameSfx()),
        IDLUtil.toOpt (brmsAddr.getCity()),
        IDLUtil.toOpt (brmsAddr.getState()),
        IDLUtil.toOpt (brmsAddr.getPostalCode()),
        IDLUtil.toOpt (""),              // PostalCodePlus4
        IDLUtil.toOpt (brmsAddr.getCounty()),
        IDLUtil.toOpt (brmsAddr.getCountry()),
        IDLUtil.toOpt (brmsAddr.getStructType()),
        IDLUtil.toOpt (brmsAddr.getStructValue()),
        IDLUtil.toOpt (brmsAddr.getLevelType()),
        IDLUtil.toOpt (brmsAddr.getLevelValue()),
        IDLUtil.toOpt (brmsAddr.getUnitType()),
        IDLUtil.toOpt (brmsAddr.getUnitValue()),
        IDLUtil.toOpt (""),              // OriginalStreetDirection
        IDLUtil.toOpt (""),              // OriginalStreetNameSuffix
        IDLUtil.toOpt (new String[0]),   // CassAddressLines
        IDLUtil.toOpt (new String[0]),   // AuxiliaryAddressLines
        IDLUtil.toOpt (""),              // CassAdditionalInfo
        IDLUtil.toOpt (brmsAddr.getAddAddrInfo()),
        IDLUtil.toOpt (""),
        IDLUtil.toOpt(""),				
		IDLUtil.toOpt(""),
		IDLUtil.toOpt(""),
		IDLUtil.toOpt(""),
		IDLUtil.toOpt(""),
		IDLUtil.toOpt(""));             // BusinessName

    Address newAddress = new Address ();
    newAddress.aFieldedAddress(fa);
    return newAddress;
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